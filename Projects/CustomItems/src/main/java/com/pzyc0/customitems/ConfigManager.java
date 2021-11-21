package com.pzyc0.customitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {
    private Customitems mainInstance;

    private HashMap<String, ItemStack> customItems;

    public ConfigManager(Customitems mainInstance){
        this.mainInstance = mainInstance;
        customItems = new HashMap<>();
        loadItemStacks();
    }
    public void SaveItem(ItemStack stack, String name){
        //Save the Item via the command
        mainInstance.getChangeDataFile().set(name, stack);
        customItems.put(name, stack);
    }
    public ItemStack getItem(String name){
        //Get the ItemStack with the name, if it doesn't find anything, returns null because of the HashMap
        return customItems.get(name);
    }

    private void loadItemStacks(){
        //Add all the ItemStacks in the Config and the custom file to the HashMap
        for(String str : mainInstance.getChangeDataFile().getConfigurationSection("").getKeys(false)){
            customItems.put(str, mainInstance.getChangeDataFile().getItemStack(str));
        }
        for(String str : mainInstance.getConfig().getConfigurationSection("items").getKeys(false)){
            ItemStack stack = new ItemStack(Material.AIR);
            if(mainInstance.getConfig().getString("items."+str+".material") != null) stack.setType(Material.valueOf(mainInstance.getConfig().getString("items."+str+".material")));
            if(mainInstance.getConfig().getInt("items."+str+".amount") != 0 && mainInstance.getConfig().getInt(str+".amount") < 65) stack.setAmount(mainInstance.getConfig().getInt("items."+str+".amount"));
            if(mainInstance.getConfig().getConfigurationSection("items."+str+".meta") != null) {
                ItemMeta meta = stack.getItemMeta();
                if(mainInstance.getConfig().getString("items."+str+".meta.displayname") != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', mainInstance.getConfig().getString("items."+str+".meta.displayname")));
                List<String> loreList = new ArrayList<>();
                for(String lore : mainInstance.getConfig().getStringList("items."+str + ".meta.lore")){
                    loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
                }
                meta.setLore(loreList);
                meta.setUnbreakable(mainInstance.getConfig().getBoolean("items."+str+".meta.unbreakable"));
                stack.setItemMeta(meta);
            }
            customItems.put(str, stack);

        }
    }
    public void saveFile(){
        try {
            mainInstance.getChangeDataFile().save(mainInstance.getDatafile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
