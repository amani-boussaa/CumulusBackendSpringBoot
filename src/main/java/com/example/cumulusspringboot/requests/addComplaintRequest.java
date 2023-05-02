package com.example.cumulusspringboot.requests;

import com.example.cumulusspringboot.entities.StatusComplaint;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class addComplaintRequest {
    private Long id;
    private String description;
    private StatusComplaint status;

}
