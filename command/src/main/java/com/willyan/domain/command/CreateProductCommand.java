package com.willyan.domain.command;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand implements Command{

    private String imageUrl;
    
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal value;
}
