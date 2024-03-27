package net.shadowbeast.projectshadow.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.shadowbeast.projectshadow.entity.ModEntities;
import net.shadowbeast.projectshadow.items.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ModChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModChestBoatEntity.class, EntityDataSerializers.INT);
    public ModChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public ModChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(ModEntities.MOD_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }
    @Override
    public @NotNull Item getDropItem() {
        if (Objects.requireNonNull(getModVariant()) == ModBoatEntity.Type.FROZEN) {
            return ModItems.FROZEN_CHEST_BOAT.get();
        }
        return super.getDropItem();
    }
    public void setVariant(ModBoatEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, ModBoatEntity.Type.FROZEN.ordinal());
    }
    protected void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addChestVehicleSaveData(pCompound);
    }
    protected void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readChestVehicleSaveData(pCompound);
    }
    public ModBoatEntity.Type getModVariant() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
