package com.osmosyscol.datasuite.mein.dtos;


public class Deudor {
	
	private Integer id;
	private Integer idCarga;
	private TipoDeDocumento tipo_identificacion;	
	private String tipo_identificacion_id;
	private String numero_identificacion;//TODO string
	private String razon_social;
	private String direccion;
	private Integer empleados_hombres;
	private Integer empleados_mujeres;
	private String correo_electronico;
	private String telefono;
	private String porcentaje_participacion;
	private MunicipioDane municipio;
	private Integer municipio_id;
	private DepartamentoDane departamento;
	private Integer departamento_id;
	private CodigoCIIU codigo_ciiu;
	private ObjGenerico macrosector;
	private ObjGenerico pais;
	private Integer pais_dane_id;
	private PaisDane pais_dane;
	private ObjGenerico naturaleza;//TODO cambiar objeto
	private PersonaOP representante_legal;
	private String certificado_existencia;
	private Integer id_representante_legal;
	private Integer certificado_matricula;
	private Integer representante_limitacion;
	private String autorizacion_organo_soc;
	private String archivo_autoriza_replegal;
	private String categoria;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}
	public String getNumero_identificacion() {
		return numero_identificacion;
	}
	public void setNumero_identificacion(String numero_identificacion) {
		this.numero_identificacion = numero_identificacion;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getEmpleados_hombres() {
		return empleados_hombres;
	}
	public void setEmpleados_hombres(Integer empleados_hombres) {
		this.empleados_hombres = empleados_hombres;
	}
	public Integer getEmpleados_mujeres() {
		return empleados_mujeres;
	}
	public void setEmpleados_mujeres(Integer empleados_mujeres) {
		this.empleados_mujeres = empleados_mujeres;
	}
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPorcentaje_participacion() {
		return porcentaje_participacion;
	}
	public void setPorcentaje_participacion(String porcentaje_participacion) {
		this.porcentaje_participacion = porcentaje_participacion;
	}
	public TipoDeDocumento getTipo_identificacion() {
		return tipo_identificacion;
	}
	public void setTipo_identificacion(TipoDeDocumento tipo_identificacion) {
		this.tipo_identificacion = tipo_identificacion;
	}
	public String getTipo_identificacion_id() {
		return tipo_identificacion_id;
	}
	public void setTipo_identificacion_id(String tipo_identificacion_id) {
		this.tipo_identificacion_id = tipo_identificacion_id;
	}
	public MunicipioDane getMunicipio() {
		return municipio;
	}
	public void setMunicipio(MunicipioDane municipio) {
		this.municipio = municipio;
	}
	public Integer getMunicipio_id() {
		return municipio_id;
	}
	public void setMunicipio_id(Integer municipio_id) {
		this.municipio_id = municipio_id;
	}
	public DepartamentoDane getDepartamento() {
		return departamento;
	}
	public void setDepartamento(DepartamentoDane departamento) {
		this.departamento = departamento;
	}
	public Integer getDepartamento_id() {
		return departamento_id;
	}
	public void setDepartamento_id(Integer departamento_id) {
		this.departamento_id = departamento_id;
	}
	public CodigoCIIU getCodigo_ciiu() {
		return codigo_ciiu;
	}
	public void setCodigo_ciiu(CodigoCIIU codigo_ciiu) {
		this.codigo_ciiu = codigo_ciiu;
	}
	public ObjGenerico getMacrosector() {
		return macrosector;
	}
	public void setMacrosector(ObjGenerico macrosector) {
		this.macrosector = macrosector;
	}
	public ObjGenerico getPais() {
		return pais;
	}
	public void setPais(ObjGenerico pais) {
		this.pais = pais;
	}
	public ObjGenerico getNaturaleza() {
		return naturaleza;
	}
	public void setNaturaleza(ObjGenerico naturaleza) {
		this.naturaleza = naturaleza;
	}
	public PersonaOP getRepresentante_legal() {
		return representante_legal;
	}
	public void setRepresentante_legal(PersonaOP representante_legal) {
		this.representante_legal = representante_legal;
	}
	public String getCertificado_existencia() {
		return certificado_existencia;
	}
	public void setCertificado_existencia(String certificado_existencia) {
		this.certificado_existencia = certificado_existencia;
	}
	public Integer getId_representante_legal() {
		return id_representante_legal;
	}
	public void setId_representante_legal(Integer id_representante_legal) {
		this.id_representante_legal = id_representante_legal;
	}
	public Integer getCertificado_matricula() {
		return certificado_matricula;
	}
	public void setCertificado_matricula(Integer certificado_matricula) {
		this.certificado_matricula = certificado_matricula;
	}
	public Integer getPais_dane_id() {
		return pais_dane_id;
	}
	public void setPais_dane_id(Integer pais_dane_id) {
		this.pais_dane_id = pais_dane_id;
	}
	public PaisDane getPais_dane() {
		return pais_dane;
	}
	public void setPais_dane(PaisDane pais_dane) {
		this.pais_dane = pais_dane;
	}
	public Integer getRepresentante_limitacion() {
		return representante_limitacion;
	}
	public void setRepresentante_limitacion(Integer representante_limitacion) {
		this.representante_limitacion = representante_limitacion;
	}
	public String getAutorizacion_organo_soc() {
		return autorizacion_organo_soc;
	}
	public void setAutorizacion_organo_soc(String autorizacion_organo_soc) {
		this.autorizacion_organo_soc = autorizacion_organo_soc;
	}
	public String getArchivo_autoriza_replegal() {
		return archivo_autoriza_replegal;
	}
	public void setArchivo_autoriza_replegal(String archivo_autoriza_replegal) {
		this.archivo_autoriza_replegal = archivo_autoriza_replegal;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
