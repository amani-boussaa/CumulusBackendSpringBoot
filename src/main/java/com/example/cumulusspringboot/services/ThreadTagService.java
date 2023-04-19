package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.interfaces.IThreadTagService;
import com.example.cumulusspringboot.repositories.ThreadTagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadTagService  implements IThreadTagService {
    @Autowired
    ThreadTagRepo threadTagRepo;


}
