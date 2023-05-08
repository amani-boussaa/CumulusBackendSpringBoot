package com.example.cumulusspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cumulusspringboot.entities.GiftCard;

public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {
    GiftCard findByCode(String code);
}
