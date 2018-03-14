package com.dreamteam.banking;

import java.util.ArrayList;

public class User {
	private int userId;
	private String fName;
	private String lName;
	private ArrayList<Account> accounts;
	private static int nextUserId = 0;
	
	public User(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
		accounts = new ArrayList<Account>();
		userId = nextUserId;
		nextUserId++;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void displayAccountBalances() {
		System.out.println("\nDisplaying balances for " + "[User: " + userId + "] " + fName + " " + lName);
		for(int i = 0; i < accounts.size();i++) {
			System.out.println("[Account: " + i + "] AccountId: " + accounts.get(i).getAccountNumber() + " - Balance: $" + accounts.get(i).getBalance());
		}
	}
}
