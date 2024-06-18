package net.shadowbeast.arcanemysteries.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.fluid.custom.EndFlowingFluid;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import net.shadowbeast.arcanemysteries.registries.ModBlocks;


import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MOD_ID;

public class FluidsMod {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MOD_ID);
    public static final RegistryObject<FlowingFluid> END_LAVA_FLUID = FLUIDS.register("end_lava_fluid", () -> new EndFlowingFluid.Source(FluidsMod.END_LAVA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> END_LAVA_FLOWING = FLUIDS.register("end_lava_flow", () -> new EndFlowingFluid.Flowing(FluidsMod.END_LAVA_PROPERTIES));
    public static final ForgeFlowingFluid.Properties END_LAVA_PROPERTIES = new EndFlowingFluid.Properties(
            FluidTypesMod.END_LAVA_FLUID_TYPE, END_LAVA_FLUID, END_LAVA_FLOWING)
            .slopeFindDistance(4)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.END_LAVA_BLOCK)
            .bucket(ItemRegistry.END_LAVA_BUCKET)
            .tickRate(30)
            .explosionResistance(100.0F);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
