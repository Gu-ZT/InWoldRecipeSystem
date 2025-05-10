package dev.dubhe.recipe;

import com.mojang.logging.LogUtils;
import dev.dubhe.recipe.init.ModRecipeOutcomeTypes;
import dev.dubhe.recipe.init.ModRecipePredicateTypes;
import dev.dubhe.recipe.init.ModRecipeTriggers;
import dev.dubhe.recipe.init.ModRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Mod(InWorldRecipeSystem.MOD_ID)
public class InWorldRecipeSystem {
    public static final String MOD_ID = "in_world_recipe";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InWorldRecipeSystem(IEventBus modEventBus, @NotNull ModContainer modContainer) {
        ModRecipeTypes.register(modEventBus);
        ModRecipeTriggers.TRIGGER.register(modEventBus);
        ModRecipePredicateTypes.PREDICATE_TYPE.register(modEventBus);
        ModRecipeOutcomeTypes.OUTCOME_TYPE.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, InWorldRecipeSystemConfig.SPEC);
    }

    public static @NotNull ResourceLocation of(String name) {
        return ResourceLocation.fromNamespaceAndPath(InWorldRecipeSystem.MOD_ID, name);
    }
}
