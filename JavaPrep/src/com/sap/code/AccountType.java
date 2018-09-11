package com.sap.code;

public enum AccountType {
	
	SAVINGS(1, "Savings"),
	FD(2, "Fixed Deposite"), 
	RD(3, "Reccurring Deposite"), 
	MLP(4, "Multiplier Deposite");

	AccountType(int id, String type) {
		this.id = id;
		this.accountType = type;
	}

	int id;
	String accountType;

}
