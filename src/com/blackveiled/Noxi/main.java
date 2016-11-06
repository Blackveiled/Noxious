/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.Noxi;

import com.blackveiled.Noxi.database.Database;
import com.blackveiled.Noxi.events.PlayerEquipEvent;
import com.blackveiled.Noxi.items.NoxiItem;
import com.blackveiled.Noxi.items.player.Player;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Blackveiled
 */
public class main extends JavaPlugin {
    
    public static HashMap<Integer, NoxiItem> NoxiItems = new HashMap<>();
    public static HashMap<UUID, Player> NoxiPlayers = new HashMap<>();
    public static Database database = new Database();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    @Override
    public void onEnable()  {
        try {
        database.getConnection();
        database.generateNoxiItems();
        }   catch(SQLException e)   {
            e.printStackTrace();
        }
        finally {
            this.getServer().getPluginManager().registerEvents(new PlayerEquipEvent(), this);
        }
    }
    
    @Override
    public void onDisable()  {
        
    }
    
}
