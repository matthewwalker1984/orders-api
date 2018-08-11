package com.example.orderapi;

import com.example.orderapi.dto.OrderRequestDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    private OrderService orderService = Mockito.mock(OrderService.class);
    private OrderController orderController;

    @Before
    public void setup() {
        orderController = new OrderController(orderService);
    }

    @Test
    public void createOrderShouldCallTheServiceMethod() {
        Mockito.when(orderService.createOrder(1, 10)).thenReturn("reference");

        OrderRequestDTO request = new OrderRequestDTO(1, 10);

        orderController.createOrder(request);

        Mockito.verify(orderService).createOrder(1, 10);
    }

    @Test
    public void createOrderShouldReturnTheOrderReference() {
        OrderRequestDTO request = new OrderRequestDTO(1, 10);

        Order expected = new Order(1, 10);
        expected.setOrderReference("testOrder");

        Mockito.when(orderService.createOrder(1, 10)).thenReturn("testOrder");

        String reference = orderController.createOrder(request).getBody();

        Assert.assertEquals("testOrder", reference);
    }

    @Test
    public void getOrderShouldCallTheServiceMethod() {
        orderController.getOrder("reference");

        Mockito.verify(orderService).getOrderByReference("reference");
    }

    @Test
    public void getOrdersShouldCallTheServiceMethod() {
        orderController.getOrders();

        Mockito.verify(orderService).getOrders();
    }
}

