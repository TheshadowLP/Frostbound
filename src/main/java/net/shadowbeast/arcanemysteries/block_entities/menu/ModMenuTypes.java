package net.shadowbeast.arcanemysteries.block_entities.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.shadowbeast.arcanemysteries.ArcaneMysteries.MOD_ID;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID);
    public static final RegistryObject<MenuType<AlloyFurnaceMenu>> ALLOY_FURNACE_MENU =
            registerMenuType(AlloyFurnaceMenu::new, "alloy_furnace_menu");
    public static final RegistryObject<MenuType<WinterFurnaceMenu>> WINTER_FURNACE_MENU =
            registerMenuType(WinterFurnaceMenu::new, "winter_furnace_menu");
    public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU =
            registerMenuType(CrusherMenu::new, "crusher_menu");
    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}