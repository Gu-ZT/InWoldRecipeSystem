package dev.dubhe.recipe.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.dubhe.recipe.event.ModEventHook;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(FallingBlockEntity.class)
abstract class FallingBlockEntityMixin extends Entity {
    @Shadow
    private BlockState blockState;

    @Shadow
    private boolean cancelDrop;

    @Unique
    private float anvilcraft$fallDistance;

    public FallingBlockEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
        method = "tick",
        at =
        @At(
            value = "INVOKE",
            ordinal = 0,
            target =
                "Lnet/minecraft/world/entity/item/FallingBlockEntity;level()Lnet/minecraft/world/level/Level;")
    )
    @SuppressWarnings("resource")
    private void anvilPerFallOnGround(CallbackInfo ci) {
        if (this.level().isClientSide()) return;
        if (this.onGround()) return;
        this.anvilcraft$fallDistance = this.fallDistance;
    }

    @Inject(
        method = "tick",
        at =
        @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/Fallable;onLand(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/item/FallingBlockEntity;)V"
        )
    )
    @SuppressWarnings("resource")
    private void anvilFallOnGround(CallbackInfo ci, @Local(ordinal = 0) BlockPos blockPos) {
        FallingBlockEntity entity = (FallingBlockEntity) (Object) this;
        if (!ModEventHook.anvilOnLand(this.level(), blockPos, entity, this.anvilcraft$fallDistance)) {
            return;
        }
        BlockState state = AnvilBlock.damage(this.blockState);
        if (state != null) {
            this.level().setBlockAndUpdate(blockPos, state);
        } else {
            this.level().setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
            if (!this.isSilent()) this.level().levelEvent(1029, this.getOnPos(), 0);
            this.cancelDrop = true;
        }
    }

    @Inject(
        method = "causeFallDamage",
        at =
        @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;getEntities(Lnet/minecraft/world/entity/Entity;"
                + "Lnet/minecraft/world/phys/AABB;Ljava/util/function/Predicate;)Ljava/util/List;")
    )
    private void anvilHurtEntity(
        float pFallDistance,
        float pMultiplier,
        DamageSource pSource,
        CallbackInfoReturnable<Boolean> cir,
        @Local Predicate<Entity> predicate,
        @Local(ordinal = 2) float f
    ) {
        FallingBlockEntity anvil = (FallingBlockEntity) (Object) this;
        Level level = this.level();
        ModEventHook.anvilHurtEntity(anvil, level, f, predicate);
    }
}
