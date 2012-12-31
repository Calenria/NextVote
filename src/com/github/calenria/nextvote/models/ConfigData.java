package com.github.calenria.nextvote.models;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.calenria.nextvote.NextVote;

public class ConfigData {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger("Minecraft");

    private String lang;
    private List<String> voteInfo;
    private List<String> thxVote;
    private List<String> noVote;
    private List<String> dayVote;
    private List<String> daysVote;
    private List<String> infoVote;
    private boolean showBroadcast;
    private boolean bonusItem;
    private int bonusChance;
    private int poorChance;
    private int averageChance;
    private int goodChance;
    private int topChance;
    private int poorMinAmount;
    private int poorMaxAmount;
    private int averageMinAmount;
    private int averageMaxAmount;
    private int goodMinAmount;
    private int goodMaxAmount;
    private int topMinAmount;
    private int topMaxAmount;
    private List<String> poorItems;
    private List<String> averageItems;
    private List<String> goodItems;
    private List<String> topItems;
    private List<String> broadcastMessageItem;
    private List<String> broadcastMessageEcon;
    private List<String> broadcastMessageItemEcon;
    private List<String> fireworkColors;
    private List<String> fireworkFadeColors;
    private String fireworkType;
    private int fireworkPower;
    private boolean firework;

    private boolean onlyEcon;
    private boolean fixEcon;
    private int fixEconAmmount;

