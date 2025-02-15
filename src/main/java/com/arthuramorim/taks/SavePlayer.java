package com.arthuramorim.taks;

import com.arthuramorim.NeroPrestigio;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.entity.PrestigePlayer;
import org.bukkit.scheduler.BukkitRunnable;

public class SavePlayer extends BukkitRunnable {

    private final NeroPrestigio plugin;

    public SavePlayer(NeroPrestigio plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        saveAll();
    }

    public void saveAll(){
        if(this.plugin.getAltPlayer().isEmpty()) return;

        for (PrestigePlayer p : this.plugin.getAltPlayer()) {
            this.plugin.getPc().savePlayerTask(p);
        }

        this.plugin.getAltPlayer().clear();
    }
    

}
