package net.shadowbeast.arcanemysteries.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.entity.custom.IceBeamProjectileEntity;
import net.shadowbeast.arcanemysteries.entity.custom.ModBoatEntity;
import net.shadowbeast.arcanemysteries.entity.custom.ModChestBoatEntity;
import net.shadowbeast.arcanemysteries.entity.custom.MudBallProjectileEntity;
import net.shadowbeast.arcanemysteries.entity.mob.custom.DungeonIceEntity;
import net.shadowbeast.arcanemysteries.entity.mob.custom.YakEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArcaneMysteries.MOD_ID);
    public static final RegistryObject<EntityType<MudBallProjectileEntity>> MUDBALL_PROJECTILE =
            ENTITY_TYPES.register("mudball_projectile",
                    () -> EntityType.Builder.<MudBallProjectileEntity>of(MudBallProjectileEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .setCustomClientFactory((spawnEntity, level) -> new MudBallProjectileEntity(level))
                            .build("mudball_projectile"));
    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));

    public static final RegistryObject<EntityType<DungeonIceEntity>> DUNGEON_ICE =
            ENTITY_TYPES.register("dungeonice", () -> EntityType.Builder.of(DungeonIceEntity::new, MobCategory.MISC)
                    .sized(2.3f, 4.3f).build("dungeonice"));

    public static final RegistryObject<EntityType<IceBeamProjectileEntity>> ICE_BEAM_PROJECTILE =
            ENTITY_TYPES.register("ice_beam_projectile", () -> EntityType.Builder.<IceBeamProjectileEntity>of(IceBeamProjectileEntity::new, MobCategory.MISC)
                    .sized(2.3f, 4.3f).build("ice_beam_projectile"));

    public static final RegistryObject<EntityType<YakEntity>> YAK =
            ENTITY_TYPES.register("yak", () -> EntityType.Builder.of(YakEntity::new, MobCategory.CREATURE)
                    .sized(1.5F, 2.1F).clientTrackingRange(10).build("yak"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
