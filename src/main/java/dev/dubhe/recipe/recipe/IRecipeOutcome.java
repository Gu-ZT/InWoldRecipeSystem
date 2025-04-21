package dev.dubhe.recipe.recipe;

import java.util.function.Consumer;

public interface IRecipeOutcome<O extends IRecipeOutcome<O>> extends Consumer<InWorldRecipe>, IPrioritized {
    IRecipeOutcomeType<O> getType();
}
