package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.config.TwilioConfig;
import com.example.cumulusspringboot.entities.CategoryComplaint;
import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.StatusComplaint;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IComplaintService;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.requests.ComplaintRequest;
import com.example.cumulusspringboot.services.EmailService;
import com.twilio.Twilio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
@RestController
@RequestMapping("/complaints")
@AllArgsConstructor
public class ComplaintController {
    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private IUserService userService;


    @PostMapping
    public ResponseEntity<Complaint> createComplaint(@RequestBody ComplaintRequest complaintRequest) {
        complaintRequest.setStatus(StatusComplaint.NEW);
        Complaint createdComplaint = complaintService.createComplaint(complaintRequest);
        System.out.println("kkk");

        return ResponseEntity.ok(createdComplaint);
    }

    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody ComplaintRequest complaintRequest) {
        return complaintService.updateComplaint(id, complaintRequest);
    }

    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable Long id) {
        return complaintService.getComplaintById(id);
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @GetMapping("/user/{userId}")
    public List<Complaint> getComplaintsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return complaintService.getComplaintsByUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeComplaint(@PathVariable Long id) {
        return complaintService.removeComplaint(id);
    }
}

