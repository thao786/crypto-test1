package rsa;

import java.math.BigInteger;
import java.util.Random;

/**********************************************
Demonstrate how RSA works using ~small numbers. 
Enable assertion for the sanity checks to work.
**********************************************/
public class RSAdemo2
{

	public static void main(String[] args)
	{
		final int CERTAINTY = 20;
		final int BIT_SIZE = 20;
		
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
		BigInteger ct2 = new BigInteger(
				"8701485697571629912108508730957703831688317541285382011555129355623048840582638"+
			    "5706604303724175236985573832006395540199066061101502996745421485579743246846982"+
			    "6363174405058850929567231994074036320411089130186716135085720028980086157008585"+
			    "79079601105011909417884801902333329415712320494308682279897714456370814");
		
		System.out.println("n = " + n);

		System.out.println("e = " + e);
		System.out.println("d = " + d);
		System.out.println("Our RSA bit size is: " + n.bitCount());
		
		BigInteger ct = ct2;
		System.out.println("ct\t" + ct);
		BigInteger bk = ct.modPow(d, n);
		System.out.println("bk\t" + bk);
		System.out.println("as a string\t" + new String(bk.toByteArray()).trim());
	}

}
