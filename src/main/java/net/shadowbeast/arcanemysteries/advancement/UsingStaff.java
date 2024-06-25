package net.shadowbeast.arcanemysteries.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import net.shadowbeast.arcanemysteries.items.staffs.ItemStaff;

public class UsingStaff extends SimpleCriterionTrigger<UsingStaff.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(ArcaneMysteries.MOD_ID,"using_staff");

    public ResourceLocation getId() {
        return ID;
    }

    public UsingStaff.TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        ItemPredicate itempredicate = ItemPredicate.fromJson(pJson.get("item"));
        return new UsingStaff.TriggerInstance(pPredicate, itempredicate);
    }

    public void trigger(ServerPlayer pPlayer, ItemStaff pItem) {
        this.trigger(pPlayer, (p_163870_) -> p_163870_.matches(pItem.getDefaultInstance()));
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

        public JsonObject serializeToJson(SerializationContext pConditions) {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("item", this.item.serializeToJson());
            return jsonobject;
        }
    }
}