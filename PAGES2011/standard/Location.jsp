<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%--
 *
 * Amendments
 * ----------
 *
 * NT	2010-04-02		201000017		Location Maintenance - Sychronize update of Locationcode and portunicon fields
 * 												
--%>
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


<script language="JavaScript">
var searchWin;

function locationcodeSearch(field, value){
	var url     = 'EditLocationcodeSearch.do?maxResults=999&headerInfo1=&headerInfo2='+field
					+'&id='
					+document.forms[0].locationcode.value
					+'&searchString1='
					//+document.forms[0].country.value
					;					  
	searchWin   = window.open('<html:rewrite href="'+url+'"></html:rewrite>', null, 'status=no,menubar=no,location=no,resizable,scrollbars,width=950,height=600');
	searchWin.focus();
}

function clearLocationcode(){
    document.forms[0].locationcode.value = '';
}
</script>



<script type="text/javascript">
// global flag
var isIE = false;

// global request and XML document objects
var req;

// retrieve XML document (reusable generic function);
// parameter is URL string (relative or complete) to
// an .xml file whose Content-Type is a valid XML
// type, such as text/xml; XML source must be from
// same domain as HTML file
function loadXMLDoc(url) {
var url2 = "RefreshLocationcodeList?countrykey="+url;
//alert(url2);
    // branch for native XMLHttpRequest object
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = processReqChange;
        req.open("GET", url2, true);
        req.send(null);
    // branch for IE/Windows ActiveX version
    } else if (window.ActiveXObject) {
        isIE = true;
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = processReqChange;
            req.open("GET", url2, true);
            req.send();
        }
    }
}

// handle onreadystatechange event of req object
function processReqChange() {
if (req.readyState == 4) {
           strResponse = req.responseText;
           switch (req.status) {	   
                   // Page-not-found error
                   case 404:
                           alert('Error: Not Found. The requested URL ' + 
                                   strURL + ' could not be found.');
                           break;
                   // Display results in a full window for server-side errors
                   case 500:
                           handleErrFullPage(strResponse);
                           break;
                   default:
                           // Call JS alert for custom error or debug messages
                           if (strResponse.indexOf('Error:') > -1 || 
                                   strResponse.indexOf('Debug:') > -1) {
                                   alert(strResponse);
                           }
                           // Call the desired result function
                           else {
                                   //eval(strResultFunc + '(strResponse);');
            						clearLocationcodeList();
            						buildLocationcodeList();							   
                           }
                           break;
           }
   }
}


function handleErrFullPage(strIn) {

        var errorWin;

        // Create new window and display error
        try {
                errorWin = window.open('', 'errorWin');
                errorWin.document.body.innerHTML = strIn;
        }
        // If pop-up gets blocked, inform user
        catch(e) {
                alert('An error occurred, but the error message cannot be' +
                        ' displayed because of your browser\'s pop-up blocker.\n' +
                        'Please allow pop-ups from this Web site.');
        }
}


// invoked by "Category" select element change;
// loads chosen XML document, clears Fromlocationkeys select
// element, loads new items into Fromlocationkeys select element
function loadDoc(evt) {
    // equalize W3C/IE event models to get event object
    evt = (evt) ? evt : ((window.event) ? window.event : null);
    if (evt) {
        // equalize W3C/IE models to get event target reference
        var elem = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
        if (elem) {
            try {
                if (elem.selectedIndex > 0) {
                    loadXMLDoc(elem.options[elem.selectedIndex].value);
                }
            }
            catch(e) {
                var msg = (typeof e == "string") ? e : ((e.message) ? e.message : "Unknown Error");
                alert("Unable to get XML data:\n" + msg);
                return;
            }
        }
    }
}

