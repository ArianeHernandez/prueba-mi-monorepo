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
import com.osmosyscol.datasuite.mein.dtos.Deudor;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;
/**
 * Servlet implementation class DescargaArchivoServletRI
 */

@WebServlet(urlPatterns={"/DescargaArchivoServletRI"})
public class DescargaArchivoServletRI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static Map<Integer, File> archivos = new HashMap<Integer, File>();
	
	private static final Integer NUMERO_PLANTILLAS = 100;
	public static int id_archivo = NUMERO_PLANTILLAS; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */

    public DescargaArchivoServletRI() {
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
	
	public String getNombrePlantilla(int tipo_proceso, int tipo_solicitante, int num_plantilla){
		String nombrePlantilla = null;
		String consulta = "select $plantilla solicitud.nombre plantilla$ "
				+ "from $plantilla solicitud$ where "
				+ "$plantilla solicitud.tipo proceso$ = $I(" + tipo_proceso +")$"
				+ " and $plantilla solicitud.tipo solicitante$=$I(" + tipo_solicitante + ")$ "
				+ "and $plantilla solicitud.num plantilla$ = $I("+ num_plantilla+")$";
		
//		String consulta = "select nombre_plantilla from dst_plantilla_solicitud where "
//				+ "tipo_proceso = " + tipo_proceso
//				+ " and tipo_solicitante = " + tipo_solicitante
//				+ " and num_plantilla = " + num_plantilla;
		try {
			String respuesta = DS_SqlUtils.queryForObject(String.class,consulta);
			if(null != respuesta){
				nombrePlantilla = "componentes/otros_procesos/" + respuesta;
			}
		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		
		return nombrePlantilla;
	}
		
	private void downloadFile(HttpSession session, HttpServletRequest request, HttpServletResponse response ) throws IOException{
		System.out.println("DescargaArchivoServlet: tipo_proceso : " + request.getParameter("tipo_proceso"));
		System.out.println("DescargaArchivoServlet: tipo_solicitante : " + request.getParameter("tipo_solicitante"));
		System.out.println("DescargaArchivoServlet: num_plantilla : " + request.getParameter("num_plantilla"));
		
		Integer tipo_proceso = Integer.valueOf(request.getParameter("tipo_proceso"));
		Integer tipo_solicitante = Integer.valueOf(request.getParameter("tipo_solicitante"));
		Integer num_plantilla = Integer.valueOf(request.getParameter("num_plantilla"));
		String nombre_plantilla = getNombrePlantilla(tipo_proceso, tipo_solicitante, num_plantilla);
		if(null != nombre_plantilla){
		
			File downloadFile = null;
			downloadFile = new File(ContextInfo.getInstance().getDiskPathForResource(nombre_plantilla));
	       
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
