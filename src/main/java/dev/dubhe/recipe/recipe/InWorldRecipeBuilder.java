package dev.dubhe.recipe.recipe;

import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class InWorldRecipeBuilder {
    private final NonNullList<ItemStack> icon = NonNullList.withSize(1, Items.ANVIL.getDefaultInstance());
    private final @NotNull IRecipeTrigger trigger;
    private final List<IRecipePredicate<?>> conflicting = new ArrayList<>();
    private final List<IRecipePredicate<?>> nonConflicting = new ArrayList<>();
    private final List<IRecipeOutcome<?>> outcomes = new ArrayList<>();
    private final boolean compatible;

    private InWorldRecipeBuilder(@NotNull IRecipeTrigger trigger, boolean compatible) {
        this.trigger = trigger;
        this.compatible = compatible;
    }

    public static @NotNull InWorldRecipeBuilder compatible(@NotNull IRecipeTrigger trigger) {
        return new InWorldRecipeBuilder(trigger, true);
    }

    public static @NotNull InWorldRecipeBuilder incompatible(@NotNull IRecipeTrigger trigger) {
        return new InWorldRecipeBuilder(trigger, false);
    }

    public InWorldRecipeBuilder icon(@NotNull ItemStack icon) {
        this.icon.set(0, icon);
        return this;
    }

    public InWorldRecipeBuilder with(@NotNull IRecipePredicate<?> predicate) {
        if (predicate.getType().conflict()) {
            this.conflicting.add(predicate);
        } else {
            this.nonConflicting.add(predicate);
        }
        return this;
    }

    public InWorldRecipeBuilder out(@NotNull IRecipeOutcome<?> outcome) {
        this.outcomes.add(outcome);
        return this;
    }

    public InWorldRecipe build() {
        int priority = trigger.getPriority();
        for (IRecipePredicate<?> predicate : conflicting) {
            priority += predicate.getPriority();
        }
        for (IRecipePredicate<?> predicate : nonConflicting) {
            priority += predicate.getPriority();
        }
        for (IRecipeOutcome<?> outcome : outcomes) {
            priority += outcome.getPriority();
        }
        return new InWorldRecipe(
            icon.getFirst(),
            trigger,
            ImmutableList.copyOf(conflicting),
            ImmutableList.copyOf(nonConflicting),
            ImmutableList.copyOf(outcomes),
            priority,
            compatible
        );
    }
}
