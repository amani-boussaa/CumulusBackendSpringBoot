package tn.esprit.cumulus.service;

import tn.esprit.cumulus.entity.Refund;

import java.util.List;

public interface IRefundService {
    List<Refund> retrieveAllRefunds();

    List<Refund> retrieveAllRefundsOfUser();

    Refund addRefund(Refund refund);

    void deleteRefund(String id);

    Refund updateRefund(Refund refund);

    Refund retrieveRefund(String Refund_id);

}
