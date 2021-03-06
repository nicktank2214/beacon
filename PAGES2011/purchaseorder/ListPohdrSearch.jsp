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

<div id="search" style="position:absolute; left:0px; top:0px; width:200px; height:650px; z-index:10; visibility: visible;">
  <table border="0" cellpadding="1" cellspacing="2" width="100%" class="purchaseorderSearchBgcolor">
    <tr>
      <td colspan="2" valign="middle"><h1 class="underline mtb0">
<INPUT name="searchButton" border="0" type="image" id="searchButton" src="images/refreshButtonBorderGrey.gif" align="bottom" onMouseOut="MM_swapImgRestore();" onMouseOver="MM_swapImage('searchButton','','images/refreshButtonBorderGrey.gif','searchButton','','images/refreshButtonBorderGrey_over_blue.gif',1);"/>
          <bean:message key="pohdr.title.search"/>
        </h1></td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="10"/></td>
    </tr>
    <tr>
      <td class="purchaseorderSearchText"><bean:message key="prompt.pono"/>
        : </td>
    </tr>
    <tr>
      <td class="purchaseorderSearchText"><html:text property="searchString1" size="30" maxlength="40" styleClass="ftforminputsmall" onkeypress="return noenter()"/>
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>
    <tr>
      <td align="left" width="60%" class="purchaseorderSearchText"><bean:message key="prompt.vendor"/>:</td>
    </tr>
    <tr>
      <td align="left" width="60%"><html:select property="searchString3" styleClass="ftforminputsmall">
          <html:option value="%">
            <bean:message key="prompt.selectavendor"/>
          </html:option>
          <html:options collection="vendors" property="addrkey" labelProperty="shortname"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>
    <tr>
      <td align="left" width="60%" class="purchaseorderSearchText"><bean:message key="prompt.shipto"/>:</td>
    </tr>
    <tr>
      <td align="left" width="60%">
		<html:select property="searchString7" styleClass="ftforminputsmall">
            <html:option value="%"><bean:message key="prompt.selectalocation"/></html:option>
			<html:options collection="locations" property="locationkey" labelProperty="shortName"/>	
		</html:select>		
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>	
    <tr>
      <td class="purchaseorderSearchText"><bean:message key="prompt.createdate"/>: </td>
    </tr>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="purchaseorderSearchText">&nbsp;<bean:message key="prompt.from"/>:</td>
          </tr>
          <tr>
            <td>&nbsp;<html:select property="searchDate1dd" styleClass="ftforminputsmall">
                <html:option value="">
                  <bean:message key="prompt.day"/>
                </html:option>
                <html:options collection="days" property="value" labelProperty="label"/>
              </html:select>
              <html:select property="searchDate1mm" styleClass="ftforminputsmall">
                <html:option value="">
                  <bean:message key="prompt.month"/>
                </html:option>
                <html:options collection="months" property="value" labelProperty="label"/>
              </html:select>
              <html:select property="searchDate1yyyy" styleClass="ftforminputsmall">
                <html:option value="">
                  <bean:message key="prompt.year"/>
                </html:option>
                <html:options collection="years" property="value" labelProperty="label"/>
              </html:select>
            </td>
          </tr>
          <tr>
            <td class="purchaseorderSearchText">&nbsp;<bean:message key="prompt.to"/>:</td>
          </tr>
          <tr>
            <td>&nbsp;<html:select property="searchDate2dd" styleClass="ftforminputsmall">
                <html:option value="">
                  <bean:message key="prompt.day"/>
                </html:option>
                <html:options collection="days" property="value" labelProperty="label"/>
              </html:select>
              <html:select property="searchDate2mm" styleClass="ftforminputsmall">
                <html:option value="">
                  <bean:message key="prompt.month"/>
                </html:option>
                <html:options collection="months" property="value" labelProperty="label"/>
              </html:select>
              <html:select property="searchDate2yyyy" styleClass="ftforminputsmall">
                <html:option value="">
                  <bean:message key="prompt.year"/>
                </html:option>
                <html:options collection="years" property="value" labelProperty="label"/>
              </html:select>
            </td>
          </tr>
        </table></td>
    </tr>	
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>
    <tr>
      <td class="purchaseorderSearchText"><bean:message key="prompt.company"/>: </td>
    </tr>
    <tr>
      <td><html:select property="searchString4" styleClass="ftforminputsmall">
          <html:option value="">
            <bean:message key="prompt.selectacompany"/>
          </html:option>
          <html:options collection="companys" property="compid" labelProperty="name"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <td align="left" class="purchaseorderSearchText"><bean:message key="prompt.department"/>: </td>
    </tr>
    <tr>
      <td align="left"><html:select property="searchString5" styleClass="ftforminputsmall">
          <html:option value="">
            <bean:message key="prompt.selectadepartment"/>
          </html:option>
          <html:options collection="departments" property="id.codekey" labelProperty="descr"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <td align="left" width="35%" class="purchaseorderSearchText"><bean:message key="prompt.activity"/>: </td>
    </tr>
    <tr>
      <td align="left" width="40%" valign="top"><html:select property="searchString6" styleClass="ftforminputsmall">
          <html:option value="">
            <bean:message key="prompt.selectaactivity"/>
          </html:option>
          <html:options collection="activitys" property="id.codekey" labelProperty="descr"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>	
    <tr>
      <td align="left" width="35%" class="purchaseorderSearchText">
		<bean:message key="prompt.resultsperpage"/>	
      </td>
    </tr>
    <tr>
      <td align="left" width="40%" valign="top">
		<html:select property="maxResults" styleClass="ftforminputsmall">
      		<html:options collection="pageresults" property="id.codekey" labelProperty="descr"/>
    	</html:select>	
      </td>
    </tr>	

  </table>
</div>
