package dev.dubhe.recipe.recipe;

import net.minecraft.resources.ResourceLocation;

public interface IRecipePredicateType<P extends IRecipePredicate<P>> {
    ResourceLocation getId();

    ISerializer<P> getSerializer();

    default boolean conflict() {
        return false;
    }
}
