package net.shadowbeast.projectshadow.items.costum;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class HealStaffItem extends Item {
    public HealStaffItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) { //Detect if the player is using the item
        Player player = pContext.getPlayer(); //get the player
        if(!pContext.getLevel().isClientSide()){ //check if the item is used on the server and not the client


            player.addEffect(new MobEffectInstance(MobEffects.HEAL, 1, 20, false, false)); //Apply the effect to the player
        }
        player.playSound(SoundEvents.ALLAY_THROW, 1f, 1f);
        player.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 1f, 1f);

        return InteractionResult.SUCCESS;
    }




}
