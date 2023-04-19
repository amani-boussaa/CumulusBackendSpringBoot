package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Thread;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IThreadService {

    Thread createThread(Thread thread);
    List<Thread> getAllThreads();
   Thread getAllComments(Long threadId);
    void getAllThre(MultipartFile file);}
