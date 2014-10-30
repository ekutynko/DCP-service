<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:pe="http://lod.chtd.org/ws/pgu_service/">

  <xsl:template match="pe:requestULType|pe:requestIPType">
		<html>
			<head>
				<title>Форма Заявления</title>
			</head>
			<body bgcolor="#ffffff" text="#0000ff">
			        <xsl:apply-templates select="pe:licensiat"/>
				<tr/>
				<xsl:apply-templates select="pe:activity_places" />
				<tr/>
				<xsl:apply-templates select="pe:accreditation_areas" />
				<tr/>
				<xsl:apply-templates select="pe:certificate" />
				<tr/>
				<xsl:apply-templates select="pe:license_request" />
				<tr/>
				<tr>
					<td colspan='2'>
						<b>
							<xsl:text>Сфера контроля Заполняется для след. типов сервиса: MONITORING_ACTIVITY, GRAIN_PROCESSING, BAKERY_PRODUCTS</xsl:text>
						</b>
					</td>
				</tr>

				<xsl:apply-templates select="pe:action_types" />
				<tr/>
				<xsl:apply-templates select="pe:examination" />
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

	<xsl:template match="pe:certificate">
		<tr>
			<td colspan='2'>
				<b>
					<xsl:text>Реквизиты аттестата (для видов услуг:  переоформление, выдача дубликата/ копии, прекращение действия сертификата)</xsl:text>
				</b>
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Номер аттестата</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:certificate_no" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Дата выдачи аттестата</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:certificate_date" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="pe:license_request">
		<tr>
			<td colspan='2'>
				<b>
					<xsl:text>Общие данные</xsl:text>
				</b>
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Вид деятельности</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:action_types" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Лицензирующий орган</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:authority" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Вид услуги</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:process_type" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Тип аттестата</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:certificate_type" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Причина переоформления</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:reform_reason" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>Заявленный срок действия аккредитации</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:cert_declared_valid_date" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="pe:action_types">
		<tr>
			<td width="30%">
				<xsl:text>Сфера контроля</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:sphere_of_control" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>перечень видов деятельности (работ(услуг)</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:action_types" />
			</td>
		</tr>
	</xsl:template>
	<xsl:template match="pe:examination">

		<tr>
			<td width="30%">
				<xsl:text>Вид негосударственной экспертизы</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:examination" />
			</td>
		</tr>

	</xsl:template>

</xsl:stylesheet>