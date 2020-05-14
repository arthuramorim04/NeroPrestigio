package com.arthuramorim.menus;

import com.arthuramorim.Main;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.ItemShop;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.utils.Configs;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menus {

    public static MakeItem backgroudPanel = new MakeItem(Material.STAINED_GLASS_PANE, (byte) 15);
    public static MakeItem profilePlayer = new MakeItem(347);
    public static MakeItem prestige = new MakeItem(Material.EYE_OF_ENDER);
    public static MakeItem prestigeShop = new MakeItem(Material.GOLD_NUGGET);
    public static MakeItem seaLantern = new MakeItem(Material.SEA_LANTERN);
    public static MakeItem web = new MakeItem(Material.WEB);
    public static MakeItem arrow = new MakeItem(Material.ARROW);
    public static Inventory prestigeMainMenu = Bukkit.createInventory(null, 9 * 6, StringColor.color("&dPrestigio"));


    public static void mainMenuPrestige(Player p) {

        ArrayList<String> infoPlayer = new ArrayList<>();
        int controller = 0;

        for (PrestigePlayer player : Main.getArrayPlayer()) {
            if (player.getUuid().equals(p.getUniqueId())) {
                infoPlayer.add(StringColor.color("&ePrestigios: &f" + player.getPrestige()));
                infoPlayer.add(StringColor.color("&ePontos: &f" + player.getPoints()));
                controller = -1;
            }
        }
        if (controller == 0) {
            PlayerController.loadPlayer(p.getName(), p.getUniqueId());
            for (PrestigePlayer player : Main.getArrayPlayer()) {
                if (player.getUuid().equals(p.getUniqueId())) {
                    infoPlayer.add(StringColor.color("&ePrestigios: &f" + player.getPrestige()));
                    infoPlayer.add(StringColor.color("&ePontos: &f" + player.getPoints()));
                    controller = -1;
                }
            }
        }

        if (controller == -1) {


            profilePlayer.setName(StringColor.color("&eJogador: &f" + p.getName())).addLore(infoPlayer);
            prestige.setName(StringColor.color("&eFazer Prestigio")).addLore((ArrayList<String>) StringColor.color((List<String>) Configs.getConfigFile().getConfigurationSection("lores").getList("prestigeButtomMenu")));
            prestigeShop.setName(StringColor.color("&ePrestigio Shop")).addLore((ArrayList<String>) StringColor.color((List<String>) Configs.getConfigFile().getConfigurationSection("lores").getList("prestigeShopMenu")));
            arrow.setName(StringColor.color("&fFechar"));
            backgroudPanel.setName(" ");

            for (int i = 0; i < prestigeMainMenu.getSize(); i++) {
                if (i == 0 || i == 8 || i == 45 || i == 53) {
                    prestigeMainMenu.setItem(i, seaLantern.build());
                    continue;
                }
                if (i == 1 || i == 7 || i == 9 || i == 17 || i == 36 || i == 44 || i == 46 || i == 52) {
                    prestigeMainMenu.setItem(i, web.build());
                    continue;
                }
                if (i == 49) {
                    prestigeMainMenu.setItem(i, arrow.build());
                    continue;
                }
                if (i == 4) {
                    prestigeMainMenu.setItem(i, profilePlayer.build());
                    continue;
                }
                if (i == 29) {
                    prestigeMainMenu.setItem(i, prestige.addGlow().build());
                    continue;
                }
                if (i == 33) {
                    prestigeMainMenu.setItem(i, prestigeShop.build());
                    continue;
                }

                prestigeMainMenu.setItem(i, backgroudPanel.build());
                continue;

            }
            p.openInventory(prestigeMainMenu);
        }else{
            p.closeInventory();
            p.sendMessage(StringColor.color("&cOcorreu um erro desconhecido, entre em contato com um staff."));
        }
    }


}
