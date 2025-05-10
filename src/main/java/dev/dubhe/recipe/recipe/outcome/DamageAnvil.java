package dev.dubhe.recipe.recipe.outcome;

import com.mojang.serialization.MapCodec;
import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.init.ModRecipeOutcomeTypes;
import dev.dubhe.recipe.recipe.IRecipeOutcome;
import dev.dubhe.recipe.recipe.InWorldRecipeContext;
import dev.dubhe.recipe.recipe.InWorldRecipeData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public class DamageAnvil implements IRecipeOutcome<DamageAnvil> {
    public static final InWorldRecipeData<Boolean> DAMAGE_ANVIL = InWorldRecipeData.of(InWorldRecipeSystem.of("damage_anvil"), false);

    @Override
    public IRecipeOutcome.Type<DamageAnvil> getType() {
        return ModRecipeOutcomeTypes.DAMAGE_ANVIL.get();
    }

    @Override
    public void accept(@NotNull InWorldRecipeContext context) {
        context.put(DAMAGE_ANVIL, true);
    }

    public static class Type implements IRecipeOutcome.Type<DamageAnvil> {
        @Override
        public @NotNull MapCodec<DamageAnvil> codec() {
            return MapCodec.unit(new DamageAnvil());
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, DamageAnvil> streamCodec() {
            return StreamCodec.of(Type::encode, Type::decode);
        }

        public static void encode(@NotNull FriendlyByteBuf buf, @NotNull DamageAnvil hasItem) {
        }

        public static @NotNull DamageAnvil decode(@NotNull FriendlyByteBuf buf) {
            return new DamageAnvil();
        }
    }
}
