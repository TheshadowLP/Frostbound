package net.shadowbeast.projectshadow.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.blocks.ModBlocks;
import net.shadowbeast.projectshadow.entity.custom.FusionFurnaceBlockEntity;
import net.shadowbeast.projectshadow.entity.custom.WinterFurnaceEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ProjectShadow.MOD_ID);

    public static final RegistryObject<BlockEntityType<FusionFurnaceBlockEntity>> FUSION_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("fusion_furnace_block_entity", () ->
                    BlockEntityType.Builder.of(FusionFurnaceBlockEntity::new,
                            ModBlocks.FUSION_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<WinterFurnaceEntity>> WINTER_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("winter_furnace_entity", () ->
                    BlockEntityType.Builder.of(WinterFurnaceEntity::new,
                            ModBlocks.WINTER_FURNACE.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
