<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-ACCION">
		
		<pagina titulo="Bienvenido">
			
			<principal>
				<contenido>
					<bloque estilo="columna">
						<h2>AVISO LEGAL:</h2>
						
						<div style="border-width: 1px; border-color: black; border-style: solid; padding: 10px; text-align: justify">
							<p>La advertencia de veracidad de la información debe ser antes de empezar a llenar el formulario. Se sugiere la siguiente:
								"Las manifestaciones en el diligenciamiento de los formularios electrónicos de los que dispone la Superintendencia de Sociedades a través de sus plataformas, así como las manifestaciones realizadas a través de mensajes de datos en el marco de los procesos de negociación de emergencia de acuerdos de reorganización se entenderán realizados bajo la gravedad de juramento. Las conductas de falsedad en la información o el uso de documentos falsos están tipificadas como delitos bajo el Código Penal, Ley 599 de 2000, y eventualmente pueden ser consideradas como falsedad material en documento público, falsedad en documento privado, uso de documento falso, falsedad personal, entre otros, que se encuentran tipificados así:
								 
								ARTÍCULO  287. Falsedad material en documento público. El que falsifique documento público que pueda servir de prueba, incurrirá en prisión de tres (3) a seis (6) años.
								Si la conducta fuere realizada por un servidor público en ejercicio de sus funciones, la pena será de cuatro (4) a ocho (8) años e inhabilitación para el ejercicio de derechos y funciones públicas de cinco (5) a diez (10) años.
								ARTÍCULO  289. Falsedad en documento privado. El que falsifique documento privado que pueda servir de prueba, incurrirá, si lo usa, en prisión de uno (1) a seis (6) años.
								ARTÍCULO 291. Uso de documento falso. El que sin haber concurrido a la falsificación hiciere uso de documento público falso que pueda servir de prueba, incurrirá en prisión de cuatro (4) a doce (12) años.
								Si la conducta recae sobre documentos relacionados con medios motorizados, el mínimo de la pena se incrementará en la mitad.
								ARTÍCULO  294. Documento. Para los efectos de la ley penal es documento toda expresión de persona conocida o conocible recogida por escrito o por cualquier medio mecánico o técnicamente impreso, soporte material que exprese o incorpore datos o hechos, que tengan capacidad probatoria.
								ARTÍCULO  296. Falsedad personal. El que con el fin de obtener un provecho para sí o para otro, o causar daño, sustituya o suplante a una persona o se atribuya nombre, edad, estado civil, o calidad que pueda tener efectos jurídicos, incurrirá en multa, siempre que la conducta no constituya otro delito.
							</p>
						</div>
					</bloque>
					
					<area_botones>
						<boton estilo="guardar" accion="osm_go('representante_legal/certificacionesRepLeg.pub');">Aceptar</boton>
					</area_botones>
				</contenido>
			</principal>
			
		</pagina>
		
	</xsl:template>

</xsl:stylesheet>
