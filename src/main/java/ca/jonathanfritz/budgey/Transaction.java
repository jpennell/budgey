package ca.jonathanfritz.budgey;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.money.Money;
import org.joda.time.DateTime;

public class Transaction {
	private final AccountType type;
	private final String accountNumber;
	private final DateTime transactionDate;
	private final Long chequeNumber;
	private final String description1;
	private final String description2;
	private final Money cad;
	private final Money usd;

	private Transaction(Builder builder) {
		type = builder.type;
		accountNumber = builder.accountNumber;
		transactionDate = builder.transactionDate;
		chequeNumber = builder.chequeNumber;
		description1 = builder.description1;
		description2 = builder.description2;
		cad = builder.cad;
		usd = builder.usd;
	}

	public AccountType getType() {
		return type;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public DateTime getTransactionDate() {
		return transactionDate;
	}

	public Long getChequeNumber() {
		return chequeNumber;
	}

	public String getDescription1() {
		return description1;
	}

	public String getDescription2() {
		return description2;
	}

	public Money getCad() {
		return cad;
	}

	public Money getUsd() {
		return usd;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static class Builder {
		private AccountType type;
		private String accountNumber;
		private DateTime transactionDate;
		private Long chequeNumber;
		private String description1;
		private String description2;
		private Money cad;
		private Money usd;

		public Builder setType(AccountType type) {
			this.type = type;
			return this;
		}

		public Builder setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder setTransactionDate(DateTime transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		public Builder setChequeNumber(Long chequeNumber) {
			this.chequeNumber = chequeNumber;
			return this;
		}

		public Builder setDescription1(String description1) {
			this.description1 = description1;
			return this;
		}

		public Builder setDescription2(String description2) {
			this.description2 = description2;
			return this;
		}

		public Builder setCad(Money cad) {
			this.cad = cad;
			return this;
		}

		public Builder setUsd(Money usd) {
			this.usd = usd;
			return this;
		}

		public Transaction build() {
			return new Transaction(this);
		}
	}
}
