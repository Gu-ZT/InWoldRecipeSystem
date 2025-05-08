package dev.dubhe.recipe.recipe.predicate;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.recipe.InWorldRecipeContext;
import dev.dubhe.recipe.recipe.util.ItemCache;
import lombok.Getter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

@Getter
public abstract class HasItemBase {
    private final Vec3 offset;
    private final Vec3 range;

    public HasItemBase(Vec3 offset, Vec3 range) {
        this.offset = offset;
        this.range = range;
    }

    public ItemCache.ItemCacheElement getItem(@NotNull InWorldRecipeContext context, Predicate<ItemStack> predicate) {
        ItemCache cache = this.getOrCreateItemCache(context);
        return cache.get(predicate);
    }

    public ItemCache getOrCreateItemCache(@NotNull InWorldRecipeContext context) {
        Level level = context.getLevel();
        Vec3 trans = this.getRange().scale(0.5d);
        Vec3 end = context.getPos().add(this.getOffset()).add(trans);
        Vec3 start = context.getPos().add(this.getOffset()).subtract(trans);
        ItemCache itemCache = context.get(InWorldRecipeSystem.of("item_cache"));
        if (itemCache != null && itemCache.isInRange(start, end)) return itemCache;
        if (itemCache == null) {
            itemCache = new ItemCache(level, start, end);
            context.put(InWorldRecipeSystem.of("item_cache"), itemCache);
        }
        List<ItemEntity> entities = level.getEntities(EntityType.ITEM, new AABB(start, end), entity -> true);
        itemCache.setRange(start, end);
        for (ItemEntity entity : entities) {
            ItemCache.ItemCacheElement element = itemCache.get(entity);
            element.add(entity);
        }
        return itemCache;
    }
}
