package net.shadowbeast.arcanemysteries.items.staffs;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.shadowbeast.arcanemysteries.util.LocalizeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ItemStaff extends Item {

    public final String tooltip;

    public ItemStaff(int durability, String tooltip, Rarity rarity) {
        super(new Properties().durability(durability).rarity(rarity));
        this.tooltip = tooltip;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {return false;}
    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {return false;}
    @Override
    public boolean isValidRepairItem(@NotNull ItemStack pStack, @NotNull ItemStack pRepairCandidate) {return false;}
    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {return false;}
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTooltip, @NotNull TooltipFlag flagIn) {
        itemTooltip.add(Component.empty());
        itemTooltip.add(LocalizeUtils.i18n("tooltip." + tooltip));
        int uses = stack.getMaxDamage() - stack.getDamageValue();

        Style durabilityStyle = Style.EMPTY.withColor(stack.getBarColor());
        MutableComponent usesComponent = LocalizeUtils.usesRemaining(uses).copy();

        if (!Minecraft.getInstance().options.advancedItemTooltips || uses >= stack.getMaxDamage()) {
            itemTooltip.add(usesComponent.withStyle(durabilityStyle));
        }
    }
}
