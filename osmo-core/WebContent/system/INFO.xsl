<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" >

	<xsl:include href="context://common/xsl/osm_page.xsl"/>
	
	<xsl:param name="contextPath" />
	
	<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
	
	<xsl:template match="OSM-INFO">
		
		<html>
			
			<head>
				<script> var CONTEXTPATH = '<xsl:value-of select="$contextPath"/>';</script>
			
				<script src="scripts/jquery.js"> </script>
				<script src="scripts/core.js"> </script>
						
				<script SRC="./scripts/systemInfo.js" TYPE="text/javascript"></script>
				<link href="./styles/systemInfo.css" rel="stylesheet" type="text/css" />
			</head>
			
			
			<body>
				
				<div class="titulo">
						[<span class="titulo1"><xsl:value-of select="substring(NOMBRE_APLICACION,1,2)"/></span><span><xsl:value-of select="substring(NOMBRE_APLICACION,3)"/></span>]
						
						<div class="version">
							<xsl:value-of select="VERSION_APLICACION"/>
						</div>
					</div>
				
				<div class="area">
					
					<h2>:: <b>Info</b> Sistema</h2>
					<table border="1px">
						<xsl:for-each select="SYSTEM/property">
							<xsl:sort select="@name"/>
							<tr>
								<td><b><xsl:value-of select="@name"/></b></td>
								<td><xsl:value-of select="."/></td>
							</tr>
						</xsl:for-each>
					</table>
					
					
					<h2>:: <b>Info</b> Memoria</h2>
					<table border="1px">
						<tr>
							<td><b>Utilizada</b></td>
							<td><xsl:value-of select="round(number(SYSTEM_MEMORY/USED) div 1048576)"/> megas</td>
						</tr>
						<tr>
							<td><b>Ocupada VM</b></td>
							<td><xsl:value-of select="round(number(SYSTEM_MEMORY/VM) div 1048576)"/> megas</td>
						</tr>
						<tr>
							<td><b>Maximo</b></td>
							<td><xsl:value-of select="round(number(SYSTEM_MEMORY/MAX) div 1048576)"/> megas</td>
						</tr>
						<tr>
							<td><b>Liberar Memoria</b></td>
							<td>
								<form action="system.info" method="post">
									<input type="hidden" name="memoria" value="memoria"/>
									<input type="submit" value="liberar"/>
								</form>
							</td>
						</tr>
					</table>
					
					<input type="hidden" id="memoriaTotal" value="{SYSTEM_MEMORY/MAX}"/>
					
					<div style="position:relative; margin-top: 30px">
						<canvas class="area_label" id="area_label" width="50" height="220"></canvas>
						<div class="area_grafico" id="area_grafico">
							<canvas id="grafico" height="220"></canvas>
						</div>
					</div>
					
					<div id="divSesionesActivas">
						<h2>:: <b>Gráfico</b> Sesiones Activas</h2>
						
						<input type="hidden" id="total_sesiones" value="{INFO_SESION/total}"/>
						
						<xsl:for-each select="INFO_SESION/DATOS/GraficoSystemInfoDto">
							<div id="data_grafico_{position()}" class="GraficoSystemInfoDto" style="display:none">
								<input type="hidden" name="memoriaMaquinaVirtual" value="{memoriaMaquinaVirtual}"/>
								<input type="hidden" name="memoriaUtilizada" value="{memoriaUtilizada}"/>
								<input type="hidden" name="tiempo" value="{tiempo}"/>
								<input type="hidden" name="tiempoFormato" value="{tiempoFormato}"/>
								<input type="hidden" name="sesiones_activas" value="{sesiones_activas}"/>
								<input type="hidden" name="hora" value="{hora}"/>
							</div>
						</xsl:for-each>
						
						<div style="position:relative; margin-top: 30px">
							<canvas class="area_label" id="area_label_users" width="50" height="220"></canvas>
							<div class="area_grafico" id="area_grafico_users">
								<canvas id="grafico_user" height="220"></canvas>
							</div>
						</div>
					</div>
					
					<h2>:: <b>Configuración</b> Aplicación</h2>
					<table border="1px">
						<xsl:for-each select="SYSTEM/property2">
							<xsl:sort select="@name"/>
							<tr>
								<td><b><xsl:value-of select="@name"/></b></td>
								<td><xsl:value-of select="."/></td>
							</tr>
						</xsl:for-each>
					</table>
					
					<h2>:: <b>Configuración</b> Correo</h2>
	
					<table border="1px">
						<tr>
							<td><b>Configuración</b></td>
							<td><xsl:value-of select="MAIL_STATUS"/></td>
						</tr>
						<tr>
							<td><b>Prueba de Correo</b></td>
							<td>
								<form action="system.info" method="post">
									<input type="text" name="correo" value=""/>
									<input type="submit" value="enviar correo"/>
								</form>
							</td>
						</tr>
						
						<xsl:if test="count(MAIL_ERROR)>0">
							<tr>
								<td><b>Error de envío</b></td>
								<td><xsl:value-of select="MAIL_ERROR"/></td>
							</tr>
						</xsl:if>
						
					</table>
					
					
					<h2>:: <b>Conexiones</b> Ibatis</h2>
					
					<xsl:choose>
						<xsl:when test="count(POOLS/DaoManagerDTO)>0">
						   
							<table border="1px">
								<tr>
									<td><b>Id</b></td>
									<td><b>Nombre</b></td>
									<td><b>URL</b></td>
									<td><b>Driver</b></td>
									<td><b>Test</b></td>
								</tr>
								
								<xsl:for-each select="POOLS/DaoManagerDTO">
									<xsl:sort select="Id"/>
									
									<tr>
										<td><xsl:value-of select="id"/></td>
										<td><xsl:value-of select="nombre"/></td>
										<td><xsl:value-of select="url"/></td>
										<td><xsl:value-of select="driver"/></td>
										<td class="red">
											<xsl:if test="test = 'OK'"><xsl:attribute name="class">green</xsl:attribute></xsl:if>
											<center>
												<xsl:value-of select="test"/>
											</center>
										</td>
									</tr>
									
								</xsl:for-each>
								
							</table>
						 </xsl:when>
						
						<xsl:otherwise>
							<p>No existen conexiones Ibatis.</p>
						</xsl:otherwise>	
						
					</xsl:choose>
					
					
					 <h2>:: <b>Servicios</b> Disponibles Datapi</h2>
					
					<xsl:choose>
						<xsl:when test="count(SERVICIOS/*)>0">
						   
							<table border="1px">
								<tr>
									<td><b>Servicio</b></td>
									<td><b>Conexion</b></td>
									<td><b>Operaciones</b></td>
									<td><b>wsdl</b></td>
								</tr>
								
								<xsl:for-each select="SERVICIOS/*[count(nombre)>0]">
									<xsl:sort select="nombre"/>
									
									<tr>
										<td><xsl:value-of select="nombre"/></td>
										<td><xsl:value-of select="conexion/nombre"/></td>
										<td>
											<xsl:for-each select="operaciones/Operacion">
												<div><xsl:value-of select="nombre"/></div>
											</xsl:for-each>
										</td>
										<td><a href="ws/{nombre}.wsdl" target="_blank">Ver</a></td>
									</tr>
									
								</xsl:for-each>
								
							</table>
						 </xsl:when>
						
						<xsl:otherwise>
							<p>No se han cargado servicios Datapi.</p>
						</xsl:otherwise>	
						
					</xsl:choose>
					
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='OSM Core'])>0">
						<h2>:: <b>Servicios</b> Core</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='OSM Core']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td><xsl:value-of select="mejorTiempo"/></td>
									<td><xsl:value-of select="peorTiempo"/></td>
									<td><xsl:value-of select="promediomili"/></td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='Private Page'])>0">
						<h2>:: <b>Paginas</b> Sesión</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='Private Page']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='Public Page'])>0">
						<h2>:: <b>Paginas</b> Publicas</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='Public Page']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='DataPi Service'])>0">
						<h2>:: <b>Servicios</b> DataPi</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='DataPi Service']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='Private Service'])>0">
						<h2>:: <b>Servicios</b> Sesión</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='Private Service']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='Component Service'])>0">
						<h2>:: <b>Servicios</b> Componente</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='Component Service']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='--'])>0">
						<h2>:: <b>Servicios</b> Adicionales</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='--']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='Ibatis SQL'])>0">
						<h2>:: <b>Ibatis</b> SQL</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='Ibatis SQL']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					<xsl:if test="count(TESTPAGE/PageLog[tipo='Datapi SQL'])>0">
						<h2>:: <b>Datapi</b> SQL</h2>
						<table border="1px">
							<tr>
								<td><b>URL</b></td>
								<td><b>Numero de llamados</b></td>
								<td><b>Mejor Respuesta (ms)</b></td>
								<td><b>Peor Respuesta (ms)</b></td>
								<td><b>Promedio Respuesta (ms)</b></td>
							</tr>
							
							<xsl:for-each select="TESTPAGE/PageLog[tipo='Datapi SQL']">
								<xsl:sort data-type="number" select="promediomili" order="descending"/>
								
								<tr>
									<td><xsl:value-of select="url"/></td>
									<td><xsl:value-of select="cantidad"/></td>
									<td>
										<xsl:if test="mejorTiempo>200"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="mejorTiempo"/>
									</td>
									<td>
										<xsl:if test="peorTiempo>1000"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="peorTiempo"/></td>
									<td>
										<xsl:if test="promediomili>500"><xsl:attribute name="class">red</xsl:attribute></xsl:if>
										<xsl:value-of select="promediomili"/>
									</td>
								</tr>
								
							</xsl:for-each>
							
						</table>
					</xsl:if>
					
					<xsl:if test="count(//SYSTEM_LOG)>0">
						<h2>:: <b>Log</b> Sistema</h2>
						<table border="1px">
							<xsl:for-each select="//SYSTEM_LOG">
								<tr>
									<td><pre><xsl:value-of select="."/></pre></td>
								</tr>
							</xsl:for-each>
						</table>
					</xsl:if>
					
					
					
					<form action="system.info" method="post">
						<input type="hidden" name="clear" value="clear"/>
						<input type="submit" value="Limpiar Log"/>
					</form>
					
					
				</div>				
			</body>
		</html>
		
	</xsl:template>

</xsl:stylesheet>
