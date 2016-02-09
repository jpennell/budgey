package ca.jonathanfritz.budgey.importer;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.util.ClasspathLoader;

public class RoyalBankCSVParserTest {

	private static final String csv = "importer/csv/rbc-chequing-with-quotes.csv";

	@Test
	public void validTransactionParsingTest() {
		final String line = ClasspathLoader.load(csv);
		final String[] fields = FieldSanitizer.sanitizeFields(line.split(","));
		final RoyalBankCSVParser parser = new RoyalBankCSVParser();
		final Transaction transaction = parser.parse(fields);

		Assert.assertThat(transaction.getAccountNumber(), IsEqual.equalTo("079022-11555"));
		Assert.assertThat(transaction.getDescription1(), IsEqual.equalTo("Email Trfs"));
		Assert.assertThat(transaction.getDescription2(), IsEqual.equalTo("INTERAC E-TRF- 7336"));
		Assert.assertThat(transaction.getCad().getAmount().doubleValue(), IsEqual.equalTo(53.00D));
		Assert.assertThat(transaction.getChequeNumber(), IsNull.nullValue());
		Assert.assertThat(transaction.getTransactionDate(), IsEqual.equalTo((new DateTime())
				.withTimeAtStartOfDay()
				.withYear(2015)
				.withMonthOfYear(10)
				.withDayOfMonth(13)));
		Assert.assertThat(transaction.getUsd(), IsEqual.equalTo(Money.zero(CurrencyUnit.USD)));
	}
}
