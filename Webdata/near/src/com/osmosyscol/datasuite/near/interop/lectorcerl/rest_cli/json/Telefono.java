
package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Telefono {

    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("telefono")
    @Expose
    private String telefono;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
