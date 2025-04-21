package dev.dubhe.recipe.recipe;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public interface ISerializer<T> {
    MapCodec<T> codec();

    StreamCodec<RegistryFriendlyByteBuf, T> streamCodec();
}
