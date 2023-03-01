package com.cotiviti.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private int id;
    private String name;
    private String description;
    private String difficulty;
    private String prepTime;
    private String category;
    private String imageUrl;
}
