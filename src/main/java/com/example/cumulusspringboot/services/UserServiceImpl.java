package com.example.cumulusspringboot.services;


import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.exception.ResourceNotFoundException;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.repositories.PasswordResetTokenRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    UserRepository userRepository;
    PasswordResetTokenRepository passwordTokenRepository;

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<HttpStatus> removeUser(Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public User retrieveUser(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        System.out.println(userRepository.findByEmail("amani.boussaa16@gmail.com"));
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public ResponseEntity<User> updateUser(long id, User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setEmail(user.getEmail());
            _user.setUsername(user.getEmail());
            _user.setAddress(user.getAddress());
            _user.setDescription(user.getDescription());
            _user.setInstitution(user.getInstitution());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String uploadFile (MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        // Save the file
        byte[] bytes = file.getBytes();
        Path path = Paths.get("uploadsamani/" + fileName);
        Files.write(path, bytes);

        return "File uploaded successfully!";
    }

    @Override
    public ResponseEntity<?> uploadImage(Long id, MultipartFile file) throws IOException {
        // Get the user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        byte[] bytes = file.getBytes();

        user.setImagePath(bytes);
        // Save the user
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

    }
}
