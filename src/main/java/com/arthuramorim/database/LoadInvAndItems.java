package com.arthuramorim.database;

import com.arthuramorim.entity.InvShop;
import com.arthuramorim.entity.ItemShop;
import com.arthuramorim.utils.Configs;
import com.arthuramorim.utils.MakeItem;
import com.arthuramorim.utils.StringColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoadInvAndItems {

    private static FileConfiguration customConfig = Configs.getShop();

    private static ArrayList<InvShop> arrayInvs = new ArrayList<>();

    private static void loadInvsShop(){

        if(!customConfig.isSet("shop")) return;


        customConfig.getConfigurationSection("shop").getKeys(false)
                .forEach(invName-> {

                    String nome = customConfig.getString("shop." + invName + ".name");
                    Integer idItem = customConfig.getInt("shop." + invName + ".id");
                    int dataItem = customConfig.getInt("shop." + invName + ".data");
                    int slot = customConfig.getInt("shop." + invName + ".slot");
                    Boolean glow = customConfig.getBoolean("shop."+invName+".glow");
                    List<String> lore = customConfig.getStringList("shop."+invName+".lore");

                    MakeItem iconeInv = new MakeItem(idItem, (byte) dataItem);

                    iconeInv.setName(nome).setLore((ArrayList<String>) lore);
                    if(glow){
                        iconeInv.addGlow();
                    }

                    InvShop invShop = new InvShop();

                    invShop.setIconeInv(iconeInv.build());
                    invShop.setSlot(slot);

                    invShop.setName(StringColor.color(invName));

                    HashMap<Integer,ItemShop> items = carregaItems(invName);

                    invShop.setItemsVenda(items);

                    arrayInvs.add(invShop);
                });


    }


    private static HashMap<Integer,ItemShop> carregaItems(String invName){

        HashMap<Integer,ItemShop> hashMapItems = new HashMap<>();

        customConfig.getConfigurationSection("shop."+invName+".items").getKeys(false)
                .forEach(items-> {

                    String path = "shop."+invName+ ".items."+items;
                    String nome = customConfig.getString(path + ".name");
                    Integer id = customConfig.getInt(path + ".id");
                    int dataItem = customConfig.getInt(path + ".data");
                    int slot = customConfig.getInt(path + ".slot");
                    int price = customConfig.getInt(path + ".data");
                    Integer amount = customConfig.getInt(path+".price");
                    List<String> lore = (customConfig.getStringList(path + ".lore") != null ? customConfig.getStringList(path + ".lore") : new ArrayList<>());


                    MakeItem item = new MakeItem(id, (byte) dataItem);
                    item.setName(nome).setLore((ArrayList<String>) lore).setAmount(amount);

                    customConfig.getStringList(path + ".enchants").forEach(enchant ->{
                        try {
                            String[] aux = null;
                            String encanto = null;
                            aux = enchant.trim().split(",");
                            encanto = aux[0];
                            Integer level = Integer.valueOf(aux[1]);

                            item.addEnchantment(Enchantment.getByName(encanto),level);
                        } catch (Exception e){
                            System.out.println("Erro ao gerar encantamento "+enchant+" do item "+items);
                            System.out.println(e);
                        }
                    });

                    ItemShop itemShop = new ItemShop(item.build(),slot,price);

                    hashMapItems.put(itemShop.getSlot(),itemShop);


                });

        return hashMapItems;
    }

    public static ArrayList<InvShop> getShopHash(){
        return arrayInvs;
    }

    public static void loadInvShop(){
        loadInvsShop();
    }



}
