package com.osmosyscol.osmoautenticador.autenticador;

public interface Autenticador {

	public int cargarConfiguracion(String rutaConfiguracion);

	public RespuestaAutenticador autenticar(EntradaAutenticador solicitud);

	public RespuestaAutenticador autorizar(EntradaAutenticador solicitud);

	public Boolean cerrarSesion(EntradaAutenticador solicitud);

	public Boolean perderSesion(EntradaAutenticador solicitud);

}
