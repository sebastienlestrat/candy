package com.candy.model.dto;

import com.candy.model.CandyTag;
import com.candy.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerOrderDto {
    private int id;
    private Date orderDateTime;
    private int totalQuantity;
    private User user;
    private CandyTag candyTag;
}