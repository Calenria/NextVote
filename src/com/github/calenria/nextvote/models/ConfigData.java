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
package com.github.calenria.nextvote.models;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.calenria.nextvote.NextVote;

/**
 * Konfigurations Klasse.
 * 
 * @author Calenria
 * 
 */
public class ConfigData {

    /**
     * Die gewählte Sprache.
     */
    private String       lang;
    /**
     * Liste von Strings die bei /vote ausgegeben wird.
     */
    private List<String> voteInfo;
    /**
     * Liste von String die als Danke für einen Vote ausgegeben wird.
     */
    private List<String> thxVote;
    /**
     *
     */
    private List<String> noVote;
    /**
     *
     */
    private List<String> dayVote;
    /**
     *
     */
    private List<String> daysVote;
    /**
     *
     */
    private List<String> infoVote;
    /**
     *
     */
    private boolean      showBroadcast;
    /**
     *
     */
    private boolean      bonusItem;
    /**
     *
     */
    private int          bonusChance;
    /**
     *
     */
    private int          poorChance;
    /**
     *
     */
    private int          averageChance;
    /**
     *
     */
    private int          goodChance;
    /**
     *
     */
    private int          topChance;
    /**
     *
     */
    private int          poorMinAmount;
    /**
     *
     */
    private int          poorMaxAmount;
    /**
     *
     */
    private int          averageMinAmount;
    /**
     *
     */
    private int          averageMaxAmount;
    /**
     *
     */
    private int          goodMinAmount;
    /**
     *
     */
    private int          goodMaxAmount;
    /**
     *
     */
    private int          topMinAmount;
    /**
     *
     */
    private int          topMaxAmount;
    /**
     *
     */
    private List<String> poorItems;
    /**
     *
     */
    private List<String> averageItems;
    /**
     *
     */
    private List<String> goodItems;
    /**
     *
     */
    private List<String> topItems;
    /**
     *
     */
    private List<String> broadcastMessageItem;
    /**
     *
     */
    private List<String> broadcastMessageEcon;
    /**
     *
     */
    private List<String> broadcastMessageItemEcon;
    /**
     *
     */
    private List<String> fireworkColors;
    /**
     *
     */
    private List<String> fireworkFadeColors;
    /**
     *
     */
    private String       fireworkType;
    /**
     *
     */
    private int          fireworkPower;
    /**
     *
     */
    private boolean      firework;
    /**
     *
     */
    private boolean      onlyEcon;
    /**
     *
     */
    private boolean      fixEcon;
    /**
     *
     */
    private int          fixEconAmmount;

    /**
     * @param plugin
     *            NextVote Plugin
     */
    public ConfigData(final NextVote plugin) {
        FileConfiguration config = plugin.getConfig();
        setLang(config.getString("lang"));
        setAverageItems(config.getStringList("averageItems"));
        setAverageMaxAmount(config.getInt("averageMaxAmount"));
        setAverageMinAmount(config.getInt("averageMinAmount"));
        setBonusChance(config.getInt("bonusChance"));
        setBonusItem(config.getBoolean("bonusItem"));
        setBroadcastMessageEcon(config.getStringList("broadcastMessageEcon"));
        setBroadcastMessageItem(config.getStringList("broadcastMessageItem"));
        setBroadcastMessageItemEcon(config.getStringList("broadcastMessageItemEcon"));
        setDaysVote(config.getStringList("daysVote"));
        setDayVote(config.getStringList("dayVote"));
        setGoodChance(config.getInt("goodChance"));
        setGoodItems(config.getStringList("goodItems"));
        setGoodMaxAmount(config.getInt("goodMaxAmount"));
        setGoodMinAmount(config.getInt("goodMinAmount"));
        setInfoVote(config.getStringList("infoVote"));
        setNoVote(config.getStringList("noVote"));
        setPoorChance(config.getInt("poorChance"));
        setPoorItems(config.getStringList("poorItems"));
        setPoorMaxAmount(config.getInt("poorMaxAmount"));
        setPoorMinAmount(config.getInt("poorMinAmount"));
        setShowBroadcast(config.getBoolean("showBroadcast"));
        setThxVote(config.getStringList("thxVote"));
        setTopChance(config.getInt("topChance"));
        setTopItems(config.getStringList("topItems"));
        setTopMaxAmount(config.getInt("topMaxAmount"));
        setTopMinAmount(config.getInt("topMinAmount"));
        setVoteInfo(config.getStringList("voteInfo"));
        setOnlyEcon(config.getBoolean("onlyEcon"));
        setFixEcon(config.getBoolean("fixEcon"));
        setFixEconAmmount(config.getInt("fixEconAmmount"));
        setFireworkColors(config.getStringList("fireworkColors"));
        setFireworkFadeColors(config.getStringList("fireworkFadeColors"));
        setFireworkType(config.getString("fireworkType"));
        setFireworkPower(config.getInt("fireworkPower"));
        setFirework(config.getBoolean("firework"));
    }

