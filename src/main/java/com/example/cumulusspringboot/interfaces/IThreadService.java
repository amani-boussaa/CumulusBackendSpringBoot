package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IThreadService {

    Thread createThread(Thread thread);
    List<Thread> getAllThreads();
    List<Thread>  getThreadByUser(Long userID);
   Thread getAllComments(Long threadId);
   Thread addCommentToThread(long threadId,Comment comment);
    void getAllThre(MultipartFile file);

    void viewThread(long     threadId,long userId);

    Thread addTagToThread(long threadId, List<ThreadTag> threadTag);
  Thread  createThreadWithTags(Thread thread,  List<String> tagNames) ;


  void deleteThread(long id);
}
