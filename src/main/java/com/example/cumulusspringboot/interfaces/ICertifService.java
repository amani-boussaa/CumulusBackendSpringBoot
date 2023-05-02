package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Certif;

import java.util.List;

public interface ICertifService {
    List<Certif> getAllCertifs();
    Certif getCertifById(Long id);
    Certif createCertif(Certif certif);
    Certif updateCertif(long id, Certif certif);
    boolean deleteCertif(Long id);
    //Certif assignCertifToUser(Long numCertif , Long numUser);




}
