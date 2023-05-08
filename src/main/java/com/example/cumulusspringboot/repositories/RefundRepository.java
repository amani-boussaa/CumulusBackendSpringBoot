package com.example.cumulusspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.cumulusspringboot.entities.Order;
import com.example.cumulusspringboot.entities.Refund;
import com.example.cumulusspringboot.entities.User;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, String>  {
    List<Refund> findByOrder_User(User user);

}
