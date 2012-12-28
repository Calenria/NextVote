package com.github.calenria.nextvote;

public class NextVoteItem {
	private int material = 0;
	private String name = null;
	private String lokalName = null;
	private short damage = 0;
	private Double amount = null;

	/**
	 * @return the material
	 */
	public int getMaterial() {
		return material;
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
	 * @return the damage
	 */
	public short getDamage() {
		return damage;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	private void Econ(String id) {
		id = id.replace("econ ", "");
		name = "econ";
		amount = Double.parseDouble(id);
	}

	private void Item(String id, NextVote plugin) {
		if (!id.contains(":")) {
			name = id;
			material = Integer.parseInt(id);
			damage = 0;
			lokalName = plugin.items.getString(id);
		} else {
			name = id;
			String[] m = id.split(":");
			material = Integer.parseInt(m[0]);
			damage = Short.parseShort(m[1]);
			lokalName = plugin.items.getString(material + "_" + damage);
		}
	}

	public void SetItem(String id, NextVote plugin) {
		if (id.contains("econ")) {
			Econ(id);
			return;
		} else {
			Item(id, plugin);
			return;
		}
	}
}
