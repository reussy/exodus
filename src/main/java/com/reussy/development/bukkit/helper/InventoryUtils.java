package com.reussy.development.bukkit.helper;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

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
     * @return The item in the slot.
     */
    public ItemStack getItem(Inventory inventory, int slot){

        if (inventory == null) return null;
        if (inventory.getItem(slot) == null) return null;

        return inventory.getItem(slot);
    }

}
