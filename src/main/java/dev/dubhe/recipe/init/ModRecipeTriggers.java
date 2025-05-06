package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.IRecipeTrigger;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTriggers {
    public static final DeferredRegister<IRecipeTrigger> TRIGGER = DeferredRegister
        .create(ModRegistries.TRIGGER_REGISTRY, InWorldRecipeSystem.MOD_ID);
}
