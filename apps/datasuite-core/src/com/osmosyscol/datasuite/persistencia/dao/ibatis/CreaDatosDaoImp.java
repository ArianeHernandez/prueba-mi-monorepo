package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.modelatos.logica.dto.Campo;
import com.osmosyscol.datasuite.modelatos.logica.dto.TipoCampo;
import com.osmosyscol.datasuite.persistencia.dao.CreaDatosDao;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class CreaDatosDaoImp extends BaseSqlMapDao implements CreaDatosDao {

	public CreaDatosDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public boolean crearTabla(String idTabla) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tablename", idTabla);

		update("CreaDatos.crearTabla", map);
		update("CreaDatos.crearIdTabla", map);
		return true;
	}

	public boolean existeTabla(String idTabla) {

		return ((Integer) queryForObject("CreaDatos.existeTabla", idTabla)) > 0;
	}

	public boolean existeCampo(String idTabla, String idCampo) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("idTabla", idTabla);
		map.put("idCampo", idCampo);

		return ((Integer) queryForObject("CreaDatos.existeCampo", map)) > 0;
	}

	public Boolean crearCampo(Campo campo, TipoCampo tipoCampo) {

		// ------

		Map<String, String> map = new HashMap<String, String>();
		map.put("tablename", "T" + campo.getId_estructura());
		map.put("id_campo", "C" + campo.getId_campo());

		// ----

		Integer longitud = null;

		if (tipoCampo.getLongitud() != null && tipoCampo.getLongitud().trim().length() > 0) {
			longitud = Integer.parseInt(tipoCampo.getLongitud());
		}

		// ---

		String ctipo = (" " + getSqlTipo("varchar2") + " ");

		if (longitud != null) {
			Integer temp_long = longitud * 3 + 1000;
			if (temp_long > 4000){
				temp_long = 4000;
			}
			ctipo += "(" + temp_long + ") ";
			map.put("tipo", ctipo);
		}

		// ------

		if (campo.getId_lista_valores() != null) {
			map.put("llave_foranea", "L" + campo.getId_lista_valores().toString());
		}

		if (tipoCampo.getTipo_dato().equals("Object")) {
			map.put("tipo", " " + getSqlTipo("number") + " ");
			if (tipoCampo.getId_tipocampo().intValue() == Constantes.CAMPO_TIPOCAMPO_ESTRUCTURA) {
				map.put("llave_foranea", "T" + campo.getId_estructurarelacionada().toString());
			}
		}

		// ------

		if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
			update("CreaDatos.crearCampo", map);
			if (map.get("llave_foranea") != null) {
				update("CreaDatos.crearLlaveForanea", map);
			}
		}

		if (Constantes.CAMPO_MULTIPLICIDAD_MULTIPLE.equals(campo.getMultiplicidad())) {

			map.put("tablaRel", "T" + campo.getId_estructura() + "C" + campo.getId_campo());

			update("CreaDatos.crearTablaRelacion", map);
			update("CreaDatos.crearLlaveForaneaRelacionP1", map);

			if (map.get("llave_foranea") != null) {
				update("CreaDatos.crearLlaveForaneaRelacionP2", map);
			}
		}

		if (campo.getObligatorio().equals("S") && campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
			update("CreaDatos.modificarNotNullCampo", map);
		}

		if (campo.getUnico().equals(Constantes.SI)) {

			if (campo.getMultiplicidad().equals(Constantes.CAMPO_MULTIPLICIDAD_UNICO)) {
				update("CreaDatos.modificarIsUnico", map);
			} else {
				update("CreaDatos.modificarIsUnicoMultiple", map);
			}
		}
		// ------

		return true;

	}

	public Boolean eliminarCampo(Campo campo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tablename", "T" + campo.getId_estructura());
		map.put("id_campo", "C" + campo.getId_campo());

		update("CreaDatos.eliminarCampo", map);

		return true;
	}

	public Boolean actualizarCampo(Campo campo, TipoCampo tipoCampo, List<Campo> camposAnteriores) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("tablename", "T" + campo.getId_estructura());
		map.put("id_campo", "C" + campo.getId_campo());
		map.put("constraint", "UK_C" + campo.getId_campo());

		if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
			if (campo.getObligatorio().equals("S")) {
				if ((Integer) queryForObject("CreaDatos.campoisnull", map) > 0) {
					update("CreaDatos.modificarNotNullCampo", map);
				}
			} else if (((Integer) queryForObject("CreaDatos.campoisnull", map) == 0)) {
				update("CreaDatos.modificarNullCampo", map);
			}

			if (campo.getUnico().equals(Constantes.SI)) {
				if ((Integer) queryForObject("CreaDatos.campoisunique", map) == 0) {
					update("CreaDatos.modificarIsUnico", map);
				}
			} else if (((Integer) queryForObject("CreaDatos.campoisunique", map) > 0)) {
				update("CreaDatos.modificarsNotUnico", map);
			}
		}

		// ------

		return true;
	}

	public boolean crearTablaLista(String idTabla, TipoCampo tipocampo) {

		Map<String, String> map = new HashMap<String, String>();

		Integer longitud = null;

		if (tipocampo.getLongitud() != null && tipocampo.getLongitud().trim().length() > 0) {
			longitud = Integer.parseInt(tipocampo.getLongitud());
		}

		// ---

		String ctipo = (" " + getSqlTipo("varchar2") + " ");

		if (tipocampo.getTipo_dato().equalsIgnoreCase("Boolean")) {
			longitud = 1;
		}
		if (tipocampo.getTipo_dato().equalsIgnoreCase("Integer")) {
			longitud = 50;
		}
		if (tipocampo.getTipo_dato().equalsIgnoreCase("Date")) {
			longitud = 30;
		}

		if (longitud != null) {
			
			int size = (longitud * 3 + 1000);
			if(size>4000){
				size = 4000;
			}
			ctipo += "(" + size + ") ";
		}

		map.put("tablename", idTabla);
		map.put("tipocampo", ctipo);

		update("CreaDatos.crearTablaLista", map);
		update("CreaDatos.crearIdTablaLista", idTabla);

		return true;
	}

	public Boolean crearVista(String id_estructura, List<Campo> campos) {

		id_estructura = StringEscapeUtils.escapeSql(id_estructura);

		StringBuffer sql = new StringBuffer();
		StringBuffer camposVista = new StringBuffer();
		int cid = 0;
		for (Campo campo : campos) {

			if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
				if (campo.getId_estructurarelacionada() != null) {

					camposVista.append(" , C" + campo.getId_campo().toString());
					camposVista.append(" , V" + campo.getId_campo().toString());

				} else if (campo.getId_lista_valores() != null) {

					camposVista.append(" , C" + campo.getId_campo().toString());
					camposVista.append(" , V" + campo.getId_campo().toString());

				} else {
					camposVista.append(" , C" + campo.getId_campo().toString());
				}
				cid++;
			}
		}

		sql.append("CREATE OR REPLACE VIEW V" + id_estructura + "(ID, ID_CARGA, ESTADO" + camposVista.toString() + ", VISUALIZACION) AS ");
		sql.append("select ID, IDCARGA, ESTADO");
		cid = 0;
		String visualizacion = "";

		for (Campo campo : campos) {
			if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
				if (campo.getId_estructurarelacionada() != null) {
					sql.append(", C" + campo.getId_campo().toString() + " as N" + cid);
					sql.append(",(select VISUALIZACION from V" + campo.getId_estructurarelacionada() + " v where v.id=C" + campo.getId_campo().toString() + ") as N" + cid);
					if (campo.getVisualizacion().equals(com.osmosyscol.datasuite.logica.constantes.Constantes.SI)) {
						visualizacion += "(select VISUALIZACION from V" + campo.getId_estructurarelacionada() + " v where v.id=C" + campo.getId_campo().toString() + ") ||' '||";
					}
				} else if (campo.getId_lista_valores() != null) {
					sql.append(", C" + campo.getId_campo().toString() + " as N" + cid);
					sql.append(", (select (l.nombre)::text from L" + campo.getId_lista_valores() + " l where l.id=C" + campo.getId_campo().toString() + ") as N" + cid);
					if (campo.getVisualizacion().equals(com.osmosyscol.datasuite.logica.constantes.Constantes.SI)) {
						visualizacion += " (select l.nombre from L" + campo.getId_lista_valores() + " l where l.id=C" + campo.getId_campo().toString() + ") ||' '||";
					}
				} else {
					sql.append(" , C" + campo.getId_campo().toString() + " as N" + cid);
					if (campo.getVisualizacion().equals(com.osmosyscol.datasuite.logica.constantes.Constantes.SI)) {
						visualizacion += " C" + campo.getId_campo().toString() + "||' '||";
					}
				}

				cid++;
			}
		}

		// campos de visualización
		sql.append(", (" + visualizacion.substring(0, visualizacion.length() - 7) + ")::text ");


		sql.append(" from T" + id_estructura.toString() + " t");

		SimpleLogger.setInfo("SQLCREADATOSSELECT: " + verifyQuery(sql.toString()));

		update("CreaDatos.createSQL", verifyQuery(sql.toString()));

		return true;

	}

	public Boolean crearVistaDummy(Integer id_estructuraint, List<Campo> campos) {

		String id_estructura = StringEscapeUtils.escapeSql(id_estructuraint.toString());

		StringBuffer sql = new StringBuffer();
		String camposVista = "";
		String valoresDummy = "";
		
		for (Campo campo : campos) {

			if (Constantes.CAMPO_MULTIPLICIDAD_UNICO.equals(campo.getMultiplicidad())) {
				if (campo.getId_estructurarelacionada() != null) {

					camposVista += (" , C" + campo.getId_campo().toString());
					camposVista += (" , V" + campo.getId_campo().toString());

					valoresDummy += (" , C" + campo.getId_campo().toString());
					valoresDummy += (" , CAST(null as text) AS V" + campo.getId_campo().toString());

				} else if (campo.getId_lista_valores() != null) {

					camposVista += (" , C" + campo.getId_campo().toString());
					camposVista += (" , V" + campo.getId_campo().toString());

					valoresDummy += (" , C" + campo.getId_campo().toString());
					valoresDummy += (" , CAST(null as text) AS V" + campo.getId_campo().toString());

				} else {
					camposVista += (" , C" + campo.getId_campo().toString());

					valoresDummy += (" , C" + campo.getId_campo().toString());
				}
			}
		}

		sql.append("CREATE OR REPLACE VIEW V" + id_estructura + "(ID, ID_CARGA, ESTADO" + camposVista + ", VISUALIZACION) AS ");
		sql.append("select CAST(null as bigint) AS ID, CAST(null as bigint) AS IDCARGA, CAST(null as character) AS ESTADO" + valoresDummy + ", CAST(null as text) AS VISUALIZACION FROM  T" + id_estructura + " where 1=2");

		SimpleLogger.setInfo("SQLCREADATOSSELECT: " + verifyQuery(sql.toString()));

		update("CreaDatos.createSQL", verifyQuery(sql.toString()));

		return true;

	}

	public void eliminarVista(Integer id_estructura) {
		update("CreaDatos.dropSQL", "DROP VIEW IF EXISTS V" + id_estructura);
	}

	public void crearPKUnique(List<Campo> camposIdNuevos, Integer id_estructura) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tablename", "t" + id_estructura);

		String campos = "( idcarga";
		for (Campo campo : camposIdNuevos) {
			campos = campos + ", c" + campo.getId_campo() ;
		}
		campos = campos + " )";
		
		map.put("campos", campos);

		update("CreaDatos.crearPKUnique", map);
	}

	public void eliminarPKUnique(Integer id_estructura) {
		update("CreaDatos.eliminarPKUnique", "t" + id_estructura);
	}

}
