package ca.jonathanfritz.budgey.commands;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.importer.CSVImporter;
import ca.jonathanfritz.budgey.importer.CSVParser;
import ca.jonathanfritz.budgey.importer.RoyalBankCSVParser;
import ca.jonathanfritz.budgey.importer.ScotiabankCSVParser;
import io.dropwizard.cli.Command;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import java.util.List;

public class ImportCommand extends Command {

	private static final String PARSER_KEY = "parser";

	private static final String ROYAL_BANK_PARSER = "royal";

	private static final String SCOTIA_BANK_PARSER = "scotia";

	private static final String FILE_KEY = "file";

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
				.help("The transaction file to import. [file://path/to/file.csv]");

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
				parser = new ScotiabankCSVParser();
				break;
		}

		final CSVImporter<CSVParser> csvImporter = new CSVImporter<>(parser);
		final List<Transaction> transactions = csvImporter.importFile(file);

		for (final Transaction t : transactions) {
			System.out.println(t.toString());
		}

	}

}
