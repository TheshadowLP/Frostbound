package net.shadowbeast.arcanemysteries.entities.boats;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import net.shadowbeast.arcanemysteries.registries.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.IntFunction;
public class EntityBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(EntityBoat.class, EntityDataSerializers.INT);
    public EntityBoat(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public EntityBoat(Level pLevel, double pX, double pY, double pZ) {
        this(EntityRegistry.MOD_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }
    @Override
    public @NotNull Item getDropItem() {
        if (Objects.requireNonNull(getModVariant()) == Type.FROZEN) {
            return ItemRegistry.FROZEN_BOAT.get();
        }
        return super.getDropItem();
    }
    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }
    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, Type.FROZEN.ordinal());
    }
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }
    public enum Type implements StringRepresentable {
        FROZEN(ModBlocks.FROZEN_PLANKS.get(), "frozen");
        private final String name;
        private final Block planks;
        @SuppressWarnings("deprecation")
        public static final EnumCodec<Type> CODEC = StringRepresentable.fromEnum(Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }
        public @NotNull String getSerializedName() { return this.name; }
        public String getName() { return this.name; }
        public Block getPlanks() { return this.planks; }
        public String toString() { return this.name; }
        public static Type byId(int pId) { return BY_ID.apply(pId); }
        public static Type byName(String pName) { return CODEC.byName(pName, FROZEN); }
    }
}
