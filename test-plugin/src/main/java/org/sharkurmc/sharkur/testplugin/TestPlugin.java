package org.sharkurmc.sharkur.testplugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.sharkurmc.sharkur.event.PlayerClientBrandEvent;

public final class TestPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info(" started");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onReload() {
        getLogger().info("reloading plugin");
        getServer().broadcast(MiniMessage.miniMessage().deserialize("<gold>"+getName()+" reloaded :)</gold>"));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("<gold>hello</gold>"));
    }

    @EventHandler
    public void onBrand(PlayerClientBrandEvent event) {
        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("<white>Your brand is: </white><gold>" + event.getBrandName() + "</gold>"));
    }
}