package test;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.osmosyscol.commons.utils.JavaToXML;
import com.osmosyscol.commons.utils.XMLFormat;
import com.osmosyscol.datasuite.bpm.dto.ApVistaIdentificacionTiposDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaMedioEnvioDto;
import com.osmosyscol.datasuite.bpm.dto.ApVistaSeguridadTipoDto;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDocumentoTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.servicios.RadicacionAutoOficioServicio;
import com.osmosyscol.datasuite.mein.servicios.rest.client.SigsAppRestClient;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RadicacionAutoOficioTest {
	public static void main(String[] args) {
		InitApp.startUp();
		
//		obtenerCuadernoTest();
//		obtenerDependenciaTest();
//		obtenerDocumentoTipoTest();
//		obtenerIdentificacionTiposTest();
//		obtenerSeguridadTipoTest();
//		obtenerApVistaTramiteTest();
//		obtenerRadicacionAutoOficioTest();
//		guardarRadicacionAutoOficioTest();
		obtenerCorresponsalDependencia();
		System.exit(0);
	}
	
	public static void obtenerCuadernoTest(){
		List<APVistaCuaderno> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaCuaderno();
		
		System.out.print(vCuaderno.get(0).getCuadernoTipo());
		
	}
	
	public static void obtenerDependenciaTest(){
		List<APVistaDependencias> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaDependencia();
		
		System.out.print(vCuaderno.get(0).getCodigo());
		
	}
	
	public static void obtenerDocumentoTipoTest(){
		List<APVistaDocumentoTipo> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaDocumentoTipo();
		
		System.out.print(vCuaderno.get(0).getDocumento_tipo());
		
	}
	
	public static void obtenerIdentificacionTiposTest(){
		List<ApVistaIdentificacionTiposDto> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaIdentificacionTipos();
		
		System.out.print(vCuaderno.get(0).getCodigo_alfanumerico());
		
	}
	
	public static void obtenerMedioEnvioTest(){
		List<ApVistaMedioEnvioDto> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaMedioEnvio();
		
		System.out.print(vCuaderno.get(0).getMedio_envio());
		
	}
	public static void obtenerSeguridadTipoTest(){
		List<ApVistaSeguridadTipoDto> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaSeguridadTipo();
		
		System.out.print(vCuaderno.get(0).getSeguridad_tipo());
		
	}
	
	public static void obtenerApVistaTramiteTest(){
		List<APVistaTramite> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerApVistaTramite();
		
		System.out.print(vCuaderno.get(0).getNombreTramite());
		
	}
	
	public static void obtenerRadicacionAutoOficioTest(){
		List<RadicacionAutoOficio> vCuaderno = RadicacionAutoOficioServicio.getInstance().obtenerRadicacionAutoOficio();
		
		System.out.print(vCuaderno.get(0).getLoginUsuario());
		
	}
	
	public static void guardarRadicacionAutoOficioTest(){
		RadicacionAutoOficio solicitud = new RadicacionAutoOficio();
		solicitud.setAnexosFisicos(1);
		solicitud.setDeudorId(15161324);
		solicitud.setEntregaFisica("0");
		solicitud.setExtensionArchivo(".pdf");
		solicitud.setFoliosNumero(5);
		solicitud.setLoginUsuario("test");
		solicitud.setFuncionarioId("1016031266");
		solicitud.setFuncionarioAsignadoId("151515151");
		solicitud.setReferenciaExterna("2020-INS-1475-MI-110890");
		solicitud.setParticularId(15160000);
		solicitud.setTipo_radicado(1);
		solicitud.setNumeroRadicado("2020-01-207283");
		solicitud.setCorresponsalDependenciaId(15160032);//falta guardar al corresponsal
		solicitud.setDependenciaId(15160032);
		solicitud.setDependenciaAsignadaId(15160032);
		solicitud.setCodigoTipoSeguridadId(15159899);
		solicitud.setCodigoMedioEnvioId(15159925);
		solicitud.setDocumentalTipoId(15159863);
		solicitud.setCuadernoTipoId(15160181);
		solicitud.setAsunto("test");
		solicitud.setCumplimiento("test");
		solicitud.setTermino_dias("test");
		solicitud.setIdcarga(111477);
		
		Boolean vCuaderno = RadicacionAutoOficioServicio.getInstance().guardarRadicacionAutoOficio(solicitud);
		
		System.out.print(vCuaderno);
		
	}
	
	public static void obtenerCorresponsalDependencia() {
		Integer dependencia = 15160055;
		
		Integer corresponsalDependencia = RadicacionAutoOficioServicio.getInstance().obtenerCorresponsalDependencia(dependencia);
		
		APVistaDependencias dependenciaObj = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select * from $apvista dependencias$ where id = " + corresponsalDependencia);
		
		System.out.println(XMLFormat.format(JavaToXML.exe("Dependencia", dependenciaObj)));
	}
}
