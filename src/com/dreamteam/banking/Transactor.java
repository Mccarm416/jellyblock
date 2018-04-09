package com.dreamteam.banking;

import com.dreamteam.jellyblock.JellyBlock;
import com.dreamteam.jellyblock.JellyHelper;

public class Transactor {
	
	public static String transactionCreate(Account sender, Account receiver, double amount) {
		//Create a new transaction with the format of "Acnt#:senderAcnt# -> amount Acnt#receieverAcnt# @ txTime"
			String newTransactionTime = JellyHelper.giveDateString();
			String newTransaction = "\n| ACCNT#:[" + sender.getAccountNumber() + "] PK:[" + JellyHelper.getStringFromKey(sender.getPublicKey()) + "\n - SENDING  $" + amount + 
					" TO - \nACCNT#:[" + receiver.getAccountNumber() +"] PK:[" + JellyHelper.getStringFromKey(receiver.getPublicKey()) + "]\n@\n" + newTransactionTime + " |\n";
			sender.setBalance(sender.getBalance() - amount);
			receiver.setBalance(receiver.getBalance() + amount);
			System.out.println("Transaction succeeded! Transaction -> " + newTransaction);
			System.out.println("New balances: Sender -> $" + sender.getBalance() + " Receiver -> $" + receiver.getBalance());
			System.out.println("Block chain size: " + JellyBlock.blockChainSize);
			return newTransaction;
	}
	
	private static boolean verifyTransaction(Account sender, Account receiver, double amount) {
		//Verify the 2 accounts are not the same
		if (sender == receiver) {
			System.out.println("Transaction failed! The sending and recieving accounts are both the same!");
			return false;
		}
		//Verify the sender is able to send the amount
		else if(sender.getBalance() - amount <= 0 ) {
			System.out.println("Transaction failed! Insufficient funds! ($" + sender.getBalance() +")");
			return false;
		}
		//Verify the amount is greater than 0
		else if (amount <= 0) {
			System.out.println("Transaction failed! Amount sent must be greater than 0!");
			return false;
		}
		return true;
	}
	
	public static void attemptTransaction(Account sender, Account receiver, double amount) {
		//Attempt to do the transaction based on the senders balance
		if (verifyTransaction(sender, receiver, amount)) {
			JellyBlock block = new JellyBlock(JellyBlock.currentBlock.getHash(), transactionCreate(receiver, sender, amount), sender, receiver);
			JellyBlock.currentBlock = block;
		}
	}
}
