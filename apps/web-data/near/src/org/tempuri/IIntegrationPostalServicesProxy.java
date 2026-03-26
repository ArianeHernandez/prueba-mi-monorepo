package org.tempuri;

public class IIntegrationPostalServicesProxy implements org.tempuri.IIntegrationPostalServices {
  private String _endpoint = null;
  private org.tempuri.IIntegrationPostalServices iIntegrationPostalServices = null;
  
  public IIntegrationPostalServicesProxy() {
    _initIIntegrationPostalServicesProxy();
  }
  
  public IIntegrationPostalServicesProxy(String endpoint) {
    _endpoint = endpoint;
    _initIIntegrationPostalServicesProxy();
  }
  
  private void _initIIntegrationPostalServicesProxy() {
    try {
      iIntegrationPostalServices = (new org.tempuri.IntegrationPostalServicesLocator()).getBasicHttpBinding_IIntegrationPostalServices();
      if (iIntegrationPostalServices != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iIntegrationPostalServices)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iIntegrationPostalServices)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iIntegrationPostalServices != null)
      ((javax.xml.rpc.Stub)iIntegrationPostalServices)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IIntegrationPostalServices getIIntegrationPostalServices() {
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices;
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass radicarEntrada(java.lang.String anexosFisicos, java.lang.String aplicaCiudadCodigo, java.lang.String aplicaDepartamentoCodigo, java.lang.String aplicaPaisCodigo, java.lang.String aplicaEmail, java.lang.String aplicaDireccion, java.lang.String aplicaNombre, java.lang.String aplicaTelefono, java.lang.String aplicaIdentificacion, java.lang.Integer aplicaTipoIdentificacionId, java.lang.String aplicaTipoIdentificacionNombre, java.lang.String particularIdentificacion, java.lang.String particularNombre, java.lang.Integer particularTipoIdentificacionId, java.lang.String particularTipoIdentificacionNombre, java.lang.String particularCiudadCodigo, java.lang.String particularDepartamentoCodigo, java.lang.String particularPaisCodigo, java.lang.String particularDireccion, java.lang.String particularTelefono, java.lang.String particularEmail, java.lang.Integer dependenciaId, java.lang.String dependenciaNombre, java.lang.Boolean entregaFisica, java.lang.Integer foliosNumero, java.lang.String referenciaExterna, java.lang.Integer cuadernoTipoId, java.lang.String cuadernoCodigo, java.lang.String documentalTipoCodigo, java.lang.Integer tramiteId, java.lang.Long tramiteCodigo, java.lang.String extensionArchivo, java.lang.Integer codigoMedioEnvio, java.lang.String codigoTipoSeguridad, java.lang.String codigoSerie, java.lang.String codigoSubSerie, java.lang.String loginUsuario, java.lang.String radicacionAnterior, java.lang.String nombreSerie, java.lang.String nombreSubSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicarEntrada(anexosFisicos, aplicaCiudadCodigo, aplicaDepartamentoCodigo, aplicaPaisCodigo, aplicaEmail, aplicaDireccion, aplicaNombre, aplicaTelefono, aplicaIdentificacion, aplicaTipoIdentificacionId, aplicaTipoIdentificacionNombre, particularIdentificacion, particularNombre, particularTipoIdentificacionId, particularTipoIdentificacionNombre, particularCiudadCodigo, particularDepartamentoCodigo, particularPaisCodigo, particularDireccion, particularTelefono, particularEmail, dependenciaId, dependenciaNombre, entregaFisica, foliosNumero, referenciaExterna, cuadernoTipoId, cuadernoCodigo, documentalTipoCodigo, tramiteId, tramiteCodigo, extensionArchivo, codigoMedioEnvio, codigoTipoSeguridad, codigoSerie, codigoSubSerie, loginUsuario, radicacionAnterior, nombreSerie, nombreSubSerie);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass radicarSalida(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida radicacionSalida) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicarSalida(radicacionSalida);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass radicarSalidaV2(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionSalida radicacionSalida) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicarSalidaV2(radicacionSalida);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaRadicacionInterna radicacion_RadicarInterna(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionInterna radicacionInterna) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_RadicarInterna(radicacionInterna);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPM(java.lang.String refBase, java.lang.String nombreTermino, java.lang.String radicacion, java.lang.String tipoDocumento, java.lang.String totalDocumentos, java.lang.String uploadBy) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.subirArchivoDesdeProcesoBPM(refBase, nombreTermino, radicacion, tipoDocumento, totalDocumentos, uploadBy);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass subirArchivoDesdeProcesoBPMV2(java.lang.String refBase, java.lang.String nombreTermino, java.lang.String radicacion, java.lang.String tipoDocumento) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.subirArchivoDesdeProcesoBPMV2(refBase, nombreTermino, radicacion, tipoDocumento);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass recuperarDocumentos(java.lang.String refBase, java.lang.Integer idTerminoBiblioteca, java.lang.String numeroRadicado, java.lang.Boolean todos) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.recuperarDocumentos(refBase, idTerminoBiblioteca, numeroRadicado, todos);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass verificarDocumentoPrincipal(java.lang.String numeroRadicado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.verificarDocumentoPrincipal(numeroRadicado);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass contarPaginasDocPrincipal(java.lang.String referencia, java.lang.String nombreBiblioteca) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.contarPaginasDocPrincipal(referencia, nombreBiblioteca);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass actualizarFoliosRadicacion(java.lang.String radicado, java.lang.Integer numeroFolios) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.actualizarFoliosRadicacion(radicado, numeroFolios);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.IBPMWSDevolucionClass enrutarRadicacion(java.lang.String radicacionNumero, java.lang.String usuarioEnrutaCodigo, java.lang.Long dependenciaDestinoCodigo, java.lang.String usuarioDestinoCodigo, java.lang.String asunto) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.enrutarRadicacion(radicacionNumero, usuarioEnrutaCodigo, dependenciaDestinoCodigo, usuarioDestinoCodigo, asunto);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo obtenerPorCodigo(java.lang.String codigoCarta) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerPorCodigo(codigoCarta);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.CartaModelo[] obtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerTodos();
  }
  
  public void perfil_ActualizarFechaCorte(java.lang.String numero, java.util.Calendar fechaCorte) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    iIntegrationPostalServices.perfil_ActualizarFechaCorte(numero, fechaCorte);
  }
  
  public java.lang.String perfil_ObtenerDigitalizacionEstado(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.perfil_ObtenerDigitalizacionEstado(numero);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] perfil_ObtenerDocumentos(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.perfil_ObtenerDocumentos(numero);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ObtenerDocumentosByConsecutivoResult perfil_ObtenerDocumentosByConsecutivo(java.lang.String numero, java.lang.String consecutivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.perfil_ObtenerDocumentosByConsecutivo(numero, consecutivo);
  }
  
  public byte[] archivo_Bajar(java.lang.String radicacion, java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.archivo_Bajar(radicacion, nombre);
  }
  
  public java.lang.String archivo_BajarBase(java.lang.String radicacion, java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.archivo_BajarBase(radicacion, nombre);
  }
  
  public byte[] archivo_BajarV2(java.lang.String radicacion, java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.archivo_BajarV2(radicacion, nombre);
  }
  
  public void archivo_Subir(byte[] archivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    iIntegrationPostalServices.archivo_Subir(archivo);
  }
  
  public java.lang.String archivo_SubirPostal(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.ArchivoUploadDTOB request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.archivo_SubirPostal(request);
  }
  
  public java.lang.String radicacion_Anular(java.lang.String request, java.lang.String observacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_Anular(request, observacion);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_Modificar(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_Modificar(request);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoSimpleOfRadicacionPlanoPzrEYndD radicacion_ModificarPlano(org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_ModificarPlano(request);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_Obtener(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_Obtener(numero);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_ObtenerV2(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_ObtenerV2(numero);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.RadicacionPlano radicacion_ObtenerPlano(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_ObtenerPlano(numero);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.RadicacionDatosBasicos radicacion_Obtener_DatosBasicos(java.lang.String numero) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_Obtener_DatosBasicos(numero);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion radicacion_Radicar(org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion request) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_Radicar(request);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitados() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.medioEnvio_ObtenerHabilitados();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerHabilitadosPorRadicacionTipo(java.lang.String radicacionTipo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.medioEnvio_ObtenerHabilitadosPorRadicacionTipo(radicacionTipo);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.medioEnvio_ObtenerPorId(id);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio medioEnvio_ObtenerPorCodigo(java.lang.Long codigo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.medioEnvio_ObtenerPorCodigo(codigo);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.MedioDeEnvio[] medioEnvio_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.medioEnvio_ObtenerTodos();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorCodigo(java.lang.String code) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoDocumental_ObtenerPorCodigo(code);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental tipoDocumental_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoDocumental_ObtenerPorId(id);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoDocumental_ObtenerTodos();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitados() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tramite_ObtenerHabilitados();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerHabilitadosPorRadicacionTipo(java.lang.Integer radicacionTipo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tramite_ObtenerHabilitadosPorRadicacionTipo(radicacionTipo);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorCodigo(java.lang.Long code) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tramite_ObtenerPorCodigo(code);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite tramite_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tramite_ObtenerPorId(id);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Tramite[] tramite_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tramite_ObtenerTodos();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerHabilitados() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoSeguridad_ObtenerHabilitados();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorCodigo(java.lang.String code) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoSeguridad_ObtenerPorCodigo(code);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad tipoSeguridad_ObtenerPorId(java.lang.Long id) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoSeguridad_ObtenerPorId(id);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.DocumentoTipoSeguridad[] tipoSeguridad_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoSeguridad_ObtenerTodos();
  }
  
  public java.lang.String documento_Subir(byte[] archivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.documento_Subir(archivo);
  }
  
  public void documento_Borrar(java.lang.String nombre) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    iIntegrationPostalServices.documento_Borrar(nombre);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl autosControl(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA consultaAutosControlEA) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.autosControl(consultaAutosControlEA);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosControl sinPostergadosAutosControl(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControlEA consultaAutosControlEA) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.sinPostergadosAutosControl(consultaAutosControlEA);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaAutosAutomaticoGeneral autosAutomatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaAutosControl consultaAutosControl) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.autosAutomatico(consultaAutosControl);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaEstados(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaEstados datosConsultaEstados) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultaEstados(datosConsultaEstados);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaPostal consultaRadicado(org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.DatosConsultaRadicado datosConsultaRadicado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultaRadicado(datosConsultaRadicado);
  }
  
  public java.lang.Integer modificarRadicadosTraslados(java.lang.Integer idProceso) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.modificarRadicadosTraslados(idProceso);
  }
  
  public java.lang.Integer modificarAutosEstados(java.lang.Integer idProceso) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.modificarAutosEstados(idProceso);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Borrador crearBorrador(com.tandem.postal.dto.Borrador borradorData) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.crearBorrador(borradorData);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Radicacion oficializarBorrador(java.lang.String numeroBorrador, java.lang.String funcionarioFirmaCerificaciones) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.oficializarBorrador(numeroBorrador, funcionarioFirmaCerificaciones);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.Documento[] obtenerDocumentos(java.lang.String numeroRadicacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerDocumentos(numeroRadicacion);
  }
  
  public java.lang.String subirBorrador(byte[] archivo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.subirBorrador(archivo);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaConsultaEstadosTraslados consultaEstadosTraslados(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaEstadosTraslados filtro) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultaEstadosTraslados(filtro);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ResultActualizaEstadoPostal actualizaEstadoPostal(java.lang.String numRadicado, java.lang.Integer codigoTramite, java.lang.Integer tipoDocumento) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.actualizaEstadoPostal(numRadicado, codigoTramite, tipoDocumento);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RepuestaConsultaAutos consultaAutos(java.lang.String tipoDocumento, java.lang.String tipoNotificacion, java.util.Calendar fechaEstado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultaAutos(tipoDocumento, tipoNotificacion, fechaEstado);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.RespuestaTrasladosAutomatico traslados_Automatico(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosAutomatico_Input trasladosAutomatico_Input) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.traslados_Automatico(trasladosAutomatico_Input);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta trasladosRadicado(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.TrasladosRadicadoInput trasladosRadicadoInput) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.trasladosRadicado(trasladosRadicadoInput);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_BPM.RespuestaConsulta consultaTraslados(org.datacontract.schemas._2004._07.InteropSuper_Modelos_Postal.ConsultaTrasladosInput consultaTrasladosInput) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultaTraslados(consultaTrasladosInput);
  }
  
  public java.lang.String radicacion_RadicarMasivo() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.radicacion_RadicarMasivo();
  }
  
  public com.tandem.postal.dto.MedioDeEnvio[] obtenerRadicacionTipoTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerRadicacionTipoTodos();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfPROFILEOyDEkVga consultarRadicaciones(java.lang.String codigoDependencia, java.lang.Integer tipoDocumento, java.lang.String fecha) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultarRadicaciones(codigoDependencia, tipoDocumento, fecha);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfMedioDeEnvioCloudUyyCEAza obtenerTipoCanalPorTipoRadicacion(java.lang.String tipoRadicacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerTipoCanalPorTipoRadicacion(tipoRadicacion);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_SIGS_Resultados.ResultadoOfDocumentoTipoSeguridadCloudUyyCEAza obtenerTipoSeguridadPorTipoRadicacion(java.lang.String tipoRadicacion) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerTipoSeguridadPorTipoRadicacion(tipoRadicacion);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerSeriesPorCodigoTramite(java.lang.String codTramite) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerSeriesPorCodigoTramite(codTramite);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfRespuestaGeneral_PHy_PfD4I obtenerTipoExpedientePorCodigoTramite(java.lang.String codTramite) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerTipoExpedientePorCodigoTramite(codTramite);
  }
  
  public java.lang.Boolean consultarRadicacionesporFechas(int[] tipoDocumento, java.lang.String fecha, java.lang.String hora) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.consultarRadicacionesporFechas(tipoDocumento, fecha, hora);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I obtenerSubSeriesPorCodigoSerie(java.lang.Integer codigoSubSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerSubSeriesPorCodigoSerie(codigoSubSerie);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfListaParametros1MTlaX0G obtenerParametrosPorIdTipo(java.lang.Integer idTipo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.obtenerParametrosPorIdTipo(idTipo);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Entidades.Cuaderno cuaderno_ObtenerPorCodigo(java.lang.Integer codigo) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.cuaderno_ObtenerPorCodigo(codigo);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serie_ObtenerPorCodigo(java.lang.Integer codigoSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.serie_ObtenerPorCodigo(codigoSerie);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfSubSerie_PHy_PfD4I subserie_ObtenerPorCodigo(java.lang.Integer codigoSubSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.subserie_ObtenerPorCodigo(codigoSubSerie);
  }
  
  public java.lang.String determinarEstadoSwitch() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.determinarEstadoSwitch();
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOfCuadernoUyyCEAza cuadernoActivos_ObtenerPorSerie(java.lang.Integer codigoSerie) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.cuadernoActivos_ObtenerPorSerie(codigoSerie);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD serieActivas_ObtenerPorDependencia(java.lang.Integer codigoDependencia) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.serieActivas_ObtenerPorDependencia(codigoDependencia);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorEstado(java.lang.String estado) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoDocumental_ObtenerPorEstado(estado);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos.TipoDocumental[] tipoDocumental_ObtenerPorDependencia(java.lang.String codigoDependencia) throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.tipoDocumental_ObtenerPorDependencia(codigoDependencia);
  }
  
  public org.datacontract.schemas._2004._07.InteropSuper_Modelos_Comun.RespuestaOftramiteSeriesPzrEYndD seriesActivas_ObtenerTodos() throws java.rmi.RemoteException, org.datacontract.schemas._2004._07.System_ServiceModel.FaultException{
    if (iIntegrationPostalServices == null)
      _initIIntegrationPostalServicesProxy();
    return iIntegrationPostalServices.seriesActivas_ObtenerTodos();
  }
  
  
}