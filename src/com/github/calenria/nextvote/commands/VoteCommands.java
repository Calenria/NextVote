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
package com.github.calenria.nextvote.commands;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import com.avaje.ebean.PagingList;
import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.Utils;
import com.github.calenria.nextvote.models.VoteAggregate;
import com.github.calenria.nextvote.models.VoteData;
import com.github.calenria.nextvote.models.VoteHistory;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

/**
 * Klasse mit untergeordneten Befehlen.
 * 
 * @author Calenria
 * 
 */
public class VoteCommands {
    /**
     * NextVote Plugin.
     */
    private final NextVote   plugin;
    /**
     * Bukkit Logger.
     */
    private static Logger    log       = Logger.getLogger("Minecraft");

    /**
     * Listengröße.
     */
    private static final int LIST_SIZE = 5;

    /**
     * @param nvPlugin
     *            Plugin
     */
    public VoteCommands(final NextVote nvPlugin) {
        this.plugin = nvPlugin;
    }

    /**
     * Gibt Informationen zu Votes von dir oder einen anderen Spieler aus.
     * 
     * @param args
     *            Optional der Spielername
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "info" }, desc = "Gibt Informationen zu Votes von dir oder einen anderen Spieler aus", usage = "[spielername]", max = 1)
    @CommandPermissions("nextvote.info")
    public final void info(final CommandContext args, final CommandSender sender) throws CommandException {
        String playerName = sender.getName();
        if (args.argsLength() > 0) {
            playerName = args.getString(0);
        }

        OfflinePlayer oPlayer = Utils.offlinePlayerWithMessage(playerName, sender.getName());
        if (oPlayer != null) {
            List<VoteHistory> votes = plugin.getDatabase().find(VoteHistory.class).where().eq("minecraft_user", playerName).findList();
            if (votes.size() == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&6%s&f hat noch nie gevotet", playerName)));
                return;
            }

            double econ = 0;
            int items = 0;
            String econName = NextVote.getEconomy().currencyNamePlural();
            for (VoteHistory vote : votes) {
                if (vote.hasEcon()) {
                    econ += vote.getEconAmmount();
                }
                if (vote.hasItem()) {
                    items += vote.getAmmount();
                }
            }

            sender.sendMessage(Utils.colorFormat("============= &6 %s &f =============", playerName));
            sender.sendMessage(Utils.colorFormat("Votes: &6 %d", votes.size()));
            sender.sendMessage(Utils.colorFormat("%s: &6 %.2f", econName, econ));
            sender.sendMessage(Utils.colorFormat("Items: &6 %d", items));
        }
    }

    /**
     * Lädt das Plugin neu.
     * 
     * @param args
     *            Sollte leer sein
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "reload" }, desc = "Läd das Plugin neu", usage = "reload")
    @CommandPermissions("nextvote.reload")
    public final void reload(final CommandContext args, final CommandSender sender) throws CommandException {
        plugin.setupConfig();
        plugin.setupLang();
        sender.sendMessage(String.format("[%s] reloaded Version %s", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
        log.log(Level.INFO, String.format("[%s] reloaded Version %s", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
    }

    /**
     * Zeigt die Top Voter an.
     * 
     * @param args
     *            Seitenzahl oder leer
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "topvotes" }, desc = "Zeigt die Top Voter an", usage = "[seitenzahl]", max = 1, min = 0)
    @CommandPermissions("nextvote.votefor")
    public final void top(final CommandContext args, final CommandSender sender) throws CommandException {
        String econName = NextVote.getEconomy().currencyNamePlural();
        PagingList<VoteAggregate> pagingList = plugin.getDatabase().find(VoteAggregate.class).findPagingList(LIST_SIZE);
        sender.sendMessage(Utils.colorFormat("=============== &6 %s &f ===============", "Top Voter"));
        for (VoteAggregate vote : pagingList.getPage(0).getList()) {
            sender.sendMessage(Utils.colorFormat("&6%s&f: &6%d &f(%s: &6%.2f&f, Items: &6%d&f)", vote.getPlayer(), vote.getTotal(), econName, vote.getTotalEcon(), vote.getTotalItems()));
        }
    }

    /**
     * Setzt einen Testvote ab.
     * 
     * @param args
     *            Sollte leer sein
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "testvote" }, desc = "Setzt einen Testvote ab")
    @CommandPermissions("nextvote.test")
    public final void testvote(final CommandContext args, final CommandSender sender) throws CommandException {
        sender.sendMessage(String.format("[%s] Testvote sucess!", plugin.getDescription().getName()));
        log.log(Level.INFO, String.format("[%s] Testvote sucess!", plugin.getDescription().getName()));
        VoteData voteData = new VoteData();
        voteData.setMinecraftUser(sender.getName());
        voteData.setTime(new Timestamp(System.currentTimeMillis()));
        voteData.setService("ExtraVote");
        voteData.setIp("");
        plugin.getDatabase().save(voteData);
        plugin.getCurrVotes().add(voteData.getMinecraftUser());
        plugin.getNextVoteManager().doVote(sender.getName());
    }

    /**
     * Setzt einen Vote im namen eines anderen Spielers ab.
     * 
     * @param args
     *            Spielername für den gevotet werden soll
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "votefor" }, desc = "Votet für einen Spieler", usage = "spielername", max = 1, min = 1)
    @CommandPermissions("nextvote.votefor")
    public final void votefor(final CommandContext args, final CommandSender sender) throws CommandException {
        sender.sendMessage(String.format("[%s] Vote for %s sucess!", plugin.getDescription().getName(), args.getString(0)));
        log.log(Level.INFO, String.format("[%s] Vote for %s sucess!", plugin.getDescription().getName(), args.getString(0)));
        VoteData voteData = new VoteData();
        voteData.setMinecraftUser(args.getString(0));
        voteData.setTime(new Timestamp(System.currentTimeMillis()));
        voteData.setService("ExtraVote");
        voteData.setIp("");
        plugin.getDatabase().save(voteData);
        plugin.getCurrVotes().add(voteData.getMinecraftUser());
        plugin.getNextVoteManager().doVote(args.getString(0));
    }
}
