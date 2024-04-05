package net.shadowbeast.projectshadow.blockEntities.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.block.Blocks;
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
import static net.shadowbeast.projectshadow.blockEntities.block.AlloyFurnaceBlock.ACTIVE;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    public static class AlloyFurnaceSlot {
        public static final int FUEL_SLOT = 0;
        public static final int INPUT_SLOT_1 = 1;
        public static final int INPUT_SLOT_2 = 2;
        public static final int OUTPUT_SLOT = 3;
        private AlloyFurnaceSlot() {}
    }

    static List<Item> sFuel = List.of(Blocks.ACACIA_LOG.asItem(), Blocks.BIRCH_LOG.asItem(),
            Blocks.OAK_LOG.asItem(), Blocks.JUNGLE_LOG.asItem(), Blocks.SPRUCE_LOG.asItem(),
            Blocks.CHERRY_LOG.asItem(), Blocks.DARK_OAK_LOG.asItem(), Blocks.MANGROVE_LOG.asItem(),
            Blocks.STRIPPED_ACACIA_LOG.asItem(), Blocks.STRIPPED_BIRCH_LOG.asItem(),
            Blocks.STRIPPED_OAK_LOG.asItem(), Blocks.STRIPPED_JUNGLE_LOG.asItem(), Blocks.STRIPPED_SPRUCE_LOG.asItem(),
            Blocks.STRIPPED_CHERRY_LOG.asItem(), Blocks.STRIPPED_DARK_OAK_LOG.asItem(), Blocks.STRIPPED_MANGROVE_LOG.asItem(),
            Blocks.ACACIA_WOOD.asItem(), Blocks.BIRCH_WOOD.asItem(),
            Blocks.OAK_WOOD.asItem(), Blocks.JUNGLE_WOOD.asItem(), Blocks.SPRUCE_WOOD.asItem(),
            Blocks.CHERRY_WOOD.asItem(), Blocks.DARK_OAK_WOOD.asItem(), Blocks.MANGROVE_WOOD.asItem(),
            Blocks.STRIPPED_ACACIA_WOOD.asItem(), Blocks.STRIPPED_BIRCH_WOOD.asItem(),
            Blocks.STRIPPED_OAK_WOOD.asItem(), Blocks.STRIPPED_JUNGLE_WOOD.asItem(), Blocks.STRIPPED_SPRUCE_WOOD.asItem(),
            Blocks.STRIPPED_CHERRY_WOOD.asItem(), Blocks.STRIPPED_DARK_OAK_WOOD.asItem(), Blocks.STRIPPED_MANGROVE_WOOD.asItem());
    static List<Item> mFuel = List.of(Items.COAL, Items.CHARCOAL);
    static List<Item> lFuel = List.of(Blocks.COAL_BLOCK.asItem());

    private enum FuelTypes {
        SMALL, MEDIUM, LARGE, NONE
    }

    private static FuelTypes getFuelItemInSlot(AlloyFurnaceBlockEntity entity) {
        FuelTypes type;
        if (sFuel.contains(entity.itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).getItem())) {
            return FuelTypes.SMALL;
        } else if (mFuel.contains(entity.itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).getItem())) {
            return FuelTypes.MEDIUM;
        } else if (lFuel.contains(entity.itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).getItem())) {
            return FuelTypes.LARGE;
        } else {
            return FuelTypes.NONE;
        }

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
    private int maxFuel = 4000;
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
                return 4;
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
        tag.putInt("alloy_furnace.fuel", fuel);
        super.saveAdditional(tag);
    }
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("alloy_furnace.progress");
        fuel = nbt.getInt("alloy_furnace.fuel");
    }
    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        boolean isActive = false;
        if (hasLavaBucketInFuelSlot() && this.maxFuel - this.fuel >= 4000) {
            clearItem(AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler);
            setItem(Items.BUCKET, AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler);
            this.fuel += 4000;
        }
        FuelTypes currentFuel = getFuelItemInSlot(this);
        if (currentFuel != FuelTypes.NONE && this.maxFuel - this.fuel >= 100) {
            if (currentFuel == FuelTypes.SMALL && this.maxFuel - this.fuel >= 100) {
                clearItem(AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler);
                this.fuel += 100;
            } else if (currentFuel == FuelTypes.MEDIUM && this.maxFuel - this.fuel >= 200) {
                clearItem(AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler);
                this.fuel += 200;
            } else if (currentFuel == FuelTypes.LARGE && this.maxFuel - this.fuel >= 2000) {
                clearItem(AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler);
                this.fuel += 2000;
            }
        }

        if(hasRecipe() && hasEnoughFuel()) {
            this.progress++;
            setChanged(pLevel, pPos, pState);
            isActive = true;
            if(this.progress > this.maxProgress) {
                craftItem();
            }
        } else {
            resetProgress();
            setChanged(pLevel, pPos, pState);
        }
        pState = pState.setValue(ACTIVE, isActive);
        level.setBlockAndUpdate(pPos, pState);
        setChanged(pLevel, pPos, pState);
    }
    private boolean hasRecipe() {
        Level level = this.level;
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        assert level != null;
        Optional<AlloyFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasEnoughFuel();
    }
    private boolean hasLavaBucketInFuelSlot() {
        return this.itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).getItem() == Items.LAVA_BUCKET;
    }

    public boolean hasEnoughFuel() {
        return this.fuel >= 200;
    }

    private void craftItem() {
        Level level = this.level;
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        assert level != null;
        Optional<AlloyFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            clearItem(AlloyFurnaceSlot.INPUT_SLOT_1, this.itemHandler);
            clearItem(AlloyFurnaceSlot.INPUT_SLOT_2, this.itemHandler);

            setItem(match.get().getResultItem().getItem(), AlloyFurnaceSlot.OUTPUT_SLOT, this.itemHandler);
            this.fuel -= 200;

            this.resetProgress();
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
