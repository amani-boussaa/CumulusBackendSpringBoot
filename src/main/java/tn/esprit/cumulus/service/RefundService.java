package tn.esprit.cumulus.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.Refund;
import tn.esprit.cumulus.entity.User;
import tn.esprit.cumulus.entity.Wallet;
import tn.esprit.cumulus.repository.OrderRepository;
import tn.esprit.cumulus.repository.RefundRepository;
import tn.esprit.cumulus.repository.UserRepository;
import tn.esprit.cumulus.repository.WalletRepository;

import java.util.List;

@Service
public class RefundService implements IRefundService{

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
