package com.arthuramorim.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MakeItem {

    private ItemStack ik;
    private static Field profileField;

    public MakeItem(Material material) {
        this.ik = new ItemStack(material);
    }

    public MakeItem(Material material, byte data) {
        this.ik = new ItemStack(material, 1, data);
    }


    public MakeItem addEnchantment(Enchantment enchant, int level) {
        this.ik.addUnsafeEnchantment(enchant, level);
        return this;
    }


    @SuppressWarnings("deprecation")
    public MakeItem(int material, byte data) {
        this.ik = new ItemStack(material, 1, data);
    }

    public MakeItem(ItemStack ik) {
        this.ik = ik.clone();
    }

    @SuppressWarnings("deprecation")
    public MakeItem(int material) {
        this.ik = new ItemStack(material);
    }

    @Deprecated
    public MakeItem setAmout(int amount) {
        this.ik.setAmount(amount);
        return this;
    }

    public MakeItem setAmount(int amount) {
        this.ik.setAmount(amount);
        return this;
    }

    public MakeItem setType(Material type) {
        this.ik.setType(type);
        return this;
    }

    public MakeItem setName(String name) {
        ItemMeta im = this.ik.getItemMeta();
        im.setDisplayName(StringColor.color(name));
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem setDurability(Short durability) {
        ik.setDurability(durability);
        return this;
    }

    public MakeItem addGlow() {
        Glow glow = new Glow();
        ItemMeta itemMeta = this.ik.getItemMeta();
        itemMeta.addEnchant(glow, 1, true);
        this.ik.setItemMeta(itemMeta);

        return this;
    }

    public MakeItem setColor(Color color) {
        try {
            LeatherArmorMeta meta = (LeatherArmorMeta) this.ik.getItemMeta();
            meta.setColor(color);
            this.ik.setItemMeta(meta);
        } catch (Exception localException) {
        }
        return this;
    }

    public MakeItem setLore(ArrayList<String> lore) {
        ItemMeta im = this.ik.getItemMeta();
        ArrayList<String> lorer = new ArrayList<String>();
        for (String r : lore) {
            lorer.add(StringColor.color(r));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLore(ArrayList<String> lore, ChatColor color) {
        ItemMeta im = this.ik.getItemMeta();
        ArrayList<String> lorer = new ArrayList<String>();
        for (String r : lore) {
            lorer.add(color + StringColor.color(r));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLoreList(String... name) {
        String[] arrayOfString;
        int j = (arrayOfString = name).length;
        for (int i = 0; i < j; i++) {
            String s = arrayOfString[i];
            addLore(s);
        }
        return this;
    }

    public MakeItem addLore(ArrayList<String> lore) {
        ItemMeta im = this.ik.getItemMeta();
        ArrayList<String> lorer = new ArrayList<String>();
        for (String r : lore) {
            lorer.add(StringColor.color(r));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLore(String lore) {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) {
            lorer = im.getLore();
        }
        if (lore.contains("/n")) {
            String[] arrayOfString;
            int j = (arrayOfString = lore.split("/n")).length;
            for (int i = 0; i < j; i++) {
                String x = arrayOfString[i];
                lorer.add(StringColor.color(x));
            }
        } else {
            lorer.add(StringColor.color(lore));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem remLore(int amount) {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) {
            lorer = im.getLore();
        }
        for (int i = 0; i < amount; i++) {
            if (!lorer.isEmpty()) {
                lorer.remove(lorer.size() - 1);
            }
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem removeLoreCmd() {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) lorer = im.getLore();
        if (lorer.isEmpty()) return this;
        for (int i = 0; i < lorer.size(); i++) {
            if(lorer.get(i).startsWith("cmd:")) lorer.remove(i);
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLore(String[] lore) {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) {
            lorer = im.getLore();
        }
        String[] arrayOfString;
        int j = (arrayOfString = lore).length;
        for (int i = 0; i < j; i++) {
            String x = arrayOfString[i];
            lorer.add(StringColor.color(x));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public ItemStack build() {
        return this.ik;
    }

    public static boolean checkIsSimilar(ItemStack ik1, ItemStack ik2) {
        if (ik1 == null)
            return false;
        if (ik2 == null)
            return false;
        if (ik1.getType() == ik2.getType()) {
            if (ik1.hasItemMeta() && ik2.hasItemMeta()) {
                if (ik1.getItemMeta().hasDisplayName() && ik2.getItemMeta().hasDisplayName()) {
                    if (ik1.getItemMeta().getDisplayName().equals(ik2.getItemMeta().getDisplayName())) {
                        if (ik1.getItemMeta().hasLore() && ik2.getItemMeta().hasLore()) {
                            if (ik1.getItemMeta().getLore().equals(ik2.getItemMeta().getLore())) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkIsSimilar(ItemStack ik1, ItemStack ik2, boolean use_lore) {
        if (ik1 == null)
            return false;
        if (ik2 == null)
            return false;
        if (ik1.getType() == ik2.getType()) {
            if (ik1.hasItemMeta() && ik2.hasItemMeta()) {
                if (ik1.getItemMeta().hasDisplayName() && ik2.getItemMeta().hasDisplayName()) {
                    if (ik1.getItemMeta().getDisplayName().equals(ik2.getItemMeta().getDisplayName())) {
                        if (use_lore) {
                            if (ik1.getItemMeta().hasLore() && ik2.getItemMeta().hasLore()) {
                                if (ik1.getItemMeta().getLore().equals(ik2.getItemMeta().getLore())) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    private static class Glow extends Enchantment {
        public Glow() {
            super(200);
        }
        public boolean canEnchantItem(ItemStack i) {
            return false;
        }
        public boolean conflictsWith(Enchantment e) {
            return false;
        }
        public EnchantmentTarget getItemTarget() {
            return null;
        }
        public int getMaxLevel() {
            return 1;
        }
        public String getName() {
            return "Glow I";
        }
        public int getStartLevel() {
            return 1;
        }
    }
}
