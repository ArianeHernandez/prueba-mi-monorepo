// CVS $Id$
package com.osmosyscol.commons.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.mozilla.universalchardet.UniversalDetector;

import com.osmosyscol.commons.log.SimpleLogger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileUtils {

	// **************************************************************

	static public void setContentFile(String pathFile, String contents) {

		pathFile = pathFile.replace("\\", "/");
		try {
			new File(pathFile.substring(0, pathFile.lastIndexOf("/"))).mkdirs();
		} catch (Exception e) {
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(pathFile));
			out.write(contents);
			out.close();
		} catch (Exception e) {
			SimpleLogger.setError("No se puede escribir en el archivo [" + pathFile + "]", e);
		}
	}

	static public StringBuffer getContentFile(File aFile) {

		try {
			return new StringBuffer(org.apache.commons.io.FileUtils.readFileToString(aFile, getCharset(aFile) ));
		} catch (IOException e) {
			return null;
		}
	}

	static public StringBuffer getContentFile(String pathFile) {

		File aFile = new File(pathFile);

		return getContentFile(aFile);
	}

	static public List<String> getContentFileList(String pathFile) {

		try {
			File afile = new File(pathFile);
			return org.apache.commons.io.FileUtils.readLines(afile, getCharset(afile));
		} catch (IOException e) {
			return null;
		}
	}

	static public String[] getContentFileToArray(String pathFile) {

		StringBuffer contents = getContentFile(pathFile);
		return contents.toString().split("\n");
	}

	public static boolean copyURLToFile(URL in_url, File out_file) {

		try {
			InputStream in = in_url.openStream();

			OutputStream out = new FileOutputStream(out_file);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();

			return true;

		} catch (Throwable e) {
			SimpleLogger.setError("copyURLToFile: " + in_url + ", " + out_file, e);

		}

		return false;

	}

	public static Checksum checksum(File file, Checksum checksum) throws IOException {
		if (file.isDirectory()) {
			throw new IllegalArgumentException("Checksums can't be computed on directories");
		}
		InputStream in = null;
		try {
			in = new CheckedInputStream(new FileInputStream(file), checksum);
			IOUtils.copy(in, new NullOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
		return checksum;
	}

	public static long checksumCRC32(File file) throws IOException {
		CRC32 crc = new CRC32();
		checksum(file, crc);
		return crc.getValue();
	}

	/**
	 * Copy file <code>src</code> to <code>dst</code>. If <code>dst</code> file doesn't exists, it's created
	 * 
	 * @param src
	 *            source file
	 * @param dst
	 *            destination file
	 * @throws IOException
	 */
	public static void copy(File src, File dst) throws IOException {
		if (src.isDirectory() || dst.isDirectory()) {
			throw new IllegalArgumentException("Can't copy directories");
		}

		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public static byte[] decodeBase64(String str) {
		if (str == null) {
			return new byte[0];
		}

		BASE64Decoder decoder = new BASE64Decoder();

		try {
			return decoder.decodeBuffer(str);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encodeBase64(File file) {
		return encodeBase64(toBytes(file));
	}

	public static String encodeBase64(byte[] decode) {

		if (decode == null) {
			return null;
		}

		BASE64Encoder encoder = new BASE64Encoder();

		try {
			return encoder.encodeBuffer(decode).replace("\r\n", "").replace("\n", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] toBytes(File file) {

		if (!file.isFile() || !file.exists()) {
			return null;
		}

		byte[] bFile = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bFile;

	}

	public static File toFile(byte[] bytes, String strFile) {

		File file = new File(strFile);

		if (file.exists()) {
			file.delete();
		}

		if (bytes == null) {
			return null;
		}

		try {
			FileOutputStream fileOuputStream = new FileOutputStream(strFile);
			fileOuputStream.write(bytes);
			fileOuputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;

	}

	public static File newFile() {
		return newFile("tmp");
	}

	public static File newFile(String extension) {
		String fileName = System.getProperty("java.io.tmpdir") + "/T" + System.currentTimeMillis() + "_" + Thread.currentThread().getId() + "_" + Math.round(1000d * Math.random()) + "." + extension.toLowerCase();

		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}

		return file;
	}

	public static File toFile(String fileBase64) {

		byte[] bw = FileUtils.decodeBase64(fileBase64);

		File file = newFile();

		return FileUtils.toFile(bw, file.getAbsolutePath());
	}

	public static File toFile(String fileBase64, String filename) {

		byte[] bw = FileUtils.decodeBase64(fileBase64);

		File file = new File(filename);

		return FileUtils.toFile(bw, file.getAbsolutePath());
	}

	public static class FileType {

		private String name;
		private String mimetype;
		private String extension;

		public FileType() {
		}

		public FileType(String name, String mimetype, String extension) {
			this.name = name;
			this.mimetype = mimetype;
			this.extension = extension;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMimetype() {
			return mimetype;
		}

		public void setMimetype(String mimetype) {
			this.mimetype = mimetype;
		}

		public String getExtension() {
			return extension;
		}

		public void setExtension(String extension) {
			this.extension = extension;
		}

	}

	private static FileType[] types;

	static {
		List<FileType> ft = new ArrayList<>();

		ft.add(new FileType("3D Crossword Plugin", "application/vnd.hzn-3d-crossword", "x3d"));
		ft.add(new FileType("3GP", "video/3gpp", "3gp"));
		ft.add(new FileType("3GP2", "video/3gpp2", "3g2"));
		ft.add(new FileType("3GPP MSEQ File", "application/vnd.mseq", "mseq"));
		ft.add(new FileType("3M Post It Notes", "application/vnd.3m.post-it-notes", "pwn"));
		ft.add(new FileType("3rd Generation Partnership Project - Pic Large", "application/vnd.3gpp.pic-bw-large", "plb"));
		ft.add(new FileType("3rd Generation Partnership Project - Pic Small", "application/vnd.3gpp.pic-bw-small", "psb"));
		ft.add(new FileType("3rd Generation Partnership Project - Pic Var", "application/vnd.3gpp.pic-bw-var", "pvb"));
		ft.add(new FileType("3rd Generation Partnership Project - Transaction Capabilities Application Part", "application/vnd.3gpp2.tcap", "tcap"));
		ft.add(new FileType("7-Zip", "application/x-7z-compressed", "7z"));
		ft.add(new FileType("AbiWord", "application/x-abiword", "abw"));
		ft.add(new FileType("Ace Archive", "application/x-ace-compressed", "ace"));
		ft.add(new FileType("Active Content Compression", "application/vnd.americandynamics.acc", "acc"));
		ft.add(new FileType("ACU Cobol", "application/vnd.acucobol", "acu"));
		ft.add(new FileType("ACU Cobol", "application/vnd.acucorp", "atc"));
		ft.add(new FileType("Adaptive differential pulse-code modulation", "audio/adpcm", "adp"));
		ft.add(new FileType("Adobe (Macropedia) Authorware - Binary File", "application/x-authorware-bin", "aab"));
		ft.add(new FileType("Adobe (Macropedia) Authorware - Map", "application/x-authorware-map", "aam"));
		ft.add(new FileType("Adobe (Macropedia) Authorware - Segment File", "application/x-authorware-seg", "aas"));
		ft.add(new FileType("Adobe AIR Application", "application/vnd.adobe.air-application-installer-package+zip", "air"));
		ft.add(new FileType("Adobe Flash", "application/x-shockwave-flash", "swf"));
		ft.add(new FileType("Adobe Flex Project", "application/vnd.adobe.fxp", "fxp"));
		ft.add(new FileType("Adobe Portable Document Format", "application/pdf", "pdf"));
		ft.add(new FileType("Adobe PostScript Printer Description File Format", "application/vnd.cups-ppd", "ppd"));
		ft.add(new FileType("Adobe Shockwave Player", "application/x-director", "dir"));
		ft.add(new FileType("Adobe XML Data Package", "application/vnd.adobe.xdp+xml", "xdp"));
		ft.add(new FileType("Adobe XML Forms Data Format", "application/vnd.adobe.xfdf", "xfdf"));
		ft.add(new FileType("Advanced Audio Coding (AAC)", "audio/x-aac", "aac"));
		ft.add(new FileType("Ahead AIR Application", "application/vnd.ahead.space", "ahead"));
		ft.add(new FileType("AirZip FileSECURE", "application/vnd.airzip.filesecure.azf", "azf"));
		ft.add(new FileType("AirZip FileSECURE", "application/vnd.airzip.filesecure.azs", "azs"));
		ft.add(new FileType("Amazon Kindle eBook format", "application/vnd.amazon.ebook", "azw"));
		ft.add(new FileType("AmigaDE", "application/vnd.amiga.ami", "ami"));
		ft.add(new FileType("Andrew Toolkit", "application/andrew-inset", "N/A"));
		ft.add(new FileType("Android Package Archive", "application/vnd.android.package-archive", "apk"));
		ft.add(new FileType("ANSER-WEB Terminal Client - Certificate Issue", "application/vnd.anser-web-certificate-issue-initiation", "cii"));
		ft.add(new FileType("ANSER-WEB Terminal Client - Web Funds Transfer", "application/vnd.anser-web-funds-transfer-initiation", "fti"));
		ft.add(new FileType("Antix Game Player", "application/vnd.antix.game-component", "atx"));
		ft.add(new FileType("Apple Installer Package", "application/vnd.apple.installer+xml", "mpkg"));
		ft.add(new FileType("Applixware", "application/applixware", "aw"));
		ft.add(new FileType("Archipelago Lesson Player", "application/vnd.hhe.lesson-player", "les"));
		ft.add(new FileType("Arista Networks Software Image", "application/vnd.aristanetworks.swi", "swi"));
		ft.add(new FileType("Assembler Source File", "text/x-asm", "s"));
		ft.add(new FileType("Atom Publishing Protocol", "application/atomcat+xml", "atomcat"));
		ft.add(new FileType("Atom Publishing Protocol Service Document", "application/atomsvc+xml", "atomsvc"));
		ft.add(new FileType("Atom Syndication Format", "application/atom+xml", "atom"));
		ft.add(new FileType("Attribute Certificate", "application/pkix-attr-cert", "ac"));
		ft.add(new FileType("Audio Interchange File Format", "audio/x-aiff", "aif"));
		ft.add(new FileType("Audio Video Interleave (AVI)", "video/x-msvideo", "avi"));
		ft.add(new FileType("Audiograph", "application/vnd.audiograph", "aep"));
		ft.add(new FileType("AutoCAD DXF", "image/vnd.dxf", "dxf"));
		ft.add(new FileType("Autodesk Design Web Format (DWF)", "model/vnd.dwf", "dwf"));
		ft.add(new FileType("BAS Partitur Format", "text/plain-bas", "par"));
		ft.add(new FileType("Binary CPIO Archive", "application/x-bcpio", "bcpio"));
		ft.add(new FileType("Binary Data", "application/octet-stream", "bin"));
		ft.add(new FileType("Bitmap Image File", "image/bmp", "bmp"));
		ft.add(new FileType("BitTorrent", "application/x-bittorrent", "torrent"));
		ft.add(new FileType("Blackberry COD File", "application/vnd.rim.cod", "cod"));
		ft.add(new FileType("Blueice Research Multipass", "application/vnd.blueice.multipass", "mpm"));
		ft.add(new FileType("BMI Drawing Data Interchange", "application/vnd.bmi", "bmi"));
		ft.add(new FileType("Bourne Shell Script", "application/x-sh", "sh"));
		ft.add(new FileType("BTIF", "image/prs.btif", "btif"));
		ft.add(new FileType("BusinessObjects", "application/vnd.businessobjects", "rep"));
		ft.add(new FileType("Bzip Archive", "application/x-bzip", "bz"));
		ft.add(new FileType("Bzip2 Archive", "application/x-bzip2", "bz2"));
		ft.add(new FileType("C Shell Script", "application/x-csh", "csh"));
		ft.add(new FileType("C Source File", "text/x-c", "c"));
		ft.add(new FileType("CambridgeSoft Chem Draw", "application/vnd.chemdraw+xml", "cdxml"));
		ft.add(new FileType("Cascading Style Sheets (CSS)", "text/css", "css"));
		ft.add(new FileType("ChemDraw eXchange file", "chemical/x-cdx", "cdx"));
		ft.add(new FileType("Chemical Markup Language", "chemical/x-cml", "cml"));
		ft.add(new FileType("Chemical Style Markup Language", "chemical/x-csml", "csml"));
		ft.add(new FileType("CIM Database", "application/vnd.contact.cmsg", "cdbcmsg"));
		ft.add(new FileType("Claymore Data Files", "application/vnd.claymore", "cla"));
		ft.add(new FileType("Clonk Game", "application/vnd.clonk.c4group", "c4g"));
		ft.add(new FileType("Close Captioning - Subtitle", "image/vnd.dvb.subtitle", "sub"));
		ft.add(new FileType("Cloud Data Management Interface (CDMI) - Capability", "application/cdmi-capability", "cdmia"));
		ft.add(new FileType("Cloud Data Management Interface (CDMI) - Contaimer", "application/cdmi-container", "cdmic"));
		ft.add(new FileType("Cloud Data Management Interface (CDMI) - Domain", "application/cdmi-domain", "cdmid"));
		ft.add(new FileType("Cloud Data Management Interface (CDMI) - Object", "application/cdmi-object", "cdmio"));
		ft.add(new FileType("Cloud Data Management Interface (CDMI) - Queue", "application/cdmi-queue", "cdmiq"));
		ft.add(new FileType("ClueTrust CartoMobile - Config", "application/vnd.cluetrust.cartomobile-config", "c11amc"));
		ft.add(new FileType("ClueTrust CartoMobile - Config Package", "application/vnd.cluetrust.cartomobile-config-pkg", "c11amz"));
		ft.add(new FileType("CMU Image", "image/x-cmu-raster", "ras"));
		ft.add(new FileType("COLLADA", "model/vnd.collada+xml", "dae"));
		ft.add(new FileType("Comma-Seperated Values", "text/csv", "csv"));
		ft.add(new FileType("Compact Pro", "application/mac-compactpro", "cpt"));
		ft.add(new FileType("Compiled Wireless Markup Language (WMLC)", "application/vnd.wap.wmlc", "wmlc"));
		ft.add(new FileType("Computer Graphics Metafile", "image/cgm", "cgm"));
		ft.add(new FileType("CoolTalk", "x-conference/x-cooltalk", "ice"));
		ft.add(new FileType("Corel Metafile Exchange (CMX)", "image/x-cmx", "cmx"));
		ft.add(new FileType("CorelXARA", "application/vnd.xara", "xar"));
		ft.add(new FileType("CosmoCaller", "application/vnd.cosmocaller", "cmc"));
		ft.add(new FileType("CPIO Archive", "application/x-cpio", "cpio"));
		ft.add(new FileType("CrickSoftware - Clicker", "application/vnd.crick.clicker", "clkx"));
		ft.add(new FileType("CrickSoftware - Clicker - Keyboard", "application/vnd.crick.clicker.keyboard", "clkk"));
		ft.add(new FileType("CrickSoftware - Clicker - Palette", "application/vnd.crick.clicker.palette", "clkp"));
		ft.add(new FileType("CrickSoftware - Clicker - Template", "application/vnd.crick.clicker.template", "clkt"));
		ft.add(new FileType("CrickSoftware - Clicker - Wordbank", "application/vnd.crick.clicker.wordbank", "clkw"));
		ft.add(new FileType("Critical Tools - PERT Chart EXPERT", "application/vnd.criticaltools.wbs+xml", "wbs"));
		ft.add(new FileType("CryptoNote", "application/vnd.rig.cryptonote", "cryptonote"));
		ft.add(new FileType("Crystallographic Interchange Format", "chemical/x-cif", "cif"));
		ft.add(new FileType("CrystalMaker Data Format", "chemical/x-cmdf", "cmdf"));
		ft.add(new FileType("CU-SeeMe", "application/cu-seeme", "cu"));
		ft.add(new FileType("CU-Writer", "application/prs.cww", "cww"));
		ft.add(new FileType("Curl - Applet", "text/vnd.curl", "curl"));
		ft.add(new FileType("Curl - Detached Applet", "text/vnd.curl.dcurl", "dcurl"));
		ft.add(new FileType("Curl - Manifest File", "text/vnd.curl.mcurl", "mcurl"));
		ft.add(new FileType("Curl - Source Code", "text/vnd.curl.scurl", "scurl"));
		ft.add(new FileType("CURL Applet", "application/vnd.curl.car", "car"));
		ft.add(new FileType("CURL Applet", "application/vnd.curl.pcurl", "pcurl"));
		ft.add(new FileType("CustomMenu", "application/vnd.yellowriver-custom-menu", "cmp"));
		ft.add(new FileType("Data Structure for the Security Suitability of Cryptographic Algorithms", "application/dssc+der", "dssc"));
		ft.add(new FileType("Data Structure for the Security Suitability of Cryptographic Algorithms", "application/dssc+xml", "xdssc"));
		ft.add(new FileType("Debian Package", "application/x-debian-package", "deb"));
		ft.add(new FileType("DECE Audio", "audio/vnd.dece.audio", "uva"));
		ft.add(new FileType("DECE Graphic", "image/vnd.dece.graphic", "uvi"));
		ft.add(new FileType("DECE High Definition Video", "video/vnd.dece.hd", "uvh"));
		ft.add(new FileType("DECE Mobile Video", "video/vnd.dece.mobile", "uvm"));
		ft.add(new FileType("DECE MP4", "video/vnd.uvvu.mp4", "uvu"));
		ft.add(new FileType("DECE PD Video", "video/vnd.dece.pd", "uvp"));
		ft.add(new FileType("DECE SD Video", "video/vnd.dece.sd", "uvs"));
		ft.add(new FileType("DECE Video", "video/vnd.dece.video", "uvv"));
		ft.add(new FileType("Device Independent File Format (DVI)", "application/x-dvi", "dvi"));
		ft.add(new FileType("Digital Siesmograph Networks - SEED Datafiles", "application/vnd.fdsn.seed", "seed"));
		ft.add(new FileType("Digital Talking Book", "application/x-dtbook+xml", "dtb"));
		ft.add(new FileType("Digital Talking Book - Resource File", "application/x-dtbresource+xml", "res"));
		ft.add(new FileType("Digital Video Broadcasting", "application/vnd.dvb.ait", "ait"));
		ft.add(new FileType("Digital Video Broadcasting", "application/vnd.dvb.service", "svc"));
		ft.add(new FileType("Digital Winds Music", "audio/vnd.digital-winds", "eol"));
		ft.add(new FileType("DjVu", "image/vnd.djvu", "djvu"));
		ft.add(new FileType("Document Type Definition", "application/xml-dtd", "dtd"));
		ft.add(new FileType("Dolby Meridian Lossless Packing", "application/vnd.dolby.mlp", "mlp"));
		ft.add(new FileType("Doom Video Game", "application/x-doom", "wad"));
		ft.add(new FileType("DPGraph", "application/vnd.dpgraph", "dpg"));
		ft.add(new FileType("DRA Audio", "audio/vnd.dra", "dra"));
		ft.add(new FileType("DreamFactory", "application/vnd.dreamfactory", "dfac"));
		ft.add(new FileType("DTS Audio", "audio/vnd.dts", "dts"));
		ft.add(new FileType("DTS High Definition Audio", "audio/vnd.dts.hd", "dtshd"));
		ft.add(new FileType("DWG Drawing", "image/vnd.dwg", "dwg"));
		ft.add(new FileType("DynaGeo", "application/vnd.dynageo", "geo"));
		ft.add(new FileType("ECMAScript", "application/ecmascript", "es"));
		ft.add(new FileType("EcoWin Chart", "application/vnd.ecowin.chart", "mag"));
		ft.add(new FileType("EDMICS 2000", "image/vnd.fujixerox.edmics-mmr", "mmr"));
		ft.add(new FileType("EDMICS 2000", "image/vnd.fujixerox.edmics-rlc", "rlc"));
		ft.add(new FileType("Efficient XML Interchange", "application/exi", "exi"));
		ft.add(new FileType("EFI Proteus", "application/vnd.proteus.magazine", "mgz"));
		ft.add(new FileType("Electronic Publication", "application/epub+zip", "epub"));
		ft.add(new FileType("Email Message", "message/rfc822", "eml"));
		ft.add(new FileType("Enliven Viewer", "application/vnd.enliven", "nml"));
		ft.add(new FileType("Express by Infoseek", "application/vnd.is-xpr", "xpr"));
		ft.add(new FileType("eXtended Image File Format (XIFF)", "image/vnd.xiff", "xif"));
		ft.add(new FileType("Extensible Forms Description Language", "application/vnd.xfdl", "xfdl"));
		ft.add(new FileType("Extensible MultiModal Annotation", "application/emma+xml", "emma"));
		ft.add(new FileType("EZPix Secure Photo Album", "application/vnd.ezpix-album", "ez2"));
		ft.add(new FileType("EZPix Secure Photo Album", "application/vnd.ezpix-package", "ez3"));
		ft.add(new FileType("FAST Search & Transfer ASA", "image/vnd.fst", "fst"));
		ft.add(new FileType("FAST Search & Transfer ASA", "video/vnd.fvt", "fvt"));
		ft.add(new FileType("FastBid Sheet", "image/vnd.fastbidsheet", "fbs"));
		ft.add(new FileType("FCS Express Layout Link", "application/vnd.denovo.fcselayout-link", "fe_launch"));
		ft.add(new FileType("Flash Video", "video/x-f4v", "f4v"));
		ft.add(new FileType("Flash Video", "video/x-flv", "flv"));
		ft.add(new FileType("FlashPix", "image/vnd.fpx", "fpx"));
		ft.add(new FileType("FlashPix", "image/vnd.net-fpx", "npx"));
		ft.add(new FileType("FLEXSTOR", "text/vnd.fmi.flexstor", "flx"));
		ft.add(new FileType("FLI/FLC Animation Format", "video/x-fli", "fli"));
		ft.add(new FileType("FluxTime Clip", "application/vnd.fluxtime.clip", "ftc"));
		ft.add(new FileType("Forms Data Format", "application/vnd.fdf", "fdf"));
		ft.add(new FileType("Fortran Source File", "text/x-fortran", "f"));
		ft.add(new FileType("FrameMaker Interchange Format", "application/vnd.mif", "mif"));
		ft.add(new FileType("FrameMaker Normal Format", "application/vnd.framemaker", "fm"));
		ft.add(new FileType("FreeHand MX", "image/x-freehand", "fh"));
		ft.add(new FileType("Friendly Software Corporation", "application/vnd.fsc.weblaunch", "fsc"));
		ft.add(new FileType("Frogans Player", "application/vnd.frogans.fnc", "fnc"));
		ft.add(new FileType("Frogans Player", "application/vnd.frogans.ltf", "ltf"));
		ft.add(new FileType("Fujitsu - Xerox 2D CAD Data", "application/vnd.fujixerox.ddd", "ddd"));
		ft.add(new FileType("Fujitsu - Xerox DocuWorks", "application/vnd.fujixerox.docuworks", "xdw"));
		ft.add(new FileType("Fujitsu - Xerox DocuWorks Binder", "application/vnd.fujixerox.docuworks.binder", "xbd"));
		ft.add(new FileType("Fujitsu Oasys", "application/vnd.fujitsu.oasys", "oas"));
		ft.add(new FileType("Fujitsu Oasys", "application/vnd.fujitsu.oasys2", "oa2"));
		ft.add(new FileType("Fujitsu Oasys", "application/vnd.fujitsu.oasys3", "oa3"));
		ft.add(new FileType("Fujitsu Oasys", "application/vnd.fujitsu.oasysgp", "fg5"));
		ft.add(new FileType("Fujitsu Oasys", "application/vnd.fujitsu.oasysprs", "bh2"));
		ft.add(new FileType("FutureSplash Animator", "application/x-futuresplash", "spl"));
		ft.add(new FileType("FuzzySheet", "application/vnd.fuzzysheet", "fzs"));
		ft.add(new FileType("G3 Fax Image", "image/g3fax", "g3"));
		ft.add(new FileType("GameMaker ActiveX", "application/vnd.gmx", "gmx"));
		ft.add(new FileType("Gen-Trix Studio", "model/vnd.gtw", "gtw"));
		ft.add(new FileType("Genomatix Tuxedo Framework", "application/vnd.genomatix.tuxedo", "txd"));
		ft.add(new FileType("GeoGebra", "application/vnd.geogebra.file", "ggb"));
		ft.add(new FileType("GeoGebra", "application/vnd.geogebra.tool", "ggt"));
		ft.add(new FileType("Geometric Description Language (GDL)", "model/vnd.gdl", "gdl"));
		ft.add(new FileType("GeoMetry Explorer", "application/vnd.geometry-explorer", "gex"));
		ft.add(new FileType("GEONExT and JSXGraph", "application/vnd.geonext", "gxt"));
		ft.add(new FileType("GeoplanW", "application/vnd.geoplan", "g2w"));
		ft.add(new FileType("GeospacW", "application/vnd.geospace", "g3w"));
		ft.add(new FileType("Ghostscript Font", "application/x-font-ghostscript", "gsf"));
		ft.add(new FileType("Glyph Bitmap Distribution Format", "application/x-font-bdf", "bdf"));
		ft.add(new FileType("GNU Tar Files", "application/x-gtar", "gtar"));
		ft.add(new FileType("GNU Texinfo Document", "application/x-texinfo", "texinfo"));
		ft.add(new FileType("Gnumeric", "application/x-gnumeric", "gnumeric"));
		ft.add(new FileType("Google Earth - KML", "application/vnd.google-earth.kml+xml", "kml"));
		ft.add(new FileType("Google Earth - Zipped KML", "application/vnd.google-earth.kmz", "kmz"));
		ft.add(new FileType("GrafEq", "application/vnd.grafeq", "gqf"));
		ft.add(new FileType("Graphics Interchange Format", "image/gif", "gif"));
		ft.add(new FileType("Graphviz", "text/vnd.graphviz", "gv"));
		ft.add(new FileType("Groove - Account", "application/vnd.groove-account", "gac"));
		ft.add(new FileType("Groove - Help", "application/vnd.groove-help", "ghf"));
		ft.add(new FileType("Groove - Identity Message", "application/vnd.groove-identity-message", "gim"));
		ft.add(new FileType("Groove - Injector", "application/vnd.groove-injector", "grv"));
		ft.add(new FileType("Groove - Tool Message", "application/vnd.groove-tool-message", "gtm"));
		ft.add(new FileType("Groove - Tool Template", "application/vnd.groove-tool-template", "tpl"));
		ft.add(new FileType("Groove - Vcard", "application/vnd.groove-vcard", "vcg"));
		ft.add(new FileType("H.261", "video/h261", "h261"));
		ft.add(new FileType("H.263", "video/h263", "h263"));
		ft.add(new FileType("H.264", "video/h264", "h264"));
		ft.add(new FileType("Hewlett Packard Instant Delivery", "application/vnd.hp-hpid", "hpid"));
		ft.add(new FileType("Hewlett-Packard's WebPrintSmart", "application/vnd.hp-hps", "hps"));
		ft.add(new FileType("Hierarchical Data Format", "application/x-hdf", "hdf"));
		ft.add(new FileType("Hit'n'Mix", "audio/vnd.rip", "rip"));
		ft.add(new FileType("Homebanking Computer Interface (HBCI)", "application/vnd.hbci", "hbci"));
		ft.add(new FileType("HP Indigo Digital Press - Job Layout Languate", "application/vnd.hp-jlyt", "jlt"));
		ft.add(new FileType("HP Printer Command Language", "application/vnd.hp-pcl", "pcl"));
		ft.add(new FileType("HP-GL/2 and HP RTL", "application/vnd.hp-hpgl", "hpgl"));
		ft.add(new FileType("HV Script", "application/vnd.yamaha.hv-script", "hvs"));
		ft.add(new FileType("HV Voice Dictionary", "application/vnd.yamaha.hv-dic", "hvd"));
		ft.add(new FileType("HV Voice Parameter", "application/vnd.yamaha.hv-voice", "hvp"));
		ft.add(new FileType("Hydrostatix Master Suite", "application/vnd.hydrostatix.sof-data", "sfd-hdstx"));
		ft.add(new FileType("Hyperstudio", "application/hyperstudio", "stk"));
		ft.add(new FileType("Hypertext Application Language", "application/vnd.hal+xml", "hal"));
		ft.add(new FileType("HyperText Markup Language (HTML)", "text/html", "html"));
		ft.add(new FileType("IBM DB2 Rights Manager", "application/vnd.ibm.rights-management", "irm"));
		ft.add(new FileType("IBM Electronic Media Management System - Secure Container", "application/vnd.ibm.secure-container", "sc"));
		ft.add(new FileType("iCalendar", "text/calendar", "ics"));
		ft.add(new FileType("ICC profile", "application/vnd.iccprofile", "icc"));
		ft.add(new FileType("Icon Image", "image/x-icon", "ico"));
		ft.add(new FileType("igLoader", "application/vnd.igloader", "igl"));
		ft.add(new FileType("Image Exchange Format", "image/ief", "ief"));
		ft.add(new FileType("ImmerVision PURE Players", "application/vnd.immervision-ivp", "ivp"));
		ft.add(new FileType("ImmerVision PURE Players", "application/vnd.immervision-ivu", "ivu"));
		ft.add(new FileType("IMS Networks", "application/reginfo+xml", "rif"));
		ft.add(new FileType("In3D - 3DML", "text/vnd.in3d.3dml", "3dml"));
		ft.add(new FileType("In3D - 3DML", "text/vnd.in3d.spot", "spot"));
		ft.add(new FileType("Initial Graphics Exchange Specification (IGES)", "model/iges", "igs"));
		ft.add(new FileType("Interactive Geometry Software", "application/vnd.intergeo", "i2g"));
		ft.add(new FileType("Interactive Geometry Software Cinderella", "application/vnd.cinderella", "cdy"));
		ft.add(new FileType("Intercon FormNet", "application/vnd.intercon.formnet", "xpw"));
		ft.add(new FileType("International Society for Advancement of Cytometry", "application/vnd.isac.fcs", "fcs"));
		ft.add(new FileType("Internet Protocol Flow Information Export", "application/ipfix", "ipfix"));
		ft.add(new FileType("Internet Public Key Infrastructure - Certificate", "application/pkix-cert", "cer"));
		ft.add(new FileType("Internet Public Key Infrastructure - Certificate Management Protocole", "application/pkixcmp", "pki"));
		ft.add(new FileType("Internet Public Key Infrastructure - Certificate Revocation Lists", "application/pkix-crl", "crl"));
		ft.add(new FileType("Internet Public Key Infrastructure - Certification Path", "application/pkix-pkipath", "pkipath"));
		ft.add(new FileType("IOCOM Visimeet", "application/vnd.insors.igm", "igm"));
		ft.add(new FileType("IP Unplugged Roaming Client", "application/vnd.ipunplugged.rcprofile", "rcprofile"));
		ft.add(new FileType("iRepository / Lucidoc Editor", "application/vnd.irepository.package+xml", "irp"));
		ft.add(new FileType("J2ME App Descriptor", "text/vnd.sun.j2me.app-descriptor", "jad"));
		ft.add(new FileType("Java Archive", "application/java-archive", "jar"));
		ft.add(new FileType("Java Bytecode File", "application/java-vm", "class"));
		ft.add(new FileType("Java Network Launching Protocol", "application/x-java-jnlp-file", "jnlp"));
		ft.add(new FileType("Java Serialized Object", "application/java-serialized-object", "ser"));
		ft.add(new FileType("Java Source File", "text/x-java-source,java", "java"));
		ft.add(new FileType("JavaScript", "text/javascript", "js"));
		ft.add(new FileType("JavaScript Object Notation (JSON)", "application/json", "json"));
		ft.add(new FileType("Joda Archive", "application/vnd.joost.joda-archive", "joda"));
		ft.add(new FileType("JPEG 2000 Compound Image File Format", "video/jpm", "jpm"));
		ft.add(new FileType("JPEG Image", "image/jpeg", "jpeg"));
		ft.add(new FileType("JPEG Image", "image/jpeg", "jpg"));
		ft.add(new FileType("JPGVideo", "video/jpeg", "jpgv"));
		ft.add(new FileType("Kahootz", "application/vnd.kahootz", "ktz"));
		ft.add(new FileType("Karaoke on Chipnuts Chipsets", "application/vnd.chipnuts.karaoke-mmd", "mmd"));
		ft.add(new FileType("KDE KOffice Office Suite - Karbon", "application/vnd.kde.karbon", "karbon"));
		ft.add(new FileType("KDE KOffice Office Suite - KChart", "application/vnd.kde.kchart", "chrt"));
		ft.add(new FileType("KDE KOffice Office Suite - Kformula", "application/vnd.kde.kformula", "kfo"));
		ft.add(new FileType("KDE KOffice Office Suite - Kivio", "application/vnd.kde.kivio", "flw"));
		ft.add(new FileType("KDE KOffice Office Suite - Kontour", "application/vnd.kde.kontour", "kon"));
		ft.add(new FileType("KDE KOffice Office Suite - Kpresenter", "application/vnd.kde.kpresenter", "kpr"));
		ft.add(new FileType("KDE KOffice Office Suite - Kspread", "application/vnd.kde.kspread", "ksp"));
		ft.add(new FileType("KDE KOffice Office Suite - Kword", "application/vnd.kde.kword", "kwd"));
		ft.add(new FileType("Kenamea App", "application/vnd.kenameaapp", "htke"));
		ft.add(new FileType("Kidspiration", "application/vnd.kidspiration", "kia"));
		ft.add(new FileType("Kinar Applications", "application/vnd.kinar", "kne"));
		ft.add(new FileType("Kodak Storyshare", "application/vnd.kodak-descriptor", "sse"));
		ft.add(new FileType("Laser App Enterprise", "application/vnd.las.las+xml", "lasxml"));
		ft.add(new FileType("LaTeX", "application/x-latex", "latex"));
		ft.add(new FileType("Life Balance - Desktop Edition", "application/vnd.llamagraphics.life-balance.desktop", "lbd"));
		ft.add(new FileType("Life Balance - Exchange Format", "application/vnd.llamagraphics.life-balance.exchange+xml", "lbe"));
		ft.add(new FileType("Lightspeed Audio Lab", "application/vnd.jam", "jam"));
		ft.add(new FileType("Lotus 1-2-3", "application/vnd.lotus-1-2-3", "123"));
		ft.add(new FileType("Lotus Approach", "application/vnd.lotus-approach", "apr"));
		ft.add(new FileType("Lotus Freelance", "application/vnd.lotus-freelance", "pre"));
		ft.add(new FileType("Lotus Notes", "application/vnd.lotus-notes", "nsf"));
		ft.add(new FileType("Lotus Organizer", "application/vnd.lotus-organizer", "org"));
		ft.add(new FileType("Lotus Screencam", "application/vnd.lotus-screencam", "scm"));
		ft.add(new FileType("Lotus Wordpro", "application/vnd.lotus-wordpro", "lwp"));
		ft.add(new FileType("Lucent Voice", "audio/vnd.lucent.voice", "lvp"));
		ft.add(new FileType("M3U (Multimedia Playlist)", "audio/x-mpegurl", "m3u"));
		ft.add(new FileType("M4v", "video/x-m4v", "m4v"));
		ft.add(new FileType("Macintosh BinHex 4.0", "application/mac-binhex40", "hqx"));
		ft.add(new FileType("MacPorts Port System", "application/vnd.macports.portpkg", "portpkg"));
		ft.add(new FileType("MapGuide DBXML", "application/vnd.osgeo.mapguide.package", "mgp"));
		ft.add(new FileType("MARC Formats", "application/marc", "mrc"));
		ft.add(new FileType("MARC21 XML Schema", "application/marcxml+xml", "mrcx"));
		ft.add(new FileType("Material Exchange Format", "application/mxf", "mxf"));
		ft.add(new FileType("Mathematica Notebook Player", "application/vnd.wolfram.player", "nbp"));
		ft.add(new FileType("Mathematica Notebooks", "application/mathematica", "ma"));
		ft.add(new FileType("Mathematical Markup Language", "application/mathml+xml", "mathml"));
		ft.add(new FileType("Mbox database files", "application/mbox", "mbox"));
		ft.add(new FileType("MedCalc", "application/vnd.medcalcdata", "mc1"));
		ft.add(new FileType("Media Server Control Markup Language", "application/mediaservercontrol+xml", "mscml"));
		ft.add(new FileType("MediaRemote", "application/vnd.mediastation.cdkey", "cdkey"));
		ft.add(new FileType("Medical Waveform Encoding Format", "application/vnd.mfer", "mwf"));
		ft.add(new FileType("Melody Format for Mobile Platform", "application/vnd.mfmp", "mfm"));
		ft.add(new FileType("Mesh Data Type", "model/mesh", "msh"));
		ft.add(new FileType("Metadata Authority Description Schema", "application/mads+xml", "mads"));
		ft.add(new FileType("Metadata Encoding and Transmission Standard", "application/mets+xml", "mets"));
		ft.add(new FileType("Metadata Object Description Schema", "application/mods+xml", "mods"));
		ft.add(new FileType("Metalink", "application/metalink4+xml", "meta4"));
		ft.add(new FileType("Micosoft PowerPoint - Macro-Enabled Template File", "application/vnd.ms-powerpoint.template.macroenabled.12", "potm"));
		ft.add(new FileType("Micosoft Word - Macro-Enabled Document", "application/vnd.ms-word.document.macroenabled.12", "docm"));
		ft.add(new FileType("Micosoft Word - Macro-Enabled Template", "application/vnd.ms-word.template.macroenabled.12", "dotm"));
		ft.add(new FileType("Micro CADAM Helix D&D", "application/vnd.mcd", "mcd"));
		ft.add(new FileType("Micrografx", "application/vnd.micrografx.flo", "flo"));
		ft.add(new FileType("Micrografx iGrafx Professional", "application/vnd.micrografx.igx", "igx"));
		ft.add(new FileType("MICROSEC e-Szign¢", "application/vnd.eszigno3+xml", "es3"));
		ft.add(new FileType("Microsoft Access", "application/x-msaccess", "mdb"));
		ft.add(new FileType("Microsoft Advanced Systems Format (ASF)", "video/x-ms-asf", "asf"));
		ft.add(new FileType("Microsoft Application", "application/x-msdownload", "exe"));
		ft.add(new FileType("Microsoft Artgalry", "application/vnd.ms-artgalry", "cil"));
		ft.add(new FileType("Microsoft Cabinet File", "application/vnd.ms-cab-compressed", "cab"));
		ft.add(new FileType("Microsoft Class Server", "application/vnd.ms-ims", "ims"));
		ft.add(new FileType("Microsoft ClickOnce", "application/x-ms-application", "application"));
		ft.add(new FileType("Microsoft Clipboard Clip", "application/x-msclip", "clp"));
		ft.add(new FileType("Microsoft Document Imaging Format", "image/vnd.ms-modi", "mdi"));
		ft.add(new FileType("Microsoft Embedded OpenType", "application/vnd.ms-fontobject", "eot"));
		ft.add(new FileType("Microsoft Excel", "application/vnd.ms-excel", "xls"));
		ft.add(new FileType("Microsoft Excel - Add-In File", "application/vnd.ms-excel.addin.macroenabled.12", "xlam"));
		ft.add(new FileType("Microsoft Excel - Binary Workbook", "application/vnd.ms-excel.sheet.binary.macroenabled.12", "xlsb"));
		ft.add(new FileType("Microsoft Excel - Macro-Enabled Template File", "application/vnd.ms-excel.template.macroenabled.12", "xltm"));
		ft.add(new FileType("Microsoft Excel - Macro-Enabled Workbook", "application/vnd.ms-excel.sheet.macroenabled.12", "xlsm"));
		ft.add(new FileType("Microsoft Html Help File", "application/vnd.ms-htmlhelp", "chm"));
		ft.add(new FileType("Microsoft Information Card", "application/x-mscardfile", "crd"));
		ft.add(new FileType("Microsoft Learning Resource Module", "application/vnd.ms-lrm", "lrm"));
		ft.add(new FileType("Microsoft MediaView", "application/x-msmediaview", "mvb"));
		ft.add(new FileType("Microsoft Money", "application/x-msmoney", "mny"));
		ft.add(new FileType("Microsoft Office - OOXML - Presentation", "application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx"));
		ft.add(new FileType("Microsoft Office - OOXML - Presentation (Slide)", "application/vnd.openxmlformats-officedocument.presentationml.slide", "sldx"));
		ft.add(new FileType("Microsoft Office - OOXML - Presentation (Slideshow)", "application/vnd.openxmlformats-officedocument.presentationml.slideshow", "ppsx"));
		ft.add(new FileType("Microsoft Office - OOXML - Presentation Template", "application/vnd.openxmlformats-officedocument.presentationml.template", "potx"));
		ft.add(new FileType("Microsoft Office - OOXML - Spreadsheet", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx"));
		ft.add(new FileType("Microsoft Office - OOXML - Spreadsheet Teplate", "application/vnd.openxmlformats-officedocument.spreadsheetml.template", "xltx"));
		ft.add(new FileType("Microsoft Office - OOXML - Word Document", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"));
		ft.add(new FileType("Microsoft Office - OOXML - Word Document Template", "application/vnd.openxmlformats-officedocument.wordprocessingml.template", "dotx"));
		ft.add(new FileType("Microsoft Office Binder", "application/x-msbinder", "obd"));
		ft.add(new FileType("Microsoft Office System Release Theme", "application/vnd.ms-officetheme", "thmx"));
		ft.add(new FileType("Microsoft OneNote", "application/onenote", "onetoc"));
		ft.add(new FileType("Microsoft PlayReady Ecosystem", "audio/vnd.ms-playready.media.pya", "pya"));
		ft.add(new FileType("Microsoft PlayReady Ecosystem Video", "video/vnd.ms-playready.media.pyv", "pyv"));
		ft.add(new FileType("Microsoft PowerPoint", "application/vnd.ms-powerpoint", "ppt"));
		ft.add(new FileType("Microsoft PowerPoint - Add-in file", "application/vnd.ms-powerpoint.addin.macroenabled.12", "ppam"));
		ft.add(new FileType("Microsoft PowerPoint - Macro-Enabled Open XML Slide", "application/vnd.ms-powerpoint.slide.macroenabled.12", "sldm"));
		ft.add(new FileType("Microsoft PowerPoint - Macro-Enabled Presentation File", "application/vnd.ms-powerpoint.presentation.macroenabled.12", "pptm"));
		ft.add(new FileType("Microsoft PowerPoint - Macro-Enabled Slide Show File", "application/vnd.ms-powerpoint.slideshow.macroenabled.12", "ppsm"));
		ft.add(new FileType("Microsoft Project", "application/vnd.ms-project", "mpp"));
		ft.add(new FileType("Microsoft Publisher", "application/x-mspublisher", "pub"));
		ft.add(new FileType("Microsoft Schedule+", "application/x-msschedule", "scd"));
		ft.add(new FileType("Microsoft Silverlight", "application/x-silverlight-app", "xap"));
		ft.add(new FileType("Microsoft Trust UI Provider - Certificate Trust Link", "application/vnd.ms-pki.stl", "stl"));
		ft.add(new FileType("Microsoft Trust UI Provider - Security Catalog", "application/vnd.ms-pki.seccat", "cat"));
		ft.add(new FileType("Microsoft Visio", "application/vnd.visio", "vsd"));
		ft.add(new FileType("Microsoft Windows Media", "video/x-ms-wm", "wm"));
		ft.add(new FileType("Microsoft Windows Media Audio", "audio/x-ms-wma", "wma"));
		ft.add(new FileType("Microsoft Windows Media Audio Redirector", "audio/x-ms-wax", "wax"));
		ft.add(new FileType("Microsoft Windows Media Audio/Video Playlist", "video/x-ms-wmx", "wmx"));
		ft.add(new FileType("Microsoft Windows Media Player Download Package", "application/x-ms-wmd", "wmd"));
		ft.add(new FileType("Microsoft Windows Media Player Playlist", "application/vnd.ms-wpl", "wpl"));
		ft.add(new FileType("Microsoft Windows Media Player Skin Package", "application/x-ms-wmz", "wmz"));
		ft.add(new FileType("Microsoft Windows Media Video", "video/x-ms-wmv", "wmv"));
		ft.add(new FileType("Microsoft Windows Media Video Playlist", "video/x-ms-wvx", "wvx"));
		ft.add(new FileType("Microsoft Windows Metafile", "application/x-msmetafile", "wmf"));
		ft.add(new FileType("Microsoft Windows Terminal Services", "application/x-msterminal", "trm"));
		ft.add(new FileType("Microsoft Word", "application/msword", "doc"));
		ft.add(new FileType("Microsoft Wordpad", "application/x-mswrite", "wri"));
		ft.add(new FileType("Microsoft Works", "application/vnd.ms-works", "wps"));
		ft.add(new FileType("Microsoft XAML Browser Application", "application/x-ms-xbap", "xbap"));
		ft.add(new FileType("Microsoft XML Paper Specification", "application/vnd.ms-xpsdocument", "xps"));
		ft.add(new FileType("MIDI - Musical Instrument Digital Interface", "audio/midi", "mid"));
		ft.add(new FileType("MiniPay", "application/vnd.ibm.minipay", "mpy"));
		ft.add(new FileType("MO:DCA-P", "application/vnd.ibm.modcap", "afp"));
		ft.add(new FileType("Mobile Information Device Profile", "application/vnd.jcp.javame.midlet-rms", "rms"));
		ft.add(new FileType("MobileTV", "application/vnd.tmobile-livetv", "tmo"));
		ft.add(new FileType("Mobipocket", "application/x-mobipocket-ebook", "prc"));
		ft.add(new FileType("Mobius Management Systems - Basket file", "application/vnd.mobius.mbk", "mbk"));
		ft.add(new FileType("Mobius Management Systems - Distribution Database", "application/vnd.mobius.dis", "dis"));
		ft.add(new FileType("Mobius Management Systems - Policy Definition Language File", "application/vnd.mobius.plc", "plc"));
		ft.add(new FileType("Mobius Management Systems - Query File", "application/vnd.mobius.mqy", "mqy"));
		ft.add(new FileType("Mobius Management Systems - Script Language", "application/vnd.mobius.msl", "msl"));
		ft.add(new FileType("Mobius Management Systems - Topic Index File", "application/vnd.mobius.txf", "txf"));
		ft.add(new FileType("Mobius Management Systems - UniversalArchive", "application/vnd.mobius.daf", "daf"));
		ft.add(new FileType("mod_fly / fly.cgi", "text/vnd.fly", "fly"));
		ft.add(new FileType("Mophun Certificate", "application/vnd.mophun.certificate", "mpc"));
		ft.add(new FileType("Mophun VM", "application/vnd.mophun.application", "mpn"));
		ft.add(new FileType("Motion JPEG 2000", "video/mj2", "mj2"));
		ft.add(new FileType("MPEG Audio", "audio/mpeg", "mpga"));
		ft.add(new FileType("MPEG Url", "video/vnd.mpegurl", "mxu"));
		ft.add(new FileType("MPEG Video", "video/mpeg", "mpeg"));
		ft.add(new FileType("MPEG-21", "application/mp21", "m21"));
		ft.add(new FileType("MPEG-4 Audio", "audio/mp4", "mp4a"));
		ft.add(new FileType("MPEG-4 Video", "video/mp4", "mp4"));
		ft.add(new FileType("MPEG4", "application/mp4", "mp4"));
		ft.add(new FileType("Multimedia Playlist Unicode", "application/vnd.apple.mpegurl", "m3u8"));
		ft.add(new FileType("MUsical Score Interpreted Code Invented for the ASCII designation of Notation", "application/vnd.musician", "mus"));
		ft.add(new FileType("Muvee Automatic Video Editing", "application/vnd.muvee.style", "msty"));
		ft.add(new FileType("MXML", "application/xv+xml", "mxml"));
		ft.add(new FileType("N-Gage Game Data", "application/vnd.nokia.n-gage.data", "ngdat"));
		ft.add(new FileType("N-Gage Game Installer", "application/vnd.nokia.n-gage.symbian.install", "n-gage"));
		ft.add(new FileType("Navigation Control file for XML (for ePub)", "application/x-dtbncx+xml", "ncx"));
		ft.add(new FileType("Network Common Data Form (NetCDF)", "application/x-netcdf", "nc"));
		ft.add(new FileType("neuroLanguage", "application/vnd.neurolanguage.nlu", "nlu"));
		ft.add(new FileType("New Moon Liftoff/DNA", "application/vnd.dna", "dna"));
		ft.add(new FileType("NobleNet Directory", "application/vnd.noblenet-directory", "nnd"));
		ft.add(new FileType("NobleNet Sealer", "application/vnd.noblenet-sealer", "nns"));
		ft.add(new FileType("NobleNet Web", "application/vnd.noblenet-web", "nnw"));
		ft.add(new FileType("Nokia Radio Application - Preset", "application/vnd.nokia.radio-preset", "rpst"));
		ft.add(new FileType("Nokia Radio Application - Preset", "application/vnd.nokia.radio-presets", "rpss"));
		ft.add(new FileType("Notation3", "text/n3", "n3"));
		ft.add(new FileType("Novadigm's RADIA and EDM products", "application/vnd.novadigm.edm", "edm"));
		ft.add(new FileType("Novadigm's RADIA and EDM products", "application/vnd.novadigm.edx", "edx"));
		ft.add(new FileType("Novadigm's RADIA and EDM products", "application/vnd.novadigm.ext", "ext"));
		ft.add(new FileType("NpGraphIt", "application/vnd.flographit", "gph"));
		ft.add(new FileType("Nuera ECELP 4800", "audio/vnd.nuera.ecelp4800", "ecelp4800"));
		ft.add(new FileType("Nuera ECELP 7470", "audio/vnd.nuera.ecelp7470", "ecelp7470"));
		ft.add(new FileType("Nuera ECELP 9600", "audio/vnd.nuera.ecelp9600", "ecelp9600"));
		ft.add(new FileType("Office Document Architecture", "application/oda", "oda"));
		ft.add(new FileType("Ogg", "application/ogg", "ogx"));
		ft.add(new FileType("Ogg Audio", "audio/ogg", "oga"));
		ft.add(new FileType("Ogg Video", "video/ogg", "ogv"));
		ft.add(new FileType("OMA Download Agents", "application/vnd.oma.dd2+xml", "dd2"));
		ft.add(new FileType("Open Document Text Web", "application/vnd.oasis.opendocument.text-web", "oth"));
		ft.add(new FileType("Open eBook Publication Structure", "application/oebps-package+xml", "opf"));
		ft.add(new FileType("Open Financial Exchange", "application/vnd.intu.qbo", "qbo"));
		ft.add(new FileType("Open Office Extension", "application/vnd.openofficeorg.extension", "oxt"));
		ft.add(new FileType("Open Score Format", "application/vnd.yamaha.openscoreformat", "osf"));
		ft.add(new FileType("Open Web Media Project - Audio", "audio/webm", "weba"));
		ft.add(new FileType("Open Web Media Project - Video", "video/webm", "webm"));
		ft.add(new FileType("OpenDocument Chart", "application/vnd.oasis.opendocument.chart", "odc"));
		ft.add(new FileType("OpenDocument Chart Template", "application/vnd.oasis.opendocument.chart-template", "otc"));
		ft.add(new FileType("OpenDocument Database", "application/vnd.oasis.opendocument.database", "odb"));
		ft.add(new FileType("OpenDocument Formula", "application/vnd.oasis.opendocument.formula", "odf"));
		ft.add(new FileType("OpenDocument Formula Template", "application/vnd.oasis.opendocument.formula-template", "odft"));
		ft.add(new FileType("OpenDocument Graphics", "application/vnd.oasis.opendocument.graphics", "odg"));
		ft.add(new FileType("OpenDocument Graphics Template", "application/vnd.oasis.opendocument.graphics-template", "otg"));
		ft.add(new FileType("OpenDocument Image", "application/vnd.oasis.opendocument.image", "odi"));
		ft.add(new FileType("OpenDocument Image Template", "application/vnd.oasis.opendocument.image-template", "oti"));
		ft.add(new FileType("OpenDocument Presentation", "application/vnd.oasis.opendocument.presentation", "odp"));
		ft.add(new FileType("OpenDocument Presentation Template", "application/vnd.oasis.opendocument.presentation-template", "otp"));
		ft.add(new FileType("OpenDocument Spreadsheet", "application/vnd.oasis.opendocument.spreadsheet", "ods"));
		ft.add(new FileType("OpenDocument Spreadsheet Template", "application/vnd.oasis.opendocument.spreadsheet-template", "ots"));
		ft.add(new FileType("OpenDocument Text", "application/vnd.oasis.opendocument.text", "odt"));
		ft.add(new FileType("OpenDocument Text Master", "application/vnd.oasis.opendocument.text-master", "odm"));
		ft.add(new FileType("OpenDocument Text Template", "application/vnd.oasis.opendocument.text-template", "ott"));
		ft.add(new FileType("OpenGL Textures (KTX)", "image/ktx", "ktx"));
		ft.add(new FileType("OpenOffice - Calc (Spreadsheet)", "application/vnd.sun.xml.calc", "sxc"));
		ft.add(new FileType("OpenOffice - Calc Template (Spreadsheet)", "application/vnd.sun.xml.calc.template", "stc"));
		ft.add(new FileType("OpenOffice - Draw (Graphics)", "application/vnd.sun.xml.draw", "sxd"));
		ft.add(new FileType("OpenOffice - Draw Template (Graphics)", "application/vnd.sun.xml.draw.template", "std"));
		ft.add(new FileType("OpenOffice - Impress (Presentation)", "application/vnd.sun.xml.impress", "sxi"));
		ft.add(new FileType("OpenOffice - Impress Template (Presentation)", "application/vnd.sun.xml.impress.template", "sti"));
		ft.add(new FileType("OpenOffice - Math (Formula)", "application/vnd.sun.xml.math", "sxm"));
		ft.add(new FileType("OpenOffice - Writer (Text - HTML)", "application/vnd.sun.xml.writer", "sxw"));
		ft.add(new FileType("OpenOffice - Writer (Text - HTML)", "application/vnd.sun.xml.writer.global", "sxg"));
		ft.add(new FileType("OpenOffice - Writer Template (Text - HTML)", "application/vnd.sun.xml.writer.template", "stw"));
		ft.add(new FileType("OpenType Font File", "application/x-font-otf", "otf"));
		ft.add(new FileType("OSFPVG", "application/vnd.yamaha.openscoreformat.osfpvg+xml", "osfpvg"));
		ft.add(new FileType("OSGi Deployment Package", "application/vnd.osgi.dp", "dp"));
		ft.add(new FileType("PalmOS Data", "application/vnd.palm", "pdb"));
		ft.add(new FileType("Pascal Source File", "text/x-pascal", "p"));
		ft.add(new FileType("PawaaFILE", "application/vnd.pawaafile", "paw"));
		ft.add(new FileType("PCL 6 Enhanced (Formely PCL XL)", "application/vnd.hp-pclxl", "pclxl"));
		ft.add(new FileType("Pcsel eFIF File", "application/vnd.picsel", "efif"));
		ft.add(new FileType("PCX Image", "image/x-pcx", "pcx"));
		ft.add(new FileType("Photoshop Document", "image/vnd.adobe.photoshop", "psd"));
		ft.add(new FileType("PICSRules", "application/pics-rules", "prf"));
		ft.add(new FileType("PICT Image", "image/x-pict", "pic"));
		ft.add(new FileType("pIRCh", "application/x-chat", "chat"));
		ft.add(new FileType("PKCS #10 - Certification Request Standard", "application/pkcs10", "p10"));
		ft.add(new FileType("PKCS #12 - Personal Information Exchange Syntax Standard", "application/x-pkcs12", "p12"));
		ft.add(new FileType("PKCS #7 - Cryptographic Message Syntax Standard", "application/pkcs7-mime", "p7m"));
		ft.add(new FileType("PKCS #7 - Cryptographic Message Syntax Standard", "application/pkcs7-signature", "p7s"));
		ft.add(new FileType("PKCS #7 - Cryptographic Message Syntax Standard (Certificate Request Response)", "application/x-pkcs7-certreqresp", "p7r"));
		ft.add(new FileType("PKCS #7 - Cryptographic Message Syntax Standard (Certificates)", "application/x-pkcs7-certificates", "p7b"));
		ft.add(new FileType("PKCS #8 - Private-Key Information Syntax Standard", "application/pkcs8", "p8"));
		ft.add(new FileType("PocketLearn Viewers", "application/vnd.pocketlearn", "plf"));
		ft.add(new FileType("Portable Anymap Image", "image/x-portable-anymap", "pnm"));
		ft.add(new FileType("Portable Bitmap Format", "image/x-portable-bitmap", "pbm"));
		ft.add(new FileType("Portable Compiled Format", "application/x-font-pcf", "pcf"));
		ft.add(new FileType("Portable Font Resource", "application/font-tdpfr", "pfr"));
		ft.add(new FileType("Portable Game Notation (Chess Games)", "application/x-chess-pgn", "pgn"));
		ft.add(new FileType("Portable Graymap Format", "image/x-portable-graymap", "pgm"));
		ft.add(new FileType("Portable Network Graphics (PNG)", "image/png", "png"));
		ft.add(new FileType("Portable Pixmap Format", "image/x-portable-pixmap", "ppm"));
		ft.add(new FileType("Portable Symmetric Key Container", "application/pskc+xml", "pskcxml"));
		ft.add(new FileType("PosML", "application/vnd.ctc-posml", "pml"));
		ft.add(new FileType("PostScript", "application/postscript", "ai"));
		ft.add(new FileType("PostScript Fonts", "application/x-font-type1", "pfa"));
		ft.add(new FileType("PowerBuilder", "application/vnd.powerbuilder6", "pbd"));
		ft.add(new FileType("Pretty Good Privacy", "application/pgp-encrypted", ""));
		ft.add(new FileType("Pretty Good Privacy - Signature", "application/pgp-signature", "pgp"));
		ft.add(new FileType("Preview Systems ZipLock/VBox", "application/vnd.previewsystems.box", "box"));
		ft.add(new FileType("Princeton Video Image", "application/vnd.pvi.ptid1", "ptid"));
		ft.add(new FileType("Pronunciation Lexicon Specification", "application/pls+xml", "pls"));
		ft.add(new FileType("Proprietary P&G Standard Reporting System", "application/vnd.pg.format", "str"));
		ft.add(new FileType("Proprietary P&G Standard Reporting System", "application/vnd.pg.osasli", "ei6"));
		ft.add(new FileType("PRS Lines Tag", "text/prs.lines.tag", "dsc"));
		ft.add(new FileType("PSF Fonts", "application/x-font-linux-psf", "psf"));
		ft.add(new FileType("PubliShare Objects", "application/vnd.publishare-delta-tree", "qps"));
		ft.add(new FileType("Qualcomm's Plaza Mobile Internet", "application/vnd.pmi.widget", "wg"));
		ft.add(new FileType("QuarkXpress", "application/vnd.quark.quarkxpress", "qxd"));
		ft.add(new FileType("QUASS Stream Player", "application/vnd.epson.esf", "esf"));
		ft.add(new FileType("QUASS Stream Player", "application/vnd.epson.msf", "msf"));
		ft.add(new FileType("QUASS Stream Player", "application/vnd.epson.ssf", "ssf"));
		ft.add(new FileType("QuickAnime Player", "application/vnd.epson.quickanime", "qam"));
		ft.add(new FileType("Quicken", "application/vnd.intu.qfx", "qfx"));
		ft.add(new FileType("Quicktime Video", "video/quicktime", "qt"));
		ft.add(new FileType("RAR Archive", "application/x-rar-compressed", "rar"));
		ft.add(new FileType("Real Audio Sound", "audio/x-pn-realaudio", "ram"));
		ft.add(new FileType("Real Audio Sound", "audio/x-pn-realaudio-plugin", "rmp"));
		ft.add(new FileType("Really Simple Discovery", "application/rsd+xml", "rsd"));
		ft.add(new FileType("RealMedia", "application/vnd.rn-realmedia", "rm"));
		ft.add(new FileType("RealVNC", "application/vnd.realvnc.bed", "bed"));
		ft.add(new FileType("Recordare Applications", "application/vnd.recordare.musicxml", "mxl"));
		ft.add(new FileType("Recordare Applications", "application/vnd.recordare.musicxml+xml", "musicxml"));
		ft.add(new FileType("Relax NG Compact Syntax", "application/relax-ng-compact-syntax", "rnc"));
		ft.add(new FileType("RemoteDocs R-Viewer", "application/vnd.data-vision.rdz", "rdz"));
		ft.add(new FileType("Resource Description Framework", "application/rdf+xml", "rdf"));
		ft.add(new FileType("RetroPlatform Player", "application/vnd.cloanto.rp9", "rp9"));
		ft.add(new FileType("RhymBox", "application/vnd.jisp", "jisp"));
		ft.add(new FileType("Rich Text Format", "application/rtf", "rtf"));
		ft.add(new FileType("Rich Text Format (RTF)", "text/richtext", "rtx"));
		ft.add(new FileType("ROUTE 66 Location Based Services", "application/vnd.route66.link66+xml", "link66"));
		ft.add(new FileType("RSS - Really Simple Syndication", "application/rss+xml", "rss"));
		ft.add(new FileType("S Hexdump Format", "application/shf+xml", "shf"));
		ft.add(new FileType("SailingTracker", "application/vnd.sailingtracker.track", "st"));
		ft.add(new FileType("Scalable Vector Graphics (SVG)", "image/svg+xml", "svg"));
		ft.add(new FileType("ScheduleUs", "application/vnd.sus-calendar", "sus"));
		ft.add(new FileType("Search/Retrieve via URL Response Format", "application/sru+xml", "sru"));
		ft.add(new FileType("Secure Electronic Transaction - Payment", "application/set-payment-initiation", "setpay"));
		ft.add(new FileType("Secure Electronic Transaction - Registration", "application/set-registration-initiation", "setreg"));
		ft.add(new FileType("Secured eMail", "application/vnd.sema", "sema"));
		ft.add(new FileType("Secured eMail", "application/vnd.semd", "semd"));
		ft.add(new FileType("Secured eMail", "application/vnd.semf", "semf"));
		ft.add(new FileType("SeeMail", "application/vnd.seemail", "see"));
		ft.add(new FileType("Server Normal Format", "application/x-font-snf", "snf"));
		ft.add(new FileType("Server-Based Certificate Validation Protocol - Validation Policies - Request", "application/scvp-vp-request", "spq"));
		ft.add(new FileType("Server-Based Certificate Validation Protocol - Validation Policies - Response", "application/scvp-vp-response", "spp"));
		ft.add(new FileType("Server-Based Certificate Validation Protocol - Validation Request", "application/scvp-cv-request", "scq"));
		ft.add(new FileType("Server-Based Certificate Validation Protocol - Validation Response", "application/scvp-cv-response", "scs"));
		ft.add(new FileType("Session Description Protocol", "application/sdp", "sdp"));
		ft.add(new FileType("Setext", "text/x-setext", "etx"));
		ft.add(new FileType("SGI Movie", "video/x-sgi-movie", "movie"));
		ft.add(new FileType("Shana Informed Filler", "application/vnd.shana.informed.formdata", "ifm"));
		ft.add(new FileType("Shana Informed Filler", "application/vnd.shana.informed.formtemplate", "itp"));
		ft.add(new FileType("Shana Informed Filler", "application/vnd.shana.informed.interchange", "iif"));
		ft.add(new FileType("Shana Informed Filler", "application/vnd.shana.informed.package", "ipk"));
		ft.add(new FileType("Sharing Transaction Fraud Data", "application/thraud+xml", "tfi"));
		ft.add(new FileType("Shell Archive", "application/x-shar", "shar"));
		ft.add(new FileType("Silicon Graphics RGB Bitmap", "image/x-rgb", "rgb"));
		ft.add(new FileType("SimpleAnimeLite Player", "application/vnd.epson.salt", "slt"));
		ft.add(new FileType("Simply Accounting", "application/vnd.accpac.simply.aso", "aso"));
		ft.add(new FileType("Simply Accounting - Data Import", "application/vnd.accpac.simply.imp", "imp"));
		ft.add(new FileType("SimTech MindMapper", "application/vnd.simtech-mindmapper", "twd"));
		ft.add(new FileType("Sixth Floor Media - CommonSpace", "application/vnd.commonspace", "csp"));
		ft.add(new FileType("SMAF Audio", "application/vnd.yamaha.smaf-audio", "saf"));
		ft.add(new FileType("SMAF File", "application/vnd.smaf", "mmf"));
		ft.add(new FileType("SMAF Phrase", "application/vnd.yamaha.smaf-phrase", "spf"));
		ft.add(new FileType("SMART Technologies Apps", "application/vnd.smart.teacher", "teacher"));
		ft.add(new FileType("SourceView Document", "application/vnd.svd", "svd"));
		ft.add(new FileType("SPARQL - Query", "application/sparql-query", "rq"));
		ft.add(new FileType("SPARQL - Results", "application/sparql-results+xml", "srx"));
		ft.add(new FileType("Speech Recognition Grammar Specification", "application/srgs", "gram"));
		ft.add(new FileType("Speech Recognition Grammar Specification - XML", "application/srgs+xml", "grxml"));
		ft.add(new FileType("Speech Synthesis Markup Language", "application/ssml+xml", "ssml"));
		ft.add(new FileType("SSEYO Koan Play File", "application/vnd.koan", "skp"));
		ft.add(new FileType("Standard Generalized Markup Language (SGML)", "text/sgml", "sgml"));
		ft.add(new FileType("StarOffice - Calc", "application/vnd.stardivision.calc", "sdc"));
		ft.add(new FileType("StarOffice - Draw", "application/vnd.stardivision.draw", "sda"));
		ft.add(new FileType("StarOffice - Impress", "application/vnd.stardivision.impress", "sdd"));
		ft.add(new FileType("StarOffice - Math", "application/vnd.stardivision.math", "smf"));
		ft.add(new FileType("StarOffice - Writer", "application/vnd.stardivision.writer", "sdw"));
		ft.add(new FileType("StarOffice - Writer (Global)", "application/vnd.stardivision.writer-global", "sgl"));
		ft.add(new FileType("StepMania", "application/vnd.stepmania.stepchart", "sm"));
		ft.add(new FileType("Stuffit Archive", "application/x-stuffit", "sit"));
		ft.add(new FileType("Stuffit Archive", "application/x-stuffitx", "sitx"));
		ft.add(new FileType("SudokuMagic", "application/vnd.solent.sdkm+xml", "sdkm"));
		ft.add(new FileType("Sugar Linux Application Bundle", "application/vnd.olpc-sugar", "xo"));
		ft.add(new FileType("Sun Audio - Au file format", "audio/basic", "au"));
		ft.add(new FileType("SundaHus WQ", "application/vnd.wqd", "wqd"));
		ft.add(new FileType("Symbian Install Package", "application/vnd.symbian.install", "sis"));
		ft.add(new FileType("Synchronized Multimedia Integration Language", "application/smil+xml", "smi"));
		ft.add(new FileType("SyncML", "application/vnd.syncml+xml", "xsm"));
		ft.add(new FileType("SyncML - Device Management", "application/vnd.syncml.dm+wbxml", "bdm"));
		ft.add(new FileType("SyncML - Device Management", "application/vnd.syncml.dm+xml", "xdm"));
		ft.add(new FileType("System V Release 4 CPIO Archive", "application/x-sv4cpio", "sv4cpio"));
		ft.add(new FileType("System V Release 4 CPIO Checksum Data", "application/x-sv4crc", "sv4crc"));
		ft.add(new FileType("Systems Biology Markup Language", "application/sbml+xml", "sbml"));
		ft.add(new FileType("Tab Seperated Values", "text/tab-separated-values", "tsv"));
		ft.add(new FileType("Tagged Image File Format", "image/tiff", "tiff"));
		ft.add(new FileType("Tao Intent", "application/vnd.tao.intent-module-archive", "tao"));
		ft.add(new FileType("Tar File (Tape Archive)", "application/x-tar", "tar"));
		ft.add(new FileType("Tcl Script", "application/x-tcl", "tcl"));
		ft.add(new FileType("TeX", "application/x-tex", "tex"));
		ft.add(new FileType("TeX Font Metric", "application/x-tex-tfm", "tfm"));
		ft.add(new FileType("Text Encoding and Interchange", "application/tei+xml", "tei"));
		ft.add(new FileType("Text File", "text/plain", "txt"));
		ft.add(new FileType("TIBCO Spotfire", "application/vnd.spotfire.dxp", "dxp"));
		ft.add(new FileType("TIBCO Spotfire", "application/vnd.spotfire.sfs", "sfs"));
		ft.add(new FileType("Time Stamped Data Envelope", "application/timestamped-data", "tsd"));
		ft.add(new FileType("TRI Systems Config", "application/vnd.trid.tpt", "tpt"));
		ft.add(new FileType("Triscape Map Explorer", "application/vnd.triscape.mxs", "mxs"));
		ft.add(new FileType("troff", "text/troff", "t"));
		ft.add(new FileType("True BASIC", "application/vnd.trueapp", "tra"));
		ft.add(new FileType("TrueType Font", "application/x-font-ttf", "ttf"));
		ft.add(new FileType("Turtle (Terse RDF Triple Language)", "text/turtle", "ttl"));
		ft.add(new FileType("UMAJIN", "application/vnd.umajin", "umj"));
		ft.add(new FileType("Unique Object Markup Language", "application/vnd.uoml+xml", "uoml"));
		ft.add(new FileType("Unity 3d", "application/vnd.unity", "unityweb"));
		ft.add(new FileType("Universal Forms Description Language", "application/vnd.ufdl", "ufd"));
		ft.add(new FileType("URI Resolution Services", "text/uri-list", "uri"));
		ft.add(new FileType("User Interface Quartz - Theme (Symbian)", "application/vnd.uiq.theme", "utz"));
		ft.add(new FileType("Ustar (Uniform Standard Tape Archive)", "application/x-ustar", "ustar"));
		ft.add(new FileType("UUEncode", "text/x-uuencode", "uu"));
		ft.add(new FileType("vCalendar", "text/x-vcalendar", "vcs"));
		ft.add(new FileType("vCard", "text/x-vcard", "vcf"));
		ft.add(new FileType("Video CD", "application/x-cdlink", "vcd"));
		ft.add(new FileType("Viewport+", "application/vnd.vsf", "vsf"));
		ft.add(new FileType("Virtual Reality Modeling Language", "model/vrml", "wrl"));
		ft.add(new FileType("VirtualCatalog", "application/vnd.vcx", "vcx"));
		ft.add(new FileType("Virtue MTS", "model/vnd.mts", "mts"));
		ft.add(new FileType("Virtue VTU", "model/vnd.vtu", "vtu"));
		ft.add(new FileType("Visionary", "application/vnd.visionary", "vis"));
		ft.add(new FileType("Vivo", "video/vnd.vivo", "viv"));
		ft.add(new FileType("Voice Browser Call Control", "application/ccxml+xml,", "ccxml"));
		ft.add(new FileType("VoiceXML", "application/voicexml+xml", "vxml"));
		ft.add(new FileType("WAIS Source", "application/x-wais-source", "src"));
		ft.add(new FileType("WAP Binary XML (WBXML)", "application/vnd.wap.wbxml", "wbxml"));
		ft.add(new FileType("WAP Bitamp (WBMP)", "image/vnd.wap.wbmp", "wbmp"));
		ft.add(new FileType("Waveform Audio File Format (WAV)", "audio/x-wav", "wav"));
		ft.add(new FileType("Web Distributed Authoring and Versioning", "application/davmount+xml", "davmount"));
		ft.add(new FileType("Web Open Font Format", "application/x-font-woff", "woff"));
		ft.add(new FileType("Web Services Policy", "application/wspolicy+xml", "wspolicy"));
		ft.add(new FileType("WebP Image", "image/webp", "webp"));
		ft.add(new FileType("WebTurbo", "application/vnd.webturbo", "wtb"));
		ft.add(new FileType("Widget Packaging and XML Configuration", "application/widget", "wgt"));
		ft.add(new FileType("WinHelp", "application/winhlp", "hlp"));
		ft.add(new FileType("Wireless Markup Language (WML)", "text/vnd.wap.wml", "wml"));
		ft.add(new FileType("Wireless Markup Language Script (WMLScript)", "text/vnd.wap.wmlscript", "wmls"));
		ft.add(new FileType("WMLScript", "application/vnd.wap.wmlscriptc", "wmlsc"));
		ft.add(new FileType("Wordperfect", "application/vnd.wordperfect", "wpd"));
		ft.add(new FileType("Worldtalk", "application/vnd.wt.stf", "stf"));
		ft.add(new FileType("WSDL - Web Services Description Language", "application/wsdl+xml", "wsdl"));
		ft.add(new FileType("X BitMap", "image/x-xbitmap", "xbm"));
		ft.add(new FileType("X PixMap", "image/x-xpixmap", "xpm"));
		ft.add(new FileType("X Window Dump", "image/x-xwindowdump", "xwd"));
		ft.add(new FileType("X.509 Certificate", "application/x-x509-ca-cert", "der"));
		ft.add(new FileType("Xfig", "application/x-xfig", "fig"));
		ft.add(new FileType("XHTML - The Extensible HyperText Markup Language", "application/xhtml+xml", "xhtml"));
		ft.add(new FileType("XML - Extensible Markup Language", "application/xml", "xml"));
		ft.add(new FileType("XML Configuration Access Protocol - XCAP Diff", "application/xcap-diff+xml", "xdf"));
		ft.add(new FileType("XML Encryption Syntax and Processing", "application/xenc+xml", "xenc"));
		ft.add(new FileType("XML Patch Framework", "application/patch-ops-error+xml", "xer"));
		ft.add(new FileType("XML Resource Lists", "application/resource-lists+xml", "rl"));
		ft.add(new FileType("XML Resource Lists", "application/rls-services+xml", "rs"));
		ft.add(new FileType("XML Resource Lists Diff", "application/resource-lists-diff+xml", "rld"));
		ft.add(new FileType("XML Transformations", "application/xslt+xml", "xslt"));
		ft.add(new FileType("XML-Binary Optimized Packaging", "application/xop+xml", "xop"));
		ft.add(new FileType("XPInstall - Mozilla", "application/x-xpinstall", "xpi"));
		ft.add(new FileType("XSPF - XML Shareable Playlist Format", "application/xspf+xml", "xspf"));
		ft.add(new FileType("XUL - XML User Interface Language", "application/vnd.mozilla.xul+xml", "xul"));
		ft.add(new FileType("XYZ File Format", "chemical/x-xyz", "xyz"));
		ft.add(new FileType("YAML Ain't Markup Language / Yet Another Markup Language", "text/yaml", "yaml"));
		ft.add(new FileType("YANG Data Modeling Language", "application/yang", "yang"));
		ft.add(new FileType("YIN (YANG - XML)", "application/yin+xml", "yin"));
		ft.add(new FileType("Z.U.L. Geometry", "application/vnd.zul", "zir"));
		ft.add(new FileType("Zip Archive", "application/zip", "zip"));
		ft.add(new FileType("ZVUE Media Manager", "application/vnd.handheld-entertainment+xml", "zmm"));
		ft.add(new FileType("Zzazz Deck", "application/vnd.zzazz.deck+xml", "zaz"));

		types = ft.toArray(new FileType[0]);
	}

	public static FileType getFileTypeByExtension(String extension) {

		if (extension == null) {
			return null;
		}

		FileType ret = null;

		for (FileType fileType : types) {
			if (extension.equalsIgnoreCase(fileType.getExtension())) {
				ret = new FileType(fileType.getName(), fileType.getMimetype(), fileType.getExtension());
			}
		}

		return ret;
	}

	public static FileType getFileTypeByMIMEType(String mimetype) {

		if (mimetype == null) {
			return null;
		}

		FileType ret = null;

		for (FileType fileType : types) {
			if (mimetype.equalsIgnoreCase(fileType.getMimetype())) {
				ret = new FileType(fileType.getName(), fileType.getMimetype(), fileType.getExtension());
			}
		}

		return ret;
	}

	public static String getCharset(File file) {

		String encoding = null;
		
		try {

			// (1)
			UniversalDetector detector = new UniversalDetector(null);

			FileInputStream fis = new FileInputStream(file);

			byte[] buf = new byte[1000];

			// (2)
			int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			// (3)
			detector.dataEnd();

			// (4)
			encoding = detector.getDetectedCharset();

			// (5)
			detector.reset();
			
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(encoding == null){
			encoding = "UTF-8";
		}
		
		return encoding;

	}
	
}
