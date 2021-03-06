<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--
 *
 * Amendments
 * ----------
 *
 * 	NT	2009-06-16		ITT-200906-0004			Vendor Tariff lump sum sub-totals
 *
 *	NT	2009-09-14		200900037				Add 'Valid From' Date
 * 
 *	NT	2009-10-20		200900049				Add 'View' functionality
 *  												
 *
-->
 
<!-- start taglib -->
<%@ include file="/includes/taglibs.inc.jsp" %>
<!-- end taglib -->
  
<!-- start datapool -->
<%@ include file="/includes/datapool.inc.jsp" %>
<!-- end datapool -->
  
<!-- start scripts -->
<%@ include file="/includes/scripts.inc.jsp" %>
<!-- end scripts -->
  

  
<app:checkLogon/>


<html:html>
<head>



<script type="text/javascript" language="javascript">
		window.onload = onLoadFunctions;
		document.onload = onLoadFunctions;
		function onLoadFunctions() {	
			var i; i=0; 
			if ( 
				document.forms[0].action.value == 'View'
			) {
				while (i < document.forms[0].elements.length) {
					document.forms[0].elements[i].disabled = true;
					i++;	
				}
			}
		}
</script>




<script language="JavaScript">
var searchWin;

function addressSearch(field, types){
	var address = document.forms[0].elements[field];
	var url     = 'EditAddressSearch.do?maxResults=999&headerInfo1='+types+'&headerInfo2='+field+'&id=' +
                      address.options[address.selectedIndex].value;
	searchWin   = window.open('<html:rewrite href="'+url+'"></html:rewrite>', null, 'status=no,menubar=no,location=no,resizable,scrollbars,width=950,height=600');
	searchWin.focus();
}

function locationSearch(field, types){
	var location = document.forms[0].elements[field];
	var url     = 'EditLocationSearch.do?maxResults=999&headerInfo1='+types+'&headerInfo2='+field+'&id=' +
                      location.options[location.selectedIndex].value;
	searchWin   = window.open('<html:rewrite href="'+url+'"></html:rewrite>', null, 'status=no,menubar=no,location=no,resizable,scrollbars,width=950,height=600');
	searchWin.focus();
}


function tariffSearch(){
var addrkey = document.forms[0].elements['vendoraddrkey'];
var costkey = document.forms[0].elements['costkey'];
var ccykey = document.forms[0].elements['ccykey'];		
	var url     = 'EditStandardVendortariffSearch.do?'+
						'searchString1='+
                      	addrkey.options[addrkey.selectedIndex].value+ 
					  	'&searchString2='+
						'&searchString7='+ 
					  	ccykey.options[ccykey.selectedIndex].value+
						'&searchString8='+
					  	'&searchString9=LUMPSUM'+
					  	'&searchString10='+
					  	'&searchString11='													
						;															
	searchWin   = window.open('<html:rewrite href="'+url+'"></html:rewrite>', null, 'status=no,menubar=no,location=no,resizable,scrollbars,width=1050,height=600');
	searchWin.focus();
}


</script>


<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Create">
  <title><bean:message key="title.create"/></title>
</logic:equal>
<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Delete">
  <title><bean:message key="title.delete"/></title>
</logic:equal>
<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Edit">
  <title><bean:message key="title.edit"/></title>
</logic:equal>	
<link rel="stylesheet" type="text/css" href="css/base.css" />


</head>

 
<body onUnload="if (searchWin != null) {searchWin.close()}">  

<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
	<td colspan="2">
<jsp:include page="/pages/header.jsp" flush="true" />
	</td>
</tr>
	

