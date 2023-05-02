package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.exception.GenericResponse;
import com.example.cumulusspringboot.interfaces.IPasswordResetTokenService;
import com.example.cumulusspringboot.requests.ForgotPasswordRequest;
import com.example.cumulusspringboot.requests.ResetPasswordRequest;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ResetPasswordController {
    IPasswordResetTokenService IPasswordResetTokenService;

    @PostMapping("/auth/forgot-password")

    public ResponseEntity<GenericResponse> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return IPasswordResetTokenService.forgotPassword(forgotPasswordRequest);
    }

    @PostMapping("/auth/reset-password")
    public ResponseEntity<GenericResponse> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return IPasswordResetTokenService.resetPassword(resetPasswordRequest);
    }

}
