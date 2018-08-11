package com.example.orderapi;

import com.example.orderapi.dto.OrderRequestDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenAnOrderRequest_whenOrderIsSubmitted_thenAnOrderReferenceIsReturned() {
        // given an order request
        OrderRequestDTO orderRequest = new OrderRequestDTO(1, 100);

        // when the request is submitted
        Order order = restTemplate.postForObject("/orders", orderRequest, Order.class);

        // then an order reference is returned
        Assert.assertEquals(1, order.getCustomerId());
        Assert.assertEquals(100, order.getQuantity());
        Assert.assertNotNull(order.getOrderReference());
    }

    @Test
    public void givenAnOrderRequest_whenAnOrderIsSubmitted_thenACreatedStatusIsReturned() {
        // given an order request
        OrderRequestDTO orderRequest = new OrderRequestDTO(1, 100);

        // when the request is submitted
        ResponseEntity<Order> responseEntity = restTemplate.postForEntity("/orders", orderRequest, Order.class);

        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