// retrieve text of an XML document element, including
// elements using namespaces
function getElementTextNS(prefix, local, parentElem, index) {
    var result = "";
    if (prefix && isIE) {
        // IE/Windows way of handling namespaces
        result = parentElem.getElementsByTagName(prefix + ":" + local)[index];
    } else {
        // the namespace versions of this method
        // (getElementsByTagNameNS()) operate
        // differently in Safari and Mozilla, but both
        // return value with just local name, provided
        // there aren't conflicts with non-namespace element
        // names
        result = parentElem.getElementsByTagName(local)[index];
    }
    if (result) {
        // get text, accounting for possible
        // whitespace (carriage return) text nodes
        if (result.childNodes.length > 1) {
            return result.childNodes[1].nodeValue;
        } else {
            return result.firstChild.nodeValue;
        }
    } else {
        return "n/a";
    }
}

// add item to select element the less
// elegant, but compatible way.
function appendToSelect(select, value, content) {
    var opt;
    opt = document.createElement("option");
    opt.value = value;	
    opt.appendChild(content);
    select.appendChild(opt);
}


// empty select list content
function clearLocationcodeList() {
    var select = document.getElementById("locationcode");
    while (select.length > 0) {
        select.remove(0);
    }
}
// fill select list with items from
// the current XML document
function buildLocationcodeList() {
    var select = document.getElementById("locationcode");
    var items = req.responseXML.getElementsByTagName("locationcode");
    // loop through <locationcode> elements, and add each nested
    // <key> element to select element
    for (var i = 0; i < items.length; i++) {	
        appendToSelect(
					select, 
					getElementTextNS("", "key", items[i], 0),
		            document.createTextNode(getElementTextNS("", "ldesc", items[i], 0))
					);
    }
}
</script>


<logic:equal name="LocationForm" property="action"
            scope="request" value="Create">
<title><bean:message key="title.create"/></title>
</logic:equal><logic:equal name="LocationForm" property="action"
            scope="request" value="Delete">
<title><bean:message key="title.delete"/></title>
</logic:equal><logic:equal name="LocationForm" property="action"
            scope="request" value="Edit">
<title><bean:message key="title.edit"/></title>
</logic:equal>
<link rel="stylesheet" type="text/css" href="css/base.css" />

</head>


<body onUnload="if (searchWin != null) {searchWin.close();}">


<table cellpadding="0" cellspacing="0" border="0" width="100%">
  <tr>
    <td colspan="2"><jsp:include page="/pages/header.jsp" flush="true" />
    </td>
  </tr>
  <%-- BREADCRUMBS -----------------%>
  <tr>
<%--    <td height="20" bgcolor="#CCCCCC">&nbsp;</td>--%>
    <td class="tdwht"><span class="heading2"> <strong><bean:message key="location.title"/></strong> </span> <span class="heading2"> <html:link action="/ListLocation" styleClass="link4"> <bean:message key="title.list"/> </html:link> </span> <span class="text6">></span> <span class="text6"><strong> <bean:message key="title.maintenance"/> </strong></span>
</td>
  </tr>
  <tr valign="top" bgcolor="#CCCCCC">
    <td height="1" colspan="2"></td>
  </tr>
  <%-- BREADCRUMBS -----------------%>
  <tr>
<%--    <td bgcolor="#CCCCCC" width="121" valign="top" align="center"><jsp:include page="/pages/sidebarWelcome.jsp" flush="true" />
    </td>--%>
    <td><div style="OVERFLOW:auto; width:650px; height:650px; position:relative;">
        <table border="0" cellpadding="5" cellspacing="5" width="100%" bgcolor="#ffffff">
          <tr>
            <td><html:errors/> 
			
			<html:form action="/SaveLocation" focus="locationkey"> 
			<html:hidden property="action"/>
			<html:hidden property="description1"/>
			<html:hidden property="description2"/>
			<html:hidden property="description3"/>
			<html:hidden property="portUnicon"/>
			<html:hidden property="plantCode"/>
