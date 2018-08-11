package com.example.orderapi;

import com.example.orderapi.dto.OrderRequestDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class OrderIT {

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void givenAnOrderRequest_whenOrderIsSubmitted_thenAnOrderReferenceIsReturned() {
        // given an order request
        OrderRequestDTO orderRequest = new OrderRequestDTO(1, 100);

        // when the request is submitted
        Order order = restTemplate.postForObject("http://localhost:8080/orders", orderRequest, Order.class);

        // then an order reference is returned
        Assert.assertNotNull(order.getOrderReference());
    }
}
