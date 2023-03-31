package tn.esprit.cumulus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tn.esprit.cumulus.entity.Refund;
import tn.esprit.cumulus.service.RefundService;
import tn.esprit.cumulus.service.WalletService;

import java.util.List;

@RestController
@RequestMapping("/refund")
@CrossOrigin(origins = "http://localhost:4200")
public class RefundController {

    @Autowired
    RefundService rs;
    @Autowired
    WalletService ws;
    @Value("${stripe.apikey}")
    String stripeKey;


    @GetMapping("/getRefunds")
    public List<Refund> getRefunds() {
        return rs.retrieveAllRefunds();
    }

    @GetMapping("/retrieveRefund/{refund_id}")
    public Refund retrieveRefund(@PathVariable("refund_id") String refund_id) {
        return rs.retrieveRefund(refund_id);
    }

    @DeleteMapping("/deleteRefund/{refund_id}")
    public void deleteRefund(@PathVariable("refund_id") String refund_id) {
        rs.deleteRefund(refund_id);
    }

    @PostMapping("/addRefund")
    public Refund addRefund(@RequestBody Refund ref) {
        return rs.addRefund(ref);
    }

    @PutMapping("/updateRefund")
    public Refund updateRefund(@RequestBody Refund ref) {
        return rs.updateRefund(ref);
    }
}
