package gepro.mobile.util;

public class Util {

	public static boolean IsNullOrEmpty(String str) {
		if (str == null)
			return true;

		if (str.length() <= 0)
			return true;

		return false;
	}

	static public byte[] convertCharArrayToByteArray(char[] ca) {
		byte[] ba = new byte[ca.length * 2];
		java.nio.ByteBuffer.wrap(ba).asCharBuffer().put(ca);
		return ba;
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", new Object[]{  s });  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$#" + n + "s", new Object[]{  s } );  
	}
}
