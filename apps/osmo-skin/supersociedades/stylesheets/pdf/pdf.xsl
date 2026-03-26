<?xml version="1.0"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">

	<xsl:param name="contextPath" />
	
	<xsl:template match="/">
		<xsl:apply-templates select="//pagina"/>
	</xsl:template>
	
	<!-- root -->
	<xsl:template match="pagina">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<!-- page templates -->
			<fo:layout-master-set>
				
				<!-- simple-page-masters page-height="29.7cm" page-width="21cm" -->
				<fo:simple-page-master master-name="page"
				 page-height="40cm" page-width="60cm"
					 margin-top="1cm"
					margin-bottom="2.5cm" margin-left="2.5cm" margin-right="2.5cm">
					<fo:region-before extent="1cm" />
					<fo:region-body margin-top="1cm" />
					<fo:region-after extent="1cm" />
				</fo:simple-page-master>

				<!--which simple-page-masters are to be used--> 
				<fo:page-sequence-master master-name="all">
					<fo:repeatable-page-master-alternatives>
						<fo:conditional-page-master-reference master-reference="page" page-position="first" />
					</fo:repeatable-page-master-alternatives>
				</fo:page-sequence-master>
			</fo:layout-master-set>

			
			<!-- content -->
			<fo:page-sequence master-reference="all">
				
				<fo:static-content flow-name="xsl-region-after">
					<fo:block text-align="end" font-size="9pt" font-family="serif" color="#666666">
						Pagina <fo:page-number />
					</fo:block>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">
					<fo:table>
						<fo:table-column column-number="1" column-width="22cm" />
						<fo:table-column column-number="2" column-width="auto" />
						
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell >
									<fo:block>
										<fo:external-graphic src="{//OSM_PAGE/base-path}/resources/images/general/logo.jpg"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell >
									<fo:block font-size="35pt" font-weight="bold" font-family="times" padding-top="50pt" padding-buttom="50pt"  color="#00467d">
										<xsl:value-of select="principal/titulo"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					
					</fo:table>

					<!--contenido-->
					<xsl:apply-templates select="principal/contenido"/>
					
					
				</fo:flow>
				
			</fo:page-sequence>
			
			
		</fo:root>
	</xsl:template>
	
	<xsl:template match="pestana">
		
		<xsl:if test="@pdfview='true' or count(@pdfview)=0">
		
		<!-- titulo de la pestana -->
		
		<fo:table space-before="5mm" space-after="5mm" border-width="0.2pt"  border-style="solid" border-left-color="#FFFFF8" border-right-color="#FFFFF8" border-top-color="#FFFFF8" >
			<fo:table-column column-width="auto"/>
			<fo:table-body>
                <fo:table-row>
	    
	              <fo:table-cell border-width="0.2pt" border-color="#000000" border-style="solid" border-left-color="#FFFFFF" border-right-color="#FFFFFF" border-top-color="#FFFFFF">
	              	<fo:block font-size="16pt" font-weight="bold" text-align="start"  font-family="times" padding-top="30pt" padding-buttom="1pt">
						<xsl:value-of select="@titulo"/>
					</fo:block>    
	              </fo:table-cell>
	                    
	            </fo:table-row>
	        </fo:table-body>
		</fo:table>
		
		
		<!-- contenido de las pestanas -->
		<xsl:apply-templates/>
		
		</xsl:if>
	</xsl:template>

	
	<xsl:template match="bloque-contenido">
		
		<xsl:if test="@pdfview='true' or count(@pdfview)=0">
			
			<!-- titulos -->
			
			<fo:table space-before="5mm" space-after="5mm" border-width="0.2pt"  border-style="solid" border-left-color="#FFFFF8" border-right-color="#FFFFF8" border-top-color="#FFFFF8">
				<fo:table-column column-width="auto"/>
				<fo:table-body>
	                <fo:table-row>
		    
		              <fo:table-cell border-width="0.2pt" border-color="#000000" border-style="solid" border-left-color="#FFFFFF" border-right-color="#FFFFFF" border-top-color="#FFFFFF">
		              	<fo:block font-size="16pt" font-weight="bold" text-align="start"  font-family="times" padding-top="30pt" padding-buttom="1pt">
							<xsl:value-of select="titulo"/>
						</fo:block>    
		              </fo:table-cell>
		                    
		            </fo:table-row>
		        </fo:table-body>
			</fo:table>
			
			
			<xsl:apply-templates select="contenido"/>
		</xsl:if>
		
				
	</xsl:template>
	
	<xsl:template match="bloque-principal">
		<xsl:apply-templates/>
	</xsl:template>
	
	
	<xsl:template match="titulo">
		<fo:block font-size="20pt" padding-top="20pt" padding-buttom="5pt" text-align="center" font-family="times">
			<xsl:value-of select="."/>
		</fo:block>
	</xsl:template>

	<xsl:template match="subtitulo">
		<fo:block font-size="25pt" font-weight="bold" color="#00467d" padding-top="5pt" padding-buttom="5pt" text-align="start" font-family="times">
			<xsl:value-of select="."/>
		</fo:block>
	</xsl:template>
	
	<xsl:template match="parrafo">
		
		<fo:block font-size="10pt" padding-buttom="5pt" text-align="start" font-family="times">
			<xsl:attribute name="color">
				<xsl:choose>
					<xsl:when test="@estilo='resaltado'">#F00000</xsl:when>
					<xsl:otherwise>#000000</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			
			<xsl:value-of select="."/>
		</fo:block>
	</xsl:template>
	
	
	<xsl:template match="tabla">
		
		<fo:table space-before="5mm" border-width="0.2pt"  border-style="solid" border-color="#9B9B9B"   width="100%"> 
	
			
			<!-- columnas titulo -->
			<xsl:for-each select="encabezado/titulo">
				<fo:table-column column-width="auto"/>
			</xsl:for-each>
			
			
			<!-- TABLE HEADER -->
			<fo:table-header>
			  <fo:table-row>
			  	
				<xsl:for-each select="encabezado/titulo">
				    <fo:table-cell border-width="0.2pt" background-color="#909090" border-style="solid" border-color="#9B9B9B">
				      <fo:block font-size="12pt" margin-left="1mm" margin-right="1mm" space-before="1mm" space-after="1.2mm" font-weight="bold" font-family="times" text-align="center" color="#FFF">
				      	<xsl:value-of select="."/>
				      </fo:block>
				    </fo:table-cell>
				</xsl:for-each>
			  	
			  </fo:table-row>
				
				
				
			</fo:table-header>
			
			
			<!-- TABLE BODY -->
            <fo:table-body>
            	
                <xsl:for-each select="fila">
                	<xsl:variable name="pos"  select="position()"></xsl:variable>
	                 <fo:table-row>
	                 	<xsl:for-each select="valor">
	                      <fo:table-cell border-width="0.2pt" border-color="#9B9B9B" border-style="solid" >
	                      <xsl:if test="$pos mod 2 = 0">
	                      	<xsl:attribute name="background-color">#ddd</xsl:attribute>
	                      </xsl:if>
	                           <fo:block font-size="10pt" margin="5pt" padding-top="5pt" padding-buttom="5pt" font-family="times" text-align="center">
	                           		<xsl:apply-templates/>
	                           </fo:block>
	                      </fo:table-cell>
	                    </xsl:for-each>
	                 	
	                 </fo:table-row>
	             </xsl:for-each>
            </fo:table-body>
			
       </fo:table>
       
      
	</xsl:template>

	
	<!-- Etiquetas sin visualizacion -->
	
	<xsl:template match="formulario">
		<xsl:apply-templates/>
	</xsl:template>
	
</xsl:stylesheet>
