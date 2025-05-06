package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.IRecipeTrigger;
import dev.dubhe.recipe.recipe.trigger.OnAnvilFallOn;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTriggers {
    public static final DeferredRegister<IRecipeTrigger> TRIGGER = DeferredRegister
        .create(ModRegistries.TRIGGER_REGISTRY, InWorldRecipeSystem.MOD_ID);

    public static final DeferredHolder<IRecipeTrigger, OnAnvilFallOn> ON_ANVIL_FALL_ON = TRIGGER.register("on_anvil_fall_on", OnAnvilFallOn::new);
}
