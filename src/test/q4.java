package test;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.*;

public class q4 {

	public static void main(String[] args) throws Exception
	{

		BigInteger g = new BigInteger("1189764068877937363846146167338638286425739196572993692965682569366341097141069998263395570909274633255291948889699631666011157726138016295598882944190605");
		BigInteger p = new BigInteger("1325381145334539358629448518279679008904176563248846688977280754468055426387578974281147797624669024229219594573053241551724763638929327015294360424366549");
		BigInteger q = new BigInteger("760221963115420466361881663545019104442925559693");
		BigInteger Y = new BigInteger("238798031115639984887276107391389446213082107084695411039149128034604179189303382254769606917989223743615871451102658150814951925710110771527560977845711");

		String msg = "On Dec 1st, sell all RBC shares in the portfolio and buy 5,750 BMO shares.";
		BigInteger r = new BigInteger("657085232116258040181430472141362000021747681660");
		BigInteger s = new BigInteger("490067537914838692497407674888646687207464807296");

		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
		SecureRandom rng = new SecureRandom();
		keyGen.initialize(512, rng);
		KeyPair pair = keyGen.generateKeyPair();
		PublicKey pub = pair.getPublic();
		PrivateKey prv = pair.getPrivate();

		
		
		Signature dsa = Signature.getInstance("SHA1withDSA");
		dsa.initSign(prv); 
		dsa.update(msg.getBytes()); 
		byte[] signature = Y.toByteArray(); // this encompasses r and s
		//----> Send the pt (possibly encrypted) and signature
		
		// Upon receipt, Bob does this:
		dsa.initVerify(pub); 
		dsa.update(msg.getBytes()); 
		System.out.println(dsa.verify(signature));
	}
}
