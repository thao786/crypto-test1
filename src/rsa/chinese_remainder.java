package rsa;

import  util.ByteWorks;
import java.math.BigInteger;

public class chinese_remainder {
	
	

	public static void main(String[] args) {
//		BigInteger a = new BigInteger("2");
//		BigInteger b = new BigInteger("3");
//		BigInteger p = new BigInteger("5");
//		BigInteger q = new BigInteger("7");
//		
//		BigInteger p_inverse_to_q = p.modInverse(q);
//		BigInteger q_inverse_to_p = q.modInverse(p);
//		
//		BigInteger y = a.multiply(q).multiply(q_inverse_to_p).
//				add(b.multiply(p).multiply(p_inverse_to_q)).
//				mod(p.multiply(q));
//		
//		System.out.println("chinese remainder: " + y);
		
		
		
		BigInteger a = new BigInteger("82994190510799396522331797639686276768940390085325404222860917576355778184237"+
           "76138636534937173175821217153438273964356322221685732151711969484042347391602"+
          "22667627344179650715195037284174432283746681012024349582787312975889174604539"+
          "19426528282097273722897748179063833582150194428186071706728385981636909081318");
		BigInteger q = new BigInteger("24446790503298370693173334475372618213955167099084888176269405820826261143456"
          + "91315773310307263588964730382151468313421276755551304098441086693424990901151"
          + "88009992096304224809571473251898743392356735801831229973743641370842254394459"
          + "47751575167273118347961881669884094483331110804819317868837178707363782883338");
		BigInteger p = new BigInteger("13091818006291548590095459763131362986483620197822713331918762421022714877615"
          + "43227070712457429806867411180308671484383704396602921369981043608607050325425"
           +"33349698053368186836516200888554212669145740474501485880521654642182193789294" 
          + "300080988945147087020219803028448157467640485725166289781136027695109151280023");
		BigInteger b = new BigInteger("24446790503298370693173334475372618213955167099084888176269405820826261143456"
          + "91315773310307263588964730382151468313421276755551304098441086693424990901151"
          + "88009992096304224809571473251898743392356735801831229973743641370842254394459"
          + "47751575167273118347961881669884094483331110804819317868837178707363782883338");
		
		BigInteger p_inverse_to_q = p.modInverse(q);
		BigInteger q_inverse_to_p = q.modInverse(p);
		
		BigInteger y = a.multiply(q).multiply(q_inverse_to_p).
				add(b.multiply(p).multiply(p_inverse_to_q)).
				mod(p.multiply(q));
		
		System.out.println("chinese remainder: " + y.toByteArray());
		System.out.println("as a string\t" + new String(y.toByteArray()).trim());

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		BigInteger n = new BigInteger("9327886631101977609987803434598887258392208251280307165451984416664791"+
//          "6261785073354252999661033823739872649192305178783791518737797092566893"+
//          "0321487352313895115251846528183261186683403798505233702948151543692369"+
//          "52734597211088629875903059097");
//		
//		
//		BigInteger eA = new BigInteger("391");
//		BigInteger eB = new BigInteger("6937");
//		BigInteger cA = new BigInteger("4335298459891461317289715523371906488589686643342172651510965539752476"
//        +   "7998165768246210713286687216909009932505123971723696936223367588654465"
//        +  "5950131615917235474484389229632911341182193608051751038383385492554067"
//         +  "2229807801706540269844905137135410998867362138736875787002758554974441"
//        +   "7587843993269783366820255291");
//		BigInteger cB = new BigInteger("6272192663602524018342853122259027078652155771947757288156515142799386"
//         +  "4352084601566113426602498514795980659634524847946227553444431615831604"
//         +  "2940828346047677562120595930717843113623983071037090363177680282611878"
//         +  "6913987538639326326104355858135229068615163434068377624750656835813131"
//         +  "8939839658370634446639196562");
//		
//		BigInteger a = new BigInteger("2129");
//		
//		BigInteger b = new BigInteger("120");
//		
//		
//		BigInteger i = cB.modInverse(n);
//		BigInteger c_to_a = cA.modPow(a, n);
//		BigInteger i_to_a = i.modPow(b, n);
//		BigInteger m = c_to_a.multiply(i_to_a).mod(n);
//		
//		
//		System.out.println("as a string\t" + new String(m.toByteArray()).trim());	
	}
}
