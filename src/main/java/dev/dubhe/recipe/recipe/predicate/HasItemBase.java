package dev.dubhe.recipe.recipe.predicate;

import lombok.Getter;
import net.minecraft.world.phys.Vec3;

@Getter
public abstract class HasItemBase {
    private final Vec3 offset;
    private final Vec3 range;

    public HasItemBase(Vec3 offset, Vec3 range) {
        this.offset = offset;
        this.range = range;
    }
}
