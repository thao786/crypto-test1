package util;

public class ByteWorks
{

	public static String byteToHex(byte[] ar)
	{
		assert ar != null;
		String result = "";
		for (int i = 0; i < ar.length; i++)
		{
			int x = ar[i] & 0x000000FF;
			String tmp = Integer.toHexString(x);
			if (x < 16) tmp = "0" + tmp;
			result += tmp;
		}
		return result.toUpperCase();
	}
	
	public static byte[] hexToByte(String hex)
	{
		assert hex != null && hex.length() % 2 == 0;
		byte[] result = new byte[hex.length() / 2];
		for (int i = 0; i < hex.length(); i = i + 2)
		{
			result[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
		}
		return result;
	}
	
	public static String bytesToBin(byte[] data){
		int n = data.length;
		StringBuffer s = new StringBuffer("");
		
		for (int i=0; i<n; i++){
			StringBuffer cur = new StringBuffer(Integer.toBinaryString(data[i]));
		//	System.out.println(cur.length());
			
			if (cur.length() < 8){
				//pad cur to get enuf 8 bits
				StringBuffer pad = new StringBuffer("");
				for (int j=0; j<(8 - cur.length()); j++){
					pad.append("0");
				}
				cur = pad.append(cur);
			}
			
			if (cur.length() > 8){
				cur = new StringBuffer(cur.substring(cur.length()-9, cur.length()-1));
			}
			
			s.append(cur);
		}
		
		return s.toString();
	}
	
	//String better be divisible by 8
	public static byte[] stringToByte(String data){
		int length = data.length()/8;
		byte[] bytes = new byte[length];
		
		//read bytes left to right
		for (int i=0; i < length; i++){
			String byte_str = data.substring(i * 8, i*8 + 8);
			
			byte cur = (byte) Integer.parseInt(byte_str, 2);
			bytes[i] = cur;
			
			System.out.println(byte_str);
			System.out.println(Integer.toBinaryString(cur));
			System.out.println();
			System.out.println();
		}
		
		return bytes;
	}
	
	public static void main(String[] args) throws Exception{		
		byte cur = (byte) Integer.parseInt("11010101", 2);
		String w = "1100011001100001011000110110010101100010011011110110111001101011";
		
		byte[] r = stringToByte(w);
		System.out.println();
		
		if (w.equals(bytesToBin(r))) System.out.println("equal");
		else {
			System.out.println(bytesToBin(r).length());
		}
		
		
	}
}
