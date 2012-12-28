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
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.avaje.ebean.EbeanServer;
import com.github.calenria.nextvote.NextVote;
import com.github.calenria.nextvote.NextVoteRadomItem;
import com.github.calenria.nextvote.models.VoteData;
import com.github.calenria.nextvote.models.VoteHistory;

public class NextVoteManager {
	private static Logger log = Logger.getLogger("Minecraft");
	private NextVote plugin = null;
	private EbeanServer database = null;

	public NextVoteManager(NextVote plugin) {
		this.plugin = plugin;
		try {
			plugin.getDatabase().find(VoteHistory.class).findRowCount();
		} catch (PersistenceException ex) {
			log.log(Level.INFO, "Installing database for " + plugin.getDescription().getName() + " due to first time usage");
			plugin.installDDL();
		}
		this.database = plugin.getDatabase();
	}

	public void doVote(String player) {
		// Check Player exists!
		OfflinePlayer thePlayer = Bukkit.getOfflinePlayer(player);
		if (!thePlayer.hasPlayedBefore()) {
			log.info(String.format(plugin.messages.getString("player.never.played"), player));
			return;
		}

		VoteHistory vote = new VoteHistory();
		vote.setMinecraftUser(player);
		if (plugin.config.isFixEcon() || plugin.config.isOnlyEcon()) {
			vote.setEconAmmount(plugin.config.getFixEconAmmount());
			vote.setEcon(true);
		}
		if (!plugin.config.isOnlyEcon()) {
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
		log.info(String.format(plugin.messages.getString("player.voted"), player));
	}

	private void doMessage(OfflinePlayer oPlayer, VoteHistory vote) {
		if (!plugin.config.isShowBroadcast()) {
			return;
		}
		List<String> translatedList = new ArrayList<String>();
		if (vote.hasEcon() && vote.hasItem()) {
			List<String> msgList = plugin.config.getBroadcastMessageItemEcon();
			for (String msg : msgList) {
				msg = msg.replaceAll("<player>", oPlayer.getName());
				msg = msg.replaceAll("<econ>", NextVote.economy.format(vote.getEconAmmount()));
				msg = msg.replaceAll("<itemamount>", String.valueOf(vote.getAmmount()));
				msg = msg.replaceAll("<itemname>", vote.getLocalName());
				translatedList.add(ChatColor.translateAlternateColorCodes('&', msg));
			}
			broadcastMessage(translatedList);
			return;
		}
		
		System.out.println(vote.hasEcon());
		
		if (vote.hasEcon()) {
			List<String> msgList = plugin.config.getBroadcastMessageEcon();
			for (String msg : msgList) {
				msg = msg.replaceAll("<player>", oPlayer.getName());
				msg = msg.replaceAll("<econ>", NextVote.economy.format(vote.getEconAmmount()));
				translatedList.add(ChatColor.translateAlternateColorCodes('&', msg));
			}
			broadcastMessage(translatedList);
			return;
		}
		if (vote.hasItem()) {
			List<String> msgList = plugin.config.getBroadcastMessageItem();
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

	private void broadcastMessage(List<String> translatedList) {
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players) {
			if (!plugin.currVotes.contains(player.getName())) {
				player.sendMessage(translatedList.toArray(new String[0]));
			} else {
				player.sendMessage(translatedList.get(0));
			}
		}
	}

	public boolean payVote(OfflinePlayer oPlayer, VoteHistory vote) {
		if (!oPlayer.isOnline()) {
			return false;
		}
		Player player = oPlayer.getPlayer();
		PlayerInventory pInv = player.getInventory();
		// Always pay Econ
		if (vote.hasEcon() && !vote.isPaidEcon()) {
			NextVote.economy.depositPlayer(player.getName(), vote.getEconAmmount());
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
			return true;
		} else {
			return false;
		}
	}

	public void currVoteSheduler() {
		plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
			public void run() {
				currVote();
			}
		}, 20L, 20 * 60);
	}

	public void payVoteSheduler() {
		plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
			public void run() {
				payVote();
			}
		}, 20L, 20 * 60);
	}

	private void payVote() {
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -30);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 1);
		List<VoteHistory> votes = database.find(VoteHistory.class).where().ge("time", cal.getTime()).eq("paid", false).findList();
		if (votes != null && votes.size() > 0) {
			log.info(String.format(plugin.messages.getString("votes.not.paid"), votes.size()));
			for (VoteHistory voteData : votes) {
				Player player = Bukkit.getPlayer(voteData.getMinecraftUser());
				if (player != null && player.isOnline()) {
					voteData.setPaid(payVote(player, voteData));
					database.save(voteData);
				}
			}
		}
	}

	private void currVote() {
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 1);
		List<VoteData> votes = database.find(VoteData.class).where().ge("time", cal.getTime()).findList();
		synchronized (plugin.currVotes) {
			plugin.currVotes.clear();
			if (votes != null && votes.size() > 0) {
				for (VoteData voteData : votes) {
					plugin.currVotes.add(voteData.getMinecraftUser());
				}
			}
		}
		log.info(String.format(plugin.messages.getString("votes.per.day"), plugin.currVotes.size()));
	}

}
