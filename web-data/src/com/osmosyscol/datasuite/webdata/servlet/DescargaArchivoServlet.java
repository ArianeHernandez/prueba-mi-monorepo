package com.osmosyscol.datasuite.webdata.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.servlet.ContextInfo;
/**
 * Servlet implementation class DescargaArchivoServlet
 */

@WebServlet(urlPatterns={"/DescargaArchivoServlet"})
public class DescargaArchivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static Map<Integer, File> archivos = new HashMap<Integer, File>();
	
	// DFRODRIGUEZ: CAMBIO PARA DESCARGA DE PLANTILLAS DE DOCUMENTOS ADJUNTOS MEIN
	private static final Integer NUMERO_PLANTILLAS = 100;
//	public static int id_archivo = 1;
	public static int id_archivo = NUMERO_PLANTILLAS; // CON ESTE CAMBIO, SE PUEDEN REGISTRAR HASTA 20 PLANTILLAS
	// DFRODRIGUEZ: CAMBIO PARA DESCARGA DE PLANTILLAS DE DOCUMENTOS ADJUNTOS MEIN
	
    /**
     * @see HttpServlet#HttpServlet()
     */

    public DescargaArchivoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
 	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			downloadFile(session, request, response);
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
	}
	
	private void downloadFile(HttpSession session, HttpServletRequest request, HttpServletResponse response ) throws IOException{
		System.out.println("DescargaArchivoServlet: id_archivo : " + request.getParameter("id_archivo"));
		
		// DFRODRIGUEZ: CAMBIO PARA DESCARGA DE PLANTILLAS DE DOCUMENTOS ADJUNTOS MEIN
//		File downloadFile = obtenerArchivo(Integer.valueOf(request.getParameter("id_archivo")));
		Integer id_archivo = Integer.valueOf(request.getParameter("id_archivo"));
		
		File downloadFile = null;
		
		if(id_archivo.compareTo(NUMERO_PLANTILLAS) < 0){
			// ES UNA PLANTILLA
			switch(id_archivo){
				case 0:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/CertificacionCausalDisolucion.pdf"));
					break;
				case 1:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/CertificacionMarcoContabilidad.pdf"));
					break;
				case 2:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/CertificacionPasivosPensionales.pdf"));
					break;
				case 3:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/CertificacionRepLegal.pdf"));
					break;
				case 4:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/CertificacionRtencionesTrabaSeguridadSo.pdf"));
					break;
				case 5:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/NEAR-PlantillaFlujoProyectado.xlsx"));
					break;
				case 6:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/NEAR-PlantillaInventarioActivos.xlsx"));
					break;
				case 7:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/NEAR-PlantillaProcesosJudiciales.xlsx"));
					break;
				case 8:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/NEAR-PlantillaProyectoCalificacion.xlsx"));
					break;
				case 9:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/NEAR-PlantillaRelacionPasivo.xlsx"));
					break;
				case 10:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/NEAR-PlantillaMemoriaExplicativa.docx"));
					break;
				case 11:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/Plantillas.zip"));
					break;
				case 12:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/Ejemplo.zip"));
					break;
				case 13:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/1_Plantilla_Memoria_Explicativa.pdf"));
					break;
				case 14:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/10_Plantilla_de_Codeudor_Garante_o_Avalista.pdf"));
					break;
				case 15:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/11. Plantilla Flujo de Caja Proyectado.xlsx"));
					break;
				case 16:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/R12. Plantilla Proyecto de calificacion, graduacion y derechos de voto.xlsx"));
					break;
				case 17:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/13_Plantilla_bienes_dados_en_garantia.pdf"));
					break;
				case 18:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/13.1_Plantilla_Obligaciones_derivadas_de_los_bienes_dados_en_garantia.pdf"));
					break;
				case 19:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/2_Plantilla_Cesacion_de_Pagos.pdf"));
					break;
				case 20:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/2.1 Anexo - Certificacion Cesacion de Pagos.xlsx"));
					break;
				case 21:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/3_Plantilla_Incapacidad_de_pago_inminente.pdf"));
					break;
				case 22:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/4.1_Certificacion_de_Pasivos_por_descuentos_a_trabajadores.pdf"));
					break;
				case 23:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/4.1.1_Plan_de_Pagos_de_Pasivos_por_descuentos_a_trabajadores.pdf"));
					break;
				case 24:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/4.2_Certificacion_de_Pasivos_por_retenciones_obligatorias_fisco.pdf"));
					break;
				case 25:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/4.2.1_Plan_de_Pagos_de_Pasivos_por_retenciones_obligatorias_fisco.pdf"));
					break;
				case 26:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/4.3_Certificacion_de_Pasivos_por_aportes_al_sistema_de_ss.pdf"));
					break;
				case 27:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/4.3.1_Plan_de_Pagos_de_Pasivos_por_aportes_al_sistema_de_ss.pdf"));
					break;
				case 28:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/5. Plantilla Inventario de Activos.xlsx"));
					break;
				case 29:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/6. Plantilla Inventario de pasivos.xlsx"));
					break;
				case 30:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/7_Plantilla_Certificacion_Contabilidad_Regular.pdf"));
					break;
				case 31:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/8_Plantilla_Certificacion_Causal_de_Disolucion.pdf"));
					break;
				case 32:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/8.1_Plantilla_Certificacion_en_No_Causal_de_Disolucion.pdf"));
					break;
				case 33:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/sociedad/9_Plantilla_de_Mesadas_Pesionales_Bonos.pdf"));
					break;
				case 34:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/10_Plantilla_de_Codeudor_Garante_o_Avalista.pdf"));
					break;
				case 35:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/11. Plantilla Flujo de Caja Proyectado.xlsx"));
					break;
				case 36:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/12. Plantilla Proyecto de calificacion, graduacion y derechos de voto.xlsx"));
					break;
				case 37:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/13.1_Plantilla_Obligaciones_derivadas_de_los_bienes_dados_en_garantia.pdf"));
					break;
				case 38:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/13_Plantilla_bienes_dados_en_garantia.pdf"));
					break;
				case 39:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/1_Plantilla_Memoria_Explicativa.pdf"));
					break;
				case 40:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/2.1 Anexo - Certificacion Cesacion de Pagos.xlsx"));
					break;
				case 41:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/2_Plantilla_Cesacion_de_Pagos.pdf"));
					break;
				case 42:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/4.1.1_Plan_de_Pagos_de_Pasivos_por_descuentos_a_trabajadores.pdf"));
					break;
				case 43:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/4.1_Certificacion_de_Pasivos_por_descuentos_a_trabajadores.pdf"));
					break;
				case 44:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/4.2.1_Plan_de_Pagos_de_Pasivos_por_retenciones_obligatorias_fisco.pdf"));
					break;
				case 45:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/4.2_Certificacion_de_Pasivos_por_retenciones_obligatorias_fisco.pdf"));
					break;
				case 46:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/4.3.1_Plan_de_Pagos_de_Pasivos_por_aportes_al_sistema_de_ss.pdf"));
					break;
				case 47:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/4.3_Certificacion_de_Pasivos_por_aportes_al_sistema_de_ss.pdf"));
					break;
				case 48:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/5. Plantilla Inventario de Activos.xlsx"));
					break;
				case 49:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/6. Plantilla Inventario de pasivos.xlsx"));
					break;
				case 50:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/7_Plantilla_Certificacion_Contabilidad_Regular.pdf"));
					break;
				case 51:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNC/9_Plantilla_de_Mesadas_Pesionales_Bonos.pdf"));
					break;
				case 52:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNNoC/12. Plantilla Proyecto de calificacion, graduacion y derechos de voto.xlsx"));
					break;
				case 53:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/PNNoC/1_Plantilla_Memoria_Explicativa.pdf"));
					break;
				case 54:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/Manuales de Usuario.zip"));
					break;
				case 55:
					downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource("componentes/solicitud_NEAR/Manuales Operativos.zip"));
					break;
					
				
			}
		}else{
			downloadFile = obtenerArchivo(id_archivo);
		}
		// DFRODRIGUEZ: CAMBIO PARA DESCARGA DE PLANTILLAS DE DOCUMENTOS ADJUNTOS MEIN
       
        FileInputStream inStream = new FileInputStream(downloadFile);
        
        String mimeType = "application/octet-stream";
        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
         
        // forces download
        
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
         
        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        inStream.close();
        outStream.flush();
        outStream.close();
	}
	
	public static int almacenarArchivo(File archivo){
		System.out.println(id_archivo);
		archivos.put(id_archivo, archivo);
		return id_archivo++;
	}
	
	public static File obtenerArchivo(Integer id){
		return archivos.remove(id);
	}

}
