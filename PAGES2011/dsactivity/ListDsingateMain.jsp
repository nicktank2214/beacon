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

<!-- tell jsp to use the user bean -->
<jsp:useBean
  id="user"
  class="com.bureaueye.beacon.model.standard.User"
  scope="session"
/>

<app:checkLogon/>

<html:html>
  <head>
  <title>
  <bean:message key="ingate.title"/>
  </title>
  <link rel="stylesheet" type="text/css" href="css/base.css" />
  
  <script type="text/javascript">
function noenter() {
  return !(window.event && window.event.keyCode == 13); }
</script>
<script language="javascript" type="text/javascript">
//<![CDATA[
	function onLoad() {	
		//onLoadSidebar('menu2');								
	}
    //]]>
</script>

  </head>

  <body onLoad="onLoad();">
  <table cellpadding="0" cellspacing="0" border="0" width="100%">
    <%-- HEADER -----------------%>
    <tr>
      <td colspan="5"><jsp:include page="/pages/header.jsp" flush="true" />
      </td>
    </tr>
    <%-- BREADCRUMBS -----------------%>
    <tr>

      <td class="tdwht" colspan="4"><span class="heading2"> <strong>
        <bean:message key="ingate.title"/>
        </strong> </span> <span class="heading2">
        <html:link action="/ListDsingate" styleClass="link5">
          <bean:message key="title.list"/>
        </html:link>
        </span> </td>
    </tr>
    <tr valign="top" bgcolor="#CCCCCC">
      <td height="1" colspan="5"></td>
    </tr>
    <%-- BREADCRUMBS -----------------%>
    <tr>


	  
      <td><div style="OVERFLOW:auto; width:1350px; height:650px; position:relative;">
	  
<html:form action="/ListDsingate" focus="searchString1">
	<html:hidden property="gotoPage" />
          <jsp:include page="/pages/dsactivity/ListDsingateSearch.jsp" flush="true" />
          <jsp:include page="/pages/dsactivity/ListDsingateResults.jsp" flush="true" />
</html:form>	
	  
        </div></td>
		

	  
      </td>
	  
    </tr>
    <%-- FOOTER -----------------%>
    <tr>
      <td colspan="5"><jsp:include page="/pages/footer.jsp" flush="true" />
      </td>
    </tr>
  </table>
  </body>
</html:html>
