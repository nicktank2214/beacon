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




</head>


<body onUnload="if (searchWin != null) {searchWin.close()}">
 
<table cellpadding="0" cellspacing="0" border="0" width="100%">
<tr>
	<td colspan="2">
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


<html:form action="/EditAgentjob6" focus="notes">

<html:hidden property="action"/>

<html:hidden property="orderhdrId"/>
<html:hidden property="orderno"/>
<html:hidden property="quohdrId"/>
<html:hidden property="quotno"/>
<html:hidden property="jobhdrId"/>
<html:hidden property="jobno"/>

<html:hidden property="unitId"/>
<html:hidden property="unitkey"/>

<html:hidden property="incotermkey"/>

<html:hidden property="ldgfromdatedd"/>
<html:hidden property="ldgfromdatemm"/>
<html:hidden property="ldgfromdateyyyy"/>
<html:hidden property="ldgfromtimehh"/>
<html:hidden property="ldgfromtimemm"/>
<html:hidden property="dchfromdatedd"/>
<html:hidden property="dchfromdatemm"/>
<html:hidden property="dchfromdateyyyy"/>
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
  	<td align="left" bgcolor="#B0C8E0"><html:link page="/Agentjob2.do"><html:img src="images/crumbs/crumb_jobdetails2.jpg" width="144" height="34" alt="job details" border="0"/></html:link></td>
  	<td align="left" bgcolor="#B0C8E0"><html:link page="/Agentjob3.do"><html:img src="images/crumbs/crumb_docs3.jpg" width="144" height="34" alt="docs" border="0"/></html:link></td>
  	<td align="left" bgcolor="#B0C8E0"><html:link page="/Agentjob4.do"><html:img src="images/crumbs/crumb_product3.jpg" width="108" height="34" alt="product" border="0"/></html:link></td>
  	<td align="left" bgcolor="#B0C8E0"><html:link page="/Agentjob5.do"><html:img src="images/crumbs/crumb_jobsections3.jpg" width="119" height="34" alt="job sections" border="0"/></html:link></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_notes3.jpg" width="91" height="34" alt="notes" border="0"/></td>
  	<td align="left" bgcolor="#B0C8E0"><html:img src="images/crumbs/crumb_summary2.jpg" width="222" height="34" alt="summary" border="0"/></td>  	
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
		<html:select property="ldgfromdatedd" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="ldgfromdatemm" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="ldgfromdateyyyy" styleClass="ftforminputsmall" disabled="true">
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
		<html:select property="dchfromdatedd" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="dchfromdatemm" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="dchfromdateyyyy" styleClass="ftforminputsmall" disabled="true">
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
        <html:text property="unitkey" size="20" disabled="true" maxlength="20" styleClass="ftforminputsmall"/>
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
		<html:select property="incotermkey" styleClass="ftforminputsmall" disabled="true">
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

  <tr>
    <td>
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">  				
        <tr>
		  <td rowspan="3">
		  	<img src="images/spacer.gif" height="89" width="1" border="0"/>
		  </td>		
          <td width="20%" class="systemtd2">
		  <div align="right"><bean:message key="prompt.documentclosing"/>:</div>		  
		  </td>
          <td width="80%" class="systemtd2">		  
		<html:select property="documentclosingdatedd" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="documentclosingdatemm" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="documentclosingdateyyyy" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.year"/></html:option>				
			<html:options collection="years" property="value" labelProperty="label"/>	
		</html:select>	
 		  </td>
        </tr>
        
        <tr>
          <td class="systemtd2"><div align="right"><bean:message key="prompt.tankclosing"/>:</div></td>
          <td class="systemtd2">
		<html:select property="tankclosingdatedd" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.day"/></html:option>				
			<html:options collection="days" property="value" labelProperty="label"/>	
		</html:select>		  
		<html:select property="tankclosingdatemm" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.month"/></html:option>				
			<html:options collection="months" property="value" labelProperty="label"/>	
		</html:select>
		<html:select property="tankclosingdateyyyy" styleClass="ftforminputsmall" disabled="true">
			<html:option value=""><bean:message key="prompt.year"/></html:option>				
			<html:options collection="years" property="value" labelProperty="label"/>	
		</html:select>	  
		  </td>
        </tr>      
 
         <tr>
          <td class="systemtd2" valign="top"><div align="right"><bean:message key="prompt.deliveryterminaltext"/>:</div></td>
          <td class="systemtd2">
		  <table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">
            <tr>
              <td class="systemtd2">                     
		<html:textarea rows="3" cols="80"  property="deliveryterminaltext" styleClass="ftforminputsmall" disabled="true"/>            
		  </td>
          <td class="systemtd2" align="right" valign="bottom">  
          </td>
          </tr>
          </table>
          </td>          
        </tr>
                       														
    </table>	
	</td>
    	
  </tr>	
  
