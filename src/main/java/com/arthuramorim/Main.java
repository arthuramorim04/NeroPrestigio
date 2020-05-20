package com.arthuramorim;

import com.arthuramorim.commands.PlayerCommands;
import com.arthuramorim.database.DBConnection;
import com.arthuramorim.enginers.LoadInvAndItems;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.events.InventoryEvents;
import com.arthuramorim.events.PlayerEvents;
import com.arthuramorim.taks.SavePlayer;
import com.arthuramorim.utils.Configs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    public static Main plugin;
    private static DBConnection dbConnection;
    private static HashMap<String,PrestigePlayer> hashPlayer = new HashMap<>();
    private static ArrayList <PrestigePlayer> altPlayer = new ArrayList<>();
    private SavePlayer sp;
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

        String user = Configs.getConfigFile().getString("storage.user");
        String pass = Configs.getConfigFile().getString("storage.pass");
        String host = Configs.getConfigFile().getString("storage.host");
        String db = Configs.getConfigFile().getString("storage.db");
        Integer port = Configs.getConfigFile().getInt("storage.port");
        dbConnection = new DBConnection(user, pass, host, port, db);
        getDbConnection().openConnection();

        loadEvents();
        loadCommands();

        LoadInvAndItems.loadInvShop();
        //task
        sp = new SavePlayer();
        sp.runTaskTimerAsynchronously(this,0L,20*600L);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "["+plugin.getName()+"]" +"INICIALIZADO COM SUCESSO!");

    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "["+plugin.getName()+"]" +"DESABILITANDO...");
        sp.saveAll();
        getDbConnection().closeConnection();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "["+plugin.getName()+"]" +"DESABILITADO COM SUCESSO!");

    }

    public void loadEvents(){

        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvents(), this);
    }

    public void loadCommands(){
        getCommand("ppoints").setExecutor(new PlayerCommands());
        getCommand("prestigios").setExecutor(new PlayerCommands());
        getCommand("setppoints").setExecutor(new PlayerCommands());
        getCommand("prestigio").setExecutor(new PlayerCommands());
        getCommand("addppoints").setExecutor(new PlayerCommands());
        getCommand("addprestige").setExecutor(new PlayerCommands());
        getCommand("setprestige").setExecutor(new PlayerCommands());
    }

    public static DBConnection getDbConnection() {
        return dbConnection;
    }

    public static HashMap<String, PrestigePlayer> getHashPlayer() {
        return hashPlayer;
    }

    public static ArrayList<PrestigePlayer> getAltPlayer() {
        return altPlayer;
    }
}
