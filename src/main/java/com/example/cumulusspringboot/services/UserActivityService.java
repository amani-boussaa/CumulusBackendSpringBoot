package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.*;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IUserActivityService;
import com.example.cumulusspringboot.repositories.ThreadRepo;
import com.example.cumulusspringboot.repositories.UserActivityRepo;
import com.example.cumulusspringboot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserActivityService implements IUserActivityService {

    @Autowired
    UserActivityRepo userActivityRepo;
    @Autowired
    ThreadRepo threadRepo;
    @Autowired
    UserRepository userRepo;

    public void updateViewCount(long userID, long threadid) {
        System.out.println("view count");
        Thread thread = threadRepo.findById(threadid).get();

        User user= userRepo.findById(userID).get();
        System.out.println(thread);
        System.out.println(user);

        List<ThreadTag> threadTags = thread.getThreadTags();

        for (ThreadTag threadTag : threadTags) {
            UserActivity userActivity = userActivityRepo.findByauserAndThreadTag(user, threadTag);
            if (userActivity != null) {
                userActivity.setViewCount(userActivity.getViewCount() + 1);
            } else {
                userActivity = new UserActivity();
                userActivity.setAuser(user);
                userActivity.setThreadTag(threadTag);
                userActivity.setViewCount(1);
            }
            userActivityRepo.save(userActivity);
        }
    }
}
