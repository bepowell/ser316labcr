/*
  File: ServerSolution	
  Author: kevinagary
  Date:	2/19/2017
  
  Description: File for the ServerSolution class
*/

package banking.primitive.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

import banking.primitive.core.Account.State;

/**
  Class: ServerSolution
  
  Description: ServerSolution class
*/
class ServerSolution implements AccountServer {

	/**
		Method: ServerSolution
		Inputs:
		Returns:

		Description: Constructor for ServerSolution
	*/
	public ServerSolution() {
		accountMap = new HashMap<String,Account>();
		File file = new File(fileName);
		ObjectInputStream in = null;
		try {
			if (file.exists()) {
				System.out.println("Reading from file " + fileName + "...");
				in = new ObjectInputStream(new FileInputStream(file));

				Integer sizeI = (Integer) in.readObject();
				int size = sizeI.intValue();
				for (int i=0; i < size; i++) {
					Account acc = (Account) in.readObject();
					if (acc != null){
						accountMap.put(acc.getName(), acc);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}
	

	//Fixed order of methods
	
	/**
		Method: getAccount
		Inputs: String name
		Returns: Account with the name

		Description: Returns the account with the given name
	*/
	public Account getAccount(String name) {
		return accountMap.get(name);
	}

	/**
		Method: getActiveAccounts
		Inputs: 
		Returns: List of active accounts

		Description: Returns a List of active accounts
	*/
	public List<Account> getActiveAccounts() {
		List<Account> result = new ArrayList<Account>();

		for (Account acc : accountMap.values()) {
			if (acc._getState() != State.CLOSED) {
				result.add(acc);
			}
		}
		return result;
	}
	
	/**
		Method: getAllAccounts
		Inputs: 
		Returns: List of accounts

		Description: Returns a List of all accounts
	*/
	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountMap.values());
	}
	
	private boolean _newAccountFactory(String type, String name, float balance)
		throws IllegalArgumentException {
		
		if (accountMap.get(name) != null) {
			return false;
		} 
		
		Account acc;
		if ("Checking".equals(type)) {
			acc = new Checking(name, balance);

		} else if ("Savings".equals(type)) {
			acc = new Savings(name, balance);

		} else {
			throw new IllegalArgumentException("Bad account type:" + type);
		}
		try {
			accountMap.put(acc.getName(), acc);
		} catch (Exception exc) {
			return false;
		}
		return true;
	}

	
	/**
		Method: newAccount
		Inputs: String type of account
				String name of account
				float balance of account
		Returns: true if successful, false if not

		Description: Creates a new account with the given information
	*/
	public boolean newAccount(String type, String name, float balance) 
		throws IllegalArgumentException {
		
		if (balance < ACCOUNT_ZERO_BAL) throw new IllegalArgumentException("New account may not be started with a negative balance");
		
		return _newAccountFactory(type, name, balance);
	}
	
	/**
		Method: closeAccount
		Inputs: String name of account
		Returns: true if successful, false if not

		Description: Closes the account with the given name
	*/
	public boolean closeAccount(String name) {
		Account acc = accountMap.get(name);
		if (acc == null) {
			return false;
		}
		acc._setState(State.CLOSED);
		return true;
	}
	
	/**
		Method: saveAccounts
		Inputs: 
		Returns: 

		Description: Saves the accounts
	*/
	public void saveAccounts() throws IOException {
		ObjectOutputStream out = null; 
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));

			out.writeObject(Integer.valueOf(accountMap.size()));
			for (int i=0; i < accountMap.size(); i++) {
				out.writeObject(accountMap.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Could not write file:" + fileName);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}
	
	private final float ACCOUNT_ZERO_BAL = 0.0f;
	//Made variables private
	private static String fileName = "accounts.ser";

	private Map<String,Account> accountMap = null;

}
