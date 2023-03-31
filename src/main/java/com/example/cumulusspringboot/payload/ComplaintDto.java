package com.example.cumulusspringboot.payload;

import com.example.cumulusspringboot.entities.CategoryComplaint;
import com.example.cumulusspringboot.entities.StatusComplaint;
import com.example.cumulusspringboot.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintDto {
     Long id;
     String description;
     StatusComplaint status;
}
