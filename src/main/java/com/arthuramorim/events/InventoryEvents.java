package com.arthuramorim.events;

import com.arthuramorim.Main;
import com.arthuramorim.enginers.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.menus.Menus;
import com.arthuramorim.menus.StaticMenus;
import com.arthuramorim.utils.Configs;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import com.google.gson.internal.$Gson$Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {

    @EventHandler
    public static void onOpenPrestigeInv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (!(p instanceof Player)) return;


        //inv principal prestigio
        if (e.getInventory().getTitle().contains("Prestigio")) {
            if (e.getCurrentItem() == null) return;
            e.setCancelled(true);
            if (e.getClick().isLeftClick()) {
                if (MakeItem.checkIsSimilar(e.getCurrentItem(), Menus.prestige.build())) {
                    if (p.hasPermission("nero.prestigio")) {
                        for (PrestigePlayer player : Main.getArrayPlayer()) {
                            if (player.getUuid().equals(p.getUniqueId())) {
                                if (player.addPrestige()) {

                                    commandResetRankPlayer(p);
                                    p.closeInventory();
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 1f, 1f);
                                    p.sendMessage(StringColor.color("\n&aVoce acabou de fazer prestigio! Parabens!\n"));

                                } else {
                                    p.sendMessage(StringColor.color("&cOcorreu algum erro e nao foi possivel realizar o prestigio, entre em contato com um staff"));
                                }
                            }
                        }

                        e.setCancelled(true);
                    } else {
                        p.sendMessage(StringColor.color("&cVoce precisa estar no ultimo rank para fazer um prestigio."));
                        p.closeInventory();
                    }
                }
                if (e.getCurrentItem().isSimilar(Menus.prestigeShop.build())) {
                    p.openInventory(StaticMenus.createShopInv(LoadInvAndItems.getArrayShopInvs()));
                }
                if (e.getCurrentItem().isSimilar(Menus.arrow.build())) {
                    p.closeInventory();
                }
            } else {
                e.setCancelled(true);
                return;
            }
        }
        //inv menu shop categorias
        if (e.getInventory().getTitle().equals(StaticMenus.titlePrestigeShop)) {
            if (e.getCurrentItem() == null) return;
            e.setCancelled(true);
            if (e.getClick().isLeftClick()) {
                if (!(e.getCurrentItem().isSimilar(Menus.arrow.build()) || e.getCurrentItem().isSimilar(Menus.backgroudPanel.build()) ||
                        e.getCurrentItem().isSimilar(Menus.seaLantern.build()) || e.getCurrentItem().isSimilar(Menus.web.build()))) {
                    p.openInventory(StaticMenus.createCategoryShopInv(e.getCurrentItem()));
                }
                if (e.getCurrentItem().isSimilar(Menus.arrow.build())) {
                    p.closeInventory();
                }
            } else {
                e.setCancelled(true);
                return;
            }
        }
        //invs categorias
        if (e.getInventory().getTitle().contains(StringColor.color("&bCategoria: "))) {
            if (e.getCurrentItem() == null) return;
            e.setCancelled(true);
            if ((e.getSlot() > 9 && e.getSlot() < 17) ||
                    (e.getSlot() > 18 && e.getSlot() < 25) ||
                    (e.getSlot() > 27 && e.getSlot() < 35) ||
                    (e.getSlot() > 36 && e.getSlot() < 44)) {

                if(e.getCurrentItem() == null){
                    return;
                }
//erro ao clicar fora do inv
                for (InvShop inv : LoadInvAndItems.getArrayShopInvs()) {
                    if (inv.getName().contains(e.getInventory().getTitle().split(StringColor.color("&bCategoria: "))[1])) {
                        if(e.getCurrentItem().equals(Material.AIR) || e.getCurrentItem() == null) return;
//                        if(e.getCurrentItem() != inv.getItemsVenda().get(e.getSlot()).getItems()) return;
//                        for (PrestigePlayer player : Main.getArrayPlayer()) {
//                            if (player.getUuid().equals(p.getUniqueId())) {
//
//                                if (player.getPoints() >= inv.getItemsVenda().get(e.getSlot()).getPrice()) {
//                                    if (inv.getItemsVenda().get(e.getSlot()).getOnlyCmd()) {
//                                        if (player.removePoints(inv.getItemsVenda().get(e.getSlot()).getPrice())) {
//                                            inv.getItemsVenda().get(e.getSlot()).executCommands(p.getName());
//                                            p.sendMessage(StringColor.color("&aCompra realizada com sucesso!"));
//                                        } else {
//                                            p.sendMessage(StringColor.color("&cOcorreu um erro ao processar a compra. Entre em contato com um staff!"));
//                                        }
//                                    } else {
//                                        if (player.removePoints(inv.getItemsVenda().get(e.getSlot()).getPrice())) {
//                                            p.getInventory().addItem(inv.getItemsVenda().get(e.getSlot()).getItems());
//                                            inv.getItemsVenda().get(e.getSlot()).executCommands(p.getName());
//                                            p.sendMessage(StringColor.color("&aCompra realizada com sucesso!"));
//                                        } else {
//                                            p.sendMessage(StringColor.color("&cVoce nao possui pontos de prestigio suficiente."));
//                                        }
//                                    }
//                                }
//
//                                break;
//                            }
//                        }
//
//                        break;
                    }
                }
                e.setCancelled(true);

                e.setCancelled(true);

            } else if (e.getCurrentItem().isSimilar(Menus.arrow.build())) {
                e.setCancelled(true);
                p.closeInventory();
            } else {
                e.setCancelled(true);
            }
        } else {
            return;
        }
    }

    @EventHandler
    public void invShopCategory(InventoryClickEvent e){

        if(e.getCurrentItem() == null) return;

    }


    protected static void commandResetRankPlayer(Player p) {

        String commandResetGroup = Configs.getConfigFile().getString("resetCommand").replace("%player%", p.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandResetGroup);
        p.sendMessage(StringColor.color("&aVoce foi enviado para o primeiro rank novamente. Boa Sorte!"));

    }

}
