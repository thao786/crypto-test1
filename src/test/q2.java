package test;
import java.math.BigInteger;

public class q2 {
	
	public static BigInteger factorial(int n){
		BigInteger res = new BigInteger(String.valueOf(n));
		for (int i=1; i< n; i++){
			res = res.multiply(new BigInteger(String.valueOf(i)));
		}
		
		System.out.println("factorial of " +n + "    " + res);
		return res;
	}

	public static void main(String[] args) {
//		 int year = 10000;
//		 BigInteger big_year = new BigInteger(String.valueOf(year));
//		 
//		 int n = 100;
//		 BigInteger big_n = new BigInteger(String.valueOf(n));
//		 
//		 BigInteger top = q2.factorial(year).multiply(new BigInteger("100000000"));
//		 BigInteger bot_fac = q2.factorial(year - n);
//		 BigInteger bot = bot_fac.multiply(big_year.pow(n));
//		 
//		 //BigInteger left = top.divide(bot);
//		 BigInteger left = top.divideAndRemainder(bot)[0];
//		 
//		 BigInteger prob = new BigInteger("100000000").subtract(left);
//		 
//		 
//		 System.out.println("left  " + left);
//		 System.out.println("prob  " + prob);
		
		
		System.out.println((new BigInteger("5")).modPow(new BigInteger("3508"), new BigInteger("6543")));
		 		 
	}

}
