package com.sap.code;

public abstract class Account {

	private long accountId;
	private String accountHolder;
	private String holdingStatus;
	private AccountType accountType;
	private double balance;

	public Account() {
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getHoldingStatus() {
		return holdingStatus;
	}

	public void setHoldingStatus(String holdingStatus) {
		this.holdingStatus = holdingStatus;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
