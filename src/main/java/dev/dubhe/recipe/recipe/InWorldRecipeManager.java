package dev.dubhe.recipe.recipe;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InWorldRecipeManager {
    public final Map<IRecipeTrigger, List<InWorldRecipe>> recipes = Collections.synchronizedMap(new HashMap<>());

    public void register(@NotNull InWorldRecipe recipe) {
        this.recipes.computeIfAbsent(
            recipe.getTrigger(),
            k -> Collections.synchronizedList(new java.util.ArrayList<>())
        ).add(recipe);
    }
}
