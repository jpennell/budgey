package ca.jonathanfritz.budgey.importer;

import ca.jonathanfritz.budgey.Transaction;
import org.joda.money.Money;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class ScotiabankCSVParser implements CSVParser {

	// date format is 9/1/15
	private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendMonthOfYear(1)
			.appendLiteral('/')
			.appendDayOfMonth(1)
			.appendLiteral('/')
			.appendTwoDigitYear(2000)
			.toFormatter();

	@Override
	public Transaction parse(final String[] fields) {

		return Transaction.newBuilder()
				.setTransactionDate(this.formatter.parseDateTime(fields[0]))
				.setDescription1(fields[1].replaceAll("^\"|\"$", ""))
				.setCad(Money.parse(String.format("CAD %s", fields[2])))
				.build();

	}

}
