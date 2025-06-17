package dev.dubhe.recipe.init;

import dev.dubhe.recipe.InWorldRecipeSystem;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModTags {
    public static class BlockEntity {
        public static final TagKey<BlockEntityType<?>> ITEM_CACHE = TagKey.create(Registries.BLOCK_ENTITY_TYPE, InWorldRecipeSystem.of("item_cache"));
    }
    public static class Entity {
        public static final TagKey<EntityType<?>> ITEM_CACHE = TagKey.create(Registries.ENTITY_TYPE, InWorldRecipeSystem.of("item_cache"));
    }
}
