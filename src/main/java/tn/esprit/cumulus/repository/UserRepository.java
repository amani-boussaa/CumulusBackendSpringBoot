package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cumulus.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
