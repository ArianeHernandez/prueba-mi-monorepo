package com.osmosyscol.datasuite.modelatos.persistencia.dao.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.config.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.ListaValores;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.modelatos.logica.dto.ValorLista;
import com.osmosyscol.datasuite.modelatos.persistencia.dao.ListaValoresDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class ListaValoresDaoImp extends BaseSqlMapDao implements ListaValoresDao {

	// --------------------------------------------------------

	public ListaValoresDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public Boolean eliminarListaValores(Integer id_listavalores) {

		update("ListaValores.eliminarListaValores", id_listavalores);
		return true;
	}

	// --------------------------------------------------------

	public ListaValores guardarListaValores(ListaValores listaValores, Integer id_persona, Integer id_modelo) {
		listaValores.setId_modelo(id_modelo);

		if (listaValores.getId_lista_valores() == null) {
			Integer id_lista_valores = (Integer) queryForObject("ListaValores.siguienteId", null);
			listaValores.setId_lista_valores(id_lista_valores);
			insert("ListaValores.insertarListaValores", listaValores);

			return listaValores;
		} else {
			update("ListaValores.actualizarListaValores", listaValores);
		}
		return listaValores;
	}

	// --------------------------------------------------------

	public ListaValores obtenerListaValores(Integer id_listavalores) {
		return (ListaValores) queryForObject("ListaValores.obtenerListaValores", id_listavalores);
	}

	// --------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<ListaValores> obtenerListaValoresPorPersona(Integer id_modelo, Integer numero_pagina) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);

		return (List<ListaValores>) queryForListPag("ListaValores.obtenerListaValoresPorPersona", map, numero_pagina, Constantes.PAGINACIONLISTADO);
	}

	public Integer totalListaValoresPorPersona(Integer id_modelo) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_modelo", id_modelo);

		return (Integer) queryForObject("ListaValores.totalListaValoresPorPersona", map);

	}

	// -------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public List<ValorLista> obtenerValoresLV(ListaValores listavalores) {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_listavalores", listavalores.getId_lista_valores());
		parametros.put("order", ListaValores.POR_ID.equalsIgnoreCase(listavalores.getCriterio_orden()) ? "id" : "nombre");
		parametros.put("type", ListaValores.DESCENDENTE.equalsIgnoreCase(listavalores.getTipo_orden()) ? "desc" : "");
		List<ValorLista> listado = queryForList("ListaValores.obtenerValoresLV", parametros);
		
	

		// Desencripta los valores de la lista
		for (ValorLista valor : listado) {
			valor.setValueid(Crypto.D(valor.getId()));
			
			String id=valor.getValueid().toString();	
			String nombre=(String) Crypto.D(valor.getNombre());
			String si=com.osmosyscol.datasuite.logica.constantes.Constantes.SI;
			String separador=com.osmosyscol.datasuite.logica.constantes.Constantes.SEPARADORLISTAVALORES;
			if(si.equals(listavalores.getTomar_codigo())&&nombre!=null&&!nombre.startsWith(id)){
				nombre=id+separador+nombre;
			}
			
			valor.setNombre(nombre);
			valor.setColorfondo((String) Crypto.D(valor.getColorfondo()));
			valor.setColorletra((String) Crypto.D(valor.getColorletra()));
		}

		Collections.sort(listado, new ComparadorValorLista(listavalores));
		return listado;
	}

	@SuppressWarnings("unchecked")
	public Boolean actualizarValoresLV(ListaValores listavalores, TipoCampo tipoCampo, List<ValorLista> valoresListaIn) {

		List<ValorLista> valoresLista = new ArrayList<ValorLista>();

		if (CollectionUtils.isNotEmpty(valoresListaIn)) {
			for (ValorLista valorLista : valoresListaIn) {
				if (valorLista != null && valorLista.getId() != null) {
					valoresLista.add(valorLista);
				}
			}
		}

		// ----

		Integer id_listavalores = listavalores.getId_lista_valores();

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("id_listavalores", id_listavalores);
		parametros.put("order", "id");
		List<ValorLista> valoresViejos = queryForList("ListaValores.obtenerValoresLVTodos", parametros);

		// Desencripta los valores de la lista
		for (ValorLista valorViejo : valoresViejos) {
			valorViejo.setValueid(Crypto.D(valorViejo.getId()));
			valorViejo.setNombre((String) Crypto.D(valorViejo.getNombre()));
			valorViejo.setColorfondo((String) Crypto.D(valorViejo.getColorfondo()));
			valorViejo.setColorletra((String) Crypto.D(valorViejo.getColorletra()));
			valorViejo.setEstado(valorViejo.getEstado());
		}

		// Actualiza los valores que ya estan o los crea
		for (ValorLista valorLista : valoresLista) {

			if (valorLista != null && valorLista.getId() != null) {

				boolean existe = false;
				for (ValorLista valorViejo : valoresViejos) {
					if (valorViejo != null && valorViejo.getId() != null) {
						if (valorLista.getId().equals(valorViejo.getId())) {
							existe = true;
						}
					}
				}

				Map<String, Object> mapa = new HashMap<String, Object>();

				mapa.put("id_listavalores", id_listavalores);

				try {
					if (tipoCampo.getTipo_dato().equals("String")) {
						mapa.put("id_valor", Crypto.E(valorLista.getId()));
					}

					if (tipoCampo.getTipo_dato().equals("Float") || tipoCampo.getTipo_dato().equals("Double") || tipoCampo.getTipo_dato().equals("BigDecimal")) {
						mapa.put("id_valor", Crypto.E(new BigDecimal(valorLista.getId())));
					}

					if (tipoCampo.getTipo_dato().equals("Boolean")) {
						mapa.put("id_valor", Crypto.E(StringUtils.esVerdad(valorLista.getId())));
					}

					if (tipoCampo.getTipo_dato().equals("Date")) {
						mapa.put("id_valor", Crypto.E(StringUtils.toDate(valorLista.getId())));
					}

					if (tipoCampo.getTipo_dato().equals("Integer") || tipoCampo.getTipo_dato().equals("Long")) {
						mapa.put("id_valor", Crypto.E(new Long(valorLista.getId().trim())));
					}

				} catch (Exception e) {
					mapa.put("id_valor", null);
				}

				// Encripta los valores para actualizar
				mapa.put("nombre_valor", Crypto.E(valorLista.getNombre()));
				mapa.put("colorletra", Crypto.E(valorLista.getColorletra()));
				mapa.put("colorfondo", Crypto.E(valorLista.getColorfondo()));
				mapa.put("estado", ValorLista.ESTADO_ACTIVO);

				if (existe) {
					update("ListaValores.actualizarValoresLV", mapa);
				} else {
					insert("ListaValores.insertarValoresLV", mapa);
				}
			}

		}

		// -------------------

		// elimina los valores que ya no estan
		for (ValorLista valorViejo : valoresViejos) {
			if (valorViejo != null && valorViejo.getId() != null) {

				boolean existe = false;
				for (ValorLista valorLista : valoresLista) {
					if (valorLista != null && valorLista.getId() != null) {
						if (valorLista.getId().equals(valorViejo.getId())) {
							existe = true;
						}
					}
				}

				if (!existe) {
					Map<String, Object> mapa = new HashMap<String, Object>();

					mapa.put("id_listavalores", id_listavalores);
					mapa.put("id_valor", Crypto.E(valorViejo.getValueid()));
					mapa.put("estado", ValorLista.ESTADO_ELIMINADO);
					update("ListaValores.borrarValoresLV", mapa);
				}
			}
		}

		return true;
	}

	// --------------------------------------------------------

	public ListaValores obtenerListaValoresPorNombre(String nombre) {
		return (ListaValores) queryForObject("ListaValores.obtenerListaValoresPorNombre", nombre);

	}

	public List<ListaValores> obtenerListasDeValores(Integer idModelo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id_modelo", idModelo);
		return (List<ListaValores>) queryForList("ListaValores.obtenerListaValoresPorPersona", map);
	}

	public List<ValorLista> obtenerValoresBD(String sql) {
		return queryForList("ListaValores.obtenerValoresBD", sql);
	}

	// --------------------------------------------------------
}
