<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : MedicineSchema.xsl
    Created on : June 7, 2013, 1:27 AM
    Author     : Soldier
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"   
                xmlns:ns0='http://xml.netbeans.org/schema/MedicineSchema'
                version="1.0">
    <xsl:output method="xml" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <xsl:template match="/">
        <table>
        
        <xsl:for-each select="ns0:Medicine/ns0:Medication">
            <td>
                <tr>
             <xsl:value-of select="ns0:Name"/>
             </tr>
             <tr>
             <xsl:value-of select="ns0:Version/ns0:Producer/ns0:Dosage/ns0:Reception"/>
             </tr>
           </td>
        </xsl:for-each>
        
        </table>
    </xsl:template>
</xsl:stylesheet>
