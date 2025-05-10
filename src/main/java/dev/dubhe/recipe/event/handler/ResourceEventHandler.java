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
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = InWorldRecipeSystem.MOD_ID)
public class ResourceEventHandler {
    @SubscribeEvent
    public static void onRecipeLoad(@NotNull RecipesUpdatedEvent event) {
        InWorldRecipeManager manager = new InWorldRecipeManager();
        RecipeManager recipeManager = event.getRecipeManager();
        for (RecipeHolder<?> holder : recipeManager.getRecipes()) {
            Recipe<?> value = holder.value();
            if (!(value instanceof InWorldRecipe recipe)) continue;
            manager.register(recipe);
        }
        recipeManager.inWoldRecipeSystem$setInWorldRecipeManager(manager);
    }
}
