package Util;

import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.codec.binary.Base64;

public class util {
	public static byte[] decodeBase64(String s) {
		byte[] decode = null;
		
		try {
			decode = Base64.decodeBase64(s.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		return decode;
	}
	
	public static String encodeBase64(byte[] bytes) {
		String encoded = new String(Base64.encodeBase64URLSafe(bytes),StandardCharsets.UTF_8);
        return encoded;
	}

	public static String hash(String s, byte[] salt) {
		String hash = asHex(Hash.hashPassword(s.toCharArray(), salt, 1000, 256));
		return hash;
	}

	//Convert from byte array to hex
	public static String asHex(byte buf[]) {
		// Obtain a StringBuffer object
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		// Return result string in Hexadecimal format
		return strbuf.toString();
	}

	//Convert from hex to byte array
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}
}
