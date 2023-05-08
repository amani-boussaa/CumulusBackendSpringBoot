package com.example.cumulusspringboot.services;

import javax.persistence.EntityManager;

import com.example.cumulusspringboot.interfaces.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cumulusspringboot.entities.User;
import com.example.cumulusspringboot.entities.Wallet;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.repositories.WalletRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class WalletService implements IWalletService {

    @Autowired
    WalletRepository rep;
    @Autowired
    private UserRepository userRepository;

    EntityManager em;

    @Override
    public List<Wallet> retrieveAllWallets() {
         return rep.findAll();
    }

    @Override
    public Wallet addWallet(Wallet c) {
        User defaultUser = userRepository.findById(2L).orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        c.setUser(defaultUser);
        return rep.save(c);
    }

    @Override
    public void deleteWallet(String id) {
        rep.deleteById(id);
        System.out.println("Wallet Deleted");
    }

    @Override
    public Wallet updateWallet(Wallet c) {
        Wallet w = rep.findById(c.getWallet_id()).get();
        w.setCoins(c.getCoins());
        w.setSubscription(c.getSubscription());
        if (w.getSubscription() == "silver") {
            System.out.println("yes");
        }
        return rep.save(w);
    }

    public void addCoinsToWallets() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Wallet wallet = user.getWallet();
            String subscriptionType = wallet.getSubscription();
            int coinsToAdd = getCoinsToAdd(subscriptionType);
            wallet.setCoins(wallet.getCoins() + coinsToAdd);
            rep.save(wallet);
        }
    }
    public void addCoinsToWalletFirstime() {
        User defaultUser = userRepository.findById(2L).orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        Wallet wallet = defaultUser.getWallet();
            String subscriptionType = wallet.getSubscription();
            int coinsToAdd = getCoinsToAdd(subscriptionType);
            wallet.setCoins(wallet.getCoins() + coinsToAdd);
            rep.save(wallet);
    }
    private int getCoinsToAdd(String subscriptionType) {
        switch (subscriptionType) {
            case "silver":
                return 200;
            case "gold":
                return 500;
            case "platinum":
                return 1000;
            default:
                return 0;
        }
    }

    @Override
    public Wallet retrieveWallet(String wallet_id) {
        return rep.findById(wallet_id).orElse(null);
    }

    // get wallet details of a connected user
    @Override
    public Wallet retrieveWalletFromUser(Long id) {
        User user =userRepository.findById(id).orElse(null);
        return rep.findByUser(user);
    }

    @Override
    public Wallet AddPaymentMethod(Wallet c) {
        Wallet w = rep.findById(c.getWallet_id()).get();
//        w.setCoins(c.getCoins());
        return rep.save(w);
    }

    public Map<String, Long> getSubscriptionStatistics() {
        Map<String, Long> statistics = new HashMap<>();
        statistics.put("silver", rep.countBySubscription("silver"));
        statistics.put("gold", rep.countBySubscription("gold"));
        statistics.put("platinum", rep.countBySubscription("platinum"));
        statistics.put("yearly", rep.countBySubscription("yearly"));
        return statistics;
    }
}
