package dev.dubhe.recipe.recipe;

import dev.dubhe.recipe.init.ModRecipeTypes;
import lombok.Getter;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

@Getter
public class InWorldRecipe implements Recipe<InWorldRecipeContext>, IPrioritized {
    @Unmodifiable
    private final ItemStack icon;
    private final IRecipeTrigger trigger;
    @Unmodifiable
    private final List<IRecipePredicate<?>> conflicting;
    @Unmodifiable
    private final List<IRecipePredicate<?>> nonConflicting;
    @Unmodifiable
    private final List<IRecipeOutcome<?>> outcomes;
    private final int priority;
    private final boolean compatible;

    public InWorldRecipe(
        @NotNull ItemStack icon,
        IRecipeTrigger trigger,
        @Unmodifiable List<IRecipePredicate<?>> conflicting,
        @Unmodifiable List<IRecipePredicate<?>> nonConflicting,
        @Unmodifiable List<IRecipeOutcome<?>> outcomes,
        int priority,
        boolean compatible
    ) {
        this.icon = icon;
        this.trigger = trigger;
        this.conflicting = conflicting;
        this.nonConflicting = nonConflicting;
        this.outcomes = outcomes;
        this.priority = priority;
        this.compatible = compatible;
    }

    @Override
    public boolean matches(@NotNull InWorldRecipeContext inWorldRecipeContext, @NotNull Level level) {
        boolean nonConflicting = ShapelessMatcher.incompatible(this.nonConflicting, inWorldRecipeContext);
        if(!nonConflicting) return false;
        if (this.compatible) {
            return ShapelessMatcher.compatible(this.conflicting, inWorldRecipeContext);
        }
        return ShapelessMatcher.incompatible(this.conflicting, inWorldRecipeContext);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull InWorldRecipeContext inWorldRecipeContext, @NotNull HolderLookup.Provider provider) {
        return this.icon.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull HolderLookup.Provider provider) {
        return this.icon.copy();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.IN_WORLD_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ModRecipeTypes.IN_WORLD_RECIPE.get();
    }
}
