package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Role;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.provider.JwtTokenProvider;
import com.example.cumulusspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
     UserRepository userRepository;

    @Autowired
     AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private EmailService emailService;
//    @Autowired
//    private VerificationTokenRepository verificationTokenRepository;


    //    @Autowired
//    private JavaMailSender javaMailSender;
 //   @Override
//    public User register(User user) {
//        // Check if user with same username or email already exists
//        if (userRepository.findByUsername(user.getUsername()) != null ||
//                userRepository.findByEmail(user.getEmail()) != null) {
//            throw new IllegalArgumentException("User with same username or email already exists");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(Role.USER);
//        User savedUser = userRepository.save(user);
//        String token = UUID.randomUUID().toString();
//        VerificationToken verificationToken = new VerificationToken(savedUser, token);
//        verificationTokenRepository.save(verificationToken);
//        sendVerificationEmail(savedUser.getEmail(), token);
//        return savedUser;
//    }
    @Override
    public User register(User user) {
        // Check if user with same username or email already exists
        if (userRepository.findByUsername(user.getUsername()) != null ||
                userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with same username or email already exists");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        String token = UUID.randomUUID().toString();
        String refreshToken = UUID.randomUUID().toString();
        Instant now = Instant.now();
        Instant expiryDate = now.plus(Duration.ofHours(24));
        user.setToken(token);
        user.setExpiryDate(Date.from(expiryDate));
        user.setRefreshToken(refreshToken);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);


        String appUrl = "http://localhost:8081/CUMULUS/api/authiorized/verify?token=";
        String emailSubject = "Confirm your registration";
        String emailBody = "Bonjour "+user.getName()+"\n\nTo confirm your registration, please click the following link below:\n\n"
                + appUrl + token+"\n\nSincerly";
        emailService.sendEmail(user.getEmail(), emailSubject, emailBody);
        return savedUser;
    }

//    private void sendVerificationEmail(String recipientEmail, String token) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(recipientEmail);
//        message.setSubject("Verify Your Email Address");
//        message.setText("To verify your email address, please click the link below:\n\n"
//                + "http://localhost:8081/CUMULUS/api/users/verify?token=" + token);
//        javaMailSender.send(message);
//    }

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

//    public boolean verifyUser(String token) {
//        User user = userRepository.findByToken(token);
//        if (user != null) {
//            user.setVerified(true);
//            userRepository.save(user);
//            return true;
//        }
//        return false;
//    }
public boolean verifyUser(String token) {
    User user = userRepository.findByToken(token);
    if (user != null) {
        Instant now = Instant.now();
        if (user.getExpiryDate().toInstant().isBefore(now)) {
            userRepository.delete(user);
            return false;
        }
        user.setVerified(true);
        refreshToken(user);
        userRepository.save(user);
        return true;
    }
    return false;
}

    public void refreshToken(User user) {
        String refreshToken = UUID.randomUUID().toString();
        user.setRefreshToken(refreshToken);
        userRepository.save(user);
    }
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        try {
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
