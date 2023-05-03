package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Blog;
import com.example.cumulusspringboot.interfaces.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
//exposer webservices

@RestController
@RequestMapping("/blog")
public class BlogController  {

    @Autowired
    private IBlogService blogService;

    //************************************************************************************
    @PostMapping("/CreateBlog")
    public Blog CreateBlog(@RequestBody Blog B) {
        return blogService.CreateBlog(B);
    }

    @GetMapping("/ReadBlog")
    public List<Blog> ReadBlog() {
        return blogService.ReadBlog();
    }

    @PutMapping("/UpdateBlog")
    public Blog UpdateBlog(@RequestBody Blog B) {
        return blogService.UpdateBlog(B);
    }

    @DeleteMapping("/DeleteBlog/{id}")
    public void DeleteBlog(@PathVariable Long id) {
        blogService.DeleteBlog(id);
    }
    //************************************************************************************


    /*      this code is used to upload a file associated with a specific id using a HTTP POST request.
    @PostMapping("/{id}/file")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        return certifService.uploadFile(id,file);
    }

     */

/*
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

 */
}

    // Implement other endpoints for updating, deleting, liking, unliking, and commenting on Blog posts


//exposer les services give URL bel postman