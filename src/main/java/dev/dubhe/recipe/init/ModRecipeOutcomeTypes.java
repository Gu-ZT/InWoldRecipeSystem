package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.IRecipeOutcome;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeOutcomeTypes {
    public static final DeferredRegister<IRecipeOutcome.Type<?>> OUTCOME_TYPE = DeferredRegister
        .create(ModRegistries.OUTCOME_TYPE_REGISTRY, InWorldRecipeSystem.MOD_ID);
}
