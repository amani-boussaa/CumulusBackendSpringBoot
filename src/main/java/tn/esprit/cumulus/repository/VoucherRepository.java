package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.cumulus.entity.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
}
