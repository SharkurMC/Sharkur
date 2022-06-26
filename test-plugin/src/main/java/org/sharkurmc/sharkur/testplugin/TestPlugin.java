package org.sharkurmc.sharkur.testplugin;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.sharkurmc.sharkur.event.PlayerChatMessageReceived;
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
        // event.joinMessage(MiniMessage.miniMessage().deserialize(event.getPlayer().getName() + " joined"));
    }

    @EventHandler
    public void onBrand(PlayerClientBrandEvent event) {
        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("<white>Your brand is: </white><gold>" + event.getBrandName() + "</gold>"));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.quitMessage(MiniMessage.miniMessage().deserialize(event.getPlayer().getName() + " left"));
    }

    @EventHandler
    public void onChatAsync(AsyncChatEvent event) {
        event.message(MiniMessage.miniMessage().deserialize(MiniMessage.miniMessage().serialize(event.message()) + " lol"));
    }

    @EventHandler
    public void onMessageReceived(PlayerChatMessageReceived event) {
        getLogger().info(MiniMessage.miniMessage().serialize(event.getMessage()));
    }
}
