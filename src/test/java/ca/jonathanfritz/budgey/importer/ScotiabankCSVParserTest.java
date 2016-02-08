package ca.jonathanfritz.budgey.importer;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.util.ClasspathLoader;
import org.hamcrest.core.IsEqual;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

public class ScotiabankCSVParserTest {

	private static final String csv = "importer/csv/scotia.csv";

	@Test
	public void validTransactionParsingTest() {

		final String line = ClasspathLoader.load(csv);
		final String[] fields = FieldSanitizer.sanitizeFields(line.split(","));
		final ScotiabankCSVParser parser = new ScotiabankCSVParser();
		final Transaction transaction = parser.parse(fields);

		Assert.assertThat(transaction.getDescription1(), IsEqual.equalTo("MOVATI ATHLETIC WATERLOO WATERLOO     ON"));
		Assert.assertThat(transaction.getCad().getAmount().doubleValue(), IsEqual.equalTo(-508.50D));
		Assert.assertThat(transaction.getTransactionDate(), IsEqual.equalTo((new DateTime())
				.withTimeAtStartOfDay()
				.withYear(2015)
				.withMonthOfYear(9)
				.withDayOfMonth(1)));

	}

}
