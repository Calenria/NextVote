/*
 * Copyright (C) 2012 Calenria <https://github.com/Calenria/> and contributors
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3.0 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package com.github.calenria.nextvote;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.calenria.nextvote.commands.CommandHandler;
import com.github.calenria.nextvote.commands.VoteCommands;
import com.github.calenria.nextvote.listener.NextVoteListener;
import com.github.calenria.nextvote.managers.NextVoteManager;
import com.github.calenria.nextvote.models.ConfigData;
import com.github.calenria.nextvote.models.VoteData;
import com.github.calenria.nextvote.models.VoteHistory;
import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.SimpleInjector;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

public class NextVote extends JavaPlugin implements Listener {

	private static Logger log = Logger.getLogger("Minecraft");

	public NextVoteListener nextVoteListener = null;
	public NextVoteManager nextVoteManager = null;
	public CommandsManager<CommandSender> commands;
	public static Permission permission = null;
	public static Economy economy = null;

	public ResourceBundle messages = null;
	public ResourceBundle items = null;

	public List<String> currVotes = new ArrayList<String>();

	public ConfigData config = null;

	public String lang = "de";

	@Override
	public void installDDL() {
		super.installDDL();
	}

	@Override
	public void onEnable() {
		setupConfig();
		setupLang();
		setupPermissions();
		setupEconomy();
		setupdListeners();
		setupdManagers();
		setupCommands();
		log.log(Level.INFO, String.format("[%s] Enabled Version %s", getDescription().getName(), getDescription().getVersion()));
	}

	public void setupLang() {
		messages = readProperties("messages_");
		items = readProperties("items_");
	}

	private PropertyResourceBundle readProperties(String type) {
		PropertyResourceBundle bundle = null;
		File messagesFile = new File(this.getDataFolder(), type + lang + ".properties");
		if (!messagesFile.exists()) {
			try {
				Utils.copy(getResource(type + lang + ".properties"), messagesFile);
			} catch (Exception e) {
				log.info("Keine " + type + " für " + lang + " gefunden! Erstelle standard Datei!");
				try {
					Utils.copy(getResource(type + "de.properties"), messagesFile);
				} catch (IOException e1) {
					log.log(Level.SEVERE, e.getLocalizedMessage());
				}
			}
		}

		try {
			bundle = new PropertyResourceBundle(new FileInputStream(messagesFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bundle;
	}

	@Override
	public void onDisable() {
		log.log(Level.INFO, String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
	}

	public void setupConfig() {
		if (!new File(this.getDataFolder(), "config.yml").exists()) {
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		} else {
			this.reloadConfig();
		}
		this.config = new ConfigData(this);
		this.lang = config.getLang();

	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	private void setupdManagers() {
		nextVoteManager = new NextVoteManager(this);
		nextVoteManager.currVoteSheduler();
		nextVoteManager.payVoteSheduler();
	}

	private void setupdListeners() {
		nextVoteListener = new NextVoteListener(this);
	}

	@Override
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(VoteData.class);
		list.add(VoteHistory.class);
		return list;
	}

	private void setupCommands() {
		this.commands = new CommandsManager<CommandSender>() {
			@Override
			public boolean hasPermission(CommandSender sender, String perm) {
				return permission.has(sender, perm);
			}
		};

		commands.setInjector(new SimpleInjector(this));

		CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
		cmdRegister.register(CommandHandler.class);
		cmdRegister.register(VoteCommands.class);
		

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			commands.execute(cmd.getName(), args, sender, sender);
		} catch (CommandPermissionsException e) {
			sender.sendMessage(ChatColor.RED + messages.getString("noPerms"));
		} catch (MissingNestedCommandException e) {
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (CommandUsageException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (WrappedCommandException e) {
			if (e.getCause() instanceof NumberFormatException) {
				sender.sendMessage(ChatColor.RED + messages.getString("numberFormat"));
			} else {
				sender.sendMessage(ChatColor.RED + messages.getString("exception"));
				e.printStackTrace();
			}
		} catch (CommandException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
		}
		return true;
	}

}
