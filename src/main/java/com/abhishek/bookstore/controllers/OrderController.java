package com.abhishek.bookstore.controllers;

import com.abhishek.bookstore.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.bookstore.config.Constants;
import com.abhishek.bookstore.data.entities.Order;
import com.abhishek.bookstore.data.models.OrderRequest;

@RestController
@RequestMapping(Constants.BASE_PATH + "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderService.findAllOrders(pageable);
    }

    @PostMapping
    public Order buyBook(@RequestBody @Validated OrderRequest orderRequest) {
        return orderService.buyBook(orderRequest);
    }
}
