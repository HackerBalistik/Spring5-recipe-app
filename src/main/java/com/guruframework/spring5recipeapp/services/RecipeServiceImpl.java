package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.domain.Recipe;
import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> RecipeList() {
        Set<Recipe> ListDeRecette = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(ListDeRecette::add);
        return ListDeRecette;
    }
}
