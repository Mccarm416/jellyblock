package com.dreamteam.jellyblock;

import com.dreamteam.banking.Account;
import com.dreamteam.banking.Transactor;
import com.dreamteam.banking.User;

import java.security.Security;


public class JellyBlockTest {

	public static void main(String[] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		
		//Instantiate users and accounts
		System.out.println("Creating user 'David Stone' with 2 accounts of a balance of $500 and $750");
		User user1 = new User("David", "Stone");
		Account account = new Account(500);
		user1.addAccount(account);
		account = new Account(750);
		user1.addAccount(account);
		System.out.println("Creating user 'Mary Sue' with 2 accounts of a balance of $120.60 and $320.50");
		User user2 = new User("Mary", "Sue");
		account = new Account(120.60);
		user2.addAccount(account);
		account = new Account(320.50);
		user2.addAccount(account);
		//Instantiate the Genesis block
		@SuppressWarnings("unused")
		JellyBlock genesisBlock = new JellyBlock();
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 1
		System.out.println("\nAttempting transcation James Stone[Acnt0] -> Mary Sue[Acnt0] : $25" );
		Transactor.attemptTransaction(user1.getAccounts().get(0), user2.getAccounts().get(0), 25);
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 2
		System.out.println("\nAttempting transcation James Stone[Acnt0] -> James Stone[Acnt1] : $96.73" );
		Transactor.attemptTransaction(user1.getAccounts().get(0), user1.getAccounts().get(1), 96.73);
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 3
		System.out.println("\nAttempting transcation James Stone[Acnt0] -> Mary Sue[Acnt0] : $99999" );
		Transactor.attemptTransaction(user1.getAccounts().get(0), user2.getAccounts().get(0), 99999);
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 4
		System.out.println("\nAttempting transcation Mary Sue[Acnt0] -> James Stone[Acnt1] : $200" );
		Transactor.attemptTransaction(user2.getAccounts().get(0), user1.getAccounts().get(1), 65);
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 5
		System.out.println("\nAttempting transcation Mary Sue[Acnt0] -> Mary Sue[Acnt1] : $275.68" );
		Transactor.attemptTransaction(user2.getAccounts().get(0), user2.getAccounts().get(1), 149.73);
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 6
		System.out.println("\nAttempting transcation Mary Sue[Acnt0] -> Mary Sue[Acnt0] : $1.00" );
		Transactor.attemptTransaction(user2.getAccounts().get(0), user2.getAccounts().get(0), 1.00);
		JellyBlock.displayCurrentBlockInfo();
		
		//Transaction 7
		System.out.println("\nAttempting transcation Mary Sue[Acnt1] -> James Stone[Acnt0] : -$1.00" );
		Transactor.attemptTransaction(user2.getAccounts().get(0), user1.getAccounts().get(0), -1.00);
		JellyBlock.displayCurrentBlockInfo();
		
		user1.displayAccountBalances();
		user2.displayAccountBalances();
	}
}
