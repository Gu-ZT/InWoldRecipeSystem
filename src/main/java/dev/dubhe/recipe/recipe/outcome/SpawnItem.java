package dev.dubhe.recipe.recipe.outcome;

import dev.dubhe.recipe.recipe.IRecipeOutcome;
import dev.dubhe.recipe.recipe.InWorldRecipeContext;

public class SpawnItem implements IRecipeOutcome<SpawnItem> {
    @Override
    public Type<SpawnItem> getType() {
        return null;
    }

    @Override
    public void accept(InWorldRecipeContext context) {

    }
}
