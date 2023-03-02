package com.cotiviti.recipe.repository;

import com.cotiviti.recipe.db.DBConnection;
import com.cotiviti.recipe.db.QueryBuilder;
import com.cotiviti.recipe.model.Recipe;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "recipeRepository", eager = true)
@RequestScoped
public class RecipeRepository extends DBConnection {

    public List<Recipe> getAllRecipes() {
        createConnection();
        QueryBuilder queryBuilder = new QueryBuilder(getConnection());
        List<Recipe> recipes = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = queryBuilder.select().from("recipes").build();
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                recipes.add(setRecipe(resultSet));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            terminateConnection();
        }
        return recipes;
    }

    public Recipe addRecipe(Recipe recipe) {
        createConnection();
        QueryBuilder queryBuilder = new QueryBuilder(getConnection());
        Recipe newRecipe = null;
        try {
            PreparedStatement prepareStatement = queryBuilder.insert("recipes", "name", "description",
                    "difficulty", "prep_time", "category", "image_url").build();
            prepareStatement.setString(1, recipe.getName());
            prepareStatement.setString(2, recipe.getDescription());
            prepareStatement.setString(3, recipe.getDifficulty());
            prepareStatement.setString(4, recipe.getPrepTime());
            prepareStatement.setString(5, recipe.getCategory());
            prepareStatement.setString(6, recipe.getImageUrl());
            prepareStatement.executeUpdate();
            ResultSet resultSet = prepareStatement.getGeneratedKeys();
            while (resultSet.next()) {
                newRecipe = setRecipe(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            terminateConnection();
        }
        return newRecipe;
    }

    private Recipe setRecipe(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setDescription(resultSet.getString("description"));
        recipe.setImageUrl(resultSet.getString("image_url"));
        recipe.setCategory(resultSet.getString("category"));
        recipe.setPrepTime(resultSet.getString("prep_time"));
        recipe.setDifficulty(resultSet.getString("difficulty"));
        return recipe;
    }
}
