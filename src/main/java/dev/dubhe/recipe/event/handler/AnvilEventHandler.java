package dev.dubhe.recipe.event.handler;

import dev.dubhe.recipe.InWorldRecipeSystem;
import dev.dubhe.recipe.event.AnvilEvent;
import dev.dubhe.recipe.init.ModRecipeTriggers;
import dev.dubhe.recipe.recipe.InWorldRecipeContext;
import dev.dubhe.recipe.recipe.InWorldRecipeManager;
import dev.dubhe.recipe.recipe.outcome.DamageAnvil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = InWorldRecipeSystem.MOD_ID)
public class AnvilEventHandler {
    @SubscribeEvent
    public static void anvilOnLand(AnvilEvent.@NotNull OnLand event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        FallingBlockEntity entity = event.getEntity();
        InWorldRecipeManager manager = level.getRecipeManager().inWoldRecipeSystem$getInWorldRecipeManager();
        InWorldRecipeContext context = new InWorldRecipeContext(level, pos.getCenter(), entity);
        manager.trigger(ModRecipeTriggers.ON_ANVIL_FALL_ON.get(), context);
        boolean damageAnvil = context.get(DamageAnvil.DAMAGE_ANVIL);
        if (!event.isDamage()) event.setDamage(damageAnvil);
        context.accept();
    }
}
