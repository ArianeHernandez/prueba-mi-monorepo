package co.htsoft.compressor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class P {

	public static void println(Object d) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(d);
		System.out.println(json);

	}

	public static void print(Object d) {
		String json = new Gson().toJson(d);
		System.out.print(json);
	}

	public static void println(Object... d) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String json = gson.toJson(d);
		System.out.println(json);

	}

	public static void print(Object... d) {
		String json = new Gson().toJson(d);
		System.out.print(json);
	}

}
