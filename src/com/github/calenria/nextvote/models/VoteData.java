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

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Datenbank Model für die eigentlichen Votes.
 * 
 * @author Calenria
 * 
 */
@Entity
@Table(name = "skymine_votes")
public class VoteData {

    /**
     * Die Vote Id.
     */
    @Id
    private Integer   id;

    /**
     * Spielername des Voters.
     */
    private String    minecraftUser;

    /**
     * Zeitpunkt des Votes.
     */
    private Timestamp time;
    /**
     * Die Ipadresse des Voters.
     */
    private String    ip;
    /**
     * Der VoteService (minecraft-server.eu z.B.).
     */
    private String    service;

    /**
     * @return Die Vote Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return Die Ipadresse des Voters
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return Spielername des Voters.
     */
    public String getMinecraftUser() {
        return minecraftUser;
    }

    /**
     * @return Der VoteService (minecraft-server.eu z.B.).
     */
    public String getService() {
        return service;
    }

    /**
     * @return Zeitpunkt des Votes.
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param vId
     *            Die Vote Id.
     */
    public void setId(final Integer vId) {
        this.id = vId;
    }

    /**
     * @param vIp
     *            Die Ipadresse des Voters
     */
    public void setIp(final String vIp) {
        this.ip = vIp;
    }

    /**
     * @param vPlayer
     *            Spielername des Voters.
     */
    public void setMinecraftUser(final String vPlayer) {
        this.minecraftUser = vPlayer;
    }

    /**
     * @param vService
     *            Der VoteService (minecraft-server.eu z.B.).
     */
    public void setService(final String vService) {
        this.service = vService;
    }

    /**
     * @param vTime
     *            Zeitpunkt des Votes.
     */
    public void setTime(final Timestamp vTime) {
        this.time = vTime;
    }

}
