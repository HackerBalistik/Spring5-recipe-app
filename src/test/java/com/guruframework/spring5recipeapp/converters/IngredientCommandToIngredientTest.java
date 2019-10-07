package com.guruframework.spring5recipeapp.converters;

import com.guruframework.spring5recipeapp.commands.IngredientCommand;
import com.guruframework.spring5recipeapp.commands.UnitOfMesureCommand;
import com.guruframework.spring5recipeapp.domain.Ingredient;
import com.guruframework.spring5recipeapp.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Recipe recipe = new Recipe();
    public static final Long LONG_VALUE = 1L;
    public static final String DESCRIPTION ="description";
    public static final BigDecimal Amout = new BigDecimal("1");
    public static final Long UOM_ID = 2L;

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(
                new UnitOfMesureCommandToUnitOfMesure());
    }

    @Test
    public void TestNullParam() {
        assertNull(converter.convert(null));
    }

    @Test
    public void TestEmptyParam() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(Amout);
        UnitOfMesureCommand uomCommand = new UnitOfMesureCommand();
        uomCommand.setId(UOM_ID);
        command.setUom(uomCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(Amout, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @Test
    public void ConvertWithNullUOM() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(Amout);
        UnitOfMesureCommand uomCommand = new UnitOfMesureCommand();

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(LONG_VALUE, ingredient.getId());
        assertEquals(Amout, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }
}