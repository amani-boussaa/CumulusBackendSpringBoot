package com.example.cumulusspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.cumulusspringboot.entities.Course;
import com.example.cumulusspringboot.entities.Order;
import com.example.cumulusspringboot.entities.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

//    List<Order> findByUser_User_id(Long userId);
    List<Order> findByUser(User user);
    Long countBy();
    @Query("SELECT SUM(o.amount) FROM Order o")
    Float getTotalAmount();
    @Query("SELECT AVG(o.amount) FROM Order o")
    Float getAverageOrderValue();

    List<Order> findByCourseIsNotNull();
    boolean existsByUserAndCourse(User user, Course course);

}
