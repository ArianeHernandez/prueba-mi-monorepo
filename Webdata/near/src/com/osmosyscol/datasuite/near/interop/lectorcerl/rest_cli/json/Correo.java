
package com.osmosyscol.datasuite.near.interop.lectorcerl.rest_cli.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Correo {

    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("comercial")
    @Expose
    private String comercial;
    @SerializedName("notificacion")
    @Expose
    private String notificacion;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComercial() {
        return comercial;
    }

    public void setComercial(String comercial) {
        this.comercial = comercial;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

}
