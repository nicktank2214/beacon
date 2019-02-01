<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

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




<logic:equal name="AgentjobForm" property="action"
            scope="session" value="Create">
  <title><bean:message key="title.create"/></title>
</logic:equal>
<logic:equal name="AgentjobForm" property="action"
            scope="session" value="Delete">
  <title><bean:message key="title.delete"/></title>
</logic:equal>
<logic:equal name="AgentjobForm" property="action"
            scope="session" value="Edit">
  <title><bean:message key="title.edit"/></title>
</logic:equal>	
<link rel="stylesheet" type="text/css" href="css/base.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">


<script language="JavaScript">
var searchWin;

function unitSearch(field, status, deleted, inactive, blockalloc){
	var url     = 'EditUnitSearch.do?maxResults=999'+
					'&headerInfo1='+status+
					'&headerInfo2='+field+
					'&headerInfo3='+deleted+
					'&headerInfo4='+inactive+
					'&headerInfo5='+blockalloc+
					'&headerInfo6='+document.forms[0].elements['orderhdrId'].value+
					'&id='+document.forms[0].elements[field].value;					
	searchWin   = window.open('<html:rewrite href="'+url+'"></html:rewrite>', null, 'status=no,menubar=no,location=no,resizable,scrollbars,width=950,height=600');
	searchWin.focus();
}
</script>


</head>


<body onUnload="if (searchWin != null) {searchWin.close()}">
 
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
	<td colspan="2">
<%--<jsp:include page="/pages/header.jsp" flush="true" />--%>
	</td>
</tr>
 	

<!-- BREADCRUMBS -----------------> 
<tr> 
	<td class="tdwht">
	<span class="text6"><strong><bean:message key="agentjob.title"/></strong></span>
	</td> 
</tr>  
<tr valign="top" bgcolor="#CCCCCC">
	<td height="1" colspan="2"></td>
</tr>
<!-- BREADCRUMBS ----------------->
	
	  	  
<tr>

	
	<td>
	
		<div style="OVERFLOW:auto; width:1250px; height:650px; position:relative;">	

<table border="0" cellpadding="5" cellspacing="5" width="100%" bgcolor="#ffffff">
<tr><td>
<html:errors/>


<html:form action="/EditAgentjob3" focus="ldgfromdatedd">

<html:hidden property="action"/>

<html:hidden property="orderhdrId"/>
<html:hidden property="orderno"/>
<html:hidden property="quohdrId"/>
<html:hidden property="quotno"/>
<html:hidden property="jobhdrId"/>
<html:hidden property="jobno"/>

<html:hidden property="unitId"/>

<html:hidden property="ldgfromtimehh"/>
<html:hidden property="ldgfromtimemm"/>
<html:hidden property="dchfromtimehh"/>
<html:hidden property="dchfromtimemm"/>
	
<html:hidden property="ldglocationkey2"/>
<html:hidden property="dchlocationkey2"/>

<html:hidden property="productkey"/>
<html:hidden property="productname"/>
<html:hidden property="unno"/>
<html:hidden property="tradname"/>
<html:hidden property="volume"/>
<html:hidden property="volunit"/>
<html:hidden property="weight"/>
<html:hidden property="wghtunit"/> 

<html:hidden property="deliveryterminaltext"/>
<html:hidden property="documentclosingdatedd"/>
<html:hidden property="documentclosingdatemm"/>
<html:hidden property="documentclosingdateyyyy"/>
<html:hidden property="tankclosingdatedd"/>
<html:hidden property="tankclosingdatemm"/>
<html:hidden property="tankclosingdateyyyy"/>

<html:hidden property="createuserid"/>
<html:hidden property="companykey"/>
<html:hidden property="departmentkey"/>
<html:hidden property="activitykey"/>



<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
  <tr>
    <td>
	
<table width="1200" border="0" cellspacing="0" cellpadding="0">

  <tr>
  <td>
  
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  	<td align="left" bgcolor="#B0C8E0"><html:link href="EditAgentjob1.do"><html:img src="images/crumbs/crumb_createjob1.jpg" width="72" height="34" alt="create job" border="0"/></html:link></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_jobdetails2.jpg" width="144" height="34" alt="job details" border="0"/></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_docs2.jpg" width="144" height="34" alt="docs" border="0"/></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_product1.jpg" width="108" height="34" alt="product" border="0"/></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_jobsections1.jpg" width="119" height="34" alt="job sections" border="0"/></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_notes1.jpg" width="91" height="34" alt="notes" border="0"/></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_summary1.jpg" width="222" height="34" alt="summary" border="0"/></td>  	
    <td align="left" bgcolor="#B0C8E0"><img src="images/spacer.gif" height="1" width="170" border="0"/>
    </td>
              
  	<td bgcolor="#B0C8E0"align="right" width="70" height="34">            
      	<html:cancel styleClass="button1">
        	<bean:message key="button.cancel"/>
      	</html:cancel>
	</td>
    <td align="right" bgcolor="#B0C8E0" width="70" height="34">
                <html:submit styleClass="button1">
          <bean:message key="button.next"/>
        </html:submit>&nbsp;	
    </td>
        
  </table>
  
  </td>
  </tr>
  		
