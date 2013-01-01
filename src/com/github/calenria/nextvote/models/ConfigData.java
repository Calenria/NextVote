package com.github.calenria.nextvote.models;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.calenria.nextvote.NextVote;

public class ConfigData {

    @SuppressWarnings("unused")
    private static Logger log = Logger.getLogger("Minecraft");

    private String        lang;
    private List<String>  voteInfo;
    private List<String>  thxVote;
    private List<String>  noVote;
    private List<String>  dayVote;
    private List<String>  daysVote;
    private List<String>  infoVote;
    private boolean       showBroadcast;
    private boolean       bonusItem;
    private int           bonusChance;
    private int           poorChance;
    private int           averageChance;
    private int           goodChance;
    private int           topChance;
    private int           poorMinAmount;
    private int           poorMaxAmount;
    private int           averageMinAmount;
    private int           averageMaxAmount;
    private int           goodMinAmount;
    private int           goodMaxAmount;
    private int           topMinAmount;
    private int           topMaxAmount;
    private List<String>  poorItems;
    private List<String>  averageItems;
    private List<String>  goodItems;
    private List<String>  topItems;
    private List<String>  broadcastMessageItem;
    private List<String>  broadcastMessageEcon;
    private List<String>  broadcastMessageItemEcon;
    private List<String>  fireworkColors;
    private List<String>  fireworkFadeColors;
    private String        fireworkType;
    private int           fireworkPower;
    private boolean       firework;

    private boolean       onlyEcon;
    private boolean       fixEcon;
    private int           fixEconAmmount;

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

    /**
     * @return the averageChance
     */
    public int getAverageChance() {
        return averageChance;
    }

    /**
     * @return the averageItems
     */
    public List<String> getAverageItems() {
        return averageItems;
    }

    /**
     * @return the averageMaxAmount
     */
    public int getAverageMaxAmount() {
        return averageMaxAmount;
    }

    /**
     * @return the averageMinAmount
     */
    public int getAverageMinAmount() {
        return averageMinAmount;
    }

    /**
     * @return the bonusChance
     */
    public int getBonusChance() {
        return bonusChance;
    }

    /**
     * @return the broadcastMessageEcon
     */
    public List<String> getBroadcastMessageEcon() {
        return broadcastMessageEcon;
    }

    /**
     * @return the broadcastMessageItem
     */
    public List<String> getBroadcastMessageItem() {
        return broadcastMessageItem;
    }

    public List<String> getBroadcastMessageItemEcon() {
        return broadcastMessageItemEcon;
    }

    /**
     * @return the daysVote
     */
    public List<String> getDaysVote() {
        return daysVote;
    }

    /**
     * @return the dayVote
     */
    public List<String> getDayVote() {
        return dayVote;
    }

    /**
     * @return the fireworkColors
     */
    public List<String> getFireworkColors() {
        return fireworkColors;
    }

    /**
     * @return the fireworkFadeColors
     */
    public List<String> getFireworkFadeColors() {
        return fireworkFadeColors;
    }

    /**
     * @return the fireworkPower
     */
    public int getFireworkPower() {
        return fireworkPower;
    }

    /**
     * @return the fireworkType
     */
    public String getFireworkType() {
        return fireworkType;
    }

    /**
     * @return the fixEconAmmount
     */
    public int getFixEconAmmount() {
        return fixEconAmmount;
    }

    /**
     * @return the goodChance
     */
    public int getGoodChance() {
        return goodChance;
    }

    /**
     * @return the goodItems
     */
    public List<String> getGoodItems() {
        return goodItems;
    }

    /**
     * @return the goodMaxAmount
     */
    public int getGoodMaxAmount() {
        return goodMaxAmount;
    }

    /**
     * @return the goodMinAmount
     */
    public int getGoodMinAmount() {
        return goodMinAmount;
    }

    /**
     * @return the infoVote
     */
    public List<String> getInfoVote() {
        return infoVote;
    }

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @return the noVote
     */
    public List<String> getNoVote() {
        return noVote;
    }

    /**
     * @return the poorChance
     */
    public int getPoorChance() {
        return poorChance;
    }

