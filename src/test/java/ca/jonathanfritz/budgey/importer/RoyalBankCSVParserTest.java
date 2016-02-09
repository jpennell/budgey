package ca.jonathanfritz.budgey.importer;

import org.hamcrest.core.IsEqual;
import org.joda.money.CurrencyUnit;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.util.ClasspathLoader;

public class RoyalBankCSVParserTest {

	private static final String csv = "importer/csv/rbc-chequing-with-quotes.csv";
	private static final String usdCsv = "importer/csv/rbc-usd-transaction.csv";

	@Test
	public void validTransactionParsingTest() {
		final String line = ClasspathLoader.load(csv);
		final String[] fields = FieldSanitizer.sanitizeFields(line.split(","));
		final RoyalBankCSVParser parser = new RoyalBankCSVParser();
		final Transaction transaction = parser.parse(fields);

		Assert.assertThat(transaction.getAccountNumber(), IsEqual.equalTo("079022-11555"));
		Assert.assertThat(transaction.getDescription(), IsEqual.equalTo("Email Trfs. INTERAC E-TRF- 7336"));
		Assert.assertThat(transaction.getAmount().getAmount().doubleValue(), IsEqual.equalTo(53.00D));
		Assert.assertThat(transaction.getAmount().getCurrencyUnit(), IsEqual.equalTo(CurrencyUnit.CAD));
		Assert.assertThat(transaction.getTransactionDate(), IsEqual.equalTo((new DateTime())
				.withTimeAtStartOfDay()
				.withYear(2015)
				.withMonthOfYear(10)
				.withDayOfMonth(13)));
	}

	@Test
	public void usdParsingTest() {
		final String line = ClasspathLoader.load(usdCsv);
		final String[] fields = FieldSanitizer.sanitizeFields(line.split(","));
		final RoyalBankCSVParser parser = new RoyalBankCSVParser();
		final Transaction transaction = parser.parse(fields);

		Assert.assertThat(transaction.getAccountNumber(), IsEqual.equalTo("079022-11555"));
		Assert.assertThat(transaction.getDescription(), IsEqual.equalTo("Email Trfs. INTERAC E-TRF- 7336"));
		Assert.assertThat(transaction.getAmount().getAmount().doubleValue(), IsEqual.equalTo(75.00D));
		Assert.assertThat(transaction.getAmount().getCurrencyUnit(), IsEqual.equalTo(CurrencyUnit.USD));
		Assert.assertThat(transaction.getTransactionDate(), IsEqual.equalTo((new DateTime())
				.withTimeAtStartOfDay()
				.withYear(2015)
				.withMonthOfYear(10)
				.withDayOfMonth(13)));
	}
}
