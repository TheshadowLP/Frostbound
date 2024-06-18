package net.shadowbeast.arcanemysteries.block_entities.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import net.shadowbeast.arcanemysteries.block_entities.menu.AlloyFurnaceMenu;
import net.shadowbeast.arcanemysteries.block_entities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.util.TagsMod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;

import static net.shadowbeast.arcanemysteries.block_entities.block.furnace.AlloyFurnaceBlock.ACTIVE;

@ParametersAreNonnullByDefault
public class AlloyFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 260;
    private int fuel = 0;
    private int maxFuel = 4000;
    private float experience = 0.0f;

    private enum FuelTypes {
        SMALL, MEDIUM, LARGE, NONE
    }

    public static class AlloyFurnaceSlot {
        public static final int FUEL_SLOT = 0;
        public static final int INPUT_SLOT_1 = 1;
        public static final int INPUT_SLOT_2 = 2;
        public static final int OUTPUT_SLOT = 3;
    }


    public static boolean isFuelItem(ItemStack itemStack) { return itemStack.is(TagsMod.Items.ALLOYING_FUEL); }

    public AlloyFurnaceBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(EntityRegistry.ALLOY_FURNACE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
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
        return Component.translatable("block.arcanemysteries.alloy_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId,  Inventory pInventory,  Player pPlayer) {
        return new AlloyFurnaceMenu(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,@Nullable Direction side) {
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
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("progress", progress);
        tag.putInt("fuel", fuel);
        tag.putFloat("experience", experience);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("progress");
        fuel = nbt.getInt("fuel");
        experience = nbt.getFloat("experience");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int idx = 0; idx < itemHandler.getSlots(); idx++) {
            inventory.setItem(idx, itemHandler.getStackInSlot(idx));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        boolean isActive = false;
        if (hasLavaBucketInFuelSlot()) {
            addFuelTime(4000, true);
        }
        FuelTypes currentFuel = getFuelItemTypeInSlot(this);
        if (canAddFuelTime(100)) {
            switch (currentFuel) {
                case SMALL -> addFuelTime(100, false);
                case MEDIUM -> addFuelTime(200, false);
                case LARGE -> addFuelTime(2000, false);
            }
        }

        if(hasRecipe() && hasEnoughFuel()) {
            this.maxProgress = getRecipe().map(AlloyFurnaceRecipe::getCookingTime).orElse(AlloyFurnaceRecipe.DEFAULT_COOK_TIME);
            this.progress++;
            setChanged(pLevel, pPos, pState);
            ambient(pPos);
            isActive = true;
            if(this.progress > this.maxProgress) {
                craftItem();
                assert level != null;
                level.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5,
                        SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.1F, 1.0F, 16);

            }
        } else {
            resetProgress();
            setChanged(pLevel, pPos, pState);
        }
        pState = pState.setValue(ACTIVE, isActive);
        assert level != null;
        level.setBlockAndUpdate(pPos, pState);
        setChanged(pLevel, pPos, pState);
    }


    public boolean hasFuelInSlot() { return !itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).isEmpty(); }
    public boolean hasEnoughFuel() { return this.fuel >= 200; }

    // The land of all the private functions

    private static FuelTypes getFuelItemTypeInSlot(AlloyFurnaceBlockEntity entity) {
        int fuelSlot = AlloyFurnaceSlot.FUEL_SLOT;
        ItemStack stackSlot = entity.itemHandler.getStackInSlot(fuelSlot);
        if (stackSlot.is(TagsMod.Items.ALLOYING_FUEL_SMALL)) {
            return FuelTypes.SMALL;
        } else if (stackSlot.is(TagsMod.Items.ALLOYING_FUEL_MEDIUM)) {
            return FuelTypes.MEDIUM;
        } else if (stackSlot.is(TagsMod.Items.ALLOYING_FUEL_LARGE)) {
            return FuelTypes.LARGE;
        } else {
            return FuelTypes.NONE;
        }
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) { setChanged(); }
    };
    private boolean hasRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        return getRecipe().isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, getRecipe().get().getResultItem())
                && hasEnoughFuel();
    }

    private @NotNull Optional<AlloyFurnaceRecipe> getRecipe() {
        Level level = this.level;
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        assert level != null;
        return level.getRecipeManager().getRecipeFor(AlloyFurnaceRecipe.Type.INSTANCE, inventory, level);
    }
    private void addFuelTime(int addedFuelTime, boolean isLava) {
        if(canAddFuelTime(addedFuelTime)) {
            clearItem(AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler);
            if(isLava) {
                setItem(Items.BUCKET, AlloyFurnaceSlot.FUEL_SLOT, this.itemHandler, 1);
            }
            this.fuel += addedFuelTime;
        }
    }

    private boolean canAddFuelTime(int addedFuelTime) { return this.maxFuel - this.fuel >= addedFuelTime; }

    private boolean hasLavaBucketInFuelSlot() {
        return this.itemHandler.getStackInSlot(AlloyFurnaceSlot.FUEL_SLOT).getItem() == Items.LAVA_BUCKET;
    }
    
    private void ambient(BlockPos pPos) {
        Level pLevel = this.level;
        if (new Random().nextInt(40) == 0) {
            assert pLevel != null;
            pLevel.playSeededSound(null, pPos.getX() + 0.5, pPos.getY() + 0.5, pPos.getZ() + 0.5,
                    SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1, 1, 16);
        }
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

            setItem(match.get().getResultItem().getItem(), AlloyFurnaceSlot.OUTPUT_SLOT, this.itemHandler, match.get().getResultItem().getCount());
            this.fuel -= 200;
            this.experience += getRecipe().map(AlloyFurnaceRecipe::getExperience).orElse(0.0f);
            this.resetProgress();
        }
    }

    private static void clearItem(int Slot,  ItemStackHandler handler) { handler.extractItem(Slot, 1, false); }

    private static void setItem( Item pItem, int Slot,  ItemStackHandler handler, int count) {
        handler.setStackInSlot(Slot, new ItemStack(pItem,
                handler.getStackInSlot(Slot).getCount() + count));
    }

    private void resetProgress() { this.progress = 0; }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).getItem() == output.getItem()
                || inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).getMaxStackSize() > inventory.getItem(AlloyFurnaceSlot.OUTPUT_SLOT).getCount();
    }
}
