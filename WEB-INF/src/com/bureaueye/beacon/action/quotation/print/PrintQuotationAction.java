package com.bureaueye.beacon.action.quotation.print;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.action.quotation.pdf.QuotationEndPage;
import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.quotation.Quohdr;
import com.bureaueye.beacon.model.quotation.Quonote;
import com.bureaueye.beacon.model.quotation.Quonotecust;
import com.bureaueye.beacon.model.quotation.dto.QuochargeDTO;
import com.bureaueye.beacon.model.quotation.dto.QuocostDTO;
import com.bureaueye.beacon.model.quotation.dto.QuomovDTO;
import com.bureaueye.beacon.model.quotation.dto.QuoprodDTO;
import com.bureaueye.beacon.model.quotation.bd.QuochargeBD;
import com.bureaueye.beacon.model.quotation.bd.QuocostBD;
import com.bureaueye.beacon.model.quotation.bd.QuohdrBD;
import com.bureaueye.beacon.model.quotation.bd.QuomovBD;
import com.bureaueye.beacon.model.quotation.bd.QuonoteBD;
import com.bureaueye.beacon.model.quotation.bd.QuonotecustBD;
import com.bureaueye.beacon.model.quotation.bd.QuoprodBD;
import com.bureaueye.beacon.model.standard.Address;
import com.bureaueye.beacon.model.standard.Location;
import com.bureaueye.beacon.model.standard.Product;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.beacon.model.standard.bd.AddressBD;
import com.bureaueye.beacon.model.standard.bd.LocationBD;
import com.bureaueye.beacon.model.standard.bd.ProductBD;
import com.bureaueye.beacon.util.Util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



/**
 * Amendments
 * ----------
 *
 * NT	2009-10-28		200900051			Add Notes to Quotation print 
 *
 * NT	2010-02-15		ITT-201001-0001		Add Demurrage Currency
 * 
 * NT	2011-03-01		ITT-201103-0001		Quotation Customer Notes 
 *												
 */
public final class PrintQuotationAction extends BaseAction {

	private static Document _Document;
	private static PdfPTable _Table1;


	private static int CELL_BORDER = 0;

	Font font;
	Font fontBold;	
	Font fontBoldLarge;
	Font fontSmall;
	Font fontSmallBold;	
	Font fontHeading;

	private static BaseColor headingColor = new BaseColor(180, 43, 22);
	private static BaseColor lghtgry = new BaseColor(247, 247, 249);
	private static BaseColor lghtgry2 = new BaseColor(238, 238, 238);
	private static BaseColor black= new BaseColor(0, 0, 0);


	public PrintQuotationAction() {
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
			fontHeading = new Font(font.getFamily(), font.getSize()+4, fontBold.getStyle());
			fontHeading.setColor(headingColor);
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
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" PrintQuotationAction: isNew = " + session.isNew());

