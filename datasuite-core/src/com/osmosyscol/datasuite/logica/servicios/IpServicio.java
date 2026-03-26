package com.osmosyscol.datasuite.logica.servicios;

import java.util.List;

import com.ibatis.dao.client.DaoManager;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.commons.utils.StringUtils;
import com.osmosyscol.datasuite.logica.dto.Ipprivada;
import com.osmosyscol.datasuite.logica.dto.Ippublica;
import com.osmosyscol.datasuite.persistencia.dao.IpDao;
import com.osmosyscol.persistencia.dao.ibatis.core.DaoConfig;

public class IpServicio {

	private static IpServicio instance;

	private IpServicio() {
	}

	public static IpServicio getInstance() {
		if (instance == null) {
			instance = new IpServicio();
		}
		return instance;
	}

	public Boolean validarIP(Integer id_usuario, String ip_publica, List<String> ip_privadas) {

		try {
			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			List<Ippublica> ippubBlanca = ipDao.obtenerIPPublicasPorEstado(id_usuario, "B");
			List<Ippublica> ippubNegra = ipDao.obtenerIPPublicasPorEstado(id_usuario, "N");

			boolean tieneListaBlanca = (ippubBlanca != null && !ippubBlanca.isEmpty());
			boolean tieneListaNegra = (ippubNegra != null && !ippubNegra.isEmpty());

			// si tiene lista blanca y no esta entonces invalidar
			if (tieneListaBlanca && !estaEnLista(ip_publica, ip_privadas, ippubBlanca, ipDao)) {
				return false;
			}

			// si tiene lista negra y esta entonces invalidar
			if (tieneListaNegra && estaEnLista(ip_publica, ip_privadas, ippubNegra, ipDao)) {
				return false;
			}

		} catch (Throwable e) {
			SimpleLogger.setError("Error al validar la ip", e);
			return false;
		}

		return true;

	}

	/**
	 * Verifica que una ip_publica con sus ips privadas se encuentren en una
	 * lista de Ippublica
	 * 
	 * @param ip_publica
	 * @param ip_privadas
	 * @param listadoIpsPublicas
	 * @param ipDao
	 * @return
	 */
	private boolean estaEnLista(String ip_publica, List<String> ip_privadas, List<Ippublica> listadoIpsPublicas, IpDao ipDao) {
		boolean estaEnLista = false;
		for (Ippublica ippublica : listadoIpsPublicas) {
			if (estaEnRango(ip_publica, ippublica.getDesde(), ippublica.getHasta())) {
				List<Ipprivada> privadas = ipDao.obtenerIPPrivadas(ippublica.getId_ippublica());

				boolean tienePrivadas = (privadas != null && !privadas.isEmpty());

				if (tienePrivadas) {
					for (String ip_privada : ip_privadas) {
						for (Ipprivada ipprivada : privadas) {
							if (estaEnRango(ip_privada, ipprivada.getDesde(), ipprivada.getHasta())) {
								return true;
							}
						}
					}
				} else {
					estaEnLista = true;
				}
			}
		}
		return estaEnLista;
	}

	private boolean estaEnRango(String ip_publica, String desde, String hasta) {

		boolean limdesde = ip2Str(desde).compareToIgnoreCase(ip2Str(ip_publica)) <= 0;
		boolean limhasta = ip2Str(hasta).compareToIgnoreCase(ip2Str(ip_publica)) >= 0;

		return (limdesde && limhasta);
	}

	public static String ip2Str(String srtip) {

		String ret = StringUtils.trim(srtip).toUpperCase().replace(':', ' ').replace('.', ' ');

		String cadena = "";

		String vals[] = ret.split(" ");
		for (String val : vals) {
			cadena += "." + StringUtils.izquierda(val, 10, '0');
		}

		return cadena;
	}

	public List<Ippublica> obtenerIPPublicas(Integer id_usuario) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.obtenerIPPublicas(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public List<Ipprivada> obtenerIPPrivadas(Integer id_ippublica) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.obtenerIPPrivadas(id_ippublica);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Integer guardarIPPrivada(Ipprivada ipprivada) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.guardarIPPrivada(ipprivada);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Integer guardarIPPublica(Ippublica ippublica) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.guardarIPPublica(ippublica);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;
	}

	public Boolean eliminarIPPublica(Integer id_ippublica) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.eliminarIPPublica(id_ippublica);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public Boolean eliminarIPPrivada(Integer id_ipprivada) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.eliminarIPPrivada(id_ipprivada);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

	public List<Ipprivada> obtenerIPPrivPorUsuario(Integer id_usuario) {

		try {

			DaoManager daoManager = DaoConfig.getDaoManager();
			IpDao ipDao = (IpDao) daoManager.getDao(IpDao.class);

			return ipDao.obtenerIPPrivPorUsuario(id_usuario);

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}
		return null;

	}

}
