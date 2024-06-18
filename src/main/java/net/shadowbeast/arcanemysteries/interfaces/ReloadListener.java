package net.shadowbeast.arcanemysteries.interfaces;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;

public interface ReloadListener extends PreparableReloadListener {
    ResourceLocation id();
}
