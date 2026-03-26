<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:include href="context://common/xsl/osm_page.xsl" />
	<xsl:include href="context://componentes/menu/acciones.xsl" />
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->

	<xsl:variable name="solicitud"
		select="//obtenerDatosPorIdCarga/solicitud" />
	<xsl:variable name="archivos"
		select="//obtenerDatosPorIdCarga/archivos" />
		
	<xsl:template match="OSM-ACCION">

		<pagina titulo="Detalle Información">

			<principal>

				<titulo>Detalle solicitud: <xsl:value-of select="$solicitud/idcarga" /></titulo>
				<contenido>
					
					<subtitulo>Identificación del deudor</subtitulo>
					<xsl:variable name="basicos"
						select="$solicitud/deudor/datos_basicos" />
					<xsl:variable name="rep_legal"
						select="$solicitud/deudor/representante_legal" />
					<xsl:variable name="contador"
						select="$solicitud/deudor/contador" />
					<xsl:variable name="revisor_fiscal"
						select="$solicitud/deudor/revisor_fiscal" />
					<xsl:variable name="apoderado"
						select="$solicitud/deudor/apoderado" />
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						<fila>
							<valor>
								Cédula del deudor solicitante
							</valor>
							<valor>
								<xsl:value-of select="$basicos/identificacion" />
							</valor>
						</fila>
						<fila>
							<valor>
								Nombre del deudor solicitante
							</valor>
							<valor>
								<xsl:value-of select="$basicos/nombre_completo" />
							</valor>
						</fila>
						<fila>
							<valor>
								Macrosector
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/macrosectorObj/nombre" />
							</valor>
						</fila>
						<fila>
							<valor>
								Departamento de domicilio
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/departamentoCiudad/departamento" />
							</valor>
						</fila>
						<fila>
							<valor>
								Ciudad de domicilio
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/departamentoCiudad/municipio" />
							</valor>
						</fila>
					
					</tabla_solicitud>
					
					
					
					<subtitulo>Información de profesionales asociados al deudor</subtitulo>
					
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						<fila>
							<valor>
								Contador
							</valor>
							<valor>
								
								<xsl:if test="not($contador/nombre_completo)=0">
									<xsl:value-of select="$contador/nombre_completo" />
								</xsl:if>
								<xsl:if test="not($contador/nombre_completo)=1">
									-_-_-
								</xsl:if>
								
							</valor>
						</fila>
						<fila>
							<valor>
								Apoderado (Opcional)
							</valor>
							<valor>
								<xsl:if test="not($apoderado/nombre_completo)=0">
									<xsl:value-of select="$apoderado/nombre_completo" />
								</xsl:if>
								<xsl:if test="not($apoderado/nombre_completo)=1">
									-_-_-
								</xsl:if>
							</valor>
						</fila>
						<fila>
							<valor>
								Asistente (Opcional)
							</valor>
							<valor>
							<!-- Falta definir la variable par el asistente. Se usa $apoderado/asistente provisionalmente -->
								<xsl:if test="not($apoderado/asistente)=0">
									<xsl:value-of select="$apoderado/asistente" />
								</xsl:if>
								<xsl:if test="not($apoderado/asistente)=1">
									-_-_-
								</xsl:if>
							</valor>
						</fila>
							
					</tabla_solicitud>
					
					<subtitulo>Memoria Explicativa de las Causas de Insolvencia</subtitulo>
					
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>						
						<fila>
							<valor>
								"Manifiesto que, de conformidad con el artículo 1 del Decreto 560 de 15 de abril de 2020, las causas que dan inicio a este proceso de Negociación de Emergencia de un Acuerdo de Reorganización son consecuencia de los hechos que dieron lugar a la Emergencia Económica, Social y Ecológica declarada mediante el Decreto 417 de 17 de marzo de 2020."
							</valor>
							<valor>
								Si
							</valor>
						</fila>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Archivo memoria explicativa']">
							<fila>
								<valor>
									Memoria explicativa de las causas de insolvencia
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Memoria explicativa de las causas de insolvencia']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<fila>
							<valor>
								La persona natural no comerciante se encuentra en el supuesto de
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/situacion_presentada_obj/nombre" />
							</valor>
						</fila>
						<fila>
							<valor>
								Certifico que la negociación de emergencia del acuerdo de reorganización se adelanta con
							</valor>
							<valor>
								<xsl:if test="$solicitud/emergencia_economica=1">
									Todos los acreedores
								</xsl:if>
								<xsl:if test="$solicitud/emergencia_economica=2">
									Categorías de acreedores
								</xsl:if>
							</valor>
						</fila>
							
					</tabla_solicitud>
					
					<subtitulo>Relación de Pasivos (Artículo 32, Ley 1429 de 2010)</subtitulo>
					
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						<fila>
							<valor>
								¿Tiene pasivos por retenciones de carácter obligatorio a favor de autoridades fiscales?
							</valor>
							<valor>
								<xsl:if test="$solicitud/relacion_de_pasivos/pasivos_por_retenciones=0">
									No
								</xsl:if>
								<xsl:if test="$solicitud/relacion_de_pasivos/pasivos_por_retenciones=1">
									Si
								</xsl:if>
							</valor>
						</fila>
						<fila>
							<valor>
								¿Tiene pasivos por descuentos efectuados a trabajadores?
							</valor>
							<valor>
								<xsl:if test="$solicitud/relacion_de_pasivos/pasivos_por_descuentos=0">
									No
								</xsl:if>
								<xsl:if test="$solicitud/relacion_de_pasivos/pasivos_por_descuentos=1">
									Si
								</xsl:if>
							</valor>
						</fila>
						<fila>
							<valor>
								¿Tiene pasivos por aportes al sistema de seguridad social?
							</valor>
							<valor>
								<xsl:if test="$solicitud/relacion_de_pasivos/pasivos_por_aportes=0">
									No
								</xsl:if>
								<xsl:if test="$solicitud/relacion_de_pasivos/pasivos_por_aportes=1">
									Si
								</xsl:if>
							</valor>
						</fila>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Archivo pasivos mes anterior']">
							<fila>
								<valor>
									Relación de pasivos con corte al mes anterior
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Relación de pasivos con corte al mes anterior']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Cálculo actuarial de pasivos pensionales']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de existencia o inexistencia de Pasivos Pensionales a Cargo']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificado de pasivos por retenciones obligatorias con el fisco, descuentos a trabajadores y aportes al Sistema de Seguridad Social']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Plan para la atención de dichos pasivos.']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
							
					</tabla_solicitud>
					
					<subtitulo>Tipo de solicitud de insolvencia </subtitulo>
					
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						<fila>
							<valor>
								Tipo de Solicitud
							</valor>
							<valor>
								Negociación de Emergencia de Acuerdos de Reorganización
							</valor>
						</fila>
						<fila>
							<valor>
								Norma Aplicable
							</valor>
							<valor>
								DECRETO 560 DEL 15 DE ABRIL DE 2020. Art. 8
							</valor>
						</fila>
							
					</tabla_solicitud>
					
					
					<subtitulo>Otros documentos requeridos</subtitulo>
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Inventario de bienes del solicitante']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de tener o no tener procesos judiciales y relación de los procesos judiciales y de cualquier procedimiento o actuación administrativa de carácter patrimonial']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Declaración de renta año anterior']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de los ingresos del deudor expedida por su empleador o declaración juramentada de los mismos en caso de ser trabajador independiente']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de recursos disponibles para el pago de las obligaciones']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de tener o no sociedad conyugal o patrimonial vigente']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de tener o no tener obligaciones alimentarias a su cargo con la discriminación de las obligaciones alimentarias a su cargo']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificado de ser controlante de una sociedad']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
					</tabla_solicitud>
					
						
					<subtitulo2>Plan de reorganización</subtitulo2>
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Flujo de caja proyectado']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Proyecto de calificación, graduación y derechos de voto de crédito']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Propuesta para la negociación de deudas']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
					</tabla_solicitud>
					<subtitulo2>Bienes en garantía</subtitulo2>
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						
						
						<xsl:choose>
				         	<xsl:when test="$archivos/ArchivoAdjunto[./descripcion='Valor de los bienes gravados con garantía']">
				           		<fila>
									<valor>
										Valor de los bienes gravados con garantía
									</valor>
									<valor>
										<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Valor de los bienes gravados con garantía']/nombre_archivo" />.<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Valor de los bienes gravados con garantía']/extension_archivo"/>
									</valor>
								</fila>
				         	</xsl:when>
				         	<xsl:otherwise>
				          		<fila>
									<valor>
										Valor de los bienes gravados con garantía
									</valor>
									<valor>
										No se cargó
									</valor>
								</fila>
				         	</xsl:otherwise>
				       	</xsl:choose>
				       	
				       	<xsl:choose>
				         	<xsl:when test="$archivos/ArchivoAdjunto[./descripcion='Certificación de garantía mobiliaria']">
				           		<fila>
									<valor>
										Certificación de garantía mobiliaria
									</valor>
									<valor>
										<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Certificación de garantía mobiliaria']/nombre_archivo" />.<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Certificación de garantía mobiliaria']/extension_archivo" />
									</valor>
								</fila>
				         	</xsl:when>
				         	<xsl:otherwise>
				          		<fila>
									<valor>
										Certificación de garantía mobiliaria
									</valor>
									<valor>
										No se cargó
									</valor>
								</fila>
				         	</xsl:otherwise>
				       	</xsl:choose>
						</tabla_solicitud>
						
				</contenido>
			</principal>

		</pagina>
	</xsl:template>

</xsl:stylesheet>
