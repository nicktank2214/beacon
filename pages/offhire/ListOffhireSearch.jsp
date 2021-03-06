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

<div id="search" style="position:absolute; left:0px; top:0px; width:220px; height:650px; z-index:10; visibility: visible;">
  <table border="0" cellpadding="1" cellspacing="2" width="100%" class="offhireSearchBgcolor">

    <tr>
      <td><img src="images/spacer.gif" height="10"/></td>
    </tr>
    <tr>
      <td class="offhireSearchText"><bean:message key="prompt.offhireno"/>: </td>
    </tr>
    <tr>
      <td class="offhireSearchText"><html:text property="searchString1" size="30" maxlength="40" styleClass="ftforminputsmall" onkeypress="return noenter()"/>
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>	
    <tr>
      <td align="left" class="offhireSearchText"><bean:message key="prompt.lessee"/>:</td>
    </tr>
    <tr>
      <td align="left"><html:select property="searchString3" styleClass="ftforminputsmall">
          <html:option value="%">
            <bean:message key="prompt.selectalessee"/>
          </html:option>
          <html:options collection="lessees" property="addrkey" labelProperty="shortname"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>
    <tr>
      <td align="left" class="offhireSearchText"><bean:message key="prompt.location"/>:</td>
    </tr>
    <tr>
      <td align="left"><html:select property="searchString4" styleClass="ftforminputsmall">
          <html:option value="%">
            <bean:message key="prompt.selectalocation"/>
          </html:option>
          <html:options collection="locations" property="locationkey" labelProperty="shortName"/>
        </html:select>
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>	
    <tr>
      <td class="offhireSearchText"><bean:message key="prompt.submitteddate"/>: </td>
    </tr>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td class="offhireSearchText">&nbsp;<bean:message key="prompt.from"/>:</td>
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
            <td class="offhireSearchText">&nbsp;<bean:message key="prompt.to"/>:</td>
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
      <td class="offhireSearchText"><bean:message key="prompt.submittedby"/>: </td>
    </tr>
    <tr>
      <td>
              <html:select property="searchString5" styleClass="ftforminputsmall">
                <html:option value="%">
                  <bean:message key="prompt.selectauserid"/>
                </html:option>
                <html:options collection="users" property="userid" labelProperty="name"/>
              </html:select>	  	  
      </td>
    </tr>
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>	
    <tr>
      <td align="left" class="offhireSearchText">
		<bean:message key="prompt.resultsperpage"/>	
      </td>
    </tr>
    <tr>
      <td align="left" valign="top">
		<html:select property="maxResults" styleClass="ftforminputsmall">
      		<html:options collection="pageresults" property="id.codekey" labelProperty="descr"/>
    	</html:select>	
      </td>
    </tr>
    	
    <tr>
      <td><img src="images/spacer.gif" height="5"/></td>
    </tr>
    
<tr><td>

 <div class="btnPaneSearch">     
		<button id="searchButton" class="btn btnFF" type="submit">
        <img class=" IM search-default" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />
        <span>Search</span>
        </button>      

		<button id="exitButton" class="btn btnFF" type="button"  onclick="document.location.href='MainMenu.do'">
		<img class=" IM cross-IM" title="" src="images/buttons/clear1x1.gif" alt="" style="border-width:0px;" />
        <span><bean:message key="button.exit"/></span>
		</button>    
 </div>
 
</td></tr>
    
  </table>
</div>
