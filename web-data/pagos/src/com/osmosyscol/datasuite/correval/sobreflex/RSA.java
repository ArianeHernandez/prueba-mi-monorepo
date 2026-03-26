package com.osmosyscol.datasuite.correval.sobreflex;

import java.math.BigInteger;
import java.util.Properties;
import java.util.Random;

//import com.osmosyscol.commons.log.SimpleLogger;
//import com.osmosyscol.commons.utils.StringUtils;

public class RSA {

	private static int tamPrimo = 128;
	
	private static int AUMENTO_NEG = 2000;

	public static final ClaveRSA clave = new ClaveRSA(
			new BigInteger(
					"22299090945883006969587587599393949300224215349008783320229011803330101518575"),
			new BigInteger(
					"7095623373282220432552268950666537195879399738281605564534229203108392665007"),
			new BigInteger(
					"48164018092158859938774739011816620180701642930247717806805587063177894911887"));
	public static final ClaveRSA clave32 = new ClaveRSA(new BigInteger(
			"3363926259490249055"), new BigInteger("3418922220431034143"),
			new BigInteger("10264896168752348989"));

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
		return generaClaves(tamPrimo);
	}

	// -----

	public static ClaveRSA generaClaves(int tamPrimo) {

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
		} while ((e.compareTo(totient) != -1)
				|| (e.gcd(totient).compareTo(BigInteger.valueOf(1)) != 0));
		d = e.modInverse(totient);

		return new ClaveRSA(d, e, n);
	}

	// -----

	public static String encripta(String mensaje, ClaveRSA clave) {

		return encripta(mensaje, clave, System.currentTimeMillis());
	}

	public static String encripta(String mensaje, ClaveRSA clave, long code) {

		mensaje = "RSA:" + mensaje + ":" + code;

		int i;
		byte[] temp = new byte[1];
		byte[] digitos = mensaje.getBytes();
		BigInteger[] bigdigitos = new BigInteger[digitos.length];

		for (i = 0; i < bigdigitos.length; i++) {
			temp[0] = digitos[i];

			int n = temp[0];
			if (n < 0) {
				n = n + AUMENTO_NEG;
			}

			bigdigitos[i] = new BigInteger(n + "");
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
				desencriptado[i] = encriptado[i].modPow(clave.getD(), clave
						.getN());

			byte[] charArray = new byte[desencriptado.length];

			for (int i = 0; i < charArray.length; i++) {
				int d = desencriptado[i].intValue();

				if (d > AUMENTO_NEG / 2) {
					d -= AUMENTO_NEG;
				}

				charArray[i] = (byte) d;
			}

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

	public static void main(String[] args) {

//		String data = encripta(StringUtils.randomString(7), clave32, 0);
//
//		SimpleLogger.setDebug(data);
//		SimpleLogger.setDebug("" + data.length());
//
//		SimpleLogger.setDebug(desencripta(data, clave32));
	}

}