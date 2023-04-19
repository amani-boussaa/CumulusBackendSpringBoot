package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IThreadService;
import com.example.cumulusspringboot.repositories.ThreadRepo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@AllArgsConstructor
public class ThreadService implements IThreadService {
    @Autowired
    ThreadRepo threadRepo;
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
    public Thread getAllComments(Long threadId) {
//List<Comment> cs = null;
//        for (Comment c :threadRepo.findById(threadId).get().getComments() ) {
//            System.out.println("aaaaaaaaaaaaa"+c);
//cs.add(c);
//
//        }
            return threadRepo.findById(threadId).get() ;

    }

}
