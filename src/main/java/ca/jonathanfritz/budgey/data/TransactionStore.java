package ca.jonathanfritz.budgey.data;

import ca.jonathanfritz.budgey.Transaction;

import java.util.List;

public interface TransactionStore {

	void put(final List<Transaction> transactions);

	List<Transaction> list();

}
