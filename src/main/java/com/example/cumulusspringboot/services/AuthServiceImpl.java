package com.example.cumulusspringboot.services;
import com.example.cumulusspringboot.entities.Category;
import com.example.cumulusspringboot.entities.Role;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.exception.CumulusAPIException;
import com.example.cumulusspringboot.interfaces.AuthService;
import com.example.cumulusspringboot.payload.LoginDto;
import com.example.cumulusspringboot.payload.RegisterDto;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.security.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail()).orElseThrow(() -> new CumulusAPIException(HttpStatus.BAD_REQUEST,"User not found"));
        if (!user.getVerified()) {
            throw new CumulusAPIException(HttpStatus.BAD_REQUEST,"User not verified");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication,user);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new CumulusAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new CumulusAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }
        User user = modelMapper.map(registerDto, User.class);

        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        user.setRole(Role.ROLE_STUDENT);
        //new ads
        String token = UUID.randomUUID().toString();

        // set verification token in user object
        user.setVerificationToken(token);
        Instant now = Instant.now();
        Instant expiryDate = now.plus(Duration.ofHours(24));
        user.setExpiryDate(Date.from(expiryDate));
        user.setVerified(false);
        User savedUser = userRepository.save(user);





        String appUrl = "http://localhost:8081/CUMULUS/api/v1/auth/verify?token=";
        String emailSubject = "Confirm your registration";
        String emailBody ="Dear "+user.getUsername()+",\n" +
                "\n" +
                "Thank you for signing up with CUMULUS! Before we can activate your account, we need to verify your email address.\n" +
                "\n" +
                "Please use the following token to complete the verification process:\n" +
                "\n"
                + appUrl + token
                +"\n" +
                "\n" +
                "This token will expire in 24 hours, so please make sure to verify your email address as soon as possible. If you do not verify your email within 24 hours, your account will be automatically deleted for security reasons.\n" +
                "\n" +
                "If you did not sign up for an account with CUMULUS, please ignore this email.\n" +
                "\n" +
                "Thank you for choosing CUMULUS. We look forward to serving you!\n" +
                "\n" +
                "Best regards,\n" +
                "CUMULUS Team";
//        String emailBody = "To confirm your registration, please click the following link below:\n\n"
//                + appUrl + token;
        emailService.sendEmail(user.getEmail(), emailSubject, emailBody);


        return "User registered successfully!.";
    }

    @Override
    public boolean verifyUser(String token) {
        User user = userRepository.findByVerificationToken(token);
        if (user != null) {
            Instant now = Instant.now();
            if (user.getExpiryDate().toInstant().isBefore(now)) {
                userRepository.delete(user);
                return false;
            }
            user.setVerified(true);
//            refreshToken(user);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