<html:hidden property="locationType"/>

						
              <table width="80%" border="0" cellspacing="1" cellpadding="0" bgcolor="#999999">
                <tr>
                  <td><table width="100%" border="0" cellspacing="2" cellpadding="2" bgcolor="#ffffff">
                      <tr>
                        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                              <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
                                  <tr>
                                    <td class="td7"><strong> <logic:equal name="LocationForm" property="action"
            scope="request" value="Create"> <bean:message key="location.title.create"/> </logic:equal> <logic:equal name="LocationForm" property="action"
            scope="request" value="Delete"> <bean:message key="location.title.delete"/> </logic:equal> <logic:equal name="LocationForm" property="action"
            scope="request" value="Edit"> <bean:message key="location.title.edit"/> </logic:equal> </strong></td>
                                  </tr>
                                </table></td>
                            </tr>
                            <tr>
                              <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.code"/>: </div></td>
                                    <td width="78%" class="td2"><logic:equal name="LocationForm" property="action"
                  scope="request" value="Create"> <html:text property="locationkey" maxlength="10" size="20" styleClass="ftforminputsmall"/> </logic:equal> <logic:notEqual name="LocationForm" property="action"
                     scope="request" value="Create"> <strong><html:hidden property="locationkey" write="true"/></strong> </logic:notEqual> </td>
                                  </tr>
                                </table></td>
                            </tr>
                            <logic:notEqual name="LocationForm" property="action"
                     scope="request" value="Delete">
                            <tr>
                              <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
                                  <tr>
                                    <td width="22%" class="td2" valign="top"><div align="right"> <bean:message key="prompt.type"/>: </div></td>
                                    <td width="78%" class="td2">
<%--									<html:select property="locationType" styleClass="ftforminputsmall"> 
									<html:option value=""><bean:message key="prompt.selectatype"/></html:option> 
									<html:options collection="locationtypes" property="typekey" labelProperty="typekey"/> 
									</html:select> --%>
<html:select property="arraylocationtypelist" multiple="true" size="10" styleClass="ftforminputsmall">
			<html:options collection="locationtypes" property="typekey" labelProperty="typekey"/> 	
</html:select>                                    
                                    
									</td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.name"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="locationName" size="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.shortname"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="shortName" size="30" maxlength="20" styleClass="ftforminputsmall"/> </td>
                                  </tr>								  
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"><bean:message key="prompt.contact"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="contact" size="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                </table></td>
                            </tr>
                            <tr>
                              <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.location"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="address1" size="50" maxlength="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2">&nbsp;</td>
                                    <td width="78%" class="td2"><html:text property="address2" size="50" maxlength="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2">&nbsp;</td>
                                    <td width="78%" class="td2"><html:text property="address3" size="50" maxlength="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.postalcode"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="postalCode" size="20" maxlength="20" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.city"/>: </div></td>
                                    <td width="78%" class="td2"><html:select property="city" styleClass="ftforminputsmall"> <html:option value=""><bean:message key="prompt.city"/></html:option> <html:options collection="citys" property="city" labelProperty="city"/> </html:select> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.state"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="state" size="20" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.country"/>: </div></td>
                                    <td width="78%" class="td2">
									<html:select 
										property="country" 
										styleClass="ftforminputsmall" onchange="clearLocationcode();"> 
										<html:option value=""><bean:message key="prompt.country"/></html:option> 
										<html:options collection="countrys" property="country" labelProperty="country"/> 
									</html:select> 
									</td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.locationuncode"/>: </div></td>
                                    <td width="78%" class="td2">
<html:text property="locationcode" styleClass="ftforminputsmall"/>&nbsp;
		<html:link href="javascript:void(locationcodeSearch('locationcode'))">
		<html:img src="images/buttons/btn_search_12x12.png" alt="Search" border="0" align="middle"/>
		</html:link> 
									</td>
                                  </tr>								  
                                </table></td>
                            </tr>
                            <tr>
                              <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.phone"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="phone" size="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                  <tr>
                                    <td width="22%" class="td2"><div align="right"> <bean:message key="prompt.fax"/>: </div></td>
                                    <td width="78%" class="td2"><html:text property="fax" size="50" styleClass="ftforminputsmall"/> </td>
                                  </tr>
                                </table></td>
                            </tr>
                            
                            
 
