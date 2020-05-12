package com.arthuramorim.entity;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InvShop {

    private ItemStack iconeInv;
    private String name;
    private Integer slot;
    private ArrayList<ItemShop> itemsVenda;

    public ItemStack getIconeInv() {
        return iconeInv;
    }

    public void setIconeInv(ItemStack iconeInv) {
        this.iconeInv = iconeInv;
    }

    public ArrayList<ItemShop> getItemsVenda() {
        return itemsVenda;
    }

    public void setItemsVenda(ArrayList<ItemShop> itemsVenda) {
        this.itemsVenda = itemsVenda;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
