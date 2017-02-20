/*
  File:	AccountServer
  Author: kevinagary
  Date:	2/19/17
  
  Description: File for the AccountServer class
*/

package banking.primitive.core;

import java.io.IOException;
import java.util.List;
//fixed

/**
  Class: AccountServer
  
  Description: interface for account server
*/
public interface AccountServer {
	 
	/**
		Method: getAccount
		Inputs: name name of the account 
		Returns: Account object or null if not found. 

		Description: Gets the Account that is named name
	*/
	public Account	getAccount(String name);

	/**
		Method: getActiveAccounts
		Inputs: 
		Returns: a list of Accounts inside the server that are not CLOSED 

		Description: Gets a list of Accounts inside the server that are not CLOSED
	*/
	public List<Account> getActiveAccounts();
	
	/**
		Method: getAllAccounts
		Inputs: 
		Returns: a list of all Accounts inside the server 

		Description: Gets a list of all Accounts inside the server 
	*/
	public List<Account> getAllAccounts();
	
	/**
		Method: newAccount
		Inputs: type must be one of Savings or Checking
				name leading or trailing whitespace is removed
				balance must be non-negative
		Returns: boolean true if the account was created and stored, false otherwise

		Description: Create a new account object in the server. if an account already exists with the given name
					 then a new account is not created and stored.
	*/
	public boolean	newAccount(String type, String name, float balance) throws IllegalArgumentException;

	/**
		Method: closeAccount
		Inputs: name leading or trailing whitespace is removed
		Returns: boolean true if there was an account with this name and close was successful

		Description: Close an account 
	*/
	public boolean	closeAccount(String name);



	/** 
	 * Saves the state of the server
	 * @throws IOException if unable to save the state
	 */
/**
		Method: saveAccounts
		Inputs: 
		Returns: void

		Description: Saves the state of the server
	*/
	public void	saveAccounts() throws IOException;
}
