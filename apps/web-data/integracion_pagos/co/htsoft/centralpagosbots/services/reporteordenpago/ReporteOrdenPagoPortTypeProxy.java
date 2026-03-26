package co.htsoft.centralpagosbots.services.reporteordenpago;

public class ReporteOrdenPagoPortTypeProxy implements co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoPortType {
  private String _endpoint = null;
  private co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoPortType reporteOrdenPagoPortType = null;
  
  public ReporteOrdenPagoPortTypeProxy() {
    _initReporteOrdenPagoPortTypeProxy();
  }
  
  public ReporteOrdenPagoPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initReporteOrdenPagoPortTypeProxy();
  }
  
  private void _initReporteOrdenPagoPortTypeProxy() {
    try {
      reporteOrdenPagoPortType = (new co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoLocator()).getReporteOrdenPagoHttpSoap11Endpoint();
      if (reporteOrdenPagoPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)reporteOrdenPagoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)reporteOrdenPagoPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (reporteOrdenPagoPortType != null)
      ((javax.xml.rpc.Stub)reporteOrdenPagoPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public co.htsoft.centralpagosbots.services.reporteordenpago.ReporteOrdenPagoPortType getReporteOrdenPagoPortType() {
    if (reporteOrdenPagoPortType == null)
      _initReporteOrdenPagoPortTypeProxy();
    return reporteOrdenPagoPortType;
  }
  
  public java.lang.String obtenerReporteOrdenPago(java.lang.Integer empresa_orden_pago, java.lang.Integer vigencia_orden_pago, java.lang.Integer numero_orden_pago) throws java.rmi.RemoteException{
    if (reporteOrdenPagoPortType == null)
      _initReporteOrdenPagoPortTypeProxy();
    return reporteOrdenPagoPortType.obtenerReporteOrdenPago(empresa_orden_pago, vigencia_orden_pago, numero_orden_pago);
  }
  
  
}