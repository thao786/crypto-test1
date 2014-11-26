package test;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.ByteWorks;

/*
 * Encrypt the plaintext "Facebook" using DES 
 * without any padding or mode of operation using the key "universe" 
 * and output the ciphertext in binary (use the byteToBin method in util). 
 * Next, generate a random number between 0 and 63; 
 * flip that bit in the plaintext; encrypt; 
 * and compute the number of bits in the ciphertext that have flipped as a result. 
 * Re-do this avalanche effect computation several times 
 * and determine the average number of flipped bits in the ciphertext.
 */

public class act2 {
	
	public static void q10 () throws Exception{
		Key secret = new SecretKeySpec("universe".getBytes(), "DES");
		// no IV, our text is 64b
		Cipher ce = Cipher.getInstance("DES/ECB/NoPadding");
		ce.init(Cipher.ENCRYPT_MODE, secret);
		byte[] plaintext = "Facebook".getBytes();		
		byte[] ciphertext = ce.doFinal(plaintext);
		
		StringBuffer plaintext_byte_string = new StringBuffer(ByteWorks.bytesToBin(plaintext)); 
		System.out.println("original Facebook --"+plaintext_byte_string);

		int random = (int) Math.ceil(Math.random()*63);
		System.out.println("random is ----"+random);
		
		if (plaintext_byte_string.charAt(random) == '0'){
			plaintext_byte_string.setCharAt(random, '1');	
		}else{
			plaintext_byte_string.setCharAt(random, '0');	
		}
		System.out.println("flipbit Facebook  --"+plaintext_byte_string);
		
		//convert it back to byte array
		byte[] plaintext2 = ByteWorks.stringToByte(plaintext_byte_string.toString());
		System.out.println("plaintext2 is ----"+plaintext2);
				
		byte[] ciphertext2 = ce.doFinal(plaintext2);
				
		ce.init(Cipher.DECRYPT_MODE, secret);
		byte[] back = ce.doFinal(ciphertext);
		byte[] back2 = ce.doFinal(ciphertext2);
		System.out.println("ciphertext1 is ----"+new String(back));
		System.out.println("ciphertext2 is ----"+new String(back2));
		
		//calculate avalanche effect
		String ct = ByteWorks.bytesToBin(ciphertext);
		String ct2 = ByteWorks.bytesToBin(ciphertext2);
		System.out.println(ct.length() + "    "  + ct);
		System.out.println(ct2.length() + "    "  +ct2);
		int flips = 0;
		for (int i=0; i<ct.length(); i++){
			try {
				if (ct.charAt(i) != ct2.charAt(i)) 
					flips ++;
			} catch (Exception e) {
				System.out.println("FAIL:   " + i);
				System.out.println(ct.length());
				System.out.println(ct2.length());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("avalanche effect is " + flips);
	}
	
	public static void q11 () throws Exception{	
		Key secret = new SecretKeySpec(
				"CSE@YORK".getBytes(), "DES");
		
		AlgorithmParameterSpec aps = new IvParameterSpec(
				ByteWorks.hexToByte("4E51297B424F90D8"));
		
		Cipher ce = Cipher.getInstance("DES/CBC/PKCS5Padding");
		
		byte[] ct = ByteWorks.hexToByte("F7E9FBA81DE5290B");		
	
		ce.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] back = ce.doFinal(ct);
		System.out.println(new String(back));
	}

	public static void main(String[] args) throws Exception{
	//	q10 ();
		q11 ();
	}	
}