    /**
     * @return the poorItems
     */
    public List<String> getPoorItems() {
        return poorItems;
    }

    /**
     * @return the poorMaxAmount
     */
    public int getPoorMaxAmount() {
        return poorMaxAmount;
    }

    /**
     * @return the poorMinAmount
     */
    public int getPoorMinAmount() {
        return poorMinAmount;
    }

    /**
     * @return the thxVote
     */
    public List<String> getThxVote() {
        return thxVote;
    }

    /**
     * @return the topChance
     */
    public int getTopChance() {
        return topChance;
    }

    /**
     * @return the topItems
     */
    public List<String> getTopItems() {
        return topItems;
    }

    /**
     * @return the topMaxAmount
     */
    public int getTopMaxAmount() {
        return topMaxAmount;
    }

    /**
     * @return the topMinAmount
     */
    public int getTopMinAmount() {
        return topMinAmount;
    }

    /**
     * @return the voteInfo
     */
    public List<String> getVoteInfo() {
        return voteInfo;
    }

    /**
     * @return the bonusItem
     */
    public boolean isBonusItem() {
        return bonusItem;
    }

    /**
     * @return the firework
     */
    public boolean isFirework() {
        return firework;
    }

    /**
     * @return the fixEcon
     */
    public boolean isFixEcon() {
        return fixEcon;
    }

    /**
     * @return the onlyEcon
     */
    public boolean isOnlyEcon() {
        return onlyEcon;
    }

    /**
     * @return the showBroadcast
     */
    public boolean isShowBroadcast() {
        return showBroadcast;
    }

    /**
     * @param averageChance
     *            the averageChance to set
     */
    public void setAverageChance(int averageChance) {
        this.averageChance = averageChance;
    }

    /**
     * @param averageItems
     *            the averageItems to set
     */
    public void setAverageItems(List<String> averageItems) {
        this.averageItems = averageItems;
    }

    /**
     * @param averageMaxAmount
     *            the averageMaxAmount to set
     */
    public void setAverageMaxAmount(int averageMaxAmount) {
        this.averageMaxAmount = averageMaxAmount;
    }

    /**
     * @param averageMinAmount
     *            the averageMinAmount to set
     */
    public void setAverageMinAmount(int averageMinAmount) {
        this.averageMinAmount = averageMinAmount;
    }

    /**
     * @param bonusChance
     *            the bonusChance to set
     */
    public void setBonusChance(int bonusChance) {
        this.bonusChance = bonusChance;
    }

    /**
     * @param bonusItem
     *            the bonusItem to set
     */
    public void setBonusItem(boolean bonusItem) {
        this.bonusItem = bonusItem;
    }

    /**
     * @param broadcastMessageEcon
     *            the broadcastMessageEcon to set
     */
    public void setBroadcastMessageEcon(List<String> broadcastMessageEcon) {
        this.broadcastMessageEcon = broadcastMessageEcon;
    }

    /**
     * @param broadcastMessageItem
     *            the broadcastMessageItem to set
     */
    public void setBroadcastMessageItem(List<String> broadcastMessageItem) {
        this.broadcastMessageItem = broadcastMessageItem;
    }

    public void setBroadcastMessageItemEcon(List<String> broadcastMessageItemEcon) {
        this.broadcastMessageItemEcon = broadcastMessageItemEcon;

    }

    /**
     * @param daysVote
     *            the daysVote to set
     */
    public void setDaysVote(List<String> daysVote) {
        this.daysVote = daysVote;
    }

    /**
     * @param dayVote
     *            the dayVote to set
     */
    public void setDayVote(List<String> dayVote) {
        this.dayVote = dayVote;
    }

    /**
     * @param firework
     *            the firework to set
     */
    public void setFirework(boolean firework) {
        this.firework = firework;
    }

    /**
     * @param fireworkColors
     *            the fireworkColors to set
     */
    public void setFireworkColors(List<String> fireworkColors) {
        this.fireworkColors = fireworkColors;
    }

