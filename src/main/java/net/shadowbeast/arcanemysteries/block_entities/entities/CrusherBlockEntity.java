package net.shadowbeast.arcanemysteries.block_entities.entities;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
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
import net.shadowbeast.arcanemysteries.block_entities.menu.CrusherMenu;
import net.shadowbeast.arcanemysteries.block_entities.recipes.AlloyFurnaceRecipe;
import net.shadowbeast.arcanemysteries.block_entities.recipes.CrusherRecipe;
import net.shadowbeast.arcanemysteries.registries.CriteriaTriggerRegistry;
import net.shadowbeast.arcanemysteries.registries.EntityRegistry;
import net.shadowbeast.arcanemysteries.registries.ItemRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class CrusherBlockEntity extends BlockEntity implements MenuProvider {
    Player advancementPlayer;
    public static class CrusherSlot {
        public static final int INPUT_SLOT = 0;
        public static final int SHREDBLADE_SLOT = 1;
        public static final int OUTPUT_SLOT = 2;
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
    private float experience = 0.0f;

    public  CrusherBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(EntityRegistry.CRUSHER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
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
            public int getCount() { return 2; }
        };
    }
    @Override
    public @NotNull Component getDisplayName() { return Component.translatable("block.arcanemysteries.crusher"); }
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        advancementPlayer = pPlayer;
        return new CrusherMenu(pContainerId, pInventory, this, this.data);
    }
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }
    @Override
    public void onLoad() {
        super.onLoad();
        this.lazyItemHandler = LazyOptional.of(() -> this.itemHandler);
    }
    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        this.lazyItemHandler.invalidate();
    }
    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", this.itemHandler.serializeNBT());
        tag.putInt("Progress", this.progress);
        tag.putFloat("Experience", this.experience);
    }
    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        if (nbt.isEmpty()) return;

        if (nbt.contains("Inventory", Tag.TAG_COMPOUND)) {
            this.itemHandler.deserializeNBT(nbt.getCompound("Inventory"));
        }

        if (nbt.contains("Progress", Tag.TAG_INT)) {
            this.progress = nbt.getInt("Progress");
        }

        if (nbt.contains("Experience", Tag.TAG_FLOAT)) {
            this.experience = nbt.getFloat("Experience");
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        saveAdditional(nbt);
        return nbt;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {return ClientboundBlockEntityDataPacket.create(this); }


    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(hasRecipe(this)) {
            Level level = this.level;
            if(advancementPlayer != null)
                CriteriaTriggerRegistry.USE_CRUSHER.trigger((ServerPlayer) advancementPlayer);
            SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
            for (int i = 0; i < this.itemHandler.getSlots(); i++) {
                inventory.setItem(i, this.itemHandler.getStackInSlot(i));
            }
            assert level != null;
            this.maxProgress = level.getRecipeManager()
                    .getRecipeFor(CrusherRecipe.Type.INSTANCE, inventory, level).map(CrusherRecipe::getCrushingTime).orElse(AlloyFurnaceRecipe.DEFAULT_COOK_TIME);
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
    private static boolean hasRecipe(CrusherBlockEntity entity) {
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
        return item == ItemRegistry.IRON_SAW_BLADE.get() || item == ItemRegistry.PLATINUM_SAW_BLADE.get() || item == ItemRegistry.DIAMOND_SAW_BLADE.get() || item == ItemRegistry.TITANIUM_SAW_BLADE.get();
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
