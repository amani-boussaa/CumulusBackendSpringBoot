package com.example.cumulusspringboot.controllers;


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
import com.example.cumulusspringboot.entities.*;
import com.example.cumulusspringboot.repositories.CourseRepository;
import com.example.cumulusspringboot.repositories.UserRepository;
import com.example.cumulusspringboot.services.EmailSenderService;
import com.example.cumulusspringboot.services.OrderService;
import com.example.cumulusspringboot.services.WalletService;

import java.util.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    OrderService os;
    @Autowired
    WalletService ws;
    @Autowired
    private EmailSenderService senderService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Value("${stripe.apikey}")
    String stripeKey;

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() throws StripeException {
        Stripe.apiKey= stripeKey;
//        Customer customer = Customer.retrieve("cus_NaAEGV2s1PY0fL");
//        Map<String, Object> params = new HashMap<>();
//        params.put("limit", 1);
//        BalanceTransactionCollection balanceTransactions = BalanceTransaction.list(params);
//        for (BalanceTransaction balanceTransaction : balanceTransactions.getData()) {
//            System.out.println(balanceTransaction.toJson());
//        }
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
        float MoneySpent = o.getAmount();

        // Email Notification
//        senderService.sendSimpleEmail("anonym14637@gmail.com",
//                "Payment Received - Thank You!",
//                "Dear [Client’s Name],\n" +
//                        "\n" +
//                        "We would like to inform you that we have received your payment of " + MoneySpent + "$. Thank you for your prompt payment.\n" +
//                        "\n" +
//                        "We appreciate your business and look forward to providing you with the highest quality Services in the future.\n" +
//                        "\n" +
//                        "If you have any questions or concerns, please do not hesitate to contact us.\n" +
//                        "\n" +
//                        "Best regards, Cumulus");

        return os.addOrder(o);

    }
    // Order of a connected user
    @PostMapping("/addSubscriptionOrder")
    public Order addSubscriptionOrder(@RequestBody Order o,
                                      @RequestParam String subscription_type,
                                      @RequestParam float price) throws StripeException {
        Stripe.apiKey= stripeKey;
        // charge creation
        Map<String, Object> params = new HashMap<>();
        // get price of the chosen course/subscription/voucher
        o.setType("Subscription");
        int amount = (int) (price * 100);
        params.put("amount",amount);
        params.put("currency", "usd");
        params.put("customer", "cus_NaAEGV2s1PY0fL");
        params.put("description","Subscription"); // to change later (type + price or smth)
        Charge charge = Charge.create(params);
        System.out.println(charge);
        // get charge info and set them to the rest of the order parameters
        o.setOrder_id(charge.getId());
        o.setCurrency(charge.getCurrency());
        o.setStatus(charge.getStatus());
        o.setAmount(price);

        //change balance of user's wallet after charge
        Customer customer = Customer.retrieve("cus_NaAEGV2s1PY0fL");

        Wallet wallet = ws.retrieveWallet("cus_NaAEGV2s1PY0fL");
        os.ExchangeRateAfterCharge(o,wallet);
        ws.updateWallet(wallet);
        String username = wallet.getUser().getUsername();
        // Email Notification
        senderService.sendSimpleEmail("anonym14637@gmail.com",
                "Payment Received - Thank You!",
                "Dear "+ username +",\n" +
                        "\n" +
                        "We would like to inform you that we have received your payment of " + price + "$. Thank you for your prompt payment.\n" +
                        "\n" +
                        "We appreciate your business and look forward to providing you with the highest quality Services in the future.\n" +
                        "\n" +
                        "If you have any questions or concerns, please do not hesitate to contact us.\n" +
                        "\n" +
                        "Best regards, Cumulus");

        return os.addSubscriptionOrder(o,subscription_type);

    }

    @GetMapping("/getAllOrdersWithCourse")
    public List<Order> getOrdersWithCourse() {
        return os.getOrdersWithCourse();
    }

    // Endpoint to check if a user has an order for a specific course
    @GetMapping("/userHasPurchased")
    public boolean hasUserPurchasedCourse(@RequestParam Long userId, @RequestParam Long courseId) {
        User defaultUser = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("User with ID 1 not found"));
        return os.hasUserPurchasedCourse(defaultUser, course);
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

    @PutMapping("/RedeemGiftCard")
    public ResponseEntity<Object> redeemGiftCard(@RequestParam("code") String code) {
        try {
            GiftCard redeemedGiftCard = os.RedeemGiftCard(code);
            return ResponseEntity.ok(redeemedGiftCard);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
    @PutMapping("/RedeemVoucher")
    public ResponseEntity<Object> redeemVoucher(@RequestParam("code") String code) {
        try {
            Voucher redeemedVoucher = os.RedeemVoucher(code);
            return ResponseEntity.ok(redeemedVoucher);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PostMapping("/createVoucher")
    public ResponseEntity<String> createVoucher(@RequestParam("name") String name) throws StripeException{
        os.BuyExamVoucher(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    public Long getOrderCount() {
        return os.getOrderCount();
    }

    @GetMapping("/totalAmount")
    public Float getTotalAmount() {
        return os.getTotalAmount();
    }

    @GetMapping("/AverageOrderValue")
    public Float getAverageOrderValue() {
        return os.getAverageOrderValue();
    }
}
