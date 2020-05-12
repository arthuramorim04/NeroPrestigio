package com.arthuramorim.menus;

import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class StaticMenus {

    public static Inventory prestigeShopMenu = Bukkit.createInventory(null,9*6, StringColor.color("&bPrestigio Shop"));
    public static Inventory comum = Bukkit.createInventory(null,9*6, StringColor.color("&bPrestigio Shop"));
    public static Inventory rare = Bukkit.createInventory(null,9*6, StringColor.color("&bPrestigio Shop"));
    public static Inventory epic = Bukkit.createInventory(null,9*6, StringColor.color("&bPrestigio Shop"));



    public static Inventory createInventory(ArrayList<InvShop> invShop){

        for(InvShop shop : LoadInvAndItems.getShopHash()){
            if((shop.getSlot() > 9 && shop.getSlot() > 17) ||
                    (shop.getSlot() > 18 && shop.getSlot() > 25) ||
                    (shop.getSlot() > 27 && shop.getSlot() > 35)  ||
                    (shop.getSlot() > 36 && shop.getSlot() > 44)){

                prestigeShopMenu.setItem(shop.getSlot(),shop.getIconeInv());

            }else {
                System.out.println("nao foi possivel setar a categoria: " + shop.getName());
                System.out.println("Motivo: A categoria so pode estar nos slots 29,31 ou 33 e foi tentado setar o item no slot " + shop.getSlot());
            }
        }

        for(int i = 0; i < prestigeShopMenu.getSize();i++){


            if(prestigeShopMenu.getItem(i) == null){
                if(i == 0 || i == 8 || i == 45 || i == 53) {
                    prestigeShopMenu.setItem(i,Menus.seaLantern.build());
                    continue;
                }
                if(i == 1 || i == 7 || i == 9 || i == 17 || i == 36 || i == 44 || i == 46 || i == 52){
                    prestigeShopMenu.setItem(i, Menus.web.build());
                    continue;
                }
                if(i == 49){
                    prestigeShopMenu.setItem(i, Menus.arrow.build());
                    continue;
                }
                if(prestigeShopMenu.getItem(i) == null){
                    prestigeShopMenu.setItem(i, Menus.backgroudPanel.build());
                }

            }

        }
        return prestigeShopMenu;
    }

}
