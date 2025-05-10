package dev.dubhe.recipe.event;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Predicate;

public class ModEventHook {
    public static boolean anvilOnLand(
        @NotNull Level level,
        BlockPos pos,
        FallingBlockEntity entity,
        float fallDistance
    ) {
        if (level.isClientSide()) return false;
        if (!entity.getBlockState().is(BlockTags.ANVIL)) return false;
        AnvilEvent.OnLand event = new AnvilEvent.OnLand(level, pos, entity, fallDistance);
        NeoForge.EVENT_BUS.post(event);
        return event.isDamage();
    }

    public static void anvilHurtEntity(
        FallingBlockEntity anvil,
        @NotNull Level level,
        float damage,
        Predicate<Entity> predicate
    ) {
        List<Entity> entities = level.getEntities(anvil, anvil.getBoundingBox(), predicate);
        for (Entity entity : entities) {
            NeoForge.EVENT_BUS.post(new AnvilEvent.HurtEntity(anvil, anvil.getOnPos(), level, entity, damage));
        }
    }
}
