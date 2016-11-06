/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.Noxi.database;

import com.blackveiled.Noxi.items.NoxiItem;
import com.blackveiled.Noxi.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class Database {

    public Connection connection;
    public PreparedStatement Query;
    public ResultSet Results;
    public String Hostname;
    public String Schema;
    public String Username;
    public String Password;
    public String Port;

    public Database() {
        this.Hostname = "localhost";
        this.Schema = "noxi";
        this.Username = "root";
        this.Password = "password";
        this.Port = "3306";
    }

    public Connection getConnection() throws SQLException {
        this.connection = null;
        try {
            return this.connection = DriverManager.getConnection("jdbc:mysql://" + this.Hostname + ":" + this.Port + "/" + this.Schema + "?allowMultiQueries=true", this.Username, this.Password);
        } catch (SQLException Exception) {
            Exception.printStackTrace();
            return this.connection = null;

        }
    }

    public int getRowCount(String table, String col, String equals) throws SQLException {
        if (this.connection != null) {
            this.Query = null;
            String Countfor = "SELECT COUNT(*) FROM `" + table + "` WHERE `" + col + "`='" + equals + "';";
            try {
                this.Query = this.connection.prepareStatement(Countfor);
                this.Results = this.Query.executeQuery();
                while (this.Results.next()) {
                    return this.Results.getInt("COUNT(*)");
                }
            } catch (SQLException Exception) {
                Exception.printStackTrace();
            } finally {
                this.Query.close();
            }
        } else {
            return -1;
        }
        return -1;
    }

    public int getRowCountAnd(String table, String col, String equals, String andCol, String andEquals) throws SQLException {
        if (this.connection != null) {
            this.Query = null;
            String Countfor = "SELECT COUNT(*) FROM `" + table + "` WHERE `" + col + "`='" + equals + "' AND `" + andCol + "`='" + andEquals + "';";
            try {
                this.Query = this.connection.prepareStatement(Countfor);
                this.Results = this.Query.executeQuery();
                while (this.Results.next()) {
                    return this.Results.getInt("COUNT(*)");
                }
            } catch (SQLException Exception) {
                Exception.printStackTrace();
            } finally {
                this.Query.close();
            }
        } else {
            return -1;
        }
        return -1;
    }

    public void createUser(String UUID, String username) throws SQLException {
        if (this.connection != null) {
            this.Query = null;
            String insert = "INSERT INTO `players` (`uuid`, `player_name`) VALUES('" + UUID + "', '" + username + "');";
            this.Query = this.connection.prepareStatement(insert);
            int rows = this.Query.executeUpdate();
            this.Query.close();
        }

    }
    
    public void generateNoxiItems() throws SQLException {
        if (this.connection != null) {
            this.Query = null;
            String Countfor = "SELECT * FROM `items`";
            try {
                this.Query = this.connection.prepareStatement(Countfor);
                this.Results = this.Query.executeQuery();
                while (this.Results.next()) {
                    NoxiItem item = new NoxiItem();
                    item.setItemId(this.Results.getInt("id"));
                    item.setItemName(this.Results.getString("item_name"));
                    item.setItemQuality(this.Results.getInt("item_quality"));
                    item.setItemLevel(this.Results.getInt("item_level"));
                    item.setItemMaterial(Material.valueOf(this.Results.getString("material")));
                    item.setItemType(this.Results.getString("item_type"));
                    item.setMinDamage(this.Results.getInt("min_damage"));
                    item.setMaxDamage(this.Results.getInt("max_damage"));
                    item.setArmor(this.Results.getInt("armor"));
                    item.setStamina(this.Results.getInt("stamina"));
                    item.setStrength(this.Results.getInt("strength"));
                    item.setAgility(this.Results.getInt("agility"));
                    item.setIntelligence(this.Results.getInt("intelligence"));
                    item.setLevelRequired(this.Results.getInt("level_required"));
                    main.NoxiItems.put(item.getItemId(), item);
                    System.out.println("Generated "+ this.Results.getString("item_name"));
                }
            } catch (SQLException Exception) {
                Exception.printStackTrace();
            } finally {
                this.Query.close();
            }
        }
    }

}