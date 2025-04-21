package dev.dubhe.recipe.recipe;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InWorldRecipeManager {
    public static final Map<ResourceLocation, IRecipeTrigger> TRIGGERS = Collections.synchronizedMap(new HashMap<>());
    public static final Map<ResourceLocation, IRecipePredicateType<?>> PREDICATES = Collections.synchronizedMap(new HashMap<>());
    public static final Map<ResourceLocation, IRecipeOutcomeType<?>> OUTCOMES = Collections.synchronizedMap(new HashMap<>());
    public final Map<IRecipeTrigger, List<InWorldRecipe>> recipes = Collections.synchronizedMap(new HashMap<>());

    public static void register(IRecipeTrigger trigger) {
        TRIGGERS.put(trigger.getId(), trigger);
    }

    public static void register(IRecipePredicateType<?> predicate) {
        PREDICATES.put(predicate.getId(), predicate);
    }

    public static void register(IRecipeOutcomeType<?> outcome) {
        OUTCOMES.put(outcome.getId(), outcome);
    }

    public void register(@NotNull InWorldRecipe recipe) {
        this.recipes.computeIfAbsent(
            recipe.getTrigger(),
            k -> Collections.synchronizedList(new java.util.ArrayList<>())
        ).add(recipe);
    }
}
