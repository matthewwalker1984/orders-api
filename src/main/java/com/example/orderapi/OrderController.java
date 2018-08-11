package com.example.orderapi;

import com.example.orderapi.dto.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        String orderReference = orderService.createOrder(orderRequest.getCustomerId(), orderRequest.getQuantity());

        return new ResponseEntity<>(orderReference, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/orders/{orderReference}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable String orderReference) {
        return orderService.getOrderByReference(orderReference);
    }

    @RequestMapping(value = "/orders/{orderReference}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateOrder(@PathVariable String orderReference, @RequestBody OrderRequestDTO request) {
        String newReference = orderService.updateOrder(orderReference, request.getQuantity());

        return new ResponseEntity<>(newReference, HttpStatus.ACCEPTED);
    }
}
