package com.example.cumulusspringboot.services;
import com.example.cumulusspringboot.repositories.UserRepo;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.Certif;
import com.example.cumulusspringboot.interfaces.ICertifService;
import com.example.cumulusspringboot.repositories.CertifRepo;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CertifService implements ICertifService {
    CertifRepo certifRepo;
    UserRepo userRepo;
    // *****************************************************************************************************
    @Override
    public List<Certif> getAllCertifs() {
        return certifRepo.findAll();
    }
    // *****************************************************************************************************
    public Certif getCertifById(Long id) {
        return certifRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No course found with id " + id));
    }
    // *****************************************************************************************************
    public Certif createCertif(Certif certif) {
        return certifRepo.save(certif);
    }
    // *****************************************************************************************************
    @Override
    public Certif updateCertif(long id, Certif certif) {
        Optional<Certif> certifOpt = certifRepo.findById(id);
        return certifOpt.map(existingCertif -> {
            existingCertif.setName(certif.getName());
            existingCertif.setDescription(certif.getDescription());
            existingCertif.setPrice(certif.getPrice());
            // set other fields as needed

            return certifRepo.save(existingCertif);
        }).orElse(null);
    }
    // *****************************************************************************************************
    public boolean deleteCertif(Long id) {
        if (!certifRepo.existsById(id)) {
            return false;
        }
        certifRepo.deleteById(id);
        return true;
    }
    // *****************************************************************************************************

}



