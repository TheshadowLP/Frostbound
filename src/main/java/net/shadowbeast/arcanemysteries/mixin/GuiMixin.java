package net.shadowbeast.arcanemysteries.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.shadowbeast.arcanemysteries.items.staffs.ItemStaff;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.UnaryOperator;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow protected ItemStack lastToolHighlight;
    Gui gui = ((Gui) (Object) this);

    @Redirect(method = "renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Rarity;getStyleModifier()Ljava/util/function/UnaryOperator;"), remap = false)
    private UnaryOperator<Style> cancelFallDamage(Rarity rarity) {
        if(lastToolHighlight.getItem() instanceof ItemStaff staff) {
            return style -> style.withColor(staff.color());
        }
        return rarity.getStyleModifier();
    }
}
