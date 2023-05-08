package com.example.cumulusspringboot.services;

import com.example.cumulusspringboot.interfaces.IOrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.model.Charge;
import javax.persistence.EntityManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.*;
import com.example.cumulusspringboot.repositories.GiftCardRepository;
import com.example.cumulusspringboot.repositories.OrderRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.repositories.VoucherRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository rep;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GiftCardRepository gc_repo;
    @Autowired
    VoucherRepository voucher_repo;
    @Autowired
    WalletService walletService;

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
        user.setId(userId);
        return rep.findByUser(user);
    }

    @Override
    public Order addOrder(Order c) {
        User defaultUser = userRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        c.setUser(defaultUser);
        return rep.save(c);
    }

    public Order addSubscriptionOrder(Order c,String subscription_type) {
        User defaultUser = userRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        c.setUser(defaultUser);
        Wallet wallet = defaultUser.getWallet();
        wallet.setSubscription(subscription_type);
        walletService.addCoinsToWalletFirstime();
        return rep.save(c);
    }

    @Scheduled(cron = "0 0 0 1 * *") // Run at midnight on the 1st day of every month (fixedRate = 30000) for 30 seconds
    public void addCoinsToWallets() {
        walletService.addCoinsToWallets();
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

    public GiftCard RedeemGiftCard(String code) {
        GiftCard giftCard = gc_repo.findByCode(code);
        if (giftCard == null) {
            throw new NoSuchElementException("Gift card not found with code " + code);
        }
        if (giftCard.getStatus().equals("Used")) {
            throw new IllegalStateException("Gift card with code " + code + " has already been used.");
        }
        else {
        giftCard.setStatus("Used");
        User defaultUser = userRepository.findById(1L)
                .orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        giftCard.setUser(defaultUser);
        Wallet wallet = defaultUser.getWallet();
        wallet.setCoins(wallet.getCoins() + giftCard.getAmount());
        return gc_repo.save(giftCard);
        }
    }

    public Voucher RedeemVoucher(String code) {
        Voucher voucher = voucher_repo.findByCode(code);
        if (voucher == null) {
            throw new NoSuchElementException("Gift card not found with code " + code);
        }
        if (voucher.getStatus().equals("Used")) {
            throw new IllegalStateException("Gift card with code " + code + " has already been used.");
        } else {
            voucher.setStatus("Used");
            User defaultUser = userRepository.findById(1L)
                    .orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
            voucher.setUser(defaultUser);
            Wallet wallet = defaultUser.getWallet();
            wallet.setBalance(wallet.getBalance() - voucher.getPrice());
            return voucher_repo.save(voucher);
        }
    }
    public Voucher BuyExamVoucher(String name) throws StripeException {
        User defaultUser = userRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        Voucher voucher = new Voucher();
        voucher.setName(name);
        voucher.setPrice(220);
        voucher.setStatus("Used");

        LocalDate now = LocalDate.now();
        LocalDate expiration = now.plusYears(1);
        voucher.setExpirationDate(expiration);
        LocalDateTime creationdate = LocalDateTime.now();
        voucher.setCreationDate(creationdate);

        String code = "voucher2023_" + RandomStringUtils.randomAlphanumeric(4).toUpperCase();
        voucher.setCode(code);
        voucher.setUser(defaultUser);

        Wallet wallet = defaultUser.getWallet();
        wallet.setBalance(wallet.getBalance() - 220);

        // Create an instance of the Order
        Order order = new Order();
        Stripe.apiKey= stripeKey;
        // charge creation
        Map<String, Object> params = new HashMap<>();
        // get price of the chosen course/subscription/voucher
        order.setAmount(220);
        int amount = (int) (220 * 100);
        params.put("amount",amount);
        params.put("currency", "usd");
        params.put("customer", "cus_NaAEGV2s1PY0fL");
        params.put("description",order.getType()); // to change later (type + price or smth)
        Charge charge = Charge.create(params);
        System.out.println(charge);
        // get charge info and set them to the rest of the order parameters
        order.setOrder_id(charge.getId());
        order.setCurrency(charge.getCurrency());
        order.setStatus(charge.getStatus());
        order.setType("Voucher");
        order.setUser(defaultUser);
        order.setCourse(null);  // Set the course if applicable
        rep.save(order);

        return voucher_repo.save(voucher);
    }

    // Method to list orders that don't have course_id as null
    public List<Order> getOrdersWithCourse() {
        return rep.findByCourseIsNotNull();
    }

    // Method to check if a user has an order for a specific course
    public boolean hasUserPurchasedCourse(User user, Course course) {
        return rep.existsByUserAndCourse(user, course);
    }

    //stats
    public Long getOrderCount() {
        return rep.countBy();
    }

    public Float getTotalAmount() {
        return rep.getTotalAmount();
    }
    public Float getAverageOrderValue() {
        return rep.getAverageOrderValue();
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
