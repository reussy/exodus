package com.reussy.development.bukkit.plugin;

import com.reussy.development.api.ExodusAPI;
import com.reussy.development.bukkit.builder.ItemStackBuilder;
import com.reussy.development.bukkit.helper.BukkitEntityHelper;
import com.reussy.development.bukkit.helper.BukkitInventoryHelper;
import com.reussy.development.bukkit.helper.BukkitServerHelper;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class WrapperExodusAPI implements ExodusAPI {

    private final BukkitServerHelper bukkitServerHelper;
    private final BukkitInventoryHelper bukkitInventoryHelper;
    private final BukkitEntityHelper bukkitEntityHelper;

    public WrapperExodusAPI() {
        this.bukkitInventoryHelper = new BukkitInventoryHelper();
        this.bukkitServerHelper = new BukkitServerHelper();
        this.bukkitEntityHelper = new BukkitEntityHelper();
    }

    /**
     * Create a new instance of the ItemStackBuilder.
     *
     * @return The new instance of the ItemStackBuilder.
     */
    @Override
    public ItemStackBuilder getItemStackBuilder() {
        return new ItemStackBuilder();
    }

    /**
     * Check if the inventory has at least one slot available.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory has at least one slot available.
     */
    @Override
    public boolean hasAvailableSpace(Inventory inventory) {
        return bukkitInventoryHelper.hasAvailableSpace(inventory);
    }

    /**
     * Check if the inventory is empty.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory is empty.
     */
    @Override
    public boolean isInventoryEmpty(Inventory inventory) {
        return bukkitInventoryHelper.isInventoryEmpty(inventory);
    }

    /**
     * Check if the inventory contains the item.
     *
     * @param inventory The inventory to check.
     * @param itemStack The item to check.
     * @return True if the inventory contains the item.
     */
    @Override
    public boolean hasItem(Inventory inventory, ItemStack itemStack) {
        return bukkitInventoryHelper.hasItem(inventory, itemStack);
    }

    /**
     * Get the item in the inventory by the slot.
     *
     * @param inventory The inventory to check.
     * @param slot      The slot to get the item from.
     * @return The item in the slot.
     */
    @Override
    public ItemStack getItem(Inventory inventory, int slot) {
        return bukkitInventoryHelper.getItem(inventory, slot);
    }

    /**
     * Add color to the message given.
     *
     * @param message The message to colorize.
     * @return returns the message colorized.
     */
    @Override
    public String colorize(String message) {
        return bukkitServerHelper.colorize(message);
    }

    /**
     * Add color to the list of messages given.
     *
     * @param messages The message to colorize.
     * @return returns the message colorized.
     */
    @Override
    public List<String> colorize(List<String> messages) {
        return bukkitServerHelper.colorize(messages);
    }

    /**
     * Strip the colors in the message.
     *
     * @param message The message to strip.
     * @return returns the message without color.
     */
    @Override
    public String fade(String message) {
        return bukkitServerHelper.fade(message);
    }

    /**
     * Build a new text component.
     *
     * @param message The message to shows.
     * @param command The command to be set in the chat to the human.
     * @param tooltip The message when the cursor is in the message.
     * @return returns the new text component.
     */
    @Override
    public TextComponent buildTextComponent(String message, String command, String tooltip) {
        return bukkitServerHelper.buildTextComponent(message, command, tooltip);
    }

    /**
     * Send a colorized message to the player.
     *
     * @param player  The player related.
     * @param message The message to send.
     * @apiNote If the message is null or empty, nothing will be sent.
     */
    @Override
    public void send(Player player, String message) {
        bukkitServerHelper.send(player, message);
    }

    /**
     * Send a colorized message to the player list.
     *
     * @param players The list of player's related.
     * @param message The message to send.
     */
    @Override
    public void send(List<Player> players, String message) {
        bukkitServerHelper.send(players, message);
    }

    /**
     * Teleport the entity to the location asynchronously.
     *
     * @param entity        The entity to teleport.
     * @param location      The location to teleport to.
     * @param teleportCause The teleport cause.
     * @return The future of the teleport. True if the teleport was successful, otherwise false.
     * @apiNote Only Paper fork can run this method asynchronously.
     */
    @Override
    public boolean teleport(Entity entity, Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
        return bukkitEntityHelper.teleport(entity, location, teleportCause);
    }
}
