
package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOut {

    @SerializedName("razon_social")
    @Expose
    private String razonSocial;
    @SerializedName("nit")
    @Expose
    private String nit;
    @SerializedName("matricula")
    @Expose
    private String matricula;
    @SerializedName("fecha_matricula")
    @Expose
    private String fechaMatricula;
    @SerializedName("ultimo_a\u00f1o_renovado")
    @Expose
    private String ultimoAORenovado;
    @SerializedName("fecha_de_renovacion")
    @Expose
    private String fechaDeRenovacion;
    @SerializedName("direcciones")
    @Expose
    private List<Direccione> direcciones = new ArrayList<Direccione>();
    @SerializedName("telefonos")
    @Expose
    private List<Telefono> telefonos = new ArrayList<Telefono>();
    @SerializedName("correos")
    @Expose
    private List<Correo> correos = new ArrayList<Correo>();
    @SerializedName("representantes_legales")
    @Expose
    private List<RepresentantesLegale> representantesLegales = new ArrayList<RepresentantesLegale>();

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public String getUltimoAORenovado() {
        return ultimoAORenovado;
    }

    public void setUltimoAORenovado(String ultimoAORenovado) {
        this.ultimoAORenovado = ultimoAORenovado;
    }

    public String getFechaDeRenovacion() {
        return fechaDeRenovacion;
    }

    public void setFechaDeRenovacion(String fechaDeRenovacion) {
        this.fechaDeRenovacion = fechaDeRenovacion;
    }

    public List<Direccione> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccione> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Correo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<Correo> correos) {
        this.correos = correos;
    }

    public List<RepresentantesLegale> getRepresentantesLegales() {
        return representantesLegales;
    }

    public void setRepresentantesLegales(List<RepresentantesLegale> representantesLegales) {
        this.representantesLegales = representantesLegales;
    }

}
