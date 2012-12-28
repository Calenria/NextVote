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

package com.github.calenria.nextvote.listener;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.avaje.ebean.EbeanServer;
import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.Utils;
import com.github.calenria.nextvote.models.VoteData;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

public class NextVoteListener implements Listener {
	private static Logger log = Logger.getLogger("Minecraft");
	private NextVote plugin = null;
	public EbeanServer database = null;

	public NextVoteListener(NextVote plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
		try {
			plugin.getDatabase().find(VoteData.class).findRowCount();
		} catch (PersistenceException ex) {
			log.log(Level.INFO, "Installing database for " + plugin.getDescription().getName() + " due to first time usage");
			plugin.installDDL();
		}
		database = plugin.getDatabase();
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onVotifierEvent(VotifierEvent event) {

		Vote vote = event.getVote();
		String user = vote.getUsername();
		if (user.equalsIgnoreCase("Test Notification")) {
			user = "Calenria";
		}
		OfflinePlayer thePlayer = Bukkit.getOfflinePlayer(user);
		if (!thePlayer.hasPlayedBefore()) {
			log.info(String.format(plugin.messages.getString("player.never.played"), user));
			return;
		}

		VoteData voteData = new VoteData();
		voteData.setMinecraftUser(user);
		voteData.setTime(new Timestamp(System.currentTimeMillis()));
		voteData.setService(vote.getServiceName());
		voteData.setIp(vote.getAddress());
		database.save(voteData);
		plugin.nextVoteManager.doVote(user);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public void onPlayerJoinEvent(final PlayerJoinEvent event) {

		if (!event.getPlayer().hasPlayedBefore()) {
			return;
		}

		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				Player player = event.getPlayer();
				if (player != null) {
					VoteData vote = database.find(VoteData.class).where(" minecraft_user like '" + player.getName() + "' order by id desc LIMIT 1").findUnique();
					if (vote != null) {
						Date voteDate = vote.getTime();
						Date nowDate = new Date();
						if (voteDate.getDay() == nowDate.getDay() && voteDate.getMonth() == nowDate.getMonth() && voteDate.getYear() == nowDate.getYear()) {
							@SuppressWarnings("unchecked")
							List<String> voteInfo = (List<String>) plugin.getConfig().getList("thxVote");
							for (String string : voteInfo) {
								player.sendMessage(Utils.replacePlayerName(string, player));
							}
						} else {
							Calendar voteCal = Calendar.getInstance();
							voteCal.setTime(voteDate);
							Calendar nowCal = Calendar.getInstance();
							nowCal.setTime(new Date());
							Long days = Utils.daysBetween(voteCal, nowCal);

							if (days == 2) {
								@SuppressWarnings("unchecked")
								List<String> dayVote = (List<String>) plugin.getConfig().getList("dayVote");
								for (String string : dayVote) {
									player.sendMessage(Utils.replacePlayerName(string, player));
								}
							} else {
								@SuppressWarnings("unchecked")
								List<String> infoVote = (List<String>) plugin.getConfig().getList("daysVote");
								for (String string : infoVote) {
									player.sendMessage(Utils.replacePlayerName(string, player, days.toString()));
								}
							}

							@SuppressWarnings("unchecked")
							List<String> infoVote = (List<String>) plugin.getConfig().getList("infoVote");
							for (String string : infoVote) {
								player.sendMessage(Utils.replacePlayerName(string, player));
							}
						}

					} else {
						@SuppressWarnings("unchecked")
						List<String> voteInfo = (List<String>) plugin.getConfig().getList("noVote");
						for (String string : voteInfo) {
							player.sendMessage(Utils.replacePlayerName(string, player));
						}

						@SuppressWarnings("unchecked")
						List<String> infoVote = (List<String>) plugin.getConfig().getList("infoVote");
						for (String string : infoVote) {
							player.sendMessage(Utils.replacePlayerName(string, player));
						}

					}

				}

			}
		}, 60L);
	}

}
