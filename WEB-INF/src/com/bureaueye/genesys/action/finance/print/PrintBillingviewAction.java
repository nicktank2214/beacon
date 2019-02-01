package com.bureaueye.genesys.action.finance.print;

import java.awt.Color;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.bean.Constants;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.genesys.action.finance.pdf.BillingviewEndPage;
import com.bureaueye.genesys.bean.finance.print.PrintFinancehdr;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfWriter;





public final class PrintBillingviewAction extends BaseAction {


	Font font;
	Font fontBold;	
	Font fontBoldLarge;
	Font fontSmall;
	Font fontSmallBold;	
	Font fontBoldHugeLightgray;


	public PrintBillingviewAction() {
		try {
			//init default fonts for document type
			BaseFont baseFont = BaseFont.createFont(com.bureaueye.beacon.bean.Constants.FONT_NAME, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			font=new Font(
					baseFont,
					com.bureaueye.beacon.bean.Constants.FONT_SIZE,
					com.bureaueye.beacon.bean.Constants.FONT_STYLE
			);
			fontBold = new Font(font.getFamily(), font.getSize(), Font.BOLD);	
			fontBoldLarge = new Font(font.getFamily(), font.getSize()+2, fontBold.getStyle());
			fontSmall = new Font(font.getFamily(), font.getSize()-3, font.getStyle());			
			fontSmallBold = new Font(fontSmall.getFamily(), fontSmall.getSize(), Font.BOLD);
			fontBoldHugeLightgray=new Font(font.getFamily(), font.getSize()+24, fontBold.getStyle());
			fontBoldHugeLightgray.setColor(new BaseColor(Color.LIGHT_GRAY));
		} catch (Exception e) {
		}
	}

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		// This will use the existing session, or create a new one if it has
		// timed out
		// The CheckLogonTag will make sure that the user is still logged in
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(com.bureaueye.beacon.bean.standard.Constants.USER_KEY);

		// Remove all other ActionForms excepts the ones on the formsToSkip Set
		// Hopefully, this will keep HttpSession objects to a minimum
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" isNew = " + session.isNew());

		String parameter = mapping.getParameter();
		if (parameter == null) {
			parameter = "";
		}
		List formsToSkip = Arrays.asList(parameter.split(","));
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" formsToSkip = " + formsToSkip);
		for (Enumeration e = session.getAttributeNames(); e.hasMoreElements();) {
			String actionFormName = (String) e.nextElement();
			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" actionFormName = " + actionFormName + "\t Mapping = "
					+ mapping.getName());

			if (actionFormName.endsWith("Form")
					&& !actionFormName.equals(mapping.getName())
					&& !formsToSkip.contains(actionFormName)) {
				log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" remove = " + actionFormName);
				session.removeAttribute(actionFormName);
			}
		}

		ListForm listForm = (ListForm) form;
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Populating form ");



		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Action: "+listForm.getAction());

		// generate print bean
		PrintFinancehdr _print = new PrintFinancehdr();

		// not a test print assign Printed fields
		/*		if (!listForm.getAction().equals("Test")) {
			_print.setPrinted(new Integer(listForm.getId()), user);					
		}	*/	


		// step 1: init document				
		Document _document = new Document();	

		// step 2: we set the ContentType and create an instance of the corresponding Writer				
		response.setContentType("application/pdf");
		PdfWriter _PdfWriter = PdfWriter.getInstance(_document, response.getOutputStream());
		_PdfWriter.setPageEvent(new BillingviewEndPage());


		try {

			// init pagesize default for user			
			_document.setPageSize(PageSize.A4);			
			_document.setPageCount(1);	
			if (user != null && user.getPagesize().equals("LEGAL")) _document.setPageSize(PageSize.LEGAL);
			if (user != null && user.getPagesize().equals("LETTER")) _document.setPageSize(PageSize.LETTER);			                      
			_document.setMargins(_document.leftMargin(), _document.rightMargin(), 50f, 50f);


			_document.open();

			_document = _print.executePrint(
					new Integer(listForm.getId()), 
					listForm.getButton(), 
					user, 
					_document
			);		


			// test print Watermark
			if (listForm.getAction().equals("Test")) {
				String waterMarkText=Constants.TEST_PRINT;
				//add watermark
				ColumnText.showTextAligned(
						_PdfWriter.getDirectContentUnder(),
						Element.ALIGN_CENTER, 
						new Phrase(waterMarkText, fontBoldHugeLightgray),
						297.5f, 
						421, 
						_PdfWriter.getPageNumber() % 2 == 1 ? 45 : -45
				);			
			}


			// close the document (the outputstream is also closed internally)
			_document.close(); 

			return null;
		}
		catch (BadElementException bee) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" executePrint: BadElementException: "+bee.getMessage());			
		}		
		catch (DocumentException de) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" executePrint: DocumentException: "+de.getMessage());			
		}
		catch (Exception e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" executePrint: Exception: "+e.getMessage());			
		}
		finally {
		}


		// Forward control to the edit user registration page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page: "
				+ mapping.findForward("success").getPath());

		return findSuccess(mapping);
	}


}