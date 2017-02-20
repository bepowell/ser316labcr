/*
  File:	AccountServerFactory
  Author: kevinagary
  Date:	2/19/17
  
  Description: File for AccountServerFactory class
*/
package banking.primitive.core;

/**
  Class: AccountServerFactory	
  
  Description: Factory for account server
*/
public class AccountServerFactory {

	protected AccountServerFactory() {

	}

	/**
		Method: getMe
		Inputs:
		Returns: 

		Description: Constructor for AccountServerFactory
	*/
	public static AccountServerFactory getMe() {
		if (singleton == null) {
			singleton = new AccountServerFactory();
		}

		return singleton;
	}

	/**
		Method: lookup
		Inputs:
		Returns: AccountServer 

		Description: Returns new ServerSolution
	*/
	public AccountServer lookup() {
		return new ServerSolution();
	}
	
	protected static AccountServerFactory singleton = null;

}
