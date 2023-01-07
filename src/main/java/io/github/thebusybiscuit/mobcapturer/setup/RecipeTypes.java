package io.github.thebusybiscuit.mobcapturer.setup;

import org.bukkit.NamespacedKey;

import io.github.thebusybiscuit.mobcapturer.MobCapturer;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;

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
            "&6生物捕捉槍",
            "&7使用 &6生物捕捉槍",
            "&7來抓指令生物."
        )
    );

    private RecipeTypes() {}
}
