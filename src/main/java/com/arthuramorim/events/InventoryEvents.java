package com.arthuramorim.events;

import com.arthuramorim.Main;
import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.menus.Menus;
import com.arthuramorim.menus.StaticMenus;
import com.arthuramorim.utils.Configs;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
                if(MakeItem.checkIsSimilar(e.getCurrentItem(),Menus.prestige.build())){
                    if(p.hasPermission("nero.prestigio")){
                        for(PrestigePlayer player : Main.getArrayPlayer()){
                            if(player.getUuid().equals(p.getUniqueId())){
                                if(player.addPrestige()){

                                    commandResetRankPlayer(p);
                                    p.closeInventory();
                                    p.playSound(p.getLocation(),Sound.ENDERDRAGON_DEATH,1f,1f);
                                    p.sendMessage(StringColor.color("\n&aVoce acabou de fazer prestigio! Parabens!\n"));

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


    protected static void commandResetRankPlayer(Player p){

        String commandResetGroup = Configs.getConfigFile().getString("resetCommand").replace("%player%",p.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandResetGroup);
        p.sendMessage(StringColor.color("&aVoce foi enviado para o primeiro rank novamente. Boa Sorte!"));

    }

}
