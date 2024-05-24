package net.shadowbeast.arcanemysteries.entities.boats;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
public class EntityChestBoat extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(EntityChestBoat.class, EntityDataSerializers.INT);
    public EntityChestBoat(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public EntityChestBoat(Level pLevel, double pX, double pY, double pZ) {
        this(EntityRegistry.MOD_CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }
    @Override
    public @NotNull Item getDropItem() {
        if (Objects.requireNonNull(getModVariant()) == EntityBoat.Type.FROZEN) {
            return ItemRegistry.frozen_chest_boat.get();
        }
        return super.getDropItem();
    }
    public void setVariant(EntityBoat.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, EntityBoat.Type.FROZEN.ordinal());
    }
    protected void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.addChestVehicleSaveData(pCompound);
    }
    protected void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.readChestVehicleSaveData(pCompound);
    }
    public EntityBoat.Type getModVariant() {return EntityBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}
