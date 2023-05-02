package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.config.TwilioConfig;
import com.example.cumulusspringboot.entities.CategoryComplaint;
import com.example.cumulusspringboot.entities.Complaint;
import com.example.cumulusspringboot.entities.StatusComplaint;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IComplaintService;
import com.example.cumulusspringboot.repositories.ComplaintRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.requests.ComplaintRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ComplaintServiceImpl implements IComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private UserRepository userRepository;
    //    @Autowired
//    private CategoryComplaintRepository categoryComplaintRepository;
    @Autowired
    private final TwilioConfig twilioConfig;
    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
//    public Complaint createComplaint(Long iduser, Long idcat, ComplaintDto complaintDto) {
    public Complaint createComplaint(ComplaintRequest complaintRequest) {
//        Complaint complaint = modelMapper.map(complaintRequest, Complaint.class);
//        Complaint savedComplaint = complaintRepository.save(complaint);
//        CategoryComplaint category = categoryComplaintRepository.findById(complaintRequest.getCategory())
//                .orElseThrow(() -> new NotFoundException("Category not found"));
        User user = userRepository.findById(complaintRequest.getUser())
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Create a new complaint object
        Complaint complaint = new Complaint();
        complaint.setDescription(complaintRequest.getDescription());
        complaint.setStatus(complaintRequest.getStatus());
        complaint.setCategorycomplaint(complaintRequest.getCategory());
        Complaint savedcomplaint = complaintRepository.save(complaint);
        assignUserToComplaint(complaintRequest.getUser(), complaint.getId());
//        assignCategoryToComplaint(complaintRequest.getCategory(), complaint.getId());

//        ////sms
        String phoneuser = savedcomplaint.getUser().getPhonenumber();


        CategoryComplaint cat = complaint.getCategorycomplaint();
        String msg = "";
        // Handle the complaint based on its category
        switch (cat) {
            case Technical:
                // Handle technical complaints
                msg = "We apologize for the technical issue. Please follow these instructions to resolve the issue: Please try clearing your browser cache or try using a different browser to see if that resolves the problem. If the issue persists, please reach out to our support team at cumulus454@gmail.com.\"";
                break;
            case Billing:
                // Handle billing complaints
                msg = "We apologize for the billing issue. Please contact our billing department at cumulus454@gmail.com to resolve the issue.";
                break;
            case Instructor:
                // Handle content-related complaints
                msg = "We're sorry to hear that you're having issues with the instructor. Our team takes all feedback seriously and will investigate the matter. We will also connect with the instructor to ensure that they are aware of your concerns and take steps to improve the situation.";
                break;
            case Accessibility:
                msg = "We take accessibility very seriously and apologize for any barriers you have encountered. Our team will work to resolve the issue as soon as possible and ensure that our website and courses are accessible to all students. If you have any additional feedback or suggestions for improvement, please let us know.";
                break;
            default:
                // Handle unknown complaints
                msg = "Thank you for your feedback. We will investigate the issue and contact you if necessary.";
                break;
        }
        Twilio.init(twilioConfig.getAccountsid(), twilioConfig.getAuthtoken());
        Message.creator(new PhoneNumber(phoneuser), new PhoneNumber(twilioConfig.getNumber()), msg).create();
//
//        //send mail
        String emailSubject = cat + " " + "Complaint";
        String emailBody = msg;
        emailService.sendEmail(complaint.getUser().getEmail(), emailSubject, emailBody);

        return complaint;
    }

    @Override
//    public Complaint updateComplaint(Long id, Complaint complaint) {
    public Complaint updateComplaint(Long id, ComplaintRequest complaintRequest) {
        Complaint existingComplaint = complaintRepository.findById(id).orElse(null);
        if (existingComplaint != null) {
//            existingComplaint.setDescription(complaintRequest.getDescription());
            existingComplaint.setStatus(complaintRequest.getStatus());
//            if (complaintRequest.getCategory()!=null){
//                assignCategoryToComplaint(complaintRequest.getCategory(), id);
//            }
            Complaint cp = complaintRepository.save(existingComplaint);
            //send mail
            StatusComplaint st = cp.getStatus();
            String msg = "Your complaint is : " + st+" ";
            System.out.println(st);
            switch (st) {
                case IN_PROGRESS:
                    // Handle technical complaints
                    msg += "Thank you for your patience while we work on resolving your complaint. We are currently investigating the issue and will keep you updated on our progress.";
                    break;
                case RESOLVED:

                    // Handle billing complaints
                    msg += "We are pleased to inform you that your complaint has been resolved. If you have any further concerns, please do not hesitate to contact us.";
                    break;
                default:
                    // Handle unknown complaints
                    msg += "Thank you for submitting your complaint. We have received it and will review it as soon as possible. We appreciate your patience.";
                    break;
            }
            String cat = String.valueOf(existingComplaint.getCategorycomplaint());
            String emailSubject = st+" "+cat + " " + "Complaint";
            msg+="\n\n Cumulus";
            String emailBody = msg;
            emailService.sendEmail(existingComplaint.getUser().getEmail(), emailSubject, emailBody);
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

//    @Override
//    public void assignCategoryToComplaint(Long idcat, Long idcomplaint) {
//        CategoryComplaint CategoryComplaint = categoryComplaintRepository.findById(idcat).orElse(null);
//        Complaint complaint = complaintRepository.findById(idcomplaint).orElse(null);
//        complaint.setCategorycomplaint(CategoryComplaint);
//        complaintRepository.save(complaint);
//    }

    @Override
    public ResponseEntity<HttpStatus> removeComplaint(Long id) {
        complaintRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
