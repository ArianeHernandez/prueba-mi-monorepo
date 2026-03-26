package com.osmosyscol.datasuite.logica.constantes;

import java.util.HashMap;
import java.util.Map;

public class ConstantesNombreArchivos {
	private static ConstantesNombreArchivos constantesNombreArchivos;
	public static Map<String, Integer> listadoArchivos = new HashMap<String, Integer>();
	public static String url;
	
	private static int contador = 100;
	
	public static ConstantesNombreArchivos getInstance(){
		
		if (constantesNombreArchivos == null){
			constantesNombreArchivos = new ConstantesNombreArchivos();
			
			listadoArchivos.put("Certificado de Existencia y Representación Legal",1);
			listadoArchivos.put("Certificado de existencia y representacion legal",1);
			listadoArchivos.put("Archivo CERTIFICADO DE EXISTENCIA",1);
			listadoArchivos.put("Poder al abogado para realizar la solicitud",2);
			listadoArchivos.put("Archivo Poder al abogado para realizar la solicitud",2);
			listadoArchivos.put("Poder al abogado para realizar la solicitud (Si aplica)",2);
			listadoArchivos.put("Archivo Poder al abogado para realizar la solicitud",2);
			listadoArchivos.put("Memoria explicativa de las causas de insolvencia",3);
			listadoArchivos.put("Archivo memoria explicativa",3);
			listadoArchivos.put("Certificación que acredite la cesación de pagos",4);
			listadoArchivos.put("Certificado cesacion pagos",4);
			listadoArchivos.put("Archivo Certificación  cesación de pagos",4);
			listadoArchivos.put("Anexo de la certificación de cesación de pagos",5);
			listadoArchivos.put("Archivo Anexo de la certificación de cesación de pagos",5);
			listadoArchivos.put("Anexo cesacion pagos",5);			
			listadoArchivos.put("Certificación que acredite la incapacidad de pago inminente",6);
			listadoArchivos.put("Certificado incapacidad pago",6);			
			listadoArchivos.put("Certificación de tener o no pasivos por retenciones obligatorias con el fisco",7);
			listadoArchivos.put("Archivo Certificación con el fisco",7);
			listadoArchivos.put("Plan para la atención de pasivos por retenciones obligatorias con el fisco",8);
			listadoArchivos.put("Plan para la atención de dichos pasivos",8);
			listadoArchivos.put("Certificación de tener o no pasivos por descuentos efectuados a trabajadores",9);
			listadoArchivos.put("Certificación de tener o no tener pasivos por descuentos efectuados a trabajadores",9);
			listadoArchivos.put("Archivo Certificación de tener o no tener pasivos por descuentos efectuados a trabajadores",9);
			listadoArchivos.put("Plan para la atención de pasivos por descuentos efectuados a trabajadores",10);
			listadoArchivos.put("Certificación de tener o no pasivos por aportes al sistema de seguridad social",11);
			listadoArchivos.put("Archivo Certificación de tener o no tener pasivos por aportes al sistema de seguridad social",11);
			listadoArchivos.put("Plan para la atención de pasivos por aportes al sistema de seguridad social",12);
			listadoArchivos.put("Plan para la atención de pasivos al sistema de seguridad social",12);	
			listadoArchivos.put("Archivo Plan para la atención de pasivos al sistema de seguridad social",12);	
			listadoArchivos.put("Estados financieros del mes anterior (ESF, ERI, EFE, ORI, ECP)",13);
			listadoArchivos.put("Archivo Estados financieros del mes anterior (ESF, ER, EFE, ORI, ECP)",13);
			
			listadoArchivos.put("Notas a estados financieros del mes anterior",14);			
			listadoArchivos.put("Estados financieros del último año",15);
			listadoArchivos.put("Notas a estados financieros del último año",16);			
			listadoArchivos.put("Dictamen del revisor fiscal del último año",17);			
			listadoArchivos.put("Estados financieros del penúltimo año",18);
			listadoArchivos.put("Notas a estados financieros del penúltimo año",19);
			listadoArchivos.put("Archivo Notas penultimo año",19);
			listadoArchivos.put("Archivo Notas penultimo año",19);
			listadoArchivos.put("Dictamen del revisor fiscal del penúltimo año",20);
			listadoArchivos.put("Estados financieros del antepenúltimo año",21);
			listadoArchivos.put("Notas a estados financieros del antepenúltimo año",22);
			listadoArchivos.put("Archivo Notas antepenultimo aÃ±o",22);
			listadoArchivos.put("Archivo Notas antepenultimo año",22);
			listadoArchivos.put("Dictamen del revisor fiscal del antepenúltimo año",23);
			listadoArchivos.put("Inventario de activos del mes anterior",24);
			listadoArchivos.put("Archivo Inventario de activos del mes anterior",24);
			listadoArchivos.put("Inventario de pasivos del mes anterior",25);
			listadoArchivos.put("Archivo Inventario de pasivos del mes anterior",25);
			listadoArchivos.put("Archivo pasivos mes anterior",25);			
			listadoArchivos.put("Certificación de llevar contabilidad regular",26);
			listadoArchivos.put("Archivo Certificación de llevar contabilidad regular",26);
			listadoArchivos.put("Certificación de llevar la contabilidad regular",26);			
			listadoArchivos.put("Certificación causal de disolución",27);
			listadoArchivos.put("Certificación de no encontrarse en causal de disolución",28);
			listadoArchivos.put("Archivo Certificacion no causal",28);
			listadoArchivos.put("Certificación del monto de las obligaciones causadas por cálculo actuarial",29);
			listadoArchivos.put("Documento que acredita el cálculo actuarial",30);
			listadoArchivos.put("Aprobación del cálculo actuarial",31);
			listadoArchivos.put("Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales",32);
			listadoArchivos.put("Certificación en la que se relacione los terceros a los cuales el deudor es (avalista, garante o codeudor de terceros)",33);
			listadoArchivos.put("Plan de negocios de reorganización",34);
			listadoArchivos.put("Archivo Plan de negocios de reorganización",34);
			listadoArchivos.put("Archivo Plan de negocios de reorganizaciÃ³n",34);
			listadoArchivos.put("Flujo de caja proyectado",35);
			listadoArchivos.put("Archivo Flujo de caja",35);
			listadoArchivos.put("Proyecto de calificación y graduación de créditos y proyecto de determinación de derechos de voto",36);
			listadoArchivos.put("Archivo Proyecto de calificación",36);
			listadoArchivos.put("Certificación de bienes sujetos a garantías mobiliarias e indicar cuáles de los mismos son necesarios para la actividad del deudor",37);
			listadoArchivos.put("Avalúo de los bienes",38);
			listadoArchivos.put("Valor de los bienes gravados con garantía",38);
			listadoArchivos.put("Certificación que relacione las obligaciones que surgen de la adquisición del bien sujeto a garantía",39);
			listadoArchivos.put("Certificación de garantía mobiliaria",39);
			
			
			url = "https://mein.test.supersociedades.htsoft.co/WebData//WebData/adjunto.bajararchivo";
		}
		return constantesNombreArchivos;
	}
	
