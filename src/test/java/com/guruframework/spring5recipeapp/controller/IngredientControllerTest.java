package com.guruframework.spring5recipeapp.controller;

import com.guruframework.spring5recipeapp.commands.IngredientCommand;
import com.guruframework.spring5recipeapp.commands.RecipeCommand;
import com.guruframework.spring5recipeapp.services.IngredientService;
import com.guruframework.spring5recipeapp.services.RecipeService;
import com.guruframework.spring5recipeapp.services.UnitOfMesureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMesureService unitOfMesureService;
    IngredientController controller;
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(recipeService, ingredientService, unitOfMesureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listIngredients() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(command);
        //then
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredients/list"))
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void TestShowRecipeIngredients() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //then
        mockMvc.perform(get("/recipe/1/ingredients/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredients/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void TestSaveOrUpdateIngredient() throws Exception{
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(1L);
        ingredientCommand.setRecipeId(2L);
        //when
        when(ingredientService.saveIngredientCommand(any())).thenReturn(ingredientCommand);
        //then
        mockMvc.perform(post("/recipe/2/ingredients")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("id", "")
                            .param("description", "some String")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients/1/show"));
    }

    @Test
    public void TestUpdateIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMesureService.ListAllUom()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredients/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredients/ingredientForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }
}