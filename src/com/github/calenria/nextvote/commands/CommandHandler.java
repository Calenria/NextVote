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

public class CommandHandler {
	private final NextVote plugin;

	public CommandHandler(NextVote plugin) {
		this.plugin = plugin;
	}

	@Command(aliases = { "nv", "nextvote" }, desc = "NextVote Commands")
	@NestedCommand({ VoteCommands.class })
	public void nextvote(CommandContext args, CommandSender sender) {
	}

	@Command(aliases = { "vote" }, desc = "Zeigt Informationen zum Voten an", usage = "/vote", min = 0, max = 0)
	@CommandPermissions("nextvote.vote")
	public void vote(CommandContext args, CommandSender sender) throws CommandException {
		List<String> voteInfo = plugin.config.getVoteInfo();
		for (String string : voteInfo) {
			sender.sendMessage(Utils.replaceColors(string));
		}
	}
}
