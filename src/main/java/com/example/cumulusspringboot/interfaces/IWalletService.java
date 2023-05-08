package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Wallet;

import java.util.List;

public interface IWalletService {
    List<Wallet> retrieveAllWallets();
    Wallet addWallet(Wallet c);

    void deleteWallet(String id);

    Wallet updateWallet(Wallet c);

    Wallet retrieveWallet(String wallet_id);
    Wallet retrieveWalletFromUser(Long id);

    Wallet AddPaymentMethod(Wallet c);
}
