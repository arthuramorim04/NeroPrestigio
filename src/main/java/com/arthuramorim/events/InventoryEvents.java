package com.arthuramorim.events;

import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.menus.Menus;
import com.arthuramorim.menus.StaticMenus;
import com.arthuramorim.utils.StringColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {

    @EventHandler
    public static void onOpenPrestigeInv(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();

        if(!(p instanceof Player)) return;

        if(!e.getInventory().getTitle().contains("Prestigio")) return;
        if (e.getCurrentItem() == null) return;
        e.setCancelled(true);

        if(e.getInventory().getTitle().contains("Prestigio")){
            if(e.getClick().isLeftClick()){
                if(e.getCurrentItem().isSimilar(Menus.prestige.build())){
                    if(p.hasPermission("nero.prestigio")){
                        p.sendMessage(StringColor.color("&aVoce acabou de fazer prestigio! Parabens!"));
                        p.sendMessage(StringColor.color("&aVoce recebeu +1 de prestigio totalizando."));
                        p.sendMessage(StringColor.color("&aVoce recebeu +190909 pontos de prestigio."));
                        e.setCancelled(true);
                    }else{
                        p.sendMessage(StringColor.color("&cVoce precisa estar no ultimo rank para fazer um prestigio."));
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                }
                if(e.getCurrentItem().isSimilar(Menus.prestigeShop.build())){
                    p.openInventory(StaticMenus.createInventory(LoadInvAndItems.getShopHash()));
                }
            }else{
                e.setCancelled(true);
                return;
            }
        }
    }

}
