package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.RecipeCommand;
import com.guruframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory categoryConverter;
    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter,
                                 NotesCommandToNotes notesConverter, IngredientCommandToIngredient ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Nullable
    @Synchronized
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final  Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPreTime(source.getPreTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setImage(source.getImage());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size()>0){
            source.getCategories()
                    .forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        if (source.getIngredients() != null && source.getIngredients().size()>0){
            source.getIngredients()
                    .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        return recipe;
    }
}
