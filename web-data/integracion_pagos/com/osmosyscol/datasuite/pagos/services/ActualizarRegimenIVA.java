package com.osmosyscol.datasuite.pagos.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.htsoft.commons.lang.P;

import com.itosmosys.www.datapi.servicios.consultar_contratos_vigentes.R_CONTRATOSArrayElement;
import com.osmosyscol.datasuite.logica.dto.Negocio;
import com.osmosyscol.datasuite.logica.dto.Usuario;
import com.osmosyscol.datasuite.logica.servicios.NegocioServicio;
import com.osmosyscol.datasuite.logica.servicios.UsuarioServicio;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class ActualizarRegimenIVA {

	private static ActualizarRegimenIVA instancia = new ActualizarRegimenIVA();

	public static ActualizarRegimenIVA getInstance() {
		return instancia;
	}

	public void actualizar(Integer id_usuario) {

		try {
			Usuario usuario = UsuarioServicio.getInstance().obtenerUsuario(id_usuario);
			String identificacion = usuario.getIdentificacion();

			DS_SqlUtils.delete("delete $REGIMEN IVA USUARIO$ where $REGIMEN IVA USUARIO.ID_USUARIO$ = $I(" + id_usuario + ")$");

			Set<String> riva = new HashSet<String>();

			List<Negocio> negocios = NegocioServicio.getInstance().obtenerListadoNegocios();

			P.println(negocios);

			if (negocios != null) {
				for (Negocio negocio : negocios) {

					if (negocio != null) {

						R_CONTRATOSArrayElement[] contratos = CentralPagosService.consultar_contratos_vigentes(Integer.parseInt(identificacion), negocio.getId_negocio());

						if (contratos != null) {
							for (R_CONTRATOSArrayElement c : contratos) {
								riva.add(c.getREGIMEN_IVA());
							}
						}
					}
				}
			}

			for (String r : riva) {
				DS_SqlUtils.insert("insert into $REGIMEN IVA USUARIO$ (id, idcarga, $REGIMEN IVA USUARIO.ID_USUARIO$, $REGIMEN IVA USUARIO.REGIMEN IVA$ ) values (sst.nextval , 0, $I(" + id_usuario + ")$, $S(" + r + ")$ )");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
