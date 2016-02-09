package ca.jonathanfritz.budgey;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.money.Money;
import org.joda.time.DateTime;

/**
 * Represents a transaction at a financial institution
 */
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

	/**
	 * @return the type of bank account that was updated by this transaction. <br/>
	 *         TODO: this might be better suited as a property of an account, rather than of a transaction
	 */
	public AccountType getType() {
		return type;
	}

	/**
	 * @return the unique identifier of the account that was updated by this transaction
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return the date on which this transaction took place. If a time for the transaction is available, it should also
	 *         be expressed in this field.
	 */
	public DateTime getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @return the unique identifier of the cheque that this transaction represents.<br/>
	 *         TODO: cheques aren't used often enough to track this information. It might be best left out.
	 */
	public Long getChequeNumber() {
		return chequeNumber;
	}

	/**
	 * @return A simple description for a transaction. <br/>
	 *         TODO: consider appending this to description1 and removing this field entirely
	 */
	public String getDescription1() {
		return description1;
	}

	/**
	 * @return A simple description for a transaction. <br/>
	 *         TODO: consider appending this to description1 and removing this field entirely
	 */
	public String getDescription2() {
		return description2;
	}

	/**
	 * @return the amount spent or earned in CAD. This amount is signed, so both negative and positive values are
	 *         supported.<br/>
	 *         TODO: consider creating a value field and using the {@link Money} type to track the currency denomination
	 */
	public Money getCad() {
		return cad;
	}

	/**
	 * @return the amount spent or earned in USD. This amount is signed, so both negative and positive values are
	 *         supported.<br/>
	 *         TODO: consider creating a value field and using the {@link Money} type to track the currency denomination
	 */
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

		/**
		 * @param type the type of bank account that was updated by this transaction.
		 */
		public Builder setType(AccountType type) {
			this.type = type;
			return this;
		}

		/**
		 * @param accountNumber the unique identifier of the account that was updated by this transaction
		 */
		public Builder setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		/**
		 * @param transactionDate the date on which this transaction took place. If a time for the transaction is
		 *            available, it should also be expressed in this field.
		 */
		public Builder setTransactionDate(DateTime transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		/**
		 * @param chequeNumber the unique identifier of the cheque that this transaction represents.
		 */
		public Builder setChequeNumber(Long chequeNumber) {
			this.chequeNumber = chequeNumber;
			return this;
		}

		/**
		 * @param description1 A simple description for a transaction.
		 */
		public Builder setDescription1(String description1) {
			this.description1 = description1;
			return this;
		}

		/**
		 * @param description2 A simple description for a transaction.
		 */
		public Builder setDescription2(String description2) {
			this.description2 = description2;
			return this;
		}

		/**
		 * @param cad the amount spent or earned in CAD. This amount is signed, so both negative and positive values are
		 *            supported.
		 */
		public Builder setCad(Money cad) {
			this.cad = cad;
			return this;
		}

		/**
		 * @param usd the amount spent or earned in USD. This amount is signed, so both negative and positive values are
		 *            supported.
		 */
		public Builder setUsd(Money usd) {
			this.usd = usd;
			return this;
		}

		/**
		 * @return a new instance of {@link Transaction} with all of the attributes of this {@link Builder}
		 */
		public Transaction build() {
			return new Transaction(this);
		}
	}
}
