package test;

public class affine {
	
	//letters - 65 to get to 0
	public static void encrypt(String data, int a, int b){
		for (int i=0; i<data.length(); i++){
			int cur = data.charAt(i) -65;
		//	System.out.println(cur);
			
			int c = (a * cur + b) % 26;
			System.out.print((char)(c + 65));
		}
	}
	
	public static void decryptCea(String data, int shift){
		for (int i=0; i<data.length(); i++){
			int cur = data.charAt(i) - 65;
			
			int pt = cur - 17;
			if (pt < 0) pt = pt+26;
			
			System.out.print((char)(pt + 65));
		}
	}

	public static void main(String[] args) {
	//	System.out.println('A'- 65);
		
//		for (int i=0; i<26; i++){
//			if (i * 3 % 26 == 1) 
//				System.out.println(i);
//		}
//		
//		encrypt("AMAZING", 9, 18);
		
		decryptCea("SVKKVICRKVKYREEVMVI", 17);
	}
}
