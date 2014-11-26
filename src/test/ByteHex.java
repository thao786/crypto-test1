package test;

import util.ByteWorks;

// Test the string-byte array - hex string utilities of ByteWorks
public class ByteHex
{

	public static void main(String[] args)
	{
		String pt = "CSE3481 \u0003";
		byte[] bar = pt.getBytes();
		for (byte b : bar) System.out.print(b + " ");
		System.out.println();
		String hex = ByteWorks.byteToHex(bar);
		System.out.println(hex);
		byte[] back = ByteWorks.hexToByte(hex);
		System.out.println(new String(back));
		
		
		
		System.out.println(ByteWorks.bytesToBin("Z1refza".getBytes()));
	}
}
