package com.reussy.development.bukkit.helper;

import io.papermc.lib.PaperLib;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class BukkitEntityHelper {

    /**
     * Teleport the entity to the location asynchronously.
     *
     * @param entity The entity to teleport.
     * @param location The location to teleport to.
     * @param teleportCause The teleport cause.
     * @return The future of the teleport. True if the teleport was successful, otherwise false.
     * @apiNote Only Paper fork can run this method asynchronously.
     */
    public boolean teleport(Entity entity, Location location, PlayerTeleportEvent.TeleportCause teleportCause) {

        if (entity == null) return false;
        if (location == null) return false;
        PlayerTeleportEvent.TeleportCause teleportCause1 = teleportCause == null ? PlayerTeleportEvent.TeleportCause.PLUGIN : teleportCause;

        try{
            PaperLib.teleportAsync(entity, location, teleportCause1).get();
        }catch (CancellationException | ExecutionException | InterruptedException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
