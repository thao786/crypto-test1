package symmetric;



import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import util.ByteWorks;

public class DES_Simple_MyKey
{

	public static void main(String[] args) throws Exception
	{
		// Encrypt "HelloCSE" with key "MyOwnKey"
		Key secret = new SecretKeySpec("MyOwnKey".getBytes(), "DES");
		// no IV, our text is 64b
		Cipher ce = Cipher.getInstance("DES/ECB/NoPadding");
		ce.init(Cipher.ENCRYPT_MODE, secret);
		byte[] pt = "IelloCSEHelloCSE".getBytes();
		byte[] ct = ce.doFinal(pt);
		System.out.println("PT = " + ByteWorks.byteToHex(pt));
		System.out.println("CT = " + ByteWorks.byteToHex(ct));
		System.out.println("KY = " + new String(secret.getEncoded()));
		
		
		
		
		
		ce.init(Cipher.DECRYPT_MODE, secret);
		byte[] back = ce.doFinal(ct);
		System.out.println(new String(back));
	}

}