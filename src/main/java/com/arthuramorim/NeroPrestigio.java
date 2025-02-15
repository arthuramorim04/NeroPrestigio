package com.arthuramorim;

import com.arthuramorim.commands.PlayerCommands;
import com.arthuramorim.controllers.PlayerController;
import com.arthuramorim.database.sql.DatabaseManager;
import com.arthuramorim.enginers.LoadInvAndItems;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.events.InventoryEvents;
import com.arthuramorim.events.PlayerEvents;
import com.arthuramorim.taks.SavePlayer;
import com.arthuramorim.utils.Configs;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class NeroPrestigio extends JavaPlugin {

    public static NeroPrestigio plugin;
    @Getter
    private DatabaseManager dbManager;
    @Getter
    private HashMap<String,PrestigePlayer> hashPlayer = new HashMap<>();
    @Getter
    private ArrayList <PrestigePlayer> altPlayer = new ArrayList<>();
    private SavePlayer sp;
    @Getter
    private PlayerController pc;
    @Override
    public void onEnable() {
        plugin = this;

        getServer().getConsoleSender().sendMessage(ChatColor.BLUE + "\n" +
                " ____  __.__                _______  _________________________   \n" +
                "|    |/ _|__| ____    ____  \\      \\ \\_____  \\______   \\   _  \\  \n" +
                "|      < |  |/    \\  / ___\\ /   |   \\  _(__  <|       _/  /_\\  \\ \n" +
                "|    |  \\|  |   |  \\/ /_/  >    |    \\/       \\    |   \\  \\_/   \\\n" +
                "|____|__ \\__|___|  /\\___  /\\____|__  /______  /____|_  /\\_____  /\n" +
                "        \\/       \\//_____/         \\/       \\/       \\/       \\/ \n\n\n" +
                ChatColor.GREEN + "         NeroPrestigio\n" +
                "         Autor: Arthur 'KingN3R0' Amorim\n" +
                "         Versao: 3.1\n" +
                "         Discord: N3R0#9378\n\n\n" +
                "");

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "["+plugin.getName()+"]" +"INICIALIZANDO...");

        Configs.createConfig();
        Configs.createShop();

        initDatabaseConnection();


        LoadInvAndItems.loadInvShop();
        pc = new PlayerController(this);
        //task
        sp = new SavePlayer(this);
        sp.runTaskTimerAsynchronously(this,0L,20*600L);
        loadEvents();
        loadCommands();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "["+plugin.getName()+"]" +"INICIALIZADO COM SUCESSO!");

    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "["+plugin.getName()+"]" +"DESABILITANDO...");
        sp.saveAll();
        dbManager.closeConnection();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "["+plugin.getName()+"]" +"DESABILITADO COM SUCESSO!");

    }

    private void initDatabaseConnection() {
        String user = Configs.getConfigFile().getString("storage.user");
        String pass = Configs.getConfigFile().getString("storage.pass");
        String host = Configs.getConfigFile().getString("storage.host");
        String db = Configs.getConfigFile().getString("storage.db");
        Integer port = Configs.getConfigFile().getInt("storage.port");
        dbManager = new DatabaseManager(this);
        dbManager.initMySQL(
                host,
                db,
                user,
                pass
        );
        dbManager.startConnection();
        dbManager.createTable(dbManager, "neroprestige", "uuid varchar(255) primary key,"+" name varchar(16) not null, "+"prestige bigint,"+"points bigint");
    }

    public void loadEvents(){

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(this), this);
    }

    public void loadCommands(){
        getCommand("ppoints").setExecutor(new PlayerCommands(this));
        getCommand("prestigios").setExecutor(new PlayerCommands(this));
        getCommand("setppoints").setExecutor(new PlayerCommands(this));
        getCommand("prestigio").setExecutor(new PlayerCommands(this));
        getCommand("addppoints").setExecutor(new PlayerCommands(this));
        getCommand("addprestige").setExecutor(new PlayerCommands(this));
        getCommand("setprestige").setExecutor(new PlayerCommands(this));
    }

}
