<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
		xmlns:pe="http://lod.chtd.org/ws/pgu_service/">

  <xsl:template match="pe:requestULType">
		<html>
			<head>
				<title>����� ���������</title>
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
					<xsl:text>���������� � ����������</xsl:text>
				</b>
			</td>
		</tr>

		<xsl:apply-templates select="pe:licensiat_info" />

		<tr>
			<td width="30%">
				<xsl:text>������������</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:location" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>���������� � �����������</xsl:text>
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
				<xsl:text>��������������-�������� �����</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:org_form" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>������ ������������</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:full_name" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>�.�.�. ������������</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:leader_names" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>�������</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:first_name" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>���</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:last_names" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="pe:contact_info">
		<tr>
			<td width="30%">
				<xsl:text>�������</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:phone_number" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>����������� �����</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:email" />
			</td>
		</tr>
		<tr>
			<td width="30%">
				<xsl:text>���������� ����</xsl:text>
			</td>
			<td>
				<xsl:value-of select="pe:contact_person" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="pe:activity_places">
		<tr>
			<td width="30%">
				<xsl:text>����� ����� ������������</xsl:text>
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
					<xsl:text>������� ������������</xsl:text>
				</b>
			</td>
		</tr>
		<tr>
			<th width="30">
				<xsl:text>������������ ���������</xsl:text>
			</th>
			<th>
				<xsl:text>���� ���, �� ���</xsl:text>
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