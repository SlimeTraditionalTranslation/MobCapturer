package io.github.thebusybiscuit.mobcapturer;

import javax.annotation.Nonnull;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.mobcapturer.listeners.MobCaptureListener;
import io.github.thebusybiscuit.mobcapturer.listeners.PelletListener;
import io.github.thebusybiscuit.mobcapturer.setup.Registry;
import io.github.thebusybiscuit.mobcapturer.setup.Setup;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.bakedlibs.dough.updater.GitHubBuildsUpdaterTR;

import org.mini2Dx.gettext.GetText;
import org.mini2Dx.gettext.PoFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * MobCapturer Slimefun addon
 *
 * @author TheBusyBiscuit
 * @author ybw0014
 */
public class MobCapturer extends JavaPlugin implements SlimefunAddon {

    private static MobCapturer instance;

    private Registry registry;

    @Nonnull
    public static MobCapturer getInstance() {
        return instance;
    }

    private static void setInstance(@Nonnull MobCapturer plugin) {
        instance = plugin;
    }

    @Nonnull
    public static Registry getRegistry() {
        return getInstance().registry;
    }

    @Override
    public void onEnable() {
        setInstance(this);

        Config cfg = new Config(this);
        new Metrics(this, 6672);

        if (cfg.getBoolean("options.auto-update") && getDescription().getVersion().startsWith("EFI - ")) {
            new GitHubBuildsUpdaterTR(this, getFile(), "SlimeTraditionalTranslation/MobCapturer/master").start();
        }

        GetText.setLocale(java.util.Locale.TRADITIONAL_CHINESE);
        InputStream inputStream = getClass().getResourceAsStream("/translations/zh_tw.po");
        if (inputStream == null) {
            getLogger().severe("錯誤！無法找到翻譯檔案，請回報給翻譯者。");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            getLogger().info("載入繁體翻譯檔案...");
            try {
                PoFile poFile = new PoFile(java.util.Locale.TRADITIONAL_CHINESE, inputStream);
                GetText.add(poFile);
            } catch (ParseCancellationException | IOException e) {
                getLogger().severe("錯誤！讀取翻譯時發生錯誤，請回報給翻譯者：" + e.getMessage());
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }

        registry = new Registry(cfg);

        Setup.setup();

        // listeners
        new PelletListener(this);
        new MobCaptureListener(this);
    }

    @Override
    @Nonnull
    public String getBugTrackerURL() {
        return "https://github.com/SlimeTraditionalTranslation/MobCapturer/issues";
    }

    @Override
    @Nonnull
    public JavaPlugin getJavaPlugin() {
        return this;
    }
}
