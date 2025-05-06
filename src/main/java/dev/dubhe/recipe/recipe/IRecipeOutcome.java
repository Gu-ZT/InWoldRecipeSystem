package dev.dubhe.recipe.recipe;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public interface IRecipeOutcome<O extends IRecipeOutcome<O>> extends Consumer<InWorldRecipe>, IPrioritized {
    Type<O> getType();

    interface Type<O extends IRecipeOutcome<O>> {
        ResourceLocation getId();

        ISerializer<O> getSerializer();
    }
}
