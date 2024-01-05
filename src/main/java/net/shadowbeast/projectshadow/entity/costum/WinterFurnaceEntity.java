package net.shadowbeast.projectshadow.entity.costum;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import net.shadowbeast.projectshadow.recipes.WinterFurnaceRecipe;
import net.shadowbeast.projectshadow.screen.WinterFurnaceMenu;

import java.util.Map;

public class WinterFurnaceEntity extends AbstractFurnaceBlockEntity {
    private Map<Item, Integer> BURN_DURATION_MAP =
            Map.of(Items.OAK_LOG, 300,
                    Items.BIRCH_LOG,300,
                    Items.DARK_OAK_LOG,300,
                    Items.SPRUCE_LOG,300,
                    Items.ACACIA_LOG,300,
                    Items.MANGROVE_LOG,300,
                    Items.CHERRY_LOG,300,
                    Items.LAVA_BUCKET,20000,
                    Items.COAL,1600);
    public WinterFurnaceEntity(BlockPos pPos, BlockState pBlockState){
        super(ModBlockEntities.WINTER_FURNACE_ENTITY.get(), pPos, pBlockState, WinterFurnaceRecipe.Type.INSTANCE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.projectshadow.winter_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new WinterFurnaceMenu(pContainerId, pInventory, this, dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        return BURN_DURATION_MAP.getOrDefault(pFuel.getItem(),0);
    }
}
