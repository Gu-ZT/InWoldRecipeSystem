package dev.dubhe.recipe.recipe;

import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InWorldRecipeContext implements RecipeInput {
    private final Map<ResourceLocation, Object> data = Collections.synchronizedMap(new HashMap<>());
    @Getter
    private final List<IRecipePredicate<?>> stack = Collections.synchronizedList(new ArrayList<>());

    public void push(@NotNull IRecipePredicate<?> predicate) {
        this.stack.add(predicate);
        predicate.snapshot(this);
    }

    public void pop(@NotNull IRecipePredicate<?> predicate) {
        predicate.rollback(this);
        this.stack.removeLast();
    }

    public <T> void put(ResourceLocation key, T value) {
        this.data.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(ResourceLocation key) {
        return (T) this.data.get(key);
    }

    @Override
    public @NotNull ItemStack getItem(int i) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
