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

import java.util.Random;

import org.bukkit.inventory.ItemStack;

import com.github.calenria.nextvote.models.ConfigData;

/**
 * Berechnet die Wahrscheinlichkeiten der einzelnen Itemlisten und wählt ein Item aus.
 * 
 * @author Calenria
 */
public class NextVoteRadomItem {
    /**
     * Qualitäts Typen.
     * 
     * @author Calenria
     */
    private enum quality {
        /**
         * Qualitäts Typen.
         */
        poor, average, good, top
    };

    /**
     * Konfigurations Objekt.
     */
    private ConfigData config      = null;
    /**
     * Item Qualität.
     */
    private quality    itemQuality = null;
    /**
     * Item Schaden.
     */
    private short      damage      = 0;
    /**
     * ItemStack.
     * 
     * @see org.bukkit.inventory.ItemStack
     */
    private ItemStack  itemstack   = null;
    /**
     * Itemname.
     */
    private String     name        = null;
    /**
     * Itemname in der gewählten Sprache.
     */
    private String     lokalName   = null;
    /**
     * Anzahl.
     */
    private int        amount      = 0;
    /**
     * Anzahl an Eco.
     */
    private Double     ecoAmount   = 0.0;
    /**
     * Material.
     */
    private int        material    = 0;
    /**
     * Item oder Eco?
     */
    private boolean    econ        = false;

    /**
     * @param plugin
     *            Bukkit Plugin NextVote
     */
    public NextVoteRadomItem(final NextVote plugin) {
        config = plugin.getPluginConfig();
        NextVoteItem voteItem = new NextVoteItem(pickItem(), plugin);
        name = voteItem.getName();
        damage = voteItem.getDamage();

        if (name != "econ") {
            amount = pickAmount();
            itemstack = new ItemStack(voteItem.getMaterial(), amount, damage);
            lokalName = voteItem.getLokalName();
            material = voteItem.getMaterial();
        } else {
            ecoAmount = voteItem.getAmount();
            econ = true;
        }

    }

    /**
     * @return the amount
     */
    public final int getAmount() {
        return amount;
    }

    /**
     * @return the damage
     */
    public final short getDamage() {
        return damage;
    }

    /**
     * @return the ecoAmount
     */
    public final Double getEcoAmount() {
        return ecoAmount;
    }

    /**
     * @return the itemQuality
     */
    public final quality getItemQuality() {
        return itemQuality;
    }

    /**
     * @return the itemstack
     */
    public final ItemStack getItemstack() {
        return itemstack;
    }

    /**
     * @return the lokalName
     */
    public final String getLokalName() {
        return lokalName;
    }

    /**
     * @return the damage
     */
    public final int getMaterial() {
        return material;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Prüfen auf EconItem.
     * 
     * @return <tt>true</tt> wen es ein Econ Item ist ansonsten <tt>false</tt>
     */
    public final boolean isEcon() {
        return econ;
    }

    /**
     * Wählt die anzahl anhand der Wahrscheinlichkeit, Qualität und der Konfiguration (min/max) aus.
     * 
     * @return die anzahl anhand der Konfiguration
     */
    private int pickAmount() {
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

    /**
     * Wählt ein zufälliges Item aus den Listen aus.
     * 
     * @return einen Itemstring aus der Itemliste oder null wen nichts gefunden wird
     * 
     *         TODO vielleicht eine eigene Exception werfen anstatt in eine NPE zu laufen.
     */
    private String pickItem() {
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
}
