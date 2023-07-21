package com.candy.processor;

import com.candy.model.CandyBox;
import com.candy.model.CandyTag;

import java.util.Date;
import java.util.List;

public interface ProcessorInterface {

    CandyBox createOrder(Date orderDateTime, int totalQuantity, CandyTag candyTagName);
}