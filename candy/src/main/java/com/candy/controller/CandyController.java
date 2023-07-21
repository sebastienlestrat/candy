package com.candy.controller;

import com.candy.model.CandyTag;
import com.candy.model.CustomerOrder;
import com.candy.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/candy")
public class CandyController {

    @Autowired
    OrderService orderService;

    @PostMapping ("/order")
        public CustomerOrder createOrder (CandyTag candyTag) {
        return orderService.sendOrder(candyTag);
    }
}
