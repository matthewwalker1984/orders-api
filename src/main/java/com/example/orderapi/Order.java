package com.example.orderapi;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customerId == order.customerId &&
                quantity == order.quantity &&
                Objects.equals(orderReference, order.orderReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderReference, customerId, quantity);
    }
}
