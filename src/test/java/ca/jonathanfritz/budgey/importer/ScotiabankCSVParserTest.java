package ca.jonathanfritz.budgey.importer;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.util.ClasspathLoader;
import org.hamcrest.core.IsEqual;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class ScotiabankCSVParserTest {

	private static final String csv = "importer/csv/scotia.csv";

	private static final String ACCOUNT_NUMBER = "1234";

	@Test
	public void validTransactionParsingTest() {

		final String line = ClasspathLoader.load(csv);
		final String[] fields = FieldSanitizer.sanitizeFields(line.split(","));
		final ScotiabankCSVParser parser = new ScotiabankCSVParser(ACCOUNT_NUMBER);
		final Transaction transaction = parser.parse(fields);

		Assert.assertThat(transaction.getDescription(), IsEqual.equalTo("MOVATI ATHLETIC WATERLOO WATERLOO     ON"));
		Assert.assertThat(transaction.getAmount().getAmount().doubleValue(), IsEqual.equalTo(-508.50D));
		Assert.assertThat(transaction.getTransactionDate(), IsEqual.equalTo((new DateTime())
				.withTimeAtStartOfDay()
				.withYear(2015)
				.withMonthOfYear(9)
				.withDayOfMonth(1)));
		Assert.assertThat(transaction.getAccountNumber(), IsEqual.equalTo(ACCOUNT_NUMBER));

	}

}
