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
<title><bean:message key="title.list"/></title>
<link rel="stylesheet" type="text/css" href="css/base.css" />

</head>


<body> 

<table cellpadding="0" cellspacing="0" border="0" width="100%">

<!-- HEADER ----------------->
<tr>
	<td colspan="2">
<jsp:include page="/pages/header.jsp" flush="true" />
	</td>
</tr>

<!-- BREADCRUMBS -----------------> 
<tr>
<%--      <td height="20" bgcolor="#CCCCCC">&nbsp;</td>--%>  
	<td class="tdwht"><span class="heading2">     
		<strong><bean:message key="movement.title"/></strong> 
		</span>
		<span class="heading2">
			<html:link action="/ListUnit" styleClass="link4">
				 	<bean:message key="unit.title.list"/>
			</html:link>
		</span>		
		<span class="text6">&gt;</span>
		<span class="heading2">
			<html:link action="/ListMovement" styleClass="link5">
				<bean:message key="title.list"/> 	
			</html:link>
		</span>							
	</td> 
</tr>  
<tr valign="top" bgcolor="#CCCCCC">
	<td height="1" colspan="2"></td>
</tr>
<!-- BREADCRUMBS -----------------> 


<tr>
<!-- SIDEBAR ----------------->
<%--	<td bgcolor="#CCCCCC" width="121" valign="top" align="center">
<jsp:include page="/pages/sidebarWelcome.jsp" flush="true" />
	</td>--%>
	
<!-- BODY ----------------->	
	<td>
	
		<div style="OVERFLOW:auto; width:950px; height:650px; position:relative;">	

<table border="0" cellpadding="5" cellspacing="5" width="100%" bgcolor="#ffffff">
<tr><td>

<html:form action="/ListMovement">
<html:hidden property="gotoPage" />



<table border="0" cellpadding="2" cellspacing="0" width="100%" bgcolor="#ffffff">
<tr><td>	


<!-- start header information -->
<table width="50%" border="0" cellspacing="1" cellpadding="0" bgcolor="#A9C0D7">
  <tr>
    <td>
<table border="0" cellpadding="3" cellspacing="0" width="100%" bgcolor="#ffffff">

<tr><td>
<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
  <tr>
    <td width="50%" class="td4"><span class="heading6"><bean:message key="prompt.unitnumber"/></span></td>
    <td width="25%" class="td4"><span class="heading6"><bean:message key="prompt.movementstatus"/></span></td>
    <td width="25%" class="td4"><span class="heading6"><bean:message key="prompt.inventorystatus"/></span></td>	
  </tr>  
  <tr class="text2"> 
    <td class="td6" width="50%">
	&nbsp;<bean:write name="ListMovementForm" property="headerInfo1" scope="session"/>
	</td>
    <td class="td6" width="25%">
	&nbsp;<bean:write name="ListMovementForm" property="headerInfo4" scope="session"/>
	</td>
    <td class="td6" width="25%">
	&nbsp;<bean:write name="ListMovementForm" property="headerInfo5" scope="session"/>
	</td>								
   </tr> 
     
</table>
</td>
</tr>

   
</table>
</td>
</tr>
</table>
<!-- end header information -->

<table>
<tr>
<td></td>
</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%" bgcolor="#ffffff">
  <tr>
	<td width="85%">
<table border="0" cellpadding="2" cellspacing="2" width="100%" bgcolor="#ffffff">
  <tr>
	<td class="text2">	
	</td>	
  </tr>
</table>
	</td>

<td width="15%">	
<table border="0" cellpadding="2" cellspacing="2" width="100%" bgcolor="#ffffff">
  <tr>
	<td align="right">
<logic:equal name="ListMovementForm" property="firstPage"
			scope="session" value="false">			
<INPUT type="image" id="startButton" name="startButton" src="images/arrow_start.gif"/>
 </logic:equal>	
<logic:notEqual name="ListMovementForm" property="firstPage"
			scope="session" value="false">
<html:img src="images/arrow_start_grey.gif"/>
 </logic:notEqual>	
<logic:equal name="ListMovementForm" property="firstPage"
			scope="session" value="false">
<INPUT type="image" id="previousButton" name="previousButton" src="images/arrow_previous.gif"/>			
 </logic:equal>	
<logic:notEqual name="ListMovementForm" property="firstPage"
			scope="session" value="false">
<html:img src="images/arrow_previous_grey.gif"/>
 </logic:notEqual>
<logic:equal name="ListMovementForm" property="lastPage"
			scope="session" value="false">
