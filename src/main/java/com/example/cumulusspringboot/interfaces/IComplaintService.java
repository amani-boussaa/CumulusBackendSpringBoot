package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.payload.ComplaintDto;
import com.example.cumulusspringboot.requests.ComplaintRequest;

import java.util.List;

public interface IComplaintService {



//    Complaint createComplaint(Long iduser, Long idcat, ComplaintDto complaint);
    Complaint createComplaint(ComplaintRequest complaintRequest);

    Complaint updateComplaint(Long id, ComplaintRequest complaintRequest);

    Complaint getComplaintById(Long id);

    List<Complaint> getAllComplaints();

    List<Complaint> getComplaintsByUser(User user);
    void assignUserToComplaint (Long iduser, Long idcomplaint);
    void assignCategoryToComplaint (Long idcat, Long idcomplaint);


}
