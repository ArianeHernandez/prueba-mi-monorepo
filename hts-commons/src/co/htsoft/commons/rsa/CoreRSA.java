package co.htsoft.commons.rsa;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;

import javax.crypto.Cipher;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;
import sun.security.rsa.SunRsaSign;

import com.sun.crypto.provider.SunJCE;

public class CoreRSA {

	private static int keysize = 1024;

	public static ClaveCoreRSA clave;

	private static final Provider cipherProvider = new SunJCE();
	private static final Provider keyPairGeneratorProvider = new SunRsaSign();

	static {
		clave = new ClaveCoreRSA("MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMKj6gPSioBJtcrRheqJ+XUH0D91/ycpD/8K6DKKM/JOpZVIyooOB9Ae5XZ5WJb1y0VjPJNMOS8VyoPCuhTvB6ItJpdBj087j+3TPnhZY+nwHqKRJTSkGWZ1SbHPVUmdaSmF4aeS8tMB3gyLi+mubwnmbcHDTQTUn899WzZu9hgDAgMBAAECgYEAnGAuygVkKu+Nc7ZkHeveFG1DnUjCxwaRiHmN1dT9KSaQl+CUgNgG5Ol9t3KEfzxYpJW2/CyYIMEkJDnVYK4t9XIcS8MOZkf5dhltx5RmlHVir4t8fHXIKLoPow6bKifXlRGdC1W8r7CKkZJJtIf1royfDYBJGN81ub2meMhlASECQQDfiO8hoI4q+CIY6dakB/TS3CmywAAtiL8YndghXJXqFByB4vpvaohFpwrUeZf9hP/Hefb2IBQ46nrowDFSuSwTAkEA3uisaUy8QmLblprkh/svOu6PEWDaiyrTcyHOpr2XNYOdZu+4k0ktC+5qin7FsNhZWdaTYevbKQ3ZPSk7Yr0CUQJBAKYFiy4blFu/rXt5ecyciC5wDJxRVBB9pwi+ez7UCBgx1bZ2gXBs3Ed3OGj04nkN5fce09ibnMllBeKGlFuPYncCQQCRmRrcoNGq7xWNVtkE9Q3RiXcLm97D3hJFdoSbpuS4gKJ0VQqTsc1pqK4QjmOkcnxSUPTtlfgN5czKxT7T7K8xAkBKQ3tS1iAu16Eq4Fkq4q5luis9DciE9ANAP8r23iM+rQVJGRYT7qtlJBysMXwJsLuqpZ41WD77YUJECMveJvGd");
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
			e.printStackTrace();
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

				sb.append(encodeBase64(encBytes));

				mensaje = r;
				r = null;

			} while (mensaje != null);

			return sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
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

			String c = "";
			for (int i = 0; i < bloqueMensaje.length; i++) {
				c = c + "=";

				if (!StringUtils.isBlank(bloqueMensaje[i])) {
					byte[] decBytes = cipher.doFinal(decodeBase64(bloqueMensaje[i] + c));
					sb.append(new String(decBytes));
					c = "";
				}

			}

			return sb.toString();

		} catch (Exception e) {
		}

		return null;
	}

	public static byte[] decodeBase64(String str) {
		if (str == null) {
			return new byte[0];
		}

		BASE64Decoder decoder = new BASE64Decoder();

		try {
			return decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encodeBase64(byte[] decode) {

		if (decode == null) {
			return null;
		}

		BASE64Encoder encoder = new BASE64Encoder();

		try {
			return encoder.encodeBuffer(decode).replace("\r\n", "").replace("\n", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertHexToString(String hex) {
		try {
			StringBuilder sb = new StringBuilder();
			StringBuilder temp = new StringBuilder();

			// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
			for (int i = 0; i < hex.length() - 1; i += 2) {

				// grab the hex in pairs
				String output = hex.substring(i, (i + 2));
				// convert hex to decimal
				int decimal = Integer.parseInt(output, 16);
				// convert the decimal to character
				sb.append((char) decimal);

				temp.append(decimal);
			}

			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

}