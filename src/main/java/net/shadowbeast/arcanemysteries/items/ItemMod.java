package net.shadowbeast.arcanemysteries.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;
public class ItemMod extends Item {
    private static final Properties props = new Properties();
    private boolean hasGlint;
    public ItemMod() {
        super(props);
    }
    public ItemMod(Properties properties) {
        super(properties);
    }
    public ItemMod(Rarity rarity) {
        super(new Properties().rarity(rarity));} //TODO delet after finishing if no usage
    public ItemMod(boolean hasGlint) {
        super(props);
        this.hasGlint = hasGlint;
    }
    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return hasGlint || super.isEnchantable(stack);
    }
}
