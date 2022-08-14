package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.gson.JsonObject;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.TropicalFish.Pattern;

import io.github.thebusybiscuit.mobcapturer.MobAdapter;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class TropicalFishAdapter implements MobAdapter<TropicalFish> {

    @Nonnull
    @Override
    public List<String> getLore(@Nonnull JsonObject json) {
        List<String> lore = MobAdapter.super.getLore(json);

        lore.add(ChatColor.GRAY + "基本顏色: " + ChatColor.WHITE + ChatUtils.humanize(json.get("baseColor").getAsString()));
        lore.add(ChatColor.GRAY + "樣式: " + ChatColor.WHITE + ChatUtils.humanize(json.get("pattern").getAsString()));
        lore.add(ChatColor.GRAY + "樣式顏色: " + ChatColor.WHITE + ChatUtils.humanize(json.get("patternColor").getAsString()));

        return lore;
    }

    @ParametersAreNonnullByDefault
    @Override
    public void apply(TropicalFish entity, JsonObject json) {
        MobAdapter.super.apply(entity, json);

        entity.setBodyColor(DyeColor.valueOf(json.get("baseColor").getAsString()));
        entity.setPattern(Pattern.valueOf(json.get("pattern").getAsString()));
        entity.setPatternColor(DyeColor.valueOf(json.get("patternColor").getAsString()));
    }

    @Nonnull
    @Override
    public JsonObject saveData(@Nonnull TropicalFish entity) {
        JsonObject json = MobAdapter.super.saveData(entity);

        json.addProperty("baseColor", entity.getBodyColor().name());
        json.addProperty("pattern", entity.getPattern().name());
        json.addProperty("patternColor", entity.getPatternColor().name());

        return json;
    }

    @Nonnull
    @Override
    public Class<TropicalFish> getEntityClass() {
        return TropicalFish.class;
    }

}
