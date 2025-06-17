package dev.dubhe.recipe.recipe.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 物品缓存
 * 缓存物品，用于物品匹配/输出
 */
public class NeoItemCache {
    protected final Type type;
    protected final Level level;
    protected final BlockPos pos;
    protected final Set<Class<? extends IItemHandler>> classes = new HashSet<>();

    @SafeVarargs
    protected NeoItemCache(Type type, Level level, BlockPos pos, Class<? extends IItemHandler>... args) {
        this.type = type;
        this.level = level;
        this.pos = pos;
        classes.addAll(Arrays.asList(args));
    }

    public static @NotNull NeoItemCache ofEntity(Level level, BlockPos pos) {
        return new NeoItemCache(Type.ITEM_ENTITY, level, pos);
    }

    @SafeVarargs
    public static @NotNull NeoItemCache ofHandler(Level level, BlockPos pos, Class<? extends IItemHandler>... classes) {
        return new NeoItemCache(Type.ITEM_HANDLER, level, pos, classes);
    }

    @SafeVarargs
    public static @NotNull NeoItemCache ofBoth(Level level, BlockPos pos, Class<? extends IItemHandler>... classes) {
        return new NeoItemCache(Type.BOTH, level, pos, classes);
    }

    public enum Type {
        ITEM_ENTITY,
        ITEM_HANDLER,
        BOTH
    }

    public static class ItemCacheElement {
        private final NeoItemCache cache;
        private final ItemStack stack;
        private final ItemStack simulate;

        public ItemCacheElement(NeoItemCache cache, ItemStack stack, ItemStack simulate) {
            this.cache = cache;
            this.stack = stack;
            this.simulate = simulate;
        }
    }

    public static class ItemEntityCacheElement extends ItemCacheElement {
        private final ItemEntity entity;

        public ItemEntityCacheElement(NeoItemCache cache, @NotNull ItemEntity entity) {
            super(cache, entity.getItem(), entity.getItem().copy());
            this.entity = entity;
        }
    }

    public static class ItemHandlerCacheElement extends ItemCacheElement {
        private final IItemHandler handler;
        private final int index;

        public ItemHandlerCacheElement(NeoItemCache cache, @NotNull IItemHandler handler, int index) {
            super(cache, handler.getStackInSlot(index), handler.getStackInSlot(index).copy());
            this.handler = handler;
            this.index = index;
        }
    }
}
