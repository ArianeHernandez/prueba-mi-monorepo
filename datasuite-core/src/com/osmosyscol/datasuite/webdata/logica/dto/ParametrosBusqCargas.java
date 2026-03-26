package com.osmosyscol.datasuite.webdata.logica.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.osmosyscol.datasuite.logica.servicios.ConfiguracionServicio;

public class ParametrosBusqCargas {

	private Integer id_usuario;
	
	private String estados;
	
	private Date fecha_inicial;
	
	private Date fecha_final;
	
	private Integer id_formato;
	
	private Integer id_tipocarga;
	
	private Integer id_negocio;
	
	private Integer ordenarPor;
	
	private Integer id_usuarioSesion;
	
	private Integer id_persona;
	
	private Integer id_administrativo;
	
	private Integer id_administrador;
	
	private Date fecha_inicial_lib;
	
	private Date fecha_final_lib;
	
	private Integer id_carga;
	
	public String getRevisor_consulta() {
		return ConfiguracionServicio.getInstance().obtenerValorByEtiqueta("REVISOR_CONSULTA");
	}

	public void setRevisor_consulta(String revisor_consulta) {
	}

	//Mapa para determinar el campo de orden en la búsqueda, evita sqlinjection
	private static Map<Integer, String> mapOrden = new HashMap<Integer, String>();
	
	static{
		mapOrden.put(1, "fecha_subida desc");
		mapOrden.put(2, "estado");
		mapOrden.put(3, "id_tipocarga");
	}
	//-------------------------------

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_cliente) {
		this.id_usuario = id_cliente;
	}

	public String getEstados() {
		return estados;
	}

	public void setEstados(String estados) {
		this.estados = estados;
	}

	public Date getFecha_inicial() {
		return fecha_inicial;
	}

	public void setFecha_inicial(Date fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}

	public Date getFecha_final() {
		return fecha_final;
	}

	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}

	public Integer getId_formato() {
		return id_formato;
	}

	public void setId_formato(Integer id_formato) {
		this.id_formato = id_formato;
	}

	public Integer getId_tipocarga() {
		return id_tipocarga;
	}

	public void setId_tipocarga(Integer tipo_carga) {
		this.id_tipocarga = tipo_carga;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Integer getOrdenarPor() {
		return ordenarPor;
	}

	public void setOrdenarPor(Integer ordenarPor) {
		this.ordenarPor = ordenarPor;
	}

	public Integer getId_usuarioSesion() {
		return id_usuarioSesion;
	}

	public void setId_usuarioSesion(Integer id_usuarioSesion) {
		this.id_usuarioSesion = id_usuarioSesion;
	}

	public Integer getId_administrativo() {
		return id_administrativo;
	}

	public void setId_administrativo(Integer id_administrativo) {
		this.id_administrativo = id_administrativo;
	}

	public Integer getId_persona() {
		return id_persona;
	}

	public void setId_persona(Integer id_persona) {
		this.id_persona = id_persona;
	}

	public String getCampoOrden(){
		return mapOrden.get(ordenarPor);
	}

	public Integer getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(Integer id_administrador) {
		this.id_administrador = id_administrador;
	}
	
	public String getFecha_inicial_formato(){
		if (fecha_inicial == null) {
			return null;
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(fecha_inicial);
	}
	
	public String getFecha_final_formato(){
		if (fecha_final == null) {
			return null;
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(fecha_final);
	}
	
	public Date getFecha_inicial_lib() {
		return fecha_inicial_lib;
	}

	public void setFecha_inicial_lib(Date fecha_inicial_lib) {
		this.fecha_inicial_lib = fecha_inicial_lib;
	}
	
	public Date getFecha_final_lib() {
		return fecha_final_lib;
	}

	public void setFecha_final_lib(Date fecha_final_lib) {
		this.fecha_final_lib = fecha_final_lib;
	}
	
	public String getFecha_inicial_formato_lib(){
		if (fecha_inicial_lib == null) {
			return null;
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(fecha_inicial_lib);
	}
	
	public String getFecha_final_lib_formato(){
		if (fecha_final_lib == null) {
			return null;
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(fecha_final_lib);
	}

	public Integer getId_carga() {
		return id_carga;
	}

	public void setId_carga(Integer id_carga) {
		this.id_carga = id_carga;
	}
	
}
