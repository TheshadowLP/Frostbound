package net.shadowbeast.projectshadow.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.shadowbeast.projectshadow.ProjectShadow;
import net.shadowbeast.projectshadow.entity.custom.MudBallProjectileEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ProjectShadow.MOD_ID);
    public static final RegistryObject<EntityType<MudBallProjectileEntity>> MUDBALL_PROJECTILE =
            ENTITY_TYPES.register("mudball_projectile",
                    () -> EntityType.Builder.<MudBallProjectileEntity>of(MudBallProjectileEntity::new, MobCategory.MISC)
                            .sized(0.25f, 0.25f)
                            .clientTrackingRange(4)
                            .updateInterval(10)
                            .setCustomClientFactory((spawnEntity, level) -> new MudBallProjectileEntity(level))
                            .build("mudball_projectile"));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
