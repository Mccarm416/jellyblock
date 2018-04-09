package com.dreamteam.jellyblock;
import java.security.MessageDigest;

public class StringHasher {
	//Takes a transaction string and applies a SHA256 algorithm to it and returns the newly generated string
	public static String hashFunction(String input){		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");	
			//Apply SHA256 to the given string
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			//Holds the newly generated hash as a hexidecimal
			StringBuffer hexHash = new StringBuffer(); 
			
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexHash.append('0');
				hexHash.append(hex);
			}
			return hexHash.toString();
		}
		
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	} 
	
	public static String calculateHash(String previousHash, String transaction) {
		//Calculate the hash to be used in the next block
		String calculatedhash = hashFunction (previousHash + transaction);
		return calculatedhash;
	}
}
