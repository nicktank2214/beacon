package com.bureaueye.beacondms.taglib.dms;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

import com.bureaueye.beacon.bean.Constants;
import com.bureaueye.beacondms.model.dms.Xdocument;






public class LinkTrackLineItemTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String page;

	private MessageResources messages = MessageResources
			.getMessageResources(Constants.APPLICATION_RESOURCES);

	public void setPage(String page) {
		this.page = page;
	}

	public int doStartTag() throws JspException {

		// Generate the URL to be encoded
		ModuleConfig config = (ModuleConfig) pageContext.getRequest()
				.getAttribute(org.apache.struts.Globals.MODULE_KEY);
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		StringBuffer url = new StringBuffer(request.getContextPath());
		url.append(config.getPrefix());
		url.append(page);

		String name = "xdocumentLineItem";

		Xdocument xdocument = null;
		try {
			xdocument = (Xdocument) pageContext.findAttribute(name);
		} catch (ClassCastException e) {
		}
		if (xdocument == null)
			throw new JspException(messages.getMessage(
					"lineItem.noXdocument", name));
		if (page.indexOf("?") < 0)
			url.append("?");
		else
			url.append("&");
		url.append("xdocumentId=");
		url.append(TagUtils.getInstance().filter(
				xdocument.getXdocumentId().toString()));

		
		// Generate the hyperlink start element
		HttpServletResponse response = (HttpServletResponse) pageContext
				.getResponse();
		StringBuffer results = new StringBuffer("<a href=\"");
		results.append(response.encodeURL(url.toString()));
		results.append("\">");

		// Print this element to our output writer
		try {
			pageContext.getOut().print(results.toString());
		} catch (IOException e) {
			throw new JspException(messages.getMessage("link.io", e.toString()));
		}

		// Evaluate the body of this tag
		return EVAL_BODY_INCLUDE;

	}

	public int doEndTag() throws JspException {
		// Print the ending element to our output writer
		try {
			pageContext.getOut().print("</a>");
		} catch (IOException e) {
			throw new JspException(messages.getMessage("link.io", e.toString()));
		}

		return EVAL_PAGE;
	}
}
