package signature;

import java.security.*;
import util.*;

public class HashDemo
{
	public static void main(String[] args) throws Exception
	{
		byte[] msg = "Hello there".getBytes();
		MessageDigest md = MessageDigest.getInstance("Sha1");
		byte[] digest = md.digest(msg);
		System.out.println(ByteWorks.byteToHex(digest));
		System.out.println(ByteWorks.byteToHex(digest).length());
	}
}