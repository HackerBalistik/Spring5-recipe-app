package com.guruframework.spring5recipeapp.controller;

import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"/recipe"})
    public String getRecipes(Model model){
        model.addAttribute("recipes", recipeRepository.findAll());

        return "recipe";
    }
}
