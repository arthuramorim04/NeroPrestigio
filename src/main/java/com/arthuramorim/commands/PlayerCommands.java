package com.arthuramorim.commands;

import com.arthuramorim.Main;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.ItemShop;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.menus.Menus;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;


//"

        if (!(p instanceof Player)) return false;

        if (args.length == 0) {

            if (command.getName().equalsIgnoreCase("teste")) {
                for (InvShop inv : LoadInvAndItems.getArrayShopInvs()) {
                    p.sendMessage(inv.getName() + "//// " + inv.toString());
                    for (ItemShop item : inv.getItemsVenda()) {
                        p.sendMessage(item.toString());
                    }
                }
            }

            if (command.getName().equalsIgnoreCase("prestigio")) {

                Menus.mainMenuPrestige(p);

            }

            if (command.getName().equalsIgnoreCase("ppoints")) {
                int controller = 0;
                for (PrestigePlayer player : Main.getArrayPlayer()) {
                    if (player.getUuid().equals(p.getUniqueId())) {

                        p.sendMessage(StringColor.color("\n&ePontos de Prestigio: &f" + player.getPoints() + "\n"));
                        controller = -1;
                        return false;

                    }
                }
                if (controller == 0) {
                    PlayerController.loadPlayer(p.getName(), p.getUniqueId());
                    for (PrestigePlayer player : Main.getArrayPlayer()) {
                        if (player.getUuid().equals(p.getUniqueId())) {

                            p.sendMessage(StringColor.color("\n&ePontos de Prestigio: &f" + player.getPoints() + "\n"));
                            controller = -1;
                            return false;

                        }
                    }
                }else{
                    p.sendMessage(StringColor.color("&cOcorreu um erro desconhecido, entre em contato com um staff"));
                }

            }


            if (command.getName().equalsIgnoreCase("prestigios")) {
                int controller = 0;
                for (PrestigePlayer player : Main.getArrayPlayer()) {
                    if (player.getUuid().equals(p.getUniqueId())) {
                        p.sendMessage(StringColor.color("\n&ePrestigios : &f" + player.getPrestige()));
                        controller = -1;
                        return false;
                    }

                }
                if (controller == 0) {
                    PlayerController.loadPlayer(p.getName(), p.getUniqueId());
                    for (PrestigePlayer player : Main.getArrayPlayer()) {
                        if (player.getUuid().equals(p.getUniqueId())) {
                            p.sendMessage(StringColor.color("\n&ePrestigios : &f" + player.getPrestige()));
                            controller = -1;
                            return false;
                        }

                    }
                }else{
                    p.sendMessage(StringColor.color("&cOcorreu um erro desconhecido, entre em contato com um staff"));
                }
            }
        }

        if (args.length == 2) {
            if (p.hasPermission("neroprestige.admin")) {
                if (command.getName().equalsIgnoreCase("setppoints")) {
                    try {

                        Boolean existAlt = false;
                        int controller = 0;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.setPoints(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                                    controller = -1;
                                    return false;
                                }
                            }
                            if (controller == 0) {
                                if (quantity >= 0 && target instanceof Player) {
                                    PlayerController.loadPlayer(target.getName(), target.getUniqueId());
                                    for (PrestigePlayer player : Main.getArrayPlayer()) {
                                        if (player.getUuid().equals(target.getUniqueId())) {
                                            player.setPoints(quantity);
                                            target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                                            sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                                            controller = -1;
                                            if(!target.isOnline()){
                                                PlayerController.savePlayerOnLeft(target.getUniqueId());
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                p.sendMessage(StringColor.color("&cVoce tentou setar um valor de pontos menor que 1 ou o jogador nao esta online."));
                            }
                        } else {

                            p.sendMessage(StringColor.color("&cO jogador nao existe."));

                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }

                if (command.getName().equalsIgnoreCase("addppoints")) {
                    try {

                        Boolean existAlt = false;
                        int controller = 0;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.addPoints(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                                    controller = -1;
                                    return false;
                                }
                            }
                            if (controller == 0) {
                                if (quantity >= 0 && target instanceof Player) {
                                    PlayerController.loadPlayer(target.getName(), target.getUniqueId());
                                    for (PrestigePlayer player : Main.getArrayPlayer()) {
                                        if (player.getUuid().equals(target.getUniqueId())) {
                                            player.addPoints(quantity);
                                            target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                                            sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                                            controller = -1;
                                            if(!target.isOnline()){
                                                PlayerController.savePlayerOnLeft(target.getUniqueId());
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                p.sendMessage(StringColor.color("&cVoce tentou setar um valor de pontos menor que 1 ou o jogador nao esta online."));
                            }
                        } else {

                            p.sendMessage(StringColor.color("&cO jogador nao existe."));

                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }
                if (command.getName().equalsIgnoreCase("addprestige")) {
                    try {

                        Boolean existAlt = false;
                        int controller = 0;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.addPrestige(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce adicionou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                                    controller = -1;
                                    return false;
                                }
                            }
                            if (controller == 0) {
                                if (quantity >= 0 && target instanceof Player) {
                                    PlayerController.loadPlayer(target.getName(), target.getUniqueId());
                                    for (PrestigePlayer player : Main.getArrayPlayer()) {
                                        if (player.getUuid().equals(target.getUniqueId())) {
                                            player.addPrestige(quantity);
                                            target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                                            sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce adicionou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                                            controller = -1;
                                            if(!target.isOnline()){
                                                PlayerController.savePlayerOnLeft(target.getUniqueId());
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                p.sendMessage(StringColor.color("&cVoce tentou adicionar uma quantidade de prestigio menor que 1 ou o jogador nao esta online."));
                            }
                        } else {

                            p.sendMessage(StringColor.color("&cO jogador nao existe."));

                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }

                if (command.getName().equalsIgnoreCase("setprestige")) {
                    try {

                        Boolean existAlt = false;
                        int controller = 0;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.setPrestige(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce setou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                                    controller = -1;
                                    return false;
                                }
                            }
                            if (controller == 0) {
                                if (quantity >= 0 && target instanceof Player) {
                                    PlayerController.loadPlayer(target.getName(), target.getUniqueId());
                                    for (PrestigePlayer player : Main.getArrayPlayer()) {
                                        if (player.getUuid().equals(target.getUniqueId())) {
                                            player.setPrestige(quantity);
                                            target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                                            sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce setou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                                            controller = -1;
                                            return false;
                                        }
                                    }
                                }
                            }else{
                                p.sendMessage(StringColor.color("&cVoce tentou setar uma quantidade de prestigio menor que 1 ou o jogador nao esta online."));
                            }
                        } else {

                            p.sendMessage(StringColor.color("&cO jogador nao existe."));

                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }
            }
        }

        return false;
    }
}
