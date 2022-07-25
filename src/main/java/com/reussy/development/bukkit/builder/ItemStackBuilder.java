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

    private ItemStack applyMaterial(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isString("material")) return itemStack;
        setMaterial(Material.valueOf(configurationSection.getString("material")));
        return itemStack;
    }

    private ItemStack applySkullOwner(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isString("skull-owner")) return itemStack;
        String material = configurationSection.getString("material");
        if (material == null) return itemStack;
        if (!material.equalsIgnoreCase("PLAYER_HEAD")) return itemStack;
        setSkullOwner(configurationSection.getString("skull-owner"));
        return itemStack;
    }

    private ItemStack applyAmount(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isInt("amount")) return itemStack;
        setAmount(configurationSection.getInt("amount"));
        return itemStack;
    }

    private ItemStack applyDisplayName(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isString("display-name")) return itemStack;
        setDisplayName(configurationSection.getString("display-name"));
        return itemStack;
    }

    private ItemStack applyLore(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isList("lore")) return itemStack;
        setLore(configurationSection.getStringList("lore"));
        return itemStack;
    }

    private ItemStack applyCustomModelData(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isInt("custom-model-data")) return itemStack;
        setCustomModelData(configurationSection.getInt("custom-model-data"));
        return itemStack;
    }

    private ItemStack applyEnchantments(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isList("enchantments")) return itemStack;
        Map<String, Integer> enchantments = new HashMap<>();
        for (String enchantment : configurationSection.getStringList("enchantments")) {
            String[] split = enchantment.split(":");
            if (split.length == 2) {
                enchantments.put(split[0], Integer.parseInt(split[1]));
            }
        }
        addEnchantments(enchantments, true);
        return itemStack;
    }

    private ItemStack applyItemFlags(final ConfigurationSection configurationSection) {
        if (configurationSection == null) return itemStack;
        if (!configurationSection.isList("item-flags")) return itemStack;
        addItemFlags(configurationSection.getStringList("item-flags"));
        return itemStack;
    }

    public ItemStack build(ConfigurationSection section) {

        applyMaterial(section);
        applySkullOwner(section);
        applyAmount(section);
        applyCustomModelData(section);
        applyDisplayName(section);
        applyLore(section);
        applyEnchantments(section);
        applyItemFlags(section);

        return build();
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
