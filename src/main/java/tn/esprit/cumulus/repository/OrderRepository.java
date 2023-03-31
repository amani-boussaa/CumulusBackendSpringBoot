package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cumulus.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {



}
