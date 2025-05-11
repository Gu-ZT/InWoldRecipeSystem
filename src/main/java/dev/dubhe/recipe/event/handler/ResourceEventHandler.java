package dev.dubhe.recipe.event.handler;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.InWorldRecipe;
import dev.dubhe.recipe.recipe.InWorldRecipeManager;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesUpdatedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = InWorldRecipeSystem.MOD_ID)
public class ResourceEventHandler {
    @SubscribeEvent
    public static void onRecipeLoad(@NotNull RecipesUpdatedEvent event) {
        ResourceEventHandler.initManager(event.getRecipeManager());
    }

    @SubscribeEvent
    public static void onServerStarted(@NotNull ServerStartedEvent event) {
        ResourceEventHandler.initManager(event.getServer().getRecipeManager());
    }

    @SubscribeEvent
    public static void onDatapackSync(@NotNull OnDatapackSyncEvent event) {
        ResourceEventHandler.initManager(event.getPlayerList().getServer().getRecipeManager());
    }

    public static void initManager(@NotNull RecipeManager manager) {
        InWorldRecipeManager manager1 = new InWorldRecipeManager();
        for (RecipeHolder<?> holder : manager.getRecipes()) {
            Recipe<?> value = holder.value();
            if (!(value instanceof InWorldRecipe recipe)) continue;
            manager1.register(recipe);
        }
        manager.inWoldRecipeSystem$setInWorldRecipeManager(manager1);
    }
}
