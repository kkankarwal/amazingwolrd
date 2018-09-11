package com.dev.strategy2;

class CurrentAccountInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance * (0.02 / 12);
	}
}

class SavingsAccountInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance * (0.04 / 12);
	}
}

class MoneyMarketInterestCalculation implements InterestCalculationStrategy {
	@Override
	public double calculateInterest(double accountBalance) {
		return accountBalance * (0.06 / 12);
	}
}

class HighRollerMoneyMarketInterestCalculation implements InterestCalculationStrategy {
	@Override
    public double calculateInterest(double accountBalance) {
        return accountBalance < 100000.00 ? 0 : accountBalance * (0.075/12);
    }
}