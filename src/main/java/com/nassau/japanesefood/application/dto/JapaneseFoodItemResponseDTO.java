package com.nassau.japanesefood.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JapaneseFoodItemResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private Boolean available;
    private String ingredients;
}