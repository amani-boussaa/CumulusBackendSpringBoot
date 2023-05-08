package com.example.cumulusspringboot.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.example.cumulusspringboot.entities.Wallet;
import com.example.cumulusspringboot.services.WalletService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
@CrossOrigin(origins = "http://localhost:4200")
public class WalletController {
    @Autowired
    WalletService ws;
    @Value("${stripe.apikey}")
    String stripeKey;


    @GetMapping("/getAllWallets")
    public List<Wallet> getAllWallets(){
        return ws.retrieveAllWallets();
    }

    @GetMapping("/getWalletOfUser/{id}")
    public Wallet getWalletOfUser(@PathVariable Long id) {
        return ws.retrieveWalletFromUser(id);
    }

    @DeleteMapping("/deleteWallet/{wallet_id}")
    public void deleteWallet(@PathVariable("wallet_id") String wallet_id) throws StripeException {
        Stripe.apiKey= stripeKey;
        Customer customer = Customer.retrieve(wallet_id);
        Customer deletedCustomer = customer.delete();
        ws.deleteWallet(wallet_id);
    }

    @PostMapping("/addWallet")
    public Wallet addWallet(@RequestBody Wallet w) throws StripeException {
        Stripe.apiKey= stripeKey;
        Map<String, Object> params = new HashMap<>();
        params.put("name","aziz");

        int balance = (int) (w.getBalance() * 100);
        params.put("balance",balance);
        //params.put("currency",w.getCurrency());

        Customer customer = Customer.create(params);
        w.setWallet_id(customer.getId());
       // w.setPayment_method(customer.getDefaultSource());

        return ws.addWallet(w);
    }

    @PutMapping("/updateWallet")
    public Wallet updateWallet(@RequestBody Wallet w) throws StripeException {
        Stripe.apiKey= stripeKey;
        Customer customer = Customer.retrieve(w.getWallet_id());
        Map<String, Object> params = new HashMap<>();
        int balance = (int) (w.getBalance() * 100);
        params.put("balance",balance);
        Customer updatedCustomer = customer.update(params);
        return ws.updateWallet(w);
    }

    @GetMapping("/getWallet/{wallet_id}")
    public Wallet getWallet(@PathVariable("wallet_id") String wallet_id) throws StripeException {
        Stripe.apiKey= stripeKey;
        //Customer customer = Customer.retrieve("cus_Na8PVdN8ZvYedl");
      return ws.retrieveWallet(wallet_id);
    }

    @PutMapping("/AddPaymentMethod")
    public Wallet AddPaymentMethod(@RequestBody Wallet w,
                                   @RequestParam String card_number,
                                   @RequestParam String exp_month,
                                   @RequestParam String exp_year,
                                   @RequestParam String cvc) throws StripeException {
        Stripe.apiKey= stripeKey;
        Map<String, Object> retrieveParams = new HashMap<String, Object>();
        List<String> expandList = new ArrayList<>();
        expandList.add("sources");
        retrieveParams.put("expand", expandList);
        Customer customer = Customer.retrieve("cus_NaAEGV2s1PY0fL", retrieveParams, null);
        Map<String, Object> cardParam = new HashMap<String, Object>(); //add card details
        cardParam.put("number", card_number);
        cardParam.put("exp_month", exp_month);
        cardParam.put("exp_year", exp_year);
        cardParam.put("cvc", cvc);
        //cardParam.put("funding", "debit");

        Map<String, Object> tokenParam = new HashMap<String, Object>();
        tokenParam.put("card", cardParam);

        Token token = Token.create(tokenParam); // create a token

        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId()); //add token as source

        Card card = (Card)customer.getSources().create(source); // add the customer details to which card is need to link
        card.setFunding("debit");
        w.setPayment_method(card.getBrand());
        w.setCurrency(customer.getCurrency());
        if (w.getBalance()==0)
        {
            w.setBalance(200.00F);
            Map<String, Object> params = new HashMap<>();
            int balance = (int) (w.getBalance() * 100);
            params.put("balance",balance);
            Customer updatedCustomer = customer.update(params);
        }
        String cardDetails = card.toJson();
        System.out.println("Card Details : " + cardDetails);
        customer = Customer.retrieve("cus_NaAEGV2s1PY0fL");//change the customer id or use to get customer by id.
        System.out.println("After adding card, customer details : " + customer);
        return ws.AddPaymentMethod(w);
    }


    @GetMapping("/subscription/statistics")
    public Map<String, Long> getSubscriptionStatistics() {
        return ws.getSubscriptionStatistics();
    }


}
