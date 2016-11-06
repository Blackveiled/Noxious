/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.Noxi.events;

import com.blackveiled.Noxi.items.NoxiItem;
import com.blackveiled.Noxi.items.player.Player;
import com.blackveiled.Noxi.main;
import java.sql.SQLException;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author Blackveiled
 */
public class PlayerEquipEvent implements Listener {
    
    @EventHandler
    public void PlayerJoinEvent2(PlayerJoinEvent e)  {
        try {
        main.database.getConnection();
        if(main.database.getRowCount("players", "uuid", e.getPlayer().getUniqueId().toString()) == 0)    {
            main.database.createUser(e.getPlayer().getUniqueId().toString(), e.getPlayer().getDisplayName());
        }
        }
        catch(SQLException e2)   {
            e2.printStackTrace();
        }
        finally {
            if(!main.NoxiPlayers.containsKey(e.getPlayer().getUniqueId()))   {
                Player newplayer = new Player(e.getPlayer().getUniqueId(), e.getPlayer().getName());
                main.NoxiPlayers.put(e.getPlayer().getUniqueId(), newplayer);
                e.getPlayer().getInventory().addItem(main.NoxiItems.get(5).getItemStack());
            }
        }
    }
    
    @EventHandler
    public void playerPickupItemEvent(PlayerItemHeldEvent e) {
        if(main.NoxiPlayers.containsKey(e.getPlayer().getUniqueId()))   {
            Player playerData = main.NoxiPlayers.get(e.getPlayer().getUniqueId());
            ItemStack previous = null;
            ItemStack nextslot = null;
            if(e.getPlayer().getInventory().getItem(e.getPreviousSlot()) != null)   {
            previous = e.getPlayer().getInventory().getItem(e.getPreviousSlot());
            }
            if(e.getPlayer().getInventory().getItem(e.getNewSlot()) != null)    {
            nextslot = e.getPlayer().getInventory().getItem(e.getNewSlot());
            }
            if(previous != null) {
                if(itemExists(previous))    {
                    NoxiItem item = getItem(previous);
                    playerData.unequipMainHand(item.getItemId());
                }
            }
            if(nextslot != null) {
                if(itemExists(nextslot))    {
                    NoxiItem item = getItem(nextslot);
                    playerData.equipMainHand(item.getItemId());
                }
            }
            
            main.NoxiPlayers.put(e.getPlayer().getUniqueId(), playerData);
        }
    }
    
    public boolean itemExists(ItemStack i)    {
        if(i.hasItemMeta()) {
            if(i.getItemMeta().hasLore())   {
                String id = i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() -1);
                id = ChatColor.stripColor(id);
                int item = Integer.parseInt(id);
                if(main.NoxiItems.containsKey(item))    {
                    return true;
                }
            }
        }
        return false;
    }
    
    public NoxiItem getItem(ItemStack i)    {
        if(i.hasItemMeta()) {
            if(i.getItemMeta().hasLore())   {
                String id = i.getItemMeta().getLore().get(i.getItemMeta().getLore().size() -1);
                id = ChatColor.stripColor(id);
                int item = Integer.parseInt(id);
                if(main.NoxiItems.containsKey(item))    {
                    return main.NoxiItems.get(item);
                }
            }
        }
        return null;
    }
}
