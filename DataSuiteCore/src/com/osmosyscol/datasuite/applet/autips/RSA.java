package com.osmosyscol.datasuite.applet.autips;

import java.math.BigInteger;
import java.util.Properties;
import java.util.Random;

public class RSA {

	private static int tamPrimo = 128;

	public static final ClaveRSA clave = new ClaveRSA(new BigInteger("22299090945883006969587587599393949300224215349008783320229011803330101518575"), new BigInteger("7095623373282220432552268950666537195879399738281605564534229203108392665007"), new BigInteger("48164018092158859938774739011816620180701642930247717806805587063177894911887"));

	private RSA() {
	}

	// -----

	public static String getPropertyValue(String data) {

		if (data != null && data.indexOf("CRYPTO://") == 0) {
			return RSA.desencripta(data.substring(9), RSA.clave);
		}

		return data;
	}

	public static String getPropertyValue(Properties properties, String key) {

		String data = properties.getProperty(key);
		if (data != null && data.indexOf("CRYPTO://") == 0) {
			return RSA.desencripta(data.substring(9), RSA.clave);
		}

		return data;
	}

	// -----

	public static ClaveRSA generaClaves() {

		BigInteger p = new BigInteger(tamPrimo, 10, new Random());
		BigInteger q;
		do {
			q = new BigInteger(tamPrimo, 10, new Random());
		} while (q.compareTo(p) == 0);

		BigInteger n = p.multiply(q);

		BigInteger totient = p.subtract(BigInteger.valueOf(1));
		totient = totient.multiply(q.subtract(BigInteger.valueOf(1)));

		BigInteger e, d;
		do {
			e = new BigInteger(2 * tamPrimo, new Random());
		} while ((e.compareTo(totient) != -1) || (e.gcd(totient).compareTo(BigInteger.valueOf(1)) != 0));
		d = e.modInverse(totient);

		return new ClaveRSA(d, e, n);
	}

	// -----

	public static String encripta(String mensaje, ClaveRSA clave) {

		mensaje = "RSA:" + mensaje + ":" + System.currentTimeMillis();

		int i;
		byte[] temp = new byte[1];
		byte[] digitos = mensaje.getBytes();
		BigInteger[] bigdigitos = new BigInteger[digitos.length];

		for (i = 0; i < bigdigitos.length; i++) {
			temp[0] = digitos[i];
			bigdigitos[i] = new BigInteger(temp);
		}

		BigInteger[] encriptado = new BigInteger[bigdigitos.length];

		for (i = 0; i < bigdigitos.length; i++) {
			encriptado[i] = bigdigitos[i].modPow(clave.getE(), clave.getN());
		}

		String salida = "";
		for (BigInteger bigInteger : encriptado) {
			salida = bigInteger.toString(22) + "z" + salida;
		}

		return salida.substring(0, salida.length() - 1).toUpperCase();
	}

	// -----

	public static String desencripta(String mensaje, ClaveRSA clave) {

		try {
			String str[] = mensaje.toLowerCase().split("[z]");

			BigInteger encriptado[] = new BigInteger[str.length];

			for (int i = 0; i < str.length; i++) {
				encriptado[i] = new BigInteger(str[str.length - i - 1], 22);
			}

			BigInteger[] desencriptado = new BigInteger[encriptado.length];

			for (int i = 0; i < desencriptado.length; i++)
				desencriptado[i] = encriptado[i].modPow(clave.getD(), clave.getN());

			char[] charArray = new char[desencriptado.length];

			for (int i = 0; i < charArray.length; i++)
				charArray[i] = (char) (desencriptado[i].intValue());

			String desc = (new String(charArray));

			if (desc.indexOf("RSA:") != 0) {
				return null;
			}

			return desc.substring(4, desc.lastIndexOf(":"));

		} catch (Exception e) {
			return null;
		}

	}

	// -----

}