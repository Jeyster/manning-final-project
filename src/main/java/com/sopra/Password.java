package com.sopra;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Password {

	public byte[] getSalt() {
		try {
			SecureRandom sr;
			sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	// POUR AVOIR UN CODE EN HEXADECIMAL
	public String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public byte[] generateStorngPasswordHash(String password, User user) {
		try {
			int iterations = 1000;
			char[] chars = password.toCharArray();
			byte[] salt = user.getSalt();

			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
			SecretKeyFactory skf;
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			return hash;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected static SecureRandom random = new SecureRandom();

	public String generateToken(User user, String login) {

		long longToken = Math.abs(random.nextLong());
		String random = Long.toString(longToken, 16);
		return (random);
	}

}
