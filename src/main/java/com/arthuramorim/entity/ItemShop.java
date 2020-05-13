package com.arthuramorim.entity;

import org.bukkit.inventory.ItemStack;

public class ItemShop {

    private static ItemStack items;
    private static String name;
    private static Integer slot;
    private static Integer price;

    public ItemShop(String name,ItemStack items, Integer slot, Integer price) {
        this.name = name;
        this.items = items;
        this.slot = slot;
        this.price = price;
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


    @Override
    public String toString() {
        return "ItemShop{ "+ items.getItemMeta().toString() +" , slot: "+this.getSlot()+"}";
    }
}