    /**
     * @param fireworkFadeColors
     *            the fireworkFadeColors to set
     */
    public void setFireworkFadeColors(List<String> fireworkFadeColors) {
        this.fireworkFadeColors = fireworkFadeColors;
    }

    /**
     * @param fireworkPower
     *            the fireworkPower to set
     */
    public void setFireworkPower(int fireworkPower) {
        this.fireworkPower = fireworkPower;
    }

    /**
     * @param fireworkType
     *            the fireworkType to set
     */
    public void setFireworkType(String fireworkType) {
        this.fireworkType = fireworkType;
    }

    /**
     * @param fixEcon
     *            the fixEcon to set
     */
    public void setFixEcon(boolean fixEcon) {
        this.fixEcon = fixEcon;
    }

    /**
     * @param fixEconAmmount
     *            the fixEconAmmount to set
     */
    public void setFixEconAmmount(int fixEconAmmount) {
        this.fixEconAmmount = fixEconAmmount;
    }

    /**
     * @param goodChance
     *            the goodChance to set
     */
    public void setGoodChance(int goodChance) {
        this.goodChance = goodChance;
    }

    /**
     * @param goodItems
     *            the goodItems to set
     */
    public void setGoodItems(List<String> goodItems) {
        this.goodItems = goodItems;
    }

    /**
     * @param goodMaxAmount
     *            the goodMaxAmount to set
     */
    public void setGoodMaxAmount(int goodMaxAmount) {
        this.goodMaxAmount = goodMaxAmount;
    }

    /**
     * @param goodMinAmount
     *            the goodMinAmount to set
     */
    public void setGoodMinAmount(int goodMinAmount) {
        this.goodMinAmount = goodMinAmount;
    }

    /**
     * @param infoVote
     *            the infoVote to set
     */
    public void setInfoVote(List<String> infoVote) {
        this.infoVote = infoVote;
    }

    /**
     * @param lang
     *            the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @param noVote
     *            the noVote to set
     */
    public void setNoVote(List<String> noVote) {
        this.noVote = noVote;
    }

    /**
     * @param onlyEcon
     *            the onlyEcon to set
     */
    public void setOnlyEcon(boolean onlyEcon) {
        this.onlyEcon = onlyEcon;
    }

    /**
     * @param poorChance
     *            the poorChance to set
     */
    public void setPoorChance(int poorChance) {
        this.poorChance = poorChance;
    }

    /**
     * @param poorItems
     *            the poorItems to set
     */
    public void setPoorItems(List<String> poorItems) {
        this.poorItems = poorItems;
    }

    /**
     * @param poorMaxAmount
     *            the poorMaxAmount to set
     */
    public void setPoorMaxAmount(int poorMaxAmount) {
        this.poorMaxAmount = poorMaxAmount;
    }

    /**
     * @param poorMinAmount
     *            the poorMinAmount to set
     */
    public void setPoorMinAmount(int poorMinAmount) {
        this.poorMinAmount = poorMinAmount;
    }

    /**
     * @param showBroadcast
     *            the showBroadcast to set
     */
    public void setShowBroadcast(boolean showBroadcast) {
        this.showBroadcast = showBroadcast;
    }

    /**
     * @param thxVote
     *            the thxVote to set
     */
    public void setThxVote(List<String> thxVote) {
        this.thxVote = thxVote;
    }

    /**
     * @param topChance
     *            the topChance to set
     */
    public void setTopChance(int topChance) {
        this.topChance = topChance;
    }

    /**
     * @param topItems
     *            the topItems to set
     */
    public void setTopItems(List<String> topItems) {
        this.topItems = topItems;
    }

    /**
     * @param topMaxAmount
     *            the topMaxAmount to set
     */
    public void setTopMaxAmount(int topMaxAmount) {
        this.topMaxAmount = topMaxAmount;
    }

    /**
     * @param topMinAmount
     *            the topMinAmount to set
     */
    public void setTopMinAmount(int topMinAmount) {
        this.topMinAmount = topMinAmount;
    }

    /**
     * @param voteInfo
     *            the voteInfo to set
     */
    public void setVoteInfo(List<String> voteInfo) {
        this.voteInfo = voteInfo;
    }

}
