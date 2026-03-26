package com.osmosyscol.datasuite.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.osmosyscol.commons.utils.StringUtils;

// -------------------

@SuppressWarnings("serial")
public class ExcelServlet extends HttpServlet {

	private static Map<String, HSSFWorkbook> mapa = new HashMap<String, HSSFWorkbook>();
	private static Map<String, String> mapaNombre = new HashMap<String, String>();

	
	public static String registrarWorkbook(HSSFWorkbook wb){
		return registrarWorkbook(wb, null);
	}
	
	public static String registrarWorkbook(HSSFWorkbook wb, String nombre) {

		if (wb == null) {
			return null;
		}

		String p = StringUtils.randomString(5) + StringUtils.MD5(System.currentTimeMillis() + "");
		mapa.put(p, wb);
		if (nombre == null){
			nombre = "Reporte_" + System.currentTimeMillis();
		}
		mapaNombre.put(p, nombre);
		return p;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req, resp);
	}
	
	public void doDo(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String fileName = mapaNombre.get(req.getParameter("id")) + ".xls";
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

		HSSFWorkbook wb = mapa.get(req.getParameter("id"));

		OutputStream out = response.getOutputStream();
		
		try {
			wb.write(out);
		} catch (IOException ioe) {
			if (!response.isCommitted()) {
				response.setContentType("text/html");
			}
		}

	}

}
