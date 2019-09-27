package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.CategoryCommand;
import com.guruframework.spring5recipeapp.commands.IngredientCommand;
import com.guruframework.spring5recipeapp.commands.NotesCommand;
import com.guruframework.spring5recipeapp.commands.RecipeCommand;
import com.guruframework.spring5recipeapp.domain.Difficulty;
import com.guruframework.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
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

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new NotesCommandToNotes(),
                new IngredientCommandToIngredient(new UnitOfMesureCommandToUnitOfMesure()));
    }

    @Test
    public void TestNullParam() {
        assertNull(converter.convert(null));
    }

    @Test
    public void TestEmptyparam() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        //given
        RecipeCommand Command = new RecipeCommand();
        Command.setId(ID_VALUE);
        Command.setCookTime(COOKTIME);
        Command.setPreTime(PRETIME);
        Command.setDescription(DESCRIPTION);
        Command.setDifficulty(DIFFICULTY);
        Command.setDirections(DIRECTIONS);
        Command.setServings(SERVING);
        Command.setSource(SOURCE);
        Command.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTE_ID);

        CategoryCommand category1 = new CategoryCommand();
        category1.setId(CAT_ID_1);
        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID_2);

        IngredientCommand ingredient1 = new IngredientCommand();
        ingredient1.setId(ING_ID_1);
        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(ING_ID_2);

        Command.setNotes(notes);
        Command.getCategories().add(category1);
        Command.getCategories().add(category2);
        Command.getIngredients().add(ingredient1);
        Command.getIngredients().add(ingredient2);

        //when
        Recipe recipe = converter.convert(Command);

        //then
        assertNotNull(recipe);
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(COOKTIME, recipe.getCookTime());
        assertEquals(PRETIME, recipe.getPreTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVING, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTE_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}