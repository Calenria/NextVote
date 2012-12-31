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

package com.github.calenria.nextvote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;

public class Utils {
	
	private static HashMap<String,ChatColor> colorMap = new HashMap<String,ChatColor>(); 
	
	public static void fillColorMap() {
		colorMap.put("<AQUA>", ChatColor.AQUA);
		colorMap.put("<BLACK>", ChatColor.BLACK);
		colorMap.put("<BLUE>", ChatColor.BLUE);
		colorMap.put("<BOLD>", ChatColor.BOLD);
		colorMap.put("<DARK_AQUA>", ChatColor.DARK_AQUA);
		colorMap.put("<DARK_BLUE>", ChatColor.DARK_BLUE);
		colorMap.put("<DARK_GRAY>", ChatColor.DARK_GRAY);
		colorMap.put("<DARK_GREEN>", ChatColor.DARK_GREEN);
		colorMap.put("<DARK_PURPLE>", ChatColor.DARK_PURPLE);
		colorMap.put("<DARK_RED>", ChatColor.DARK_RED);
		colorMap.put("<GOLD>", ChatColor.GOLD);
		colorMap.put("<GRAY>", ChatColor.GRAY);
		colorMap.put("<GREEN>", ChatColor.GREEN);
		colorMap.put("<ITALIC>", ChatColor.ITALIC);
		colorMap.put("<LIGHT_PURPLE>", ChatColor.LIGHT_PURPLE);
		colorMap.put("<MAGIC>", ChatColor.MAGIC);
		colorMap.put("<RED>", ChatColor.RED);
		colorMap.put("<RESET>", ChatColor.RESET);
		colorMap.put("<STRIKETHROUGH>", ChatColor.STRIKETHROUGH);
		colorMap.put("<UNDERLINE>", ChatColor.UNDERLINE);
		colorMap.put("<WHITE>", ChatColor.WHITE);
		colorMap.put("<YELLOW>", ChatColor.YELLOW);
	}
	final public static HashMap<String, Color> colorFireworkMap = new HashMap<String, Color>() {
		private static final long serialVersionUID = 1L;
		{
			put("AQUA", Color.AQUA);
			put("BLACK", Color.BLACK);
			put("BLUE", Color.BLUE);
			put("BLUE", Color.FUCHSIA);
			put("GRAY", Color.GRAY);
			put("GREEN", Color.GREEN);
			put("LIME", Color.LIME);
			put("MAROON", Color.MAROON);
			put("NAVY", Color.NAVY);
			put("OLIVE", Color.OLIVE);
			put("ORANGE", Color.ORANGE);
			put("PURPLE", Color.PURPLE);
			put("RED", Color.RED);
			put("SILVER", Color.SILVER);
			put("TEAL", Color.TEAL);
			put("WHITE", Color.WHITE);
			put("YELLOW", Color.YELLOW);
		}
	};
	public static String replaceColors(String text) {
		text = text.trim();
		fillColorMap();
		for (String replKey : colorMap.keySet()) {
			text = text.replaceAll(replKey.toLowerCase(), String.valueOf(colorMap.get(replKey).toString())  );
		}

		return ChatColor.translateAlternateColorCodes('&',text);
	}
	
	public static String replacePlayerName(String text, Player player) {
		text = text.trim();
		text = text.replaceAll("<player>", player.getName());
		text = text.replaceAll("<playerdn>", player.getDisplayName());
		text = replaceColors(text);
		return text;
	}
	
	public static String replacePlayerName(String text, Player player, String days) {
		text = text.trim();
		text = text.replaceAll("<days>", days);
		text = replacePlayerName(text,player);
		text = replaceColors(text);
		return text;
	}
	
	public static String implodeArray(String[] inputArray, String glueString) {

		/** Output variable */
		String output = "";

		if (inputArray.length > 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(inputArray[0]);

			for (int i = 1; i < inputArray.length; i++) {
				sb.append(glueString);
				sb.append(inputArray[i]);
			}

			output = sb.toString();
		}

		return output;
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		long daysBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}
	
	public static long daysBetweenMidnight(Date startDate, Date endDate) {
		Calendar startCal = new GregorianCalendar();
		startCal.setTime(startDate);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		startCal.set(Calendar.MILLISECOND, 0);

		Calendar endCal = new GregorianCalendar();
		endCal.setTime(endDate);
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.SECOND, 0);
		endCal.set(Calendar.MILLISECOND, 0);

		Calendar date = (Calendar) startCal.clone();

		long daysBetween = 0;
		while (date.before(endCal)) {
			date.add(Calendar.DAY_OF_MONTH, 1);
			daysBetween++;
		}
		return daysBetween;
	}
	
	public static void copy(InputStream input, File target) throws IOException {
		if (target.exists()) {
			throw new IOException("File already exists!");
		}

		if (!target.createNewFile()) {
			throw new IOException("Failed at creating new empty file!");
		}

		byte[] buffer = new byte[1024];

		OutputStream output = new FileOutputStream(target);

		int realLength;

		while ((realLength = input.read(buffer)) > 0) {
			output.write(buffer, 0, realLength);
		}

		output.flush();
		output.close();
	}

}
