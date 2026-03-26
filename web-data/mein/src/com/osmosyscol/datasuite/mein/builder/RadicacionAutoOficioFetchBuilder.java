package com.osmosyscol.datasuite.mein.builder;

import com.osmosyscol.datasuite.mein.dtos.APVistaCuaderno;
import com.osmosyscol.datasuite.mein.dtos.APVistaDependencias;
import com.osmosyscol.datasuite.mein.dtos.APVistaDocumentoTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaMedioEnvio;
import com.osmosyscol.datasuite.mein.dtos.APVistaSeguridadTipo;
import com.osmosyscol.datasuite.mein.dtos.APVistaSerie;
import com.osmosyscol.datasuite.mein.dtos.APVistaSubserie;
import com.osmosyscol.datasuite.mein.dtos.APVistaTipoAutoActa;
import com.osmosyscol.datasuite.mein.dtos.APVistaTramite;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoCiudad;
import com.osmosyscol.datasuite.mein.dtos.DepartamentoDane;
import com.osmosyscol.datasuite.mein.dtos.EnrolamientoCliente;
import com.osmosyscol.datasuite.mein.dtos.MunicipioDane;
import com.osmosyscol.datasuite.mein.dtos.PaisDane;
import com.osmosyscol.datasuite.mein.dtos.ParticularPostal;
import com.osmosyscol.datasuite.mein.dtos.RadicacionAutoOficio;
import com.osmosyscol.datasuite.mein.dtos.TipoDeDocumento;
import com.osmosyscol.datasuite.utils.DS_SqlUtils;

public class RadicacionAutoOficioFetchBuilder {

	private RadicacionAutoOficioFetchBuilder () {}
	
	public static RadicacionAutoOficioFetchBuilder newInstance() {
		return new RadicacionAutoOficioFetchBuilder();
	}
	
	private RadicacionAutoOficio radicacion = new RadicacionAutoOficio();
	private Integer idCarga;
	
