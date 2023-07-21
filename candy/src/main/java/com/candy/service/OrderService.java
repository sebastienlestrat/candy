package com.candy.service;

import com.candy.model.CandyTag;
import com.candy.model.CustomerOrder;
import com.candy.model.dto.CustomerOrderDto;
import com.candy.model.dto.CustomerOrderResponseDto;
import com.candy.processor.ProcessorInterface;
import com.candy.processor.factory.AbstractProcessorFactory;
import com.candy.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    AbstractProcessorFactory processorFactory;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

//    public CustomerOrderResponseDto sendOrder (CustomerOrderDto customerOrderDto) {
//        CustomerOrderResponseDto customerOrderResponseDto = new CustomerOrderResponseDto();
//
//        ProcessorInterface processor = processorFactory.createProcessor(customerOrderDto.getCandyTagName().getCandyTagName());
//        processor.createOrder(customerOrderDto.getTotalQuantity(), customerOrderDto.getCandyTagName().getCandyTagName());
//
//        return customerOrderRepository.save(processor);
//
//    }
}