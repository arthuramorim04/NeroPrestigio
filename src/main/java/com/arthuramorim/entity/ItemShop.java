package com.arthuramorim.entity;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemShop {

    private ItemStack items;
    private String name;
    private Integer slot;
    private Integer price;
    private List<String> cmds;
    private Boolean onlyCmd;

    public ItemShop(String name, ItemStack items, Integer slot, Integer price, List<String> cmds, Boolean onlyCmd) {
        this.name = name;
        this.items = items;
        this.slot = slot;
        this.price = price;
        this.cmds = cmds;
        this.onlyCmd = onlyCmd;
    }


    public ItemStack getItems() {
        return this.items;
    }


    public Integer getSlot() {
        return this.slot;
    }


    public Integer getPrice() {
        return this.price;
    }

    public List<String> getCmds() {
        return cmds;
    }

    public  void setCmds(List<String> cmds) {
        this.cmds = cmds;
    }

    public String getName() {
        return name;
    }

    public Boolean getOnlyCmd() {
        return onlyCmd;
    }

    public Boolean onlyCommand(){
        return this.getOnlyCmd();
    }

    public void executCommands(String name){
        for(String cmd : getCmds()){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", name));
        }
    }

    @Override
    public String toString() {
        return "ItemShop{ " + items.getItemMeta().toString() + " , slot: " + this.getSlot() + "}";
    }
}
