/*
  File:	Account
  Author: kevinagary
  Date:	2/19/17
  
  Description: Abstract account class file
*/
package banking.primitive.core;

/**
  Class: Account
  
  Description: Abstract Account class
*/
public abstract class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    protected enum State {
        OPEN, CLOSED, OVERDRAWN
    };

 
    protected Account(String n) {
        name = n;
        state = State.OPEN;
    }

    protected Account(String n, float b) {
        this(n); 
        balance = b;
    }


    /**
		Method: getBalance
		Inputs: 
		Returns: balance of the account

		Description: Gets the balance
	*/
    public final float getBalance() {
        return balance;
    }
    
    /**
		Method: getName
		Inputs: 
		Returns: name of the account

		Description: Gets the name
	*/
    public final String getName() {
        return name;
    }
    
    /**
		Method: _getState
		Inputs: 
		Returns: state of the account

		Description: Gets the state
	*/
    protected final State _getState() {
        return state;
    }

    /**
		Method: getType
		Inputs: 
		Returns: either checkings or savings

		Description: Gets the type of account
	*/
    public abstract String getType();


    protected final void _setState(State s) {
        state = s;
    }
	 
    /**
		Method: deposit
		Inputs: amount is a deposit and must be > 0
		Returns: true if the deposit was successful, false if not due to amount or invalid state

		Description: Adds money to an account. May not be done if the account is CLOSED
	*/
    public abstract boolean deposit(float amount);

    /**
     * Takes money out of an account. If the balance falls below 0 then the
     * account is moved to an OVERDRAWN state
     * 
     * @param parameter
     *            amount is a withdrawal and must be > 0
     * @return true if the deposit was successful, false if not due to amount or
     *         invalid state
     */
	 
    /**
		Method: withdraw
		Inputs: amount is a withdrawal and must be > 0
		Returns: true if the withdrawal was successful, false if not due to amount or invalid state

		Description: Takes money out of an account. If the balance falls below 0 then the
					 account is moved to an OVERDRAWN state
	*/
    public abstract boolean withdraw(float amount);

	/**
		Method: toString
		Inputs: 
		Returns: String representation of Account

		Description: Returns a String representation of Account
	*/
    public String toString() {
        return "Account " + name + " has $" + balance + "and is " + _getState()
                + "\n";
    }
    protected float balance = 0.0F;
    protected String name;
    private State state;

}
