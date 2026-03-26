import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.lang.P;
import co.htsoft.commons.lang.StringUtils;

public class TestReplaceRegex {
	

	public static void reemplazoMasivo(String rootPath, final String ext,
			String regex, String reemplazar) {
		try {
			File root = new File(rootPath);
			File[] directories = root.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					return (pathname.getName().toLowerCase().endsWith(ext)
							|| pathname.isDirectory()) && !(pathname.getName().equals("jsonrpc.js") || pathname.getName().equals("jsonrpc2.js")) ;
				}
			});
			for (File file : directories) {
				if (file.isDirectory()) {
					reemplazoMasivo(file.getPath(), ext, regex, reemplazar);
				} else if (file.isFile()) {
					List<String> text = FileUtils.readLines(file);
					List<String> textToWrite = new ArrayList<String>();
					for (String string : text) {
						if (string.matches(regex)) {
							String method = string.split("jsonrpc[A-Za-z0-9_]*")[1].split("[\\)\\(]")[0];
							String toReplace = "._(\"" + method.substring(1) + "\")";
						    textToWrite.add(string.replace(method, toReplace));
						    
						}else{
							textToWrite.add(string);
						}
					}
					if (!text.containsAll(textToWrite)){
						FileUtils.writeLines(file, textToWrite);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String ruta = "C:\\Users\\HTS0-47\\workspace\\CPSUITE BTG\\CentralRecaudos";
		reemplazoMasivo(ruta, ".js", ".*jsonrpc[A-Za-z0-9_]*\\..*", "");


	}
}
