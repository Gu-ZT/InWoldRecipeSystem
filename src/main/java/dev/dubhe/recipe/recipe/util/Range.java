package dev.dubhe.recipe.recipe.util;

import lombok.Data;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Objects;


@Data
public class Range implements Iterable<BlockPos> {
    @NotNull
    private Vec3 start;
    @NotNull
    private Vec3 end;

    public Range(@NotNull Vec3 start, @NotNull Vec3 end) {
        this.start = new Vec3(
            Math.min(start.x, end.x),
            Math.min(start.y, end.y),
            Math.min(start.z, end.z)
        );
        this.end = new Vec3(
            Math.max(start.x, end.x),
            Math.max(start.y, end.y),
            Math.max(start.z, end.z)
        );
    }

    public static @NotNull Range of(@NotNull Vec3 pos, @NotNull Vec3 range) {
        range = new Vec3(Math.abs(range.x()), Math.abs(range.y()), Math.abs(range.z()));
        return new Range(pos.subtract(range), pos.add(range));
    }

    public boolean contains(@NotNull Range range) {
        return range.start.x() >= this.start.x()
            && range.start.y() >= this.start.y()
            && range.start.z() >= this.start.z()
            && range.end.x() <= this.end.x()
            && range.end.y() <= this.end.y()
            && range.end.z() <= this.end.z();
    }

    public boolean contains(@NotNull Vec3 pos, @NotNull Vec3 range) {
        return this.contains(Range.of(pos, range));
    }

    public boolean cross(@NotNull Range range) {
        return Math.max(range.start.x, this.start.x) < Math.min(range.end.x, this.end.x) &&
            Math.max(range.start.y, this.start.y) < Math.min(range.end.y, this.end.y) &&
            Math.max(range.start.z, this.start.z) < Math.min(range.end.z, this.end.z);
    }

    public boolean cross(@NotNull Vec3 pos, @NotNull Vec3 range) {
        return this.cross(Range.of(pos, range));
    }

    public void grow(@NotNull Range range) {
        this.start = new Vec3(
            Math.min(range.start.x, this.start.x),
            Math.min(range.start.y, this.start.y),
            Math.min(range.start.z, this.start.z)
        );
        this.end = new Vec3(
            Math.max(range.end.x, this.end.x),
            Math.max(range.end.y, this.end.y),
            Math.max(range.end.z, this.end.z)
        );
    }

    public AABB toAABB() {
        return new AABB(
            this.start.x,
            this.start.y,
            this.start.z,
            this.end.x,
            this.end.y,
            this.end.z
        );
    }

    @Override
    public @NotNull Iterator<BlockPos> iterator() {
        return new BlockPosIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start.equals(range.start) && end.equals(range.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    private static class BlockPosIterator implements Iterator<BlockPos> {
        private final Range range;
        private BlockPos lastPos = null;
        private boolean genNext = false;
        private BlockPos nextPos = null;
        private boolean end = false;

        public BlockPosIterator(Range range) {
            this.range = range;
        }

        private void genNext() {
            if (this.end) return;
            if (this.genNext) return;
            this.genNext = true;
            int startX = (int) Math.floor(range.start.x);
            int startY = (int) Math.floor(range.start.y);
            int startZ = (int) Math.floor(range.start.z);
            if (this.lastPos == null) {
                this.nextPos = new BlockPos(startX, startY, startZ);
                return;
            }
            BlockPos nextPos = this.lastPos.offset(1, 0, 0);
            final Vec3 vec3_1 = new Vec3(1.0, 1.0, 1.0);
            if (this.range.cross(nextPos.getCenter(), vec3_1)) {
                this.nextPos = nextPos;
                return;
            }
            nextPos = new BlockPos(startX, this.lastPos.getY(), this.lastPos.getZ() + 1);
            if (this.range.cross(nextPos.getCenter(), vec3_1)) {
                this.nextPos = nextPos;
                return;
            }
            nextPos = new BlockPos(startX, this.lastPos.getY() + 1, startZ);
            if (this.range.cross(nextPos.getCenter(), vec3_1)) {
                this.nextPos = nextPos;
            }
        }

        @Override
        public boolean hasNext() {
            this.genNext();
            if (this.nextPos == null) {
                this.end = true;
                return false;
            }
            return true;
        }

        @Override
        public @Nullable BlockPos next() {
            BlockPos next = null;
            if (this.hasNext()) {
                next = this.nextPos;
            }
            this.nextPos = null;
            this.lastPos = next;
            this.genNext = false;
            return next;
        }
    }
}
