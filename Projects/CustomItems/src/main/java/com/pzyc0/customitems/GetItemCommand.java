package com.pzyc0.customitems;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetItemCommand implements CommandExecutor {
    private ConfigManager manager;

    public GetItemCommand(ConfigManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED+"You need to specify the name of the item! The name can't have any spaces!");
            return false;
        }
        if(manager.getItem(args[0]) == null){
            player.sendMessage(ChatColor.RED+"The specified item couldn't be found!");
            return false;
        }else {
            player.getInventory().addItem(manager.getItem(args[0]));
            player.sendMessage(ChatColor.GREEN+"Gave you the item!");
        }

        return false;
    }
}
