package com.reussy.development.api;

import com.reussy.development.bukkit.builder.ItemStackBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ExodusAPI {

    /**
     * Create a new instance of the ItemStackBuilder.
     *
     * @return The new instance of the ItemStackBuilder.
     */
    ItemStackBuilder getItemStackBuilder();

    /**
     * Check if the inventory has at least one slot available.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory has at least one slot available.
     */
    boolean hasAvailableSpace(Inventory inventory);

    /**
     * Check if the inventory is empty.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory is empty.
     */
    boolean isInventoryEmpty(Inventory inventory);

    /**
     * Check if the inventory contains the item.
     *
     * @param inventory The inventory to check.
     * @param itemStack The item to check.
     * @return True if the inventory contains the item.
     */
    boolean hasItem(Inventory inventory, ItemStack itemStack);

    /**
     * Get the item in the inventory by the slot.
     *
     * @param inventory The inventory to check.
     * @param slot      The slot to get the item from.
     * @return The item in the slot.
     */
    ItemStack getItem(Inventory inventory, int slot);

    /**
     * Add color to the message given.
     *
     * @param message The message to colorize.
     * @return returns the message colorized.
     */
    String colorize(String message);

    /**
     * Add color to the list of messages given.
     *
     * @param messages The message to colorize.
     * @return returns the message colorized.
     */
    List<String> colorize(List<String> messages);

    /**
     * Strip the colors in the message.
     *
     * @param message The message to strip.
     * @return returns the message without color.
     */
    String fade(String message);

    /**
     * Build a new text component.
     *
     * @param message The message to shows.
     * @param command The command to be set in the chat to the human.
     * @param tooltip The message when the cursor is in the message.
     * @return returns the new text component.
     */
    TextComponent buildTextComponent(String message, String command, String tooltip);

    /**
     * Send a colorized message to the player.
     *
     * @param player  The player related.
     * @param message The message to send.
     * @apiNote If the message is null or empty, nothing will be sent.
     */
    void send(Player player, String message);

    /**
     * Send a colorized message to the player list.
     *
     * @param players The list of player's related.
     * @param message The message to send.
     */
    void send(List<Player> players, String message);

    /**
     * Teleport the entity to the location asynchronously.
     *
     * @param entity The entity to teleport.
     * @param location The location to teleport to.
     * @param teleportCause The teleport cause.
     * @return The future of the teleport. True if the teleport was successful, otherwise false.
     * @apiNote Only Paper fork can run this method asynchronously.
     */
    boolean teleport(Entity entity, Location location, PlayerTeleportEvent.TeleportCause teleportCause);
}