<script type="text/javascript" language="javascript" src="/beacon/scripts/ajax/ajaxV2.js"></script> 
<script type="text/javascript" language="javascript" src="/beacon/scripts/ajax/standard/ListG2codeAjax.js"></script>
<script type="text/javascript" language="javascript" src="/beacon/scripts/ajax/standard/ListG3codeAjax.js"></script>
<script type="text/javascript" language="javascript" src="/beacon/scripts/ajax/standard/ListG4codeAjax.js"></script>

      <tr>
    <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#999999">
        <tr>
		  <td rowspan="2">
		  	<img src="images/spacer.gif" width="1" border="0"/>
		  </td>
          <td class="td2" width="100%">
		        &nbsp;<strong><bean:message key="gcode.title"/> </strong>
		  </td>
		</tr>		
 <tr><td>	  
                  	<table width="100%" border="0" cellspacing="0" cellpadding="1" bgcolor="#ffffff">
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="heading.g1code"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
        <html:select property="g1codekey" styleClass="ftforminputsmall" onchange="processListG2code();">
			<html:option value=""> <bean:message key="prompt.selectacode"/></html:option>
			<html:options collection="g1codes" property="g1codekey" labelProperty="g1codekey"/>	
		</html:select>			  
          </td>
        </tr>
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="heading.g2code"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
        <html:select property="g2codekey" styleClass="ftforminputsmall" onchange="processListG3code();">
			<html:option value=""> <bean:message key="prompt.selectacode"/></html:option>	
            			<html:options collection="g2codes" property="g2codekey" labelProperty="g2codekey"/>	
		</html:select>	  
          </td>
        </tr>
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="heading.g3code"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
        <html:select property="g3codekey" styleClass="ftforminputsmall" onchange="processListG4code();">
			<html:option value=""> <bean:message key="prompt.selectacode"/></html:option>	
			<html:options collection="g3codes" property="g3codekey" labelProperty="g3codekey"/>	            
		</html:select>	  
          </td>
        </tr> 
        <tr>
          <td width="22%" class="td2">
		  <div align="right">
		        <bean:message key="heading.g4code"/>:
		  </div>
		  </td>
          <td width="78%" class="td2">
        <html:select property="g4codekey" styleClass="ftforminputsmall">
			<html:option value=""> <bean:message key="prompt.selectacode"/></html:option>
			<html:options collection="g4codes" property="g4codekey" labelProperty="g4codekey"/>	            	
		</html:select>	 		  
          </td>
        </tr>                	        			        			
    </table>
</td></tr>
    </table></td>
  </tr>	
                              
                            </logic:notEqual>
							
							
                            <tr>
                              <td><table width="100%"  border="0" cellpadding="0" cellspacing="1" bgcolor="#ffffff">
                                  <tr>
                                    <td class="td1">
									
<logic:equal name="LocationForm" property="action" scope="request" value="Create"> 
	<input type="submit" value='<bean:message key="button.add"/>' class="button1" onClick="if (searchWin != null) {searchWin.close();}">		  
</logic:equal> 

<logic:equal name="LocationForm" property="action"
                  scope="request" value="Delete"> 
	<html:submit styleClass="button1"> <bean:message key="button.confirm"/> </html:submit> 
</logic:equal> 

<logic:equal name="LocationForm" property="action"
                  scope="request" value="Edit"> 
	<input type="submit" value='<bean:message key="button.save"/>' class="button1" onClick="if (searchWin != null) {searchWin.close();}"> 
</logic:equal> 

<logic:notEqual name="LocationForm" property="action" scope="request" value="Delete"> 
					 <html:reset styleClass="button1"> <bean:message key="button.reset"/> </html:reset> 
</logic:notEqual>
 
	<input type="submit" name="org.apache.struts.taglib.html.CANCEL" value='<bean:message key="button.cancel"/>' onClick="if (searchWin != null) {searchWin.close();}; bCancel=true;" class="button1">
					 
					 </td>
                                  </tr>
                                </table></td>
                            </tr>
                          </table></td>
                      </tr>
                    </table></td>
                </tr>
              </table>
              </html:form> </td>
          </tr>
        </table>
      </div></td>
  </tr>
  <tr>
    <td colspan="2"><jsp:include page="/pages/footer.jsp" flush="true" />
    </td>
  </tr>
</table>
</body>
</html:html>