package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.Refund;
import tn.esprit.cumulus.entity.User;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, String>  {
    List<Refund> findByOrder_User(User user);

}
