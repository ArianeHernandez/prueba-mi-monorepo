package com.osmosyscol.datasuite.pagos.acciones.validacionsarlaft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.htsoft.commons.util.SMessage;

import com.itosmosys.www.datapi.servicios.consulta_sarlaft.LISTASArrayElement;
import com.itosmosys.www.datapi.servicios.consulta_sarlaft.TipoElementoSalidaconsulta_sarlaft;
import com.osmosyscol.datasuite.logica.pagos.AccionCarga;
import com.osmosyscol.datasuite.pagos.services.IntegracionPagosService;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;
import com.osmosyscol.persistencia.dao.ibatis.core.ParametrosInicio;

public class ValidacionSarlaft implements AccionCarga {

	public SMessage ejecutar(Integer id_carga) {

		try {

			List<SarlaftThread> ss = new ArrayList<ValidacionSarlaft.SarlaftThread>();

			String sql = "select r.id id_retiro from $RETIROS$  r " + "where r.idcarga= " + id_carga;

			List<Integer> retiros = DS_SqlUtils.queryForList(Integer.class, sql);
			ExecutorService executor = Executors.newFixedThreadPool(8);
			for (Integer retiro : retiros) {

				SarlaftThread s = new SarlaftThread(retiro);
				ss.add(s);

				executor.execute(s);
			}

			executor.shutdown();
			while (!executor.isTerminated()) {
			}

			for (SarlaftThread s : ss) {

				if (!s.ejecutado) {
					return new SMessage(false, "No se ejecuto sarlaft");
				}

			}

			return new SMessage(true, "Se ejecuto exitosamente");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new SMessage(false, e.getMessage());
		}
	}

	public static class EjecutarSarlaftThread extends Thread {

		Integer id_carga;

		public EjecutarSarlaftThread(Integer id_carga) {
			this.id_carga = id_carga;
		}

		@Override
		public void run() {

		}
	}

	public static class SarlaftThread implements Runnable {

		private Integer id_retiro;
		public boolean ejecutado = false;

		public SarlaftThread(Integer id_retiro) {
			this.id_retiro = id_retiro;
		}

		public void run() {
			ejecutado = ejecutarSarlaft(id_retiro);
		}

	}

	private static boolean ejecutarSarlaft(Integer id_retiro) {

		String endPoint = ParametrosInicio.getProperty("sippte.endpoint");

		// Si no se tiene configurada la integracion continua..
		if (endPoint == null) {
			return true;
		}
		
		String sql = "select b.id id_beneficiario, $TIPO DE DOCUMENTO.NOMBRE$ tipo_documento,  $BENEFICIARIO.NUMERO DE DOCUMENTO$ numero_documento, " //
				+ "$BENEFICIARIO.PRIMER NOMBRE$ primer_nombre,  $BENEFICIARIO.SEGUNDO NOMBRE$ segundo_nombre, " //
				+ "$BENEFICIARIO.PRIMER APELLIDO$, $BENEFICIARIO.SEGUNDO APELLIDO$ " + "from $RETIROS$ r,  $BENEFICIARIO$ b, " //
				+ "$TIPO DE DOCUMENTO$ t where b.id = $RETIROS.BENEFICIARIO$ and t.id = $BENEFICIARIO.TIPO DE DOCUMENTO$ " //
				+ "and $BENEFICIARIO.EN LISTA$ is null " //
				+ "and r.id = " + id_retiro;

		try {

			Beneficiario beneficiario = DS_SqlUtils.queryForObject(Beneficiario.class, sql);
			if (beneficiario == null) {
				return true;
			}

			IntegracionPagosService pagos = new IntegracionPagosService(endPoint);

			System.out.println("CONSULTANDO VALICACION SARLAFT...");

			TipoElementoSalidaconsulta_sarlaft[] respuesta = pagos.consultaSarlaft(beneficiario.getTipo_documento(), beneficiario.getNumero_documento(), beneficiario.getPrimer_nombre());

			if (respuesta != null && respuesta.length != 0) {
				TipoElementoSalidaconsulta_sarlaft elem = respuesta[0];
				if (elem.getLISTAS().length != 0) {

					Map<String, Object> params = new HashMap<String, Object>();
					DS_SqlUtils.delete("DELETE FROM $LISTA CAUTELAR$ WHERE $LISTA CAUTELAR.BENEFICIARIO$ = " + beneficiario.getId_beneficiario());

					for (LISTASArrayElement lista : elem.getLISTAS()) {
						params.put("id", DS_SqlUtils.nextId());
						params.put("beneficiario", beneficiario.getId_beneficiario());
						params.put("nombre", lista.getP_NOMBRE());
						params.put("porcentaje_coincidencia", lista.getP_PORCENTAJE_COINCIDENCIA());

						DS_SqlUtils.insert("INSERT INTO $LISTA CAUTELAR$ (id, $LISTA CAUTELAR.BENEFICIARIO$, $LISTA CAUTELAR.NOMBRE$, $LISTA CAUTELAR.PORCENTAJE COINCIDENCIA$) VALUES ($id$, $beneficiario$, #nombre#, #porcentaje_coincidencia#)", params);
					}

					Map<String, String> paramsU = new HashMap<String, String>();
					paramsU.put("enLista", "S");
					DS_SqlUtils.update("UPDATE $BENEFICIARIO$ SET $BENEFICIARIO.EN LISTA$ = #enLista# where $BENEFICIARIO$.id = " + beneficiario.getId_beneficiario(), paramsU);

				} else {
					if (beneficiario.getId_beneficiario() != null) {
						Map<String, String> paramsU = new HashMap<String, String>();
						paramsU.put("enLista", "N");
						DS_SqlUtils.update("UPDATE $BENEFICIARIO$ SET $BENEFICIARIO.EN LISTA$ = #enLista# where $BENEFICIARIO$.id = " + beneficiario.getId_beneficiario(), paramsU);
					}

				}

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public Boolean visualizar(Integer id_carga, Integer id_administrativo) {
		return true;
	}
}
