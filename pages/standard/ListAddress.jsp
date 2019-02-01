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


<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css" href="css/basedms.css" />
<link rel="stylesheet" type="text/css" href="css/button.css" />


<head>
<title><bean:message key="address.title.list"/></title>


</head>


<body onLoad="onLoad();"> 

<table cellpadding="0" cellspacing="0" border="0" width="100%">

<tr><td colspan="2"><jsp:include page="/pages/header.jsp" flush="true" /></td></tr>
	

<!-- BREADCRUMBS -----------------> 
<tr bgcolor="#F1F1F1"><td><span class="header2"><bean:message key="address.title.list"/></span></td></tr>  
<tr valign="top" bgcolor="#CCCCCC"><td height="1" colspan="2"></td></tr>
<!-- BREADCRUMBS ----------------->
	
	  	  
<tr><td>
	
<div style="OVERFLOW:auto; width:1000px; height:650px; position:relative;">	

    <table border="0" cellpadding="0" cellspacing="2" width="100%" bgcolor="#ffffff">
            
		<tr><td colspan="2"><html:errors/></td></tr>
    
<html:form action="/ListAddress" focus="searchString1">
<html:hidden property="gotoPage" />
        
        
       			<tr><td><img src="images/spacer.gif" height="5"/></td></tr>  
    
       
       			<tr>
            		<td><html:img src="images/spacer.gif" border="0" width="1" height="1"/></td>
       
       				<td width="100%">      
      					<table border="0" cellpadding="5" cellspacing="5" width="100%" bgcolor="#ffffff">
                   
            				<tr><td class="label2"><bean:message key="prompt.address.searchname"/>:</td></tr>
    						<tr><td class="label2">
									<html:text property="searchString1" size="100" maxlength="100" styleClass="forminput1" onkeypress="return noenter()"/>
              				</td></tr>
                  
							<tr><td class="label8">                           
		 <bean:message key="prompt.address.wildcardhelp"/>	
							</td></tr>
                                                                 
            				<tr><td class="label2"><bean:message key="prompt.address.searchtype"/>:</td></tr>
    						<tr><td class="label2">
		<html:select property="searchString2" styleClass="forminput1">
			<html:option value=""><bean:message key="prompt.selectatype"/></html:option>
			<html:options collection="addrtypes" property="typekey" labelProperty="typekey"/>	
		</html:select>	
              				</td></tr>
                                                        
    					</table>
					</td>
    			</tr>
		
    
    			<tr><td colspan="2"><img src="images/spacer.gif" height="10"/></td></tr>  
      
				<tr><td colspan="2">
					<div class="btnPane">	        
						<button id="searchButton" class="btn btnFF" type="submit">
						<img class=" IM search-default" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />
        					<span>Search</span>
        				</button>    
     
						<button id="addButton" class="btn btnFF" type="button" onClick="document.location.href='EditAddress.do?action=Create'">
        					<img class=" IM N-IM" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />
        					<span>Add</span>
        				</button>    
                        
						<button id="exitButton" class="btn btnFF" type="button"  onclick="document.location.href='MainMenu.do'">
			                    <img class=" IM cross-IM" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />
            			        <span><bean:message key="button.exit"/></span>
			            </button>                                               
					</div> 
				</td></tr>
 


        		<tr>
					<td><html:img src="images/spacer.gif" border="0" width="1" height="1"/></td>
       
       				<td width="100%">   
 						<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
							<tr>
		  						<td class="column-header1" width="35%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="document.location.href='ListAddress.do?orderBy=Name&gotoPage=0'">
										<span><bean:message key="heading.name"/></span>                        
										<logic:notEqual name="ListAddressForm" property="orderBy" scope="session" value="Name">              
									         <img class=" IM2 Spacer-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:notEqual>
										<logic:equal name="ListAddressForm" property="orderBy" scope="session" value="Name">              
									         <img class=" IM2 Sortdown-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:equal>
							        </button>
								</td>

		  						<td class="column-header2" width="30%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="document.location.href='ListAddress.do?orderBy=Addr1&gotoPage=0'">
										<span><bean:message key="heading.address"/></span>                        
										<logic:notEqual name="ListAddressForm" property="orderBy" scope="session" value="Addr1">              
									         <img class=" IM2 Spacer-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:notEqual>
										<logic:equal name="ListAddressForm" property="orderBy" scope="session" value="Addr1">              
									         <img class=" IM2 Sortdown-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:equal>
							        </button>
								</td>  
                                                    
		  						<td class="column-header2" width="10%">              
                    				<button id="sortButton" class="column-header-btn1" type="button" onClick="document.location.href='ListAddress.do?orderBy=Typekey&gotoPage=0'">
										<span><bean:message key="heading.type"/></span>                        
										<logic:notEqual name="ListAddressForm" property="orderBy" scope="session" value="Typekey">              
									         <img class=" IM2 Spacer-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:notEqual>
										<logic:equal name="ListAddressForm" property="orderBy" scope="session" value="Typekey">              
									         <img class=" IM2 Sortdown-IM2" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />       
										</logic:equal>
							        </button>
								</td>                                                    
                    
                                                                                                  					
		  						<td width="25%"></td>
							</tr>
                
<%int row=0;%>			
							<logic:iterate id="addressLineItem" name="ListAddressForm" property="lineItems">
                            
<%String _class = "column-cell1";if ((row % 2) == 0) _class = "column-cell2";row++;%>	
			
  								<tr>
									<td class="<%= _class %>"><bean:write name="addressLineItem" property="name" filter="true"/></td> 
                                    
									<td class="<%= _class %>"><bean:write name="addressLineItem" property="addr1" filter="true"/>&nbsp;</td>                                     
									<td class="<%= _class %>"><bean:write name="addressLineItem" property="typekey" filter="true"/></td>                                      
                                                                                                    					 
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
											<tr>
		  										<td class="<%= _class %>">
<app:linkAddressLineItem page="/ListAddresscontact.do"><bean:message key="prompt.contacts"/></app:linkAddressLineItem>                                                
                                                </td>

                                    			<td class="<%= _class %>" align="right">
<app:linkAddressLineItem page="/EditAddress.do?action=Edit"><bean:message key="prompt.edit"/></app:linkAddressLineItem>
&nbsp;&nbsp;|&nbsp;&nbsp;
<app:linkAddressLineItem page="/EditAddress.do?action=Copy"><bean:message key="prompt.copy"/></app:linkAddressLineItem>	
&nbsp;&nbsp;|&nbsp;&nbsp;
<app:linkAddressLineItem page="/EditAddress.do?action=Delete"><bean:message key="prompt.delete"/></app:linkAddressLineItem>		
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

<tr><td colspan="2"><jsp:include page="/pages/footer.jsp" flush="true" /></td></tr>

</table>

</body>
</html:html>
