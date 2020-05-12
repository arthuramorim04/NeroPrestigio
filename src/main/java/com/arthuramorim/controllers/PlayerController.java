package com.arthuramorim.controllers;

import com.arthuramorim.Main;
import com.arthuramorim.entity.PrestigePlayer;
import com.arthuramorim.utils.StringColor;

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
            System.out.println(1);
            ps = con.prepareStatement("select * from neroprestige" +
                    " where  uuid = '" + p.getUuid() + "'");

            System.out.println(2);
            result = ps.executeQuery();

            while (result.next()) {

                Integer prestige = result.getInt("prestige");
                Integer points = result.getInt("points");
                p.setPoints(points);
                p.setPrestige(prestige);
                Main.getArrayPlayer().add(p);

            }

            ps.close();
        } catch (Exception e) {

            Main.plugin.getServer().getConsoleSender().sendMessage(StringColor.color("&c[LoadPlayer] Nao foi possivel carregar o player \n" +
                    "Nome: " + p.getName() + "\n " +
                    "UUID: " + p.getUuid() + "\n O nome do jogador pode ter sofrido alguma alteracao e nao esta compativel com o uuid cadastrado no banco de dados.\n Mais informacoes do erro: " + e.getMessage()));

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
                    "UUID: " + p.getUuid() + "\n O nome do jogador pode ter sofrido alguma alteracao e nao esta compativel com o uuid cadastrado no banco de dados.\n Mais informacoes do erro: " + e.getMessage()));

        }
    }


    public static void savePlayerOnLeft(UUID uuid) {



        for (PrestigePlayer player : Main.getArrayPlayer()) {

            if (player.getUuid().equals(uuid)) {
                savePlayer(player);
                Main.getArrayPlayer().remove(player);
                return;
            }
        }
    }
}
