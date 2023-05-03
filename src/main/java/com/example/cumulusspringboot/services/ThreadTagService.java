package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Thread;
import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.interfaces.IThreadTagService;
import com.example.cumulusspringboot.repositories.ThreadTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    @Override
    public List<Thread> getThreadByName(String name) {
        return threadTagRepo.findByName(name).getThreadT();
    }

    public List<Thread> getThreadsByTag(String tagName) {
        ThreadTag tag = threadTagRepo.findByName(tagName);
        if (tag == null) {
            throw new EntityNotFoundException("Tag not found.");
        }
        return tag.getThreadT();
    }
}
