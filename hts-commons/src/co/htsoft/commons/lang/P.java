package co.htsoft.commons.lang;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class P {

	public static void println(Object d) {

		System.out.println(toStringln(d));
	}

	public static void print(Object d) {
		System.out.print(toString(d));
	}

	public static void println(Object... d) {

		System.out.println(toStringln(d));
	}

	public static void print(Object... d) {
		System.out.print(toString(d));
	}
	
	public static String toStringln(Object d) {
		
		try {
			if (d == null) {
				return "null";
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(d);
			return json;
			
		} catch (Exception e) {
			return d.toString();
		}
	}
	
	public static String toString(Object d) {
		
		try {
			if (d == null) {
				return "null";
			}
			Gson gson = new Gson();
			String json = gson.toJson(d);
			return json;
			
		} catch (Exception e) {
			return d.toString();
		}
	}
	
}
