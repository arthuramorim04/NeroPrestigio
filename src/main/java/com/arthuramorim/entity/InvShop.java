package com.arthuramorim.entity;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InvShop {

    private ItemStack iconeInv;
    private String name;
    private Integer slot;
    private HashMap<Integer,ItemShop> itemsVenda;

    public ItemStack getIconeInv() {
        return iconeInv;
    }

    public void setIconeInv(ItemStack iconeInv) {
        this.iconeInv = iconeInv;
    }

    public HashMap<Integer,ItemShop> getItemsVenda() {
        return itemsVenda;
    }

    public void setItemsVenda(HashMap<Integer,ItemShop> itemsVenda) {
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

    @Override
    public String toString() {
        return "InvShop{" +
                "iconeInv=" + iconeInv +
                ", name='" + name + '\'' +
                ", slot=" + slot +
                ", itemsVenda=" + itemsVenda +
                '}';
    }
}