<!-- BREADCRUMBS -----------------> 
<tr>
<%--      <td height="20" bgcolor="#CCCCCC">&nbsp;</td>--%>  
	<td class="tdwht"><span class="heading2">     
		<strong><bean:message key="vendortariffhdr.title"/></strong> 
		</span>
		<span class="heading2">
			<html:link action="/ListVendortariffhdr" styleClass="link4">
				<bean:message key="title.list"/> 	
			</html:link>
		</span>		
		<span class="text6">&gt;</span>
			<span class="text6"><strong>
				<bean:message key="title.maintenance"/> 	
			</strong></span> 							
	</td> 
</tr>  
<tr valign="top" bgcolor="#CCCCCC">
	<td height="1" colspan="2"></td>
</tr>
<!-- BREADCRUMBS ----------------->
	
	  	  
<tr>
	
	<td>
	
		<div style="OVERFLOW:auto; width:1150px; height:650px; position:relative;">	

<table border="0" cellpadding="5" cellspacing="5" width="100%" bgcolor="#ffffff">

<tr><td>
<html:messages message="true" id="message" header="messages.header" footer="messages.footer">
<tr><td><span class="text2">-&nbsp;<c:out value="${message}"/></span></td></tr>
</html:messages>
</td></tr>

<tr><td>

<html:errors/>

<html:form action="/SaveVendortariffhdr" focus="vendtarfhdrId">
<html:hidden property="action"/>
<logic:notEqual name="VendortariffhdrForm" property="action"
            scope="request" value="Create">
<html:hidden property="vendtarfhdrId"/>
</logic:notEqual>


<table width="80%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">


  <tr>
    <td>
<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="#ffffff">

  <tr>
    <td>	
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
        <tr>
          <td class="td7"><strong>
<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Create">
  <bean:message key="vendortariffhdr.title.create"/>
</logic:equal>
<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Delete">
  <bean:message key="vendortariffhdr.title.delete"/>
</logic:equal>
<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Edit">
  <bean:message key="vendortariffhdr.title.edit"/>
</logic:equal>		  
		  </strong></td>
        </tr>
    </table></td>
  </tr>
  
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        Code:
		  </div>
		  </td>
          <td width="78%" class="td2">
<logic:equal name="VendortariffhdrForm" property="action"
            scope="request" value="Create">
        <html:text property="vendtarfhdrId" size="15" maxlength="10" styleClass="ftforminputsmall"/>
</logic:equal>		  				  
<logic:notEqual name="VendortariffhdrForm" property="action"
            scope="request" value="Create">
<strong><html:hidden property="vendtarfhdrId" write="true"/></strong>
</logic:notEqual>		
          </td>
        </tr>	
    </table></td>
  </tr>
  
      <logic:notEqual name="VendortariffhdrForm" property="action"
                     scope="request" value="Delete">    		
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
        <tr>			
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.vendor"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
		<html:select property="vendoraddrkey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.selectavendor"/></html:option>
			<html:options collection="vendors" property="addrkey" labelProperty="name"/>	
		</html:select>&nbsp;
		<html:link href="javascript:void(addressSearch('vendoraddrkey','VENDOR'))">
		<html:img src="images/buttons/btn_search_12x12.png" alt="Search" border="0" align="middle"/>
		</html:link>								  
          </td>
        </tr>
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.cost"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
		<html:select property="costkey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.selectacost"/></html:option>
			<html:options collection="costs" property="costkey" labelProperty="descr"/>	
		</html:select>
          </td>
        </tr>			
    </table></td>
  </tr>	
  

  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.fromlocation"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
		<html:select property="fromlocationkey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.selectalocation"/></html:option>
			<html:options collection="locations" property="locationkey" labelProperty="labelProperty"/>	
		</html:select>&nbsp;
		<html:link href="javascript:void(locationSearch('fromlocationkey','PLANT|DEPOT|PORT|PIER'))">
		<html:img src="images/buttons/btn_search_12x12.png" alt="Search" border="0" align="middle"/>
		</html:link>
          </td>
        </tr>
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        Transhipment Location:
		  </div>
		  </td>
          <td width="78%" class="td2">
		<html:select property="tslocationkey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.selectalocation"/></html:option>
			<html:options collection="locations" property="locationkey" labelProperty="labelProperty"/>	
		</html:select>&nbsp;
		<html:link href="javascript:void(locationSearch('tslocationkey','PORT'))">
		<html:img src="images/buttons/btn_search_12x12.png" alt="Search" border="0" align="middle"/>
		</html:link>
          </td>
        </tr>		
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.tolocation"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
		<html:select property="tolocationkey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.selectalocation"/></html:option>
			<html:options collection="locations" property="locationkey" labelProperty="labelProperty"/>	
		</html:select>&nbsp;
		<html:link href="javascript:void(locationSearch('tolocationkey','PLANT|DEPOT|PORT|PIER'))">
		<html:img src="images/buttons/btn_search_12x12.png" alt="Search" border="0" align="middle"/>
		</html:link>
          </td>
        </tr>						
    </table></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.product"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
		<html:select property="productkey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.selectaproduct"/></html:option>
			<html:options collection="products" property="productkey" labelProperty="tradname"/>	
		</html:select>
          </td>
        </tr>				
    </table></td>
  </tr>	
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">  																							
    </table></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">  																	
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.ccy"/>:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
		<html:select property="ccykey" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.ccy"/></html:option>				
			<html:options collection="ccys" property="ccykey" labelProperty="ccykey"/>	
		</html:select>
          </td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
      
      
