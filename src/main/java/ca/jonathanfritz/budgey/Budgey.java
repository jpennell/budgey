package ca.jonathanfritz.budgey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ca.jonathanfritz.budgey.importer.CSVImporter;
import ca.jonathanfritz.budgey.importer.RoyalBankCSVParser;

public class Budgey
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		final RoyalBankCSVParser royalBankCsvParser = new RoyalBankCSVParser();
		final CSVImporter<RoyalBankCSVParser> csvImporter = new CSVImporter<RoyalBankCSVParser>(royalBankCsvParser);

		final List<Transaction> transactions = csvImporter.importFile(args[0]);
		for (final Transaction t : transactions) {
			System.out.println(t.toString());
		}
	}
}
