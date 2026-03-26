/**
 * BotServicioPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package co.htsoft.centralpagosbots.services.botservicio;

public interface BotServicioPortType extends java.rmi.Remote {
    public java.lang.Integer radicarSolicitud(java.lang.Integer empresa, java.lang.Integer vigencia_radicacion, java.lang.Integer id_solicitante, java.lang.String tipo_doc_presupuesto, java.lang.Integer vigencia_doc_presupuesto, java.lang.Integer numero_doc_presupuesto, java.lang.Double valor_total, co.htsoft.centralpagosbots.services.botservicio.Factura[] facturas) throws java.rmi.RemoteException;
    public java.lang.Integer anularSolicitud(java.lang.Integer empresa_orden_opago, java.lang.Integer vigencia_orden_pago, java.lang.Integer numero_orden_pago) throws java.rmi.RemoteException;
}
