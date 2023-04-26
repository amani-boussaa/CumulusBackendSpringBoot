package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.*;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IThreadService;
import com.example.cumulusspringboot.repositories.ThreadRepo;

import com.example.cumulusspringboot.repositories.UserActivityRepo;
import com.example.cumulusspringboot.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class ThreadService implements IThreadService {
    @Autowired
    ThreadRepo threadRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserActivityRepo userActivityRepo;



    public void incrementTagCount(String tag, List<TagCount> tagCounts) {
        for (TagCount tagCount : tagCounts) {
            if (tagCount.getTagName().equals(tag)) {
                tagCount.setCount(tagCount.getCount() + 1);
                return;
            }
        }

        tagCounts.add(new TagCount( 1,tag));
    }



    @Override
    public Thread createThread(Thread thread) {
        return threadRepo.save(thread);
    }

    @Override
    public List<Thread> getAllThreads() {
        return threadRepo.findAll();
    }
    @Override
    public void getAllThre(MultipartFile file) {
        try {
            System.out.println(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void viewThread(long threadId, long userId) {


        Thread thread = threadRepo.findById(threadId).orElse(null);
        if (thread != null) {
            List<ThreadTag> tags = thread.getThreadTags();
            User user = null;

                user = userRepo.findById(userId).orElse(null);

            if (user != null) {
                UserActivity userActivity = userActivityRepo.findByAuserId(userId);
                List<TagCount> tagCounts = userActivity.getTagCounts();
                if (userActivity == null) {
                    userActivity = new UserActivity();
                    userActivity.setAuser(user);
                    for (ThreadTag tag : tags) {
                        tagCounts.add(new TagCount(1,tag.getName()));
                    }
                    userActivity.setTagCounts(tagCounts);
                }

                for (ThreadTag tag : tags) {
                   incrementTagCount(tag.getName(),tagCounts);
                }
                userActivityRepo.save(userActivity);
                System.out.println("VIEW THREAD SAVED");
            } else {
                System.out.println("VIEW THREAD ELSE (USER NOT NULL )");


               /* UserActivity userActivity = new UserActivity();
                userActivity.setSessionId(sessionId);
                userActivity.setActivityType("view_thread");
                userActivity.setActivityData(String.valueOf(threadId));
                userActivity.setTagCounts(new HashMap<>());
                Map<String, Integer> tagCounts = userActivity.getTagCounts();
                for (String tag : tags) {
                    tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                }
                userActivityRepository.save(userActivity);*/
            }
        }



    }

    @Override
    public Thread getAllComments(Long threadId) {
//List<Comment> cs = null;
//        for (Comment c :threadRepo.findById(threadId).get().getComments() ) {
//            System.out.println("aaaaaaaaaaaaa"+c);
//cs.add(c);
//
//        }
            return threadRepo.findById(threadId).get() ;

    }

    @Override
    public Thread addCommentToThread(long threadId,Comment comment) {
        Thread thread =threadRepo.findById(threadId).get();

        thread.addComment(comment);




        return threadRepo.save(thread) ;
    }


}
