<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="OSM-ERM">
		
<diagram>
	<page_setting>
		<direction_horizontal>true</direction_horizontal>
		<scale>100</scale>
		<paper_size>A4 210 x 297 mm</paper_size>
		<top_margin>10</top_margin>
		<left_margin>10</left_margin>
		<bottom_margin>10</bottom_margin>
		<right_margin>10</right_margin>
	</page_setting>
	<category_index>0</category_index>
	<zoom>1.0</zoom>
	<x>76</x>
	<y>156</y>
	<default_color>
		<r>128</r>
		<g>128</g>
		<b>192</b>
	</default_color>
	<color>
		<r>255</r>
		<g>255</g>
		<b>255</b>
	</color>
	<font_name></font_name>
	<font_size>9</font_size>
	<settings>
		<database>Oracle</database>
		<capital>true</capital>
		<table_style></table_style>
		<notation></notation>
		<notation_level>4</notation_level>
		<notation_expand_group>true</notation_expand_group>
		<view_mode>1</view_mode>
		<outline_view_mode>1</outline_view_mode>
		<view_order_by>1</view_order_by>
		<auto_ime_change>false</auto_ime_change>
		<validate_physical_name>true</validate_physical_name>
		<use_bezier_curve>false</use_bezier_curve>
		<suspend_validator>false</suspend_validator>
		<export_setting>
			<category_name_to_export></category_name_to_export>
			<ddl_output></ddl_output>
			<excel_output></excel_output>
			<excel_template></excel_template>
			<image_output></image_output>
			<put_diagram_on_excel>true</put_diagram_on_excel>
			<use_logical_name_as_sheet>true</use_logical_name_as_sheet>
			<open_after_saved>true</open_after_saved>
			<create_comment>true</create_comment>
			<create_foreignKey>true</create_foreignKey>
			<create_index>true</create_index>
			<create_sequence>true</create_sequence>
			<create_table>true</create_table>
			<create_tablespace>true</create_tablespace>
			<create_trigger>true</create_trigger>
			<create_view>true</create_view>
			<drop_index>true</drop_index>
			<drop_sequence>true</drop_sequence>
			<drop_table>true</drop_table>
			<drop_tablespace>true</drop_tablespace>
			<drop_trigger>true</drop_trigger>
			<drop_view>true</drop_view>
			<inline_column_comment>true</inline_column_comment>
			<inline_table_comment>true</inline_table_comment>
			<comment_value_description>true</comment_value_description>
			<comment_value_logical_name>false</comment_value_logical_name>
			<comment_value_logical_name_description>false</comment_value_logical_name_description>
			<comment_replace_line_feed>false</comment_replace_line_feed>
			<comment_replace_string></comment_replace_string>
			<export_java_setting>
				<java_output></java_output>
				<package_name></package_name>
				<class_name_suffix></class_name_suffix>
				<src_file_encoding>ISO-8859-1</src_file_encoding>
				<with_hibernate>false</with_hibernate>
			</export_java_setting>
			<export_testdata_setting>
				<file_encoding></file_encoding>
				<file_path></file_path>
				<format>0</format>
			</export_testdata_setting>
		</export_setting>
		<category_settings>
			<free_layout>false</free_layout>
			<show_referred_tables>false</show_referred_tables>
			<categories>
			</categories>
		</category_settings>
		<translation_settings>
			<use>false</use>
			<translations>
			</translations>
		</translation_settings>
		<model_properties>
			<id></id>
			<height>-1</height>
			<width>-1</width>
				<font_name>Segoe UI</font_name>
				<font_size>9</font_size>
			<x>50</x>
			<y>50</y>
			<color>
				<r>255</r>
				<g>255</g>
				<b>255</b>
			</color>
			<connections>
			</connections>
			<display>false</display>
			<creation_date><xsl:value-of select="Fecha" /></creation_date>
			<updated_date><xsl:value-of select="Fecha" /></updated_date>
			<model_property>
				<name>Project Name</name>
				<value></value>
			</model_property>
			<model_property>
				<name>Model Name</name>
				<value></value>
			</model_property>
			<model_property>
				<name>Version</name>
				<value></value>
			</model_property>
			<model_property>
				<name>Compnay</name>
				<value></value>
			</model_property>
			<model_property>
				<name>Author</name>
				<value></value>
			</model_property>
		</model_properties>
		<table_properties>
			<schema></schema>
		</table_properties>
		<environment_setting>
			<environment>
				<id>0</id>
				<name>Default</name>
			</environment>
		</environment_setting>
	</settings>
	<dictionary>
		
		<xsl:for-each select="//Campos/Campo">
			<word>
				<id><xsl:value-of select="id_xml"/></id>
				<length>0</length>
				<decimal><xsl:choose><xsl:when test="id_tipocampo = 1 or id_tipocampo = 6 or id_tipocampo = 3 or id_tipocampo = 4">null</xsl:when><xsl:otherwise>0</xsl:otherwise></xsl:choose></decimal>
				<array>false</array>
				<array_dimension>null</array_dimension>
				<unsigned>false</unsigned>
				<args></args>
				<description><xsl:value-of select="descripcion"/></description>
				<logical_name><xsl:value-of select="nombre"/></logical_name>
				<physical_name><xsl:value-of select="nombreFisico"/></physical_name>
				<type><xsl:choose><xsl:when test="id_tipocampo = 1 or id_tipocampo = 6 or id_tipocampo = 3">varchar(n)</xsl:when><xsl:when test="id_tipocampo = 4">date</xsl:when><xsl:otherwise>decimal(p,s)</xsl:otherwise></xsl:choose></type>
			</word>
		</xsl:for-each>
		
	</dictionary>
	<tablespace_set>
	</tablespace_set>
	<contents>
		
		<xsl:for-each select="//Estructuras/Estructura">
			<xsl:variable name="id_xml" select="id_xml"/>
			<xsl:variable name="id_estructura" select="id_estructura"/>
			<table>
				<id><xsl:value-of select="id_xml"/></id>
				<height>-1</height>
				<width>-1</width>
					<font_name>Segoe UI</font_name>
					<font_size>9</font_size>
				<x><xsl:value-of select="xpos"/></x>
				<y><xsl:value-of select="ypos"/></y>
				<color>
					<r>128</r>
					<g>128</g>
					<b>192</b>
				</color>
				<connections>
					<xsl:for-each select="//Relaciones/Relacion[source=$id_xml]">
						<relation>
							<id><xsl:value-of select="id" /></id>
							<source><xsl:value-of select="source" /></source>
							<target><xsl:value-of select="target" /></target>
							<child_cardinality>1..n</child_cardinality>
							<parent_cardinality>1</parent_cardinality>
							<reference_for_pk>true</reference_for_pk>
							<name><xsl:value-of select="name" /></name>
							<on_delete_action>RESTRICT</on_delete_action>
							<on_update_action>CASCADE</on_update_action>
							<source_xp>0</source_xp>
							<source_yp>45</source_yp>
							<target_xp>-1</target_xp>
							<target_yp>-1</target_yp>
							<referenced_column>null</referenced_column>
							<referenced_complex_unique_key>null</referenced_complex_unique_key>
						</relation>
					</xsl:for-each>
				</connections>
				<logical_name><xsl:value-of select="nombre"/></logical_name>
				<physical_name><xsl:value-of select="nombreFisico"/></physical_name>
				<xsl:choose>
					<xsl:when test="descripcion != '' and descripcion != ' '">
						<description><xsl:value-of select="descripcion"/></description>
					</xsl:when>
					<xsl:otherwise>
						<description />
					</xsl:otherwise>
				</xsl:choose>
				<constraint></constraint>
				<option></option>
				<columns>
					<normal_column>
						<word_id><xsl:value-of select="//IdWordId"/></word_id>
						<xsl:for-each select="//ColumnaCampo/HashMap[idEstructura=$id_estructura]">
							<id><xsl:value-of select="idColumn"/></id>
						</xsl:for-each>
						<description></description>
						<unique_key_name></unique_key_name>
						<logical_name></logical_name>
						<physical_name></physical_name>
						<type>decimal(p,s)</type>
						<constraint></constraint>
						<default_value></default_value>
						<auto_increment>false</auto_increment>
						<foreign_key>false</foreign_key>
						<not_null>true</not_null>
						<primary_key>true</primary_key>
						<unique_key>false</unique_key>
						<character_set></character_set>
						<collation></collation>
						<sequence>
							<name></name>
							<schema></schema>
							<increment></increment>
							<min_value></min_value>
							<max_value></max_value>
							<start></start>
							<cache></cache>
							<cycle>false</cycle>
							<order>false</order>
							<description></description>
							<data_type></data_type>
							<decimal_size>0</decimal_size>
						</sequence>
					</normal_column>
					<xsl:for-each select="//Campos/Campo[id_estructura=$id_estructura]">
						<xsl:variable name="id_campo" select="id_campo"/>
						<normal_column>
							<word_id><xsl:value-of select="id_xml"/></word_id>
							<xsl:for-each select="//ColumnaCampo/HashMap[idCampo=$id_campo]">
								<id><xsl:value-of select="idColumn"/></id>
							</xsl:for-each>
							<xsl:choose>
								<xsl:when test="count(id_estructurarelacionada)>0">
									<xsl:variable name="id_estructurarelacionada" select="id_estructurarelacionada"/>
									<xsl:for-each select="//ColumnaCampo/HashMap[idEstructura=$id_estructurarelacionada]">
										<referenced_column><xsl:value-of select="idColumn"/></referenced_column>
									</xsl:for-each>
									<xsl:for-each select="//Relaciones/Relacion[idEstructuraTarget=$id_estructura and idEstructuraSource=$id_estructurarelacionada]">
										<relation><xsl:value-of select="id" /></relation>
									</xsl:for-each>
									<foreign_key>true</foreign_key>
                                </xsl:when>
								<xsl:otherwise><foreign_key>false</foreign_key></xsl:otherwise>
							</xsl:choose>
							<description></description>
							<unique_key_name></unique_key_name>
							<logical_name></logical_name>
							<physical_name></physical_name>
							<type><xsl:choose><xsl:when test="id_tipocampo = 1 or id_tipocampo = 6 or id_tipocampo = 3">varchar(n)</xsl:when><xsl:when test="id_tipocampo = 4">date</xsl:when><xsl:otherwise>decimal(p,s)</xsl:otherwise></xsl:choose></type>
							<constraint></constraint>
							<default_value></default_value>
							<auto_increment>false</auto_increment>
							<not_null><xsl:choose><xsl:when test="obligatorio = 'S'">true</xsl:when><xsl:otherwise>false</xsl:otherwise></xsl:choose></not_null>
							<primary_key>false</primary_key>
							<unique_key>false</unique_key>
							<character_set></character_set>
							<collation></collation>
							<sequence>
								<name></name>
								<schema></schema>
								<increment></increment>
								<min_value></min_value>
								<max_value></max_value>
								<start></start>
								<cache></cache>
								<cycle>false</cycle>
								<order>false</order>
								<description></description>
								<data_type></data_type>
								<decimal_size>0</decimal_size>
							</sequence>
						</normal_column>
					</xsl:for-each>
				</columns>
				<indexes>
				</indexes>
				<complex_unique_key_list>
				</complex_unique_key_list>
				<table_properties>
					<schema>MODELATOS</schema>
				</table_properties>
			</table>
		</xsl:for-each>
	</contents>
	<column_groups>
	</column_groups>
	<test_data_list>
	</test_data_list>
	<sequence_set>
	</sequence_set>
	<trigger_set>
	</trigger_set>
	<change_tracking_list>
	</change_tracking_list>
</diagram>
		
	</xsl:template>
</xsl:stylesheet>