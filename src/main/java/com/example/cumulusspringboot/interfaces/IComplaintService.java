package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.User;

import java.util.List;

public interface IComplaintService {



    Complaint createComplaint(Long iduser, Long idcat, Complaint complaint);

    Complaint updateComplaint(Long id, Complaint complaint);

    Complaint getComplaintById(Long id);

    List<Complaint> getAllComplaints();

    List<Complaint> getComplaintsByUser(User user);
    void assignUserToComplaint (Long iduser, Long idcomplaint);
    void assignCategoryToComplaint (Long idcat, Long idcomplaint);


}
