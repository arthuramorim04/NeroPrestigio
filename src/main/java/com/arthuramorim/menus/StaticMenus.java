package com.arthuramorim.menus;

import com.arthuramorim.enginers.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.ItemShop;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class StaticMenus {

    public static String titlePrestigeShop = StringColor.color("Prestigio Shop");
    public static String titleInv;
    public static Inventory prestigeShopMenu = Bukkit.createInventory(null, 9 * 6, titlePrestigeShop);

    public static Inventory createShopInv(ArrayList<InvShop> invShop) {

        for (InvShop shop : LoadInvAndItems.getArrayShopInvs()) {
            if ((shop.getSlot() > 9 && shop.getSlot() < 17) ||
                    (shop.getSlot() > 18 && shop.getSlot() < 25) ||
                    (shop.getSlot() > 27 && shop.getSlot() < 35) ||
                    (shop.getSlot() > 36 && shop.getSlot() < 44)) {

                prestigeShopMenu.setItem(shop.getSlot(), shop.getIconeInv());

            } else {
                System.out.println("nao foi possivel setar a categoria: " + shop.getName());
                System.out.println("Motivo: A categoria nao pode estar no slot " + shop.getSlot());
            }
        }

        for (int i = 0; i < prestigeShopMenu.getSize(); i++) {


            if (prestigeShopMenu.getItem(i) == null) {
                if (i == 0 || i == 8 || i == 45 || i == 53) {
                    prestigeShopMenu.setItem(i, Menus.seaLantern.build());
                    continue;
                }
                if (i == 1 || i == 7 || i == 9 || i == 17 || i == 36 || i == 44 || i == 46 || i == 52) {
                    prestigeShopMenu.setItem(i, Menus.web.build());
                    continue;
                }
                if (i == 49) {
                    prestigeShopMenu.setItem(i, Menus.arrow.build());
                    continue;
                }
                if (prestigeShopMenu.getItem(i) == null) {
                    prestigeShopMenu.setItem(i, Menus.backgroudPanel.build());
                }

            }

        }
        return prestigeShopMenu;
    }

    public static Inventory createCategoryShopInv(ItemStack item) {

        Inventory category = null;



        for (InvShop inv : LoadInvAndItems.getArrayShopInvs()) {
            if (MakeItem.checkIsSimilar(inv.getIconeInv(),item)) {
                titleInv = StringColor.color("&bCategoria: " + inv.getName());
                category = Bukkit.createInventory(null, 9 * 6, titleInv);


                for (int i = 0; i < 44;i++) {
                    ItemShop itemShop = inv.getItemsVenda().get(i);
                    if(itemShop != null){
                    if ((itemShop.getSlot() > 9 && itemShop.getSlot() < 17) ||
                            (itemShop.getSlot() > 18 && itemShop.getSlot() < 25) ||
                            (itemShop.getSlot() > 27 && itemShop.getSlot() < 35) ||
                            (itemShop.getSlot() > 36 && itemShop.getSlot() < 44)) {

                        Integer slot = itemShop.getSlot();
                        ItemStack itemClone = itemShop.getItems().clone();

                        if(itemClone.getItemMeta().hasLore()){

                            ItemMeta metaItemClone = itemClone.getItemMeta();
                            List<String> lore = metaItemClone.getLore();
                            lore.add(StringColor.color(""));
                            lore.add(StringColor.color("&eValor: &f" + itemShop.getPrice()));
                            metaItemClone.setLore(lore);
                            itemClone.setItemMeta(metaItemClone);
                        }

                        category.setItem(itemShop.getSlot(), itemClone);
                        System.out.println(itemShop.getItems().getItemMeta().getDisplayName() + "   slot: " + itemShop.getSlot() + "lore" + itemClone.getItemMeta().getLore());

                    } else {
                        System.out.println("nao foi possivel setar a item: " + itemShop.getItems().getItemMeta().getDisplayName());
                        System.out.println("Motivo: O item que voce tentou setar esta em um slot nao permitido.");
                    }}
                    continue;
                }

                for (int i = 0; i < category.getSize(); i++) {


                    if (category.getItem(i) == null) {
                        if (i == 0 || i == 8 || i == 45 || i == 53) {
                            category.setItem(i, Menus.seaLantern.build());
                            continue;
                        }
                        if (i == 1 || i == 7 || i == 9 || i == 17 || i == 36 || i == 44 || i == 46 || i == 52) {
                            category.setItem(i, Menus.web.build());
                            continue;
                        }
                        if (i == 49) {
                            category.setItem(i, Menus.arrow.build());
                            continue;
                        }
                        if (category.getItem(i) == null) {
                            category.setItem(i, Menus.backgroudPanel.build());
                        }

                    }

                }
                break;
            }
        }
        return category;
    }

}
