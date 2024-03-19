package com.ghostbusters;

public class Ghost {
	
	private String specimen;
	
	private String color;
	
	private Integer maxSize;
	
	private boolean ephemeral;
	
	private HuntingStatus status; 
		
	private Integer health;
	
	private String history; 

	public Ghost(String specimen, String color, Integer size, boolean agressif, HuntingStatus status, Integer health, String history) {
		super();
		this.specimen = specimen;
		this.color = color;
		this.maxSize = size;
		this.ephemeral = agressif;
		this.status = status;
		this.health = health;
		this.history = history;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public HuntingStatus getStatus() {
		return status;
	}

	public void setStatus(HuntingStatus status) {
		this.status = status;
	}

	public String getSpecimen() {
		return specimen;
	}

	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer size) {
		this.maxSize = size;
	}

	public boolean isEphemeral() {
		return ephemeral;
	}

	public void setEphemeral(boolean ephemeral) {
		ephemeral = ephemeral;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
	
} 