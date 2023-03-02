package com.cotiviti.recipe.service;

import com.cotiviti.recipe.model.Recipe;
import com.cotiviti.recipe.repository.RecipeRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "recipeService", eager = true)
@RequestScoped
public class RecipeService {

    @ManagedProperty(value = "#{recipeRepository}")
    private RecipeRepository repository;

    public void setRepository(RecipeRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> getAllRecipes() {
        return repository.getAllRecipes();
    }

    public Recipe addRecipe(Recipe recipe) {
        return repository.addRecipe(recipe);
    }
}