<!--sITT-200906-0004--> 
<%--<logic:notEqual name="VendortariffhdrForm" property="action"
            scope="request" value="Create">    
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		  </div>		  
		  </td>
                        <td class="td2"><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
                            <tr>
                              <td class="tddkgry" width="4%"><span class="heading4"></span></td>													
                              <td class="tddkgry" width="23%"><span class="heading4"> Tariff Id </span> </td>
                              <td class="tddkgry" width="27%"><span class="heading4"> <bean:message key="prompt.vendor"/> </span> </td>
                              <td class="tddkgry" width="23%"><span class="heading4"> <bean:message key="prompt.cost"/> </span> </td>
                              <td class="tddkgry" width="23%"><span class="heading4"> <bean:message key="prompt.lumpsum"/> </span> </td>
                              <td class="tddkgry"><span class="heading4">
<html:submit styleClass="button1" property="action2"> Remove </html:submit>							  
							  </span> </td>
                            </tr>

                                                                                        
<%
int row=0;
String _class = "";
%>
                            <logic:iterate id="lumpsumDTO" name="VendortariffhdrForm" property="list">
<%
_class = row++ % 2 == 0 ? "tdlghtgry2" : "tdlghtgry";
%>
                            <tr> <span class="text2">
<td class="<%= _class %>">
<bean:write name="lumpsumDTO" property="row"/>
</td>							
<td class="<%= _class %>">
<bean:write name="lumpsumDTO" property="vendtarfhdrId2"/>
</td>
<td class="<%= _class %>">
<bean:write name="lumpsumDTO" property="vendoraddrkey.shortname"/>
</td>
<td class="<%= _class %>">
<bean:write name="lumpsumDTO" property="costkey.costkey"/>
</td>
<td class="<%= _class %>">
<bean:write name="lumpsumDTO" property="lumpsum"/>
</td>

                              <td class="<%= _class %>" width="20%" align="center">
								<logic:notEqual	name="lumpsumDTO" property="vendtarflumpsumId" value="">
									<html:multibox property="selectedObjects"><bean:write name="lumpsumDTO" property="vendtarflumpsumId" /></html:multibox>
								</logic:notEqual>
						
                              </td>
                              </span> </tr>
                            </logic:iterate>
<%
_class = row++ % 2 == 0 ? "tdlghtgry2" : "tdlghtgry";
%>
                            <tr> <span class="text2">						
                            <td class="<%= _class %>">
<html:submit styleClass="button1" property="action2"> <bean:message key="button.add"/> </html:submit></td>
                            <td class="<%= _class %>" colspan="5">                              
