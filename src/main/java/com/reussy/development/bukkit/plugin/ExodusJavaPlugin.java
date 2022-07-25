package com.reussy.development.bukkit.plugin;

import com.reussy.development.api.ExodusAPI;
import io.papermc.lib.PaperLib;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.Website;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name = "exodus", version = "1.0.0-SNAPSHOT")
@Description("API for easier creation of plugins.")
@Author("Ricardo (reussy)")
@Website("reussy.me")

public class ExodusJavaPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        PaperLib.suggestPaper(this);
        ExodusAPI exodusAPI = new WrapperExodusAPI();
        this.getServer().getServicesManager().register(ExodusAPI.class, exodusAPI, this, ServicePriority.High);
    }

    @Override
    public void onDisable() {
    }
}
