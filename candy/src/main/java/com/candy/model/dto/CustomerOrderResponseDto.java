package com.candy.model.dto;

import com.candy.model.CustomerOrder;
import lombok.Data;

@Data
public class CustomerOrderResponseDto {
   private CustomerOrder order;
    private String errorMessage;
    private int status;
}