package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.commands.RecipeCommand;
import com.guruframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import com.guruframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import com.guruframework.spring5recipeapp.domain.Recipe;
import com.guruframework.spring5recipeapp.exception.NotFoundException;
import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> RecipeList() {
        log.debug("I m in the service ");
        Set<Recipe> ListDeRecette = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(ListDeRecette::add);
        return ListDeRecette;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);

        if(!recipeOptional.isPresent()){
            throw new NotFoundException("Recipe not found for ID " +l.toString());
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    /*permet de recuperer depuis le web un objet de type recipeCommand, de le convertire
    en simple POJO afin de l'enregistrer dans la BD ensuite o retourne l'objet apres un reconvertion*/
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" +savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long idToDelete) {
           recipeRepository.deleteById(idToDelete);
    }
}
