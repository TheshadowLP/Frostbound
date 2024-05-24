package net.shadowbeast.arcanemysteries.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
public class ItemModHoe extends HoeItem {
    public ItemModHoe(Tier tier, float attackSpeed) {
        super(tier, -4, attackSpeed, new Properties());
    }
}
