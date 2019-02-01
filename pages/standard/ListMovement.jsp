<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<!--
 *
 * Amendments
 * ----------
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


<script language="JavaScript">
	function screenRefresh() 
	{
	}
</script>


<script type="text/javascript">
function noenter() {
  return !(window.event && window.event.keyCode == 13); }
</script>
<script language="javascript" type="text/javascript">
//<![CDATA[
	function onLoad() {	
	}
    //]]>
</script>


<script language="JavaScript">
<!--	
	function showHideSearch(div1,div2) { 
		var v,obj1,obj2;

		obj1=MM_findObj(div1);
		obj1=obj1.style; 
		v=obj1.visibility;
		if (v=='hidden') {
			obj1.visibility='visible';	
		}
		else {
			obj1.visibility='hidden'; 
		}

		obj2=MM_findObj(div2);
		obj2=obj2.style; 
		v=obj2.visibility;
		if (v=='hidden') {
			obj2.visibility='visible';	
		}
		else {
			obj2.visibility='hidden'; 
		}
	}
-->
</script>



<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css" href="css/basedms.css" />
<link rel="stylesheet" type="text/css" href="css/button.css" />


<head>
<title><bean:message key="movement.title.list"/></title>


</head>


<body onLoad="onLoad();"> 

<table cellpadding="0" cellspacing="0" border="0" width="100%">

<tr><td><jsp:include page="/pages/header.jsp" flush="true" /></td></tr>
	

<!-- BREADCRUMBS -----------------> 
<tr bgcolor="#F1F1F1"><td>
<span class="header2"><bean:message key="movement.title.list"/></span>
<span class="header2">&gt;</span>
<html:link action="/ListUnit"><span class="header4"><bean:message key="unit.title.list"/></span></html:link>        
  </td></tr>  
<tr valign="top" bgcolor="#CCCCCC"><td height="1"></td></tr>
<!-- BREADCRUMBS ----------------->
	
    
    
<!-- HEADER INFORMATION ----------------->
<tr><td> 
<div id="header" style="position:absolute; left:5px; width:600px; height:50px; z-index:10; visibility: visible; border-left:solid 1px #C6C6C6;  border-right:solid 1px #C6C6C6; border-bottom:solid 1px #C6C6C6; background-color:#FFF;">
	<table border="0" cellpadding="0" cellspacing="5" width="100%">
    
    <tr>
		<td class="label11" width="33%"><bean:message key="prompt.unitnumber"/></td>
		<td class="label11" width="33%"><bean:message key="prompt.manufacturenumber"/></td>           
		<td align="right" width="33%">
<a href="javascript:showHideSearch('header','header2');" >        
<img class="NavigationSprite Expand" title="Show More Information" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;"/>
</a>
		</td>   
    </tr>
    <tr>
      <td class="label10"><bean:write name="ListMovementForm" property="headerInfo1" scope="session"/></td>
      <td class="label10"><bean:write name="ListMovementForm" property="headerInfo2" scope="session"/></td>
      <td class="label10"></td>              
    </tr>

	</table>
</div>

<div id="header2" style="position:absolute; left:5px; width:600px; height:230px; z-index:10; visibility: hidden; border-left:solid 1px #C6C6C6; border-right:solid 1px #C6C6C6; border-bottom:solid 1px #C6C6C6; background-color:#FFF;">
	<table border="0" cellpadding="0" cellspacing="5" width="100%">
    
    	<tr>
      		<td class="label11" width="33%"><bean:message key="prompt.unitnumber"/></td>
		    <td class="label11" width="33%"><bean:message key="prompt.manufacturenumber"/></td> 
			<td align="right" width="33%">
<a href="javascript:showHideSearch('header','header2');" >        
<img class="NavigationSprite Collapse" title="Hide More Information" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;"/>
</a>
			</td>
 		</tr>        
    	<tr>
		    <td class="label10"><bean:write name="ListMovementForm" property="headerInfo1" scope="session"/></td>
		    <td class="label10"><bean:write name="ListMovementForm" property="headerInfo2" scope="session"/></td>        
		    <td class="label10"></td>      
		</tr>       
<tr><td><img src="images/spacer.gif" height="5"/></td></tr>        
    	<tr>
      		<td class="label11" colspan="3"><bean:message key="prompt.manufacturedate"/></td>
 		</tr>      
    	<tr>
		    <td class="label10" colspan="3"><bean:write name="ListMovementForm" property="headerInfo3" scope="session"/></td>               
		</tr>           
                     
	</table>
</div>
</td></tr> 
<!-- HEADER INFORMATION ----------------->


<tr><td><img src="images/spacer.gif" height="45"/></td></tr>
        	  	  
<tr><td>
	<div style="OVERFLOW:auto; width:1200px; height:650px; position:relative;">	

    <table border="0" cellpadding="0" cellspacing="2" width="100%" bgcolor="#ffffff">

          
		<tr><td colspan="2">
            <html:messages id="error" name="APP_ERROR_KEY" header="errors.header" footer="errors.footer">
              <bean:message key="errors.prefix"/>
              <bean:write name="error"/>
              <bean:message key="errors.suffix"/>
            </html:messages>
            <html:messages id="msg" name="APP_INFORMATION_KEY" header="messages.header" footer="messages.footer">
              <bean:message key="messages.prefix"/>
              <bean:write name="msg"/>
              <bean:message key="messages.suffix"/>
            </html:messages>                
        </td></tr>

    
