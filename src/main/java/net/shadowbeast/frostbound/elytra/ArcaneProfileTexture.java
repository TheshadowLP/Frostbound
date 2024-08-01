package net.shadowbeast.frostbound.elytra;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ArcaneProfileTexture {
    public enum Type {
        ENDER_ELYTRA
        ;
    }

    public static final int PROFILE_TEXTURE_COUNT = com.mojang.authlib.minecraft.MinecraftProfileTexture.Type.values().length;

    private final String url;
    private final Map<String, String> metadata;

    public ArcaneProfileTexture(final String url, final Map<String, String> metadata) {
        this.url = url;
        this.metadata = metadata;
    }

    public String getUrl() {
        return url;
    }

    @Nullable
    public String getMetadata(final String key) {
        if (metadata == null) {
            return null;
        }
        return metadata.get(key);
    }

    public String getHash() {
        try {
            return FilenameUtils.getBaseName(new URL(url).getPath());
        } catch (final MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid profile texture url");
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("url", url)
                .append("hash", getHash())
                .toString();
    }
}

