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
								NIT deudor del solicitante
							</valor>
							<valor>
								<xsl:value-of select="$basicos/identificacion" />
							</valor>
						</fila>
						<fila>
							<valor>
								Razón Social del Deudor Solicitante
							</valor>
							<valor>
								<xsl:value-of select="$basicos/nombre_completo" />
							</valor>
						</fila>
						<fila>
							<valor>
								Código CIIU
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/actividad_economica" />
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
						<fila>
							<valor>
								Naturaleza
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/naturalezaObj/nombre" />
							</valor>
						</fila>
						<fila>
							<valor>
								Porcentaje de participación estastal
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/porcentaje_participacion" />
							</valor>
						</fila>
						<fila>
							<valor>
								Número de empleadas mujeres
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/trabajadores_mujeres" />
							</valor>
						</fila>
						<fila>
							<valor>
								Número de empleados hombres
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/trabajadores_hombres" />
							</valor>
						</fila>
						<fila>
							<valor>
								Representante legal
							</valor>
							<valor>
								<xsl:value-of select="$rep_legal/nombre_completo" />
							</valor>
						</fila>
						<fila>
							<valor>
								Categoría
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/categoria" />
							</valor>
						</fila>
						<fila>
							<valor>
								Tamaño
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/deudor/tamano_empresa_nombre" />
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
								Revisor fiscal(Si aplica)
							</valor>
							<valor>
								<xsl:if test="not($revisor_fiscal/nombre_completo)=0">
									<xsl:value-of select="$revisor_fiscal/nombre_completo" />
								</xsl:if>
								<xsl:if test="not($revisor_fiscal/nombre_completo)=1">
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
<!-- 							<xsl:if test="$solicitud/memoria_explicativa=1">
									Si
								</xsl:if>
								<xsl:if test="$solicitud/memoria_explicativa!=1">
									No
								</xsl:if>    -->
