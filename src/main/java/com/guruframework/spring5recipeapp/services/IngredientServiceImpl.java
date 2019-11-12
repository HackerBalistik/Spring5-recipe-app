package com.guruframework.spring5recipeapp.services;

import com.guruframework.spring5recipeapp.commands.IngredientCommand;
import com.guruframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import com.guruframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import com.guruframework.spring5recipeapp.domain.Ingredient;
import com.guruframework.spring5recipeapp.domain.Recipe;
import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import com.guruframework.spring5recipeapp.repository.UnitOfMesureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMesureRepository unitOfMesureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, UnitOfMesureRepository unitOfMesureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe ID not found : "+recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("ingredient Id not found :"+ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        //on recupère un optional de la recette correspondant a l'ingredient
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        //si aucune recette n'est trouvée on retourne un nouvelle ingredient, sinon
        //on recupère la recette trouvée
        if (!recipeOptional.isPresent()){
            log.error("Recipe not found for id :"+command.getId());
            return new IngredientCommand();
        }else {
            Recipe recipe = recipeOptional.get();
            /*on recupère pour cette recette un stream de tous les ingredients, on les filtres
            et on recupère l'ingredient dont l'ID correspond a l'ingredient qu'on recherche(ici command)*/
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()){
                Ingredient foundIngredient = ingredientOptional.get();
                foundIngredient.setDescription(command.getDescription());
                foundIngredient.setAmount(command.getAmount());
                foundIngredient.setUom(unitOfMesureRepository.findById(command.getUom().getId())
                                    .orElseThrow(() -> new RuntimeException("Uom not found")));
            }else {
                //on ajoute un nouvelle ingredient
                recipe.addIngredients(Objects.requireNonNull(ingredientCommandToIngredient.convert(command)));
            }

            //on sauvegarde la rectte dont on viens de modifier l'ingredient
            Recipe savedRecipe = recipeRepository.save(recipe);

            //On retourne ensuite le command object de notre ingredient
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients()
                                            .stream()
                                            .filter(recipeIngredient -> recipeIngredient.getId().equals(command.getId()))
                                            .findFirst().get());
        }
    }

    @Override
    public void deleteById(Long recipeId, Long idToDelete) {
        log.debug("Deleting ingredient "+recipeId+" "+idToDelete);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            log.debug("recipe found");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(idToDelete))
                    .findFirst();

            if (ingredientOptional.isPresent()){
                log.debug("ingredient found");
                Ingredient ingredientToDelete = ingredientOptional.get();
                //on enlève notre recette dans les paramètre de cet ingredient
                ingredientToDelete.setRecipe(null);
                //et on supprime l'ingredient de la recette
                //on annule ainsi la relation entre les deux ce qui causera la suppression de l'ingredient dans la base de donnée par hibernate
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        }else {
            log.debug("recipe id "+recipeId+" not found");
        }
    }
}
