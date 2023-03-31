package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Category;
import com.example.cumulusspringboot.entities.CategoryComplaint;
import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IComplaintService;
import com.example.cumulusspringboot.payload.ComplaintDto;
import com.example.cumulusspringboot.repositories.CategoryComplaintRepository;
import com.example.cumulusspringboot.repositories.ComplaintRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.requests.ComplaintRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryComplaintRepository categoryComplaintRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
//    public Complaint createComplaint(Long iduser, Long idcat, ComplaintDto complaintDto) {
    public Complaint createComplaint(ComplaintRequest complaintRequest) {
//        Complaint complaint = modelMapper.map(complaintRequest, Complaint.class);
//        Complaint savedComplaint = complaintRepository.save(complaint);
        CategoryComplaint category = categoryComplaintRepository.findById(complaintRequest.getCategory())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        User user = userRepository.findById(complaintRequest.getUser())
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Create a new complaint object
        Complaint complaint = new Complaint();
        complaint.setDescription(complaintRequest.getDescription());
        complaint.setStatus(complaintRequest.getStatus());
        complaintRepository.save(complaint);
        assignUserToComplaint(complaintRequest.getUser(), complaint.getId());
        assignCategoryToComplaint(complaintRequest.getCategory(), complaint.getId());
        return complaint;
    }

    @Override
//    public Complaint updateComplaint(Long id, Complaint complaint) {
    public Complaint updateComplaint(Long id, ComplaintRequest complaintRequest) {
        Complaint existingComplaint = complaintRepository.findById(id).orElse(null);
        if (existingComplaint != null) {
            existingComplaint.setDescription(complaintRequest.getDescription());
            existingComplaint.setStatus(complaintRequest.getStatus());
            if (complaintRequest.getCategory()!=null){
                assignCategoryToComplaint(complaintRequest.getCategory(), id);
            }
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
