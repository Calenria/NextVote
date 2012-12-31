package com.github.calenria.nextvote;

import java.util.Random;

import org.bukkit.inventory.ItemStack;

import com.github.calenria.nextvote.models.ConfigData;

public class NextVoteRadomItem {
    private enum quality {
        poor, average, good, top
    };

    private ConfigData config = null;
    private quality itemQuality = null;
    private short damage = 0;
    private ItemStack itemstack = null;
    private String name = null;
    private String lokalName = null;
    private int amount = 0;
    private Double ecoAmount = 0.0;
    private int material = 0;
    private boolean econ = false;

    public boolean isEcon() {
        return econ;
    }

    /**
     * @return the itemQuality
     */
    public quality getItemQuality() {
        return itemQuality;
    }

    /**
     * @return the damage
     */
    public short getDamage() {
        return damage;
    }

    /**
     * @return the damage
     */
    public int getMaterial() {
        return material;
    }

    /**
     * @return the itemstack
     */
    public ItemStack getItemstack() {
        return itemstack;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the lokalName
     */
    public String getLokalName() {
        return lokalName;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return the ecoAmount
     */
    public Double getEcoAmount() {
        return ecoAmount;
    }

    public NextVoteRadomItem(NextVote plugin) {
        config = plugin.config;
        NextVoteItem voteItem = new NextVoteItem();
        voteItem.SetItem(PickItem(), plugin);
        name = voteItem.getName();
        damage = voteItem.getDamage();

        if (name != "econ") {
            amount = PickAmount();
            itemstack = new ItemStack(voteItem.getMaterial(), amount, damage);
            lokalName = voteItem.getLokalName();
            material = voteItem.getMaterial();
        } else {
            ecoAmount = voteItem.getAmount();
            econ = true;
        }

    }

    private String PickItem() {
        Random rand = new Random();
        int num = rand.nextInt(config.getPoorChance() + config.getAverageChance() + config.getGoodChance() + config.getTopChance()) + 1;

        if (num <= config.getPoorChance()) {
            itemQuality = quality.poor;
            return config.getPoorItems().get(rand.nextInt(config.getPoorItems().size()));
        } else if (num <= (config.getPoorChance() + config.getAverageChance())) {
            itemQuality = quality.average;
            return config.getAverageItems().get(rand.nextInt(config.getAverageItems().size()));
        } else if (num <= (config.getPoorChance() + config.getAverageChance() + config.getGoodChance())) {
            itemQuality = quality.good;
            return config.getGoodItems().get(rand.nextInt(config.getGoodItems().size()));
        } else if (num <= (config.getPoorChance() + config.getAverageChance() + config.getGoodChance() + config.getTopChance())) {
            itemQuality = quality.top;
            return config.getTopItems().get(rand.nextInt(config.getTopItems().size()));
        }

        return null;
    }

    private int PickAmount() {
        Random rand = new Random();
        if (itemQuality == quality.poor) {
            return rand.nextInt(config.getPoorMaxAmount() - config.getPoorMinAmount() + 1) + config.getPoorMinAmount();
        } else if (itemQuality == quality.average) {
            return rand.nextInt(config.getAverageMaxAmount() - config.getAverageMinAmount() + 1) + config.getAverageMinAmount();
        } else if (itemQuality == quality.good) {
            return rand.nextInt(config.getGoodMaxAmount() - config.getGoodMinAmount() + 1) + config.getGoodMinAmount();
        } else if (itemQuality == quality.top) {
            return rand.nextInt(config.getTopMaxAmount() - config.getTopMinAmount() + 1) + config.getTopMinAmount();
        }
        return 0;
    }
}
