package com.guruframework.spring5recipeapp.controller;
import com.guruframework.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index"})
    public String getIndex(Model model){
       model.addAttribute("recipe", recipeService.RecipeList());
        return "index";
    }
}
