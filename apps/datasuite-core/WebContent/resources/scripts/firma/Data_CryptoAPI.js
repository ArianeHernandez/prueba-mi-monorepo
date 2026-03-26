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

/*------------------------------*/
/* Funciones de alcance público	*/
/*------------------------------*/

/*----------------------------------------------------------------------*/
/* Firma el contenido diligenciado por un usuario en un control de un   */
/* formulario HTML.                                                     */
/*----------------------------------------------------------------------*/

function ExecuteSignHTMLInputForm (inputDataToSign, inputHiddenSign)
{
	if (navigator.appName=="Microsoft Internet Explorer")
	{
		inputHiddenSign.value = SignDataIE(inputDataToSign.value);
	}
	else
	{
		inputHiddenSign.value = SignDataMozilla(inputDataToSign.value);
	}
}
