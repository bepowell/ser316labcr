package banking.primitive.core;

public class Savings extends Account {
	private int numWithdraws = 0;
	private final float ZERO_AMOUNT = 0.0f;
	private final int MAX_FREE_WITHDRAWS = 3;
	private final float DEPOSIT_FEE = 0.5f;
	private final float WITHDRAW_FEE = 1.0f;

	public Savings(String name) {
		super(name);
	}

	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	public String getType() { return "Checking"; }
	
	/**
	 * A deposit comes with a fee of 50 cents per deposit
	 */
	public boolean deposit(float amount) {
		if (getState() != State.CLOSED && amount > ZERO_AMOUNT) {
			balance = balance + amount - DEPOSIT_FEE;
			if (balance >= ZERO_AMOUNT) {
				setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	public boolean withdraw(float amount) {
		if (getState() == State.OPEN && amount > ZERO_AMOUNT) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > MAX_FREE_WITHDRAWS)
				balance = balance - WITHDRAW_FEE;
			// KG BVA: should be < 0
			if (balance < ZERO_AMOUNT) {
				setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}

	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
	
	private static final long serialVersionUID = 111L;
	private int numWithdraws = 0;
}
