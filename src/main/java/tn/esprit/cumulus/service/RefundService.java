package tn.esprit.cumulus.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.Refund;
import tn.esprit.cumulus.entity.Wallet;
import tn.esprit.cumulus.repository.OrderRepository;
import tn.esprit.cumulus.repository.RefundRepository;
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

    EntityManager em;

    @Override
    public List<Refund> retrieveAllRefunds() {
        return rep.findAll();
    }

    @Override
    public List<Refund> retrieveAllRefundsOfUser() {
        return rep.findAll();
    }

    @Override
    public Refund addRefund(Refund refund) {
        return rep.save(refund);
    }

    @Override
    public void deleteRefund(String id) {
        rep.deleteById(id);
    }

    @Override
    public Refund updateRefund(Refund refund) {
        Refund ref = rep.findById(refund.getRefund_id()).get();
        ref.setStatus(refund.getStatus());
        if (ref.getStatus().equals("successful")) {
            Order order = ref.getOrder();
            order.setStatus("Refunded");
            order_repo.save(order);
//            User user = order.getUser();
//            Wallet wallet = user.getWallet();
 //           wallet.updateBalance(order.getAmount() + wallet.getBalance();
//            wallet_repo.save(wallet);
        }
        return rep.save(ref);
    }

    @Override
    public Refund retrieveRefund(String Refund_id) {
        return rep.findById(Refund_id).orElse(null);
    }
}
