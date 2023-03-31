package com.example.cumulusspringboot.requests;

import com.example.cumulusspringboot.entities.StatusComplaint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplaintRequest {
    private String description;
    private StatusComplaint status;
    private Long category;
    private Long user;

}
