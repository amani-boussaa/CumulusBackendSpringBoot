package com.example.cumulusspringboot.repositories;


import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserActivityRepo  extends JpaRepository<UserActivity,Long> {
    @Query("SELECT ua FROM UserActivity ua WHERE ua.Auser.id = :userId")
    UserActivity findByAuserId(@Param("userId") long id);
}
