package com.guruframework.spring5recipeapp.controller;

import com.guruframework.spring5recipeapp.commands.RecipeCommand;
import com.guruframework.spring5recipeapp.exception.NotFoundException;
import com.guruframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/recipe"})
    public String getRecipes(Model model){
        log.debug("Getting Recipe page");
        model.addAttribute("recipes", recipeService.RecipeList());

        return "recipe";
    }

    @GetMapping({"/recipe/{id}/show"})
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "/recipe/show";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){
        log.error("Handle Not Found Exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Error404");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "/recipe/recipeForm";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "/recipe/recipeForm";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/"+savedCommand.getId()+"/show";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("deleting id " +id);
        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/recipe";
    }
}
