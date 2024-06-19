package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.commands.CommandSource;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Nameable;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EEntityMixin extends net.minecraftforge.common.capabilities.CapabilityProvider<Entity> implements Nameable, EntityAccess, CommandSource, IForgeEntity {

    public EEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(Entity.class);
    }

    @Shadow
    @Final
    protected SynchedEntityData entityData;
    @Shadow public boolean isInPowderSnow;
    @Shadow public int tickCount;
    @Shadow public Level level() {return null;}
    @Shadow public EntityType<?> getType() {return null;}
    @Shadow public boolean isSpectator() {return false;}
    @Shadow public boolean canFreeze() {return false;} // its either true or false, and is never happy
    @Shadow public boolean isFullyFrozen() {return false;}
    @Shadow public DamageSources damageSources() {return null;}

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init_inject(CallbackInfo info) {

    }

}