package net.shadowbeast.projectshadow.fluid;

import org.joml.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;

public class ModFluidTypes {
    public static final ResourceLocation END_LAVA_STILL_RL = new ResourceLocation(ProjectShadow.MOD_ID, "block/end_lava_still");
    public static final ResourceLocation END_LAVA_FLOWING_RL = new ResourceLocation(ProjectShadow.MOD_ID, "block/end_lava_flow");
    public static final ResourceLocation END_LAVA_OVERLAY_RL = new ResourceLocation("blocks/lava_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ProjectShadow.MOD_ID);

    public static final RegistryObject<FluidType> END_LAVA_FLUID_TYPE = register("end_lava_fluid",
            FluidType.Properties.create().lightLevel(2).density(15).viscosity(5).sound(SoundAction.get("drink"),
                    SoundEvents.HONEY_DRINK));



    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(END_LAVA_STILL_RL, END_LAVA_FLOWING_RL, END_LAVA_OVERLAY_RL,
                0xA1E038D0, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
