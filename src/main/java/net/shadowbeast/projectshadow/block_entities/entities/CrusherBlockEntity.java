package net.shadowbeast.projectshadow.block_entities.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.shadowbeast.projectshadow.block_entities.menu.CrusherMenu;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import net.shadowbeast.projectshadow.items.ModItems;
import net.shadowbeast.projectshadow.block_entities.recipes.CrusherRecipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CrusherBlockEntity extends BlockEntity implements MenuProvider {
    public static class CrusherSlot {
        public static final int INPUT_SLOT = 0;
        public static final int SHREDBLADE_SLOT = 1;
        public static final int OUTPUT_SLOT = 2;
        private CrusherSlot() {}
    }
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;
    public  CrusherBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.CRUSHER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> CrusherBlockEntity.this.progress;
                    case 1 -> CrusherBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CrusherBlockEntity.this.progress = value;
                    case 1 -> CrusherBlockEntity.this.maxProgress = value;
                }
            }
            public int getCount() {
                return 2;
            }
        };
    }
    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.projectshadow.crusher");
    }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new CrusherMenu(pContainerId, pInventory, this, this.data);
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
        tag.putInt("crushing.progress", progress);
        super.saveAdditional(tag);
    }
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("crushing.progress");
    }
    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe(this)) {
            this.progress++;
            setChanged(pLevel, pPos, pState);
            if(this.progress > this.maxProgress) {
                craftItem();
            }
        } else {
            this.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }
    private static boolean hasRecipe( CrusherBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        assert level != null;
        Optional<CrusherRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level);
        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem())
                && hasItemInFuelSlot(entity);
    }
    private static boolean hasItemInFuelSlot(CrusherBlockEntity entity) {
        Item item = entity.itemHandler.getStackInSlot(CrusherSlot.SHREDBLADE_SLOT).getItem();

        // New Saws Go Here
        return item == ModItems.IRON_SAW_BLADE.get() || item == ModItems.PLATINUM_SAW_BLADE.get() || item == ModItems.DIAMOND_SAW_BLADE.get() || item == ModItems.TITANIUM_SAW_BLADE.get();
    }

    private void craftItem() {
        Level level = this.level;
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        assert level != null;
        Optional<CrusherRecipe> match = level.getRecipeManager()
                .getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level);
        if(match.isPresent()) {
            ItemStack stack = this.itemHandler.getStackInSlot(CrusherSlot.SHREDBLADE_SLOT);
            CompoundTag damage = stack.getTagElement("DamageData");
            if (damage == null) {
                CompoundTag newDamage = stack.getOrCreateTagElement("DamageData");
                newDamage.putInt("Damage", 1);
            } else {
                CompoundTag newDamage = stack.getOrCreateTagElement("DamageData");
                damage = stack.getTagElement("DamageData");

                assert damage != null;

                newDamage.putInt("Damage", damage.getInt("Damage") + 1);
                if (damage.getInt("Damage") + 1 > 256) {
                    clearItem(1, this.itemHandler);
                }
            }
            clearItem(CrusherSlot.INPUT_SLOT, this.itemHandler);
            setItem(match.get().getResultItem().getItem(), this.itemHandler, match.get().getResultItem().getCount());
            this.resetProgress();
        }
    }
    private static void clearItem(int Slot, @NotNull ItemStackHandler handler) {
        handler.extractItem(Slot, 1, false);
    }
    private static void setItem(@NotNull Item pItem, @NotNull ItemStackHandler handler, int count) {
        handler.setStackInSlot(CrusherSlot.OUTPUT_SLOT, new ItemStack(pItem,
                handler.getStackInSlot(CrusherSlot.OUTPUT_SLOT).getCount() + count));
    }
    private void resetProgress() {
        this.progress = 0;
    }
    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack output) {
        return inventory.getItem(CrusherSlot.OUTPUT_SLOT).getItem() == output.getItem()
                || inventory.getItem(CrusherSlot.OUTPUT_SLOT).isEmpty();
    }
    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(CrusherSlot.OUTPUT_SLOT).getMaxStackSize()
                > inventory.getItem(CrusherSlot.OUTPUT_SLOT).getCount();
    }
}
