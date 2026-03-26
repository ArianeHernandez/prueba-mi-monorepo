package co.htsoft.compressor;

import java.io.File;

public class Test {

	public static void main(String[] args) throws Throwable {

		MainCompressor.comprimirJavascript(new File("demo/demo.js"), new File("demo/demo.cp.js"));

	}

}
