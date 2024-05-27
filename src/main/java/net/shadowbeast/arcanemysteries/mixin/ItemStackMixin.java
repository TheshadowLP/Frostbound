package net.shadowbeast.arcanemysteries.mixin;


import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.shadowbeast.arcanemysteries.items.staffs.ItemStaff;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.UnaryOperator;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    ItemStack itemStack = ((ItemStack) (Object) this);

    @Redirect(method = "getDisplayName", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Rarity;getStyleModifier()Ljava/util/function/UnaryOperator;"), remap = false)
    private UnaryOperator<Style> cancelFallDamage(Rarity rarity) {
        if(itemStack.getItem() instanceof ItemStaff staff) {
            return style -> style.withColor(staff.color());
        }
        return rarity.getStyleModifier();
    }

    @Redirect(method = "getTooltipLines", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Rarity;getStyleModifier()Ljava/util/function/UnaryOperator;"), remap = false)
    private UnaryOperator<Style> cancelFallDamag(Rarity rarity) {
        if(itemStack.getItem() instanceof ItemStaff staff) {
            return style -> style.withColor(staff.color());
        }
        return rarity.getStyleModifier();
    }
}
