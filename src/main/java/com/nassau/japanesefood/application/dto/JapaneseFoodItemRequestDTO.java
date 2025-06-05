package com.nassau.japanesefood.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JapaneseFoodItemRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    private String description;

    @NotBlank(message = "Categoria é obrigatória")
    private String category;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    private BigDecimal price;

    private Boolean available = true;

    private String ingredients;
}