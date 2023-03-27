package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.interfaces.IThreadService;
import com.example.cumulusspringboot.repositories.ThreadRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ThreadService implements IThreadService {

    ThreadRepo threadRepo;
    @Override
    public Thread createThread(Thread thread) {
        return threadRepo.save(thread);
    }

    @Override
    public List<Thread> getAllThreads() {
        return threadRepo.findAll();
    }
}
