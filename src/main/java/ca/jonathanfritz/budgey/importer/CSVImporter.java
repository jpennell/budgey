package ca.jonathanfritz.budgey.importer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ca.jonathanfritz.budgey.Transaction;

public class CSVImporter<T extends CSVParser> {

	private final T parser;

	public CSVImporter(T parser) {
		this.parser = parser;
	}

	public List<Transaction> importFile(String path) throws FileNotFoundException, IOException {
		final List<Transaction> transactions = new ArrayList<>();
		try (FileReader file = new FileReader(path)) {
			try (BufferedReader reader = new BufferedReader(file)) {
				String line = reader.readLine();
				while (StringUtils.isNotBlank(line)) {
					try {
						final String[] fields = FieldSanitizer.sanitizeFields(line.split(","));
						transactions.add(parser.parse(fields));
					} catch (final Throwable t) {
						System.out.println("Dropped line " + line);
						t.printStackTrace();
					} finally {
						line = reader.readLine();
					}
				}
			}
		}
		return transactions;
	}

}
