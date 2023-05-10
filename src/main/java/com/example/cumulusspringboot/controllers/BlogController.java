package com.example.cumulusspringboot.controllers;

import com.example.cumulusspringboot.entities.Blog;
import com.example.cumulusspringboot.interfaces.IBlogService;
import com.example.cumulusspringboot.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
//exposer webservices

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/blog")
public class BlogController  {

    @Autowired
    private BlogService blogService;

    //************************************************************************************
    @PostMapping("/CreateBlog")
    public Blog CreateBlog(@RequestBody Blog B) {
        return blogService.CreateBlog(B);
    }
/*
    @PostMapping(value = "/CreateBlog", consumes = {"multipart/form-data"})
    public Blog createBlog(@ModelAttribute Blog blog,
                           @RequestParam("image") MultipartFile image) {
        String imageName = new Date().getTime() + '_' + image.getOriginalFilename();
        Path imagePath = Paths.get("./assets/images/" + imageName);
        try {
            Files.copy(image.getInputStream(), imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setImagePath(imageName);
        return blogService.CreateBlog(blog);
    }
*/

    @GetMapping("/ReadBlog")
    public List<Blog> ReadBlog() {


        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return blogService.ReadBlog();
    }

    @GetMapping("/RetrieveBlog/{id}")
    public Blog RetrieveBlog(@PathVariable Long id) {
        return blogService.RetrieveBlog(id);
    }

    @PutMapping("/UpdateBlog")
    public Blog UpdateBlog( @RequestBody Blog B) {
        return blogService.UpdateBlog(B);
    }

    @DeleteMapping("/DeleteBlog/{id}")
    public void DeleteBlog(@PathVariable("id") Long id) {
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
    @GetMapping("/userblog/{id}")
    public List<Blog> getBlogByIdUser(@PathVariable("id")Long iduser) {
        return blogService.getBlogByIdUser(iduser);
    }

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


//exposer les services give URL bel postman handel requetes