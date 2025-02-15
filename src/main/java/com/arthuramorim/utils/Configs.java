package com.arthuramorim.utils;

import com.arthuramorim.NeroPrestigio;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configs {

    private static File configFile;
    private static FileConfiguration config;

    private static File shopFile;
    private static FileConfiguration shop;

    public static void createConfig() {
        configFile = new File(NeroPrestigio.plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            NeroPrestigio.plugin.saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void createShop(){
        shopFile = new File(NeroPrestigio.plugin.getDataFolder(), "shop.yml");
        if(!shopFile.exists()){
            shopFile.getParentFile().mkdirs();
            NeroPrestigio.plugin.saveResource("shop.yml", false);
        }

        shop = new YamlConfiguration();
        try{
            shop.load(shopFile);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }
    }



    public static void carregaConfigs(){
        createConfig();
        createShop();
    }

    public static FileConfiguration getConfigFile() {
        return config;
    }

    public static FileConfiguration getShop() {
        return shop;
    }
}