    /**
     * @return the averageChance
     */
    public final int getAverageChance() {
        return averageChance;
    }

    /**
     * @return the averageItems
     */
    public final List<String> getAverageItems() {
        return averageItems;
    }

    /**
     * @return the averageMaxAmount
     */
    public final int getAverageMaxAmount() {
        return averageMaxAmount;
    }

    /**
     * @return the averageMinAmount
     */
    public final int getAverageMinAmount() {
        return averageMinAmount;
    }

    /**
     * @return the bonusChance
     */
    public final int getBonusChance() {
        return bonusChance;
    }

    /**
     * @return the broadcastMessageEcon
     */
    public final List<String> getBroadcastMessageEcon() {
        return broadcastMessageEcon;
    }

    /**
     * @return the broadcastMessageItem
     */
    public final List<String> getBroadcastMessageItem() {
        return broadcastMessageItem;
    }

    /**
     * @return the getBroadcastMessageItemEcon
     */
    public final List<String> getBroadcastMessageItemEcon() {
        return broadcastMessageItemEcon;
    }

    /**
     * @return the daysVote
     */
    public final List<String> getDaysVote() {
        return daysVote;
    }

    /**
     * @return the dayVote
     */
    public final List<String> getDayVote() {
        return dayVote;
    }

    /**
     * @return the fireworkColors
     */
    public final List<String> getFireworkColors() {
        return fireworkColors;
    }

    /**
     * @return the fireworkFadeColors
     */
    public final List<String> getFireworkFadeColors() {
        return fireworkFadeColors;
    }

    /**
     * @return the fireworkPower
     */
    public final int getFireworkPower() {
        return fireworkPower;
    }

    /**
     * @return the fireworkType
     */
    public final String getFireworkType() {
        return fireworkType;
    }

    /**
     * @return the fixEconAmmount
     */
    public final int getFixEconAmmount() {
        return fixEconAmmount;
    }

    /**
     * @return the goodChance
     */
    public final int getGoodChance() {
        return goodChance;
    }

    /**
     * @return the goodItems
     */
    public final List<String> getGoodItems() {
        return goodItems;
    }

    /**
     * @return the goodMaxAmount
     */
    public final int getGoodMaxAmount() {
        return goodMaxAmount;
    }

    /**
     * @return the goodMinAmount
     */
    public final int getGoodMinAmount() {
        return goodMinAmount;
    }

    /**
     * @return the infoVote
     */
    public final List<String> getInfoVote() {
        return infoVote;
    }

    /**
     * @return the lang
     */
    public final String getLang() {
        return lang;
    }

    /**
     * @return the noVote
     */
    public final List<String> getNoVote() {
        return noVote;
    }

    /**
     * @return the poorChance
     */
    public final int getPoorChance() {
        return poorChance;
    }

    /**
     * @return the poorItems
     */
    public final List<String> getPoorItems() {
        return poorItems;
    }

    /**
     * @return the poorMaxAmount
     */
    public final int getPoorMaxAmount() {
        return poorMaxAmount;
    }

    /**
     * @return the poorMinAmount
     */
    public final int getPoorMinAmount() {
        return poorMinAmount;
    }

    /**
     * @return the thxVote
     */
    public final List<String> getThxVote() {
        return thxVote;
    }

    /**
     * @return the topChance
     */
    public final int getTopChance() {
        return topChance;
    }

    /**
     * @return the topItems
     */
    public final List<String> getTopItems() {
        return topItems;
    }

