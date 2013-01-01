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

@Entity
@Table(name = "skymine_votes")
public class VoteData {

    @Id
    private Integer   id;

    private String    minecraftUser;

    private Timestamp time;
    private String    ip;
    private String    service;

    public Integer getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getMinecraftUser() {
        return minecraftUser;
    }

    public String getService() {
        return service;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMinecraftUser(String player) {
        this.minecraftUser = player;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

}