    public ConfigData(NextVote plugin) {
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

    public void setBroadcastMessageItemEcon(List<String> broadcastMessageItemEcon) {
        this.broadcastMessageItemEcon = broadcastMessageItemEcon;

    }

    public List<String> getBroadcastMessageItemEcon() {
        return broadcastMessageItemEcon;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang
     *            the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the voteInfo
     */
    public List<String> getVoteInfo() {
        return voteInfo;
    }

    /**
     * @param voteInfo
     *            the voteInfo to set
     */
    public void setVoteInfo(List<String> voteInfo) {
        this.voteInfo = voteInfo;
    }

    /**
     * @return the thxVote
     */
    public List<String> getThxVote() {
        return thxVote;
    }

    /**
     * @param thxVote
     *            the thxVote to set
     */
    public void setThxVote(List<String> thxVote) {
        this.thxVote = thxVote;
    }

    /**
     * @return the noVote
     */
    public List<String> getNoVote() {
        return noVote;
    }

    /**
     * @param noVote
     *            the noVote to set
     */
    public void setNoVote(List<String> noVote) {
        this.noVote = noVote;
    }

    /**
     * @return the dayVote
     */
    public List<String> getDayVote() {
        return dayVote;
    }

    /**
     * @param dayVote
     *            the dayVote to set
     */
    public void setDayVote(List<String> dayVote) {
        this.dayVote = dayVote;
    }

    /**
     * @return the daysVote
     */
    public List<String> getDaysVote() {
        return daysVote;
    }

    /**
     * @param daysVote
     *            the daysVote to set
     */
    public void setDaysVote(List<String> daysVote) {
        this.daysVote = daysVote;
    }

    /**
     * @return the infoVote
     */
    public List<String> getInfoVote() {
        return infoVote;
    }

    /**
     * @param infoVote
     *            the infoVote to set
     */
    public void setInfoVote(List<String> infoVote) {
        this.infoVote = infoVote;
    }

    /**
     * @return the showBroadcast
     */
    public boolean isShowBroadcast() {
        return showBroadcast;
    }

    /**
     * @param showBroadcast
     *            the showBroadcast to set
     */
    public void setShowBroadcast(boolean showBroadcast) {
        this.showBroadcast = showBroadcast;
    }

    /**
     * @return the bonusItem
     */
    public boolean isBonusItem() {
        return bonusItem;
    }

    /**
     * @param bonusItem
     *            the bonusItem to set
     */
    public void setBonusItem(boolean bonusItem) {
        this.bonusItem = bonusItem;
    }

    /**
     * @return the bonusChance
     */
    public int getBonusChance() {
        return bonusChance;
    }

    /**
     * @param bonusChance
     *            the bonusChance to set
     */
    public void setBonusChance(int bonusChance) {
        this.bonusChance = bonusChance;
    }

    /**
     * @return the poorChance
     */
    public int getPoorChance() {
        return poorChance;
    }

    /**
     * @param poorChance
     *            the poorChance to set
     */
    public void setPoorChance(int poorChance) {
        this.poorChance = poorChance;
    }

    /**
     * @return the averageChance
     */
    public int getAverageChance() {
        return averageChance;
    }

    /**
     * @param averageChance
     *            the averageChance to set
     */
    public void setAverageChance(int averageChance) {
        this.averageChance = averageChance;
    }

    /**
     * @return the goodChance
     */
    public int getGoodChance() {
        return goodChance;
    }

    /**
     * @param goodChance
     *            the goodChance to set
     */
    public void setGoodChance(int goodChance) {
        this.goodChance = goodChance;
    }

    /**
     * @return the topChance
     */
    public int getTopChance() {
        return topChance;
    }

    /**
     * @param topChance
     *            the topChance to set
     */
    public void setTopChance(int topChance) {
        this.topChance = topChance;
    }

    /**
     * @return the poorMinAmount
     */
    public int getPoorMinAmount() {
        return poorMinAmount;
    }

    /**
     * @param poorMinAmount
     *            the poorMinAmount to set
     */
    public void setPoorMinAmount(int poorMinAmount) {
        this.poorMinAmount = poorMinAmount;
    }

    /**
     * @return the poorMaxAmount
     */
    public int getPoorMaxAmount() {
        return poorMaxAmount;
    }

    /**
     * @param poorMaxAmount
     *            the poorMaxAmount to set
     */
    public void setPoorMaxAmount(int poorMaxAmount) {
        this.poorMaxAmount = poorMaxAmount;
    }

    /**
     * @return the averageMinAmount
     */
    public int getAverageMinAmount() {
        return averageMinAmount;
    }

    /**
     * @param averageMinAmount
     *            the averageMinAmount to set
     */
    public void setAverageMinAmount(int averageMinAmount) {
        this.averageMinAmount = averageMinAmount;
    }

    /**
     * @return the averageMaxAmount
     */
    public int getAverageMaxAmount() {
        return averageMaxAmount;
    }

    /**
     * @param averageMaxAmount
     *            the averageMaxAmount to set
     */
    public void setAverageMaxAmount(int averageMaxAmount) {
        this.averageMaxAmount = averageMaxAmount;
    }

    /**
     * @return the goodMinAmount
     */
    public int getGoodMinAmount() {
        return goodMinAmount;
    }

    /**
     * @param goodMinAmount
     *            the goodMinAmount to set
     */
    public void setGoodMinAmount(int goodMinAmount) {
        this.goodMinAmount = goodMinAmount;
    }

    /**
     * @return the goodMaxAmount
     */
    public int getGoodMaxAmount() {
        return goodMaxAmount;
    }

    /**
     * @param goodMaxAmount
     *            the goodMaxAmount to set
     */
    public void setGoodMaxAmount(int goodMaxAmount) {
        this.goodMaxAmount = goodMaxAmount;
    }

    /**
     * @return the topMinAmount
     */
    public int getTopMinAmount() {
        return topMinAmount;
    }

    /**
     * @param topMinAmount
     *            the topMinAmount to set
     */
    public void setTopMinAmount(int topMinAmount) {
        this.topMinAmount = topMinAmount;
    }

    /**
     * @return the topMaxAmount
     */
    public int getTopMaxAmount() {
        return topMaxAmount;
    }

    /**
     * @param topMaxAmount
     *            the topMaxAmount to set
     */
    public void setTopMaxAmount(int topMaxAmount) {
        this.topMaxAmount = topMaxAmount;
    }

    /**
     * @return the poorItems
     */
    public List<String> getPoorItems() {
        return poorItems;
    }

    /**
     * @param poorItems
     *            the poorItems to set
     */
    public void setPoorItems(List<String> poorItems) {
        this.poorItems = poorItems;
    }

    /**
     * @return the averageItems
     */
    public List<String> getAverageItems() {
        return averageItems;
    }

    /**
     * @param averageItems
     *            the averageItems to set
     */
    public void setAverageItems(List<String> averageItems) {
        this.averageItems = averageItems;
    }

    /**
     * @return the goodItems
     */
    public List<String> getGoodItems() {
        return goodItems;
    }

    /**
     * @param goodItems
     *            the goodItems to set
     */
    public void setGoodItems(List<String> goodItems) {
        this.goodItems = goodItems;
    }

    /**
     * @return the topItems
     */
    public List<String> getTopItems() {
        return topItems;
    }

    /**
     * @param topItems
     *            the topItems to set
     */
    public void setTopItems(List<String> topItems) {
        this.topItems = topItems;
    }

    /**
     * @return the broadcastMessageItem
     */
    public List<String> getBroadcastMessageItem() {
        return broadcastMessageItem;
    }

    /**
     * @param broadcastMessageItem
     *            the broadcastMessageItem to set
     */
    public void setBroadcastMessageItem(List<String> broadcastMessageItem) {
        this.broadcastMessageItem = broadcastMessageItem;
    }

    /**
     * @return the broadcastMessageEcon
     */
    public List<String> getBroadcastMessageEcon() {
        return broadcastMessageEcon;
    }

    /**
     * @param broadcastMessageEcon
     *            the broadcastMessageEcon to set
     */
    public void setBroadcastMessageEcon(List<String> broadcastMessageEcon) {
        this.broadcastMessageEcon = broadcastMessageEcon;
    }

    /**
     * @return the onlyEcon
     */
    public boolean isOnlyEcon() {
        return onlyEcon;
    }

    /**
     * @param onlyEcon
     *            the onlyEcon to set
     */
    public void setOnlyEcon(boolean onlyEcon) {
        this.onlyEcon = onlyEcon;
    }

    /**
     * @return the fixEcon
     */
    public boolean isFixEcon() {
        return fixEcon;
    }

    /**
     * @param fixEcon
     *            the fixEcon to set
     */
    public void setFixEcon(boolean fixEcon) {
        this.fixEcon = fixEcon;
    }

    /**
     * @return the fixEconAmmount
     */
    public int getFixEconAmmount() {
        return fixEconAmmount;
    }

    /**
     * @param fixEconAmmount
     *            the fixEconAmmount to set
     */
    public void setFixEconAmmount(int fixEconAmmount) {
        this.fixEconAmmount = fixEconAmmount;
    }

    /**
     * @return the fireworkColors
     */
    public List<String> getFireworkColors() {
        return fireworkColors;
    }

    /**
     * @param fireworkColors
     *            the fireworkColors to set
     */
    public void setFireworkColors(List<String> fireworkColors) {
        this.fireworkColors = fireworkColors;
    }

    /**
     * @return the fireworkFadeColors
     */
    public List<String> getFireworkFadeColors() {
        return fireworkFadeColors;
    }

    /**
     * @param fireworkFadeColors
     *            the fireworkFadeColors to set
     */
    public void setFireworkFadeColors(List<String> fireworkFadeColors) {
        this.fireworkFadeColors = fireworkFadeColors;
    }

    /**
     * @return the fireworkType
     */
    public String getFireworkType() {
        return fireworkType;
    }

    /**
     * @param fireworkType
     *            the fireworkType to set
     */
    public void setFireworkType(String fireworkType) {
        this.fireworkType = fireworkType;
    }

    /**
     * @return the fireworkPower
     */
    public int getFireworkPower() {
        return fireworkPower;
    }

    /**
     * @param fireworkPower
     *            the fireworkPower to set
     */
    public void setFireworkPower(int fireworkPower) {
        this.fireworkPower = fireworkPower;
    }

    /**
     * @return the firework
     */
    public boolean isFirework() {
        return firework;
    }

    /**
     * @param firework
     *            the firework to set
     */
    public void setFirework(boolean firework) {
        this.firework = firework;
    }

}
