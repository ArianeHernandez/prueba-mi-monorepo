package test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.IPorcentaje;
import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorColpatriaXML;
import com.osmosyscol.datasuite.webdata.correval.grupogiro.GrupoGiroServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosGenerados;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio.Filtro;
import com.osmosyscol.datasuite.webdata.correval.servicios.ReportePagosServicio.Pago;

import co.htsoft.commons.lang.P;

public class ReportePagosTest {

	public static void main(String[] args) throws Throwable {

		InitApp.startUp();

		/*
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Pago> pagos = ReportePagosGenerados.consultaPagos(sdf.parse("11/07/2017"), sdf.parse("12/07/2017")); 
		P.println(pagos); 
		File f = ReportePagosGenerados.generarReporte(pagos);
		P.println(f);
		*/
		
		/*Filtro filtro = new Filtro();
		filtro.setId_cliente(48097);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		filtro.setFecha_desde(sdf.parse("1/08/2017"));
		filtro.setFecha_hasta(sdf.parse("30/08/2017"));
		filtro.setId_carga(106755);
		P.println(filtro);
		Filtro filtro2 = new Filtro();
		List<Pago> pagos = ReportePagosServicio.consultaPagos(filtro2);
		P.println(pagos);
		File f = ReportePagosServicio.generarReporte(pagos);
		P.println(f);
		*/
		
		GeneradorColpatriaXML generador = new GeneradorColpatriaXML();
		SalidaBanco salidabanco = new SalidaBanco();
		String cod_banco_empresa = "00";
		salidabanco.setCod_banco_empresa(cod_banco_empresa);
		String codigo_oficina_empresa = "2";
		salidabanco.setCodigo_oficina_empresa(codigo_oficina_empresa);
		String direccion_empresa = "dirEmp";
		salidabanco.setDireccion_empresa(direccion_empresa);
		Date fecha_grupo = new Date();
		salidabanco.setFecha_grupo(fecha_grupo);
		Date fecha_hoy = new Date();
		salidabanco.setFecha_hoy(fecha_hoy);
		Long nit_empresa = new Long( "8909316099");
		salidabanco.setNit_empresa(nit_empresa);
		String nombre_empresa = "DANILO";
		salidabanco.setNombre_empresa(nombre_empresa);
		Integer num_cargadeldia = 11;
		salidabanco.setNum_cargadeldia(num_cargadeldia);
		Long num_cuenta_empresa = new Long("601037842");
		salidabanco.setNum_cuenta_empresa(num_cuenta_empresa);
		Long num_cuenta_principal_empresa = new Long("601037842");
		salidabanco.setNum_cuenta_principal_empresa(num_cuenta_principal_empresa);
		List<RegistroEntradaBanco> registros = new ArrayList<RegistroEntradaBanco>();
		
		//registros.add(registro1);
		salidabanco.setRegistro(registros);
		String tipo_cuenta_empresa = "CC";
		salidabanco.setTipo_cuenta_empresa(tipo_cuenta_empresa);
		Mensaje mensaje = new Mensaje();
		mensaje.setPorcentaje(1);
		System.out.println(generador.generar(salidabanco, mensaje).toString());
		
		System.exit(0);
	}
}
