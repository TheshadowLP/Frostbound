package net.shadowbeast.arcanemysteries.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import net.shadowbeast.arcanemysteries.registries.ModBlocks;


import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MODID;

public class FluidsMod {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
    public static final RegistryObject<FlowingFluid> END_LAVA_FLUID = FLUIDS.register("end_lava_fluid", () -> new ForgeFlowingFluid.Source(FluidsMod.END_LAVA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> END_LAVA_FLOWING = FLUIDS.register("end_lava_flow", () -> new ForgeFlowingFluid.Flowing(FluidsMod.END_LAVA_PROPERTIES));
    public static final ForgeFlowingFluid.Properties END_LAVA_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidTypesMod.END_LAVA_FLUID_TYPE, END_LAVA_FLUID, END_LAVA_FLOWING)
            .slopeFindDistance(4)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.END_LAVA_BLOCK)
            .bucket(ItemRegistry.end_lava_bucket)
            .tickRate(30)
            .explosionResistance(100.0F);
}