<input type="text" name="vendtarfhdrId2" id="vendtarfhdrId2" styleClass="ftforminputsmall" size="20"/>&nbsp;
<html:link href="javascript:void(tariffSearch())">
<html:img src="images/buttons/btn_search_12x12.png" alt="Tariff Search" border="0" align="middle"/>
</html:link>
							</td>

                              </span> </tr> 
                                                           
                          </table></td>
                      </tr> 
</logic:notEqual>   --%>
<!--eITT-200906-0004-->                      
                       	
                      																
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        Lump Sum Total:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
        <html:text property="lumpsum" size="15" styleClass="ftforminputsmall"/>
          </td>
        </tr>
    </table></td>
  </tr>  
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">  						

<!--s200900037-->
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		       <bean:message key="prompt.validfrom"/>:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
		<html:select property="validfromdd" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="validfrommm" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="validfromyyyy" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.year"/></html:option>				
			<html:options collection="years" property="value" labelProperty="label"/>	
		</html:select>		  	  
          </td>
        </tr>	
<!--e200900037-->

        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		       <bean:message key="prompt.expirydate"/>:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
		<html:select property="validtodd" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="validtomm" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="validtoyyyy" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.year"/></html:option>				
			<html:options collection="years" property="value" labelProperty="label"/>	
		</html:select>		  	  
          </td>
        </tr>	
        												
    </table></td>
  </tr>		
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">  			
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		       <bean:message key="prompt.unittype"/>:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
        <html:text property="unittype" size="15" styleClass="ftforminputsmall"/>		
          </td>
        </tr>													
    </table></td>
  </tr>
  
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">  			
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		       Contract details:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
        <html:text property="note1" size="60" styleClass="ftforminputsmall"/>		
          </td>
        </tr>													
    </table></td>
  </tr>
    		
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">  			
        <tr>
          <td width="22%" class="td2" valign="top">
		  <div align="right">
		       Notes:
		  </div>		  
		  </td>
          <td width="78%" class="td2">
		<html:textarea rows="5" cols="90" property="note2" styleClass="ftforminputsmall"/>	
          </td>
        </tr>													
    </table></td>
  </tr>			     
        </logic:notEqual>
		
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
    
    <logic:notEqual name="VendortariffhdrForm" property="action" scope="request" value="View"> 
    
        <tr>
          <td class="td1">

<logic:equal name="VendortariffhdrForm" property="action" scope="request" value="Create"> 
	<input type="submit" value='<bean:message key="button.add"/>' class="button1" onClick="if (searchWin != null) {searchWin.close();}">		  
</logic:equal> 

<logic:equal name="VendortariffhdrForm" property="action"
                  scope="request" value="Delete"> 
	<html:submit styleClass="button1"> <bean:message key="button.confirm"/> </html:submit> 
</logic:equal> 

<logic:equal name="VendortariffhdrForm" property="action"
                  scope="request" value="Edit"> 
	<input type="submit" value='<bean:message key="button.save"/>' class="button1" onClick="if (searchWin != null) {searchWin.close();}"> 
</logic:equal> 

<logic:notEqual name="VendortariffhdrForm" property="action" scope="request" value="Delete"> 
	<html:reset styleClass="button1"> <bean:message key="button.reset"/> </html:reset> 
</logic:notEqual>
 
	<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value='<bean:message key="button.cancel"/>' onClick="if (searchWin != null) {searchWin.close();}; bCancel=true;" class="button1">
	
    </td>
  </tr>	
  
  </logic:notEqual> 
  	  
    </table></td>
  </tr>
</table>

</td></tr></table>
</td></tr></table>
</html:form>

</td>
</tr>
</table>

</div>
</td>
</tr>



<tr>
	<td colspan="2">
<jsp:include page="/pages/footer.jsp" flush="true" />
	</td>
</tr>

</table>

</body>
</html:html>
