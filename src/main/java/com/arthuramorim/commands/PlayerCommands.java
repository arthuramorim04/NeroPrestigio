package com.arthuramorim.commands;

import com.arthuramorim.Main;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.Menus;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;

public class PlayerCommands implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;


//"

        if (!(p instanceof Player)) return false;

        if (args.length == 0) {

            if(command.getName().equalsIgnoreCase("prestigio")){

                Menus.mainMenuPrestige(p);

            }

            if (command.getName().equalsIgnoreCase("ppoints")) {
                int controller = 0;
                for (PrestigePlayer player : Main.getArrayPlayer()) {
                    if (player.getUuid().equals(p.getUniqueId())) {

                        p.sendMessage(StringColor.color("\n&ePontos de Prestigio: &f" + player.getPoints() + "\n"));

                    }
                }

            }


            if (command.getName().equalsIgnoreCase("prestigios")) {
                for (PrestigePlayer player : Main.getArrayPlayer()) {
                    if (player.getUuid().equals(p.getUniqueId())) {
                        p.sendMessage(StringColor.color("\n&ePrestigios : &f" + player.getPrestige()));
                    }

                }
            }
        }

        if (args.length == 2) {
            if(p.hasPermission("neroprestige.admin")){
                if (command.getName().equalsIgnoreCase("setppoints")) {
                    try {

                        Boolean existAlt = false;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.setPoints(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                                }
                            }
                        } else {
                            p.sendMessage(StringColor.color("&cVoce tentou setar um valor de pontos menor que 1 ou o jogador nao existe."));
                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }

                if(command.getName().equalsIgnoreCase("addppoints")){
                    try {

                        Boolean existAlt = false;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.addPoints(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                                }
                            }
                        } else {
                            p.sendMessage(StringColor.color("&cVoce tentou adicionar um valor de pontos menor que 1 ou o jogador nao existe."));
                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }
                if(command.getName().equalsIgnoreCase("addprestige")){
                    try {

                        Boolean existAlt = false;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.addPrestige(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce adicionou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                                }
                            }
                        } else {
                            p.sendMessage(StringColor.color("&cVoce tentou adicionar um valor de prestigio menor que 1 ou o jogador nao existe."));
                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }
                }

                if(command.getName().equalsIgnoreCase("setprestige")){
                    try {

                        Boolean existAlt = false;

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if (quantity >= 0 && target instanceof Player) {
                            for (PrestigePlayer player : Main.getArrayPlayer()) {
                                if (player.getUuid().equals(target.getUniqueId())) {
                                    player.setPrestige(quantity);
                                    target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                                    sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce setou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                                }
                            }
                        } else {
                            p.sendMessage(StringColor.color("&cVoce tentou setar um valor de prestigio menor que 1 ou o jogador nao existe."));
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
