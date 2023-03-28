package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.PasswordResetToken;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.exception.CumulusAPIException;
import com.example.cumulusspringboot.exception.GenericResponse;
import com.example.cumulusspringboot.interfaces.IPasswordResetTokenService;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.repositories.PasswordResetTokenRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.requests.ForgotPasswordRequest;
import com.example.cumulusspringboot.requests.ResetPasswordRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@AllArgsConstructor
public class PasswordResetTokenServiceImpl implements IPasswordResetTokenService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    IUserService userService;
    JavaMailSender mailSender;
    MessageSource messageSource;
    Environment env;


    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public String generateToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    public ResponseEntity<String> resetPassword(String token, String newPassword) {
        return null;
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    public void deletePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public void updateUserPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public ResponseEntity<GenericResponse> forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userService.findUserByEmail(forgotPasswordRequest.getEmail());
        if (user == null) {
            throw new CumulusAPIException(HttpStatus.NOT_FOUND,"User Not Found");
        }
        String token = UUID.randomUUID().toString();
        this.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail("http://localhost:4200/auth/reset-password",
                Locale.US, token, user));
        String message = messageSource.getMessage("message.resetPasswordEmail", null, Locale.US);
        return ResponseEntity.ok(new GenericResponse(message));
    }

    @Override
    public ResponseEntity<GenericResponse> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        String token = resetPasswordRequest.getToken();
        String newPassword = resetPasswordRequest.getNewPassword();
        PasswordResetToken passwordResetToken = this.findByToken(token);
        if (passwordResetToken == null || passwordResetToken.isExpired()) {
            return ResponseEntity.badRequest().body(new GenericResponse("Invalidpasswordresettoken or token expired"));
        }
        User user = passwordResetToken.getUser();
        this.updateUserPassword(user, newPassword);
        this.deletePasswordResetToken(passwordResetToken);
        return ResponseEntity.ok(new GenericResponse("Password has been successfully reset."));
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, User user) {
        String url = contextPath + "/" + token;
        String message = messageSource.getMessage("message.resetPassword",
                null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body,
                                             User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }
}
