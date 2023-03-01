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
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getInt("id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setImageUrl(resultSet.getString("image_url"));
                recipe.setCategory(resultSet.getString("category"));
                recipe.setPrepTime(resultSet.getString("prep_time"));
                recipe.setDifficulty(resultSet.getString("difficulty"));
                recipes.add(recipe);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            terminateConnection();
        }
        return recipes;
    }
}
