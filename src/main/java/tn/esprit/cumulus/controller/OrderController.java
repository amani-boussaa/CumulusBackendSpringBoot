package tn.esprit.cumulus.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cumulus.entity.Order;
import tn.esprit.cumulus.entity.Wallet;
import tn.esprit.cumulus.service.OrderService;
import tn.esprit.cumulus.service.WalletService;

import java.util.ArrayList;
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
        return os.retrieveAllOrders();
    }

    @GetMapping("/getAllOrdersOfUser/{user_id}")
    public List<Order> getAllOrdersOfUser(@PathVariable("user_id") Long user_id) throws StripeException {
        Stripe.apiKey= stripeKey;
        return os.retrieveAllOrdersOfUser(user_id);
    }

    @PutMapping("/updateOrder/{order_id}")
    public Order updateRefund(@RequestBody Order order, @PathVariable("order_id") String order_id) {
        order.setOrder_id(order_id);
        return os.updateOrder(order);
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
    public Order addOrder(@RequestBody Order o,@RequestParam int CoinsBuy) throws StripeException {
        Stripe.apiKey= stripeKey;
        // charge creation
        Map<String, Object> params = new HashMap<>();
        // get price of the chosen course/subscription/voucher
        int amount = (int) (o.getAmount() * 100);
        params.put("amount",amount);
        params.put("currency", "usd");
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
        wallet.setCoins(wallet.getCoins()+CoinsBuy);
        os.ExchangeRateAfterCharge(o,wallet);
        ws.updateWallet(wallet);

        return os.addOrder(o);

        /////////////// add email : receipt_email is parametre in charge
    }

    @GetMapping("/ChooseCard")
    public ResponseEntity<Map<String, String>> getLastCard() throws StripeException {
        Stripe.apiKey= stripeKey;
        List<String> expandList = new ArrayList<>();
        expandList.add("sources");

        Map<String, Object> retrieveParams = new HashMap<>();
        retrieveParams.put("expand", expandList);

        Customer customer =Customer.retrieve("cus_NaAEGV2s1PY0fL", retrieveParams, null);

        Map<String, Object> params = new HashMap<>();
        params.put("object", "card");
        params.put("limit", 1);

        PaymentSourceCollection cards =customer.getSources().list(params);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(cards.toJson());
            String last4 = jsonNode.get("data").get(0).get("last4").asText();
            String Brand = jsonNode.get("data").get(0).get("brand").asText();
            String exp_month = jsonNode.get("data").get(0).get("exp_month").asText();
            String exp_year = jsonNode.get("data").get(0).get("exp_year").asText();

            // print the value of "last4"
            System.out.println(last4);
            System.out.println(Brand);
            System.out.println(exp_month);
            Map<String, String> cardData = new HashMap<>();
            cardData.put("last4", last4);
            cardData.put("brand", Brand);
            cardData.put("exp_month", exp_month);
            cardData.put("exp_year", exp_year);

            return ResponseEntity.ok(cardData);

        } catch (Exception e) {
            // handle the exception
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while processing the request: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }




    }
}
