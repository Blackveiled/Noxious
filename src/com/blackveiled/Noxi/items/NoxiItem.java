/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.Noxi.items;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author Blackveiled
 */
public class NoxiItem {
    
    // Basic Item Information
    private String ITEM_NAME;
    private String ITEM_TYPE;
    private int ITEM_ID;
    private int ITEM_LEVEL = 0;
    private int CLASS_REQUIRED = -1;
    private Material ITEM_MATERIAL;
    private NoxiItem[] SOCKETS = null;
    private int SOCKET_COUNT = 0;
    
    /* Item Quality
        0 = Common (White)
        1 = Uncommon (Green)
        2 = Rare (Blue)
        3 = Epic (Purple)
        4 = Legendary (Orange)
        5 = Artifact (Yellow)
        6 = Quest Item (Cyan)
    */
    private int ITEM_QUALITY = 0;
    
    // Currency Related Data
    //private int GOLD_PRICE = 0;
    //private int PLATINUM_PRICE = 0;
    
    // Combat Related Stats
    private double MIN_DAMAGE = 0.0;
    private double MAX_DAMAGE = 0.0;
    private int ARMOR = 0;
    private int STAMINA = 0;
    private int STRENGTH = 0;
    private int AGILITY = 0;
    private int INTELLIGENCE = 0;
    private int LEVEL_REQUIRED = 0;
    //private double CRITICAL_DAMAGE = 0.0;
    //private double CRITICAL_STIKE = 0.0;
    //private double DODGE_CHANCE = 0.0;
    //private double BLOCK_CHANCE = 0.0;
    
