package com.osmosyscol.datasuite.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.logica.dto.Contenido;

public interface ContenidoDao {

	public Boolean guardarContenido(Contenido contenido);
	
	public Contenido obtenerContenido(String url, String etiqueta);
	
	public List<Contenido> obtenerContenidosByURL(String url);
	
	public List<Contenido> obtenerTodosLosContenidos(Integer numero_pagina, Integer registrosPorPagina);
	
	public Boolean actualizarTextoContenido(Contenido contenido);
	
	public Contenido obtenerContenidoPorID(Integer id_contenido);
	
	public Integer obtenerTotalTodosLosContenidos();
	
	public List<String> obtenerTodosLosNombreServicio();
	
	public List<Contenido> obtenerContenidosPorServicio(String nombre_servicio);
	
}