	public Integer getIdArchivo(String descripcion){
		if (null ==  listadoArchivos.get(descripcion)) {
			contador++;
			return contador;
		}
		return listadoArchivos.get(descripcion);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		ConstantesNombreArchivos.url = url;
	}
	
	public Integer getContador() {
		return contador++;
	}
	
	
	
//	String FILE_1 = "Certificado de Existencia y Representación Legal";
//	String FILE_2 = "Poder al abogado para realizar la solicitud";
//	String FILE_3 = "Memoria explicativa de las causas de insolvencia";
//	String FILE_4 = "Certificación que acredite la cesación de pagos";
//	String FILE_5 = "Anexo de la certificación de cesación de pagos";
//	String FILE_6 = "Certificación que acredite la incapacidad de pago inminente";
//	String FILE_7 = "Certificación de tener o no pasivos por retenciones obligatorias con el fisco";
//	String FILE_8 = "Plan para la atención de pasivos por retenciones obligatorias con el fisco";
//	String FILE_9 = "Certificación de tener o no pasivos por descuentos efectuados a trabajadores";
//	String FILE_10 = "Plan para la atención de pasivos por descuentos efectuados a trabajadores";
//	String FILE_11 = "Certificación de tener o no pasivos por aportes al sistema de seguridad social";
//	String FILE_12 = "Plan para la atención de pasivos por aportes al sistema de seguridad social";
//	String FILE_13 = "Estados financieros del mes anterior (ESF, ERI, EFE, ORI, ECP)";
//	String FILE_14 = "Notas a estados financieros del mes anterior";
//	String FILE_15 = "Estados financieros del último año";
//	String FILE_16 = "Notas a estados financieros del último año";
//	String FILE_17 = "Dictamen del revisor fiscal del último año";
//	String FILE_18 = "Estados financieros del penúltimo año";
//	String FILE_19 = "Notas a estados financieros del penúltimo año";
//	String FILE_20 = "Dictamen del revisor fiscal del penúltimo año";
//	String FILE_21 = "Estados financieros del antepenúltimo año";
//	String FILE_22 = "Notas a estados financieros del antepenúltimo año";
//	String FILE_23 = "Dictamen del revisor fiscal del antepenúltimo año";
//	String FILE_24 = "Inventario de activos del mes anterior";
//	String FILE_25 = "Inventario de pasivos del mes anterior";
//	String FILE_26 = "Certificación de llevar contabilidad regular";
//	String FILE_27 = "Certificación causal de disolución";
//	String FILE_28 = "Certificación de no encontrarse en causal de disolución";
//	String FILE_29 = "Certificación del monto de las obligaciones causadas por cálculo actuarial";
//	String FILE_30 = "Documento que acredita el cálculo actuarial";
//	String FILE_31 = "Aprobación del cálculo actuarial";
//	String FILE_32 = "Certificación de encontrarse al día en mesadas pensionales, bonos y títulos pensionales";
//	String FILE_33 = "Certificación en la que se relacione los terceros a los cuales el deudor es (avalista, garante o codeudor de terceros)";
//	String FILE_34 = "Plan de negocios de reorganización";
//	String FILE_35 = "Flujo de caja proyectado";
//	String FILE_36 = "Proyecto de calificación y graduación de créditos y proyecto de determinación de derechos de voto";
//	String FILE_37 = "Certificación de bienes sujetos a garantías mobiliarias e indicar cuáles de los mismos son necesarios para la actividad del deudor";
//	String FILE_38 = "Avalúo de los bienes";
//	String FILE_39 = "Certificación que relacione las obligaciones que surgen de la adquisición del bien sujeto a garantía";

}