    public ItemStack getItemStack() {
        ItemStack item = new ItemStack(ITEM_MATERIAL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(getQualityColor() + ITEM_NAME);
        meta.setLore(this.generateItemLore());
        item.setItemMeta(meta);
        return item;
    }
    
    public boolean isWeapon()   {
        if(ITEM_MATERIAL.equals(Material.WOOD_SWORD) ||
                ITEM_MATERIAL.equals(Material.STONE_SWORD) ||
                ITEM_MATERIAL.equals(Material.IRON_SWORD) ||
                ITEM_MATERIAL.equals(Material.GOLD_SWORD) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_SWORD) ||
                ITEM_MATERIAL.equals(Material.WOOD_AXE) ||
                ITEM_MATERIAL.equals(Material.STONE_AXE) ||
                ITEM_MATERIAL.equals(Material.IRON_AXE) ||
                ITEM_MATERIAL.equals(Material.GOLD_AXE) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_AXE) ||
                ITEM_MATERIAL.equals(Material.WOOD_SPADE) ||
                ITEM_MATERIAL.equals(Material.STONE_SPADE) ||
                ITEM_MATERIAL.equals(Material.IRON_SPADE) ||
                ITEM_MATERIAL.equals(Material.GOLD_SPADE) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_SPADE) ||
                ITEM_MATERIAL.equals(Material.WOOD_PICKAXE) ||
                ITEM_MATERIAL.equals(Material.STONE_PICKAXE) ||
                ITEM_MATERIAL.equals(Material.IRON_PICKAXE) ||
                ITEM_MATERIAL.equals(Material.GOLD_PICKAXE) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_PICKAXE)
                )   {
            return true;
        }   else return false;
    }
    
    public boolean isHelm()   {
        if(ITEM_MATERIAL.equals(Material.LEATHER_HELMET) ||
                ITEM_MATERIAL.equals(Material.IRON_HELMET) ||
                ITEM_MATERIAL.equals(Material.GOLD_HELMET) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_HELMET)
                )   {
            return true;
        }   else return false;
    }

    public boolean isChestplate()   {
        if(ITEM_MATERIAL.equals(Material.LEATHER_CHESTPLATE) ||
                ITEM_MATERIAL.equals(Material.IRON_CHESTPLATE) ||
                ITEM_MATERIAL.equals(Material.GOLD_CHESTPLATE) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_CHESTPLATE)
                )   {
            return true;
        }   else return false;
    }
    
    public boolean isLeggings()   {
        if(ITEM_MATERIAL.equals(Material.LEATHER_LEGGINGS) ||
                ITEM_MATERIAL.equals(Material.IRON_LEGGINGS) ||
                ITEM_MATERIAL.equals(Material.GOLD_LEGGINGS) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_LEGGINGS)
                )   {
            return true;
        }   else return false;
    }
    
    public boolean isBoots()   {
        if(ITEM_MATERIAL.equals(Material.LEATHER_BOOTS) ||
                ITEM_MATERIAL.equals(Material.IRON_BOOTS) ||
                ITEM_MATERIAL.equals(Material.GOLD_BOOTS) ||
                ITEM_MATERIAL.equals(Material.DIAMOND_BOOTS)
                )   {
            return true;
        }   else return false;
    }
    
    public boolean isShield()   {
        if(ITEM_MATERIAL.equals(Material.SHIELD)
                )   {
            return true;
        }   else return false;
    }
    
    public int getStamina() {
        return STAMINA;
    }
    
    public int getStrength()    {
        return STRENGTH;
    }
    
    public int getAgility() {
        return AGILITY;
    }
    
    public int getIntelligence()    {
        return INTELLIGENCE;
    }
    
    public boolean hasStats()   {
        if(STAMINA > 0 || AGILITY > 0 || STRENGTH > 0 || INTELLIGENCE > 0)  {
            return true;
        }
        return false;
    }
    
    public double getMinDamage()    {
        return MIN_DAMAGE;
    }
    
    public double getMaxDamage()    {
        return MAX_DAMAGE;
    }
    
    public int getArmor()   {
        return ARMOR;
    }
    
    public int getItemId()  {
        return ITEM_ID;
    }
    
    public List<String> generateItemLore()  {
        List<String> g = new ArrayList<>();
        g.add(getQualityColored() + ChatColor.GRAY + " " + ITEM_TYPE);
        if(isWeapon())  {
            g.add("");
            g.add(ChatColor.GOLD + "Combat Stats");
            g.add(ChatColor.GRAY + String.valueOf(getMinDamage()) + "-" + String.valueOf(getMaxDamage()) + " Damage");
        }
        if(isHelm() || isChestplate() || isLeggings() || isBoots() || isShield()) {
            g.add("");
            g.add(ChatColor.GOLD + "Armor Stats");
            g.add(ChatColor.GRAY + String.valueOf(getArmor()) + " Armor");
        }
        if(hasStats())  {
            g.add("");
            g.add(ChatColor.GOLD + "Item Stats");
            if(getStamina() > 0) {
                g.add(ChatColor.AQUA + "+" +getStamina()+" Stamina");
            }
            if(getStrength() > 0) {
                g.add(ChatColor.RED + "+" +getStrength()+" Strength");
            }
            if(getAgility() > 0) {
                g.add(ChatColor.YELLOW + "+" +getAgility()+" Agility");
            }
            if(getIntelligence() > 0) {
                g.add(ChatColor.BLUE + "+" +getIntelligence()+" Intelligence");
            }
        }
        if(LEVEL_REQUIRED>0)    {
            g.add("");
            g.add(ChatColor.RED + "Level Required: "+ChatColor.GRAY +LEVEL_REQUIRED);
        }
        if(CLASS_REQUIRED>0)    {
            g.add(ChatColor.RED + "Requires Class: "+ getClassRequiredString());
        }
        g.add("");
        g.add(ChatColor.DARK_GRAY + String.valueOf(ITEM_ID));
        return g;
    }
    
    public String getQuality()  {
        switch (ITEM_QUALITY)    {
            case 0: return "Common";
            case 1: return "Uncommon";
            case 2: return "Rare";
            case 3: return "Epic";
            case 4: return "Legendary";
            case 5: return "Artifact";
            case 6: return "Quest Item";
    }
        return null;
    }
    
    public String getQualityColored()  {
        switch (ITEM_QUALITY)    {
            case 0: return ChatColor.WHITE + "Common";
            case 1: return ChatColor.GREEN + "Uncommon";
            case 2: return ChatColor.BLUE + "Rare";
            case 3: return ChatColor.LIGHT_PURPLE + "Epic";
            case 4: return ChatColor.GOLD + "Legendary";
            case 5: return ChatColor.YELLOW + "Artifact";
            case 6: return ChatColor.AQUA + "Quest Item";
    }
        return null;
    }
    
    public String getClassRequiredString()    {
        switch (CLASS_REQUIRED) {
            case 0: return ChatColor.GRAY + "Warrior";
            case 1: return ChatColor.YELLOW + "Rogue";
            case 2: return ChatColor.BLUE + "Mage";
            case 3: return ChatColor.LIGHT_PURPLE + "Paladin";
            case 4: return ChatColor.WHITE + "Priest";
        }
        return null;
    }
    
    public ChatColor getQualityColor()  {
        switch (ITEM_QUALITY)    {
            case 0: return ChatColor.WHITE;
            case 1: return ChatColor.GREEN;
            case 2: return ChatColor.BLUE;
            case 3: return ChatColor.LIGHT_PURPLE;
            case 4: return ChatColor.GOLD;
            case 5: return ChatColor.YELLOW;
            case 6: return ChatColor.AQUA;
    }
        return null;
    }

    public void setItemName(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    public void setItemType(String ITEM_TYPE) {
        this.ITEM_TYPE = ITEM_TYPE;
    }
    
    public void setItemId(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public void setItemLevel(int ITEM_LEVEL) {
        this.ITEM_LEVEL = ITEM_LEVEL;
    }

    public void setItemMaterial(Material ITEM_MATERIAL) {
        this.ITEM_MATERIAL = ITEM_MATERIAL;
    }

    public void setItemQuality(int ITEM_QUALITY) {
        this.ITEM_QUALITY = ITEM_QUALITY;
    }

    public void setMinDamage(double MIN_DAMAGE) {
        this.MIN_DAMAGE = MIN_DAMAGE;
    }

    public void setMaxDamage(double MAX_DAMAGE) {
        this.MAX_DAMAGE = MAX_DAMAGE;
    }

    public void setArmor(int ARMOR) {
        this.ARMOR = ARMOR;
    }

    public void setStamina(int STAMINA) {
        this.STAMINA = STAMINA;
    }

    public void setStrength(int STRENGTH) {
        this.STRENGTH = STRENGTH;
    }

    public void setAgility(int AGILITY) {
        this.AGILITY = AGILITY;
    }

    public void setIntelligence(int INTELLIGENCE) {
        this.INTELLIGENCE = INTELLIGENCE;
    }
    
    public void setLevelRequired(int LEVEL_REQUIRED)    {
        this.LEVEL_REQUIRED = LEVEL_REQUIRED;
    }
    
    public int getClassRequired()   {
        return this.CLASS_REQUIRED;
    }
    
    public void setClassRequired(int i)  {
        this.CLASS_REQUIRED = i;
    }
}

