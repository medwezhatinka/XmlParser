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
        <Medication>
          <xsl:apply-templates/>
        </Medication>
    </xsl:template>
<xsl:template match="Medication">
    <Medication>
        <Name><xsl:value-of select="Name"/>  </Name> 
        <Dosage>
            <Reception><xsl:value-of select="Reception"/></Reception>
        </Dosage>
    </Medication>
    </xsl:template>
</xsl:stylesheet>
