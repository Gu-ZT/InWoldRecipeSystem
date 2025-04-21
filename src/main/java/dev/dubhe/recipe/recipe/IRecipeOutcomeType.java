package dev.dubhe.recipe.recipe;

public interface IRecipeOutcomeType<O extends IRecipeOutcome<O>> {
    ISerializer<O> getSerializer();
}
