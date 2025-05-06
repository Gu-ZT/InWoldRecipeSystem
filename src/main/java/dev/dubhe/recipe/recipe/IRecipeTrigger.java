package dev.dubhe.recipe.recipe;

import dev.dubhe.recipe.init.ModRegistries;
import net.minecraft.resources.ResourceLocation;

public interface IRecipeTrigger extends IPrioritized {
    default ResourceLocation getId() {
        return ModRegistries.TRIGGER_REGISTRY.getKey(this);
    }
}
