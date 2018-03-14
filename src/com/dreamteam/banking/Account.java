package com.dreamteam.banking;

public class Account {
	private int accountNumber;
	private double balance;
	private static int nextAccountNumber = 0;
	
	
	public Account(double balance) {
		accountNumber = nextAccountNumber;
		nextAccountNumber++;
		this.balance = balance;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
