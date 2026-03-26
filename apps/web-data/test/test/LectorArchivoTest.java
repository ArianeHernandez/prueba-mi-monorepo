package test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import co.htsoft.commons.lang.P;
import co.htsoft.commons.net.CallPage;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.servicios.SQLServicio;
import com.osmosyscol.datasuite.mein.servicios.CalculoCategoriaTamanoServicio;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ListaValoresServicio;
import com.osmosyscol.datasuite.pagos.rest.Archivo;
import com.osmosyscol.datasuite.pagos.rest.IntegracionPagosREST;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.test.TestUtils;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.LectorArchivoPlanoBancolombiaPAB;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.LectorArchivoPlanoBogota;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.LectorArchivoPlanoDavivienda;
import com.osmosyscol.datasuite.webdata.correval.cargueplano.dto.Retiro;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.CargaCliente;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.ReporteDiarioPagos;
import com.osmosyscol.datasuite.webdata.correval.ordenespago.CargaClienteServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.Carga;
import com.osmosyscol.datasuite.webdata.logica.dto.NumeroValor;
import com.osmosyscol.datasuite.webdata.logica.dto.TablaOperacion;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;
import com.osmosyscol.datasuite.webdata.logica.servicios.FlujoCargaServicio;
import com.osmosyscol.datasuite.webdata.persistencia.dao.CargaDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class LectorArchivoTest {

	public static void main(String[] args) {

		TestUtils.startUp();

//		LectorArchivoPlanoBancolombiaPAB test = new LectorArchivoPlanoBancolombiaPAB();
//		List<Retiro> tester = test.leerRetiros("test/test/PAB.TXT");
//		P.println(tester);
		
//		LectorArchivoPlanoBogota test = new LectorArchivoPlanoBogota();
//		List<Retiro> tester = test.leerRetiros("test/test/Ejemplo Bogota.TXT");
//		P.println(tester);
		
//		LectorArchivoPlanoDavivienda test = new LectorArchivoPlanoDavivienda();
//		List<Retiro> tester = test.leerRetiros("test/test/Ejemplo Davivienda.TXT");
//		P.println(tester);
//		
//		BigDecimal suma = new BigDecimal(0);
//		
//		for (int i = 0; i < 3; i++) {
//			suma = suma.add(new BigDecimal(5000));
//		}
//		
//		
//		System.out.println(suma);
//		CallPage cp = new CallPage();
//		Archivo archivo = new Archivo(14934231);
//		P.println(cp.callJSON("http://127.0.0.1:8080/WebData"+ "/pagos/obtenerNumeroCuenta", archivo, com.osmosyscol.datasuite.pagos.rest.NumeroCuenta.class));
	
//		 NumeroValor test = CargaServicio.getInstance().obtenerDatosExcel(106851);
//		 P.println(test);
		
//		List<Carga> test = CargaServicio.getInstance().listarCargasDuplicadas(106860, "DFRQIBRGJUSSTWUJFXCLRFHMREUDABVXCDQBEMWIMXSSFACGYJCJCSTFMAHMREUDABVXBWCmCeD6BrAHCUD.CzAAA0DYC6A4B0B1", "IUGUDCDYNLWTJIMAHMREUDABVXBFASTFMAHMREUDABWCBpC9C6AcDECOBBCZAPBmCnAUAtCGABCu");
//		
//		P.println(test);
		
//		List<TablaOperacion> tabla = CargaServicio.getInstance().generarTablaControl(106849);
//		P.println(tabla);
//		DaoManager daoManager;
//		try {
//			daoManager = DaoConfig.getDaoManager();
//			
//			CargaDao cargaDao = (CargaDao) daoManager.getDao(CargaDao.class);
//			List<Carga> cargas = cargaDao.obtenerCargasLiberacion(Constantes.CARGA_ESTADO_ENVIO, new Date());
//			P.println(cargas);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Integer total = FlujoCargaServicio.getInstance().liberarCargas();
//		P.println(total);
//		Integer total = CargaServicio.getInstance().asociarCargasAInstanciasDeProceso();
//		P.println(total);
//		List<CargaCliente> test = ReporteRetirosClienteServicio.consultaCargasPorCliente(48097);
//		P.println(test);
//		List<String> test1 = ReporteRetirosClienteServicio.consultaRespuestaBanco(48097);
//		P.println(test1);
		
//		ReporteDiarioPagos test = CargaClienteServicio.obtenerReporteDiario(48097);
//		P.println(test);
		
//		obtenerSociedad("1122334455");
		
//		System.out.println(CalculoCategoriaTamanoServicio.getInstance().calcularCatEmpresa(new BigDecimal("100000000000")));
	}
	
	public static Map<String, Object> obtenerSociedad(String identificacion){
		String sql_cr_persona = "SELECT $PERSONA$.ID FROM $PERSONA$ WHERE $PERSONA.TIPO IDENTIFICACION$ = $S(N)$ AND $PERSONA.IDENTIFICACION$ = $S(" + identificacion + ")$";
		
		System.out.println("--..--..--..--..--..--..--..--..--..--..--..--..--..-.-.-.--");
		System.out.println("sql_cr: ->" + sql_cr_persona);
		System.out.println("--..--..--..--..--..--..--..--..--..--..--..--..--..-.-.-.--");
		
		Integer id_cr_persona = DS_SqlUtils.queryForObject(Integer.class, sql_cr_persona);
		
		if(id_cr_persona != null){
			String sql = "SELECT $SOCIEDAD.ACTIVIDAD ECONOMICA$ AS CIIU, "
					+ "$SOCIEDAD.MACROSECTOR$ AS MACROSECTOR, "
					+ "$SOCIEDAD.DEPARTAMENTO DANE$ AS DEPARTAMENTO "
					+ "$SOCIEDAD.MUNICIPIO DANE$ AS MUNICIPIO "
					+ "FROM $SOCIEDAD$ WHERE $SOCIEDAD.DATOS BASICOS$ = " + id_cr_persona;
			
			String sql_nuevo = RDServicio.reemplazarNombres(sql);
			
			List<Map<String, Object>> resp = CargaServicio.getInstance().selectSql(sql_nuevo);
			
			resp = SQLServicio.desencriptarMapaStringFormat(resp);
			
			System.out.println("--..--..--..--..--..--..--..--..--..--..--..--..--..-.-.-.--");
			System.out.println("sql: ->" + sql);
			System.out.println("sql_nuevo: ->" + sql_nuevo);
			System.out.println("resp: ->" + resp);
			System.out.println("--..--..--..--..--..--..--..--..--..--..--..--..--..-.-.-.--");
			
			if(resp != null && resp.size() > 0){
				return resp.get(0);
			}
		}
		
		return null;
		
	}

}
