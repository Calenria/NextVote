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

import java.util.List;

import org.bukkit.command.CommandSender;

import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.Utils;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.NestedCommand;

/**
 * Befehls Klasse für NextVote.
 * 
 * @author Calenria
 * 
 */
public class CommandHandler {
    /**
     *
     */
    private final NextVote plugin;

    /**
     * @param nvPlugin
     *            Plugin
     */
    public CommandHandler(final NextVote nvPlugin) {
        this.plugin = nvPlugin;
    }

    /**
     * Delegiert Befehle an die Klasse VoteCommands.
     * 
     * @see com.github.calenria.nextvote.commands.VoteCommands
     * @param args
     *            Befehls Argumente
     * @param sender
     *            Absender des Befehls
     */
    @Command(aliases = { "nv", "nextvote" }, desc = "NextVote Commands")
    @NestedCommand({ VoteCommands.class })
    public void nextvote(final CommandContext args, final CommandSender sender) {
    }

    /**
     * Der Befehl /vote, gibt Informationen zum Voten aus.
     * 
     * @param args
     *            Befehls Argumente
     * @param sender
     *            Absender des Befehls
     * @throws CommandException
     *             CommandException
     */
    @Command(aliases = { "vote" }, desc = "Zeigt Informationen zum Voten an", usage = "/vote", min = 0, max = 0)
    @CommandPermissions("nextvote.vote")
    public final void vote(final CommandContext args, final CommandSender sender) throws CommandException {
        List<String> voteInfo = plugin.getPluginConfig().getVoteInfo();
        for (String string : voteInfo) {
            sender.sendMessage(Utils.replaceColors(string));
        }
    }
}
