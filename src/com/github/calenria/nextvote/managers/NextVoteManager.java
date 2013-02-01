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
package com.github.calenria.nextvote.managers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.FireworkMeta;

import com.avaje.ebean.EbeanServer;
import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.NextVoteRadomItem;
import com.github.calenria.nextvote.Utils;
import com.github.calenria.nextvote.models.VoteAggregate;
import com.github.calenria.nextvote.models.VoteData;
import com.github.calenria.nextvote.models.VoteHistory;

/**
 * @author Calenria
 * 
 */
public class NextVoteManager {
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
     * Initialisiert und erstellt die Datenbank falls nicht vorhanden.
     * 
     * @param nvPlugin
     *            NextVote Plugin
     */
    public NextVoteManager(final NextVote nvPlugin) {
        this.plugin = nvPlugin;
        try {
            plugin.getDatabase().find(VoteHistory.class).findRowCount();
        } catch (PersistenceException ex) {
            log.log(Level.INFO, "Installing database for " + plugin.getDescription().getName() + " due to first time usage");
            plugin.installDDL();
        }
        try {
            plugin.getDatabase().find(VoteAggregate.class).findRowCount();
        } catch (PersistenceException ex) {
            log.log(Level.INFO, "Installing database for " + plugin.getDescription().getName() + " due to first time usage");
            VoteAggregate.initView(plugin);
        }
        this.database = plugin.getDatabase();
    }

    /**
     * Sendet die Broadcastnachricht an alle Spieler die Online sind. Wer schon gevotet hatt bekommt nur die erste Zeile zu sehen.
     * 
     * @param translatedList
     *            Stringliste mit fertigen Nachrichten
     */
    private void broadcastMessage(final List<String> translatedList) {
        Player[] players = Bukkit.getOnlinePlayers();
        for (Player player : players) {
            if (!plugin.getCurrVotes().contains(player.getName())) {
                player.sendMessage(translatedList.toArray(new String[0]));
            } else {
                player.sendMessage(translatedList.get(0));
            }
        }
    }

