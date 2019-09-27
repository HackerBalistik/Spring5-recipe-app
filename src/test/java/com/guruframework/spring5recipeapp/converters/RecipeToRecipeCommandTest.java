package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.RecipeCommand;
import com.guruframework.spring5recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    public static final  Integer PRETIME = 25;
    public static final Integer COOKTIME = 15;
    public static final Integer SERVING = 4;
    public static final String SOURCE = "source";
    public static final String URL = "www";
    public static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    public static final String DIRECTIONS = "directions";
    public static final Long CAT_ID_1 = 2L;
    public static final Long CAT_ID_2 = 3L;
    public static final Long ING_ID_1 = 4L;
    public static final Long ING_ID_2 = 5L;
    public static final Long NOTE_ID =  6L;

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
                new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMesureToUnitOfMesureCommand()));
    }

    @Test
    public void TestNullParam() {
        assertNull(converter.convert(null));
    }

    @Test
    public void TestEmptyparam() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setCookTime(COOKTIME);
        recipe.setPreTime(PRETIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVING);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTE_ID);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);
        Category category2 = new Category();
        category2.setId(CAT_ID_2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(ING_ID_1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ING_ID_2);

        recipe.setNotes(notes);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(ID_VALUE, command.getId());
        assertEquals(COOKTIME, command.getCookTime());
        assertEquals(PRETIME, command.getPreTime());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(DIFFICULTY, command.getDifficulty());
        assertEquals(DIRECTIONS, command.getDirections());
        assertEquals(SERVING, command.getServings());
        assertEquals(SOURCE, command.getSource());
        assertEquals(URL, command.getUrl());
        assertEquals(NOTE_ID, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());
    }
}