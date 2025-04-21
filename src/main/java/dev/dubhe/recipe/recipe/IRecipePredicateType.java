package dev.dubhe.recipe.recipe;

public interface IRecipePredicateType<P extends IRecipePredicate<P>> {
    ISerializer<P> getSerializer();

    default boolean conflict() {
        return false;
    }
}