<tr><td><img src="images/spacer.gif" height="2" width="1" border="0"/></td></tr>
        
  <tr>
    <td>
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">  				
        <tr>
		  <td rowspan="6">
		  	<img src="images/spacer.gif" height="89" width="1" border="0"/>
		  </td>		
          <td width="20%" class="systemtd2">
		  <div align="right"><bean:message key="prompt.quotno"/>:</div>		  
		  </td>
          <td width="30%" class="systemtd2">		  
<html:text property="quotno" size="30" maxlength="20" styleClass="ftforminputsmall" disabled="true"/>
 		  </td>
          <td width="20%" class="systemtd2">
		  <div align="right"><bean:message key="prompt.jobno"/>:</div>		  
		  </td>
          <td width="30%" class="systemtd2">		  
		  <strong><html:hidden property="jobno" write="true"/></strong>   
		  </td>
        </tr>
        
        <tr>
          <td width="20%" class="systemtd2"><div align="right">Loading Port:</div></td>
          <td width="80%" class="systemtd2" colspan="3">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">          
          <td width="30%" class="systemtd2">
		<html:select property="ldglocationkey2" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.selectalocation"/></html:option>
			<html:options collection="ports" property="locationkey" labelProperty="locationName"/>	
		</html:select>          
          </td>
          <td width="20%" class="systemtd2">
		  <div align="right">
		        Discharge Port:		  
          </div>		  
          </td>
          <td width="30%" class="systemtd2">
		<html:select property="dchlocationkey2" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.selectalocation"/></html:option>
			<html:options collection="ports" property="locationkey" labelProperty="locationName"/>	
		</html:select>          
          </td>
          </table>
          </td>          
        </tr>
        
                <tr>
          <td class="systemtd2"><div align="right">Loading Date:</div></td>
          <td class="systemtd2" colspan="3">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">
            <tr>
              <td width="30%" class="systemtd2">
		<html:select property="ldgfromdatedd" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="ldgfromdatemm" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="ldgfromdateyyyy" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.year"/></html:option>				
			<html:options collection="years" property="value" labelProperty="label"/>	
		</html:select>
        </td>
           </tr>
          </table>		  
		  </td>
        </tr>
        
                <tr>
          <td class="systemtd2"><div align="right">Discharge Date:</div></td>
          <td class="systemtd2" colspan="3">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">
            <tr>
              <td width="30%" class="systemtd2">
		<html:select property="dchfromdatedd" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="dchfromdatemm" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="dchfromdateyyyy" styleClass="ftforminputsmall">
			<html:option value=""><bean:message key="prompt.year"/></html:option>				
			<html:options collection="years" property="value" labelProperty="label"/>	
		</html:select>		  
			  </td>
            </tr>
          </table>		  
		  </td>
        </tr>	

      <tr>
          <td width="20%" class="systemtd2">
		  <div align="right">
		        <bean:message key="prompt.unitno"/>:
		  </div>
		  </td>
          <td width="80%" class="systemtd2" colspan="3">
        <html:text property="unitkey" size="20" readonly="true" maxlength="20" styleClass="ftforminputsmall"/>&nbsp;	
		<html:link href="javascript:void(unitSearch('unitkey','AVAILABLE,EMPTY,AVA','N','N','N'))">
		<html:img src="images/buttons/btn_search_12x12.png" alt="Search" border="0" align="middle"/>
		</html:link>									  
          </td>			
      </tr>
                              		
        <tr>
          <td width="20%" class="systemtd2"> 
		  <div align="right">
		         <bean:message key="prompt.incoterm"/>:
		  </div>
		  </td>
          <td width="80%" class="systemtd2" colspan="3">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">
            <tr>          
          <td class="systemtd2">
		<html:select property="incotermkey" styleClass="ftforminputsmall">
			<html:option value=""> <bean:message key="prompt.selectaterm"/></html:option>
			<html:options collection="incoterms" property="incotermkey" labelProperty="description"/>	
		</html:select>		  
          </td>
          <td class="systemtd2" align="right">
	  
          </td>
          </tr>
          </table>
          </td>          
        </tr>
        
                														
    </table>	
	</td>
    	
  </tr>
  
<tr><td><img src="images/spacer.gif" height="2" width="1" border="0"/></td></tr>  

<%--  <tr>
    <td>
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">
        <tr>
		<td class="td7">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">
            <tr>
              <td class="td7">            
      	<html:cancel styleClass="button1">
        	<bean:message key="button.cancel"/>
      	</html:cancel>
		</td>
        		<td class="td7" align="right">
                <html:submit styleClass="button1">
          <bean:message key="button.next"/>
        </html:submit>	
        </td>
        </tr></table></td>
  </tr>		  
    </table></td>
  </tr>--%>
   
</table>

</td>
  </tr>

  
  </table>
 
  
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
