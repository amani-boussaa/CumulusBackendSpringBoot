package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.interfaces.IThreadTagService;
import com.example.cumulusspringboot.repositories.ThreadTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadTagService  implements IThreadTagService {
    @Autowired
    ThreadTagRepo threadTagRepo;
    @Override
    public ThreadTag createThreadTag(ThreadTag threadTag) {
        return threadTagRepo.save(threadTag);
    }

    @Override
    public List<ThreadTag> getAllThreadTags() {
        return threadTagRepo.findAll();
    }

}
