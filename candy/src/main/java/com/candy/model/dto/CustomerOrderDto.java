package com.candy.model.dto;

import com.candy.model.CandyTag;
import com.candy.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerOrderDto {

    private int totalQuantity;
    private int userId;
    private CandyTag candyTagName;
}