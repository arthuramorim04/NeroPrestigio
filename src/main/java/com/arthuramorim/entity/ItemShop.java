package com.arthuramorim.entity;

import org.bukkit.inventory.ItemStack;

public class ItemShop {

    private static ItemStack items;
    private static Integer slot;
    private static Integer price;

    public ItemShop(ItemStack items, Integer slot, Integer price) {
        this.items = items;
        this.slot = slot;
        this.price = price;
    }


    public ItemStack getItems() {
        return items;
    }

    public void setItems(ItemStack items) {
        this.items = items;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public static Integer getPrice() {
        return price;
    }

    public static void setPrice(Integer price) {
        ItemShop.price = price;
    }
}
