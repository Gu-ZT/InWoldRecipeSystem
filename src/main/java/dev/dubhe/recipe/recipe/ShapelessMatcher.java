package dev.dubhe.recipe.recipe;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShapelessMatcher {
    public static <T extends InWorldRecipeContext> boolean incompatible(@NotNull List<IRecipePredicate<?>> predicates, @NotNull T ctx) {
        for (IRecipePredicate<?> predicate : predicates) {
            if (!predicate.test(ctx)) continue;
            List<IRecipePredicate<?>> next = new ArrayList<>();
            for (IRecipePredicate<?> predicate1 : predicates) {
                if (predicate1 == predicate) continue;
                next.add(predicate1);
            }
            ctx.push(predicate);
            if (next.isEmpty()) return true;
            boolean flag = incompatible(next, ctx);
            if (!flag) ctx.pop(predicate);
            return flag;
        }
        return false;
    }

    public static <T extends InWorldRecipeContext> boolean compatible(@NotNull List<IRecipePredicate<?>> predicates, @NotNull T ctx) {
        for (IRecipePredicate<?> predicate : predicates) {
            if (!predicate.test(ctx)) return false;
            ctx.push(predicate);
        }
        return true;
    }
}
