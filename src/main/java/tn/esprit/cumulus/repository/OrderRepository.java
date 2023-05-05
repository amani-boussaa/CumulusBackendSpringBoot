package tn.esprit.cumulus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

//    List<Order> findByUser_User_id(Long userId);
    List<Order> findByUser(User user);


}
