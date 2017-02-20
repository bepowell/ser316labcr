/*
  File:	Savings
  Author: kevinagary	
  Date:	2/19/2017
  
  Description: File for Savings class
*/
package banking.primitive.core;

/**
  Class: Savings	
  
  Description: Represents a Savings account
*/
public class Savings extends Account {

	/**
		Method: Savings
		Inputs:
		Returns:

		Description: Constructor for Savings
	*/
	public Savings(String name) {
		super(name);
	}

	/**
		Method: Savings
		Inputs: String name of the account
				float balance of the account
		Returns: 

		Description: Constructor for Savings
	*/
	public Savings(String name, float balance) throws IllegalArgumentException {
		super(name, balance);
	}

	/**
		Method: getType
		Inputs:
		Returns: String "Savings"

		Description: returns "Savings"
	*/
	public String getType() { 
		return "Savings"; 
	}
	
	/**
		Method: deposit
		Inputs: float amount to be deposited
		Returns: boolean true if successful, false if not

		Description: A deposit comes with a fee of 50 cents per deposit
	*/
	public boolean deposit(float amount) {
		if (_getState() != State.CLOSED && amount > ZERO_AMOUNT) {
			balance = balance + amount - DEPOSIT_FEE;
			if (balance >= ZERO_AMOUNT) {
				_setState(State.OPEN);
			}
		}
		return false;
	}

	/**
	 * A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
	 * An account whose balance dips below 0 is in an OVERDRAWN state
	 */
	/**
		Method: withdraw
		Inputs: float amount to be withdrawed
		Returns: boolean true if successful, false if not

		Description: A withdrawal. After 3 withdrawals a fee of $1 is added to each withdrawal.
					 An account whose balance dips below 0 is in an OVERDRAWN state
	*/
	public boolean withdraw(float amount) {
		if (_getState() == State.OPEN && amount > ZERO_AMOUNT) {
			balance = balance - amount;
			numWithdraws++;
			if (numWithdraws > MAX_FREE_WITHDRAWS){
				balance = balance - WITHDRAW_FEE;
			}
			// KG BVA: should be < 0
			if (balance < ZERO_AMOUNT) {
				_setState(State.OVERDRAWN);
			}
			return true;
		}
		return false;
	}

	/**
		Method: toString
		Inputs: 
		Returns: String representation of Savings

		Description: returns String representation of Savings
	*/
	public String toString() {
		return "Savings: " + getName() + ": " + getBalance();
	}
	
	private int numWithdraws = 0;
	private final float ZERO_AMOUNT = 0.0f;
	private final int MAX_FREE_WITHDRAWS = 3;
	private final float DEPOSIT_FEE = 0.5f;
	private final float WITHDRAW_FEE = 1.0f;
}
