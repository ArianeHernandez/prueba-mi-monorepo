package com.osmosyscol.datasuite.webdata.logica.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cocoon.environment.Session;
import org.apache.commons.collections.CollectionUtils;

import com.osmosyscol.datasuite.logica.dto.Proceso;
import com.osmosyscol.datasuite.modelatos.logica.dto.Formato;
import com.osmosyscol.datasuite.modelatos.logica.dto.GrupoFormato;
import com.osmosyscol.datasuite.modelatos.logica.servicios.FormatoServicio;
import com.osmosyscol.datasuite.modelatos.logica.servicios.ProcesoServicio;
import com.osmosyscol.datasuite.webdata.logica.dto.MenuGrupoFormato;
import com.osmosyscol.datasuite.webdata.logica.dto.ProcesoFormato;

public class MenuGrupoFormatoServicio {
	// ---------------------------------------------------------------

	private static MenuGrupoFormatoServicio servicio;

	private MenuGrupoFormatoServicio() {
	}

	public static MenuGrupoFormatoServicio getInstance() {
		if (servicio == null) {
			servicio = new MenuGrupoFormatoServicio();
		}
		return servicio;
	}

	public List<MenuGrupoFormato> obtenerMenusGrupoFormatoPorPreparador(Integer id_usuario, Integer id_negocio, Integer id_persona, Session session) {

		ProcesoServicio procesoServicio = ProcesoServicio.getInstance();
		List<Proceso> procesos = procesoServicio.listarProcesosPreparador(id_usuario, id_negocio, id_persona, null);

		Map<Integer, GrupoFormato> mapGruposFormato = new HashMap<Integer, GrupoFormato>();
		Map<GrupoFormato, List<ProcesoFormato>> mapGrupoProcesos = new HashMap<GrupoFormato, List<ProcesoFormato>>();

		List<Formato> formatosNegocio = FormatoServicio.getInstance().obtenerFormatosPorNegocio(id_negocio);

		List<Integer> id_formatos = new ArrayList<Integer>();

		if (CollectionUtils.isNotEmpty(formatosNegocio)) {
			for (Formato formato : formatosNegocio) {
				id_formatos.add(formato.getId_formato());
			}
		}

		for (Proceso proceso : procesos) {
			Integer id_grupoformato = proceso.getId_grupoformato();

			if (id_grupoformato != null) {

				// Si el grupo no existe lo crea
				if (!mapGruposFormato.containsKey(id_grupoformato)) {

					GrupoFormato grupoFormato = FormatoServicio.getInstance().obtenerGrupoFormato(id_grupoformato);

					mapGruposFormato.put(id_grupoformato, grupoFormato);

					mapGrupoProcesos.put(grupoFormato, new ArrayList<ProcesoFormato>());

				}

				GrupoFormato grupoFormato = mapGruposFormato.get(id_grupoformato);

				List<ProcesoFormato> listProcesosPorGrupo = mapGrupoProcesos.get(grupoFormato);

				List<Formato> formatosSinFiltrar = FormatoServicio.getInstance().obtenerFormatosProceso(proceso.getId_proceso());

				// Segun el grupoformato que tenga el proceso, se filtran
				// los formatos
				List<Formato> formatos = new ArrayList<Formato>();

				if (proceso.getId_grupoformato() != null && formatosSinFiltrar != null) {
					for (Formato formato : formatosSinFiltrar) {
						if (formato.getId_grupoformato() != null && formato.getId_grupoformato().equals(proceso.getId_grupoformato())
								&& (id_formatos.contains(formato.getId_formato()) || id_negocio == 0) && FormatoServicio.getInstance().validarFormatoListaDinamica(formato, session)) {
							formatos.add(formato);
						}

					}
				}

				ProcesoFormato procesoFormato = new ProcesoFormato();
				procesoFormato.setProceso(proceso);
				procesoFormato.setFormatoAsociados(formatos);

				listProcesosPorGrupo.add(procesoFormato);

			}// fin if-else

		}// fin for

		// Finalmente se crea la lista menu
		List<MenuGrupoFormato> menus = new ArrayList<MenuGrupoFormato>();

		Set<GrupoFormato> set = mapGrupoProcesos.keySet();

		for (GrupoFormato grupoFormato : set) {
			MenuGrupoFormato menu = new MenuGrupoFormato();
			menu.setGrupoFormato(grupoFormato);
			menu.setListaProcesoFormato(mapGrupoProcesos.get(grupoFormato));

			menus.add(menu);

		}

		return menus;
	}

}
