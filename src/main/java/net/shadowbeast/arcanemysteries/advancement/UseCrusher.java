package net.shadowbeast.arcanemysteries.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.shadowbeast.arcanemysteries.ArcaneMysteries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class UseCrusher extends SimpleCriterionTrigger<UseCrusher.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation(ArcaneMysteries.MOD_ID,"use_crusher");

    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public UseCrusher.@NotNull TriggerInstance createInstance(JsonObject pJson, ContextAwarePredicate pPredicate, DeserializationContext pDeserializationContext) {
        return new UseCrusher.TriggerInstance(pPredicate);
    }

    public void trigger(ServerPlayer pPlayer) {
        this.trigger(pPlayer, (p_163870_) -> true);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        public TriggerInstance(ContextAwarePredicate pPlayer) {
            super(UseCrusher.ID, pPlayer);
        }
    }
}