package com.arthuramorim.events;

import com.arthuramorim.NeroPrestigio;
import com.arthuramorim.controllers.PlayerController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    private final NeroPrestigio plugin;

    public PlayerEvents(NeroPrestigio plugin) {
        this.plugin = plugin;
    }

    // criacao de novo jogador está com problema
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()) {
            this.plugin.getPc().registerNewPlayer(player.getName(), player.getUniqueId());
            return;
        }

        if (player.hasPlayedBefore()) {
            this.plugin.getPc().loadPlayer(player.getName(), player.getUniqueId());
            return;
        }

        if (this.plugin.getHashPlayer().get(player.getName()) == null) {
            this.plugin.getPc().registerNewPlayer(player.getName(), player.getUniqueId());
        }

    }
    @EventHandler
    public void leftServer(PlayerQuitEvent e){

        try {
            this.plugin.getPc().savePlayerOnLeft(e.getPlayer().getName());
        } catch (NullPointerException err) {
            if((e.getPlayer().hasPlayedBefore())) {
                this.plugin.getPc().registerNewPlayer(e.getPlayer().getName(),e.getPlayer().getUniqueId());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
//    @Deprecated
//    @EventHandler
//    public void firstJoin(PlayerJoinEvent e){
//
//        org.bukkit.entity.Player player = e.getPlayer();
//
//        PrestigePlayer p = new PrestigePlayer(player.getName(),player.getUniqueId());
//
//        if(e.getPlayer().hasPlayedBefore()){
//
//            PlayerController.loadPlayer(p.getName(),p.getUuid());
//            //adicionar metodo pra validar se o player ja existe no banco mesmo se ele ja tenha logado anteriormente
//
//        }
//
//
//        if(!(e.getPlayer().hasPlayedBefore())) {
//            PlayerController.registerNewPlayer(p.getName(),p.getUuid());
//        }
//
//    }
//
//    @Deprecated
//    @EventHandler
//    public void leftServer(PlayerQuitEvent e){
//
//        try {
//            PlayerController.savePlayerOnLeft(e.getPlayer().getName());
//        } catch (NullPointerException err) {
//            if((e.getPlayer().hasPlayedBefore())) {
//                PlayerController.registerNewPlayer(e.getPlayer().getName(),e.getPlayer().getUniqueId());
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//
//    }
//}
