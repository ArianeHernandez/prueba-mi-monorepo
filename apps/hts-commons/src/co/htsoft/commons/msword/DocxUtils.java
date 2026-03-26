package co.htsoft.commons.msword;

import java.io.File;

import co.htsoft.commons.file.FileUtils;
import co.htsoft.commons.zip.ZipUtil;

public class DocxUtils {

	public static File getXmlDocumentFromDocx(File docxFile) {

		File folder = FileUtils.newFile();
		ZipUtil.extractZip(docxFile.getAbsolutePath(), folder.getAbsolutePath());
		File srcFile = new File(folder.getAbsoluteFile() + "/word/document.xml");
		File destFile = FileUtils.newFile();

		try {
			FileUtils.copyFile(srcFile, destFile);
			FileUtils.deleteDirectory(folder);
			return destFile;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Boolean setXmlDocumentFromDocx(File docxFile, File xmlFile) {

		File folder = FileUtils.newFile();
		ZipUtil.extractZip(docxFile.getAbsolutePath(), folder.getAbsolutePath());
		File destFile = new File(folder.getAbsoluteFile() + "/word/document.xml");

		destFile.delete();

		try {
			FileUtils.copyFile(xmlFile, destFile);
			docxFile.delete();
			ZipUtil.createZip(folder.getAbsolutePath(), docxFile.getAbsolutePath());

			FileUtils.deleteDirectory(folder);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
