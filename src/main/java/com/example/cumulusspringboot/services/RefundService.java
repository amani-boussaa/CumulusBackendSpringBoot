package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.interfaces.IRefundService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.Order;
import com.example.cumulusspringboot.entities.Refund;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.entities.Wallet;
import com.example.cumulusspringboot.repositories.OrderRepository;
import com.example.cumulusspringboot.repositories.RefundRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.repositories.WalletRepository;

import java.util.List;

@Service
public class RefundService implements IRefundService {

    @Autowired
    RefundRepository rep;
    @Autowired
    WalletRepository wallet_repo;
    @Autowired
    OrderRepository order_repo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService os;

    EntityManager em;

    @Override
    public List<Refund> retrieveAllRefunds() {
        return rep.findAll();
    }

    @Override
    public List<Refund> retrieveAllRefundsOfUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        return rep.findByOrder_User(user);
    }

    @Override
    public Refund addRefund(Refund refund, String order_id) {
        // Fetch the Order entity by ID
        Order order = order_repo.findById(order_id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + order_id));
        refund.setRefund_id(order.getOrder_id().replaceFirst("^ch", "ref"));
        // Set the Order reference on the Refund entity
        refund.setOrder(order);

        // Save the Refund entity using the repository
        return rep.save(refund);
    }

    @Override
    public void deleteRefund(String id) {
        rep.deleteById(id);
    }

    @Override
    public Refund updateRefund(Refund refund) throws StripeException {
        Stripe.apiKey= os.stripeKey;
        Refund ref = rep.findById(refund.getRefund_id()).get();
        ref.setStatus(refund.getStatus());
        if (ref.getStatus().equals("accepted")) {
            Order order = ref.getOrder();
            order.setStatus("refunded");
            order_repo.save(order);

            User user = order.getUser();
            Wallet wallet = user.getWallet();
            os.ExchangeRateAfterRefund(order, wallet);
        }
        return rep.save(ref);
    }

    @Override
    public Refund retrieveRefund(String Refund_id) {
        return rep.findById(Refund_id).orElse(null);
    }
}
