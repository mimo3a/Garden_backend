package com.example.garden.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    private int minHumidity;
    private int maxHumidity;
}
