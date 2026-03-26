package co.htsoft.commons.rsa;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

public class ClaveCoreRSA {

	RSAPrivateCrtKeyImpl privatekey;
	RSAPublicKeyImpl publicKey;

	public ClaveCoreRSA(String privateEncoded) {
		try {
			privatekey = (RSAPrivateCrtKeyImpl) RSAPrivateCrtKeyImpl.newKey(CoreRSA.decodeBase64(privateEncoded));
			publicKey = new RSAPublicKeyImpl(privatekey.getModulus(), privatekey.getPublicExponent());
		} catch (Exception e) {

			try {
				privatekey = null;
				publicKey = new RSAPublicKeyImpl(CoreRSA.decodeBase64(privateEncoded));

			} catch (Exception e2) {
				e.printStackTrace();
			}

		}
	}

	public ClaveCoreRSA(String modulus, String exp) {

		try {
			BigInteger m = new BigInteger(modulus, 16);
			BigInteger e = new BigInteger(exp, 16);

			privatekey = null;
			publicKey = new RSAPublicKeyImpl(m, e);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ClaveCoreRSA(RSAPrivateCrtKeyImpl privatekey, RSAPublicKeyImpl publicKey) {
		this.privatekey = privatekey;
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(RSAPrivateCrtKeyImpl privatekey) {
		this.privatekey = privatekey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(RSAPublicKeyImpl publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateEncoded() {
		return CoreRSA.encodeBase64(privatekey.getEncoded());
	}

	public String getPublicEncoded() {
		return CoreRSA.encodeBase64(publicKey.getEncoded());
	}

	public BigInteger getModulus() {
		return publicKey.getModulus();
	}

	public BigInteger getPublicExponent() {
		return publicKey.getPublicExponent();
	}

}
