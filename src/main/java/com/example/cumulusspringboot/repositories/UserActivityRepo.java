package com.example.cumulusspringboot.repositories;


import com.example.cumulusspringboot.entities.ThreadTag;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserActivityRepo  extends JpaRepository<UserActivity,Long> {

    List<UserActivity> findByauserId(@Param("userId") long id);

    UserActivity findByauserAndThreadTag(User user, ThreadTag threadTag);
}
