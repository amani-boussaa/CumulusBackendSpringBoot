package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cumulus.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {

}
