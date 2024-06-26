package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.shadowbeast.arcanemysteries.registries.CriteriaTriggerRegistry;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import net.shadowbeast.arcanemysteries.registries.SoundRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cow.class)
public class CowMixin{
    @Unique
    Cow cow = ((Cow) (Object) this);

    @Inject(method = "mobInteract", at=@At("HEAD"), cancellable = true)
    private void mobInteract(Player pPlayer, InteractionHand pHand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.GLASS_BOTTLE) && !cow.isBaby()) {
            if (pPlayer instanceof ServerPlayer serverPlayer)
                CriteriaTriggerRegistry.MILK_COW_WITH_BOTTLE.trigger(serverPlayer);
            pPlayer.playSound(SoundRegistry.MILKING_SOUND_BOTTLE.get(), 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, pPlayer, ItemRegistry.MILK_BOTTLE.get().getDefaultInstance());
            pPlayer.setItemInHand(pHand, itemstack1);
            cir.setReturnValue(InteractionResult.sidedSuccess(cow.level().isClientSide));
        }
    }
}
