package com.reussy.development.bukkit.helper;

import io.papermc.lib.PaperLib;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class BukkitEntityHelper {

    public boolean teleport(Entity entity, Location location, PlayerTeleportEvent.TeleportCause teleportCause) {

        if (entity == null) return false;
        if (location == null) return false;
        if (teleportCause == null) teleportCause = PlayerTeleportEvent.TeleportCause.PLUGIN;

        try{
            PaperLib.teleportAsync(entity, location, teleportCause).get();
        }catch (CancellationException | ExecutionException | InterruptedException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
