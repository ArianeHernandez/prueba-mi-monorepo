package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Mensaje;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.RegistroEntradaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.SalidaBanco;
import com.osmosyscol.datasuite.webdata.correval.bancos.salida.generadores.GeneradorColpatriaXML;

public class GeneradorColpatriaXMLTest {
	public static void main(String[] args) throws Throwable {
		InitApp.startUp();
		
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
