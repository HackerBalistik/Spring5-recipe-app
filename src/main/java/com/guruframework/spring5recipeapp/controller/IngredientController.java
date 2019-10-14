package com.guruframework.spring5recipeapp.controller;

import com.guruframework.spring5recipeapp.commands.IngredientCommand;
import com.guruframework.spring5recipeapp.services.IngredientService;
import com.guruframework.spring5recipeapp.services.RecipeService;
import com.guruframework.spring5recipeapp.services.UnitOfMesureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMesureService unitOfMesureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMesureService unitOfMesureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMesureService = unitOfMesureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String ListIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredients List for recipe Id :"+recipeId);
        //use command object to avoid lazy load errors in thymeleaf
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
        return "/recipe/ingredients/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{ingredientId}/show")
    public String showRecipeIngredients(@PathVariable String recipeId,
                                        @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "/recipe/ingredients/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                               @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        //recuperation de la liste des UOM
        model.addAttribute("uomList", unitOfMesureService.ListAllUom());

        return "/recipe/ingredients/ingredientForm";
    }

    @PostMapping("/recipe/{recipeId}/ingredients")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand saveCommand = ingredientService.saveIngredientCommand(command);

        log.debug("save ingredient Id :"+saveCommand.getId());
        System.out.println(saveCommand.getId());

        return "redirect:/recipe/"+saveCommand.getRecipeId()+"/ingredients/"+saveCommand.getId()+"/show";
    }

}
