package tn.esprit.cumulus.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.User;
import tn.esprit.cumulus.entity.Wallet;
import tn.esprit.cumulus.repository.OrderRepository;
import tn.esprit.cumulus.repository.UserRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class OrderService implements IOrderService{

    @Autowired
    OrderRepository rep;
    @Autowired
    UserRepository userRepository;

    EntityManager em;
    @Value("${stripe.apikey}")
    String stripeKey;

    @Override
    public List<Order> retrieveAllOrders() {
        return rep.findAll();
    }

    @Override
    public List<Order> retrieveAllOrdersOfUser(Long userId) {
        User user = new User();
        user.setUser_id(userId);
        return rep.findByUser(user);
    }

    @Override
    public Order addOrder(Order c) {
        User defaultUser = userRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        c.setUser(defaultUser);
        return rep.save(c);
    }

    @Override
    public void deleteOrder(String id) {
        rep.deleteById(id);
        System.out.println("Order Deleted");
    }

    @Override
    public Order updateOrder(Order c) {
        Order order = rep.findById(c.getOrder_id()).get();
        order.setAmount(c.getAmount());
        order.setStatus(c.getStatus());
        return rep.save(order);
    }

    @Override
    public Order retrieveOrder(String Order_id) {
        return rep.findById(Order_id).orElse(null);
    }

    public void ExchangeRateAfterCharge(Order order, Wallet wallet) throws StripeException {
        Stripe.apiKey= stripeKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        BalanceTransactionCollection balanceTransactions = BalanceTransaction.list(params);
        for (BalanceTransaction balanceTransaction : balanceTransactions.getData()) {
            BigDecimal exchangeRate = balanceTransaction.getExchangeRate();
            if (!wallet.getCurrency().equals(order.getCurrency()) && exchangeRate!=null)
            {
                float newBalance = wallet.getBalance()- order.getAmount() * exchangeRate.floatValue();
                wallet.setBalance(newBalance);
            }
            else if (exchangeRate==null){
                float newBalance = wallet.getBalance()- order.getAmount();
                wallet.setBalance(newBalance);
            }
        }
    }

    public void ExchangeRateAfterRefund(Order order, Wallet wallet) throws StripeException {
        Stripe.apiKey= stripeKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        BalanceTransactionCollection balanceTransactions = BalanceTransaction.list(params);
        for (BalanceTransaction balanceTransaction : balanceTransactions.getData()) {
            BigDecimal exchangeRate = balanceTransaction.getExchangeRate();
            if (!wallet.getCurrency().equals(order.getCurrency()) && exchangeRate!=null)
            {
                float newBalance = wallet.getBalance()+ order.getAmount() * exchangeRate.floatValue();
                wallet.setBalance(newBalance);
            }
            else {
                float newBalance = wallet.getBalance()+ order.getAmount();
                wallet.setBalance(newBalance);
            }
        }
    }
}
