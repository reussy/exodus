package com.reussy.development.bukkit.helper;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.stream.Stream;

public class BukkitInventoryHelper {

    /**
     * Check if the inventory has at least one slot available.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory has at least one slot available.
     */
    public boolean hasAvailableSpace(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the inventory is empty.
     *
     * @param inventory The inventory to check.
     * @return True if the inventory is empty.
     */
    public boolean isInventoryEmpty(Inventory inventory) {
        final int i = (int) Stream.of(inventory.getContents()).filter(Objects::isNull).count();
        return i == inventory.getSize();
    }

    /**
     * Check if the inventory contains the item.
     *
     * @param inventory The inventory to check.
     * @param itemStack The item to check.
     * @return True if the inventory contains the item.
     */
    public boolean hasItem(Inventory inventory, ItemStack itemStack) {

        if (inventory == null) return false;

        for (ItemStack item : inventory.getContents()) {
            if (item == null) continue;
            if (item.isSimilar(itemStack)) return true;
        }
        return false;
    }

    /**
     * Get the item in the inventory by the slot.
     *
     * @param inventory The inventory to check.
     * @param slot      The slot to get the item from.
     * @return The item in the slot.
     */
    public ItemStack getItem(Inventory inventory, int slot) {

        if (inventory == null) return null;
        if (inventory.getItem(slot) == null) return null;

        return inventory.getItem(slot);
    }

    /**
     * Add an item to the inventory.
     *
     * @param inventory The inventory to add the item to.
     * @param itemStack The item to add.
     */
    public void addItem(Inventory inventory, ItemStack itemStack) {
        inventory.addItem(itemStack);
    }

    /**
     * Remove the item from the inventory by the slot.
     *
     * @param inventory The inventory to check.
     * @param slot The slot to remove the item from.
     */
    public void removeItem(Inventory inventory, int slot){
        if (inventory == null) return;
        if (inventory.getItem(slot) == null) return;

        inventory.setItem(slot, null);
    }
}
