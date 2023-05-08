package com.example.cumulusspringboot.controllers;


import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.example.cumulusspringboot.entities.Order;
import com.example.cumulusspringboot.entities.Refund;
import com.example.cumulusspringboot.services.OrderService;
import com.example.cumulusspringboot.services.RefundService;
import com.example.cumulusspringboot.services.WalletService;

import java.util.List;

@RestController
@RequestMapping("/refund")
@CrossOrigin(origins = "http://localhost:4200")
public class RefundController {

    @Autowired
    RefundService rs;
    @Autowired
    WalletService ws;
    @Autowired
    OrderService os;
    @Value("${stripe.apikey}")
    String stripeKey;


    @GetMapping("/getRefunds")
    public List<Refund> getRefunds() {
        return rs.retrieveAllRefunds();
    }

    @GetMapping("/getRefundsOfUser/{user_id}")
    public List<Refund> getRefundsOfUser(@PathVariable("user_id") Long user_id) {
        return rs.retrieveAllRefundsOfUser(user_id);
    }

    @GetMapping("/retrieveRefund/{refund_id}")
    public Refund retrieveRefund(@PathVariable("refund_id") String refund_id) {
        return rs.retrieveRefund(refund_id);
    }

    @DeleteMapping("/deleteRefund/{refund_id}")
    public void deleteRefund(@PathVariable("refund_id") String refund_id) {
        rs.deleteRefund(refund_id);
    }

    @PostMapping("/addRefund/{order_id}")
    public Refund addRefund(@RequestBody Refund ref,@PathVariable String order_id) {
        return rs.addRefund(ref,order_id);
    }

    @PutMapping("/updateRefund/{refund_id}")
    public Refund updateRefund(@PathVariable("refund_id") String refund_id,@RequestBody Refund ref) throws StripeException {
        ref.setRefund_id(refund_id);
        return rs.updateRefund(ref);
    }
}
