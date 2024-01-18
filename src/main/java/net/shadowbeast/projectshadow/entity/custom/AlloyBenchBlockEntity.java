package net.shadowbeast.projectshadow.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.shadowbeast.projectshadow.entity.ModBlockEntities;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlloyBenchBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) { setChanged();}
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty()

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 200;

    public AlloyBenchBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.ALLOY_BENCH_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> AlloyBenchBlockEntity.this.progress;
                    case 1 -> AlloyBenchBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AlloyBenchBlockEntity.this.progress = value;
                    case 1 -> AlloyBenchBlockEntity.this.maxProgress = value;
                }
            }

            public int getCount() { return 2; };
        };
    }

    @Override
    public @NotNull Component getDisplayName() { return Component.translatable("alloy_bench.projectshadow.displayname");}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new Alloy
    }


}
