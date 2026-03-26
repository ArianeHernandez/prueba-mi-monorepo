
package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Direccione {

    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("municipio")
    @Expose
    private String municipio;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

}
