package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.ByteWorks;

public class DES_ChainAndPadding_MyKey{
	public static void main(String[] args) throws Exception{
		// Encrypt "HelloCSE" with key "MyOwnKey"
		Key secret = new SecretKeySpec("MyOwnKey".getBytes(), "DES");
		AlgorithmParameterSpec aps = new IvParameterSpec(ByteWorks.hexToByte("0123456701234567"));
		Cipher ce = Cipher.getInstance("DES/CBC/PKCS5Padding");
		ce.init(Cipher.ENCRYPT_MODE, secret, aps);
		
		byte[] pt = "Hello CSE".getBytes();
		byte[] ct = ce.doFinal(pt);
		
		System.out.println("PT = " + ByteWorks.byteToHex(pt));
		System.out.println("CT = " + ByteWorks.byteToHex(ct));
		System.out.println("KY = " + new String(secret.getEncoded()));
	
		ce.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] back = ce.doFinal(ct);
		System.out.println(new String(back));
	}
}