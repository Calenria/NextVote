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

public class VoteCommands {
	private final NextVote plugin;
	private static Logger log = Logger.getLogger("Minecraft");

	public VoteCommands(NextVote plugin) {
		this.plugin = plugin;
	}

	@Command(aliases = { "reload", "rl" }, desc = "Läd das Plugin neu", usage = "reload")
	@CommandPermissions("nextvote.reload")
	public void reload(CommandContext args, CommandSender sender) throws CommandException {
		plugin.setupConfig();
		plugin.setupLang();
		sender.sendMessage(String.format("[%s] reloaded Version %s", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
		log.log(Level.INFO, String.format("[%s] reloaded Version %s", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
	}

	@Command(aliases = { "test" }, desc = "Debug")
	@CommandPermissions("nextvote.test")
	public void test(CommandContext args, CommandSender sender) throws CommandException {
		sender.sendMessage(String.format("[%s] Testvote sucess!", plugin.getDescription().getName()));
		log.log(Level.INFO, String.format("[%s] Testvote sucess!", plugin.getDescription().getName()));
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
		sender.sendMessage(String.format("[%s] Vote for %s sucess!", plugin.getDescription().getName(), args.getString(0)));
		log.log(Level.INFO, String.format("[%s] Vote for %s sucess!", plugin.getDescription().getName(), args.getString(0)));
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
