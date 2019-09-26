package com.guruframework.spring5recipeapp.commands;

import com.guruframework.spring5recipeapp.domain.UnitOfMesure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long Id;
    private String description;
    private BigDecimal amount;
    private UnitOfMesure unitOfMesure;
}
