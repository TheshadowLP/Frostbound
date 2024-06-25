package net.shadowbeast.arcanemysteries.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;

public class MilkCowWithBottle extends SimpleCriterionTrigger<MilkCowWithBottle.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(ArcaneMysteries.MOD_ID,"milk_cow_with_bottle");

    public ResourceLocation getId() {
        return ID;
    }

    public MilkCowWithBottle.TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        return new MilkCowWithBottle.TriggerInstance(pPredicate);
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (p_163870_) -> true);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        public TriggerInstance(ContextAwarePredicate pPlayer) {
            super(MilkCowWithBottle.ID, pPlayer);
        }
    }
}