package com.osmosyscol.datasuite.webdata.correval.grupogiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.pagos.acciones.aplicardevolucion.AplicarDevolucion;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.datasuite.webdata.correval.tablero_control.Banco;
import com.osmosyscol.datasuite.webdata.correval.tablero_control.TableroControlServicio;

public class EdicionGrupoServicio {

	// lista los archivos generados por un usuario

	public static List<ArchivoGrupoGiro> listarArchivosGenerados(String login) {
		String sql = " select a.*, " //
				+ " (select count(1) from $GRUPO GIRO$ WHERE $GRUPO GIRO.ARCHIVO$ = A.ID) CANTIDAD " //
				+ " from $ARCHIVO$ a WHERE " //
				+ " (select count(1) from $GRUPO GIRO$ gg, $RETIROS$ RR WHERE $GRUPO GIRO.CODIGO REGISTRO$ = rr.id and $GRUPO GIRO.ARCHIVO$ = A.ID and $RETIROS.ESTADO DE RESPUESTA BANCO$ is not null) = 0 " //
				+ " and $ARCHIVO.PERSONA$ = #login# " //
				+ " and (select count(1) from $GRUPO GIRO$ WHERE $GRUPO GIRO.ARCHIVO$ = A.ID) > 0 "
				+ " order by id desc ";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("login", login);
		return DS_SqlUtils.queryForList(ArchivoGrupoGiro.class, sql, params);
	}

	// listar los grupos de giro por archivo
	public static List<GrupoGiroRegistro> listarGruposGiro(Integer id_archivo) {

		String sql = "select gg.id, rr.idcarga ID_CARGA, $RETIROS.VALOR$ valor, " //
				+ " rr.$RETIROS.ID CLIENTE$ ID_CLIENTE, BB.$BENEFICIARIO.NUMERO DE CUENTA$ CUENTA_DESTINO, " //
				+ " $BENEFICIARIO.BANCO$ BANCO_DESTINO " //
				+ " from $GRUPO GIRO$ gg, $RETIROS$ RR, $beneficiario$ bb " //
				+ " where " //
				+ " $GRUPO GIRO.ARCHIVO$ =  " + id_archivo//
				+ " and bb.ID = $RETIROS.BENEFICIARIO$ " //
				+ " AND gg.$GRUPO GIRO.CODIGO REGISTRO$ = rr.id ";

		List<GrupoGiroRegistro> listado = DS_SqlUtils.queryForList(GrupoGiroRegistro.class, sql);

		List<Banco> bancos = TableroControlServicio.getInstance().obtenerBancos();

		Map<Long, String> nombre_bancos = new HashMap<Long, String>();

		for (Banco banco : bancos) {
			nombre_bancos.put(banco.getId(), banco.getNombre());
		}

		Map<Integer, Usuario> usuarios = new HashMap<Integer, Usuario>();

		for (GrupoGiroRegistro r : listado) {

			Usuario usuario = usuarios.get(r.getId_cliente());

			if (usuario == null) {
				usuario = UsuarioServicio.getInstance().obtenerUsuario(r.getId_cliente());
				if (usuario != null) {
					usuarios.put(r.getId_cliente(), usuario);
				}
			}

			if (usuario != null) {
				r.setNombre_cliente(usuario.getNombre());
				r.setIdentificacion_cliente(usuario.getIdentificacion());
			}

			if (r.getBanco_destino() != null) {
				r.setNombre_banco_destino(nombre_bancos.get(r.getBanco_destino()));
			}

		}

		return listado;

	}

	public static Boolean rechazarGrupoGiro(Integer id_grupo) {

		try {

			// adicionar respueta banco rechazado
			{
				String sql = "update $RETIROS$ set $RETIROS.ESTADO DE RESPUESTA BANCO$ = $S(RECHAZADO - INFORMACION INVALIDA)$, $RETIROS.RECHAZADO$ = $B(true)$, $RETIROS.ESTADO$=$S('F')$, $RETIROS.ACTUALIZACION$ = $T(SYSDATE)$ WHERE $RETIROS$.ID IN ( select MAX(rr.ID) from $GRUPO GIRO$ gg, $RETIROS$ RR WHERE $GRUPO GIRO.CODIGO REGISTRO$ = rr.id and GG.ID = " + id_grupo + " )";
				DS_SqlUtils.update(sql);
				AplicarDevolucion.ejecutar();
			}

			// Elimina el grupo de giro
			{
				DS_SqlUtils.update("delete $GRUPO GIRO$ WHERE ID = " + id_grupo);
			}
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

}
