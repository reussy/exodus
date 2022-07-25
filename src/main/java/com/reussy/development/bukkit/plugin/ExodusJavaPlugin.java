package com.reussy.development.bukkit.plugin;

import com.reussy.development.api.ExodusAPI;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name = "ExodusPlugin", version = "1.0.0-SNAPSHOT")
@Description("API for easier creation of plugins.")
@Author("Ricardo (reussy)")
@Website("www.reussy.me")

public class ExodusJavaPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        ExodusAPI exodusAPI = new WrapperExodusAPI();
        this.getServer().getServicesManager().register(ExodusAPI.class, exodusAPI, this, ServicePriority.High);
        getLogger().info("exodus has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("exodus has been disabled!");
    }
}
