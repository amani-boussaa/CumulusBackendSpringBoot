package tn.esprit.cumulus.service;

import tn.esprit.cumulus.entity.Wallet;

import java.util.List;

public interface IWalletService {
    List<Wallet> retrieveAllWallets();
    Wallet addWallet(Wallet c);

    void deleteWallet(String id);

    Wallet updateWallet(Wallet c);

    Wallet retrieveWallet(String wallet_id);
    Wallet retrieveWalletFromUser();

    Wallet AddPaymentMethod(Wallet c);
}
