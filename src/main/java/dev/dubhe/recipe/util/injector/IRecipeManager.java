package dev.dubhe.recipe.util.injector;

import dev.dubhe.recipe.recipe.InWorldRecipeManager;

public interface IRecipeManager {
    default void inWoldRecipeSystem$setInWorldRecipeManager(InWorldRecipeManager manager) {
        throw new AssertionError();
    }

    default InWorldRecipeManager inWoldRecipeSystem$getInWorldRecipeManager() {
        throw new AssertionError();
    }
}
