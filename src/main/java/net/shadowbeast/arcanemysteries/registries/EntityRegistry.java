package net.shadowbeast.arcanemysteries.registries;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.block_entities.entities.AlloyFurnaceBlockEntity;
import net.shadowbeast.arcanemysteries.block_entities.entities.CrusherBlockEntity;
import net.shadowbeast.arcanemysteries.block_entities.entities.WinterFurnaceEntity;
import net.shadowbeast.arcanemysteries.entities.boats.EntityBoat;
import net.shadowbeast.arcanemysteries.entities.boats.EntityChestBoat;
import net.shadowbeast.arcanemysteries.entities.mobs.custom.DungeonIceEntity;
import net.shadowbeast.arcanemysteries.entities.mobs.custom.YakEntity;
import net.shadowbeast.arcanemysteries.entities.projectile.EntityIceBeam;
import net.shadowbeast.arcanemysteries.entities.projectile.EntityMudBall;
import net.shadowbeast.arcanemysteries.entities.signs.EntityHangingSignBlock;
import net.shadowbeast.arcanemysteries.entities.signs.EntitySignBlock;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MOD_ID;
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);
    public static final RegistryObject<EntityType<EntityMudBall>> MUDBALL_PROJECTILE =
            ENTITIES.register("mudball_projectile",
                    () -> EntityType.Builder.<EntityMudBall>of(EntityMudBall::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .setCustomClientFactory((spawnEntity, level) -> new EntityMudBall(level))
                            .build("mudball_projectile"));
    public static final RegistryObject<EntityType<DungeonIceEntity>> DUNGEON_ICE =
            ENTITIES.register("dungeonice", () -> EntityType.Builder.of(DungeonIceEntity::new, MobCategory.MISC)
                    .sized(2.3f, 4.3f).build("dungeonice"));
    public static final RegistryObject<EntityType<EntityIceBeam>> ICE_BEAM_PROJECTILE =
            ENTITIES.register("ice_beam_projectile", () -> EntityType.Builder.<EntityIceBeam>of(EntityIceBeam::new, MobCategory.MISC)
                    .sized(2.3f, 4.3f).build("ice_beam_projectile"));
    public static final RegistryObject<EntityType<YakEntity>> YAK =
            ENTITIES.register("yak", () -> EntityType.Builder.of(YakEntity::new, MobCategory.MISC)
                    .sized(2.3f, 4.3f).build("yak"));
    public static final RegistryObject<EntityType<EntityBoat>> MOD_BOAT =
            ENTITIES.register("mod_boat", () -> EntityType.Builder.<EntityBoat>of(EntityBoat::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final RegistryObject<EntityType<EntityChestBoat>> MOD_CHEST_BOAT =
            ENTITIES.register("mod_chest_boat", () -> EntityType.Builder.<EntityChestBoat>of(EntityChestBoat::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));
    public static final RegistryObject<BlockEntityType<WinterFurnaceEntity>> WINTER_FURNACE_ENTITY =
            BLOCK_ENTITIES.register("winter_furnace_entity", () ->
                    BlockEntityType.Builder.of(WinterFurnaceEntity::new,
                            ModBlocks.WINTER_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<AlloyFurnaceBlockEntity>> ALLOY_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("alloy_furnace_block_entity", () ->
                    BlockEntityType.Builder.of(AlloyFurnaceBlockEntity::new,
                            ModBlocks.ALLOY_FURNACE.get()).build(null));
    public static final RegistryObject<BlockEntityType<CrusherBlockEntity>> CRUSHER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("crusher_block_entity", () ->
                    BlockEntityType.Builder.of(CrusherBlockEntity::new,
                            ModBlocks.CRUSHER.get()).build(null));
    public static final RegistryObject<BlockEntityType<EntitySignBlock>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(EntitySignBlock::new,
                            ModBlocks.FROZEN_SIGN.get(), ModBlocks.FROZEN_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<EntityHangingSignBlock>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(EntityHangingSignBlock::new,
                            ModBlocks.FROZEN_HANGING_SIGN.get(), ModBlocks.FROZEN_WALL_HANGING_SIGN.get()).build(null));
}
