package com.arthuramorim.events;

import com.arthuramorim.Main;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.menus.Menus;
import com.arthuramorim.menus.StaticMenus;
import com.arthuramorim.utils.MakeItem;
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


        //inv principal prestigio
        if(e.getInventory().getTitle().contains("Prestigio")){
            if (e.getCurrentItem() == null) return;
            e.setCancelled(true);
            if(e.getClick().isLeftClick()){
                if(e.getCurrentItem().isSimilar(Menus.prestige.build())){
                    if(p.hasPermission("nero.prestigio")){
                        for(PrestigePlayer player : Main.getArrayPlayer()){
                            if(player.getUuid().equals(p.getUniqueId())){
                                if(player.addPrestige()){
                                    p.sendMessage(StringColor.color("&aVoce acabou de fazer prestigio! Parabens!"));
                                    p.sendMessage(StringColor.color("&aVoce recebeu +1 de prestigio totalizando."));
                                    p.sendMessage(StringColor.color("&aVoce recebeu +190909 pontos de prestigio."));
                                    //criar metodo para executar comando e resetar o rank do player(comando da config)
                                }else{
                                    p.sendMessage(StringColor.color("&cOcorreu algum erro e nao foi possivel realizar o prestigio, entre em contato com um staff"));
                                }
                            }
                        }

                        e.setCancelled(true);
                    }else{
                        p.sendMessage(StringColor.color("&cVoce precisa estar no ultimo rank para fazer um prestigio."));
                        e.setCancelled(true);
                        p.closeInventory();
                    }
                }
                if(e.getCurrentItem().isSimilar(Menus.prestigeShop.build())){
                    p.openInventory(StaticMenus.createShopInv(LoadInvAndItems.getArrayShopInvs()));
                }
                if(e.getCurrentItem().isSimilar(Menus.arrow.build())){
                    p.closeInventory();
                }
            }else{
                e.setCancelled(true);
                return;
            }
        }
        //inv menu shop categorias
        if(e.getInventory().getTitle().equals(StaticMenus.titlePrestigeShop)){
            if (e.getCurrentItem() == null) return;
            e.setCancelled(true);
            if(e.getClick().isLeftClick()){
                if(!(e.getCurrentItem().isSimilar(Menus.arrow.build()) || e.getCurrentItem().isSimilar(Menus.backgroudPanel.build()) ||
                        e.getCurrentItem().isSimilar(Menus.seaLantern.build()) || e.getCurrentItem().isSimilar(Menus.web.build()))){
                    p.openInventory(StaticMenus.createCategoryShopInv(e.getCurrentItem()));
                }
                if(e.getCurrentItem().isSimilar(Menus.arrow.build())){
                    p.closeInventory();
                }
            }else{
                e.setCancelled(true);
                return;
            }
        }
        //invs categorias
        if(e.getInventory().getTitle().equals(StaticMenus.titleInv)){
            e.setCancelled(true);
            if(e.getCurrentItem().isSimilar(Menus.arrow.build())){
                p.closeInventory();
            }
        }else{
            e.setCancelled(true);
            return;
        }
    }

}
