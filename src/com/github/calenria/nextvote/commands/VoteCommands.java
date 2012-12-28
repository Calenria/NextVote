package com.github.calenria.nextvote.commands;

import java.sql.Timestamp;

import org.bukkit.command.CommandSender;

import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.models.VoteData;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class VoteCommands {
	private final NextVote plugin;

	public VoteCommands(NextVote plugin) {
		this.plugin = plugin;
	}

	@Command(aliases = { "reload", "rl" }, desc = "Läd das Plugin neu", usage = "reload")
	@CommandPermissions("nextvote.reload")
	public void reload(CommandContext args, CommandSender sender) throws CommandException {
		plugin.setupConfig();
		plugin.setupLang();
	}

	@Command(aliases = { "test" }, desc = "Debug")
	@CommandPermissions("nextvote.test")
	public void test(CommandContext args, CommandSender sender) throws CommandException {
		VoteData voteData = new VoteData();
		voteData.setMinecraftUser(sender.getName());
		voteData.setTime(new Timestamp(System.currentTimeMillis()));
		voteData.setService("ExtraVote");
		voteData.setIp("");
		plugin.getDatabase().save(voteData);
		plugin.currVotes.add(voteData.getMinecraftUser());
		plugin.nextVoteManager.doVote(sender.getName());
	}

	@Command(aliases = { "votefor" }, desc = "Votet für einen Spieler", usage = "spielername", max = 1, min = 1)
	@CommandPermissions("nextvote.votefor")
	public void votefor(CommandContext args, CommandSender sender) throws CommandException {
		VoteData voteData = new VoteData();
		voteData.setMinecraftUser(args.getString(0));
		voteData.setTime(new Timestamp(System.currentTimeMillis()));
		voteData.setService("ExtraVote");
		voteData.setIp("");
		plugin.getDatabase().save(voteData);
		plugin.currVotes.add(voteData.getMinecraftUser());
		plugin.nextVoteManager.doVote(args.getString(0));
	}
}
