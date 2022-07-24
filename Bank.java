package atm_func;

import java.util.ArrayList;
import java.util.Random;

public class Bank {
	
	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	/**
	 * Create  a new Bank object with empty lists of users and accounts
	 * @param name  the name of the bank
	 */
	public Bank(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * Generate a new universally unique ID for user.
	 * @return the uuid
	 */
	public String getNewUserUUID() {
		
		//inits
		String uuid;
		Random rng = new Random();
		int len = 6;
		boolean nonUnique;
		
		// Continuing looping until we get a unique ID
		do {
			//generate the number
			uuid = "";
			for(int c =0; c<len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			// check to make sure it's unique
			nonUnique = false;
			for(User u: this.users) {
				if(uuid.compareTo(u.getUUID())==0) {
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
	}
	
	public String getNewAccountUUID() {
		//inits
		String uuid;
		Random rng = new Random();
		int len = 10;
		boolean nonUnique;
		
		// Continuing looping until we get a unique ID
		do {
			//generate the number
			uuid = "";
			for(int c =0; c<len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			// check to make sure it's unique
			nonUnique = false;
			for(Account a: this.accounts) {
				if(uuid.compareTo(a.getUUID())==0) {
					nonUnique = true;
					break;
				}
			}
			
		}while(nonUnique);
		
		return uuid;
	}
	
	/**
	 * Add an account
	 * @param anAct  the account to add
	 */
	public void addAccount(Account anAct) {
		this.accounts.add(anAct);
	}
	
	/**
	 * Create a new user of the bank
	 * @param firstName the user's first name
	 * @param lastName  the user's last name
	 * @param pin       the user's pin
	 * @return			the new User Object
	 */
	public User addUser(String firstName, String lastName, String pin) {
		
		// Create a new User Object and add it to out list
		User newUser = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		// Create a savings account for the user
		Account newAccount = new Account("Savings", newUser, this);
		newUser.addAccount(newAccount);
		this.addAccount(newAccount);
		
		return newUser;
	}
	
	/**
	 * Get the User object associated with a particular userID and pin, 
	 * if they are valid
	 * @param userID	the UUID of the user to log in
	 * @param pin		the pin of the user
	 * @return			the User object, if the login is successful, or 
	 *					NULL, if it is not
	 */
	public User userLogin(String userID, String pin) {
		
		//search through list of users
		for(User u : this.users) {
			
			// Check user ID is Correct
			if(u.getUUID().compareTo(userID)==0 && u.validatePin(pin)) {
				return u;
			}
		}
		
		// if we haven't found the user or have an incorrect pin
		return null;
	}
	
	/**
	 * Get the name of the bank
	 * @return the name of the bank
	 */
	public String getName() {
		return this.name;
	}
}
