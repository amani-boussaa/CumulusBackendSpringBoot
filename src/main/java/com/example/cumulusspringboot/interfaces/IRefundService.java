package com.example.cumulusspringboot.interfaces;

import com.stripe.exception.StripeException;
import com.example.cumulusspringboot.entities.Refund;

import java.util.List;

public interface IRefundService {
    List<Refund> retrieveAllRefunds();

    List<Refund> retrieveAllRefundsOfUser(Long userId);

    Refund addRefund(Refund refund, String order_id);

    void deleteRefund(String id);

    Refund updateRefund(Refund refund) throws StripeException;

    Refund retrieveRefund(String Refund_id);

}
