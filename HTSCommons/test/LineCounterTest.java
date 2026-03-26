import java.io.File;

import co.htsoft.commons.file.LineCounter;
import co.htsoft.commons.lang.P;

public class LineCounterTest {

	private static String[] extensiones = new String[] { "java", "xml", "xslt", "xsl", "xmap", "xconf", "jsp", "xsp", "js", "html", "css", "sql", "fnc", "pck", "prc", "seq", "tps", "vw", "properties", "conf" };

	public static void main(String[] args) {

		misPesos();
		// bancoTerceros();
		bancoProveedores();

	}

	private static void misPesos() {

		System.out.println("HTSCommons");
		P.println(LineCounter.count(new File("../HTSCommons"), true, extensiones));

		System.out.println("MisPesosWeb");
		P.println(LineCounter.count(new File("../MisPesosWeb"), true, extensiones));

		System.out.println("MisPesosSkin/powwi");
		P.println(LineCounter.count(new File("../MisPesosSkin/powwi"), true, extensiones));

		System.out.println("MisPesosJsonServer");
		P.println(LineCounter.count(new File("../MisPesosJsonServer"), true, extensiones));

		System.out.println("SIPMPesos");
		P.println(LineCounter.count(new File("../SIPMPesos"), true, extensiones));

		System.out.println("SIPMPesosClient");
		P.println(LineCounter.count(new File("../SIPMPesosClient"), true, extensiones));

		System.out.println("MisPesosBD");
		P.println(LineCounter.count(new File("../MisPesosBD"), true, extensiones));

		System.out.println("HTS_SPACore");
		P.println(LineCounter.count(new File("../HTS_SPACore"), true, extensiones));

		System.out.println("HTS_SPAServerCore");
		P.println(LineCounter.count(new File("../HTS_SPAServerCore"), true, extensiones));

		System.out.println("HTS_QRCode");
		P.println(LineCounter.count(new File("../HTS_QRCode"), true, extensiones));

		System.out.println("HTSAuditoria");
		P.println(LineCounter.count(new File("../HTSAuditoria"), true, extensiones));

		System.out.println("HTSAuditoriaClient");
		P.println(LineCounter.count(new File("../HTSAuditoriaClient"), true, extensiones));

		System.out.println("HTSSarlaft");
		P.println(LineCounter.count(new File("../HTSSarlaft"), true, extensiones));

		System.out.println("RecargasPTM");
		P.println(LineCounter.count(new File("../RecargasPTM"), true, extensiones));

		System.out.println("ValidacionPersona");
		P.println(LineCounter.count(new File("../ValidacionPersona"), true, extensiones));

		System.out.println("ValidacionPersonaCifin");
		P.println(LineCounter.count(new File("../ValidacionPersonaCifin"), true, extensiones));

		System.out.println("ValidacionPersonaClient");
		P.println(LineCounter.count(new File("../ValidacionPersonaClient"), true, extensiones));

	}

	private static void bancoTerceros() {

		System.out.println("BancoTercerosBD");
		P.println(LineCounter.count(new File("../BancoTercerosBD"), true, extensiones));

		System.out.println("BancoTercerosServer");
		P.println(LineCounter.count(new File("../BancoTercerosServer"), true, extensiones));

		System.out.println("BancoTercerosWeb");
		P.println(LineCounter.count(new File("../BancoTercerosWeb"), true, extensiones));

		System.out.println("HTS_LDAP");
		P.println(LineCounter.count(new File("../HTS_LDAP"), true, extensiones));

		System.out.println("HTS_SearchEngineProviders");
		P.println(LineCounter.count(new File("../HTS_SearchEngineProviders"), true, extensiones));

		System.out.println("MapaCoberturaProveedores");
		P.println(LineCounter.count(new File("../MapaCoberturaProveedores"), true, extensiones));

		System.out.println("OsmoAutenticador");
		P.println(LineCounter.count(new File("../OsmoAutenticador"), true, extensiones));

		System.out.println("OsmoAutenticadorClient");
		P.println(LineCounter.count(new File("../OsmoAutenticadorClient"), true, extensiones));

		System.out.println("OsmoCore");
		P.println(LineCounter.count(new File("../OsmoCore"), true, extensiones));

		System.out.println("SIPBTerceros");
		P.println(LineCounter.count(new File("../SIPBTerceros"), true, extensiones));

		System.out.println("SIPBTercerosClient");
		P.println(LineCounter.count(new File("../SIPBTercerosClient"), true, extensiones));
	}

	private static void bancoProveedores() {

		System.out.println("HTSCommons");
		P.println(LineCounter.count(new File("../MisPesosWeb"), true, extensiones));

	}

}
