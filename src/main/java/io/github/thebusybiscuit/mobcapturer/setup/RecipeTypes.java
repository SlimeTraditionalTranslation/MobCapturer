package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

import org.mini2Dx.gettext.GetText;

/**
 * All the {@link RecipeType}s in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public final class RecipeTypes {
    public static final RecipeType MOB_CAPTURING = new RecipeType(
        new NamespacedKey(MobCapturer.getInstance(), "mob_capturing"),
        new CustomItemStack(
            ItemStacks.MOB_CANNON,
            GetText.tr("&6Mob Capturing Cannon"),
            GetText.tr("&7Use a &6Mob Capturing Cannon"),
            GetText.tr("&7to catch the given Mob.")
        )
    );

    private RecipeTypes() {}
}
