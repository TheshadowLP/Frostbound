package net.shadowbeast.arcanemysteries.block_entities.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import net.shadowbeast.arcanemysteries.block_entities.entities.AlloyFurnaceBlockEntity;
import net.shadowbeast.arcanemysteries.block_entities.slot.ResultSlotMod;
import net.shadowbeast.arcanemysteries.registries.ModBlocks;
import org.jetbrains.annotations.NotNull;
import static net.shadowbeast.arcanemysteries.block_entities.entities.AlloyFurnaceBlockEntity.isFuelItem;

public class AlloyFurnaceMenu extends AbstractContainerMenu {
    private final AlloyFurnaceBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    public AlloyFurnaceMenu(int pContainerId, Inventory inv,@NotNull FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }
    public AlloyFurnaceMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(MenuTypesMod.ALLOY_FURNACE_MENU.get(), pContainerId);
        checkContainerSize(inv, 4);
        blockEntity = ((AlloyFurnaceBlockEntity) entity);
        this.level = inv.player.level();
        this.data = data;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);

        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler,
                    AlloyFurnaceBlockEntity.AlloyFurnaceSlot.FUEL_SLOT, 80, 26) {
                public boolean mayPlace(@NotNull ItemStack pStack) {
                    return isFuelItem(pStack);
                }
            });
            this.addSlot(new SlotItemHandler(handler,
                    AlloyFurnaceBlockEntity.AlloyFurnaceSlot.INPUT_SLOT_1, 59, 21));
            this.addSlot(new SlotItemHandler(handler,
                    AlloyFurnaceBlockEntity.AlloyFurnaceSlot.INPUT_SLOT_2, 101, 21));
            this.addSlot(new ResultSlotMod(handler,
                    AlloyFurnaceBlockEntity.AlloyFurnaceSlot.OUTPUT_SLOT, 80, 58));
        });
        addDataSlots(data);
    }
    public boolean isCrafting() {
        return data.get(0) > 0;
    }
    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 31; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
    public int getScaledFuel() {
        int fuel = this.data.get(2);
        int maxFuel = this.data.get(3);  // Max Progress
        int fuelFlameSize = 14; // This is the height in pixels of your arrow
        return maxFuel != 0 && fuel != 0 ? fuel * fuelFlameSize / maxFuel : -1;
    }
    public boolean hasFuelInSlot() {
        return blockEntity.hasFuelInSlot();
    }
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!
    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }
    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.ALLOY_FURNACE.get());
    }
    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }
    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
