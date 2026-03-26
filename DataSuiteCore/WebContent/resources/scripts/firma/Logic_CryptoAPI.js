/*----------------------------------------------------------------------*/
/* Módulo de criptografía para formularios Web							*/
/*----------------------------------------------------------------------*/
/* Autores:	Alex Vicente Chacón	Jiménez									*/
/* Versión:	1.1															*/
/* Editor:	Visual Studio .NET 											*/
/*----------------------------------------------------------------------*/
/* Certicámara S.A. (Todos los derechos reservados)						*/
/*																		*/
/* Bogotá, Colombia 22 de Diciembre del 2007							*/
/*----------------------------------------------------------------------*/

/*----------------------------------------------------------------------*/
/* Declaración de constantes de CAPICOM									*/
/*----------------------------------------------------------------------*/
var CAPICOM_AUTHENTICATED_ATTRIBUTE_SIGNING_TIME = 0;
var CAPICOM_DETACHED = true;
var CAPICOM_ENCODIG_TYPE = 0;

/*----------------------------------------------------------------------*/
/* Definición de excepciones											*/
/*----------------------------------------------------------------------*/
var capicomError = new Error ("La librería de criptografía de Windows ::CAPICOM:: no se encuentra instalada.");
var cryptoErrorNoMatchingCert = new Error ("El certificado seleccionado por el usuario no fue emitido por una Autoridad de Certificación autorizada por el sistema.");
var cryptoErrorUserCancel = new Error ("El usuario ha cancelado la operación de firma digital.");
var cryptoErrorInternalError= new Error ("Ha ocurrido un error interno al momento de generar la firma digital.");
		
	
/*----------------------------------------------------------------------*/
/* Valida que el componente CAPICOM esté instalado en la máquina cliente*/
/*----------------------------------------------------------------------*/

function verifyCapicomAvailability()
{
	var testObject = new ActiveXObject("CAPICOM.Signer");
	if(typeof(testObject) != "object")
	{
		throw capicomError;
	}
}

/*----------------------------------------------------------------------*/
/* Firma la información especificada en los parámetros de entrada.		*/
/* Esta función se invocará exclusivamente para navegadores IE.         */
/*----------------------------------------------------------------------*/

function SignDataIE(data2sign)
{
	try
	{
		//verify Capicom avalability
		verifyCapicomAvailability();
		
		// instantiate the CAPICOM objects
		var SignedData = new ActiveXObject("CAPICOM.SignedData");
		var Signer = new ActiveXObject("CAPICOM.Signer");
		var TimeAttribute = new ActiveXObject("CAPICOM.Attribute");
		
		//Capturar los datos que van a ser firmados 
		SignedData.Content = data2sign;
	
		// Set the time in which we are applying the signature
		var Today = new Date();
		TimeAttribute.Name = CAPICOM_AUTHENTICATED_ATTRIBUTE_SIGNING_TIME;
		TimeAttribute.Value = Today.getVarDate();
		Signer.AuthenticatedAttributes.Add(TimeAttribute);
	
		//Crear la firma digital	
		return SignedData.Sign(Signer, CAPICOM_DETACHED, CAPICOM_ENCODIG_TYPE);
	}
	catch (e)
	{
		throw e;
	}
}

/*----------------------------------------------------------------------*/
/* Firma la información especificada en los parámetros de entrada.		*/
/* Esta función se invocará exclusivamente para navegadores Mozilla.    */
/*----------------------------------------------------------------------*/
function SignDataMozilla(data2sign)
{
	var firmado = window.crypto.signText(data2sign, "ask");
	
	if (firmado == "error:noMatchingCert") 
	{
		throw cryptoErrorNoMatchingCert;
	}
	else if (firmado == "error:userCancel") 
	{
		throw cryptoErrorUserCancel;
	}
	else if (firmado == "error:internalError") 
	{
		throw cryptoErrorInternalError;
	} 
	else 
	{
		return firmado;
	}
}
