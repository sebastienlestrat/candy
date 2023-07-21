package com.candy.processor;

import com.candy.model.CandyBox;
import com.candy.model.CandyTag;
import com.candy.model.CandyTagEnum;

import java.util.Date;

public interface ProcessorInterface {

    CandyBox createOrder(int totalQuantity, CandyTagEnum candyTagName);
}