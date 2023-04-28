package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.Course;
import com.example.cumulusspringboot.exception.ResourceNotFoundException;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.repositories.CertifRepo;
import com.example.cumulusspringboot.repositories.CourseRepo;
import com.example.cumulusspringboot.requests.assignCertifToUserReq;
import com.example.cumulusspringboot.services.CertifService;
import com.example.cumulusspringboot.services.EmailService;
import com.example.cumulusspringboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/certifs")
@AllArgsConstructor
public class CertifController {

    // *****************************************************************************************************
    private final CertifService certifService;
    private final UserService userService;
    private final CertifRepo certifRepo;

    private final EmailService emailService;


    // *****************************************************************************************************
    @GetMapping("/getAllCertifs")
    public List<Certif> getAllCertifs() {
        return certifService.getAllCertifs();
    }
    // *****************************************************************************************************
    @GetMapping("/certifs/{id}")
    public ResponseEntity<Certif> getCertifById(@PathVariable Long id) {
        Certif certif = certifService.getCertifById(id);
        if (certif == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(certif, HttpStatus.OK);
    }

    // *****************************************************************************************************
    @PostMapping("/createCertif")
    public ResponseEntity<Certif> createCertif(@RequestBody Certif certif) {
        Certif createdCertif = certifService.createCertif(certif);
        return new ResponseEntity<>(createdCertif, HttpStatus.CREATED);
    }
    // *****************************************************************************************************
    @PutMapping("/updateCertif/{id}")
    public ResponseEntity<Certif> updateCertif(@PathVariable("id") long id, @RequestBody Certif certif) {
        Certif updatedCertif = certifService.updateCertif(id, certif);
        if (updatedCertif == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCertif, HttpStatus.OK);
    }
    // *****************************************************************************************************
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCertif(@PathVariable Long id) {
        boolean isDeleted = certifService.deleteCertif(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // *****************************************************************************************************
    @PostMapping("/assignCertifToUser")
    public Certif assignCertifToUser(@RequestBody assignCertifToUserReq assignCertifToUserReq) {
        return userService.assignCertifToUser(assignCertifToUserReq.getNumCertif(), assignCertifToUserReq.getNumUser());
    }

    @PostMapping("/{id}/file")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        return certifService.uploadFile(id,file);
    }
    @GetMapping("/getblobfile/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Certif certif = certifRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("certif", "id", id));

        byte[] filePath = certif.getFilePath();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);


        return new ResponseEntity<>(filePath, headers, HttpStatus.OK);

    }


    @PostMapping("/sendEmailWithAttachment")
    public ResponseEntity<?> sendEmailWithAttachment(@RequestParam String toEmail,
                                                     @RequestParam String body,
                                                     @RequestParam String subject,
                                                     @RequestParam String attachment) {
        try {
            emailService.sendMailWithAttachment(toEmail, body, subject, attachment);
            return ResponseEntity.ok("Email sent with attachment successfully.");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email with attachment: " + e.getMessage());
        }
    }



}
