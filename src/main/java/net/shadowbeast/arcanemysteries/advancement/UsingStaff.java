package net.shadowbeast.arcanemysteries.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.items.staffs.ItemStaff;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class UsingStaff extends SimpleCriterionTrigger<UsingStaff.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(ArcaneMysteries.MOD_ID,"using_staff");

    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public UsingStaff.@NotNull TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        ItemPredicate itempredicate = ItemPredicate.fromJson(pJson.get("item"));
        return new UsingStaff.TriggerInstance(pPredicate, itempredicate);
    }

    public void trigger(ServerPlayer pPlayer, ItemStaff pItem) {
        this.trigger(pPlayer, (triggerInstance) -> triggerInstance.matches(pItem.getDefaultInstance()));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ItemPredicate item;

        public TriggerInstance(ContextAwarePredicate pPlayer, ItemPredicate pItem) {
            super(UsingStaff.ID, pPlayer);
            this.item = pItem;
        }

        public boolean matches(ItemStack pItem) {
            return this.item.matches(pItem);
        }

        public @NotNull JsonObject serializeToJson(SerializationContext pConditions) {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("item", this.item.serializeToJson());
            return jsonobject;
        }
    }
}