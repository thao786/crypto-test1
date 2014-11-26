package rsa;

import java.math.BigInteger;
import java.security.*;

import javax.crypto.*;

import util.ByteWorks;

import java.security.interfaces.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class q10 {

	public static void main(String[] args) throws Exception{
		BigInteger e = new BigInteger("74327");
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722" + 
								      "07092649169084385369007124813013442783232496672858253283236322154223178706820376"+ 
								      "30270674000828353944598575250177072847684118190067762114937353265007829546216602"+ 
								      "56501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger ct = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629"+
								     "52991052582846432979261500260278236678653125327546335884041286783340625646715334"+
								     "51395019521734099553221296896703454456327755743017818003765454489903326085581032"+
								     "66831217073027652061091790342124418143422318965525239492387183438956");
		BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127"+
									"888522373061586445417642355843316524942445924294144921649080401518286829171");

		BigInteger q = n.divide(p);
		BigInteger phi = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
		BigInteger d = e.modInverse(phi);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);
		RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(n, d);
		RSAPublicKey pubKey = (RSAPublicKey)keyFactory.generatePublic(pubKeySpec);
		RSAPrivateKey privKey = (RSAPrivateKey)keyFactory.generatePrivate(privKeySpec);
		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		
		cipher.init(Cipher.DECRYPT_MODE, privKey);
		byte[] bk = cipher.doFinal(ct.toByteArray());
		String result = new String(bk);
		System.out.println(result.trim());
		
	}
}
