package test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import co.htsoft.commons.lang.P;

import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.utils.ObjectJRDS;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.ReporteDiarioPagos;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.CargaClienteServicio;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.ReporteDiarioServicio;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JasperBuild {

	public static void main(String[] args) throws Throwable {

		// ---------------------------------
		TestUtils.startUp();

	//	P.println(ReporteDiarioServicio.generarReporte(48097));
		ReporteDiarioServicio.enviarEmailReporteDiarioPagos();
		return;
	}

}
