package dev.dubhe.recipe.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class InWorldRecipeSerializer implements RecipeSerializer<InWorldRecipe> {
    @Override
    public MapCodec<InWorldRecipe> codec() {
        return null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, InWorldRecipe> streamCodec() {
        return null;
    }
}
