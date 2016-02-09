package ca.jonathanfritz.budgey.importer;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import ca.jonathanfritz.budgey.Transaction;

import com.google.common.base.Joiner;

public class ScotiabankCSVParser implements CSVParser {

	// date format is 9/1/15
	private final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	        .appendMonthOfYear(1)
	        .appendLiteral('/')
	        .appendDayOfMonth(1)
	        .appendLiteral('/')
	        .appendTwoDigitYear(2000)
	        .toFormatter();

	private final NumberFormat cadFormat = NumberFormat.getNumberInstance(Locale.CANADA);

	@Override
	public Transaction parse(final String[] fields) {

		Money amount = Money.zero(CurrencyUnit.CAD);
		try {
			if (fields.length > 2 && StringUtils.isNotBlank(fields[2])) {
				final BigDecimal cad = new BigDecimal(cadFormat.parse(fields[2]).toString());
				amount = Money.of(CurrencyUnit.CAD, cad);
			}
		} catch (final ParseException e) {
			throw new RuntimeException("Failed to parse transaction [" + Joiner.on(",").join(fields) + "]", e);
		}

		return Transaction.newBuilder()
		        .setTransactionDate(formatter.parseDateTime(fields[0]))
		        .setDescription(fields[1].replaceAll("^\"|\"$", ""))
		        .setAmount(amount)
		        .build();
	}
}
