<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : MedicineSchema.xsl
    Created on : June 7, 2013, 1:27 AM
    Author     : Soldier
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <Medicament>
            <xsl:apply-templates  />
        </Medicament>
    </xsl:template>
    <xsl:template match="Medication">
        <Medicament>
            <Name>
                <xsl:value-of select="Name" /> 
            </Name>
        </Medicament>
    </xsl:template>
</xsl:stylesheet>
