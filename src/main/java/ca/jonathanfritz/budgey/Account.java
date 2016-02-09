package ca.jonathanfritz.budgey;

import org.joda.money.Money;

/**
 * Represents an account at a financial institution
 */
public class Account {
	private final String accountNumber;
	private final AccountType type;
	private final Money balance;

	public Account(String accountNumber, AccountType type, Money balance) {
		this.accountNumber = accountNumber;
		this.type = type;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public AccountType getType() {
		return type;
	}

	public Money getBalance() {
		return balance;
	}
}
