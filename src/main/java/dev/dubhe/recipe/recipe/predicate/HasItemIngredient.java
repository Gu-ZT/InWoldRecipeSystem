package dev.dubhe.recipe.recipe.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.dubhe.recipe.recipe.IRecipePredicate;
import dev.dubhe.recipe.recipe.InWorldRecipeContext;
import dev.dubhe.recipe.recipe.util.ItemIngredientPredicate;
import lombok.Getter;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

@Getter
public class HasItemIngredient extends HasItemBase implements IRecipePredicate<HasItemIngredient> {
    private final ItemIngredientPredicate item;

    public HasItemIngredient(Vec3 offset, Vec3 range, ItemIngredientPredicate item) {
        super(offset, range);
        this.item = item;
    }

    @Override
    public IRecipePredicate.Type<HasItemIngredient> getType() {
        return null;
    }

    @Override
    public boolean test(InWorldRecipeContext context) {
        return false;
    }

    public static class Type implements IRecipePredicate.Type<HasItemIngredient> {
        private static final MapCodec<HasItemIngredient> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                Vec3.CODEC.fieldOf("offset").forGetter(HasItemIngredient::getOffset),
                Vec3.CODEC.fieldOf("range").forGetter(HasItemIngredient::getRange),
                ItemIngredientPredicate.CODEC.fieldOf("item").forGetter(HasItemIngredient::getItem)
            ).apply(instance, HasItemIngredient::new)
        );

        @Override
        public @NotNull MapCodec<HasItemIngredient> codec() {
            return Type.CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, HasItemIngredient> streamCodec() {
            return StreamCodec.of(Type::encode, Type::decode);
        }

        public static void encode(@NotNull FriendlyByteBuf buf, @NotNull HasItemIngredient hasItem) {
            buf.writeVec3(hasItem.getOffset());
            buf.writeVec3(hasItem.getRange());
            Tag tag = ItemIngredientPredicate.CODEC.encodeStart(NbtOps.INSTANCE, hasItem.item).getOrThrow();
            buf.writeNbt(tag);
        }

        public static @NotNull HasItemIngredient decode(@NotNull FriendlyByteBuf buf) {
            Vec3 offset = buf.readVec3();
            Vec3 range = buf.readVec3();
            ItemIngredientPredicate item = ItemIngredientPredicate.CODEC.decode(NbtOps.INSTANCE, buf.readNbt()).getOrThrow().getFirst();
            return new HasItemIngredient(offset, range, item);
        }
    }
}
