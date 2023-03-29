package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IComplaintService;
import com.example.cumulusspringboot.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    private IComplaintService complaintService;

    @Autowired
    private IUserService userService;
    @PostMapping("/{iduser}/{idcat}")
    public ResponseEntity<Complaint> createComplaint(@PathVariable Long iduser, @PathVariable Long idcat, @RequestBody Complaint complaint) {
        Complaint createdComplaint = complaintService.createComplaint(iduser, idcat, complaint);
        return ResponseEntity.ok(createdComplaint);
    }

    @PutMapping("/{id}")
    public Complaint updateComplaint(@PathVariable Long id, @RequestBody Complaint complaint) {
        return complaintService.updateComplaint(id, complaint);
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
}

