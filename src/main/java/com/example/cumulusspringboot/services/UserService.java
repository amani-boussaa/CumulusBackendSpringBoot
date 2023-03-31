package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.repositories.CertifRepo;
import com.example.cumulusspringboot.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService  implements IUserService {
    UserRepo userRepo;
    CertifRepo certifRepo;

    @Override
    public List<User> retrieveAllUsers() {
        return userRepo.findAll();
    }


    public Certif assignCertifToUser(Long numCertif, Long numUser) {
        try {
            Certif certif = certifRepo.findById(numCertif).orElseThrow(() -> new NoSuchElementException("Certif not found"));
            System.out.println("amani");
            User user = userRepo.findById(numUser).orElseThrow(() -> new NoSuchElementException("User not found"));
            user.getCertifs().add(certif);
            certifRepo.save(certif);
            return certif;
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving books", e);
        }
    }
}