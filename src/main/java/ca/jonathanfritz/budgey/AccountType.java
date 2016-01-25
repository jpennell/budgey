package ca.jonathanfritz.budgey;

public enum AccountType {
	CHECKING("Chequing"),
	VISA("Visa"),
	ROYAL_CREDIT_LINE("Royal Credit Line");

	private final String type;

	private AccountType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static AccountType fromString(String type) {
		for (final AccountType at : AccountType.values()) {
			if (type.equals(at.type)) {
				return at;
			}
		}
		throw new IllegalArgumentException("No matching AccountType for string " + type);
	}
}
