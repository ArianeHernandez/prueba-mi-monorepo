package com.osmosyscol.commons.utils.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;

import javax.crypto.Cipher;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;
import sun.security.rsa.SunRsaSign;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.sun.crypto.provider.SunJCE;

public class CoreRSA {

	private static int keysize = 1024;

	public static final ClaveCoreRSA clave;

	private static final Provider cipherProvider = new SunJCE();
	private static final Provider keyPairGeneratorProvider = new SunRsaSign();

	static {

		clave = new ClaveCoreRSA(
				"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAK0HUfysJ1wBkFnPOKZO6Yj4HDeLAujGYeWqIxjqy34KO8/dOciCF4jhSUHHQRBYQyESkmOP2ydEQDyNrX+VwMXkouccIActrsHWFBPumIjDZH0fliuu2yVcapzdfIq3DzLBBZDTm7wlAq7fcGAvvzuRWgyaFSXZEWto2mrQbbGdAgMBAAECgYEAoknUZ1adEBnTZ7kJ3Er1leeIp7v1vZicp8ndY3iN5qj4kq1fjiYept8PU+fsqcTvKaz+qu150ymbym/kRF+UTzbZG0ne/K/W3MeVPaUZ/v1E0YYLklORMipn03ZibxTZ+MuZjUI3aKZso91UhrnF8qmevHg5wh/D0GyZ/Y9ZiuECQQD9UTr99X44wEUZjIMB/DGG1SdIrFWLxdvK6bXEmV1nNs3bz78OUfqgENT4gJy+kQJyuhkIDrPaIgJM4JHh1pdlAkEArtxrNPd8ZZFbmJeevcamS0IH72fXlOTYPX0yWjx1Og0aouggpInNH3Q0gmt+CC51bSHF69k3Fo/SUxXyKhyZ2QJBAPygG+LVBrH2HXNjS+uY5JOA05KIOzAPfCiadqnAAQREBXyna/QOEZG794h0St1JpFVh/kCo2Pg7XmvosdO1vHkCQBxE0CQg+oBlDnzNzkD583ppM2U10Xllwbu3GMPT0FPROIQrqNNQ39N88tw+yoJCGIPLGPc3B5vjJmp+OscqFokCQQD2ivjBxeLgc78hBxZzF0Us1PeZ+WaJIVkCrKKGG692sBq/UlHg3mDujB6QsZ5zaDBBmjVNNdHwphSZPi6WA3eO");

	}

	private CoreRSA() {
	}

	// -----

	public static ClaveCoreRSA generaClaves() {
		return generaClaves(keysize);
	}

	// -----

	public static ClaveCoreRSA generaClaves(int keysize) {

		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", keyPairGeneratorProvider);
			keyGen.initialize(keysize);
			KeyPair keypair = keyGen.genKeyPair();

			RSAPrivateCrtKeyImpl privatekey = (RSAPrivateCrtKeyImpl) keypair.getPrivate();
			RSAPublicKeyImpl publicKey = (RSAPublicKeyImpl) keypair.getPublic();

			return new ClaveCoreRSA(privatekey, publicKey);

		} catch (Exception e) {
			SimpleLogger.setError("Error al generar Llaves RSA", e);
		}

		return null;
	}

	public static String cifrar(String mensaje, ClaveCoreRSA clave) {

		if (StringUtils.isBlank(mensaje)) {
			return null;
		}

		StringBuffer sb = new StringBuffer();

		try {

			PublicKey pubk = clave.getPublicKey();

			Cipher cipher = Cipher.getInstance("RSA", cipherProvider);

			cipher.init(Cipher.ENCRYPT_MODE, pubk);

			String r = null;

			do {
				int blockSize = 110;

				if (mensaje.length() > blockSize) {
					r = mensaje.substring(blockSize);
					mensaje = mensaje.substring(0, blockSize);
				}

				byte[] encBytes = cipher.doFinal(mensaje.getBytes());

				sb.append(StringUtils.encodeBase64(encBytes));

				mensaje = r;
				r = null;

			} while (mensaje != null);

			return sb.toString();

		} catch (Exception e) {
			SimpleLogger.setError("Error al cifrar", e);
		}

		return null;
	}

	public static String decifrar(String mensaje, ClaveCoreRSA clave) {

		if (StringUtils.isBlank(mensaje)) {
			return null;
		}

		try {

			StringBuffer sb = new StringBuffer();

			PrivateKey prik = clave.getPrivatekey();

			Cipher cipher = Cipher.getInstance("RSA", cipherProvider);
			cipher.init(Cipher.DECRYPT_MODE, prik);

			String[] bloqueMensaje = mensaje.split("[=]");

			for (int i = 0; i < bloqueMensaje.length; i++) {
				byte[] decBytes = cipher.doFinal(StringUtils.decodeBase64(bloqueMensaje[i] + "="));
				sb.append(new String(decBytes));
			}

			return sb.toString();

		} catch (Exception e) {
			SimpleLogger.setError("Error al decifrar", e);
		}

		return null;

	}

}