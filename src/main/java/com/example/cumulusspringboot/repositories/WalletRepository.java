package com.example.cumulusspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Wallet findByUser(User user);

    @Query("SELECT COUNT(w) FROM Wallet w WHERE w.subscription = :subscription")
    Long countBySubscription(@Param("subscription") String subscription);
}
