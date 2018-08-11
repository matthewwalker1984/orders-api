package com.example.orderapi.dto;

public class OrderRequestDTO {

    private Integer customerId;
    private Integer quantity;

    public OrderRequestDTO() {}

    public OrderRequestDTO(Integer customerId, Integer quantity) {
        this.customerId = customerId;
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
