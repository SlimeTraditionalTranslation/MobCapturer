package io.github.thebusybiscuit.mobcapturer;

import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.gson.JsonObject;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SimpleSlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.NotPlaceable;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.inventory.ItemUtils;

public class MobEgg<T extends LivingEntity> extends SimpleSlimefunItem<ItemUseHandler> implements NotPlaceable {
	
	private final NamespacedKey key;
	private final MobAdapter<T> adapter;
	
	public MobEgg(Category category, SlimefunItemStack item, NamespacedKey key, MobAdapter<T> adapter, RecipeType recipeType, ItemStack[] recipe) {
		super(category, item, recipeType, recipe);
		
		this.key = key;
		this.adapter = adapter;
	}

	public ItemStack getEggItem(T entity) {
		JsonObject json = adapter.save(entity);
		
		ItemStack item = this.item.clone();
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, adapter, json);
		item.setItemMeta(meta);
		
		return item;
	}

	@Override
	public ItemUseHandler getItemHandler() {
		return e -> {
			e.cancel();
			
			Optional<Block> block = e.getClickedBlock();
			
			if (block.isPresent()) {
				ItemUtils.consumeItem(e.getItem(), false);
				
				Block b = block.get();
				T entity = b.getWorld().spawn(b.getRelative(e.getClickedFace()).getLocation(), adapter.getEntityClass());
				
				JsonObject json = e.getItem().getItemMeta().getPersistentDataContainer().get(key, adapter);
				
				if (json != null) {
					adapter.apply(entity, json);
				}
			}
		};
	}

}