<INPUT type="image" id="nextButton" name="nextButton" src="images/arrow_next.gif"/>				
</logic:equal>
<logic:notEqual name="ListMovementForm" property="lastPage"
			scope="session" value="false">
<html:img src="images/arrow_next_grey.gif"/>		
</logic:notEqual>
<logic:equal name="ListMovementForm" property="lastPage"
			scope="session" value="false">
<INPUT type="image" id="endButton" name="endButton" src="images/arrow_end.gif"/>				
</logic:equal>
<logic:notEqual name="ListMovementForm" property="lastPage"
			scope="session" value="false">
<html:img src="images/arrow_end_grey.gif"/>		
</logic:notEqual>
	</td>
  </tr> 
</table>

	</td>
  </tr> 
</table>


<table width="100%" border="0" cellspacing="2" cellpadding="0" valign="top" bgcolor="#FFFFFF">
	<tr>
		<td>
			<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
				<tr>		
		  			<td class="tddkgry" width="10%"><span class="heading4">
						<bean:message key="heading.movedate"/></span>
					</td>
		  			<td class="tddkgry" width="10%"><span class="heading4">
						<bean:message key="heading.movetime"/> </span>
					</td>
		  			<td class="tddkgry" width="20%"><span class="heading4">
						<bean:message key="heading.movelocation"/> </span>
					</td>
		  			<td class="tddkgry" width="10%"><span class="heading4">
						<bean:message key="heading.movestatus"/></span>
					</td>
		  			<td class="tddkgry" width="20%"><span class="heading4">
						<bean:message key="heading.movementref"/></span>
					</td>																				
		  			<td class="tddkgry" width="20%">			
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="left" class="heading4">
<bean:message key="heading.action"/>
		  				</td>
						<td align="right" class="heading4">
<%--com.bureaueye.beacon.bean.Constants.AVAILABLE_STATUS--%>							
<%--<c:if test="${ListMovementForm.headerInfo4 == 'AVAILABLE'}">--%>					
	<app:linkMovementCreate page="/EditMovement.do?action=Create">
		<bean:message key="prompt.addamovement"/>
	</app:linkMovementCreate>
<%--</c:if>	--%>										
						</td>
					</tr>
					</table>									
					</td>
				</tr>		
			<c:forEach var="lineItem" items="${ListMovementForm.lineItems}" varStatus="loop">
				<c:set var="_class" value="tdlghtgry"/>
				<c:if test="${loop.count % 2 == 0}">
					<c:set var="_class" value="tdlghtgry2"/>				
				</c:if>			
  				<tr>
				<span class="text2">			
					<td class='<c:out value="${_class}"/>' width="10%">
					<bean:write name="lineItem" property="movedate" format="dd-MMM-yyyy" filter="true"/>	
					</td> 
					<td class='<c:out value="${_class}"/>' width="10%">
						<c:out value="${lineItem.movetime}"/>					
					</td> 
					<td class='<c:out value="${_class}"/>' width="20%">
						<c:out value="${lineItem.movelocationkey.locationName}"/>
					</td> 	
					<td class='<c:out value="${_class}"/>' width="10%">
						<c:out value="${lineItem.movests}"/>
					</td> 	
					<td class='<c:out value="${_class}"/>' width="20%">
						<c:out value="${lineItem.moveref}"/>
					</td> 																			 						
					<td class='<c:out value="${_class}"/>' width="20%" align="right">
									<table border="0" width="100%" align="right" cellpadding="1" cellspacing="3">
										<tr>							
											<td class="text2" align="left">													

											</td>												
											<td class="text2" align="right">
<c:if test="${lineItem.onhireeqpId==0 && lineItem.offhireeqpId==0}"> 																			
	<app:linkMovementLineItem page="/EditMovement.do?action=Delete"><bean:message key="prompt.delete"/></app:linkMovementLineItem>&nbsp;|&nbsp;						
	<app:linkMovementLineItem page="/EditMovement.do?action=Edit"><bean:message key="prompt.edit"/></app:linkMovementLineItem>
</c:if>						
<c:if test="${lineItem.onhireeqpId!=0 || lineItem.offhireeqpId!=0}">
	<app:linkMovementLineItem page="/EditMovement.do?action=View"><bean:message key="prompt.view"/></app:linkMovementLineItem>						
</c:if>	
						</td>							
										</tr>
									</table>												  
					</td>								 										 
				</span>
  				</tr>
							
			</c:forEach>
			
			</table>
		</td>
	</tr>
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


<!-- FOOTER ----------------->
<tr>
	<td colspan="2">
<jsp:include page="/pages/footer.jsp" flush="true" />
	</td>
</tr>

</table>

</body>
</html:html>
