package net.shadowbeast.arcanemysteries.levitation_staff;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerLevitationTagProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerLevitationTag> PLAYER_THIRST = CapabilityManager.get(new CapabilityToken<>() {
    });

    private PlayerLevitationTag levitationTag = null;
    private final LazyOptional<PlayerLevitationTag> optional = LazyOptional.of(this::createPlayerLevitationTag);

    private PlayerLevitationTag createPlayerLevitationTag() {
        if(this.levitationTag == null) {
            this.levitationTag = new PlayerLevitationTag();
        }

        return this.levitationTag;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_THIRST) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerLevitationTag().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerLevitationTag().loadNBTData(nbt);
    }
}