import java.math.BigDecimal;

import com.osmosyscol.commons.crypto.Crypto;

public class Encriptar {
	public static void main(String[] args) throws Throwable {

		BigDecimal a = new BigDecimal("1");

		System.out.println(a.toString().length());
		String e = Crypto.E(a);
		System.out.println(e);
		System.out.println(e.length());

		System.out.println( Crypto.D(e) );

	}

}
