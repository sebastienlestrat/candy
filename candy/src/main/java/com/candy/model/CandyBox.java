package com.candy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CandyBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_customerOrder")
    private CustomerOrder customerOrder;

   @OneToMany(mappedBy = "candyBox")
   private List<ItemCandyBox> totalCandyBox;
}