
package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepresentantesLegale {

    @SerializedName("cargo")
    @Expose
    private String cargo;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("tipo_documento")
    @Expose
    private String tipoDocumento;
    @SerializedName("documento")
    @Expose
    private String documento;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

}
