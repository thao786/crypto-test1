package rsa;

import java.math.BigInteger;
import java.security.*;

import javax.crypto.*;

import util.ByteWorks;

import java.security.interfaces.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class RSAjce2
{
	public static void main(String[] args) throws Exception
	{	
		// ------------- Message must be shorter than size
		byte[] pt = "cse03257:fall2010".getBytes();

		BigInteger n = new BigInteger(
				"945874683351289829816050197767812346183848578056570056860845622609107886220137"+
				"220709264916908438536900712481301344278323249667285825328323632215422317870682"+
				"037630270674000828353944598575250177072847684118190067762114937353265007829546"+
				"21660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger d = new BigInteger(
					"7289370196881601766768920490284861650464951706793000236386405648425161747775298"+
			      	"3441046583933853592091262678338882236956093668440986552405421520173544428836766"+
			      	"3419319185756836904299985444024205035318170370675348574916529512369448767695219"+
			      	"8090537385200990850805837963871485320168470788328336240930212290450023");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(n, d);
		RSAPublicKey pubKey = (RSAPublicKey)keyFactory.generatePublic(pubKeySpec);
		RSAPrivateKey privKey = (RSAPrivateKey)keyFactory.generatePrivate(privKeySpec);

		// ------------- Use pkcs1 or no padding
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);

		// ------------- Encrypt
		byte[] ct = cipher.doFinal(pt);
		System.out.println(ByteWorks.byteToHex(ct));
		
		// -------------- Decrypt for sanity
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		byte[] bk = cipher.doFinal(ct);
		String result = new String(bk);
		System.out.println(result.trim());

		System.out.println("n\t"+ privKey.getModulus());
		System.out.println("e\t"+ pubKey.getPublicExponent());
		System.out.println("d\t"+ privKey.getPrivateExponent());
	}
}

