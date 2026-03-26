package com.osmosyscol.datasuite.modelatos.persistencia.dao;

import java.util.List;

import com.osmosyscol.datasuite.modelatos.logica.dto.Uri;


public interface UriDao {

	Uri obtenerUri(Integer id_uri);

	List<Uri> obtenerTodasUri(Integer numero_pagina);

	Integer totalUris();

	Boolean guardarUri(Uri uri);

	Boolean eliminarUri(Integer id_uri);

}
