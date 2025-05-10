package dev.dubhe.recipe.util.injector;

import dev.dubhe.recipe.recipe.InWorldRecipeManager;

public interface IRecipeManager {
    void inWoldRecipeSystem$setInWorldRecipeManager(InWorldRecipeManager manager);

    InWorldRecipeManager inWoldRecipeSystem$getInWorldRecipeManager();
}
