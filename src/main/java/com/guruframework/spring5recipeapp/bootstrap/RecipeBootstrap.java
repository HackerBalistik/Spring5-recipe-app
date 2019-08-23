package com.guruframework.spring5recipeapp.bootstrap;

import com.guruframework.spring5recipeapp.domain.*;
import com.guruframework.spring5recipeapp.repository.CategoryRepository;
import com.guruframework.spring5recipeapp.repository.RecipeRepository;
import com.guruframework.spring5recipeapp.repository.UnitOfMesureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMesureRepository unitOfMesureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMesureRepository unitOfMesureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMesureRepository = unitOfMesureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(RecipeList());
    }

    public List<Recipe> RecipeList() {
        List<Recipe> recipeList = new ArrayList<>();

        //UomList
        Optional<UnitOfMesure> UomTeaspoonOptional = unitOfMesureRepository.findByDescription("Teaspoon");
        if (!UomTeaspoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> UomTablespoonOptional = unitOfMesureRepository.findByDescription("Tablespoon");
        if (!UomTablespoonOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> UomCupOptional = unitOfMesureRepository.findByDescription("Cup");
        if (!UomCupOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMesure> UomPinchOptional = unitOfMesureRepository.findByDescription("Pinch");
        if (!UomPinchOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMesure> UomOunceOptional = unitOfMesureRepository.findByDescription("Ounce");
        if (!UomOunceOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMesure> UomEachOptional = unitOfMesureRepository.findByDescription("Each");
        if (!UomEachOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMesure> UomDashOptional = unitOfMesureRepository.findByDescription("Dash");
        if (!UomDashOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMesure> UomPintOptional = unitOfMesureRepository.findByDescription("Pint");
        if (!UomPintOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        //getUom
        UnitOfMesure UomTeaspoon = UomTeaspoonOptional.get();
        UnitOfMesure UomTablespoon = UomTablespoonOptional.get();
        UnitOfMesure UomCup = UomCupOptional.get();
        UnitOfMesure UomPinch = UomPinchOptional.get();
        UnitOfMesure UomOunce = UomOunceOptional.get();
        UnitOfMesure UomEach = UomEachOptional.get();
        UnitOfMesure UomDash = UomDashOptional.get();
        UnitOfMesure UomPint = UomPintOptional.get();

        //CategoryList
        Optional<Category> AmericanCategoryOptional = categoryRepository.findByDescription("American");
        if(!AmericanCategoryOptional.isPresent()){
            throw new RuntimeException("Category Expected not found");
        }

        Optional<Category> MexicanCategoryOptional  = categoryRepository.findByDescription("Mexican");
        if(!MexicanCategoryOptional.isPresent()){
            throw new RuntimeException("category Expected not found");
        }

        //getCategory
        Category AmericanCategory = AmericanCategoryOptional.get();
        Category MexicanCategory = MexicanCategoryOptional.get();

        //Recette Guaccamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPreTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setServings(4);
        guacRecipe.setSource("Simply Recipes");
        guacRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole");
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."
        );
        guacRecipe.setDifficulty(Difficulty.EASY);

        //ajout des differents ingredients a la listes des ingredients de cette recettes
        guacRecipe.addIngredients(new Ingredient("ripe avocados", new BigDecimal(2), UomEach));
        guacRecipe.addIngredients(new Ingredient("Kosher salt", new BigDecimal(".5"), UomTeaspoon));
        guacRecipe.addIngredients(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), UomTablespoon));
        guacRecipe.addIngredients(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), UomTablespoon));
        guacRecipe.addIngredients(new Ingredient(" serrano chiles, stems and seeds removed, minced", new BigDecimal(2), UomEach));
        guacRecipe.addIngredients(new Ingredient("cilantro", new BigDecimal(2), UomTablespoon));
        guacRecipe.addIngredients(new Ingredient("freshly grated black pepper", new BigDecimal(1), UomDash));
        guacRecipe.addIngredients(new Ingredient("tomato, seeds and pulp removed, chopped", new BigDecimal(".5"), UomEach));

        //creation d'une nouvelles note
        Notes guacNote = new Notes();
        guacNote.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!"
        );
        //ajout de la note a la recette
        //et aussi ajout de la recette a la note pour qu'on puisse aller dans les deux sens
        guacRecipe.setNotes(guacNote);

        //ajouter cette recette a des categories(on ne creer pas un nouvelle objet category on ajoute simplement une category deja existente a notre recette
        guacRecipe.getCategories().add(AmericanCategory);
        guacRecipe.getCategories().add(MexicanCategory);

        //on ajoute notre recette a la liste de recette
        recipeList.add(guacRecipe);



        //Recette Tacos
        Recipe TacosRecipe = new Recipe();
        TacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        TacosRecipe.setPreTime(20);
        TacosRecipe.setCookTime(15);
        TacosRecipe.setServings(6);
        TacosRecipe.setSource("Simply Recipes");
        TacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");
        TacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
        );

        TacosRecipe.setDifficulty(Difficulty.MODERATE);

        TacosRecipe.addIngredients(new Ingredient("ancho chili powder", new BigDecimal(2), UomTablespoon));
        TacosRecipe.addIngredients(new Ingredient("dried oregano", new BigDecimal(1), UomTeaspoon));
        TacosRecipe.addIngredients(new Ingredient("Dried Cumin", new BigDecimal(1), UomTeaspoon));
        TacosRecipe.addIngredients(new Ingredient("Sugar", new BigDecimal(1), UomTeaspoon));
        TacosRecipe.addIngredients(new Ingredient("Salt", new BigDecimal(".5"), UomTeaspoon));
        TacosRecipe.addIngredients(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), UomEach));
        TacosRecipe.addIngredients(new Ingredient("finely grated orange zestr", new BigDecimal(1), UomTablespoon));
        TacosRecipe.addIngredients(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), UomTablespoon));
        TacosRecipe.addIngredients(new Ingredient("Olive Oil", new BigDecimal(2), UomTablespoon));
        TacosRecipe.addIngredients(new Ingredient("boneless chicken thighs", new BigDecimal(4), UomTablespoon));
        TacosRecipe.addIngredients(new Ingredient("small corn tortillas", new BigDecimal(8), UomEach));
        TacosRecipe.addIngredients(new Ingredient("packed baby arugula", new BigDecimal(3), UomCup));
        TacosRecipe.addIngredients(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), UomEach));
        TacosRecipe.addIngredients(new Ingredient("radishes, thinly sliced", new BigDecimal(4), UomEach));
        TacosRecipe.addIngredients(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), UomPint));
        TacosRecipe.addIngredients(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), UomEach));
        TacosRecipe.addIngredients(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), UomEach));
        TacosRecipe.addIngredients(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), UomCup));
        TacosRecipe.addIngredients(new Ingredient("lime, cut into wedges", new BigDecimal(4), UomEach));

        Notes tacosNote = new Notes();
        tacosNote.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\\n\" +\n"
                + "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\\n\" " +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\\n\" " +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\\n\" " +
                 "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\\n\" +\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ"
        );
        TacosRecipe.setNotes(tacosNote);

        TacosRecipe.getCategories().add(AmericanCategory);
        TacosRecipe.getCategories().add(MexicanCategory);

        recipeList.add(TacosRecipe);

        return recipeList;
    }
}
