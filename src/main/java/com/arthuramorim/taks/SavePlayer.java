package com.arthuramorim.taks;

import com.arthuramorim.Main;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.entity.PrestigePlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class SavePlayer extends BukkitRunnable {

    @Override
    public void run() {

        saveAll();
    }

    public void saveAll(){
        if(Main.getAltPlayer().isEmpty()) return;

        for (PrestigePlayer p : Main.getAltPlayer()) {
            PlayerController.savePlayerTask(p);
        }

        Main.getAltPlayer().clear();
    }
    

}
