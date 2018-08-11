package com.example.orderapi;

import java.util.List;

public interface OrderService {

    String createOrder(int customerId, int numberOfBricks);
    Order getOrderByReference(String orderReference);
    List<Order> getOrders();
    String updateOrder(String reference, int newQuantity);
}
