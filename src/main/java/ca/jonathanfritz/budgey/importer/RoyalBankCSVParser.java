package ca.jonathanfritz.budgey.importer;

import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import ca.jonathanfritz.budgey.AccountType;
import ca.jonathanfritz.budgey.Transaction;

public class RoyalBankCSVParser implements CSVParser {

	// date format is 10/13/2015
	private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	.appendMonthOfYear(2)
	.appendLiteral('/')
	.appendDayOfMonth(2)
	.appendLiteral('/')
	.appendYear(4, 4)
	.toFormatter();

	@Override
	public Transaction parse(String[] fields) {
		return Transaction
				.newBuilder()
				.setType(AccountType.fromString(fields[0]))
				.setAccountNumber(fields[1])
				.setTransactionDate(formatter.parseDateTime(fields[2]))
				.setChequeNumber(StringUtils.isNotBlank(fields[3]) ? Long.parseLong(fields[3]) : null)
				.setDescription1(fields[4])
				.setDescription2(fields[5])
				.setCad((fields.length > 6) ? Money.parse("CAD " + fields[6]) : Money.zero(CurrencyUnit.CAD))
				.setUsd((fields.length > 7) ? Money.parse("USD " + fields[7]) : Money.zero(CurrencyUnit.USD))
				.build();
	}
}
