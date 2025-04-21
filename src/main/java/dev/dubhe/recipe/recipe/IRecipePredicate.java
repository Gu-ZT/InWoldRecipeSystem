package dev.dubhe.recipe.recipe;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IRecipePredicate<P extends IRecipePredicate<P>> extends Predicate<InWorldRecipeContext>, Consumer<InWorldRecipeContext>, IPrioritized {
    @Override
    default void accept(InWorldRecipeContext inWorldRecipeContext) {
    }

    default void snapshot(InWorldRecipeContext inWorldRecipeContext) {
    }

    default void rollback(InWorldRecipeContext inWorldRecipeContext) {
    }

    IRecipePredicateType<P> getType();
}
