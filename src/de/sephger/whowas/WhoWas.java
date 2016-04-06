package de.sephger.whowas;
import org.bukkit.plugin.java.JavaPlugin;

public class WhoWas extends JavaPlugin {

    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	
    //	getServer().getPluginManager().registerEvents(new WhoListens(), this);
    	this.getCommand("who").setExecutor(new WhoListens());
    	
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
