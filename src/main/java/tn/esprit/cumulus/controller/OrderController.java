package tn.esprit.cumulus.controller;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.Wallet;
import tn.esprit.cumulus.service.OrderService;
import tn.esprit.cumulus.service.WalletService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    OrderService os;
    @Autowired
    WalletService ws;
    @Value("${stripe.apikey}")
    String stripeKey;

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() throws StripeException {
        Stripe.apiKey= stripeKey;
        Customer customer = Customer.retrieve("cus_NaAEGV2s1PY0fL");
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        BalanceTransactionCollection balanceTransactions = BalanceTransaction.list(params);
        for (BalanceTransaction balanceTransaction : balanceTransactions.getData()) {
            System.out.println(balanceTransaction.toJson());
        }
//        BalanceTransaction balanceTransaction = BalanceTransaction.retrieve("");
//        BigDecimal exchangeRate = balanceTransaction.getExchangeRate();
        return os.retrieveAllOrders();
    }

    @DeleteMapping("/deleteOrder/{order_id}")
    public void deleteOrder(@PathVariable("order_id") String order_id) {
//        Stripe.apiKey= stripeKey;
//        Customer customer = Customer.retrieve(order_id);
//        Customer deletedCustomer = customer.delete();
        os.deleteOrder(order_id);
    }

    // Order of a connected user
    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order o) throws StripeException {
        Stripe.apiKey= stripeKey;
        // charge creation
        Map<String, Object> params = new HashMap<>();
        // get price of the chosen course/subscription/voucher
        int amount = (int) (o.getAmount() * 100);
        params.put("amount",amount);
        params.put("currency", "gbp");
        params.put("customer", "cus_NaAEGV2s1PY0fL");
        params.put("description",o.getType()); // to change later (type + price or smth)
        Charge charge = Charge.create(params);
        System.out.println(charge);
        // get charge info and set them to the rest of the order parameters
        o.setOrder_id(charge.getId());
        o.setCurrency(charge.getCurrency());
        o.setStatus(charge.getStatus());

        //change balance of user's wallet after charge
        Customer customer = Customer.retrieve("cus_NaAEGV2s1PY0fL");

        Wallet wallet = ws.retrieveWallet("cus_NaAEGV2s1PY0fL");
        os.ExchangeRateAfterCharge(o,wallet);
        ws.updateWallet(wallet);

        return os.addOrder(o);

        /////////////// add email : receipt_email is parametre in charge
    }
}