<tr><td><img src="images/spacer.gif" height="2" width="1" border="0"/></td></tr>  

  <tr>
    <td>
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">  				
        <tr>
		  <td rowspan="4">
		  	<img src="images/spacer.gif" height="89" width="1" border="0"/>
		  </td>		
          <td width="20%" class="systemtd2">
		  <div align="right"><bean:message key="prompt.product"/>:</div>		  
		  </td>
          <td width="80%" class="systemtd2">		  
		<html:select property="productkey" disabled="true" styleClass="ftforminputsmall"> 
		<html:option value=""><bean:message key="prompt.selectaproduct"/></html:option> 
		<html:options collection="products" property="productkey" labelProperty="tradname"/> 
		</html:select> 
 		  </td>
        </tr>     
        <tr>	
          <td width="20%" class="systemtd2">
		  <div align="right"><bean:message key="prompt.tradname"/>:</div>		  
		  </td>
          <td width="80%" class="systemtd2">		  
<html:text property="tradname" size="40" disabled="true" styleClass="ftforminputsmall"/>
 		  </td>
        </tr>
        <tr>	
          <td width="20%" class="systemtd2">
		  <div align="right"><bean:message key="prompt.unnumber"/>:</div>		  
		  </td>
          <td width="80%" class="systemtd2">		  
<html:text property="unno" size="10" disabled="true" styleClass="ftforminputsmall"/>
 		  </td>
        </tr>
        
  <tr>
            <td width="20%" class="systemtd2">
		  <div align="right">
		        Volume:
		  </div>
		  </td>
    <td width="80%" class="systemtd2">
	<table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#999999">  
        <tr>		
          <td width="30%" class="systemtd2">	
        <html:text property="volume" size="10" maxlength="7" styleClass="ftforminputsmall" disabled="true"/>			  
		<html:select property="volunit" styleClass="ftforminputsmall" disabled="true">
			<html:option value="">- Units -</html:option>
			<html:options collection="volunits" property="id.codekey" labelProperty="descr"/>	
		</html:select>									  
          </td>
          <td width="15%" class="systemtd2">
		  <div align="right">
		        Weight:
		  </div>
		  </td>
          <td width="35%" class="systemtd2">
        <html:text property="weight" size="10" maxlength="7" styleClass="ftforminputsmall" disabled="true"/>		  
		<html:select property="wghtunit" styleClass="ftforminputsmall" disabled="true">
			<html:option value="">- Units -</html:option>
			<html:options collection="wghtunits" property="id.codekey" labelProperty="descr"/>	
		</html:select>	  
          </td>
         <td class="systemtd2" align="right" valign="bottom">
  
          </td>          		  
        </tr>
    </table>
	</td>
  </tr>	
                                 														
    </table>	
	</td>
    	
  </tr>	
  
<tr><td><img src="images/spacer.gif" height="2" width="1" border="0"/></td></tr>  

  <tr>
    <td>
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">  				
        <tr>
		  <td rowspan="1">
		  	<img src="images/spacer.gif" height="89" width="1" border="0"/>
		  </td>		
          <td class="systemtd2" width="100%">
          
