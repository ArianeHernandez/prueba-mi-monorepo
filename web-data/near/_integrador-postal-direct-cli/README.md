

## DEPLOYMENT

* configurar las propiedades que apuntan a: 
	- IntgradorPostal
	- ServicioPostal



## SCAFFOLD

### POSTAL WS CLIENT
1: genear cliente

descargar axis1 de [aqui](https://downloads.apache.org/axis/axis/java/1.4/)
descomprimir tar -xvf axis-bin-1_4.tar.gz
configurar properties con el axis home

2: axis1 envio attachments

axis1 no maneja naturalmente los binary attachments
* https://stackoverflow.com/questions/9729104/axis-client-v-s-axis2-service/9778563#9778563
* https://forums.asp.net/t/960666.aspx?Sending+SOAP+attachments+from+Axis+client+to+NET+web+service
* https://forums.asp.net/t/960666.aspx?Sending+SOAP+attachments+from+Axis+client+to+NET+web+service
axis1 no maneja bien lso heaers
* https://stackoverflow.com/questions/21838130/adding-soap-header-authentication-to-wsdl2java-generated-code
* https://coderanch.com/t/599193/java/axis-remove-mustUnderstand-actor-attributes

la forma más adecuada para hacerlo es manejarlo con un datahandler, indicandole el modo de 
transferencia del archivo (MTOM, MULTIPART, etc).
En las pruebas preliminares el objeto se envia como un array de strings donde cada posicion es un byte del attachment.   
Por ello se cambia el wsdl, para que el stub generado, trate al archivo como un string y se envia el codificado en b64. en conclusión, para el manejo temporal se hace el cambio en wsdl y regenrera el cliente; 

cambiar:
```
                        <xs:element name="Archivo" type="q17:StreamBody" 
                            xmlns:q17="http://schemas.microsoft.com/Message"/>

```
por: 
                        <xs:element name="Archivo" type="xs:string" type="q17:StreamBody" 
                            xmlns:q17="http://schemas.microsoft.com/Message"/>
```
```



# Metodos servicio

* Archivo_Subir
enviar el archivo a postal.
requiere heaers
requeire enviar el contenido codificado en b64

* Archivo_Bajar
verificar el archivo
primero se debe lanzar Perfil_ObtenerDocumentos con el radicado para ver 
el nombre con el que queda el archivo.
posteriormente descargarlo indicando este nombre


# Ejemplos axis1

```

	

/*
I am trying to send a file with an axis1.4 client to a jaxws service. My client code is like below.

System.out.println(service.getCalcImplPort().getFile(new DataHandler(new DataSource() {

            @Override
            public OutputStream getOutputStream() throws IOException {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String getName() {
                // TODO Auto-generated method stub
                return "abc.txt";
            }

            @Override
            public InputStream getInputStream() throws IOException {
                InputStream bs = new ByteArrayInputStream("Hello world".getBytes());
                return bs;
            }

            @Override
            public String getContentType() {
                // TODO Auto-generated method stub
                return "application/soap+xml";
            }
        })));
	
    void attachFile(IIntegrationPostalServicesclient client, byte[] archivo) throws java.rmi.RemoteException, com.osmosyscol.datasuite.near.interop.postal.FaultException {
        com.osmosyscol.datasuite.near.interop.postal.BasicHttpBinding_IIntegrationPostalServicesStub stub =(com.osmosyscol.datasuite.near.interop.postal.BasicHttpBinding_IIntegrationPostalServicesStub)client;
        org.apache.axis.client.Call _call = stub._createCall();
        //_call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/IIntegrationPostalServices/Archivo_Subir");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "ArchivoUploadDto"));
        
        _call.addAttachmentPart(archivo);
	
    }
    
	void interceptStub(IIntegrationPostalServices client, byte[] archivo){
        com.osmosyscol.datasuite.near.interop.postal.BasicHttpBinding_IIntegrationPostalServicesStub _stub =(com.osmosyscol.datasuite.near.interop.postal.BasicHttpBinding_IIntegrationPostalServicesStub)client;
        
        _stub._setProperty(Call.ATTACHMENT_ENCAPSULATION_FORMAT, Call.ATTACHMENT_ENCAPSULATION_FORMAT_DIME);
        _stub.addAttachment(archivo);
        
        
	}
    
*/
    
    
    

```