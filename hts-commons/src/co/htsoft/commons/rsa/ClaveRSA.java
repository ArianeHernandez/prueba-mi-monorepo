package co.htsoft.commons.rsa;

import java.math.BigInteger;

public class ClaveRSA {

	private BigInteger d;
	private BigInteger e;
	private BigInteger n;

	public ClaveRSA(BigInteger d, BigInteger e, BigInteger n) {
		this.d = d;
		this.e = e;
		this.n = n;
	}

	public BigInteger getD() {
		return d;
	}

	public void setD(BigInteger d) {
		this.d = d;
	}

	public BigInteger getE() {
		return e;
	}

	public void setE(BigInteger e) {
		this.e = e;
	}

	public BigInteger getN() {
		return n;
	}

	public void setN(BigInteger n) {
		this.n = n;
	}

}
