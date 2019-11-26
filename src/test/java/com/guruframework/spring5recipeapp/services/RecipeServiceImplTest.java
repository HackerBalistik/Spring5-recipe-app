package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.guruframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.guruframework.spring5recipeapp.domain.Recipe;
import com.guruframework.spring5recipeapp.exception.NotFoundException;
import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeSet = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeSet);

        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeIdTestNotFound() throws Exception{
        Optional<Recipe> recipeOptional = Optional.empty();
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
    }

    @Test
    public void recipeList() {
        Recipe recipe = new Recipe();
        HashSet recipeData  = new HashSet();
        recipeData.add(recipe);

        when(recipeService.RecipeList()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.RecipeList();

        assertEquals(recipes.size(), 1);
        assertTrue(recipes.contains(recipe));

        verify(recipeRepository, times(1)).findAll();
    }
}