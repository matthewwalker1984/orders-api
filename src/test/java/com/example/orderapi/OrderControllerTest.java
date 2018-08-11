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
    public void whenAnOrderRequestIsSubmittedTheOrderServiceIsCalled() {
        // given an order request
        OrderRequestDTO request = new OrderRequestDTO(1, 10);

        // when the request is submitted
        orderController.createOrder(request);

        // then the order service should be called;
        Mockito.verify(orderService).createOrder(1, 10);
    }

    @Test
    public void whenAnOrderRequestIsSubmittedTheOrderIsReturned() {
        // given an order request
        OrderRequestDTO request = new OrderRequestDTO(1, 10);

        Order expected = new Order(1, 10);
        expected.setOrderReference("testOrder");

        Mockito.when(orderService.createOrder(1, 10)).thenReturn(expected);

        // when the order request is submitted
        Order actual = orderController.createOrder(request).getBody();

        // then the submitted order is returned
        Assert.assertEquals(expected, actual);
    }
}
