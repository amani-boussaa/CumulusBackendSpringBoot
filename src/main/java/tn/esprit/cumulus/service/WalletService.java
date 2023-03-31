package tn.esprit.cumulus.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.cumulus.entity.Wallet;
import tn.esprit.cumulus.repository.WalletRepository;

import java.util.List;

@Service
public class WalletService implements IWalletService {

    @Autowired
    WalletRepository rep;

    EntityManager em;

    @Override
    public List<Wallet> retrieveAllWallets() {
         return rep.findAll();
    }

    @Override
    public Wallet addWallet(Wallet c) {
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
        if (w.getSubscription() == "Silver") {
            System.out.println("yes");
        }
        else if (w.getSubscription() == "Gold") {

        }
        else if (w.getSubscription() == "Platinum") {

        }
        else if (w.getSubscription() == "Yearly") {

        }
        else if (w.getSubscription() == "Student") {

        }

        return rep.save(w);
    }

    @Override
    public Wallet retrieveWallet(String wallet_id) {
        return rep.findById(wallet_id).orElse(null);
    }

    // get wallet details of a connected user
    @Override
    public Wallet retrieveWalletFromUser(String wallet_id) {
        return null;
    }

    @Override
    public Wallet AddPaymentMethod(Wallet c) {
        Wallet w = rep.findById(c.getWallet_id()).get();
        w.setCoins(c.getCoins());
        return rep.save(w);
    }
}
