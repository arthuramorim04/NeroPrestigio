package com.arthuramorim.events;

import com.arthuramorim.NeroPrestigio;
import com.arthuramorim.enginers.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.ItemShop;
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

    private final NeroPrestigio plugin;

    public InventoryEvents(NeroPrestigio plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOpenPrestigeInv(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (!(p instanceof Player)) return;

        //inv principal prestigio
        if (e.getInventory().getTitle().contains("Prestigio")) {
            if (e.getCurrentItem() == null) {
                return;
            }
            e.setCancelled(true);
            if (e.getClick().isLeftClick()) {
                if (MakeItem.checkIsSimilar(e.getCurrentItem(), Menus.prestige.build())) {
                    if (p.hasPermission("nero.prestigio")) {

                        PrestigePlayer player = this.plugin.getHashPlayer().get(p.getName());
                        if (player.addPrestige()) {

                            commandResetRankPlayer(p);
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_DEATH, 1f, 1f);
                            p.sendMessage(StringColor.color("\n&aVoce acabou de fazer prestigio! Parabens!\n"));

                        } else {
                            p.sendMessage(StringColor.color("&cOcorreu algum erro e nao foi possivel realizar o prestigio, entre em contato com um staff"));
                        }


                    } else {
                        p.sendMessage(StringColor.color("&cVoce precisa estar no ultimo rank para fazer um prestigio."));
                        p.closeInventory();
                        return;
                    }
                }
                if (e.getCurrentItem().isSimilar(Menus.prestigeShop.build())) {
                    p.openInventory(StaticMenus.createShopInv(LoadInvAndItems.getArrayShopInvs()));
                    return;
                }
                if (e.getCurrentItem().isSimilar(Menus.arrow.build())) {
                    p.closeInventory();
                    return;
                }
            } else {
                e.setCancelled(true);
                return;
            }
        }
        //inv menu shop categorias
        if (e.getInventory().getTitle().equals(StaticMenus.titlePrestigeShop)) {
            if (e.getCurrentItem() == null) {
                return;
            } else {
                e.setCancelled(true);
                try {
                    if (e.getClick().isLeftClick()) {
                        if (!(e.getCurrentItem().isSimilar(Menus.arrow.build()) || e.getCurrentItem().isSimilar(Menus.backgroudPanel.build()) ||
                                e.getCurrentItem().isSimilar(Menus.seaLantern.build()) || e.getCurrentItem().isSimilar(Menus.web.build()))) {
                            p.openInventory(StaticMenus.createCategoryShopInv(e.getCurrentItem()));
                            return;
                        }
                        if (e.getCurrentItem().isSimilar(Menus.arrow.build())) {
                            p.closeInventory();
                            return;
                        }
                    }
                } catch (NullPointerException nullError) {
                    return;
                } catch (Exception error) {
                    error.getStackTrace();
                    return;
                }
            }
            return;
        }

        //invs categorias
        if (e.getInventory().getTitle().equals(StaticMenus.titleInv)) {
            if (e.getCurrentItem() == null) {
                return;
            }
            e.setCancelled(true);
            if (e.getCurrentItem().isSimilar(Menus.arrow.build())) {
                p.closeInventory();
                return;
            }


            try {
                String title = e.getInventory().getTitle();
                String titleInv = StaticMenus.titleInv;
                e.setCancelled(true);
                String[] split = e.getInventory().getTitle().split(StringColor.color("&bCategoria: "));
                String s = LoadInvAndItems.getArrayShopInvs().toString();
                for (InvShop inv : LoadInvAndItems.getArrayShopInvs()) {
                    if (inv.getName().equals(split[1].trim())) {

                        ItemShop itemShop = inv.getItemsVenda().get(e.getSlot());
                        PrestigePlayer prestigePlayer = this.plugin.getHashPlayer().get(p.getName());

                        if (prestigePlayer.getPoints() >= itemShop.getPrice()) {

                            if (p.getInventory().firstEmpty() > -1) {

                                if (prestigePlayer.removePoints(itemShop.getPrice())) {

                                    if (itemShop.getOnlyCmd()) {
                                        itemShop.executCommands(p.getName());
                                        p.sendMessage(StringColor.color("&aCompra realizada com sucesso!"));
                                        this.plugin.getAltPlayer().add(prestigePlayer);

                                        return;
                                    } else {
                                        if (itemShop.getCmds() != null) {
                                            itemShop.executCommands(p.getName());
                                            p.getInventory().addItem(itemShop.getItems());
                                            p.sendMessage(StringColor.color("&aCompra realizada com sucesso!"));
                                            this.plugin.getAltPlayer().add(prestigePlayer);
                                            return;
                                        } else {
                                            p.getInventory().addItem(itemShop.getItems());
                                            p.sendMessage(StringColor.color("&aCompra realizada com sucesso!"));
                                            this.plugin.getAltPlayer().add(prestigePlayer);
                                            return;
                                        }
                                    }
                                } else {
                                    p.sendMessage(StringColor.color("&cAlguma coisa nao esta certa... Tente de novo ou fale com um staff."));
                                    return;
                                }
                            } else {
                                p.sendMessage(StringColor.color("&cVoce deve ter pelo menos 1 slot do inventario livre."));
                                return;
                            }
                        } else {
                            p.sendMessage(StringColor.color("&cVoce nao possui ppoints suficiente."));
                            return;
                        }
                    }
                }
            } catch (Exception err) {
                return;
            }
        }


        return;
    }


    protected static void commandResetRankPlayer(Player p) {

        String commandResetGroup = Configs.getConfigFile().getString("resetCommand").replace("%player%", p.getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandResetGroup);
        p.sendMessage(StringColor.color("&aVoce foi enviado para o primeiro rank novamente. Boa Sorte!"));

    }

}