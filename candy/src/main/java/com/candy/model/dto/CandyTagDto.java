package com.candy.model.dto;

import com.candy.model.CandyTagEnum;
import lombok.Data;

@Data
public class CandyTagDto {

    private int id;
    private CandyTagEnum candyTagName;
}