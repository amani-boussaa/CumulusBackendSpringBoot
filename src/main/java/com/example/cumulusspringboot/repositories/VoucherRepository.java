package com.example.cumulusspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.cumulusspringboot.entities.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
}
