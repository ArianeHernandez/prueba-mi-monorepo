import java.io.File;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.zip.ZipUtil;

public class ZipTest {

	public static void main(String[] args) {

		File f = FileUtils.newFile("zip");

		ZipUtil.createZip("D:/TEMP/cosas", f.getAbsolutePath());

		System.err.println(f.getAbsolutePath());

	}
}
