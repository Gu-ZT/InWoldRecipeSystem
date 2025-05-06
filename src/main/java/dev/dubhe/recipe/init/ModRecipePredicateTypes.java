package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.IRecipePredicate;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipePredicateTypes {
    public static final DeferredRegister<IRecipePredicate.Type<?>> PREDICATE_TYPE = DeferredRegister
        .create(ModRegistries.PREDICATE_TYPE_REGISTRY, InWorldRecipeSystem.MOD_ID);
}
