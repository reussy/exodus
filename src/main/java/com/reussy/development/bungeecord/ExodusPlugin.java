package com.reussy.development.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;

public class ExodusPlugin extends Plugin {

    @Override
    public void onEnable() {
        getLogger().info("exodus has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("exodus has been disabled!");
    }

}
