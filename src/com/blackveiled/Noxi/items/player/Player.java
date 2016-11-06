/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.Noxi.items.player;

import com.blackveiled.Noxi.items.NoxiItem;
import com.blackveiled.Noxi.main;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.PlayerInventory;

/**
 *
 * @author Blackveiled
 */
public class Player {
    
    // Player Information
    private UUID PLAYER_UUID;
    private String PLAYER_NAME;
    private Currency PLAYER_CURRENCY = new Currency();
    
    // Player Stats
    // 0 = Warrior, 1 = Rogue, 2 = Mage, 3 = Paladin, 4 = Priest
    private int PLAYER_CLASS = 0;
    private int PLAYER_LEVEL = 1;
    private int PLAYER_EXPERIENCE = 0;
    private int PLAYER_STAMINA = 20;
    private int PLAYER_STRENGTH = 0;
    private int PLAYER_AGILITY = 0;
    private int PLAYER_INTELLIGENCE = 0;
    private double PLAYER_MIN_DAMAGE = this.getBaseMinDamage();
    private double PLAYER_MAX_DAMAGE = this.getBaseMaxDamage();
    private double PLAYER_CURRENT_HEALTH;
    
    // Player Checks
    private boolean PLAYER_WEAPON_EQUIPPED = false;
    
    // Player Equipment
    
    private NoxiItem MAIN_HAND;
    private NoxiItem OFF_HAND;
    private NoxiItem HELM;
    private NoxiItem CHESTPLATE;
    private NoxiItem LEGGINGS;
    private NoxiItem BOOTS;
    
    public Player(UUID uuid, String name) {
        this.PLAYER_UUID = uuid;
        this.PLAYER_NAME = name;
    }
    
    public int getPlayerLevel() {
        return PLAYER_LEVEL;
    }
    
    public int getPlayerExperience()    {
        return PLAYER_EXPERIENCE;
    }
    
    public int getExperienceRequired()  {
        return 0;
    }
    
    public int getPlayerClass() {
        return PLAYER_CLASS;
    }
    
    public double getMinimumDamage()    {
        return PLAYER_MIN_DAMAGE;
    }
    
    public double getMaximumDamage()    {
        return PLAYER_MAX_DAMAGE;
    }
    
    public int getAgility() {
        return PLAYER_AGILITY;
    }
    
    public int getStamina() {
        return PLAYER_STAMINA;
    }
    
    public int getStrength()    {
        return PLAYER_STRENGTH;
    }
    
    public int getIntelligence()    {
        return PLAYER_INTELLIGENCE;
    }
    
    public Currency getCurrency()   {
        return PLAYER_CURRENCY;
    }
    
    public boolean equipMainHand(int i) {
        if(main.NoxiItems.containsKey(i))   {
            NoxiItem item = main.NoxiItems.get(i);
            if(item.isWeapon())    {
                this.PLAYER_STAMINA = PLAYER_STAMINA + item.getStamina();
                this.PLAYER_AGILITY = PLAYER_AGILITY + item.getAgility();
                this.PLAYER_STRENGTH = PLAYER_STRENGTH + item.getStrength();
                this.PLAYER_INTELLIGENCE = PLAYER_INTELLIGENCE + item.getIntelligence();
                Bukkit.getPlayer(PLAYER_UUID).sendMessage(ChatColor.GREEN + "Weapon equipped!");
                return true;
            }
        }   else    {
            MAIN_HAND = null;
            return false;
        }
        return false;
    }
    
    public boolean unequipMainHand(int i) {
        if(main.NoxiItems.containsKey(i))   {
            NoxiItem item = main.NoxiItems.get(i);
            if(item.isWeapon())    {
                this.MAIN_HAND = item;
                this.PLAYER_STAMINA = PLAYER_STAMINA - item.getStamina();
                this.PLAYER_AGILITY = PLAYER_AGILITY - item.getAgility();
                this.PLAYER_STRENGTH = PLAYER_STRENGTH - item.getStrength();
                this.PLAYER_INTELLIGENCE = PLAYER_INTELLIGENCE - item.getIntelligence();
                
                return true;
            }
        }   else    {
            MAIN_HAND = null;
            return false;
        }
        return false;
    }
    
    public double getBaseMinDamage()   {
        return 1*PLAYER_LEVEL;
    }
    
    public double getBaseMaxDamage()    {
        return (1*PLAYER_LEVEL) + (0.5*PLAYER_LEVEL)+2;
    }
    
    public NoxiItem getMainHand()   {
        return MAIN_HAND;
    }
    
    public NoxiItem getOffHand()   {
        return OFF_HAND;
    }
    
    public NoxiItem getHelm()   {
        return HELM;
    }
    
    public NoxiItem getChestplate()   {
        return CHESTPLATE;
    }
    
    public NoxiItem getLeggings()   {
        return LEGGINGS;
    }
    
    public NoxiItem getBoots()   {
        return BOOTS;
    }
}
