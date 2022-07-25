package com.reussy.development.bukkit.builder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SuppressWarnings("UnusedReturnValue")
public class ItemStackBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final SkullMeta skullMeta;

    public ItemStackBuilder() {
        this.itemStack = new ItemStack(Material.STONE);
        this.itemMeta = itemStack.getItemMeta();
        this.skullMeta = (SkullMeta) itemMeta;
    }

    public ItemStackBuilder setMaterial(final Material material) {

        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null.");
        }

        itemStack.setType(material);
        return this;
    }

    public ItemStackBuilder setAmount(final int amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        itemStack.setAmount(amount);
        return this;
    }

    public ItemStackBuilder setDisplayName(final String displayName) {

        if (displayName == null || displayName.isEmpty()) return this;

        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        return this;
    }

    public ItemStackBuilder setLore(final List<String> lore) {

        if (lore == null || lore.isEmpty()) return this;

        for (String line : lore) {
            if (line == null || line.isEmpty()) continue;
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        itemMeta.setLore(lore);
        return this;
    }

    public ItemStackBuilder setCustomModelData(final int customModelData) {
        itemMeta.setCustomModelData(customModelData);
        return this;
    }

    public ItemStackBuilder setSkullOwner(final String owner) {

        if (owner == null || owner.isEmpty()) return this;

        PlayerProfile playerProfile = Bukkit.createPlayerProfile(owner);

        if (playerProfile.getTextures().isEmpty()) {
            throw new IllegalArgumentException("Owner must have a texture.");
        }

        skullMeta.setOwnerProfile(playerProfile);
        return this;
    }

    public ItemStackBuilder addItemFlags(final List<String> itemFlags) {

        for (String itemFlag : itemFlags) {
            itemMeta.addItemFlags(ItemFlag.valueOf(itemFlag));
        }
        return this;
    }

    public ItemStackBuilder addEnchantment(final String enchant, final int level, final boolean ignoreLevelRestriction) {

        if (enchant == null || enchant.isEmpty()) return this;

        Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchant.toLowerCase(Locale.ROOT)));

        if (enchantment == null) return this;

        itemMeta.addEnchant(enchantment, level, ignoreLevelRestriction);
        return this;
    }

    public ItemStackBuilder addEnchantments(final Map<String, Integer> enchantments, final boolean ignoreLevelRestriction) {

        for (Map.Entry<String, Integer> enchantment : enchantments.entrySet()) {
            addEnchantment(enchantment.getKey(), enchantment.getValue(), ignoreLevelRestriction);
        }
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack build(ConfigurationSection section) {

        if (section == null) return null;

        if (section.contains("material")) {
            setMaterial(Material.valueOf(section.getString("material")));
        }

        if (section.contains("amount")) {
            setAmount(section.getInt("amount"));
        }

        if (section.contains("display-name")) {
            setDisplayName(section.getString("display-name"));
        }

        if (section.contains("lore") || section.contains("description")) {
            setLore(section.getStringList("lore"));
        }

        if (section.contains("custom-model-data")) {
            setCustomModelData(section.getInt("custom-model-data"));
        }

        if (section.contains("enchantments")) {

            Map<String, Integer> enchantments = new HashMap<>();
            for (String enchantment : section.getStringList("enchantments")) {
                String[] split = enchantment.split(":");
                if (split.length == 2) {
                    enchantments.put(split[0], Integer.parseInt(split[1]));
                }
            }
            addEnchantments(enchantments, true);
        }

        if (section.contains("item-flags")) {
            addItemFlags(section.getStringList("item-flags"));
        }

        if (section.getString("material").equalsIgnoreCase("PLAYER_HEAD") && section.contains("skull-owner")) {
            setSkullOwner(section.getString("skull-owner"));
            itemStack.setItemMeta(skullMeta);
        }

        return build();
    }
}
