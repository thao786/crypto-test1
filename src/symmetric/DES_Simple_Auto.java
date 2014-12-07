package symmetric;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import util.ByteWorks;

public class DES_Simple_Auto
{

	public static void main(String[] args) throws Exception
	{
		// Encrypt "HelloCSE" with an auto-gen key
		KeyGenerator kg = KeyGenerator.getInstance("DES");
		kg.init(56);
		Key secret = kg.generateKey();
		// no IV, our text is 64b
		Cipher ce = Cipher.getInstance("DES/ECB/NoPadding");
		ce.init(Cipher.ENCRYPT_MODE, secret);
		byte[] pt = "HelloCSE".getBytes();
		byte[] ct = ce.doFinal(pt);
		System.out.println("PT = " + ByteWorks.byteToHex(pt));
		System.out.println("CT = " + ByteWorks.byteToHex(ct));
		System.out.println("KY = " + ByteWorks.byteToHex(secret.getEncoded()));
		
		ce.init(Cipher.DECRYPT_MODE, secret);
		byte[] back = ce.doFinal(ct);
		System.out.println(new String(back));
	}

}