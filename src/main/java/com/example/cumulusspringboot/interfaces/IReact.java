package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Comment;
import com.example.cumulusspringboot.entities.React;

import java.util.List;

public interface IReact {

    React CreateReact (React R);

    List<React> ReadReact ();

    React UpdateReact(React R);

    void DeleteReact(Integer id);
}
