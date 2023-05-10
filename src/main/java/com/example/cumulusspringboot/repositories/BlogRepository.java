package com.example.cumulusspringboot.repositories;

import com.example.cumulusspringboot.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE b.user.id = :iduser")
    List<Blog> getBlogByUserId(@Param("iduser") Long iduser);
}