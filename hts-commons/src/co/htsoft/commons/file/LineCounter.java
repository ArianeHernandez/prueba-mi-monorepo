package co.htsoft.commons.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import co.htsoft.commons.array.ArrayUtils;
import co.htsoft.commons.lang.StringUtils;

public class LineCounter {

	public static class InfoCounter {
		String extension;
		Long lineas = 0l, caracteres = 0l, archivos = 0l;
	}

	public static List<InfoCounter> count(File path, boolean omitirBlancos, String[] extensiones) {

		List<InfoCounter> data = new ArrayList<>();
		count(path, omitirBlancos, data, ArrayUtils.toSet(extensiones));

		return data;
	}

	private static void count(File path, boolean omitirBlancos, List<InfoCounter> data, Set<String> extensiones) {

		if (!path.exists()) {
			return;
		}

		if (path.isDirectory()) {

			File[] files = path.listFiles();
			for (File file : files) {
				count(file, omitirBlancos, data, extensiones);
			}

		}

		if (path.isFile()) {

			String extension = "undefined";
			try {
				extension = path.getName().substring(path.getName().lastIndexOf(".") + 1).toLowerCase();
			} catch (Exception e) {
			}

			if (extensiones == null || extensiones.contains(extension)) {

				InfoCounter ic = null;
				for (InfoCounter infoCounter : data) {
					if (infoCounter.extension.equals(extension)) {
						ic = infoCounter;
					}
				}

				if (ic == null) {
					ic = new InfoCounter();
					ic.extension = extension;
					data.add(ic);
				}

				ic.archivos++;

				try {
					List<String> lines = FileUtils.readLines(path);

					for (String line : lines) {
						if (StringUtils.trimToNull(line) != null) {
							ic.lineas++;
							ic.caracteres += StringUtils.trimToNull(line).length();
						}
					}

				} catch (Exception e) {
				}
			}
		}

	}

}
