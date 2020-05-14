package com.arthuramorim.taks;

import com.arthuramorim.Main;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.entity.PrestigePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SavePlayerStats {

    public static void savePlayer(){

        new BukkitRunnable() {
        int sec = 0;
        int controller = 0;

        @Override
        public void run() {
            sec = 0;
            sec++;

            if (sec == 1) {

                for (PrestigePlayer p : Main.getAltPlayer()) {
                    PlayerController.savePlayerTask(p);
                }
            }
        }
    }.runTaskTimer(Main.plugin, 0, 20 * 600L);
    }

}
