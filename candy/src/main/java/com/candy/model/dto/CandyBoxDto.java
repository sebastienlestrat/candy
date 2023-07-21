package com.candy.model.dto;

import com.candy.model.CustomerOrder;
import com.candy.model.ItemCandyBox;
import lombok.Data;
import java.util.List;

@Data
public class CandyBoxDto {

    private int id;
    private CustomerOrder customerOrder;
    private List<ItemCandyBox> totalCandyBox;
}