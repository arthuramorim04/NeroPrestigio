package com.arthuramorim.controllers;

import com.arthuramorim.Main;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.utils.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class PlayerController {

    private static Connection con = Main.getDbConnection().getConnection();

    public static void registerNewPlayer(String name, UUID uuid) {

        PrestigePlayer p = new PrestigePlayer(name, uuid);

        try {

            PreparedStatement ps;

            if (p.getName() != null) {
                ps = con.prepareStatement("insert into neroprestige" +
                        "(name, uuid, prestige,points)" +
                        "values ('" + p.getName() + "','" + p.getUuid() + "','" + p.getPrestige() + "','" + p.getPoints() + "')");

                ps.execute();
                loadPlayer(name, uuid);
                ps.close();

            }

        } catch (Exception e) {

        }

    }

    public static void loadPlayer(String name, UUID uuid) {

        PrestigePlayer p = new PrestigePlayer(name, uuid);

        try {

            PreparedStatement ps;
            ResultSet result;
            ps = con.prepareStatement("select * from neroprestige" +
                    " where  uuid = '" + p.getUuid() + "'");

            result = ps.executeQuery();

            while (result.next()) {

                Integer prestige = result.getInt("prestige");
                Integer points = result.getInt("points");
                p.setPoints(points);
                p.setPrestige(prestige);
                Main.getHashPlayer().put(p.getName(), p);

            }
            if (!result.next()) {
                registerNewPlayer(name, uuid);
            }

            ps.close();
        } catch (Exception e) {

            Main.plugin.getServer().getConsoleSender().sendMessage(StringColor.color("&c[LoadPlayer] Nao foi possivel carregar o player \n" +
                    "Nome: " + p.getName() + "\n " +
                    "UUID: " + p.getUuid() + "\nErro inesperado: " + e.getMessage()));

        }


    }


    private static void savePlayer(PrestigePlayer p) {


        try {

            PreparedStatement ps;

            ps = con.prepareStatement("update neroprestige set prestige = " + p.getPrestige() + ", points = " + p.getPoints() + " where uuid = '" + p.getUuid() + "'");
            ps.execute();
            ps.close();

        } catch (Exception e) {

            Main.plugin.getServer().getConsoleSender().sendMessage(StringColor.color("&c[] Nao foi possivel salvar o player \n" +
                    "Nome: " + p.getName() + "\n" +
                    "UUID: " + p.getUuid() + "\nErro inesperado: " + e.getMessage()));

        }
    }


    public static void savePlayerOnLeft(String name) {

        PrestigePlayer player = Main.getHashPlayer().get(name);
        savePlayer(player);
        Main.getHashPlayer().remove(player);
        return;

    }

    public static void savePlayerTask(PrestigePlayer p) {

        PrestigePlayer player = Main.getHashPlayer().get(p.getName());
        savePlayer(player);

    }
}
