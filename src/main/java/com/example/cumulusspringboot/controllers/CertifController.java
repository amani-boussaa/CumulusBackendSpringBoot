package com.example.cumulusspringboot.controllers;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.requests.assignCertifToUserReq;
import com.example.cumulusspringboot.services.CertifService;
import com.example.cumulusspringboot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

}
