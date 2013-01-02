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
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Datenbank Model für die Voteblohnungen.
 * 
 * @author Calenria
 * 
 */
@Entity
@Table(name = "skymine_vote_history")
public class VoteHistory {
    /**
     * Vote id.
     */
    @Id
    private Integer   id;

    /**
     * Der Spieler der gevotet hat.
     */
    private String    minecraftUser;

    /**
     * Zeitpunkt der Votes.
     */
    private Timestamp time;

    /**
     * Augezahlte Item Anzahl.
     */
    private int       ammount     = 0;

    /**
     * Schaden/Farbe/Typ des Items.
     */
    private short     damage      = 0;
    /**
     * Material des Items.
     */
    private int       material    = 0;

    /**
     * Name des Items.
     */
    private String    name;

    /**
     * Name des Items in der gewählten Sprache.
     */
    private String    localName;

    /**
     * Votebelohnung enthält Econ.
     */
    private boolean   econ        = false;
    /**
     * Votebelohnung enthält ein Item.
     */
    private boolean   item        = false;
    /**
     * Anzahl der Econ Belohnung.
     */
    private double    econAmmount = 0;

    /**
     * Vote ausgezahlt.
     */
    private boolean   paid        = false;
    /**
     * Econ Belohnung ausgezahlt.
     */
    private boolean   paidEcon    = false;

    /**
     * Letztes Update des Votes.
     */
    @Version
    private Timestamp lastUpdate;

    /**
     * Vote Totals.
     */
    @Transient
    private int       total;

    /**
     * @return the ammount
     */
    public int getAmmount() {
        return ammount;
    }

    /**
     * @return the damage
     */
    public short getDamage() {
        return damage;
    }

    /**
     * @return the econ
     */
    public boolean getEcon() {
        return econ;
    }

    /**
     * @return the econAmmount
     */
    public double getEconAmmount() {
        return econAmmount;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the item
     */
    public boolean getItem() {
        return item;
    }

    /**
     * @return the lastUpdate
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @return the localName
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * @return the material
     */
    public int getMaterial() {
        return material;
    }

    /**
     * @return the minecraftUser
     */
    public String getMinecraftUser() {
        return minecraftUser;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the paid
     */
    public boolean getPaid() {
        return paid;
    }

    /**
     * @return the econ
     */
    public boolean getPaidEcon() {
        return paidEcon;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * Vote Totals.
     * 
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return the econ
     */
    public boolean hasEcon() {
        return econ;
    }

    /**
     * @return the item
     */
    public boolean hasItem() {
        return item;
    }

    /**
     * @return the econ
     */
    public boolean hasPaidEcon() {
        return paidEcon;
    }

    /**
     * @return the econ
     */
    public boolean isEcon() {
        return econ;
    }

    /**
     * @return the item
     */
    public boolean isItem() {
        return item;
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @return the econ
     */
    public boolean isPaidEcon() {
        return paidEcon;
    }

    /**
     * @param vAmmount
     *            the ammount to set
     */
    public void setAmmount(final int vAmmount) {
        this.ammount = vAmmount;
    }

    /**
     * @param vDdamage
     *            the damage to set
     */
    public void setDamage(final short vDdamage) {
        this.damage = vDdamage;
    }

    /**
     * @param vEcon
     *            the econ to set
     */
    public void setEcon(final boolean vEcon) {
        this.econ = vEcon;
    }

    /**
     * @param vEconAmmount
     *            the econAmmount to set
     */
    public void setEconAmmount(final double vEconAmmount) {
        this.econAmmount = vEconAmmount;
    }

    /**
     * @param vId
     *            set the vote Id
     */
    public void setId(final Integer vId) {
        this.id = vId;
    }

    /**
     * @param vItem
     *            the item to set
     */
    public void setItem(final boolean vItem) {
        this.item = vItem;
    }

    /**
     * @param vLastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate(final Timestamp vLastUpdate) {
        this.lastUpdate = vLastUpdate;
    }

    /**
     * @param vLocalName
     *            the localName to set
     */
    public void setLocalName(final String vLocalName) {
        this.localName = vLocalName;
    }

    /**
     * @param vMaterial
     *            the material to set
     */
    public void setMaterial(final int vMaterial) {
        this.material = vMaterial;
    }

    /**
     * @param vMinecraftUser
     *            the minecraftUser to set
     */
    public void setMinecraftUser(final String vMinecraftUser) {
        this.minecraftUser = vMinecraftUser;
    }

    /**
     * @param vName
     *            the name to set
     */
    public void setName(final String vName) {
        this.name = vName;
    }

    /**
     * @param vPaid
     *            the paid to set
     */
    public void setPaid(final boolean vPaid) {
        this.paid = vPaid;
    }

    /**
     * @param vPaidEcon
     *            the econ to set
     */
    public void setPaidEcon(final boolean vPaidEcon) {
        this.paidEcon = vPaidEcon;
    }

    /**
     * @param vTime
     *            the time to set
     */
    public void setTime(final Timestamp vTime) {
        this.time = vTime;
    }

    /**
     * @param vTotal
     *            the total to set
     */
    public void setTotal(final int vTotal) {
        this.total = vTotal;
    }
}
