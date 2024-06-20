package net.shadowbeast.arcanemysteries.block_entities.slot;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class ResultSlotMod extends SlotItemHandler {
    private final ContainerData containerData;
    public ResultSlotMod(IItemHandler itemHandler, int index, int xPosition, int yPosition, ContainerData containerData) {
        super(itemHandler, index, xPosition, yPosition);
        this.containerData = containerData;
    }
    @Override
    public boolean mayPlace(@NotNull ItemStack stack) { return false; }

    @Override
    public void onTake(@NotNull Player player, @NotNull ItemStack pStack) {
        // player.giveExperiencePoints(containerData.get(3)); //TODO We need a slot for xp stuff
        super.onTake(player, pStack);
    }

}