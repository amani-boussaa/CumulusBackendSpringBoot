package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.entities.Blog;
import com.example.cumulusspringboot.interfaces.IBlogService;
import com.example.cumulusspringboot.repositories.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class BlogService implements IBlogService {
    @Autowired
    private BlogRepository blogRepository;



    @Override
    public Blog CreateBlog(Blog B) {

        return blogRepository.save(B);
    }

    @Override
    public List<Blog> ReadBlog() {
        List<Blog> blogList = new ArrayList<>();
        blogRepository.findAll().forEach(blogList::add);
        return blogList;
    }

    @Override
    public Blog UpdateBlog(Blog B) {
        return blogRepository.save(B);
    }

    @Override
    public void DeleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    //*****************************************

/*
    @Override
    public void PersonalizedFeed() {
        //getreactbyauthorbtkeywords
        //readreactby_that_author
        //

}
*/

}
