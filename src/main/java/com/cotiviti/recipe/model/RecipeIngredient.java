package com.cotiviti.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeIngredient {
    private int id;
    private Recipe recipe;
    private Ingredient ingredient;
    private double quantity;
    private String unit;
}
