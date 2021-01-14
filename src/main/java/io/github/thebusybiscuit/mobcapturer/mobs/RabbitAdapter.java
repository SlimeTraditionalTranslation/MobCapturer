package io.github.thebusybiscuit.mobcapturer.mobs;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Rabbit.Type;

import com.google.gson.JsonObject;

import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

public class RabbitAdapter extends AnimalsAdapter<Rabbit> {

    public RabbitAdapter() {
        super(Rabbit.class);
    }

    @Override
    public List<String> getLore(JsonObject json) {
        List<String> lore = super.getLore(json);

        lore.add(ChatColor.GRAY + "品種: " + ChatColor.WHITE + ChatUtils.humanize(json.get("rabbitType").getAsString()));

        return lore;
    }

    @Override
    public void apply(Rabbit entity, JsonObject json) {
        super.apply(entity, json);

        entity.setRabbitType(Type.valueOf(json.get("rabbitType").getAsString()));
    }

    @Override
    public JsonObject saveData(Rabbit entity) {
        JsonObject json = super.saveData(entity);

        json.addProperty("rabbitType", entity.getRabbitType().name());

        return json;
    }

}
