package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.cumulus.entity.GiftCard;

public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {
    GiftCard findByCode(String code);
}
