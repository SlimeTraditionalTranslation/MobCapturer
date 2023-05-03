package io.github.thebusybiscuit.mobcapturer.setup;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import de.unpixelt.locale.Locale;
import de.unpixelt.locale.Translate;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import io.github.thebusybiscuit.slimefun4.utils.ChatUtils;

import org.mini2Dx.gettext.GetText;

/**
 * All the {@link SlimefunItemStack}s in MobCapturer.
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public final class ItemStacks {
    public static final SlimefunItemStack MOB_CANNON = new SlimefunItemStack(
        "MOB_CANNON",
        Material.BLAZE_ROD,
        GetText.tr("&6Mob Capturing Cannon"),
        "",
        GetText.tr("&eRight Click &7to shoot a &fMob Caging Pellet")
    );
    public static final SlimefunItemStack MOB_CAPTURING_PELLET = new SlimefunItemStack(
        "MOB_CAPTURING_PELLET",
        "983b30e9d135b05190eea2c3ac61e2ab55a2d81e1a58dbb26983a14082664",
        GetText.tr("&fMob Capturing Pellet"),
        "",
        GetText.tr("&7These Pellets are used as"),
        GetText.tr("&7Ammunition for your &6Mob Capturing Cannon")
    );

    private ItemStacks() {}

    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack buildMobEgg(EntityType type, String eggTexture) {
        Validate.notNull(type, "Entity type cannot be null");
        Validate.notNull(eggTexture, "Egg texture cannot be null");

        return new SlimefunItemStack(
            "MOB_EGG_" + type,
            eggTexture,
            GetText.tr("&aMob Egg &7(") + Translate.getEntity(Locale.zh_tw, type) + GetText.tr(")"),
            "",
            GetText.tr("&7Right Click this Item on a Block"),
            GetText.tr("&7to release your captured Mob")
        );
    }
}
