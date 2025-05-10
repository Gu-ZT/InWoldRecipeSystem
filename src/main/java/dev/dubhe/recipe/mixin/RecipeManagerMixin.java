package dev.dubhe.recipe.mixin;

import dev.dubhe.recipe.recipe.InWorldRecipeManager;
import dev.dubhe.recipe.util.injector.IRecipeManager;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin implements IRecipeManager {
    @Unique
    private InWorldRecipeManager inWoldRecipeSystem$inWorldRecipeManager = null;

    @Override
    public void inWoldRecipeSystem$setInWorldRecipeManager(InWorldRecipeManager manager) {
        this.inWoldRecipeSystem$inWorldRecipeManager = manager;
    }

    @Override
    public InWorldRecipeManager inWoldRecipeSystem$getInWorldRecipeManager() {
        return this.inWoldRecipeSystem$inWorldRecipeManager;
    }
}
