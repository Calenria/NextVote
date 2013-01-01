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

/**
 * Klasse für die eigentliche Item Representation welches bei Vote ausgegeben wird.
 * 
 * @author Calenria
 * 
 *         TODO Unterstützung für Verzauberungen
 */
public class NextVoteItem {
    /**
     * Materialnummer.
     */
    private int      material  = 0;
    /**
     * Itemname.
     */
    private String   name      = null;
    /**
     * Name in der gewählten Sprache.
     */
    private String   lokalName = null;
    /**
     * Itemschaden.
     */
    private short    damage    = 0;
    /**
     * Anzahl.
     */
    private Double   amount    = null;
    /**
     * Plugin.
     */
    private NextVote plugin;

    /**
     * @param id
     *            Item id und damage als String
     * @param nvPlugin
     *            Plugin
     */
    public NextVoteItem(final String id, final NextVote nvPlugin) {
        this.plugin = nvPlugin;
        if (id.contains("econ")) {
            setEconItem(id);
            return;
        } else {
            setItem(id);
            return;
        }
    }

    /**
     * @return the amount
     */
    public final Double getAmount() {
        return amount;
    }

    /**
     * @return the damage
     */
    public final short getDamage() {
        return damage;
    }

    /**
     * @return the lokalName
     */
    public final String getLokalName() {
        return lokalName;
    }

    /**
     * @return the material
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
     * Erstellt ein "Economy Item", enthält nur die anzahl und den namen econ.
     * 
     * @param id
     *            String der die anzahl enthält und mit "econ " beginnt
     */
    private void setEconItem(final String id) {
        name = "econ";
        amount = Double.parseDouble(id.replace("econ ", ""));
    }

    /**
     * Erstellt ein Normales Item.
     * 
     * @param id
     *            Item id und damage als String
     */
    private void setItem(final String id) {
        if (!id.contains(":")) {
            name = id;
            material = Integer.parseInt(id);
            damage = 0;
            lokalName = this.plugin.getItems().getString(id);
        } else {
            name = id;
            String[] m = id.split(":");
            material = Integer.parseInt(m[0]);
            damage = Short.parseShort(m[1]);
            lokalName = this.plugin.getItems().getString(material + "_" + damage);
        }
    }
}
