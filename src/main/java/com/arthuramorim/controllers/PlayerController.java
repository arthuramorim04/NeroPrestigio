package com.arthuramorim.controllers;

import com.arthuramorim.NeroPrestigio;
import com.arthuramorim.database.sql.DatabaseManager;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.utils.StringColor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class PlayerController {

    private final DatabaseManager dbManager;
    private final NeroPrestigio plugin;

    public PlayerController(NeroPrestigio plugin) {
        this.plugin = plugin;
        this.dbManager = this.plugin.getDbManager();
    }

    //reescrever
    public void registerNewPlayer(String name, UUID uuid) {

        PrestigePlayer p = new PrestigePlayer(name, uuid);

        try {

            PreparedStatement ps;

            if (p.getName() != null) {
                String insertQuery = "INSERT INTO neroprestige (name, uuid, prestige, points) VALUES (?,?,?,?)";
                ps = dbManager.getSql().prepareStatement(insertQuery);
                ps.setString(1, name);
                ps.setString(2, String.valueOf(uuid));
                ps.setInt(3, 0);
                ps.setInt(4, 0);
                int row = ps.executeUpdate();
                ps.close();
                loadPlayer(name, uuid);

            }

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void loadPlayer(String name, UUID uuid) {

        PrestigePlayer p = new PrestigePlayer(name, uuid);

        try {

            PreparedStatement ps;
            ResultSet result;
            result = dbManager.getSql().query("select * from neroprestige" +
                    " where  uuid = '" + p.getUuid() + "'");

            while (result.next()) {

                Integer prestige = result.getInt("prestige");
                Integer points = result.getInt("points");
                p.setPoints(points);
                p.setPrestige(prestige);
                this.plugin.getHashPlayer().put(p.getName(), p);

            }
            if (!result.next()) {
                registerNewPlayer(name, uuid);
            }
        } catch (Exception e) {

            NeroPrestigio.plugin.getServer().getConsoleSender().sendMessage(StringColor.color("&c[LoadPlayer] Nao foi possivel carregar o player \n" +
                    "Nome: " + p.getName() + "\n " +
                    "UUID: " + p.getUuid() + "\nErro inesperado: " + e.getMessage()));

        }


    }


    private void savePlayer(PrestigePlayer p) {


        try {

            PreparedStatement ps;

            ps = dbManager.getSql().prepareStatement("update neroprestige set prestige = " + p.getPrestige() + ", points = " + p.getPoints() + " where uuid = '" + p.getUuid() + "'");
            ps.execute();
            ps.close();

        } catch (Exception e) {

            NeroPrestigio.plugin.getServer().getConsoleSender().sendMessage(StringColor.color("&c[] Nao foi possivel salvar o player \n" +
                    "Nome: " + p.getName() + "\n" +
                    "UUID: " + p.getUuid() + "\nErro inesperado: " + e.getMessage()));

        }
    }


    public void savePlayerOnLeft(String name) {
        PrestigePlayer player = this.plugin.getHashPlayer().get(name);
        savePlayer(player);
        this.plugin.getHashPlayer().remove(player);

    }

    public void savePlayerTask(PrestigePlayer p) {
        PrestigePlayer player = this.plugin.getHashPlayer().get(p.getName());
        savePlayer(player);
    }
}
