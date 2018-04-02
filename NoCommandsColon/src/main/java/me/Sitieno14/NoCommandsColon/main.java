package me.Sitieno14.NoCommandsColon;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class main extends org.bukkit.plugin.java.JavaPlugin
	implements org.bukkit.event.Listener{
		public void onEnable(){
			File file = new File(getDataFolder(), "config.yml");
			if (!file.exists()){
				saveDefaultConfig();
				getConfig().options().copyDefaults(true);
			}
			getServer().getPluginManager().registerEvents(this, this);
			Logger.getLogger("Minecraft").info("[NoCommandsColon] NoCommandsColon 1.1 by Sitieno14 enabled");
		}

		public void onDisable(){
			Logger.getLogger("Minecraft").info("[NoCommandsColon] NoCommandsColon 1.1 by Sitieno14 disabled");
		}

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		if (event.getMessage().split(" ")[0].contains(":"))
		{
			event.setCancelled(true);
			player.sendMessage(getConfig().getString("block-message").replaceAll("&", "§").replace("%p", player.getName()));
		}
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if (command.getName().equalsIgnoreCase("nocommandscolon") || command.getName().equalsIgnoreCase("ncc")){
			if (args.length == 0){
				if (!sender.hasPermission("nocommandscolon.reload")){
					sender.sendMessage("§cYou don't have the required permission for this command§4!");
					return true;
				}
				sender.sendMessage("§aNoCommandsColon by §2Sitieno14");
				sender.sendMessage("§7/§enocommandscolon §breload §7- §eReloads the §6config.yml");
				return true;
			}
			if (args.length > 0){
				if (args[0].equalsIgnoreCase("reload"))
				{
					if (!sender.hasPermission("nocommandscolon.reload"))
					{
						sender.sendMessage("§cYou don't have the required permission for this command§4!");
						return true;
					}
					reloadConfig();
					sender.sendMessage("§aNoCommandsColon §21.1 §areloaded");
					return true;
				}
				sender.sendMessage("§cInvalid argument, §euse §7/§enocommandscolon");
				return true;
			}
			return false;
		}
		return false;
	}
}