<!-- 								<xsl:value-of select="$solicitud/memoria_explicativa" /> -->
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
								Certifico que la sociedad que represento se encuentra en el supuesto de
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
								¿La sociedad tiene pasivos por retenciones de carácter obligatorio a favor de autoridades fiscales?
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
								¿La sociedad tiene pasivos por descuentos efectuados a trabajadores?
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
								¿La sociedad tiene pasivos por aportes al sistema de seguridad social?
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
						<xsl:choose>
				         	<xsl:when test="$archivos/ArchivoAdjunto[./descripcion='Cálculo actuarial de pasivos pensionales']">
				           		<fila>
									<valor>
										Cálculo actuarial de pasivos pensionales
									</valor>
									<valor>
										<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Cálculo actuarial de pasivos pensionales']/nombre_archivo" />.<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Cálculo actuarial de pasivos pensionales']/extension_archivo" />
									</valor>
								</fila>
				         	</xsl:when>
				         	<xsl:otherwise>
				          		<fila>
									<valor>
										Cálculo actuarial de pasivos pensionales
									</valor>
									<valor>
										No se cargó
									</valor>
								</fila>
				         	</xsl:otherwise>
				       	</xsl:choose>
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
					
					<subtitulo>Información financiera con corte al último día del mes anterior</subtitulo>
					
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
								Valor total activos
							</valor>
							<valor>
								<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/valor_activos" />
								</moneda>
							</valor>
						</fila>
						<fila>
							<valor>
								Valor total pasivos
							</valor>
							<valor>
								<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/valor_pasivos" />
								</moneda>
							</valor>
						</fila>
						<fila>
							<valor>
								Valor total patrimonio
							</valor>
							<valor>
								<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/valor_patrimonio" />
								</moneda>
							</valor>
						</fila>
						<fila>
							<valor>
								Grupo NIIF
							</valor>
							<valor>
								<xsl:value-of select="$solicitud/tipo_solicitud/grupo_niif"/>
							</valor>
						</fila>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Inventario de activos del mes anterior']">
							<fila>
								<valor>
									Inventario de activos con corte del mes anterior
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Inventario de activos con corte del mes anterior']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Estados financieros del mes anterior (ESF, ERI, EFE, ORI, ECP)']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Notas a estados financieros del mes anterior']">
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
					
					<subtitulo>Ingresos por actividades anuales</subtitulo>
					
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
								Valor total ingresos ordinarios
							</valor>
							<valor>
								<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/total_ingresos_ordinarios" />
								</moneda>
							</valor>
						</fila>
						<fila>
							<valor>
								Valor total otros ingresos
							</valor>
							<valor>
								<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/total_otros_ingresos" />
								</moneda>
							</valor>
						</fila>
						<fila>
							<valor>
								¿La sociedad tiene inversiones en subsidiarias, negocios conjuntos, asociadas u otras inversiones?
							</valor>
							<valor>
								<xsl:if test="$solicitud/informacion_financiera/tiene_inversiones=0">
									No
								</xsl:if>
								<xsl:if test="$solicitud/informacion_financiera/tiene_inversiones=1">
									Si
								</xsl:if>
							</valor>
							</fila>
						<xsl:if test="$solicitud/informacion_financiera/tiene_inversiones=1">
							<fila>
								<valor>
									Valor Total Participación en ganancias (pérdidas) por medición al método de participación
								</valor>
								<valor>
									<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/valor_p_participacion" />
									</moneda>
								</valor>
							</fila>
							<fila>
								<valor>
									Valor Total Participación en ganancias (pérdidas) por medición al método al costo
								</valor>
								<valor>
									<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/valor_p_costo" />
									</moneda>
								</valor>
							</fila>
							<fila>
								<valor>
									Valor Total Participación en ganancias (pérdidas) por medición al método al valor razonable
								</valor>
								<valor>
									<moneda>
									<xsl:value-of select="$solicitud/informacion_financiera/valor_p_razonable" />
									</moneda>
								</valor>
							</fila>
						</xsl:if>
							
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
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de llevar la contabilidad regular']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación causal de disolución']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:choose>
				         	<xsl:when test="$archivos/ArchivoAdjunto[./descripcion='Dictamen del revisor fiscal del mes anterior']">
				           		<fila>
									<valor>
										Dictamen del revisor fiscal del mes anterior
									</valor>
									<valor>
										<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Dictamen del revisor fiscal del mes anterior']/nombre_archivo" />.<xsl:value-of select="$archivos/ArchivoAdjunto[./descripcion='Dictamen del revisor fiscal del mes anterior']/extension_archivo" />
									</valor>
								</fila>
				         	</xsl:when>
				         	<xsl:otherwise>
				          		<fila>
									<valor>
										Dictamen del revisor fiscal del mes anterior
									</valor>
									<valor>
										No se cargó
									</valor>
								</fila>
				         	</xsl:otherwise>
				       	</xsl:choose>
						
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificado de existencia y representacion legal']">
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
					<subtitulo2>Estados financieros años anteriores (ESF, ERI, EFE, ORI, ECP)</subtitulo2>
					<tabla_solicitud>
						<encabezado>
							<titulo>
								Campo
							</titulo>
							<titulo>
								Valor
							</titulo>
						</encabezado>
						
					
						
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Estados financieros del último año']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Notas a estados financieros del último año']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Estados financieros del penúltimo año']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Notas a estados financieros del penúltimo año']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Estados financieros del antepenúltimo año']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Notas a estados financieros del antepenúltimo año']">
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
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Certificación de capacidad del representante legal para tramitar la solicitud']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Soporte de capacidad del representante legal para tramitar la solicitud']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Poder al abogado para realizar la solicitud (Si aplica)']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
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
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Plan de negocios de reorganización']">
							<fila>
								<valor>
									<xsl:value-of select="descripcion"/>
								</valor>
								<valor>
									<xsl:value-of select="nombre_archivo"/>.<xsl:value-of select="extension_archivo"/>
								</valor>
							</fila>	
						</xsl:for-each>
						<xsl:for-each select="$archivos/ArchivoAdjunto[./descripcion='Informe (avalista, garante o codeudor de terceros)']">
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
