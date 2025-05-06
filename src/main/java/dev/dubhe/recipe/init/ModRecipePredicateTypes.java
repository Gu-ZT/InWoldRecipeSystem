package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.IRecipePredicate;
import dev.dubhe.recipe.recipe.predicate.HasItem;
import dev.dubhe.recipe.recipe.predicate.HasItemIngredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipePredicateTypes {
    public static final DeferredRegister<IRecipePredicate.Type<?>> PREDICATE_TYPE = DeferredRegister
        .create(ModRegistries.PREDICATE_TYPE_REGISTRY, InWorldRecipeSystem.MOD_ID);

    public static final DeferredHolder<IRecipePredicate.Type<?>, HasItem.Type> HAS_ITEM = PREDICATE_TYPE.register("has_item", HasItem.Type::new);

    public static final DeferredHolder<IRecipePredicate.Type<?>, HasItemIngredient.Type> HAS_ITEM_INGREDIENT = PREDICATE_TYPE.register("has_item_ingredient", HasItemIngredient.Type::new);
}
