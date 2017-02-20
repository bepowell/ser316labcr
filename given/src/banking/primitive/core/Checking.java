/*
  File:	Checking
  Author: kevinagary
  Date:	2/19/17
  
  Description: File for the Checking class
*/

package banking.primitive.core;


/**
  Class: Checking	
  
  Description: Represents a checking account
*/
public class Checking extends Account {

	private Checking(String name) {
		super(name);
	}

	/**
		Method: createChecking
		Inputs: name of new checking account
		Returns: Checking new account

		Description: creates and returns a new Checking account
	*/
    public static Checking createChecking(String name) {
        return new Checking(name);
    }

	/**
		Method: Checking
		Inputs: name of checking account
				balance of the checkng account
		Returns:

		Description: Constructor for Checking
	*/
	public Checking(String name, float balance) {
		super(name, balance);
	}

	/**
		Method: getType
		Inputs: 
		Returns: String "Checking"

		Description: returns "Checking"
	*/
	public String getType() { 
		return "Checking"; 
	}
	
	/**
		Method: deposit
		Inputs: amount of the deposit
		Returns: boolean true if successful, false if not

		Description: A deposit may be made unless the Checking account is closed
	*/
	public boolean deposit(float amount) {
		if (_getState() != State.CLOSED && amount > FLOAT_ZERO) {
			balance = balance + amount;
			if (balance >= FLOAT_ZERO) {
				_setState(State.OPEN);
			}
			return true;
		}
		return false;
	}

	/**
		Method: withdraw
		Inputs: amount of the withdrawal
		Returns: boolean true if successful, false if not

		Description: After 10 withdrawals a fee of $2 is charged per transaction You may 
					 continue to withdraw an overdrawn account until the balance is below -$100
	*/
	public boolean withdraw(float amount) {
		if (amount >= FLOAT_ZERO) {		
			// KG: incorrect, last balance check should be >=
			if (_getState() == State.OPEN || (_getState() == State.OVERDRAWN && balance > -WITHDRAW_LIMIT_AMOUNT_LEFT)) {
				balance = balance - amount;
				numWithdraws++;
				if (numWithdraws > WITHDRAW_LIMIT){
					balance = balance - WITHDRAW_FEE;
				}
				if (balance < FLOAT_ZERO) {
					_setState(State.OVERDRAWN);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
		Method: toString
		Inputs: 
		Returns: String representation of Checking

		Description: Returns String representation of Checking
	*/
	public String toString() {
		return "Checking: " + getName() + ": " + getBalance();
	}
	
	
	private int numWithdraws = 0;
	private final float FLOAT_ZERO = 0.0f;
	private final float WITHDRAW_LIMIT_AMOUNT_LEFT = 100.0f;
	private final float WITHDRAW_LIMIT = 10;
	private final float WITHDRAW_FEE = 2.0f;
  
}
