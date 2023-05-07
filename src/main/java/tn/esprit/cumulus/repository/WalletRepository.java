package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.cumulus.entity.User;
import tn.esprit.cumulus.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Wallet findByUser(User user);

    @Query("SELECT COUNT(w) FROM Wallet w WHERE w.subscription = :subscription")
    Long countBySubscription(@Param("subscription") String subscription);
}
