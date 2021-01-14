package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Parrot.Variant;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class ParrotAdapter extends AbstractTameableAdapter<Parrot> {

    public ParrotAdapter() {
        super(Parrot.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "品種: " + ChatColor.WHITE + ChatUtils.humanize(json.get("variant").getAsString()));

        if (!json.get("ownerUUID").isJsonNull()) {
            lore.add(ChatColor.GRAY + "坐著: " + ChatColor.WHITE + json.get("sitting").getAsBoolean());
        }

        return lore;
    }

    @Override
    public void apply(Parrot entity, JsonObject json) {
        super.apply(entity, json);

        entity.setVariant(Variant.valueOf(json.get("variant").getAsString()));
        entity.setSitting(json.get("sitting").getAsBoolean());
    }

    @Override
    public JsonObject saveData(Parrot entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("variant", entity.getVariant().name());
        json.addProperty("sitting", entity.isSitting());

        return json;
    }

}
