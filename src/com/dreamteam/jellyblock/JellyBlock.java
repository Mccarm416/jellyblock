package com.dreamteam.jellyblock;

import java.util.Date;

public class JellyBlock {
	private String hash;
	private String previousHash;
	private String transaction;
	private long timeStamp;
	public static int blockChainSize = 0;
	public static JellyBlock currentBlock;

	//Basic JellyBlock constructor
	public JellyBlock(String previousHash, String transaction) {
			this.transaction = transaction;
			this.previousHash = previousHash;
			this.hash = StringHasher.calculateHash(previousHash, transaction);
			timeStamp = new Date().getTime();
			blockChainSize++;
	}

	public JellyBlock() {
		//Used to create the first block of the block chain (the 'genesis' block)
		if (blockChainSize == 0) {
		//Instantiate the genesis block
		System.out.println("Creating genesis block...");
		JellyBlock genesisBlock = new JellyBlock("In the beginning when God created the heavens and the earth,", 
				"the earth was a formless void and darkness covered the face of the deep, while a wind from God swept over the face of the waters.");
		currentBlock = genesisBlock;
		}
		else {
			System.out.println("Genesis block has already been instantiated! Only one is needed.");
		}
	}

	public long getTimeStamp() {
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
	
	public static void displayCurrentBlockInfo() {
        System.out.println("Current block previous hash -> " + currentBlock.getPreviousHash());
		System.out.println("Current block hash -> " + currentBlock.getHash());
	}
	
}
