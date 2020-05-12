package com.arthuramorim.events;

import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.entity.PrestigePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void firstJoin(PlayerJoinEvent e){

        org.bukkit.entity.Player player = e.getPlayer();

        PrestigePlayer p = new PrestigePlayer(player.getName(),player.getUniqueId());

        if(e.getPlayer().hasPlayedBefore()){

            PlayerController.loadPlayer(p.getName(),p.getUuid());

        }


        if(!(e.getPlayer().hasPlayedBefore())) {
            PlayerController.registerNewPlayer(p.getName(),p.getUuid());
        }

    }

    @EventHandler
    public void leftServer(PlayerQuitEvent e){

        PlayerController.savePlayerOnLeft(e.getPlayer().getUniqueId());

    }
}
