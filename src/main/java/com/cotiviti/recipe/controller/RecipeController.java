package com.cotiviti.recipe.controller;

import com.cotiviti.recipe.model.Recipe;
import com.cotiviti.recipe.service.RecipeService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class RecipeController {
    @ManagedProperty(value = "#{recipeService}")
    private RecipeService recipeService;

    private List<Recipe> recipes = new ArrayList<>();

    public List<Recipe> getRecipes() {
        if (recipeService != null) {
            return recipeService.getAllRecipes();
        }
        return recipes;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
