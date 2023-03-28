package com.example.cumulusspringboot.services;


import com.example.cumulusspringboot.entities.PasswordResetToken;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.interfaces.IUserService;
import com.example.cumulusspringboot.repositories.PasswordResetTokenRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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

//    @Override
//    public void updateUserPassword(User user, String newPassword) {
//        user.setPassword(newPassword);
//        userRepository.save(user);
//    }

    @Override
    public ResponseEntity<User> updateUser(long id, User user) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setEmail(user.getEmail());
            _user.setUsername(user.getEmail());
            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
