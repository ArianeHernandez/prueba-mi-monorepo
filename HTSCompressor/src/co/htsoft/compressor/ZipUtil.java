package co.htsoft.compressor;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class ZipUtil {

	public static void createZip(String directoryPath, String zipPath) throws IOException {
		
		System.out.println("ZIP: " + directoryPath + " --> " + zipPath);
		
		FileOutputStream fOut = null;
		BufferedOutputStream bOut = null;
		ZipArchiveOutputStream tOut = null;

		try {
			fOut = new FileOutputStream(new File(zipPath));
			bOut = new BufferedOutputStream(fOut);
			tOut = new ZipArchiveOutputStream(bOut);

			File f = new File(directoryPath);

			if (f.exists()) {
				if (f.isDirectory()) {

					File[] files = f.listFiles();

					for (File file : files) {
						addFileToZip(tOut, file.getAbsolutePath(), "");
					}

				} else if (f.isFile()) {
					addFileToZip(tOut, directoryPath, "");
				}
			} else {
				throw new RuntimeException("File no found: " + f.getAbsolutePath());
			}

		} finally {
			tOut.finish();
			tOut.close();
			bOut.close();
			fOut.close();
		}

	}

	private static void addFileToZip(ZipArchiveOutputStream zOut, String path, String base) throws IOException {
		File f = new File(path);
		String entryName = base + f.getName();
		ZipArchiveEntry zipEntry = new ZipArchiveEntry(f, entryName);

		zOut.putArchiveEntry(zipEntry);

		if (f.isFile()) {
			FileInputStream fInputStream = null;
			try {
				fInputStream = new FileInputStream(f);
				IOUtils.copy(fInputStream, zOut);
				zOut.closeArchiveEntry();
			} finally {
				IOUtils.closeQuietly(fInputStream);
			}

		} else {
			zOut.closeArchiveEntry();
			File[] children = f.listFiles();

			if (children != null) {
				for (File child : children) {
					addFileToZip(zOut, child.getAbsolutePath(), entryName + "/");
				}
			}
		}
	}

	public static void extractZip(String archivePath, String destinationPath) {
		File archiveFile = new File(archivePath);
		File unzipDestFolder = null;

		try {
			unzipDestFolder = new File(destinationPath);
			unzipFolder(archiveFile, archiveFile.length(), unzipDestFolder);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean unzipFolder(File archiveFile, long compressedSize, File zipDestinationFolder) {

		zipDestinationFolder.mkdirs();

		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(archiveFile);
			byte[] buf = new byte[65536];

			Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();

			while (entries.hasMoreElements()) {
				ZipArchiveEntry zipEntry = entries.nextElement();
				String name = zipEntry.getName();

				name = name.replace('\\', '/');

				File destFile = new File(zipDestinationFolder.getAbsolutePath() + "/" + name);

				if (name.endsWith("/")) {
					destFile.mkdirs();
				} else {

					// Create the the parent directory if it doesn't exist
					destFile.getParentFile().mkdirs();

					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(destFile);
						int n;
						InputStream entryContent = zipFile.getInputStream(zipEntry);
						while ((n = entryContent.read(buf)) != -1) {
							if (n > 0) {
								fos.write(buf, 0, n);
							}
						}
					} finally {
						if (fos != null) {
							fos.close();
						}
					}
				}
			}

			return true;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

}