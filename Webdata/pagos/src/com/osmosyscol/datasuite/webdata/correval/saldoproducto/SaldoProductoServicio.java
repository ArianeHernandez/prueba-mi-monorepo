package com.osmosyscol.datasuite.webdata.correval.saldoproducto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.osmosyscol.commons.crypto.Crypto;
import com.osmosyscol.commons.log.SimpleLogger;
import com.osmosyscol.datasuite.reportedim.servicio.RDServicio;
import com.osmosyscol.datasuite.servlet.InitApp;
import com.osmosyscol.datasuite.webdata.logica.servicios.CargaServicio;

public class SaldoProductoServicio {

	private static SaldoProductoServicio instance;

	private SaldoProductoServicio() {
	}

	public static SaldoProductoServicio getInstance() {
		if (instance == null) {
			instance = new SaldoProductoServicio();
		}
		return instance;
	}

	public List<SaldoProducto> obtenerSaldosProductoPorCliente(Integer id_usuario) {

		try {

			String sqlSaldoProducto = "select e.$ENCARGO.CODIGO$ C_CODIGO, e.$ENCARGO.ID CLIENTE$ C_ID_CLIENTE, e.$ENCARGO.SALDO TOTAL$ C_ST, e.$ENCARGO.SALDO DISPONIBLE$ C_SD, p.$PRODUCTO.NOMBRE$ C_P_NOMBRE, lp.$LINEA DE PRODUCTO.NOMBRE$ C_LP_NOMBRE, lp.ID ID_LP  from $ENCARGO$ e, $PRODUCTO$ p, $LINEA DE PRODUCTO$ lp" + " where e.$ENCARGO.PRODUCTO$ = p.ID" + " and p.$PRODUCTO.LINEA DE PRODUCTO$ = lp.ID" + " and e.$ENCARGO.ID CLIENTE$ = $I(" + id_usuario + ")$" + " AND $ENCARGO.ACTIVO$ = $B(true)$ ";

			sqlSaldoProducto = RDServicio.reemplazarNombres(sqlSaldoProducto);

			List<Map<String, Object>> listaMapas = CargaServicio.getInstance().selectSql(sqlSaldoProducto);
			listaMapas = desencriptarMapa(listaMapas);

			List<SaldoProducto> saldosProductoPorCliente = new ArrayList<SaldoProducto>();

			if (listaMapas != null) {
				for (Map<String, Object> map : listaMapas) {

					try {

						SaldoProducto saldoProducto = new SaldoProducto();

						String id_producto = map.get("ID_LP").toString();
						saldoProducto.setId_negocio(Integer.parseInt(id_producto));

						String id_usuario_map = map.get("C_ID_CLIENTE").toString();
						saldoProducto.setId_usuario(Integer.parseInt(id_usuario_map));

						saldoProducto.setNombre_negocio(map.get("C_LP_NOMBRE").toString());

						saldoProducto.setNombre_producto(map.get("C_P_NOMBRE").toString());

						String saldoDisponible = map.get("C_SD").toString();
						saldoProducto.setSaldo_disponible(new BigDecimal(saldoDisponible));

						String saldoTotal = map.get("C_ST").toString();
						saldoProducto.setSaldo_total(new BigDecimal(saldoTotal));

						String codigo = map.get("C_CODIGO").toString();
						saldoProducto.setCodigo(codigo);

						saldosProductoPorCliente.add(saldoProducto);

					} catch (Exception e) {
						SimpleLogger.setError("No se puede cargar el cliente " + map);
					}

				}
			}

			return saldosProductoPorCliente;

		} catch (Exception e) {
			SimpleLogger.setError("Ha ocurrido un error", e);
		}

		return null;
	}

	public static void main(String[] args) {
		InitApp.startUp();

		List<SaldoProducto> saldos = SaldoProductoServicio.getInstance().obtenerSaldosProductoPorCliente(1580);

		for (SaldoProducto saldoProducto : saldos) {
			SimpleLogger.setDebug(saldoProducto.getNombre_negocio());
			SimpleLogger.setDebug(saldoProducto.getNombre_producto());
			SimpleLogger.setDebug("" + saldoProducto.getId_negocio());
			SimpleLogger.setDebug("" + saldoProducto.getId_usuario());
			SimpleLogger.setDebug("" + saldoProducto.getSaldo_disponible());
			SimpleLogger.setDebug("" + saldoProducto.getSaldo_total());

		}
	}

	private List<Map<String, Object>> desencriptarMapa(List<Map<String, Object>> resp) {

		if (resp == null || resp.isEmpty()) {
			return resp;
		}

		// desencripta
		for (Map<String, Object> registro : resp) {

			Set<String> keys = registro.keySet();
			for (String key : keys) {

				// se verifica si es un campo
				Boolean esCampo;
				try {
					esCampo = (key.toUpperCase().startsWith("C") || key.toUpperCase().startsWith("V"));
				} catch (Exception e) {
					esCampo = false;
				}

				// se verifica si el valor es texto
				if (registro.get(key) instanceof String && esCampo) {
					// System.out
					// .println("CargaDaoImp.obtenerDatosCargaMultiple() "+key);
					registro.put(key, Crypto.DF(registro.get(key)));
				}
			}
		}

		return resp;
	}

}
