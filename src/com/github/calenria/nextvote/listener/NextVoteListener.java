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

/**
 * Eventlistener Klasse.
 * 
 * @author Calenria
 * 
 */
public class NextVoteListener implements Listener {
    /**
     * Bukkit Logger.
     */
    private static Logger log      = Logger.getLogger("Minecraft");
    /**
     * NextVote Plugin.
     */
    private NextVote      plugin   = null;
    /**
     * Bukkit Datenbank.
     */
    private EbeanServer   database = null;

    /**
     * Registriert die Eventhandler und erstellt die Datenbank falls nicht vorhanden.
     * 
     * @param nvPlugin
     *            NextVote Plugin
     */
    public NextVoteListener(final NextVote nvPlugin) {
        this.plugin = nvPlugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
        try {
            plugin.getDatabase().find(VoteData.class).findRowCount();
        } catch (PersistenceException ex) {
            // TODO I18N Messages
            log.log(Level.INFO, "Installing database for " + plugin.getDescription().getName() + " due to first time usage");
            plugin.installDDL();
        }
        database = plugin.getDatabase();
    }

    /**
     * Wird aufgerufen sowie ein Spieler den Server betritt.
     * 
     * @param event
     *            PlayerJoinEvent
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public final void onPlayerJoinEvent(final PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            return;
        }

        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Player player = event.getPlayer();
                if (player != null) {
                    VoteData vote = database.find(VoteData.class).where(" minecraft_user like '" + player.getName() + "' order by id desc LIMIT 1").findUnique();
                    if (vote != null) {
                        Date voteDate = vote.getTime();
                        Date nowDate = new Date();
                        if (Utils.daysBetweenMidnight(voteDate, nowDate) == 0) {
                            List<String> voteInfo = plugin.getConfig().getStringList("thxVote");
                            for (String string : voteInfo) {
                                player.sendMessage(Utils.replacePlayerName(string, player));
                            }
                        } else {
                            Long days = Utils.daysBetweenMidnight(voteDate, nowDate);
                            if (days == 1) {
                                List<String> dayVote = plugin.getConfig().getStringList("dayVote");
                                for (String string : dayVote) {
                                    player.sendMessage(Utils.replacePlayerName(string, player));
                                }
                            } else {
                                List<String> infoVote = plugin.getConfig().getStringList("daysVote");
                                for (String string : infoVote) {
                                    player.sendMessage(Utils.replacePlayerName(string, player, days.toString()));
                                }
                            }
                            List<String> infoVote = plugin.getConfig().getStringList("infoVote");
                            for (String string : infoVote) {
                                player.sendMessage(Utils.replacePlayerName(string, player));
                            }
                        }
                    } else {
                        List<String> voteInfo = plugin.getConfig().getStringList("noVote");
                        for (String string : voteInfo) {
                            player.sendMessage(Utils.replacePlayerName(string, player));
                        }
                        List<String> infoVote = plugin.getConfig().getStringList("infoVote");
                        for (String string : infoVote) {
                            player.sendMessage(Utils.replacePlayerName(string, player));
                        }
                    }
                }
            }
        }, Utils.TASK_THREE_SECONDS);
    }

    /**
     * Lauscht auf Events des Votifier Plugins.
     * 
     * @param event
     *            Votifier Event
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public final void onVotifierEvent(final VotifierEvent event) {
        Vote vote = event.getVote();
        String user = vote.getUsername();

        log.info(String.format(plugin.getMessages().getString("player.vote.event"), user));

        OfflinePlayer thePlayer = Bukkit.getOfflinePlayer(user);
        if (!thePlayer.isOnline()) {
            if (!thePlayer.hasPlayedBefore()) {
                log.info(String.format(plugin.getMessages().getString("player.never.played"), user));
                return;
            }
        }

        VoteData voteData = new VoteData();
        voteData.setMinecraftUser(user);
        voteData.setTime(new Timestamp(System.currentTimeMillis()));
        voteData.setService(vote.getServiceName());
        voteData.setIp(vote.getAddress());
        database.save(voteData);
        plugin.getNextVoteManager().doVote(user);
    }

}
