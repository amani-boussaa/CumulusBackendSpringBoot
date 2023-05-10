package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.entities.User;

public interface IUserActivityService {

    void updateViewCount(long userid, long threadid);

}
