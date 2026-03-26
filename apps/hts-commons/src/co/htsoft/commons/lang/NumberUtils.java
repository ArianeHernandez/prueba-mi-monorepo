package co.htsoft.commons.lang;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {

	public static boolean equals(Integer a, Integer b) {

		if (a == null && b == null) {
			return true;
		}

		if (a == null || b == null) {
			return false;
		}

		return a.intValue() == b.intValue();

	}

}
