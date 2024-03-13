package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class Ghost {
	
	private String specimen;
	
	private String color;
	
	private Integer size;
	
	private boolean agressif;
	
	private HuntingStatus status; 
	
	private Integer damageBonus;
	
	private Integer health;
	
	
	
	public Ghost(String specimen, String color, Integer size, boolean agressif, HuntingStatus status,
			Integer damageBonus, Integer health) {
		super();
		this.specimen = specimen;
		this.color = color;
		this.size = size;
		this.agressif = agressif;
		this.status = status;
		this.damageBonus = damageBonus;
		this.health = health;
	}

	/**
	 * @return the health
	 */
	public Integer getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(Integer health) {
		this.health = health;
	}

	/**
	 * @return the damageBonus
	 */
	public Integer getDamageBonus() {
		return damageBonus;
	}

	/**
	 * @param damageBonus the damageBonus to set
	 */
	public void setDamageBonus(Integer damageBonus) {
		this.damageBonus = damageBonus;
	}

	/**
	 * @return the status
	 */
	public HuntingStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HuntingStatus status) {
		this.status = status;
	}

	/**
	 * @return the specimen
	 */
	public String getSpecimen() {
		return specimen;
	}

	/**
	 * @param specimen the specimen to set
	 */
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	public boolean isAgressif() {
		return agressif;
	}

	public void setAgressif(boolean aggressif) {
		this.agressif = aggressif;
	}



	
	
} 