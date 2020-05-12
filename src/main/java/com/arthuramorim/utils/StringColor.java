package com.arthuramorim.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class StringColor {

    public static String color(String string){

        return ChatColor.translateAlternateColorCodes('&',string);
    }

    public static List<String> color(List<String> list){

        List<String> lista = new ArrayList<String>();

        for (String linha:list) {
            lista.add(color(linha));
        }
        return lista;
    }

}
