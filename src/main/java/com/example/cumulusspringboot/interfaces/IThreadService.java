package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IThreadService {

    Thread createThread(Thread thread);
    List<Thread> getAllThreads();
   Thread getAllComments(Long threadId);
   Thread addCommentToThread(long threadId,Comment comment);
    void getAllThre(MultipartFile file);

    void viewThread(long     threadId,long userId);
}
