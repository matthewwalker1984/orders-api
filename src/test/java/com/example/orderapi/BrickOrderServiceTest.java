package com.example.orderapi;

import org.aspectj.weaver.ast.Or;
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
    public void createOrderShouldReturnTheOrderReference() {
        String reference = service.createOrder(1, 10);

        Assert.assertNotNull(reference);
    }

    @Test
    public void createOrderShouldPersistTheOrder() {
        service.createOrder(1, 10);

        Mockito.verify(orderRepository).save(Mockito.any(Order.class));
    }

    @Test
    public void getOrderShouldRetrieveTheCorrectOrder() {
        String reference = "reference";
        service.getOrderByReference(reference);

        Mockito.verify(orderRepository).findByOrderReference(reference);
    }

    @Test
    public void getOrdersShouldGetAllTheOrdersFromTheRepository() {
        service.getOrders();

        Mockito.verify(orderRepository).findAll();
    }

    @Test
    public void updateOrderShouldSaveTheUpdatedOrder() {
        Order order = new Order(1, 10);
        order.setOrderReference("reference");

        Mockito.when(orderRepository.findByOrderReference("reference")).thenReturn(order);

        service.updateOrder("reference", 100);

        ArgumentCaptor<Order> argumentCaptor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderRepository).save(argumentCaptor.capture());

        Assert.assertEquals(100, argumentCaptor.getValue().getQuantity());
    }
}
