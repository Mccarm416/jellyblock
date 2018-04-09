package com.dreamteam.jellyblock;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.dreamteam.banking.Account;

public class JellyBlock {
	private String hash;
	private String previousHash;
	private PublicKey senderKey;
	private PublicKey receiverKey;
	private String transaction;
	private String timeStamp;
	public byte[] signature;
	public static int blockChainSize = 0;
	public static JellyBlock currentBlock;

	//Basic JellyBlock constructor
	public JellyBlock(String previousHash, String transaction, Account sender, Account receiver) {
			this.transaction = transaction;
			this.previousHash = previousHash;
			this.hash = JellyHelper.calculateHash(previousHash, transaction);
			senderKey = sender.getPublicKey();
			receiverKey = receiver.getPublicKey();
			timeStamp = JellyHelper.giveDateString();
			generateSignature(sender.getPrivateKey());
			blockChainSize++;
	}

	public JellyBlock() {
		//Used to create the first block of the block chain (the 'genesis' block)
		if (blockChainSize == 0) {
		//Instantiate the genesis block
		System.out.println("Creating genesis block...");
		Account adam = new Account(0.00);
		Account eve = new Account(0.00);

		JellyBlock genesisBlock = new JellyBlock("In the beginning when God created the heavens and the earth,", 
				"the earth was a formless void and darkness covered the face of the deep, while a wind from God swept over the face of the waters.", adam, eve);
		currentBlock = genesisBlock;
		}
		else {
			System.out.println("Genesis block has already been instantiated! Only one is needed.");
		}
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public String getTransaction() {
		return transaction;
	}
	
	public byte[] getSignature() {
		return signature;
	}

	public static JellyBlock getCurrentBlock() {
		return currentBlock;
	}

	private void generateSignature(PrivateKey senderPrivateKey) {
		String data = JellyHelper.getStringFromKey(senderKey) + JellyHelper.getStringFromKey(receiverKey) + transaction;
		signature = JellyHelper.applyECDSASig(senderPrivateKey,data);		
	}
	public boolean verifiySignature() {
		String data = JellyHelper.getStringFromKey(senderKey) + JellyHelper.getStringFromKey(receiverKey) + transaction;
		return JellyHelper.verifyECDSASig(senderKey, data, signature);
	}
	public static void displayCurrentBlockInfo() {
        System.out.println("Current block previous hash -> " + currentBlock.getPreviousHash());
		System.out.println("Current block hash -> " + currentBlock.getHash());
	}
	

	
}
