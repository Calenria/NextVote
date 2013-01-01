package com.github.calenria.nextvote.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "skymine_vote_history")
public class VoteHistory {
    @Id
    private Integer   id;

    private String    minecraftUser;

    private Timestamp time;

    private int       ammount     = 0;

    private short     damage      = 0;
    private int       material    = 0;

    private String    name;

    private String    localName;

    private boolean   econ        = false;
    private boolean   item        = false;
    private double    econAmmount = 0;

    private boolean   paid        = false;
    private boolean   paidEcon    = false;

    @Version
    private Timestamp lastUpdate;

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
     * @param ammount
     *            the ammount to set
     */
    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    /**
     * @param damage
     *            the damage to set
     */
    public void setDamage(short damage) {
        this.damage = damage;
    }

    /**
     * @param econ
     *            the econ to set
     */
    public void setEcon(boolean econ) {
        this.econ = econ;
    }

    /**
     * @param d
     *            the econAmmount to set
     */
    public void setEconAmmount(double d) {
        this.econAmmount = d;
    }

    /**
     * set the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(boolean item) {
        this.item = item;
    }

    /**
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @param localName
     *            the localName to set
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * @param material
     *            the material to set
     */
    public void setMaterial(int material) {
        this.material = material;
    }

    /**
     * @param minecraftUser
     *            the minecraftUser to set
     */
    public void setMinecraftUser(String minecraftUser) {
        this.minecraftUser = minecraftUser;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param paid
     *            the paid to set
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * @param econ
     *            the econ to set
     */
    public void setPaidEcon(boolean paidEcon) {
        this.paidEcon = paidEcon;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
