package com.osmosyscol.datasuite.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author hjdiaz
 *
 */
@SuppressWarnings("serial")
public class DownloadLogService extends HttpServlet{
	
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDo(req, resp);
	}
	
	public void doDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.reset();	
		
		String nombreArchivo = request.getParameter("nombre");
		File f = new File("logs");
		
		 byte[] zip = zipFile(f, nombreArchivo);

         ServletOutputStream sos = response.getOutputStream();
         response.setContentType("application/zip");
         response.setHeader("Content-Disposition", "attachment; filename=\""+nombreArchivo+".ZIP\"");
         sos.write(zip);
         sos.flush();         
		
	}
	
	private byte[] zipFile(File directory, String fileName) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];    
        FileInputStream fis = new FileInputStream(directory.getPath() + DownloadLogService.FILE_SEPARATOR + fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        zos.putNextEntry(new ZipEntry(fileName));

        int bytesRead;
        while ((bytesRead = bis.read(bytes)) != -1) {
            zos.write(bytes, 0, bytesRead);
        }
        zos.closeEntry();
        bis.close();
        fis.close();       
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
        return baos.toByteArray();
    }

}
