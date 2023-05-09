package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.PasswordResetToken;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.exception.GenericResponse;
import com.example.cumulusspringboot.requests.ForgotPasswordRequest;
import com.example.cumulusspringboot.requests.ResetPasswordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

public interface IPasswordResetTokenService {
     String generateToken();
    public void createPasswordResetTokenForUser(User user, String token);

    public ResponseEntity<String> resetPassword(String token, String newPassword);
    public void deletePasswordResetToken(PasswordResetToken passwordResetToken) ;
    public PasswordResetToken findByToken(String token);
    public void updateUserPassword(User user, String newPassword);

    public ResponseEntity<GenericResponse> forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    public ResponseEntity<GenericResponse> resetPassword(ResetPasswordRequest resetPasswordRequest);
    }
