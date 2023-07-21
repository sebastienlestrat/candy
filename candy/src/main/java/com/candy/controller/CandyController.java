package com.candy.controller;

import com.candy.model.CandyTag;
import com.candy.model.CustomerOrder;
import com.candy.model.dto.CustomerOrderDto;
import com.candy.model.dto.CustomerOrderResponseDto;
import com.candy.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("api/candy")
public class CandyController {


    @PostMapping ("/order")
        public CustomerOrderResponseDto createOrder (@RequestBody CustomerOrderDto customerOrderDto) {
        return null;
    }
}