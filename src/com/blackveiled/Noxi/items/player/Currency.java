/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackveiled.Noxi.items.player;

/**
 *
 * @author Blackveiled
 */
public class Currency {
    
    private int GOLD = 150;
    private int PLATINUM = 100;
    
    public int getGold()    {
        return GOLD;
    }
    
    public int getPlatinum()    {
        return PLATINUM;
    }
    
    public void setGold(int i)  {
        GOLD = i;
    }
    
    public void setPlatinum(int i)  {
        PLATINUM = i;
    }
    
    public void addPlatinum(int i) {
        PLATINUM = PLATINUM + i;
    }
    
    public void addGold(int i)  {
        GOLD = GOLD + i;
    }
    
    public boolean takeGold(int i) {
        if(GOLD < i)    {
            return false;
        }   else    {
            GOLD = GOLD - i;
            return true;
        }
    }
    
    public boolean takePlatinum(int i) {
        if(PLATINUM < i)    {
            return false;
        }   else    {
            PLATINUM = PLATINUM - i;
            return true;
        }
    }
}