<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
				<tr>		
		  			<td class="tddkgry" width="10%" valign="top"><span class="heading4">
						Section
		  			</span>					</td>
		  			<td class="tddkgry" width="10%" valign="top"><span class="heading4">
						From Location
		  			</span>					</td>
		  			<td class="tddkgry" width="12%"><span class="heading4">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr><td class="heading4">
								Est FROM Date/Time
		  					</td></tr>
							<tr><td class="heading4">
								Act FROM Date/Time
		  					</td></tr>							
						</table>
		  			</span>					</td>																									
		  			<td class="tddkgry" width="10%" valign="top"><span class="heading4">
						To Location					
		  			</span>					</td>
		  			<td class="tddkgry" width="12%"><span class="heading4">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr><td class="heading4">
								Est TO Date/Time
		  					</td></tr>
							<tr><td class="heading4">
								Act TO Date/Time
		  					</td></tr>							
						</table>					
		  			</span>					</td>					
		  			<td class="tddkgry" width="15%" valign="top"><span class="heading4">
						Vendor
						</span>					
                    </td>																								

				</tr>
							
			<c:forEach var="jobmovLineItem" items="${AgentjobForm.jobmovs}" varStatus="loop">
            
				<c:set var="_class" value="tdlghtgry"/>
				<c:if test="${loop.count % 2 == 0}">
					<c:set var="_class" value="tdlghtgry2"/>				
				</c:if>	
                
                		
  				<tr>
				<span class="text2">			
					<td class='<c:out value="${_class}"/>' width="10%" valign="top">
						<c:out value="${jobmovLineItem.value.section.sectionkey}"/>					</td> 
					<td class='<c:out value="${_class}"/>' width="10%" valign="top">
<c:out value="${jobmovLineItem.value.fromlocation.locationName}"/>					</td>
					<td class='<c:out value="${_class}"/>' width="12%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr><td class='<c:out value="${_class}"/>'>
<fmt:formatDate value="${jobmovLineItem.value.estfromdate}" pattern="dd-MMM-yyyy" />/<c:out value="${jobmovLineItem.value.estfromtime}"/>					
		  					</td></tr>
							<tr><td class='<c:out value="${_class}"/>'>
<fmt:formatDate value="${jobmovLineItem.value.actfromdate}" pattern="dd-MMM-yyyy" />/<c:out value="${jobmovLineItem.value.actfromtime}"/>					
		  					</td></tr>							
						</table>					</td>					  																				
					<td class='<c:out value="${_class}"/>' width="10%" valign="top">
<c:out value="${jobmovLineItem.value.tolocation.locationName}"/>					</td> 
					<td class='<c:out value="${_class}"/>' width="12%">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr><td class='<c:out value="${_class}"/>'>
<fmt:formatDate value="${jobmovLineItem.value.esttodate}" pattern="dd-MMM-yyyy" />/<c:out value="${jobmovLineItem.value.esttotime}"/>										
		  					</td></tr>
							<tr><td class='<c:out value="${_class}"/>'>
<fmt:formatDate value="${jobmovLineItem.value.acttodate}" pattern="dd-MMM-yyyy" />/<c:out value="${jobmovLineItem.value.acttotime}"/>										
		  					</td></tr>							
						</table>					</td>					
					<td class='<c:out value="${_class}"/>' width="15%" valign="top">
<c:out value="${jobmovLineItem.value.vendoraddr.name}"/>					</td>						

                    								 										 
				</span>  				
                </tr>
				
               
					<tr><td colspan="8" background="images/border_dotted.gif"></td></tr>
									
			</c:forEach>
			</table>
            
 		  </td>
        </tr>                                     														
    </table>	
	</td>
    	
  </tr>	
  
<tr><td><img src="images/spacer.gif" height="2" width="1" border="0"/></td></tr>  

<tr>
    <td>
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">  				
        <tr>
		  <td rowspan="1">
		  	<img src="images/spacer.gif" height="89" width="1" border="0"/>
		  </td>		
          <td width="20%" class="systemtd2" valign="top">
		  <div align="right"><bean:message key="prompt.notes"/>:</div>		  
		  </td>
          <td width="80%" class="systemtd2">
<html:textarea rows="10" cols="110" property="notes" styleClass="ftforminputsmall"/>	          		  
 		  </td>
        </tr>                                      														
    </table>	
	</td>   	
</tr>
  
<tr><td><img src="images/spacer.gif" height="2" width="1" border="0"/></td></tr>  
   
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