    /**
     * @return the topMaxAmount
     */
    public final int getTopMaxAmount() {
        return topMaxAmount;
    }

    /**
     * @return the topMinAmount
     */
    public final int getTopMinAmount() {
        return topMinAmount;
    }

    /**
     * @return the voteInfo
     */
    public final List<String> getVoteInfo() {
        return voteInfo;
    }

    /**
     * @return the bonusItem
     */
    public final boolean isBonusItem() {
        return bonusItem;
    }

    /**
     * @return the firework
     */
    public final boolean isFirework() {
        return firework;
    }

    /**
     * @return the fixEcon
     */
    public final boolean isFixEcon() {
        return fixEcon;
    }

    /**
     * @return the onlyEcon
     */
    public final boolean isOnlyEcon() {
        return onlyEcon;
    }

    /**
     * @return the showBroadcast
     */
    public final boolean isShowBroadcast() {
        return showBroadcast;
    }

    /**
     * @param cAverageChance
     *            the averageChance to set
     */
    public final void setAverageChance(final int cAverageChance) {
        this.averageChance = cAverageChance;
    }

    /**
     * @param cAverageItems
     *            the averageItems to set
     */
    public final void setAverageItems(final List<String> cAverageItems) {
        this.averageItems = cAverageItems;
    }

    /**
     * @param cAverageMaxAmount
     *            the averageMaxAmount to set
     */
    public final void setAverageMaxAmount(final int cAverageMaxAmount) {
        this.averageMaxAmount = cAverageMaxAmount;
    }

    /**
     * @param cAverageMinAmount
     *            the averageMinAmount to set
     */
    public final void setAverageMinAmount(final int cAverageMinAmount) {
        this.averageMinAmount = cAverageMinAmount;
    }

    /**
     * @param cBonusChance
     *            the bonusChance to set
     */
    public final void setBonusChance(final int cBonusChance) {
        this.bonusChance = cBonusChance;
    }

    /**
     * @param cBonusItem
     *            the bonusItem to set
     */
    public final void setBonusItem(final boolean cBonusItem) {
        this.bonusItem = cBonusItem;
    }

    /**
     * @param cBroadcastMessageEcon
     *            the broadcastMessageEcon to set
     */
    public final void setBroadcastMessageEcon(final List<String> cBroadcastMessageEcon) {
        this.broadcastMessageEcon = cBroadcastMessageEcon;
    }

    /**
     * @param cBroadcastMessageItem
     *            the broadcastMessageItem to set
     */
    public final void setBroadcastMessageItem(final List<String> cBroadcastMessageItem) {
        this.broadcastMessageItem = cBroadcastMessageItem;
    }

    /**
     * @param cBroadcastMessageItemEcon
     *            the broadcastMessageItemEcon to set
     */
    public final void setBroadcastMessageItemEcon(final List<String> cBroadcastMessageItemEcon) {
        this.broadcastMessageItemEcon = cBroadcastMessageItemEcon;

    }

    /**
     * @param cDaysVote
     *            the daysVote to set
     */
    public final void setDaysVote(final List<String> cDaysVote) {
        this.daysVote = cDaysVote;
    }

    /**
     * @param cDayVote
     *            the dayVote to set
     */
    public final void setDayVote(final List<String> cDayVote) {
        this.dayVote = cDayVote;
    }

    /**
     * @param cFirework
     *            the firework to set
     */
    public final void setFirework(final boolean cFirework) {
        this.firework = cFirework;
    }

    /**
     * @param cFireworkColors
     *            the fireworkColors to set
     */
    public final void setFireworkColors(final List<String> cFireworkColors) {
        this.fireworkColors = cFireworkColors;
    }

    /**
     * @param cFireworkFadeColors
     *            the fireworkFadeColors to set
     */
    public final void setFireworkFadeColors(final List<String> cFireworkFadeColors) {
        this.fireworkFadeColors = cFireworkFadeColors;
    }

    /**
     * @param cFireworkPower
     *            the cFireworkPower to set
     */
    public final void setFireworkPower(final int cFireworkPower) {
        this.fireworkPower = cFireworkPower;
    }

