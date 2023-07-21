package com.candy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CandyTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private CandyTagEnum candyTagName;
}
