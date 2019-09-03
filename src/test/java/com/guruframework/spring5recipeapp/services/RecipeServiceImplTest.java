package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.domain.Recipe;
import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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