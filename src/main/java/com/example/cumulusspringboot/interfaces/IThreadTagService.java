package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.ThreadTag;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IThreadTagService {
    ThreadTag createThreadTag(ThreadTag threadTag);
    List<ThreadTag> getAllThreadTags();
}
