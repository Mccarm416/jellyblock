package com.dreamteam.jellyblock;
import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class JellyHelper {
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
		String calculatedhash = hashFunction(previousHash + transaction);
		return calculatedhash;
	}
	
	public static String getStringFromKey(Key key) {
		//Takes a public/private key and returns it as a string
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	public static String giveDateString() {
		long date = new Date().getTime();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String formattedDate = formatDate.format(date);
		return formattedDate;
	}
	
	//Applies ECDSA Signature and returns the result ( as bytes ).
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSig = dsa.sign();
			output = realSig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}
		
	//Verifies a String signature 
	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
		try {
			Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