	public RadicacionAutoOficioFetchBuilder withIdCarga (Integer idCarga) {
		this.idCarga = idCarga;
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion() {
		this.radicacion = DS_SqlUtils.queryForObject(RadicacionAutoOficio.class, 
				"select radicacion.*, " +
				" radicacion.$radicacion auto oficio.deudor$ deudorId, " +
				" radicacion.$radicacion auto oficio.particular$ particularId, " +
				" radicacion.$radicacion auto oficio.dependenciaasignada$ dependenciaAsignadaId, " +
				" radicacion.$radicacion auto oficio.codigomedioenvio$ codigoMedioenvioId, " +
				" radicacion.$radicacion auto oficio.codigotiposeguridad$ codigoTipoSeguridadId, " +
				" radicacion.$radicacion auto oficio.corresponsaldependencia$ corresponsalDependenciaId, " +
				" radicacion.$radicacion auto oficio.dependencia$ dependenciaId, " +
				" radicacion.$radicacion auto oficio.documentaltipo$ documentalTipoId, " +
				" radicacion.$radicacion auto oficio.cuadernotipo$ cuadernoTipoId, " +
				" radicacion.$radicacion auto oficio.tramite$ tramiteId, " +
				" radicacion.$radicacion auto oficio.fecha radicado$ fechaRadicado, " +
				" radicacion.$radicacion auto oficio.tipo auto acta$ tipoAutoActaId, " +
				" radicacion.$radicacion auto oficio.serie$ serieId, " +
				" radicacion.$radicacion auto oficio.subserie$ subserieId " +
				" from $radicacion auto oficio$ radicacion " +
				" where radicacion.idcarga = " + idCarga);
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Particular () {
		
		if ( this.radicacion != null && this.radicacion.getParticularId() != null ) {
			ParticularPostal particularPostal = DS_SqlUtils.queryForObject(ParticularPostal.class, 
				"select particular.*, " +
				" particular.$particular postal.tipo identificacion$ tipo_identificacion_id, " +
				" particular.$particular postal.ciudadcodigo$ ciudad_codigo_id " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $particular postal$ particular on radicacion.$radicacion auto oficio.particular$ = particular.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setParticular(particularPostal);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Dependencia () {
		
		if (this.radicacion != null && this.radicacion.getDependenciaId() != null ) {
			APVistaDependencias dependencia = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select dependencia.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista dependencias$ dependencia on radicacion.$radicacion auto oficio.dependencia$ = dependencia.id " + 
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setDependencia(dependencia);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_DependenciaAsignada () {
		
		if (this.radicacion != null && this.radicacion.getDependenciaAsignadaId() != null ) {
			APVistaDependencias dependencia = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select dependencia.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista dependencias$ dependencia on radicacion.$radicacion auto oficio.dependenciaasignada$ = dependencia.id " + 
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setDependenciaAsignada(dependencia);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_CorresponsalDependencia () {
		
		if (this.radicacion != null && this.radicacion.getCorresponsalDependenciaId() != null ) {
			APVistaDependencias dependencia = DS_SqlUtils.queryForObject(APVistaDependencias.class, 
				"select dependencia.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista dependencias$ dependencia on radicacion.$radicacion auto oficio.corresponsaldependencia$ = dependencia.id " + 
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setCorresponsalDependencia(dependencia);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_CodigoTipoSeguridad () {
		
		if (this.radicacion != null && this.radicacion.getCodigoTipoSeguridadId() != null ) {
			APVistaSeguridadTipo codigoSeguridadTipo = DS_SqlUtils.queryForObject(APVistaSeguridadTipo.class, 
				"select codigo_seguridad.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista seguridad tipo$ codigo_seguridad on radicacion.$radicacion auto oficio.codigotiposeguridad$ = codigo_seguridad.id " + 
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setCodigoTipoSeguridad(codigoSeguridadTipo);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_CodigoMedioEnvio () {
		
		if (this.radicacion != null && this.radicacion.getCodigoMedioEnvioId() != null ) {
			APVistaMedioEnvio codigoMedioEnvio = DS_SqlUtils.queryForObject(APVistaMedioEnvio.class, 
				"select medio_envio.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista medio envio$ medio_envio on radicacion.$radicacion auto oficio.codigomedioenvio$ = medio_envio.id " + 
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setCodigoMedioEnvio(codigoMedioEnvio);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_DocumentalTipo () {
		
		if (this.radicacion != null && this.radicacion.getDocumentalTipoId() != null) {
			APVistaDocumentoTipo documentalTipo = DS_SqlUtils.queryForObject(APVistaDocumentoTipo.class, 
				"select documental.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista documento tipo$ documental on radicacion.$radicacion auto oficio.documentaltipo$ = documental.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setDocumentalTipo(documentalTipo);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_CuadernoTipo () {
		
		if (this.radicacion != null && this.radicacion.getCuadernoTipoId() != null) {
			APVistaCuaderno cuadernoTipo = DS_SqlUtils.queryForObject(APVistaCuaderno.class, 
				"select cuaderno.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista cuaderno$ cuaderno on radicacion.$radicacion auto oficio.cuadernotipo$ = cuaderno.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setCuadernoTipo(cuadernoTipo);
		}
		
		return this;
		
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Tramite () {
		
		if (this.radicacion != null && this.radicacion.getTramiteId() != null) {
			APVistaTramite tramite = DS_SqlUtils.queryForObject(APVistaTramite.class, 
				"select tramite.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista tramite$ tramite on radicacion.$radicacion auto oficio.tramite$ = tramite.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setTramite(tramite);
		}
		
		return this;
		
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_TipoAutoActa () {
		
		if (this.radicacion != null && this.radicacion.getTipoAutoActaId() != null) {
			APVistaTipoAutoActa tipoAutoActa = DS_SqlUtils.queryForObject(APVistaTipoAutoActa.class, 
				"select tipoAutoActa.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista tipo auto acta$ tipoAutoActa on radicacion.$radicacion auto oficio.tipo auto acta$ = tipoAutoActa.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setTipoAutoActa(tipoAutoActa);
		}
		
		return this;
		
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Deudor () {
		
		if (this.radicacion != null && this.radicacion.getDeudorId() != null) {
			EnrolamientoCliente deudor = DS_SqlUtils.queryForObject(EnrolamientoCliente.class, 
				"select deudor.*, " +
				" deudor.$enrolamiento cliente.tipo identificacion$ tipo_identificacion_id, " +
				" deudor.$enrolamiento cliente.pais$ pais_id " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $enrolamiento cliente$ deudor on radicacion.$radicacion auto oficio.deudor$ = deudor.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setDeudor(deudor);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Deudor_TipoIdentificacion () {
		if (this.radicacion != null && this.radicacion.getDeudor() != null
				&& this.radicacion.getDeudor().getTipo_identificacion_id() != null) {
			TipoDeDocumento tipoIdentificacion = DS_SqlUtils.queryForObject(TipoDeDocumento.class, 
				"select tipo_documento.* " +
				" from $enrolamiento cliente$ deudor " +
				" left join $tipo de documento$ tipo_documento on deudor.$enrolamiento cliente.tipo identificacion$ = tipo_documento.id " +
				" where deudor.id = " + this.radicacion.getDeudor().getId());
			
			this.radicacion.getDeudor().setTipo_identificacion(tipoIdentificacion);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Deudor_Pais () {
		if (this.radicacion != null && this.radicacion.getDeudor() != null
				&& this.radicacion.getDeudor().getPais_id() != null) {
			PaisDane pais = DS_SqlUtils.queryForObject(PaisDane.class, 
				"select pais.* " +
				" from $enrolamiento cliente$ deudor " +
				" left join $paises dane$ pais on deudor.$enrolamiento cliente.pais$ = pais.id " +
				" where deudor.id = " + this.radicacion.getDeudor().getId());
			
			this.radicacion.getDeudor().setPais(pais);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Deudor_Departamento() {
		if (this.radicacion != null && this.radicacion.getDeudor() != null
				&& this.radicacion.getDeudor().getDepartamento_dane() != null) {
			DepartamentoDane departamento = DS_SqlUtils.queryForObject(DepartamentoDane.class, 
					"select departamento.* " + 
							" from $enrolamiento cliente$ deudor " +
							" left join $departamentos dane$ departamento on deudor.$enrolamiento cliente.departamento dane$ = departamento.id " +
							" where deudor.id = " + this.radicacion.getDeudor().getId());
			this.radicacion.getDeudor().setDepartamento_dane_obj(departamento);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Deudor_Municipio() {
		if (this.radicacion != null && this.radicacion.getDeudor() != null
				&& this.radicacion.getDeudor().getMunicipio_dane() != null) {
			MunicipioDane municipio = DS_SqlUtils.queryForObject(MunicipioDane.class, 
					"select municipio.* " + 
							" from $enrolamiento cliente$ deudor " +
							" left join $municipios dane$ municipio on deudor.$enrolamiento cliente.municipio dane$ = municipio.id " +
							" where deudor.id = " + this.radicacion.getDeudor().getId());
			this.radicacion.getDeudor().setMunicipio_dane_obj(municipio);
		}
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Particular_TipoIdentificacion () {
		if (this.radicacion != null && this.radicacion.getParticular() != null
				&& this.radicacion.getParticular().getTipo_identificacion_id() != null) {
			TipoDeDocumento tipoIdentificacion = DS_SqlUtils.queryForObject(TipoDeDocumento.class, 
				"select tipo_documento.id, " +
				" tipo_documento.$tipo de documento.nombre$," +
				" tipo_documento.$tipo de documento.codigo$," +
				" tipo_documento.$tipo de documento.id postal$," +
				" tipo_documento.$tipo de documento.nombre postal$ " +
				" from $particular postal$ particular " +
				" left join $tipo de documento$ tipo_documento on particular.$particular postal.tipo identificacion$ = tipo_documento.id " +
				" where particular.id = " + this.radicacion.getParticular().getId());
			
			this.radicacion.getParticular().setTipo_identificacion(tipoIdentificacion);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Particular_CiudadCodigo () {
		if (this.radicacion != null && this.radicacion.getParticular() != null
				&& this.radicacion.getParticular().getCiudad_codigo_id() != null) {	
			MunicipioDane ciudadCodigo = DS_SqlUtils.queryForObject(MunicipioDane.class, 
				"select ciudad.* " +
				" from $particular postal$ particular " +
				" left join $municipios dane$ ciudad on particular.$particular postal.ciudadcodigo$ = ciudad.id " +
				" where particular.id = " + this.radicacion.getParticular().getId());
			
			if (ciudadCodigo != null) {
				DepartamentoDane departamento = DS_SqlUtils.queryForObject(DepartamentoDane.class, 
					"select departamento.* " +
					" from $municipios dane$ ciudad " +
					" left join $departamentos dane$ departamento on ciudad.$municipios dane.codigo departamento$ = departamento.id " +
					" where ciudad.id = " + ciudadCodigo.getId());
				
				if (departamento != null) {
					PaisDane pais = DS_SqlUtils.queryForObject(PaisDane.class, 
						"select pais.* " +
						" from $departamentos dane$ departamento " +
						" left join $paises dane$ pais on departamento.$departamentos dane.codigo pais$ = pais.id " +
						" where departamento.id = " + departamento.getId());
					
					departamento.setPais(pais);
				}
				
				ciudadCodigo.setDepartamento(departamento);
			}
			
			this.radicacion.getParticular().setCiudad(ciudadCodigo);
		}
		
		return this;
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Serie () {
		
		if (this.radicacion != null && this.radicacion.getSerieId() != null) {
			APVistaSerie serie = DS_SqlUtils.queryForObject(APVistaSerie.class, 
				"select serie.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista serie$ serie on radicacion.$radicacion auto oficio.serie$ = serie.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setSerie(serie);
		}
		
		return this;
		
	}
	
	public RadicacionAutoOficioFetchBuilder fetchRadicacion_Subserie () {
		
		if (this.radicacion != null && this.radicacion.getSubserieId() != null) {
			APVistaSubserie subserie = DS_SqlUtils.queryForObject(APVistaSubserie.class, 
				"select subserie.* " +
				" from $radicacion auto oficio$ radicacion " +
				" left join $apvista subserie$ subserie on radicacion.$radicacion auto oficio.subserie$ = subserie.id " +
				" where radicacion.idcarga = " + idCarga);
			
			this.radicacion.setSubserie(subserie);
		}
		
		return this;
		
	}
	
	public RadicacionAutoOficio getRadicacion() {
		return this.radicacion;
	}
	
}
