package ca.jonathanfritz.budgey.data.memory;

import ca.jonathanfritz.budgey.Transaction;
import ca.jonathanfritz.budgey.data.TransactionStore;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryTransactionStore implements TransactionStore {

	private final Map<String, Transaction> transactions;

	public InMemoryTransactionStore() {
		this.transactions = Maps.newHashMap();
	}

	@Override
	public void put(final List<Transaction> transactions) {
		transactions.stream().forEach(this::put);
	}

	public void put(final Transaction transaction) {

		if(!this.transactions.containsKey(transaction.getTransactionId())) {
			this.transactions.put(transaction.getTransactionId(), transaction);
		}

	}

	@Override
	public List<Transaction> list() {
		return this.transactions.values().stream().collect(Collectors.toList());
	}

}
