//Group members: Zhong Yi, Clement, Leyond

package Util;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;

public class Hash {
	//https://www.owasp.org/index.php/Hashing_Java
	public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
			SecretKey key = skf.generateSecret(spec);
			byte[] res = key.getEncoded();

			return res;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Random salt
	//https://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html
	public static byte[] getSalt() {
		try {
			SecureRandom sr = new SecureRandom();
			byte[] salt = new byte[16];
			sr.nextBytes(salt);

			return salt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
