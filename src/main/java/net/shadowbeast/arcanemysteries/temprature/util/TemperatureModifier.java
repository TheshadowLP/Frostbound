package net.shadowbeast.arcanemysteries.temprature.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.shadowbeast.arcanemysteries.util.MathHelper;

public class TemperatureModifier {
    private ResourceLocation id;
    private double mod;
    private ContributingFactor factor;

    public TemperatureModifier() {
    }

    public TemperatureModifier(ResourceLocation id, double mod) {
        this.id = id;
        this.mod = mod;
        this.factor = ContributingFactor.INTERNAL;
    }

    public TemperatureModifier(ResourceLocation id, double mod, ContributingFactor factor) {
        this.id = id;
        this.mod = mod;
        this.factor = factor;
    }

    public double getMod() {
        return mod;
    }

    public ContributingFactor getFactor() {
        return factor;
    }

    public ResourceLocation getId() {
        return id;
    }

    public CompoundTag write(CompoundTag nbt) {
        nbt.putString("id", this.id.toString());
        nbt.putDouble("mod", this.mod);
        nbt.putInt("factor", this.factor.ordinal());
        return nbt;
    }

    public void read(CompoundTag nbt) {
        this.id = new ResourceLocation(nbt.getString("id"));
        this.mod = nbt.getDouble("mod");
        this.factor = nbt.getInt("factor") >= ContributingFactor.values().length ? ContributingFactor.INTERNAL : ContributingFactor.values()[nbt.getInt("factor")];
    }

    public TemperatureModifier setMod(double mod) {
        this.mod = MathHelper.roundDecimal(3, mod);
        return this;
    }

    public TemperatureModifier setFactor(ContributingFactor factor) {
        this.factor = factor;
        return this;
    }

    @Override
    public String toString() {
        return "{id: "+id+", modifier: "+mod+", contributing factor: "+factor+"}";
    }

    public enum ContributingFactor {

        ENVIRONMENTAL,

        INTERNAL
    }
}