package com.example.orderapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BrickOrderService implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public BrickOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(int customerId, int quantity) {
        String orderReference = UUID.randomUUID().toString();

        Order order = new Order(customerId, quantity);
        order.setOrderReference(orderReference);

        orderRepository.save(order);

        return order;
    }
}
