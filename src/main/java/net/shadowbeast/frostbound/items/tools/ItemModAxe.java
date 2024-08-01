package net.shadowbeast.frostbound.items.tools;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;
public class ItemModAxe extends AxeItem {
    public ItemModAxe(Tier tier, float attackSpeed) {
        super(tier, 5.0F, attackSpeed, new Properties());
    }
}