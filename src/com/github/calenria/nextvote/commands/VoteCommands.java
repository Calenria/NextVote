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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;

import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.models.VoteData;
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
    private final NextVote plugin;
    /**
     * Bukkit Logger.
     */
    private static Logger  log = Logger.getLogger("Minecraft");

    /**
     * @param nvPlugin
     *            Plugin
     */
    public VoteCommands(final NextVote nvPlugin) {
        this.plugin = nvPlugin;
    }

    /**
     * Lädt das Plugin neu.
     * 
     * @param args
     *            Befehls Argumente
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "reload", "rl" }, desc = "Läd das Plugin neu", usage = "reload")
    @CommandPermissions("nextvote.reload")
    public final void reload(final CommandContext args, final CommandSender sender) throws CommandException {
        plugin.setupConfig();
        plugin.setupLang();
        sender.sendMessage(String.format("[%s] reloaded Version %s", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
        log.log(Level.INFO, String.format("[%s] reloaded Version %s", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
    }

    /**
     * Setzt einen Testvote ab.
     * 
     * @param args
     *            Befehls Argumente
     * @param sender
     *            Absender des Befehls
     * @throws com.sk89q.minecraft.util.commands.CommandException
     *             CommandException
     */
    @Command(aliases = { "vote" }, desc = "Setzt einen Testvote ab")
    @CommandPermissions("nextvote.test")
    public final void vote(final CommandContext args, final CommandSender sender) throws CommandException {
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
     *            Befehls Argumente
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
