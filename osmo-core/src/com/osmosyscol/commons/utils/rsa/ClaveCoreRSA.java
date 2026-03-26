package com.osmosyscol.commons.utils.rsa;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;

public class ClaveCoreRSA {

	RSAPrivateCrtKeyImpl privatekey;
	RSAPublicKeyImpl publicKey;

	public ClaveCoreRSA(String privateEncoded) {
		try {
			privatekey = (RSAPrivateCrtKeyImpl) RSAPrivateCrtKeyImpl.newKey(StringUtils.decodeBase64(privateEncoded));
			publicKey = new RSAPublicKeyImpl(privatekey.getModulus(), privatekey.getPublicExponent());
		} catch (Exception e) {
			SimpleLogger.setError("Error generando ClaveCoreRSA", e);
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
		return StringUtils.encodeBase64(privatekey.getEncoded());
	}

	public String getPublicEncoded() {
		return StringUtils.encodeBase64(publicKey.getEncoded());
	}

	public BigInteger getModulus() {
		return publicKey.getModulus();
	}

	public BigInteger getPublicExponent() {
		return publicKey.getPublicExponent();
	}

}
