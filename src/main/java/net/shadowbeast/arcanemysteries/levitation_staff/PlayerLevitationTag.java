package net.shadowbeast.arcanemysteries.levitation_staff;

import net.minecraft.nbt.CompoundTag;

public class PlayerLevitationTag {
    private boolean levitationTagged;

    public boolean isLevitationTagged() {
        return levitationTagged;
    }

    public void setLevitationTagged(boolean levitationTagged) {
        this.levitationTagged = levitationTagged;
    }

    public void copyFrom(PlayerLevitationTag source) {
        this.levitationTagged = source.levitationTagged;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("levitationTagged", levitationTagged);
    }

    public void loadNBTData(CompoundTag nbt) {
        levitationTagged = nbt.getBoolean("levitationTagged");
    }
}
