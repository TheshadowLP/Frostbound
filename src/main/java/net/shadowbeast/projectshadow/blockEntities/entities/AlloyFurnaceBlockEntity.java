package net.shadowbeast.projectshadow.blockEntities.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import net.shadowbeast.projectshadow.blockEntities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.projectshadow.blockEntities.menu.AlloyFurnaceMenu;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;
public class AlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    public static class AlloyFurnaceSlot {
        public static final int FUEL_SLOT = 0;
        public static final int INPUT_SLOT_1 = 1;
        public static final int INPUT_SLOT_2 = 2;
        public static final int OUTPUT_SLOT = 3;
        private AlloyFurnaceSlot() {}
    }
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 260;
    private int fuel = 0;
    private int maxFuel = 1000;
    public  AlloyFurnaceBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ALLOY_FURNACE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.progress;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxProgress;
                    case 2 -> AlloyFurnaceBlockEntity.this.fuel;
                    case 3 -> AlloyFurnaceBlockEntity.this.maxFuel;
                    default -> 0;
                };
            }
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlloyFurnaceBlockEntity.this.progress = value;
                    case 1 -> AlloyFurnaceBlockEntity.this.maxProgress = value;
                    case 2 -> AlloyFurnaceBlockEntity.this.fuel = value;
                    case 3 -> AlloyFurnaceBlockEntity.this.maxFuel = value;
                }
            }
            public int getCount() {
                return 2;
            }
        };
    }
    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.projectshadow.alloy_furnace");
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new AlloyFurnaceMenu(pContainerId, pInventory, this, this.data);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }
    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("alloy_furnace.progress", progress);
        super.saveAdditional(tag);
    }
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("alloy_furnace.progress");
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState,  AlloyFurnaceBlockEntity pBlockEntity) {
        if (hasItemInFuelSlot(pBlockEntity) && pBlockEntity.fuel == 0) {
            clearItem(AlloyFurnaceSlot.FUEL_SLOT, pBlockEntity.itemHandler);
            setItem(Items.BUCKET, AlloyFurnaceSlot.FUEL_SLOT, pBlockEntity.itemHandler);
            pBlockEntity.fuel = 1000;
        }

        if(hasRecipe(pBlockEntity) && hasEnoughFuel(pBlockEntity)) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                craftItem(pBlockEntity);
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }
    private static boolean hasRecipe( AlloyFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        assert level != null;
        Optional<AlloyFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasEnoughFuel(entity);
    }
    private static boolean hasItemInFuelSlot(AlloyFurnaceBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).getItem() == Items.LAVA_BUCKET;
    }

    private static boolean hasEnoughFuel(AlloyFurnaceBlockEntity entity) {
        return entity.fuel >= 200;
    }

    private static void craftItem(AlloyFurnaceBlockEntity entity) {Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        assert level != null;
        Optional<AlloyFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            clearItem(AlloyFurnaceSlot.INPUT_SLOT_1, entity.itemHandler);
            clearItem(AlloyFurnaceSlot.INPUT_SLOT_2, entity.itemHandler);

            setItem(match.get().getResultItem().getItem(), AlloyFurnaceSlot.OUTPUT_SLOT, entity.itemHandler);
            entity.fuel -= 200;

            entity.resetProgress();
        }
    }
    private static void clearItem(int Slot, @NotNull ItemStackHandler handler) {
        handler.extractItem(Slot, 1, false);
    }
    private static void setItem(@NotNull Item pItem, int Slot, @NotNull ItemStackHandler handler) {
        handler.setStackInSlot(Slot, new ItemStack(pItem,
                handler.getStackInSlot(Slot).getCount() + 1));
    }
    private void resetProgress() {
        this.progress = 0;
    }
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).getItem() == output.getItem()
                || inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).isEmpty();
    }
    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).getMaxStackSize()
                > inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).getCount();
    }
}
