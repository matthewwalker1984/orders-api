package com.example.orderapi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BrickOrderServiceTest {

    private OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private OrderService service;

    @Before
    public void setup() {
        service = new BrickOrderService(orderRepository);
    }

    @Test
    public void whenCreateOrderIsCalledItShouldReturnTheOrder() {
        Order order = service.createOrder(1, 10);

        Assert.assertEquals(1, order.getCustomerId());
        Assert.assertEquals(10, order.getQuantity());
        Assert.assertNotNull(order.getOrderReference());
    }

    @Test
    public void whenCreateOrderIsCalledItShouldPersistTheOrder() {
        ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);

        Order order = service.createOrder(1, 10);

        Mockito.verify(orderRepository).save(argumentCaptor.capture());
        Assert.assertEquals(order, argumentCaptor.getValue());
    }
}
