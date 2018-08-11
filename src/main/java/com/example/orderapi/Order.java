package com.example.orderapi;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "orders")
public class Order {

    @Id
    private String orderReference;
    private int customerId;
    private int quantity;

    public Order() {}

    public Order(int customerId, int quantity) {
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public String getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
