package com.osmosyscol.datasuite.mein.servicios.rest.api;
import javax.servlet.annotation.WebServlet;

import co.htsoft.commons.net.GET;
import co.htsoft.commons.net.POST;
import co.htsoft.commons.net.RestService;
import co.htsoft.commons.util.SMessage;

import com.google.gson.Gson;
import com.osmosyscol.datasuite.bpm.servicios.BpmServicios;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerRequest;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerResponse;
import com.osmosyscol.datasuite.mein.dtos.GenerarStickerServicio;
import com.osmosyscol.datasuite.mein.dtos.WSData;
import com.osmosyscol.datasuite.near.webdata.IntegrarSticker;

@WebServlet("/api/stickers/*")
public class GenerarStickerRest extends RestService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String estado = "OK";

	@GET("/api/stickers/test")
	public String test() {
		return estado;
	}
	
	public static class RestStickerRequest{
		Integer idcarga;
		String numRadicado;
		Integer formatoRequerido;
		String numeroProceso;
		String token;
	}
	@POST(value="/api/stickers/obtenerSticker")
	public GenerarStickerResponse obtenerSticker(GenerarStickerRequest r) {
		WSData integracion = GenerarStickerServicio.getInstance().generarSticker(r, -1);
		return new Gson().fromJson(integracion.getResponse(), GenerarStickerResponse.class);
	}
	@POST(value="/api/stickers/obtenerStickerNear")
	public GenerarStickerResponse obtenerStickerNear(RestStickerRequest r) {
		return GenerarStickerServicio.getInstance().generarStickerNear(r.idcarga);
	}
	@POST(value="/api/stickers/obtenerStickerInsolvencia")
	public GenerarStickerResponse obtenerStickerInsolvencia(RestStickerRequest r) {
		return GenerarStickerServicio.getInstance().generarStickerInsolvencia(r.idcarga);
	}
	@GET("/api/stickers/auth")
	public String autenticacion() throws Throwable{
		return BpmServicios.getInstance().generarToken(null);
	}
	public static class integrarRequest{
		Integer idCarga;
	}
	@POST(value="/api/stickers/integrarStickers")
	public String integrarSticker(integrarRequest r) {
		Integer id_carga = r.idCarga;
		SMessage response = null;
		IntegrarSticker op_interna = new IntegrarSticker();
		response = op_interna.ejecutar(id_carga);
		
		if (response != null) {
			return response.getMsg();
		} else {
			return "Operacion no Exitosa";
		}
	}
}