    /**
     * Wird jede Minute aufgerufen um die heutigen Votes zu berechnen.
     */
    public final void currVote() {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);
        List<VoteData> votes = database.find(VoteData.class).where().ge("time", cal.getTime()).findList();
        synchronized (plugin.getCurrVotes()) {
            plugin.getCurrVotes().clear();
            if (votes != null && votes.size() > 0) {
                for (VoteData voteData : votes) {
                    plugin.addVote(voteData.getMinecraftUser());
                }
            }
        }
        log.info(String.format(plugin.getMessages().getString("votes.per.day"), plugin.getCurrVotes().size()));
    }

    /**
     * Erstellt einen Task der jede Minute currVote() aufruft.
     */
    public final void currVoteScheduler() {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                currVote();
            }
        }, Utils.TASK_ONE_SECOND, Utils.TASK_ONE_MINUTE);
    }

    /**
     * @param player
     *            Player
     */
    private void doFireWork(final Player player) {
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
                ItemStack i = new ItemStack(Material.FIREWORK);
                FireworkMeta fm = (FireworkMeta) i.getItemMeta();

                List<Color> c = new ArrayList<Color>();
                List<String> colors = plugin.getPluginConfig().getFireworkColors();
                for (String color : colors) {
                    c.add(Utils.COLORFIREWORKMAP.get(color.toUpperCase().trim()));
                }
                List<Color> f = new ArrayList<Color>();
                List<String> fades = plugin.getPluginConfig().getFireworkFadeColors();
                for (String fade : fades) {
                    f.add(Utils.COLORFIREWORKMAP.get(fade.toUpperCase().trim()));
                }
                FireworkEffect e = FireworkEffect.builder().flicker(true).withColor(c).withFade(f).with(Type.valueOf(plugin.getPluginConfig().getFireworkType())).trail(true).build();
                fm.addEffect(e);
                fm.setPower(plugin.getPluginConfig().getFireworkPower());
                firework.setFireworkMeta(fm);
            }
        }, Utils.TASK_THREE_SECONDS);
    }

    /**
     * Stellt die Nachrichten zusammen.
     * 
     * @param oPlayer
     *            Offline Player
     * @param vote
     *            VoteHistory
     */
    private void doMessage(final OfflinePlayer oPlayer, final VoteHistory vote) {
        if (!plugin.getPluginConfig().isShowBroadcast()) {
            return;
        }
        List<String> translatedList = new ArrayList<String>();
        if (vote.hasEcon() && vote.hasItem()) {
            List<String> msgList = plugin.getPluginConfig().getBroadcastMessageItemEcon();
            for (String msg : msgList) {
                msg = msg.replaceAll("<player>", oPlayer.getName());
                msg = msg.replaceAll("<econ>", NextVote.getEconomy().format(vote.getEconAmmount()));
                msg = msg.replaceAll("<itemamount>", String.valueOf(vote.getAmmount()));
                msg = msg.replaceAll("<itemname>", vote.getLocalName());
                translatedList.add(ChatColor.translateAlternateColorCodes('&', msg));
            }
            broadcastMessage(translatedList);
            return;
        }
        if (vote.hasEcon()) {
            List<String> msgList = plugin.getPluginConfig().getBroadcastMessageEcon();
            for (String msg : msgList) {
                msg = msg.replaceAll("<player>", oPlayer.getName());
                msg = msg.replaceAll("<econ>", NextVote.getEconomy().format(vote.getEconAmmount()));
                translatedList.add(ChatColor.translateAlternateColorCodes('&', msg));
            }
            broadcastMessage(translatedList);
            return;
        }
        if (vote.hasItem()) {
            List<String> msgList = plugin.getPluginConfig().getBroadcastMessageItem();
            for (String msg : msgList) {
                msg = msg.replaceAll("<player>", oPlayer.getName());
                msg = msg.replaceAll("<itemamount>", String.valueOf(vote.getAmmount()));
                msg = msg.replaceAll("<itemname>", vote.getLocalName());
                translatedList.add(ChatColor.translateAlternateColorCodes('&', msg));
            }
            broadcastMessage(translatedList);
            return;
        }

    }

    /**
     * Stößt den eigentlichen Vote an, übernimmt aber nicht die Auszahlung!
     * 
     * @param player
     *            Player
     */
    public final void doVote(final String player) {
        // Check Player exists!
        OfflinePlayer thePlayer = Bukkit.getOfflinePlayer(player);
        if (!thePlayer.hasPlayedBefore()) {
            log.info(String.format(plugin.getMessages().getString("player.never.played"), player));
            return;
        }
        // Player in die Heutige Voteliste aufnehmen, verhindert die ausgabe von mehr als einer Zeile beim Broadcast
        plugin.addVote(player);
        VoteHistory vote = new VoteHistory();
        vote.setMinecraftUser(player);
        if (plugin.getPluginConfig().isFixEcon() || plugin.getPluginConfig().isOnlyEcon()) {
            vote.setEconAmmount(plugin.getPluginConfig().getFixEconAmmount());
            vote.setEcon(true);
        }
        if (!plugin.getPluginConfig().isOnlyEcon()) {
            NextVoteRadomItem item = new NextVoteRadomItem(plugin);
            if (item.isEcon()) {
                vote.setEconAmmount(vote.getEconAmmount() + item.getEcoAmount());
                vote.setEcon(true);
            } else {
                vote.setAmmount(item.getAmount());
                vote.setDamage(item.getDamage());
                vote.setItem(true);
                vote.setLocalName(item.getLokalName());
                vote.setName(item.getName());
                vote.setMaterial(item.getMaterial());
            }
        }
        vote.setTime(new Timestamp(System.currentTimeMillis()));
        vote.setPaid(payVote(thePlayer, vote));
        database.save(vote);
        doMessage(thePlayer, vote);
        log.info(String.format(plugin.getMessages().getString("player.voted"), player));
    }

    /**
     * Online Playercheck ob noch jemand auf Vote auszahlung wartet.
     */
    private void payVote() {
        final int days = -30;
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, days);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 1);
        List<VoteHistory> votes = database.find(VoteHistory.class).where().ge("time", cal.getTime()).eq("paid", false).findList();
        if (votes != null && votes.size() > 0) {
            log.info(String.format(plugin.getMessages().getString("votes.not.paid"), votes.size()));
            for (VoteHistory voteData : votes) {
                Player player = Bukkit.getPlayer(voteData.getMinecraftUser());
                if (player != null && player.isOnline()) {
                    voteData.setPaid(payVote(player, voteData));
                    database.save(voteData);
                }
            }
        }
    }

    /**
     * Auszahlung der Votes.
     * 
     * @param oPlayer
     *            Player
     * @param vote
     *            VoteHistory
     * @return <tt>true</tt> wenn der Spieler ausgezahlt worden ist
     */
    public final boolean payVote(final OfflinePlayer oPlayer, final VoteHistory vote) {
        if (!oPlayer.isOnline()) {
            return false;
        }
        Player player = oPlayer.getPlayer();
        PlayerInventory pInv = player.getInventory();
        // Always pay Econ
        if (vote.hasEcon() && !vote.isPaidEcon()) {
            NextVote.getEconomy().depositPlayer(player.getName(), vote.getEconAmmount());
            // no item left? set as paid!
            if (!vote.hasItem()) {
                vote.setPaidEcon(true);
                return true;
            }
        }
        // Check free Slot an pay Item
        int slot = pInv.firstEmpty();
        if (slot != -1 && vote.hasItem()) {
            ItemStack itemstack = new ItemStack(vote.getMaterial(), vote.getAmmount(), vote.getDamage());
            pInv.addItem(itemstack);

            if (plugin.getPluginConfig().isFirework()) {
                doFireWork(player);
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Erstellt einen Task der jede Minute payVote() aufruft.
     */
    public final void payVoteScheduler() {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                payVote();
            }
        }, Utils.TASK_ONE_SECOND, Utils.TASK_ONE_MINUTE);
    }

}
