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

import javax.persistence.Entity;
import javax.persistence.Table;

import com.github.calenria.nextvote.NextVote;

/**
 * Hilfklasse um Topvoter usw auszugeben.
 * 
 * @author Calenria
 * 
 */
@Entity
@Table(name = "skymine_vote_aggregate")
public class VoteAggregate {
    /**
     *
     */
    private String player;

    /**
     *
     */
    private int    total;

    /**
     *
     */
    private int    totalItems;
    /**
     *
     */
    private double totalEcon;

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return the totalEcon
     */
    public double getTotalEcon() {
        return totalEcon;
    }

    /**
     * @return the totalItems
     */
    public int getTotalItems() {
        return totalItems;
    }

    /**
     * @return the voteHistory
     */
    public String getPlayer() {
        return player;
    }

    /**
     * @param vTotal
     *            the total to set
     */
    public void setTotal(final int vTotal) {
        this.total = vTotal;
    }

    /**
     * @param vTotalEcon
     *            the totalEcon to set
     */
    public void setTotalEcon(final double vTotalEcon) {
        this.totalEcon = vTotalEcon;
    }

    /**
     * @param vTotalItems
     *            the totalItems to set
     */
    public void setTotalItems(final int vTotalItems) {
        this.totalItems = vTotalItems;
    }

    /**
     * @param vPlayer
     *            the player
     */
    public void setPlayer(final String vPlayer) {
        this.player = vPlayer;
    }

    /**
     * @param plugin
     *            the plugin
     */
    public static void initView(final NextVote plugin) {
        plugin.getDatabase().createSqlUpdate("CREATE VIEW `skymine_vote_aggregate` AS select svh.minecraft_user as player, sum(svh.econ_ammount) as total_econ, sum(svh.ammount) as total_items, count(*) as total from skymine_vote_history as svh group by svh.minecraft_user order by total desc").execute();
    }
}
