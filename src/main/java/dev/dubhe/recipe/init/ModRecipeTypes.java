package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.InWorldRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

public class ModRecipeTypes {
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
        DeferredRegister.create(Registries.RECIPE_TYPE, InWorldRecipeSystem.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
        DeferredRegister.create(Registries.RECIPE_SERIALIZER, InWorldRecipeSystem.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<InWorldRecipe>> IN_WORLD_RECIPE =
        registerType("in_world_recipe");
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InWorldRecipe>> IN_WORLD_RECIPE_SERIALIZER =
        RECIPE_SERIALIZERS.register("in_world_recipe", InWorldRecipe.Serializer::new);

    private static <T extends Recipe<?>> @NotNull DeferredHolder<RecipeType<?>, RecipeType<T>> registerType(
        @SuppressWarnings("SameParameterValue") String name
    ) {
        return RECIPE_TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return InWorldRecipeSystem.of(name).toString();
            }
        });
    }

    public static void register(IEventBus bus) {
        RECIPE_TYPES.register(bus);
        RECIPE_SERIALIZERS.register(bus);
    }
}
