package ca.jonathanfritz.budgey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ca.jonathanfritz.budgey.importer.CSVImporter;
import ca.jonathanfritz.budgey.importer.RoyalBankCSVParser;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Budgey
{
	@Parameter(names = "--csv", description = "the path to a csv file to import")
	private String csvPath;

	@Parameter(names = "--help", help = true)
	private boolean help;

	public void run() throws FileNotFoundException, IOException {
		if (help || StringUtils.isBlank(csvPath)) {
			displayHelp();
			return;
		}

		final RoyalBankCSVParser royalBankCsvParser = new RoyalBankCSVParser();
		final CSVImporter<RoyalBankCSVParser> csvImporter = new CSVImporter<RoyalBankCSVParser>(royalBankCsvParser);

		final List<Transaction> transactions = csvImporter.importFile(csvPath);
		for (final Transaction t : transactions) {
			System.out.println(t.toString());
		}
	}

	private void displayHelp() {
		System.out.println("Budgey v0.1");
		System.out.println("A budgeting utility for people with dollars and sense.\n");
		System.out.println("usage: budgey.jar --csv [a file to import]");
		System.out.println("--help\t display this help text");
		System.out.println("--csv\t the path to a CSV file to import\n");
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		final Budgey budgey = new Budgey();
		new JCommander(budgey, args);
		budgey.run();
	}
}
