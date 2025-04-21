package dev.dubhe.recipe.recipe;

import net.minecraft.resources.ResourceLocation;

public interface IRecipeTrigger extends IPrioritized {
    ResourceLocation getId();
}
