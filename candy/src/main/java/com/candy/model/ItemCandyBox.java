package com.candy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
//@IdClass(ItemCandyBoxPK.class)
@Data
public class ItemCandyBox {
    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Color color;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_candyBox")
    private CandyBox candyBox;

    private int quantity;
}