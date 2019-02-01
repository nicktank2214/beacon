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
<logic:equal name="UsermenuForm" property="action"
            scope="request" value="Create">
  <title><bean:message key="usermenu.title.create"/></title>
</logic:equal>
<logic:equal name="UsermenuForm" property="action"
            scope="request" value="Delete">
  <title><bean:message key="usermenu.title.delete"/></title>
</logic:equal>
<logic:equal name="UsermenuForm" property="action"
            scope="request" value="Edit">
  <title><bean:message key="usermenu.title.edit"/></title>
</logic:equal>	
<link rel="stylesheet" type="text/css" href="css/base.css" />

</head>


<body> 

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
		<strong><bean:message key="usermenu.title"/></strong> 
		</span>
		<span class="heading2">
			<html:link action="/ListUser" styleClass="link4">
				<bean:message key="user.title.list"/> 	
			</html:link>
		</span>		
		<span class="text6">&gt;</span>		
		<span class="heading2">
			<html:link action="/ListUsermenu" styleClass="link4">
				<bean:message key="usermenu.title.list"/> 	
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
<%--	<td bgcolor="#CCCCCC" width="121" valign="top" align="center">
<jsp:include page="/pages/sidebarWelcome.jsp" flush="true" />
	</td>--%>
	
	<td>
	
		<div style="OVERFLOW:auto; width:650px; height:650px; position:relative;">	

<table border="0" cellpadding="5" cellspacing="5" width="100%" bgcolor="#ffffff">
<tr><td>

<html:errors/>

<html:form action="/SaveUsermenu" focus="menuid">
<html:hidden property="action"/>
<html:hidden property="readonly"/>
<html:hidden property="usermenuId"/>
	
		
<table width="100%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">
  <tr>
    <td>
<table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="#ffffff">
  <tr>
    <td>
	
<table width="100%" border="0" cellspacing="3" cellpadding="0">
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">
        <tr>
          <td class="td7"><strong>
<logic:equal name="UsermenuForm" property="action"
            scope="request" value="Create">
<bean:message key="usermenu.title.create"/>
</logic:equal>
<logic:equal name="UsermenuForm" property="action"
            scope="request" value="Delete">
  <bean:message key="usermenu.title.delete"/>
  </logic:equal>
<logic:equal name="UsermenuForm" property="action"
            scope="request" value="Edit">
 	<bean:message key="usermenu.title.edit"/>
 </logic:equal>		  
		  </strong></td>
        </tr>
    </table></td>
  </tr>
  
  
        <logic:equal name="UsermenuForm" property="action"
                     scope="request" value="Delete">  
  <tr>
    <td width="100%" valign="top" colspan="2">
	<table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">  				
        <tr>
			<td rowspan="2">
		  		<img src="images/spacer.gif" width="1" border="0"/>
		  	</td>
          <td width="60%" class="td2">
		  <div align="right">
		        <bean:message key="prompt.userid"/>:
		  </div>
		  </td>
          <td width="70%" class="td2">
        <strong><html:hidden property="userid" write="true"/></strong>
          </td>
        </tr>
        <tr>
          <td class="td2">
		  <div align="right">
		        <bean:message key="prompt.menuid"/>:
		  </div>
		  </td>
          <td class="td2">
        <strong><html:hidden property="menuid" write="true"/></strong>
          </td>
        </tr>		
	</table>
	</td>
	</tr>
	</logic:equal>		
	
	
	  
        <logic:notEqual name="UsermenuForm" property="action"
                     scope="request" value="Delete"> 
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">
        <tr>
			<td rowspan="9">
		  		<img src="images/spacer.gif" width="1" border="0"/></td>
          <td width="30%" class="td2">
		  <div align="right"><bean:message key="prompt.userid"/>:</div></td>
          <td width="70%" class="td2">
        <strong><html:hidden property="userid" write="true"/></strong>
          </td>
        </tr>
        <tr>
          <td class="td2">
		  <div align="right"><bean:message key="prompt.menuid"/>:</div></td>
          <td class="td2">
		  <c:if test="${UsermenuForm.action == 'Create'}">
        	<html:text property="menuid" size="30" maxlength="40" styleClass="ftforminputsmall"/>
		  </c:if>
		  <c:if test="${UsermenuForm.action != 'Create'}">
        	<html:text property="menuid" size="30" disabled="true" maxlength="40" styleClass="ftforminputsmall"/>
			<html:hidden property="menuid"/>			
		  </c:if>			
          </td>
        </tr>	
        <tr>
          <td class="td2">
		  <div align="right"><bean:message key="prompt.sequence"/>:</div></td>
          <td class="td2">
		  <html:text property="sequence" size="5" maxlength="5" styleClass="ftforminputsmall"/>
          </td>
        </tr>				
        <tr>
          <td class="td2">
		  <div align="right"><bean:message key="prompt.programid"/>:		  </div>		  </td>
          <td class="td2">
		  <html:text property="programid" size="50" maxlength="100" styleClass="ftforminputsmall"/>
          </td>
        </tr>
        <tr>
          <td class="td2">
		  <div align="right"><bean:message key="prompt.programtype"/>:		  </div>		  </td>
          <td class="td2">
		  <html:text property="programtype" size="5" maxlength="10" styleClass="ftforminputsmall"/>
          </td>
        </tr>
		        <tr>
          <td class="td2"><div align="right">Desktop Option:</div></td>
          <td class="td2"><html:checkbox property="desktopflag" /></td>
          </tr>		
        <tr>
          <td class="td2">
		  <div align="right">Program Button:		  </div>		  </td>
          <td class="td2">
		  <html:text property="programbutton" size="50" maxlength="100" styleClass="ftforminputsmall"/>
          </td>
        </tr>
		        <tr>
          <td class="td2"><div align="right">Disabled Menu Option:</div></td>
          <td class="td2"><html:checkbox property="disabledflag" /></td>
          </tr>
        <tr>
          <td class="td2">
		  <div align="right">Program Button Disabled:		  </div>		  </td>
          <td class="td2">
		  <html:text property="programbuttondisabled" size="50" maxlength="100" styleClass="ftforminputsmall"/>
          </td>
        </tr>				  											  			
    </table>
	</td>
  </tr>
 
  	</logic:notEqual>
	
  
  
  <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
        <tr>
          <td class="td1">
      <logic:equal name="UsermenuForm" property="action"
                  scope="request" value="Create">
        <html:submit styleClass="button1">
          <bean:message key="button.add"/>
        </html:submit>
      </logic:equal>		  
      <logic:equal name="UsermenuForm" property="action"
                  scope="request" value="Delete">
        <html:submit styleClass="button1">
          <bean:message key="button.confirm"/>
        </html:submit>
      </logic:equal>
      <logic:equal name="UsermenuForm" property="action"
                  scope="request" value="Edit">
        <html:submit styleClass="button1">
          <bean:message key="button.save"/>
        </html:submit>
      </logic:equal>
      <logic:notEqual name="UsermenuForm" property="action"
                     scope="request" value="Delete">
        <html:reset styleClass="button1">
          <bean:message key="button.reset"/>
        </html:reset>
      </logic:notEqual>
      <html:cancel styleClass="button1">
        <bean:message key="button.cancel"/>
      </html:cancel>
    </td>
  </tr>	
  	  
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
