package dev.dubhe.recipe.recipe;

import dev.dubhe.recipe.InWorldRecipeSystemConfig;
import dev.dubhe.recipe.init.ModRecipeTriggers;
import dev.dubhe.recipe.recipe.builder.InWorldRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class InWorldRecipeManager {
    public final Map<IRecipeTrigger, Set<InWorldRecipe>> recipes = Collections.synchronizedMap(new HashMap<>());

    public InWorldRecipeManager() {
        InWorldRecipe recipe = InWorldRecipeBuilder
            .incompatible(ModRecipeTriggers.ON_ANVIL_FALL_ON.get())
            .hasItemIngredient(ItemTags.LOGS)
            .hasItemIngredient(Items.BIRCH_LOG)
            .damageAnvil()
            .build();
        this.register(recipe);
    }

    public void register(@NotNull InWorldRecipe recipe) {
        Set<InWorldRecipe> recipeList = this.recipes.computeIfAbsent(
            recipe.getTrigger(),
            k -> Collections.synchronizedSet(new TreeSet<>())
        );
        recipeList.add(recipe);
    }

    public void trigger(IRecipeTrigger trigger, @NotNull InWorldRecipeContext ctx) {
        if (ctx.getLevel().isClientSide()) return;
        recipes.getOrDefault(trigger, Collections.emptySet()).forEach(recipe -> {
            boolean accept = false;
            for (int i = 0; i < InWorldRecipeSystemConfig.maxProcessingEfficiency; i++) {
                if (!recipe.matches(ctx, ctx.getLevel())) {
                    if (!accept) break;
                    return;
                }
                accept = true;
                recipe.assemble(ctx, ctx.getLevel().registryAccess());
            }
        });
    }
}
