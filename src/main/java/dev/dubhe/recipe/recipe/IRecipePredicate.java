package dev.dubhe.recipe.recipe;

import dev.dubhe.recipe.init.ModRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IRecipePredicate<P extends IRecipePredicate<P>> extends Predicate<InWorldRecipeContext>, Consumer<InWorldRecipeContext>, IPrioritized {
    @Override
    default void accept(InWorldRecipeContext inWorldRecipeContext) {
    }

    default void snapshot(InWorldRecipeContext inWorldRecipeContext) {
    }

    default void rollback(InWorldRecipeContext inWorldRecipeContext) {
    }

    Type<P> getType();

    interface Type<P extends IRecipePredicate<P>> extends ISerializer<P> {
        default ResourceLocation getId() {
            return ModRegistries.PREDICATE_TYPE_REGISTRY.getKey(this);
        }

        default boolean conflict() {
            return false;
        }
    }
}
