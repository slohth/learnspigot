package com.pzyc0.customitems;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Customitems extends JavaPlugin {

    //The manager that saves and loads ItemStacks
    private ConfigManager manager;

    public File getDatafile() {
        return datafile;
    }

    //File for the custom items and the yml file of it
    private File datafile;
    private YamlConfiguration changeDataFile;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        datafile = new File(getDataFolder(), "customitems.yml");
        if(datafile.exists()){
            try {
                datafile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        changeDataFile = YamlConfiguration.loadConfiguration(datafile);
        try {
            changeDataFile.save(datafile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        manager = new ConfigManager(this);
        getCommand("getitem").setExecutor(new GetItemCommand(manager));
        getCommand("saveitem").setExecutor(new SaveItemCommand(manager));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            changeDataFile.save(datafile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getChangeDataFile() {
        return changeDataFile;
    }
}
