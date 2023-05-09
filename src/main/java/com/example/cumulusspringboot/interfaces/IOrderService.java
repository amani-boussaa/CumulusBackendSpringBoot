package com.example.cumulusspringboot.interfaces;

import com.example.cumulusspringboot.entities.Order;

import java.util.List;

public interface IOrderService {

    List<Order> retrieveAllOrders();

    List<Order> retrieveAllOrdersOfUser(Long userId);

    Order addOrder(Order c,Long id);

    void deleteOrder(String id);

    Order updateOrder(Order c);

    Order retrieveOrder(String order_id);
}
