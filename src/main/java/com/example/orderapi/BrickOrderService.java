package com.example.orderapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BrickOrderService implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public BrickOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createOrder(int customerId, int quantity) {
        String orderReference = UUID.randomUUID().toString();

        Order order = new Order(customerId, quantity);
        order.setOrderReference(orderReference);

        orderRepository.save(order);

        return orderReference;
    }

    @Override
    public Order getOrderByReference(String orderReference) {
        return orderRepository.findByOrderReference(orderReference);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public String updateOrder(String reference, int newQuantity) {
        Order order = orderRepository.findByOrderReference(reference);

        order.setQuantity(newQuantity);

        orderRepository.save(order);

        return reference;
    }
}