<html:form action="/ListMovement">
<html:hidden property="gotoPage" />
	<html:hidden name="ListMovementForm" property="headerInfo1"/>        
	<html:hidden name="ListMovementForm" property="id"/>            
    	
		
    
    			<tr><td colspan="2"><img src="images/spacer.gif" height="5"/></td></tr>  
      
				<tr><td colspan="2">
					<div class="btnPane3">	              
						
                           
						<button id="exitButton" class="btn btnFF" type="button"  onclick="document.location.href='ListUnit.do'">
			                    <img class=" IM cross-IM" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />
            			        <span><bean:message key="button.exit"/></span>
			            </button>                                                  
					</div> 
				</td></tr>
 

        		<tr>
					<td><html:img src="images/spacer.gif" border="0" width="1" height="1"/></td>
       
       				<td width="100%">   
 						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>

		  						<td class="column-header1" width="10%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="document.location.href='ListMovement.do?orderBy=Movedate&gotoPage=0'">
										<span><bean:message key="heading.movedate"/></span>                        
										<logic:notEqual name="ListMovementForm" property="orderBy" scope="session" value="Movedate">              
									         <img class=" IM2 Spacer-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:notEqual>
										<logic:equal name="ListMovementForm" property="orderBy" scope="session" value="Movedate">              
									         <img class=" IM2 Sortdown-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:equal>
							        </button>
								</td> 
                                
		  						<td class="column-header2" width="10%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="#">
										<span><bean:message key="heading.movetime"/></span>                        
							        </button>
								</td>  

	  						<td class="column-header2" width="20%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="#">
										<span><bean:message key="heading.movelocation"/></span>                        
							        </button>
								</td>  
                                
 		  						<td class="column-header2" width="10%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="document.location.href='ListMovement.do?orderBy=Movests&gotoPage=0'">
										<span><bean:message key="heading.movestatus"/></span>                        
										<logic:notEqual name="ListMovementForm" property="orderBy" scope="session" value="Movests">              
									         <img class=" IM2 Spacer-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:notEqual>
										<logic:equal name="ListMovementForm" property="orderBy" scope="session" value="Movests">              
									         <img class=" IM2 Sortdown-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:equal>
							        </button>
								</td>                                                                                                                                                    		 		  						<td class="column-header2" width="20%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="document.location.href='ListMovement.do?orderBy=Moveref&gotoPage=0'">
										<span><bean:message key="heading.movementref"/></span>                        
										<logic:notEqual name="ListMovementForm" property="orderBy" scope="session" value="Moveref">              
									         <img class=" IM2 Spacer-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:notEqual>
										<logic:equal name="ListMovementForm" property="orderBy" scope="session" value="Moveref">              
									         <img class=" IM2 Sortdown-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:equal>
							        </button>
								</td>

		  						<td width="20%"></td>
                                
							</tr>
                
<%int row=0;%>			
	<logic:iterate id="lineItem" name="ListMovementForm" property="lineItems">
                            
<%String _class = "column-cell1";if ((row % 2) == 0) _class = "column-cell2";%>          
<%row++;%>	
			
<!-- row1 -->            
		<tr>
									<td class="<%= _class %>">
					<bean:write name="lineItem" property="movedate" format="dd-MMM-yyyy" filter="true"/>&nbsp;
                                    </td> 
									<td class="<%= _class %>">
						<c:out value="${lineItem.movetime}"/>&nbsp;
                                    </td> 
									<td class="<%= _class %>">
						<c:out value="${lineItem.movelocationkey.locationName}"/>&nbsp;
									</td>  
 									<td class="<%= _class %>">
						<c:out value="${lineItem.movests}"/>&nbsp;
									</td>  
 									<td class="<%= _class %>">
						<c:out value="${lineItem.moveref}"/>&nbsp;
									</td>                                     									                                    
                                                                                                                                                    							<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>				
								<td class="<%= _class %>" align="right"> 

<c:if test="${lineItem.onhireeqpId==0 && lineItem.offhireeqpId==0}"> 																			
	<app:linkMovementLineItem page="/EditMovement.do?action=Delete"><bean:message key="prompt.delete"/></app:linkMovementLineItem>&nbsp;&nbsp;|&nbsp;&nbsp;						
	<app:linkMovementLineItem page="/EditMovement.do?action=Edit"><bean:message key="prompt.edit"/></app:linkMovementLineItem>
</c:if>						
<c:if test="${lineItem.onhireeqpId!=0 || lineItem.offhireeqpId!=0}">
	<app:linkMovementLineItem page="/EditMovement.do?action=View"><bean:message key="prompt.view"/></app:linkMovementLineItem>						
</c:if>	 					
						</td>
							</tr>
						</table>		                                    
									</td>
                                                           					 
								</tr>
                    
	</logic:iterate>
    
            			</table>
					</td>
				</tr>
    
			</html:form>
      
		</table>

</div>
</td></tr>

<tr><td><jsp:include page="/pages/footer.jsp" flush="true" /></td></tr>

</table>

</body>
</html:html>
