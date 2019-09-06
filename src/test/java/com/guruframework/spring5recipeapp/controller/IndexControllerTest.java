package com.guruframework.spring5recipeapp.controller;

import com.guruframework.spring5recipeapp.domain.Recipe;
import com.guruframework.spring5recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;
    IndexController controller;

    @Before
    public void SetUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeService);
    }

    @Test
    public void MockMvcTest() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndex() {
        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);

        when(recipeService.RecipeList()).thenReturn(recipes);
        ArgumentCaptor <Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        //when
        String viewName = controller.getIndex(model);

        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).RecipeList();
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        Set<Recipe> IndController = argumentCaptor.getValue();
        assertEquals(2, IndController.size());

    }
}