package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.CategoryComplaint;
import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IComplaintService;
import com.example.cumulusspringboot.repositories.CategoryComplaintRepository;
import com.example.cumulusspringboot.repositories.ComplaintRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryComplaintRepository categoryComplaintRepository;

    @Override
    public Complaint createComplaint(Long iduser, Long idcat, Complaint complaint) {

        Complaint Complaint2 =  complaintRepository.save(complaint);
        assignUserToComplaint(iduser, Complaint2.getId());
        assignCategoryToComplaint(idcat, Complaint2.getId());
        return Complaint2;
    }

    @Override
    public Complaint updateComplaint(Long id, Complaint complaint) {
        Complaint existingComplaint = complaintRepository.findById(id).orElse(null);
        if (existingComplaint != null) {
            existingComplaint.setDescription(complaint.getDescription());
            existingComplaint.setStatus(complaint.getStatus());
            complaint.setCategorycomplaint(complaint.getCategorycomplaint());
            return complaintRepository.save(existingComplaint);
        }
        return null;
    }

    @Override
    public Complaint getComplaintById(Long id) {
        return complaintRepository.findById(id).orElse(null);
    }

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public List<Complaint> getComplaintsByUser(User user) {
        return complaintRepository.findByUser(user);
    }

    @Override
    public void assignUserToComplaint(Long iduser, Long idcomplaint) {
        User user = userRepository.findById(iduser).orElse(null);
        Complaint complaint = complaintRepository.findById(idcomplaint).orElse(null);
        complaint.setUser(user);
        complaintRepository.save(complaint);
    }

    @Override
    public void assignCategoryToComplaint(Long idcat, Long idcomplaint) {
        CategoryComplaint CategoryComplaint = categoryComplaintRepository.findById(idcat).orElse(null);
        Complaint complaint = complaintRepository.findById(idcomplaint).orElse(null);
        complaint.setCategorycomplaint(CategoryComplaint);
        complaintRepository.save(complaint);
    }
}
