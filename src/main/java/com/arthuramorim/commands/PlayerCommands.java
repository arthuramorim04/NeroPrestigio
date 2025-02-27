package com.arthuramorim.commands;

import com.arthuramorim.NeroPrestigio;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.menus.Menus;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {

    private final NeroPrestigio plugin;
    private final Menus menus;

    public PlayerCommands(NeroPrestigio plugin) {
        this.plugin = plugin;
        menus = new Menus(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;


        if (!(p instanceof Player)) return false;

        if (args.length == 0) {

            if (command.getName().equalsIgnoreCase("prestigio")) {

                menus.mainMenuPrestige(p);

            }

            if (command.getName().equalsIgnoreCase("ppoints")) {

                try {
                    PrestigePlayer player = this.plugin.getHashPlayer().get(p.getName());
                    p.sendMessage(StringColor.color("\n&ePontos de Prestigio: &f" + player.getPoints() + "\n"));
                    return false;

                } catch (NullPointerException e) {
                    this.plugin.getPc().loadPlayer(p.getName(), p.getUniqueId());
                    PrestigePlayer player = this.plugin.getHashPlayer().get(p.getName());
                    p.sendMessage(StringColor.color("\n&ePontos de Prestigio: &f" + player.getPoints() + "\n"));
                    return false;
                } catch (Exception e) {
                    p.sendMessage(StringColor.color("&e[Prestigio] &cOcorreu um erro desconhecido, se o erro continuar entre em contato com um staff"));
                }
            }


                if (command.getName().equalsIgnoreCase("prestigios")) {
                    try {

                        PrestigePlayer player = this.plugin.getHashPlayer().get(p.getName());
                        p.sendMessage(StringColor.color("\n&ePrestigios : &f" + player.getPrestige()));
                        return false;

                    } catch (NullPointerException e) {

                        this.plugin.getPc().loadPlayer(p.getName(), p.getUniqueId());
                        PrestigePlayer player = this.plugin.getHashPlayer().get(p.getName());
                        p.sendMessage(StringColor.color("\n&ePrestigios : &f" + player.getPrestige()));
                        return false;

                    } catch (Exception e) {
                        p.sendMessage(StringColor.color("&e[Prestigio] &cOcorreu um erro desconhecido, se o erro continuar entre em contato com um staff"));
                    }

                }
            }

        if (args.length == 2) {

            if (p.hasPermission("neroprestige.admin")) {
                if (command.getName().equalsIgnoreCase("setppoints")) {
                    try {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.setPoints(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (NullPointerException e) {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        this.plugin.getPc().loadPlayer(target.getName(), target.getUniqueId());
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.setPoints(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (Exception e) {
                        p.sendMessage(StringColor.color("&e[Prestigio] &cOcorreu um erro desconhecido, se o erro continuar entre em contato com os desenvolvedores"));
                    }
                }

                if (command.getName().equalsIgnoreCase("addppoints")) {
                    try {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.addPoints(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (NullPointerException e) {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        this.plugin.getPc().loadPlayer(target.getName(), target.getUniqueId());
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.addPoints(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &ade pontos de prestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce enviou &f" + quantity + " &ade pontos de prestigio para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (Exception e) {
                        p.sendMessage(StringColor.color("&e[Prestigio] &cOcorreu um erro desconhecido, se o erro continuar entre em contato com os desenvolvedores"));
                    }

                }
                if (command.getName().equalsIgnoreCase("addprestige")) {
                    try {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.addPrestige(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce adicionou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (NullPointerException e) {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        this.plugin.getPc().loadPlayer(target.getName(), target.getUniqueId());
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.addPrestige(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce adicionou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (Exception e) {
                        p.sendMessage(StringColor.color("&e[Prestigio] &cOcorreu um erro desconhecido, se o erro continuar entre em contato com os desenvolvedores"));
                    }

                }

                if (command.getName().equalsIgnoreCase("setprestige")) {
                    try {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.setPrestige(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce setou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (NullPointerException e) {

                        Integer quantity = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(!(target instanceof Player)) {
                            p.sendMessage(StringColor.color("&e[Prestigio] &cJogador nao existe."));
                            return false;
                        }
                        this.plugin.getPc().loadPlayer(target.getName(), target.getUniqueId());
                        PrestigePlayer player = this.plugin.getHashPlayer().get(target.getName());
                        player.setPrestige(quantity);
                        target.sendMessage(StringColor.color("\n&e[Prestigio] &aVoce recebeu &f" + quantity + " &aprestigio!\n"));
                        sender.sendMessage(StringColor.color("&e[Prestigio] &aVoce setou &f" + quantity + " &aprestigios para o jogador &f" + target.getName()));
                        this.plugin.getAltPlayer().add(player);
                        return false;

                    } catch (Exception e) {
                        p.sendMessage(StringColor.color("&e[Prestigio] &cOcorreu um erro desconhecido, se o erro continuar entre em contato com os desenvolvedores"));
                    }

                }
            }
        }
        return false;
    }
}
