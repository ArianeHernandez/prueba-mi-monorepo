package co.htsoft.centralpagosbots.services.botservicio;

public class BotServicioPortTypeProxy implements co.htsoft.centralpagosbots.services.botservicio.BotServicioPortType {
  private String _endpoint = null;
  private co.htsoft.centralpagosbots.services.botservicio.BotServicioPortType botServicioPortType = null;
  
  public BotServicioPortTypeProxy() {
    _initBotServicioPortTypeProxy();
  }
  
  public BotServicioPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initBotServicioPortTypeProxy();
  }
  
  private void _initBotServicioPortTypeProxy() {
    try {
      botServicioPortType = (new co.htsoft.centralpagosbots.services.botservicio.BotServicioLocator()).getBotServicioHttpSoap11Endpoint();
      if (botServicioPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)botServicioPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)botServicioPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (botServicioPortType != null)
      ((javax.xml.rpc.Stub)botServicioPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.htsoft.centralpagosbots.services.botservicio.BotServicioPortType getBotServicioPortType() {
    if (botServicioPortType == null)
      _initBotServicioPortTypeProxy();
    return botServicioPortType;
  }
  
  public java.lang.Integer radicarSolicitud(java.lang.Integer empresa, java.lang.Integer vigencia_radicacion, java.lang.Integer id_solicitante, java.lang.String tipo_doc_presupuesto, java.lang.Integer vigencia_doc_presupuesto, java.lang.Integer numero_doc_presupuesto, java.lang.Double valor_total, co.htsoft.centralpagosbots.services.botservicio.Factura[] facturas) throws java.rmi.RemoteException{
    if (botServicioPortType == null)
      _initBotServicioPortTypeProxy();
    return botServicioPortType.radicarSolicitud(empresa, vigencia_radicacion, id_solicitante, tipo_doc_presupuesto, vigencia_doc_presupuesto, numero_doc_presupuesto, valor_total, facturas);
  }
  
  public java.lang.Integer anularSolicitud(java.lang.Integer empresa_orden_opago, java.lang.Integer vigencia_orden_pago, java.lang.Integer numero_orden_pago) throws java.rmi.RemoteException{
    if (botServicioPortType == null)
      _initBotServicioPortTypeProxy();
    return botServicioPortType.anularSolicitud(empresa_orden_opago, vigencia_orden_pago, numero_orden_pago);
  }
  
  
}