package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.interfaces.IUserActivityService;
import com.example.cumulusspringboot.repositories.UserActivityRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserActivityService implements IUserActivityService {

    @Autowired
    UserActivityRepo userActivityRepo;


}
