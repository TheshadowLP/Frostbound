package net.shadowbeast.projectshadow.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.items.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, ProjectShadow.MOD_ID);

    public static final RegistryObject<FlowingFluid> END_LAVA_FLUID = FLUIDS.register("end_lava_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.END_LAVA_PROPERTIES));
    public static final RegistryObject<FlowingFluid> END_LAVA_FLOWING = FLUIDS.register("end_lava_flow", () -> new ForgeFlowingFluid.Flowing(ModFluids.END_LAVA_PROPERTIES));


    public static final ForgeFlowingFluid.Properties END_LAVA_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.END_LAVA_FLUID_TYPE, END_LAVA_FLUID, END_LAVA_FLOWING)
            .slopeFindDistance(4)
            .levelDecreasePerBlock(1)
            .block(ModBlocks.END_LAVA_BLOCK)
            .bucket(ModItems.END_LAVA_BUCKET)
            .tickRate(30)
            .explosionResistance(100.0F);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
