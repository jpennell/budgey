package ca.jonathanfritz.budgey.importer;

import ca.jonathanfritz.budgey.Transaction;

public interface CSVParser {

	Transaction parse(String[] fields);
}
