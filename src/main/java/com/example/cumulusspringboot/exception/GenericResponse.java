package com.example.cumulusspringboot.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private String message;
    private String error;

    public GenericResponse(String message) {
        super();
        this.message = message;
    }


}