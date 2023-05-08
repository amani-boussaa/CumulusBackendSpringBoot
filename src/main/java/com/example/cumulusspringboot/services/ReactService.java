package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.React;
import com.example.cumulusspringboot.interfaces.IReact;
import com.example.cumulusspringboot.repositories.CommentRepository;
import com.example.cumulusspringboot.repositories.ReactRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReactService implements IReact {

    @Autowired
    private ReactRepository reactRepository;



    @Override
    public React CreateReact(React R) {
        return reactRepository.save(R);
    }

    @Override
    public List<React> ReadReact() {
        List<React> reactList = new ArrayList<>();
        reactRepository.findAll().forEach(reactList::add);
        return reactList;    }

    @Override
    public React UpdateReact(React R) {
        return reactRepository.save(R);
    }

    @Override
    public void DeleteReact(Integer id) {
        reactRepository.deleteById(id);
    }
}
