package com.osmosyscol.datasuite.persistencia.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.datasuite.logica.constantes.Constantes;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.persistencia.dao.NegocioDao;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.persistencia.dao.ibatis.core.BaseSqlMapDao;

public class NegocioDaoImp extends BaseSqlMapDao implements NegocioDao {

	public NegocioDaoImp(DaoManager daoManager) {
		super(daoManager);
	}

	// --------------------------------------------------------

	public Negocio obtenerNegocio(Integer id_negocio) {
		return (Negocio) queryForObject("Negocio.obtenerNegocio", id_negocio);
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerListadoNegocios() {
		return (List<Negocio>) queryForList("Negocio.obtenerListadoNegocios", null);
	}
	
	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerListadoNegociosActivos() {
		return (List<Negocio>) queryForList("Negocio.obtenerListadoNegociosActivos", null);
	}

	public Integer obtenerSiguienteId() {
		return (Integer) queryForObject("Negocio.obtenerSiguienteId", null);
	}

	public Negocio crearNegocio(Negocio negocio) {

		if (negocio.getId_negocio() == null) {
			negocio.setId_negocio(Integer.parseInt(negocio.getCod_negocio()));
		}

		if (negocio.getActivo() == null) {
			negocio.setActivo(Constantes.SI);
		}

		insert("Negocio.crearNegocio", negocio);
		return negocio;
	}

	public Negocio actualizarNegocio(Negocio negocio) {

		Negocio negocioexis = (Negocio) queryForObject("Negocio.obtenerNegocioPorCodigo", negocio.getCod_negocio());

		if (negocioexis == null) {
			negocio = crearNegocio(negocio);
		} else {
			if (negocio.getActivo() == null) {
				negocio.setActivo(negocioexis.getActivo());
			}
			update("Negocio.actualizarNegocio", negocio);
		}

		return negocio;
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerListadoNegociosPorLogin(String login) {
		return queryForList("Negocio.obtenerListadoNegociosPorLogin", login);
	}

	public void marcarEliminadoNegociosPorModelo(Integer id_modelo) {
		update("Negocio.marcarEliminadoNegociosPorModelo", id_modelo);
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerListadoNegociosPorAdministrador(Integer id_administrador) {
		return queryForList("Negocio.obtenerListadoNegociosPorAdministrador", id_administrador);
	}

	public Boolean guardarUsuarioNegocio(Integer id_usuario, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);

		insert("Negocio.guardarUsuarioNegocio", map);
		return true;
	}

	public Boolean existeUsuarioNegocio(Integer id_usuario, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);
		if((Integer)queryForObject("Negocio.existeUsuarioNegocio", map) == 1) return true;
		else return false;
	}

	public Boolean eliminarUsuarioNegocio(Integer id_usuario, Integer id_negocio) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_usuario", id_usuario);
		map.put("id_negocio", id_negocio);

		delete("Negocio.eliminarUsuarioNegocio", map);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerNegociosPorUsuario(Integer id_usuario) {
		return queryForList("Negocio.obtenerNegociosPorUsuario", id_usuario);
	}

	public Boolean esPersonaAdministrador(Integer id_persona, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_negocio", id_negocio);

		Integer num = (Integer) queryForObject("Negocio.cantidadAdministradorNegocio", map);

		return num > 0;
	}

	public Boolean esPersonaCliente(Integer id_persona, Integer id_negocio) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("id_persona", id_persona);
		map.put("id_negocio", id_negocio);

		Integer num = (Integer) queryForObject("Negocio.cantidadUsuarioNegocio", map);

		return num > 0;
	}

	public void actualizarActivoNegocio(Integer id_negocio, String activo) {

		if (id_negocio != null) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id_negocio", id_negocio);
			map.put("activo", activo == null ? "N" : activo);

			update("Negocio.actualizarActivoNegocio", map);
		}

	}

	public Boolean eliminarNegocio(Integer idNegocio){
		delete("Negocio.eliminarNegocio", idNegocio);
		return true;
	}
	
	public Negocio obtenerNegocioPorCodigo(String cod_negocio){
		return (Negocio) queryForObject("Negocio.obtenerNegocioPorCodigo", cod_negocio);
	}

	//Atualizacion de negocios en CREADATOS 
	
	public Boolean actualizarNegocioEnCreadatos(Negocio negocio) {
		
		
		
		String sql =" update $LINEA DE PRODUCTO$ " +
			 " set $LINEA DE PRODUCTO.ID$ = $I(" +negocio.getCod_negocio()+")$,"+
			 " $LINEA DE PRODUCTO.NOMBRE$ = $S(" +negocio.getNombre()+")$,"+
			 " $LINEA DE PRODUCTO.DESCRIPCION$ = $S(" +negocio.getDescripcion()+")$,"+
			 " $LINEA DE PRODUCTO.ACTIVO$ = $B(" +negocio.getActivo()+")$ "+
			 " where ID = "+negocio.getId_negocio();
		
		String nuevo_sql = RDServicio.reemplazarNombres(sql);
		
		update("Negocio.updateSQLCreadatos", nuevo_sql);
		
		
		return true;
	}

	public Boolean insertarNegocioEnCreadatos(Negocio negocio) {
		
		String sql =" insert into $LINEA DE PRODUCTO$ " +
		 " (ID, idcarga, $LINEA DE PRODUCTO.ID$, $LINEA DE PRODUCTO.NOMBRE$, $LINEA DE PRODUCTO.DESCRIPCION$, $LINEA DE PRODUCTO.ACTIVO$) "+
		 " values ( "+negocio.getId_negocio()+", 0, $I("+negocio.getCod_negocio()+")$, $S("+negocio.getNombre()+")$, " +
		 " $S("+negocio.getDescripcion()+")$, $B("+negocio.getActivo()+")$ ) "; 
	
		String nuevo_sql = RDServicio.reemplazarNombres(sql);
	
		insert("Negocio.insertSQLCreadatos", nuevo_sql);
		
		return true;
	}

	public Integer existeNegocioEnCreadatos(Integer id_negocio) {
		
		return (Integer)queryForObject("Negocio.existeNegocioEnCreadatos", id_negocio);
	}

	public Boolean inactivarTodosLosNegociosEnCreadatos() {
		String sql =" update $LINEA DE PRODUCTO$ " +
		 			" set $LINEA DE PRODUCTO.ACTIVO$ = $B(N)$";
		 
		String nuevo_sql = RDServicio.reemplazarNombres(sql);
	
		update("Negocio.updateSQLCreadatos", nuevo_sql);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerNegociosPorUsuarioFormato(Integer id_usuario, Integer id_formato) {
		
		Map<String, Object> datos = new HashMap<String, Object>();
		datos.put("id_usuario", id_usuario);
		datos.put("id_formato", id_formato);
		
		return queryForList("Negocio.obtenerNegociosPorUsuarioFormato", datos);
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> obtenerListadoNegociosPorTipoProceso(Integer id_tipo_proceso) {
		return queryForList("Negocio.obtenerListadoNegociosPorTipoProceso", id_tipo_proceso);
	}
}