		String parameter = mapping.getParameter();
		if (parameter == null) {
			parameter = "";
		}
		List formsToSkip = Arrays.asList(parameter.split(","));
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" PrintQuotationAction: formsToSkip = " + formsToSkip);
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
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Populating form");




		// Set a transactional control token to prevent double posting
		// log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Setting transactional control token");
		// saveToken(request);

		try {

			// convert report to PDF format
			// step 1: init document				
			_Document = new Document();	
			// init pagesize default for user			
			_Document.setPageSize(PageSize.A4);			
			_Document.setPageCount(1);			
			if (user != null && user.getPagesize().equals("LEGAL")) _Document.setPageSize(PageSize.LEGAL);
			if (user != null && user.getPagesize().equals("LETTER")) _Document.setPageSize(PageSize.LETTER);

			// step 2: we set the ContentType and create an instance of the corresponding Writer				
			response.setContentType("application/pdf");
			PdfWriter _PdfWriter = PdfWriter.getInstance(_Document, response.getOutputStream());

			// step 3: set document                       
			_Document.setMargins(_Document.leftMargin(), _Document.rightMargin(), 50f, 50f);
			_Document.open();				
			_PdfWriter.setPageEvent(new QuotationEndPage());

			// create main table and add it to the document
			_Table1 = new PdfPTable(1);
			_Table1.getDefaultCell().setBorder(0);
			_Table1.getDefaultCell().setNoWrap(false);            		
			_Table1.setWidthPercentage(100f);  
			_Table1.getDefaultCell().setPadding(0);

			// read quotation header
			Quohdr quohdr = null;
			try { quohdr = new QuohdrBD(this.getSessionFactoryClusterMap()).read(new Integer(listForm.getId())); } catch (ApplicationException ae) {}
			if (quohdr != null) outputQuohdrToPDF(quohdr);   

			// new page at end of footer
			_Document.newPage();
			//_Document.add(Chunk.NEWLINE);


			// read quotation products
			// set line items
			List quoprods = null;
			try {
				quoprods = new QuoprodBD(
						this.getSessionFactoryClusterMap()
				)
				.findQuoprodsById(
						quohdr.getId().toString(), 
						0, 
						99, 
						"Productkey"
				);
			} catch (ApplicationException ae) {}					


			if (quoprods != null && quoprods.size() > 0) {
				outputQuoprodHeaderToPDF();   

				// process charges list collection
				Iterator it = quoprods.iterator();
				int row = 0;
				while (it.hasNext()) {
					// init 
					QuoprodDTO lineItem = (QuoprodDTO) it.next();

					// init select parameter					
					try {							
						// output to PDF
						outputQuoprodToPDF(lineItem, row);
					}
					catch (NullPointerException npe) {
					}	    	    		
					// increment pointer
					row++;					
				} // end list loop				
			}


			// read quotation charges
			// set line items
			List quocharges = null;
			try {
				quocharges = new QuochargeBD(
						this.getSessionFactoryClusterMap()
				)
				.findQuochargesById(
						quohdr.getId().toString(), 
						0, 
						99, 
						"Chargekey"
				);
			} catch (ApplicationException ae) {}					

			if (quocharges != null && quocharges.size() > 0) {
				outputQuochargeHeaderToPDF();   

				// process list collection
				Iterator it = quocharges.iterator();
				int row = 0;
				while (it.hasNext()) {
					// init 
					QuochargeDTO lineItem = (QuochargeDTO) it.next();

					// init select parameter					
					try {							
						// output to PDF
						outputQuochargeToPDF(lineItem, row);
					}
					catch (NullPointerException npe) {
					}	    	    		
					// increment pointer
					row++;					
				} // end list loop				
			}


			// read quotation movements
			// set line items
			List quomovs = null;
			try {
				quomovs = new QuomovBD(
						this.getSessionFactoryClusterMap()
				)
				.findQuomovsById(
						quohdr.getId().toString(), 
						0, 
						99, 
						"Seqno"
				);
			} catch (ApplicationException ae) {}					

			if (quomovs != null && quomovs.size() > 0) {
				outputQuomovHeaderToPDF();   

				// process list collection
				Iterator it = quomovs.iterator();
				int row = 0;
				while (it.hasNext()) {
					// init 
					QuomovDTO quomov = (QuomovDTO) it.next();

					//outputQuomovHeaderToPDF(); 					
					// init select parameter					
					try {							
						// output to PDF
						outputQuomovToPDF(quomov, row);
					}
					catch (NullPointerException npe) {
					}	    	    		
					// increment pointer
					row++;

					// read quotation movement costs
					// set line items
					List quocosts = null;
					try {
						quocosts = new QuocostBD(
								this.getSessionFactoryClusterMap()
						)
						.findQuomovcostsById(
								quomov.getQuomovId().toString(), 
								0, 
								99, 
								"Costkey"
						);
					} catch (ApplicationException ae) {						
					}					

					if (quocosts != null && quocosts.size() > 0) {
						outputQuocostHeaderToPDF();   

						// process list collection
						Iterator it2 = quocosts.iterator();
						int row2 = 0;
						while (it2.hasNext()) {
							// init 
							QuocostDTO lineItem = (QuocostDTO) it2.next();					    
							// init select parameter					
							try {							
								// output to PDF
								outputQuocostToPDF(lineItem, row2);
							}
							catch (NullPointerException npe) {
							}	    	    		
							// increment pointer
							row2++;

						} // end list quocost loop				
					} // quocosts found


				} // end list quomov loop				
			} // quomovs found


			// read quotation header costs
			// set line items
			List quocosts2 = null;
			try {
				quocosts2 = new QuocostBD(
						this.getSessionFactoryClusterMap()
				).findQuohdrcostsByQuohdrId(
						quohdr.getId().toString(), 
						0, 
						99, 
						"Costkey"
				);
			} catch (ApplicationException ae) {	
			}						
			if (quocosts2 != null) {
				outputQuocostHeaderToPDF2();   

				// process list collection
				Iterator it = quocosts2.iterator();
				int row = 0;
				while (it.hasNext()) {					
					// init 
					QuocostDTO lineItem = (QuocostDTO) it.next();

					// init select parameter					
					try {							
						// output to PDF
						outputQuocostToPDF2(lineItem, row);
					}
					catch (NullPointerException npe) {
					}	    	    		
					// increment pointer
					row++;				
				} // end list loop				
			}


			// read quotation notes
			// set line items
			List quonotes = null;
			try {
				quonotes = new QuonoteBD(
						this.getSessionFactoryClusterMap()		
				).findQuonotesById(
						quohdr.getId().toString(), 
						0, 
						99, 
						"Createdate",
						"Createtime"
				);
			} catch (ApplicationException ae) {	
			}					

			if (quonotes != null) {
				outputQuonoteHeaderToPDF();   

				// process list collection
				Iterator it = quonotes.iterator();
				int row = 0;
				while (it.hasNext()) {
					// init 
					Quonote lineItem = (Quonote) it.next();

					// init select parameter					
					try {							
						// output to PDF
						outputQuonoteToPDF(lineItem, row);
					}
					catch (NullPointerException npe) {
					}	    	    		
					// increment pointer
					row++;					
				} // end list loop				
			}



			//sITT-201103-0001
			// read quotation customer notes
			// set line items
			List quonotecusts = null;
			try {
				quonotecusts = new QuonotecustBD(
						this.getSessionFactoryClusterMap()		
				).findQuonotecustsById(
						quohdr.getId().toString(), 
						0, 
						99, 
						"Createdate",
						"Createtime"
				);
			} catch (ApplicationException ae) {	
			}					

			if (quonotecusts != null) {
				outputQuonotecustHeaderToPDF();   

				// process list collection
				Iterator it = quonotecusts.iterator();
				int row = 0;
				while (it.hasNext()) {
					// init 
					Quonotecust lineItem = (Quonotecust) it.next();

					// init select parameter					
					try {							
						// output to PDF
						outputQuonotecustToPDF(lineItem, row);
					}
					catch (NullPointerException npe) {
					}	    	    		
					// increment pointer
					row++;					
				} // end list loop				
			}
			//eITT-201103-0001



			// add main table to PDF document
			_Document.add(_Table1);  

			// step 4: we close the document (the outputstream is also closed internally)
			_Document.close();                                

			return null; 

		}
		catch (BadElementException bee) {
		}		
		catch (DocumentException de) {
		}
		catch (Exception e) {
		}
		finally {
		}	

		// Forward control to the edit user registration page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page: "
				+ mapping.findForward("success").getPath());

		return findSuccess(mapping);
	}


	public void outputQuoprodHeaderToPDF() 
	throws DocumentException, BadElementException
	{

		try {  				

			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);

			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_2.addCell(new Phrase("PRODUCTS",fontBold));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_3 = {0.3f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase("Product",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Volume",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Weight",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Comprt",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Ldg Temp",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Dch Temp",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Lqd Temp",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Srf Temp",fontSmallBold));		

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			_PdfPCell = new PdfPCell();
			_PdfPCell.setBorder(CELL_BORDER);
			_PdfPCell.setBackgroundColor(black);
			_PdfPCell.setFixedHeight(1);
			_Table1.addCell(_PdfPCell);	

		}
		catch (Exception e) {}			
	}			


	public void outputQuoprodToPDF(QuoprodDTO quoprod, int row) 
	throws DocumentException, BadElementException
	{

		try {  				

			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.3f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(
					new Phrase(
							new ProductBD(this.getSessionFactoryClusterMap()).read(quoprod.getProductkey()).getLdesc(),
							fontSmall							
					)
			);		

			String tempunit = "C";
			if (quoprod.getTempunit().equals("F")) tempunit = "F";			
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getVolume()+" "+quoprod.getVolunit(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getWeight()+" "+quoprod.getWghtunit(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getComprt(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getLdgtemp()+" "+tempunit,fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getDchtemp()+" "+tempunit,fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getLqdtemp()+" "+tempunit,fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(
						quoprod.getSurftemp()+" "+tempunit,fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);				 	   	
			_Table1.addCell(_PdfPCell);

		}
		catch (Exception e) {}			
	}		


	public void outputQuohdrToPDF(Quohdr quohdr) 
	throws DocumentException, BadElementException
	{

		try {  	


			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// title label
			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER);    				    			
			_Table1_2.addCell(new Phrase("Quotation Overview",fontHeading));
			_Table1_2.addCell(new Phrase(" ",font));			
			_Table1_2.addCell(new Phrase("Date: "+Util.dateTextFormat2(new Date()),fontBold));
			_Table1_2.addCell(new Phrase(" ",font));
			_Table1_2.addCell(new Phrase(" ",font));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);


			float[] widths1_7 = {0.25f, 0.75f}; 
			PdfPTable _Table1_7 = new PdfPTable(widths1_7);
			_Table1_7.getDefaultCell().setBorder(CELL_BORDER);  	   	  		
			// insert blank row
			_BlankCell.setColspan(2);
			_Table1_7.addCell(_BlankCell);
			_Table1_7.addCell(new Phrase("Quote No:",fontBold));
			_Table1_7.addCell(new Phrase(quohdr.getQuotno(),font));
			_PdfPCell = new PdfPCell(_Table1_7);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);


			float[] widths1_3 = {0.25f, 0.25f, 0.25f, 0.25f}; 

			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER);  	   	  		

			// insert blank row
			_BlankCell.setColspan(4);
			_Table1_3.addCell(_BlankCell);

			_Table1_3.addCell(new Phrase("Quote Date:",fontBold));
			_Table1_3.addCell(new Phrase(Util.dateTextFormat2(quohdr.getQuotedate()),font));				
			_Table1_3.addCell(new Phrase("Created by User:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getQuotebyuserid(),font));				

			_Table1_3.addCell(new Phrase("Effective Date:",fontBold));
			_Table1_3.addCell(new Phrase(Util.dateTextFormat2(quohdr.getEffectivedate()),font));					
			_Table1_3.addCell(new Phrase("Company:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getCompanykey(),font));	

			_Table1_3.addCell(new Phrase("Expiry Date:",fontBold));
			_Table1_3.addCell(new Phrase(Util.dateTextFormat2(quohdr.getExpirydate()),font));				
			_Table1_3.addCell(new Phrase("Department:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getDepartmentkey(),font));

			_Table1_3.addCell(new Phrase("Ship Method:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getShipmethod(),font));
			_Table1_3.addCell(new Phrase("Role:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getActivitykey(),font));



			// insert blank row
			_BlankCell.setColspan(4);
			_Table1_3.addCell(_BlankCell);

			_Table1_3.addCell(new Phrase("Quote Status:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getQuotestatus(),font));				
			_Table1_3.addCell(new Phrase("Reject Reason:",fontBold));
			_Table1_3.addCell(new Phrase(quohdr.getRejectreason(),font));

			// insert blank row
			_BlankCell.setColspan(4);
			_Table1_3.addCell(_BlankCell);

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_4 = {0.25f, 0.45f, 0.15f, 0.15f}; 

			PdfPTable _Table1_4 = new PdfPTable(widths1_4);
			_Table1_4.getDefaultCell().setBorder(CELL_BORDER);  	   	  		


			_Table1_4.addCell(new Phrase("Customer:",fontBold));
			Address address = null;
			_Table1_4.addCell(new Phrase(quohdr.getCustomername(),font));
			_Table1_4.addCell(new Phrase("Reference:",fontBold));
			_Table1_4.addCell(new Phrase(quohdr.getCustomerref(),font));

			_Table1_4.addCell(new Phrase("Shipper:",fontBold));
			address = null;
			try {address = new AddressBD(this.getSessionFactoryClusterMap()).read(quohdr.getShipperaddrkey());}catch(ApplicationException ae){}
			if (address != null) _Table1_4.addCell(new Phrase(address.getName(),font));
			else _Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("Reference:",fontBold));
			_Table1_4.addCell(new Phrase(quohdr.getShipperref(),font));

			_Table1_4.addCell(new Phrase("Consignee:",fontBold));
			address = null;
			try {address = new AddressBD(this.getSessionFactoryClusterMap()).read(quohdr.getConsigneeaddrkey());}catch(ApplicationException ae){}
			if (address != null) _Table1_4.addCell(new Phrase(address.getName(),font));
			else _Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("Reference:",fontBold));
			_Table1_4.addCell(new Phrase(quohdr.getConsigneeref(),font));

			_Table1_4.addCell(new Phrase("Forward Agent:",fontBold));
			address = null;
			try {address = new AddressBD(this.getSessionFactoryClusterMap()).read(quohdr.getFwdagentaddrkey());}catch(ApplicationException ae){}
			if (address != null) _Table1_4.addCell(new Phrase(address.getName(),font));
			else _Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("Reference:",fontBold));
			_Table1_4.addCell(new Phrase(quohdr.getFwdagentref(),font));

			_Table1_4.addCell(new Phrase("Broker:",fontBold));
			address = null;
			try {address = new AddressBD(this.getSessionFactoryClusterMap()).read(quohdr.getBrokeraddrkey());}catch(ApplicationException ae){}
			if (address != null) _Table1_4.addCell(new Phrase(address.getName(),font));
			else _Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("Reference:",fontBold));
			_Table1_4.addCell(new Phrase(quohdr.getBrokerref(),font));

			// insert blank row
			_BlankCell.setColspan(4);
			_Table1_4.addCell(_BlankCell);

			_Table1_4.addCell(new Phrase("Currency:",fontBold));
			_Table1_4.addCell(new Phrase(quohdr.getCcykey(),font));
			_Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("",font));

			// insert blank row
			_BlankCell.setColspan(4);
			_Table1_4.addCell(_BlankCell);

			_Table1_4.addCell(new Phrase("Product:",fontBold));
			Product product = null;
			try {product = new ProductBD(this.getSessionFactoryClusterMap()).read(quohdr.getProductkey());}catch(ApplicationException ae){}
			if (product != null) _Table1_4.addCell(new Phrase(product.getLdesc(),font));
			else _Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("",font));
			_Table1_4.addCell(new Phrase("",font));

			// insert blank row
			_BlankCell.setColspan(4);
			_Table1_4.addCell(_BlankCell);

			_PdfPCell = new PdfPCell(_Table1_4);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_5 = {0.25f, 0.75f}; 		
			PdfPTable _Table1_5 = new PdfPTable(widths1_5);
			_Table1_5.getDefaultCell().setBorder(CELL_BORDER); 

			_Table1_5.addCell(new Phrase("Loading Plant:",fontBold));
			Location location = null;
			try {location = new LocationBD(this.getSessionFactoryClusterMap()).read(quohdr.getLdglocationkey1());}catch(ApplicationException ae){}
			if (location != null) _Table1_5.addCell(new Phrase(location.getLocationName(),font));
			else _Table1_5.addCell(new Phrase("",font));

			_Table1_5.addCell(new Phrase("Loading Port:",fontBold));
			location = null;
			try {location = new LocationBD(this.getSessionFactoryClusterMap()).read(quohdr.getLdglocationkey2());}catch(ApplicationException ae){}
			if (location != null) _Table1_5.addCell(new Phrase(location.getLocationName(),font));
			else _Table1_5.addCell(new Phrase("",font));

			_Table1_5.addCell(new Phrase("Discharge Port:",fontBold));
			location = null;
			try {location = new LocationBD(this.getSessionFactoryClusterMap()).read(quohdr.getDchlocationkey2());}catch(ApplicationException ae){}
			if (location != null) _Table1_5.addCell(new Phrase(location.getLocationName(),font));
			else _Table1_5.addCell(new Phrase("",font));

			_Table1_5.addCell(new Phrase("Discharge Plant:",fontBold));
			location = null;
			try {location = new LocationBD(this.getSessionFactoryClusterMap()).read(quohdr.getDchlocationkey1());}catch(ApplicationException ae){}
			if (location != null) _Table1_5.addCell(new Phrase(location.getLocationName(),font));
			else _Table1_5.addCell(new Phrase("",font));

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1_5.addCell(_BlankCell);

			_PdfPCell = new PdfPCell(_Table1_5);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_6 = {0.25f, 0.4f, 0.35f}; 		
			PdfPTable _Table1_6 = new PdfPTable(widths1_6);
			_Table1_6.getDefaultCell().setBorder(0);

			_Table1_6.addCell(new Phrase("Demurrage",fontBold));
			_Table1_6.addCell(new Phrase("",font));
			_Table1_6.addCell(new Phrase("",font));

			_Table1_6.addCell(new Phrase("Location:",fontBold));
			location = null;
			try {location = new LocationBD(this.getSessionFactoryClusterMap()).read(quohdr.getDmrglocationkey());}catch(ApplicationException ae){}
			if (location != null) _Table1_6.addCell(new Phrase(location.getLocationName(),font));
			else _Table1_6.addCell(new Phrase("",font));
			_Table1_6.addCell(new Phrase("",font));


			_Table1_6.addCell(new Phrase("Free days:",fontBold));
			try {_Table1_6.addCell(new Phrase(quohdr.getDmrgfreedays().toString(),font));} catch (Exception e) {_Table1_6.addCell(new Phrase("",font));}
			_Table1_6.addCell(new Phrase("",font));


			//sITT-201001-0001
			_Table1_6.addCell(new Phrase("Currency:",fontBold));
			try {_Table1_6.addCell(new Phrase(quohdr.getDmrgccykey(),font));} catch (Exception e) {_Table1_6.addCell(new Phrase("",font));}
			_Table1_6.addCell(new Phrase("",font));
			//eITT-201001-0001


			_Table1_6.addCell(new Phrase("Rates:",fontBold));


			float[] widths1_6_1 = {0.33f, 0.33f, 0.33f}; 			
			PdfPTable _Table1_6_1 = new PdfPTable(widths1_6_1);			
			_Table1_6_1.getDefaultCell().setBorder(0);			


			float[] _widths1_6_1_1 = {0.5f, 0.5f}; 			
			PdfPTable _Table1_6_1_1 = new PdfPTable(_widths1_6_1_1);			
			_Table1_6_1_1.getDefaultCell().setBorder(1);
			_PdfPCell = new PdfPCell(new Phrase("Period1",fontSmallBold));
			_PdfPCell.setColspan(2);
			_Table1_6_1_1.addCell(_PdfPCell);			
			_Table1_6_1_1.addCell(new Phrase("Days",fontSmall));			
			_Table1_6_1_1.addCell(new Phrase("Rate",fontSmall));
			try {_Table1_6_1_1.addCell(new Phrase(quohdr.getDmrgdays1().toString(),fontSmall));} 
			catch (Exception e) {_Table1_6_1_1.addCell(new Phrase("",fontSmall));}			
			try {_Table1_6_1_1.addCell(new Phrase(quohdr.getDmrgdlyrate1().toString(),fontSmall));} 
			catch (Exception e) {_Table1_6_1_1.addCell(new Phrase("",fontSmall));}				
			_Table1_6_1.addCell(new PdfPCell(_Table1_6_1_1));					

			float[] _widths1_6_1_2 = {0.5f, 0.5f}; 			
			PdfPTable _Table1_6_1_2 = new PdfPTable(_widths1_6_1_2);			
			_Table1_6_1_2.getDefaultCell().setBorder(1);
			_PdfPCell = new PdfPCell(new Phrase("Period2",fontSmallBold));
			_PdfPCell.setColspan(2);
			_Table1_6_1_2.addCell(_PdfPCell);			
			_Table1_6_1_2.addCell(new Phrase("Days",fontSmall));			
			_Table1_6_1_2.addCell(new Phrase("Rate",fontSmall));
			try {_Table1_6_1_2.addCell(new Phrase(quohdr.getDmrgdays2().toString(),fontSmall));} 
			catch (Exception e) {_Table1_6_1_2.addCell(new Phrase("",fontSmall));}			
			try {_Table1_6_1_2.addCell(new Phrase(quohdr.getDmrgdlyrate2().toString(),fontSmall));} 
			catch (Exception e) {_Table1_6_1_2.addCell(new Phrase("",fontSmall));}				
			_Table1_6_1.addCell(new PdfPCell(_Table1_6_1_2));			

			float[] _widths1_6_1_3 = {0.5f, 0.5f}; 			
			PdfPTable _Table1_6_1_3 = new PdfPTable(_widths1_6_1_3);			
			_Table1_6_1_3.getDefaultCell().setBorder(1);
			_PdfPCell = new PdfPCell(new Phrase("Period3",fontSmallBold));
			_PdfPCell.setColspan(2);
			_Table1_6_1_3.addCell(_PdfPCell);			
			_Table1_6_1_3.addCell(new Phrase("Rate",fontSmall));			
			_Table1_6_1_3.addCell(new Phrase("",fontSmall));		
			try {_Table1_6_1_3.addCell(new Phrase(quohdr.getDmrgdlyrate3().toString(),fontSmall));} 
			catch (Exception e) {_Table1_6_1_3.addCell(new Phrase("",fontSmall));}
			_Table1_6_1_3.addCell(new Phrase("",fontSmall));
			_Table1_6_1.addCell(new PdfPCell(_Table1_6_1_3));			

			_PdfPCell = new PdfPCell(_Table1_6_1);
			_PdfPCell.setColspan(1);
			_PdfPCell.setBorder(1);				
			_Table1_6.addCell(_PdfPCell);			
			_Table1_6.addCell(new Phrase("",font));
			//_Table1_6.addCell(new Phrase("",font));			

			_PdfPCell = new PdfPCell(_Table1_6);
			_PdfPCell.setColspan(1);			
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);



			// insert blank rows
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);
			_Table1.addCell(_BlankCell);			


		}
		catch (Exception e) {}		
	}	


	public void outputQuochargeHeaderToPDF() 
	throws DocumentException, BadElementException
	{

		try {  				

			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);

			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_2.addCell(new Phrase("CHARGES",fontBold));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_3 = {0.3f, 0.1f, 0.1f, 0.05f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase("Customer",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Charge",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Type",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Ccy",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Inv",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Units",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Unit Name",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Unit Rate ",fontSmallBold));
			_Table1_3.addCell(new Phrase("Chg Amt",fontSmallBold));			

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			_PdfPCell = new PdfPCell();
			_PdfPCell.setBorder(CELL_BORDER);
			_PdfPCell.setBackgroundColor(black);
			_PdfPCell.setFixedHeight(1);
			_Table1.addCell(_PdfPCell);		

		}
		catch (Exception e) {}			
	}			


	public void outputQuochargeToPDF(QuochargeDTO quocharge, int row) 
	throws DocumentException, BadElementException
	{

		try {  				

			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.3f, 0.1f, 0.1f, 0.05f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f};
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER);

			try {
				_Table1_3.addCell(
						new Phrase(
								quocharge.getCustomeraddrkey().getName(),
								fontSmall
						)
				);		
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}	

			try {
				_Table1_3.addCell(new Phrase(quocharge.getChargekey().getChargekey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocharge.getChargetype(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocharge.getCcykey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}	
			try {
				_Table1_3.addCell(new Phrase(quocharge.getInvoiceccykey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocharge.getUnits().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocharge.getUname(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocharge.getRate().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocharge.getChgamt().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);				 	   	
			_Table1.addCell(_PdfPCell);



			//s200900051
			float[] widths1_4 = {0.01f, 0.99f};
			PdfPTable _Table1_4 = new PdfPTable(widths1_4);
			_Table1_4.getDefaultCell().setBorder(CELL_BORDER);	
			_Table1_4.addCell(new Phrase(" ",fontSmall));//space
			try {_Table1_4.addCell(new Phrase("Notes: "+quocharge.getTxt1(),fontSmall));} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			_PdfPCell = new PdfPCell(_Table1_4);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);				 	   	
			_Table1.addCell(_PdfPCell);	
			//e200900051


		}
		catch (Exception e) {}			
	}		



	public void outputQuomovHeaderToPDF() 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);

			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_2.addCell(new Phrase("MOVEMENTS",fontBold));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);


			float[] widths1_3 = {0.1f, 0.2f, 0.2f, 0.3f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase("Section",fontSmallBold));		
			_Table1_3.addCell(new Phrase("From Location",fontSmallBold));		
			_Table1_3.addCell(new Phrase("To Location",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Vendor",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Fr Days",fontSmallBold));		
			_Table1_3.addCell(new Phrase("To Days",fontSmallBold));				

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);


			_PdfPCell = new PdfPCell();
			_PdfPCell.setBorder(CELL_BORDER);
			_PdfPCell.setBackgroundColor(black);
			_PdfPCell.setFixedHeight(1);
			_Table1.addCell(_PdfPCell);	

		}
		catch (Exception e) {}			
	}			


	public void outputQuomovHeaderToPDF2() 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);


			float[] widths1_3 = {0.1f, 0.2f, 0.2f, 0.3f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase("Section",fontSmallBold));		
			_Table1_3.addCell(new Phrase("From Location",fontSmallBold));		
			_Table1_3.addCell(new Phrase("To Location",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Vendor",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Fr Days",fontSmallBold));		
			_Table1_3.addCell(new Phrase("To Days",fontSmallBold));				

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

		}
		catch (Exception e) {}			
	}	


	public void outputQuomovToPDF(QuomovDTO quomov, int row) 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.1f, 0.2f, 0.2f, 0.3f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 

			try {
				_Table1_3.addCell(
						new Phrase(
								quomov.getSectionkey().getSectionkey(),
								fontSmall
						)
				);
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(
						new Phrase(
								quomov.getFromlocationkey().getLocationName(),
								fontSmall
						)
				);
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(
						new Phrase(
								quomov.getTolocationkey().getLocationName(),
								fontSmall
						)
				);
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}	
			try {
				_Table1_3.addCell(
						new Phrase(
								quomov.getVendoraddrkey().getName(),
								fontSmall
						)
				);
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quomov.getFromdayno().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quomov.getTodayno().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);			 	   	
			_Table1.addCell(_PdfPCell);

		}
		catch (Exception e) {}			
	}


	public void outputQuocostHeaderToPDF() 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			//_BlankCell.setColspan(2);
			//_Table1.addCell(_BlankCell);


			float[] widths1_3 = {0.05f, 0.3f, 0.1f, 0.1f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase(" ",fontSmallBold));			
			_Table1_3.addCell(new Phrase("Vendor",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Cost",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Type",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Ccy",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Units",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Unit Name",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Unit Rate ",fontSmallBold));
			_Table1_3.addCell(new Phrase("Cost Amt",fontSmallBold));			

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

		}
		catch (Exception e) {}			
	}


	public void outputQuocostHeaderToPDF2() 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);

			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_2.addCell(new Phrase("COSTS",fontBold));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_3 = {0.3f, 0.1f, 0.1f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 			
			_Table1_3.addCell(new Phrase("Vendor",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Cost",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Type",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Ccy",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Units",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Unit Name",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Unit Rate ",fontSmallBold));
			_Table1_3.addCell(new Phrase("Cost Amt",fontSmallBold));			

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			_PdfPCell = new PdfPCell();
			_PdfPCell.setBorder(CELL_BORDER);
			_PdfPCell.setBackgroundColor(black);
			_PdfPCell.setFixedHeight(1);
			_Table1.addCell(_PdfPCell);	

		}
		catch (Exception e) {}			
	}			


	public void outputQuocostToPDF(QuocostDTO quocost, int row) 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.05f, 0.3f, 0.1f, 0.1f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f};
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 

			_Table1_3.addCell(new Phrase(" ",fontSmallBold));				
			_Table1_3.addCell(
					new Phrase(
							quocost.getVendoraddrkey().getName(),
							fontSmall
					)
			);		

			try {
				_Table1_3.addCell(new Phrase(quocost.getCostkey().getCostkey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocost.getCosttype(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocost.getCcykey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}			
			try {
				_Table1_3.addCell(new Phrase(quocost.getUnits().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocost.getUnitname(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocost.getRate().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocost.getCstamt().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);			 	   	
			_Table1.addCell(_PdfPCell);



			//s200900051
			float[] widths1_4 = {0.06f, 0.94f};
			PdfPTable _Table1_4 = new PdfPTable(widths1_4);
			_Table1_4.getDefaultCell().setBorder(CELL_BORDER);	
			_Table1_4.addCell(new Phrase(" ",fontSmall));//space
			try {_Table1_4.addCell(new Phrase("Notes: "+quocost.getTxt(),fontSmall));} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			_PdfPCell = new PdfPCell(_Table1_4);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);				 	   	
			_Table1.addCell(_PdfPCell);	
			//e200900051



		}
		catch (Exception e) {}			
	}		


	public void outputQuocostToPDF2(QuocostDTO quocost, int row) 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.3f, 0.1f, 0.1f, 0.05f, 0.1f, 0.1f, 0.1f, 0.1f};
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 

			try {
				_Table1_3.addCell(
						new Phrase(
								quocost.getVendoraddrkey().getName(),
								fontSmall
						)
				);
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocost.getCostkey().getCostkey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocost.getCosttype(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quocost.getCcykey(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}			
			try {
				_Table1_3.addCell(new Phrase(quocost.getUnits().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocost.getUnitname(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocost.getRate().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quocost.getCstamt().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);				
			_Table1.addCell(_PdfPCell);



			//s200900051
			float[] widths1_4 = {0.01f, 0.99f};
			PdfPTable _Table1_4 = new PdfPTable(widths1_4);
			_Table1_4.getDefaultCell().setBorder(CELL_BORDER);	
			_Table1_4.addCell(new Phrase(" ",fontSmall));//space
			try {_Table1_4.addCell(new Phrase("Notes: "+quocost.getTxt(),fontSmall));} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			_PdfPCell = new PdfPCell(_Table1_4);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);				 	   	
			_Table1.addCell(_PdfPCell);	
			//e200900051



		}
		catch (Exception e) {}			
	}






	//sITT-201103-0001	
	public void outputQuonotecustHeaderToPDF() 
	throws DocumentException, BadElementException
	{

		try {  				


			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);

			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_2.addCell(new Phrase("CUSTOMER NOTES",fontBold));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_3 = {0.1f, 0.08f, 0.10f, 0.05f, 0.15f, 0.57f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase("Date",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Time",fontSmallBold));		
			_Table1_3.addCell(new Phrase("User",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Pty",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Category",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Notes",fontSmallBold));				

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			_PdfPCell = new PdfPCell();
			_PdfPCell.setBorder(CELL_BORDER);
			_PdfPCell.setBackgroundColor(black);
			_PdfPCell.setFixedHeight(1);
			_Table1.addCell(_PdfPCell);	

		}
		catch (Exception e) {}			
	}			
	//eITT-201103-0001

	//sITT-201103-0001
	public void outputQuonotecustToPDF(Quonotecust quonotecust, int row) 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.1f, 0.08f, 0.10f, 0.05f, 0.15f, 0.57f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 

			try {
				_Table1_3.addCell(new Phrase(Util.dateTextFormat2(quonotecust.getCreatedate()),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quonotecust.getCreatetime(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quonotecust.getCreateuserid(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}	
			try {
				_Table1_3.addCell(new Phrase(quonotecust.getPriority(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quonotecust.getCategory().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quonotecust.getNote1(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);			 	   	
			_Table1.addCell(_PdfPCell);

		}
		catch (Exception e) {}			
	}		
	//eITT-201103-0001



	public void outputQuonoteHeaderToPDF() 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(2);
			_Table1.addCell(_BlankCell);

			float[] widths1_2 = {0.95f, 0.05f}; 
			PdfPTable _Table1_2 = new PdfPTable(widths1_2);
			_Table1_2.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_2.addCell(new Phrase("NOTES",fontBold));
			_Table1_2.addCell(new Phrase(" ",font));

			_PdfPCell = new PdfPCell(_Table1_2);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			float[] widths1_3 = {0.1f, 0.08f, 0.10f, 0.05f, 0.15f, 0.57f};  
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 
			_Table1_3.addCell(new Phrase("Date",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Time",fontSmallBold));		
			_Table1_3.addCell(new Phrase("User",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Pty",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Category",fontSmallBold));		
			_Table1_3.addCell(new Phrase("Notes",fontSmallBold));				

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER);			 	   	
			_Table1.addCell(_PdfPCell);

			_PdfPCell = new PdfPCell();
			_PdfPCell.setBorder(CELL_BORDER);
			_PdfPCell.setBackgroundColor(black);
			_PdfPCell.setFixedHeight(1);
			_Table1.addCell(_PdfPCell);	

		}
		catch (Exception e) {}			
	}			


	public void outputQuonoteToPDF(Quonote quonote, int row) 
	throws DocumentException, BadElementException
	{

		try {  				



			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			float[] widths1_3 = {0.1f, 0.08f, 0.10f, 0.05f, 0.15f, 0.57f}; 
			PdfPTable _Table1_3 = new PdfPTable(widths1_3);
			_Table1_3.getDefaultCell().setBorder(CELL_BORDER); 

			try {
				_Table1_3.addCell(new Phrase(Util.dateTextFormat2(quonote.getCreatedate()),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quonote.getCreatetime(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}
			try {
				_Table1_3.addCell(new Phrase(quonote.getCreateuserid(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}	
			try {
				_Table1_3.addCell(new Phrase(quonote.getPriority(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quonote.getCategory().toString(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		
			try {
				_Table1_3.addCell(new Phrase(quonote.getNote1(),fontSmall));
			} catch (Exception e) {_Table1_3.addCell(new Phrase("",font));}		

			_PdfPCell = new PdfPCell(_Table1_3);
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setBackgroundColor(lghtgry);
			if ((row % 2) == 0) _PdfPCell.setBackgroundColor(lghtgry2);			 	   	
			_Table1.addCell(_PdfPCell);

		}
		catch (Exception e) {}			
	}		



}