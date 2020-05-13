package com.arthuramorim.menus;

import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.ItemShop;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class StaticMenus {

    public static String titlePrestigeShop = StringColor.color("Prestigio Shop");
    public static String titleInv;
    public static Inventory prestigeShopMenu = Bukkit.createInventory(null, 9 * 6, titlePrestigeShop);

    public static Inventory createShopInv(ArrayList<InvShop> invShop) {

        for (InvShop shop : LoadInvAndItems.getArrayShopInvs()) {
            if ((shop.getSlot() > 9 && shop.getSlot() > 17) ||
                    (shop.getSlot() > 18 && shop.getSlot() > 25) ||
                    (shop.getSlot() > 27 && shop.getSlot() > 35) ||
                    (shop.getSlot() > 36 && shop.getSlot() > 44)) {

                prestigeShopMenu.setItem(shop.getSlot(), shop.getIconeInv());

            } else {
                System.out.println("nao foi possivel setar a categoria: " + shop.getName());
                System.out.println("Motivo: A categoria so pode estar nos slots 29,31 ou 33 e foi tentado setar o item no slot " + shop.getSlot());
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


                for (ItemShop itemShop : inv.getItemsVenda()) {
                    if ((itemShop.getSlot() > 9 && itemShop.getSlot() > 17) ||
                            (itemShop.getSlot() > 18 && itemShop.getSlot() > 25) ||
                            (itemShop.getSlot() > 27 && itemShop.getSlot() > 35) ||
                            (itemShop.getSlot() > 36 && itemShop.getSlot() > 44)) {

                        Integer slot = itemShop.getSlot();
                        category.setItem(itemShop.getSlot(), itemShop.getItems());
                        System.out.println(itemShop.getItems().getItemMeta().getDisplayName() + "   slot: " + itemShop.getSlot());

                    } else {
                        System.out.println("nao foi possivel setar a item: " + itemShop.getItems().getItemMeta().getDisplayName());
                        System.out.println("Motivo: O item que voce tentou setar esta em um slot nao permitido.");
                    }
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
