package tn.esprit.cumulus.service;

import tn.esprit.cumulus.entity.Order;

import java.util.List;

public interface IOrderService {

    List<Order> retrieveAllOrders();

    List<Order> retrieveAllOrdersOfUser(Long userId);

    Order addOrder(Order c);

    void deleteOrder(String id);

    Order updateOrder(Order c);

    Order retrieveOrder(String order_id);
}
