package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Thread;

import java.util.List;

public interface IThreadService {

    Thread createThread(Thread thread);
    List<Thread> getAllThreads();
   Thread getAllComments(Long threadId);
}