    /**
     * @param cFireworkType
     *            the fireworkType to set
     */
    public final void setFireworkType(final String cFireworkType) {
        this.fireworkType = cFireworkType;
    }

    /**
     * @param cFixEcon
     *            the fixEcon to set
     */
    public final void setFixEcon(final boolean cFixEcon) {
        this.fixEcon = cFixEcon;
    }

    /**
     * @param cFixEconAmmount
     *            the fixEconAmmount to set
     */
    public final void setFixEconAmmount(final int cFixEconAmmount) {
        this.fixEconAmmount = cFixEconAmmount;
    }

    /**
     * @param cGoodChance
     *            the goodChance to set
     */
    public final void setGoodChance(final int cGoodChance) {
        this.goodChance = cGoodChance;
    }

    /**
     * @param cGoodItems
     *            the goodItems to set
     */
    public final void setGoodItems(final List<String> cGoodItems) {
        this.goodItems = cGoodItems;
    }

    /**
     * @param cGoodMaxAmount
     *            the goodMaxAmount to set
     */
    public final void setGoodMaxAmount(final int cGoodMaxAmount) {
        this.goodMaxAmount = cGoodMaxAmount;
    }

    /**
     * @param cGoodMinAmount
     *            the goodMinAmount to set
     */
    public final void setGoodMinAmount(final int cGoodMinAmount) {
        this.goodMinAmount = cGoodMinAmount;
    }

    /**
     * @param cInfoVote
     *            the infoVote to set
     */
    public final void setInfoVote(final List<String> cInfoVote) {
        this.infoVote = cInfoVote;
    }

    /**
     * @param cLang
     *            the lang to set
     */
    public final void setLang(final String cLang) {
        this.lang = cLang;
    }

    /**
     * @param cNoVote
     *            the noVote to set
     */
    public final void setNoVote(final List<String> cNoVote) {
        this.noVote = cNoVote;
    }

    /**
     * @param cOnlyEcon
     *            the onlyEcon to set
     */
    public final void setOnlyEcon(final boolean cOnlyEcon) {
        this.onlyEcon = cOnlyEcon;
    }

    /**
     * @param cPoorChance
     *            the poorChance to set
     */
    public final void setPoorChance(final int cPoorChance) {
        this.poorChance = cPoorChance;
    }

    /**
     * @param cPoorItems
     *            the poorItems to set
     */
    public final void setPoorItems(final List<String> cPoorItems) {
        this.poorItems = cPoorItems;
    }

    /**
     * @param cPoorMaxAmount
     *            the poorMaxAmount to set
     */
    public final void setPoorMaxAmount(final int cPoorMaxAmount) {
        this.poorMaxAmount = cPoorMaxAmount;
    }

    /**
     * @param cPoorMinAmount
     *            the poorMinAmount to set
     */
    public final void setPoorMinAmount(final int cPoorMinAmount) {
        this.poorMinAmount = cPoorMinAmount;
    }

    /**
     * @param cShowBroadcast
     *            the showBroadcast to set
     */
    public final void setShowBroadcast(final boolean cShowBroadcast) {
        this.showBroadcast = cShowBroadcast;
    }

    /**
     * @param cThxVote
     *            the thxVote to set
     */
    public final void setThxVote(final List<String> cThxVote) {
        this.thxVote = cThxVote;
    }

    /**
     * @param cTopChance
     *            the topChance to set
     */
    public final void setTopChance(final int cTopChance) {
        this.topChance = cTopChance;
    }

    /**
     * @param cTopItems
     *            the topItems to set
     */
    public final void setTopItems(final List<String> cTopItems) {
        this.topItems = cTopItems;
    }

    /**
     * @param cTopMaxAmount
     *            the topMaxAmount to set
     */
    public final void setTopMaxAmount(final int cTopMaxAmount) {
        this.topMaxAmount = cTopMaxAmount;
    }

    /**
     * @param cTopMinAmount
     *            the topMinAmount to set
     */
    public final void setTopMinAmount(final int cTopMinAmount) {
        this.topMinAmount = cTopMinAmount;
    }

    /**
     * @param cVoteInfo
     *            the voteInfo to set
     */
    public final void setVoteInfo(final List<String> cVoteInfo) {
        this.voteInfo = cVoteInfo;
    }

}
