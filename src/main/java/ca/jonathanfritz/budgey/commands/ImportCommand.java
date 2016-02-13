package ca.jonathanfritz.budgey.commands;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.importer.CSVImporter;
import ca.jonathanfritz.budgey.importer.CSVParser;
import ca.jonathanfritz.budgey.importer.RoyalBankCSVParser;
import ca.jonathanfritz.budgey.importer.ScotiabankCSVParser;
import com.google.common.base.Strings;
import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ImportCommand extends Command {

	private final NumberFormat cadFormat = NumberFormat.getNumberInstance(Locale.CANADA);

	private static final String PARSER_KEY = "parser";

	private static final String ROYAL_BANK_PARSER = "royal";

	private static final String SCOTIA_BANK_PARSER = "scotia";

	private static final String FILE_KEY = "file";

	private static final String ACCOUNT_KEY = "account";

	public ImportCommand() {
		super("import", "Import transactions.");
	}

	@Override
	public void configure(final Subparser subparser) {

		subparser.addArgument(String.format("-%s", PARSER_KEY.charAt(0)), String.format("--%s", PARSER_KEY))
				.dest(PARSER_KEY)
				.type(String.class)
				.setDefault(ROYAL_BANK_PARSER)
				.required(true)
				.help("The parser to use. [royal, scotia]");

		subparser.addArgument(String.format("-%s", FILE_KEY.charAt(0)), String.format("--%s", FILE_KEY))
				.dest(FILE_KEY)
				.type(String.class)
				.required(true)
				.help("The transaction file to import. [path/to/file.csv]");

		subparser.addArgument(String.format("-%s", ACCOUNT_KEY.charAt(0)), String.format("--%s", ACCOUNT_KEY))
				.dest(ACCOUNT_KEY)
				.type(String.class)
				.help("Account number. [path/to/file.csv]");

	}

	@Override
	public void run(final Bootstrap<?> bootstrap, final Namespace namespace) throws Exception {

		final String parserOption = namespace.getString(PARSER_KEY);
		final String file = namespace.getString(FILE_KEY);

		CSVParser parser;

		switch (parserOption) {
			default:
			case ROYAL_BANK_PARSER:
				parser = new RoyalBankCSVParser();
				break;
			case SCOTIA_BANK_PARSER:

				final String account = namespace.getString(ACCOUNT_KEY);

				if(Strings.isNullOrEmpty(account)) {
					System.out.println(String.format("field: %s is required when using parser: %s", ACCOUNT_KEY, SCOTIA_BANK_PARSER));
					return;
				}

				parser = new ScotiabankCSVParser(account);
				break;

		}

		final CSVImporter<CSVParser> csvImporter = new CSVImporter<>(parser);
		final List<Transaction> transactions = csvImporter.importFile(file);

		for (final Transaction t : transactions) {
			System.out.println(t.toString());
		}

	}

}
