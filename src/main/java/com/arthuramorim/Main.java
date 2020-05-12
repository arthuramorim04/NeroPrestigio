package com.arthuramorim;

import com.arthuramorim.commands.PlayerCommands;
import com.arthuramorim.database.DBConnection;
import com.arthuramorim.database.LoadInvAndItems;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.events.InventoryEvents;
import com.arthuramorim.events.PlayerEvents;
import com.arthuramorim.utils.Configs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static Main plugin;
    private static DBConnection dbConnection;
    // Key = uuid
    private static ArrayList<PrestigePlayer> arrayPlayer = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;

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

//        Menus.createInvetorys(LoadInvAndItems.getShopHash());

        System.out.println(LoadInvAndItems.getShopHash().toString());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "["+plugin.getName()+"]" +"INICIALIZADO COM SUCESSO!");

    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "["+plugin.getName()+"]" +"DESABILITANDO...");

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

    public static ArrayList<PrestigePlayer> getArrayPlayer() {
        return arrayPlayer;
    }

}
