package dev.dubhe.recipe.recipe;

import dev.dubhe.recipe.init.ModRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public interface IRecipeOutcome<O extends IRecipeOutcome<O>> extends Consumer<InWorldRecipeContext>, IPrioritized {
    Type<O> getType();

    interface Type<O extends IRecipeOutcome<O>> extends ISerializer<O> {
        default ResourceLocation getId() {
            return ModRegistries.OUTCOME_TYPE_REGISTRY.getKey(this);
        }
    }
}
