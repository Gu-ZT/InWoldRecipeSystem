package dev.dubhe.recipe.recipe;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class InWorldRecipeManager {
    public final Map<IRecipeTrigger, Set<InWorldRecipe>> recipes = Collections.synchronizedMap(new HashMap<>());

    public void register(@NotNull InWorldRecipe recipe) {
        Set<InWorldRecipe> recipeList = this.recipes.computeIfAbsent(
            recipe.getTrigger(),
            k -> Collections.synchronizedSet(new TreeSet<>())
        );
        recipeList.add(recipe);
    }

    public void trigger(IRecipeTrigger trigger, @NotNull InWorldRecipeContext ctx) {
        recipes.getOrDefault(trigger, Collections.emptySet()).forEach(recipe -> {
            if (recipe.matches(ctx, ctx.getLevel())) {
                recipe.assemble(ctx, ctx.getLevel().registryAccess());
            }
        });
    }
}
