package com.bureaueye.beacon.taglib.quotation;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.config.ModuleConfig;
import org.apache.struts.taglib.TagUtils;
import org.apache.struts.util.MessageResources;

import com.bureaueye.beacon.bean.Constants;
import com.bureaueye.beacon.model.quotation.Quonote;


/**
 * Generate a URL-encoded hyperlink to the specified URI, with associated query
 * parameters selecting a specified Subscription.
 * 
 * @version $Rev: 54929 $ $Date: 2004-10-16 17:38:42 +0100 (Sat, 16 Oct 2004) $
 */

public class LinkQuonoteLineItemTag extends TagSupport {

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

		String name = "quonoteLineItem";

		Quonote quonote = null;
		try {
			quonote = (Quonote) pageContext.findAttribute(name);
		} catch (ClassCastException e) {
		}
		if (quonote == null)
			throw new JspException(messages.getMessage(
					"lineItem.noQuonote", name));
		if (page.indexOf("?") < 0)
			url.append("?");
		else
			url.append("&");
		url.append("quohdrId=");
		url.append(TagUtils.getInstance().filter(
				quonote.getQuohdrId().toString()));
		url.append("&quonoteId=");
		url.append(TagUtils.getInstance().filter(
				quonote.getQuonoteId().toString()));

		
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
