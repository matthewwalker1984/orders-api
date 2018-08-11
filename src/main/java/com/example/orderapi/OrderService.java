package com.example.orderapi;

public interface OrderService {

    Order createOrder(int customerId, int numberOfBricks);
}
