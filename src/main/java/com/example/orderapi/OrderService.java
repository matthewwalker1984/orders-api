package com.example.orderapi;

import java.util.List;

public interface OrderService {

    Order createOrder(int customerId, int numberOfBricks);
    Order getOrderByReference(String orderReference);
    List<Order> getOrders();
}
