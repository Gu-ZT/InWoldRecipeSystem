package dev.dubhe.recipe.recipe;

import net.minecraft.resources.ResourceLocation;

public interface IRecipeOutcomeType<O extends IRecipeOutcome<O>> {
    ResourceLocation getId();

    ISerializer<O> getSerializer();
}
