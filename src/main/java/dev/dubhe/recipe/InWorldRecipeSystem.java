package dev.dubhe.recipe;

import com.mojang.logging.LogUtils;
import dev.dubhe.recipe.init.ModRecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(InWorldRecipeSystem.MOD_ID)
public class InWorldRecipeSystem {
    public static final String MOD_ID = "in_world_recipe";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InWorldRecipeSystem(IEventBus modEventBus, ModContainer modContainer) {
        ModRecipeTypes.register(modEventBus);
    }

    public static @NotNull ResourceLocation of(String name) {
        return ResourceLocation.fromNamespaceAndPath(InWorldRecipeSystem.MOD_ID, name);
    }
}
