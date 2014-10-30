<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:pe="http://lod.chtd.org/ws/pgu_service/">

  <xsl:template match="pe:requestULType">
		<html>
			<head>
				<title>Форма Заявления</title>
			</head>
			<body bgcolor="#ffffff" text="#0000ff">
			        <xsl:apply-templates select="pe:licensiat"/>
				<xsl:apply-templates select="pe:activity_places" />
				<xsl:apply-templates select="pe:accreditation_areas" />
			</body>
		</html>
  </xsl:template>

  <xsl:template match="pe:licensiat">
		<tr>
			<td colspan='2'>
				<b>
					<xsl:text>Информация о лицензиате</xsl:text>
				</b>
			</td>
		</tr>

		<xsl:apply-templates select="pe:licensiat_info" />

		<tr>
			<td width="30%">
				<xsl:text>Расположение</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:location" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Информация о регистрации</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:registration" />
			</td>
		</tr>

		<xsl:apply-templates select="pe:contact_info" />

  </xsl:template>

	<xsl:template match="pe:licensiat_info">
		<tr>
			<td width="30%">
				<xsl:text>Организационно-правовая форма</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:org_form" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Полное наименование</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:full_name" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Ф.И.О. руководителя</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:leader_names" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Фамилия</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:first_name" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Имя</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:last_names" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="pe:contact_info">
		<tr>
			<td width="30%">
				<xsl:text>Телефон</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:phone_number" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Электронная почта</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:email" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Контактное лицо</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:contact_person" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="pe:activity_places">
		<tr>
			<td width="30%">
				<xsl:text>Адрес места деятельности</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:ActivityPlace" />
			</td>
		</tr>

	</xsl:template>

	<xsl:template match="pe:accreditation_areas">
		<tr>
			<td colspan='2'>
				<b>
					<xsl:text>Области аккредитации</xsl:text>
				</b>
			</td>
		</tr>
		<tr>
			<th width="30">
				<xsl:text>Наименование продукции</xsl:text>
			</th>
			<th>
				<xsl:text>Коды ОКР, ТН ВЭД</xsl:text>
			</th>
		</tr>

		<xsl:for-each select="pe:AccreditationAreaType">
			<tr>
				<td width="30%">
					<xsl:value-of select="pe:product_name" />
				</td>
				<td>
					<xsl:value-of select="pe:codes" />
				</td>
			</tr>
		</xsl:for-each>

	</xsl:template>


</xsl:stylesheet>