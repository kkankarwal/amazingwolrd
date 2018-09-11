package com.sap.dp.factory2;

public enum DealHandlerType {
	
	SINGLE_DEAL("Single Deal"), MULTI_DEAL("Multi Deal");

	private DealHandlerType(String description) {
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String description;
}