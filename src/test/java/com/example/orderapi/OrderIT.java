package com.example.orderapi;

import com.example.orderapi.dto.OrderRequestDTO;
import org.aspectj.weaver.ast.Or;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void givenAnOrderRequest_whenOrderIsSubmitted_thenAnOrderReferenceIsReturned() {
        // given an order request
        OrderRequestDTO orderRequest = new OrderRequestDTO(1, 100);

        // when the request is submitted
        String orderReference = restTemplate.postForObject("/orders", orderRequest, String.class);

        // then an order reference is returned
        Assert.assertNotNull(orderReference);
    }

    @Test
    public void givenAnOrderRequest_whenAnOrderIsSubmitted_thenACreatedStatusIsReturned() {
        // given an order request
        OrderRequestDTO orderRequest = new OrderRequestDTO(1, 100);

        // when the request is submitted
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("/orders", orderRequest, String.class);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void givenARequestToViewAnOrder_whenTheRequestIsSubmitted_thenTheOrderIsReturned() {
        // given a customer has created an order
        Order order = new Order(1, 100);
        order.setOrderReference("testReference");

        repository.save(order);

        // when a get order request is submitted
        ResponseEntity<Order> responseEntity = restTemplate.getForEntity("/orders/testReference", Order.class);

        // then the order details are returned and the quantity is correct
        Assert.assertEquals(order, responseEntity.getBody());
    }

    @Test
    public void givenARequestToViewAnOrderWithAnInvalidReference_whenTheRequestIsSubmitted_thenNoOrderIsReturned() {
        // given a request to view an order with an invalid reference
        // when the request is submitted
        ResponseEntity<Order> responseEntity = restTemplate.getForEntity("/orders/invalidReference", Order.class);

        // then no order is returned
        Assert.assertNull(responseEntity.getBody());
    }

    @Test
    public void givenManyCustomersHaveCreatedOrders_whenAGetOrdersRequestIsSubmitted_thenAllOrdersAreReturned() {
        // given many customers have submitted orders
        List<Order> orders = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Order order = new Order(i, new Random().nextInt());
            order.setOrderReference("reference " + i);
            orders.add(order);
        }

        repository.saveAll(orders);

        // when a get orders request is submitted
        ResponseEntity<Order[]> responseEntity = restTemplate.getForEntity("/orders", Order[].class);

        // then all the orders are returned
        Assert.assertArrayEquals(orders.toArray(), responseEntity.getBody());
    }
}
