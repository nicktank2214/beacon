package com.bureaueye.beacon.action.quotation.pdf;


import java.sql.SQLException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class QuosummaryEndPage extends PdfPageEventHelper {


	Font font;
	Font fontBold;	
	Font fontBoldLarge;
	Font fontSmall;
	Font fontSmallBold;	


	int DEFAULT_CELL_BORDER = 0;
	int CELL_BORDER = 0;

	public QuosummaryEndPage() {
		try {
			//init default fonts for document type
			BaseFont baseFont = BaseFont.createFont(com.bureaueye.beacon.bean.Constants.FONT_NAME, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			font=new Font(
					//Font.FontFamily.COURIER,
					baseFont,
					com.bureaueye.beacon.bean.Constants.FONT_SIZE,
					com.bureaueye.beacon.bean.Constants.FONT_STYLE
			);
			fontBold = new Font(font.getFamily(), font.getSize(), Font.BOLD);	
			fontBoldLarge = new Font(font.getFamily(), font.getSize()+2, fontBold.getStyle());
			fontSmall = new Font(font.getFamily(), font.getSize()-3, font.getStyle());			
			fontSmallBold = new Font(fontSmall.getFamily(), fontSmall.getSize(), Font.BOLD);
		} catch (Exception e) {
		}
	}



	public void onEndPage(PdfWriter writer, Document document) {
		try {
			Rectangle page = document.getPageSize();
			PdfPTable head = doHeader();
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(
					0, 
					-1, 
					document.leftMargin(), 
					page.getHeight() - document.topMargin() + head.getTotalHeight(),						
					writer.getDirectContent()
			);
			PdfPTable foot = doFooter();
			foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			foot.writeSelectedRows(
					0, 							// rowstart
					-1, 						// rowend
					document.leftMargin(), 		// xPos
					document.bottomMargin(),	// yPos
					writer.getDirectContent()
			);
		}
		catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}

	public PdfPTable doHeader() throws DocumentException, BadElementException, SQLException, Exception
	{

		PdfPTable _Table1 = new PdfPTable(1);

		try { 

			// create main table and add it to the document
			_Table1.getDefaultCell().setBorder(0);
			_Table1.getDefaultCell().setNoWrap(false);            		
			_Table1.setWidthPercentage(100f);  
			_Table1.getDefaultCell().setPadding(0);

			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);


			// title
			PdfPTable _Table1_1 = new PdfPTable(1);
			_Table1_1.getDefaultCell().setBorder(CELL_BORDER);    		

			if (true) {
				// logo image process
				//jpeg = Image.getInstance(_Tblreportheaders.getLogo());
				//		jpeg.scalePercent(25);
				//_PdfPCell = new PdfPCell(new Phrase(_Tblreportheaders.getCompanyName(),fontLarge));
				//_PdfPCell = new PdfPCell(jpeg);
				_PdfPCell = new PdfPCell();
				_PdfPCell.setBorder(CELL_BORDER); 
				_PdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				_Table1_1.addCell(_PdfPCell);

				// contact details
				//_PdfPCell = new PdfPCell(new Phrase(_Tblreportheaders.getPhone()+" "+ _Tblreportheaders.getFax(),font));
				_PdfPCell = new PdfPCell();
				_PdfPCell.setBorder(CELL_BORDER); 
				_PdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);     			
				_Table1_1.addCell(_PdfPCell);
				// address details
				//_PdfPCell = new PdfPCell(new Phrase(_Tblreportheaders.getAddress()+" "+_Tblreportheaders.getCity()+" "+_Tblreportheaders.getState()+" "+_Tblreportheaders.getCountry(),font));
				_PdfPCell = new PdfPCell();
				_PdfPCell.setBorder(CELL_BORDER); 
				_PdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);     			
				_Table1_1.addCell(_PdfPCell);				
			}
			else {
				_BlankCell.setColspan(1);
				_Table1_1.addCell(_BlankCell);
				_Table1_1.addCell(_BlankCell);
				_Table1_1.addCell(_BlankCell);   			
			}
			_PdfPCell = new PdfPCell(_Table1_1);
			_PdfPCell.setFixedHeight(100f);
			_PdfPCell.setBorder(CELL_BORDER);			
			_Table1.addCell(_PdfPCell);


			// insert blank row
			_BlankCell.setColspan(1);
			_Table1.addCell(_BlankCell);  		


		}
		catch (Exception e) {}

		return _Table1;
	}


	public PdfPTable doFooter() throws DocumentException, BadElementException, SQLException, Exception
	{

		PdfPTable _Table1 = new PdfPTable(1);

		try { 

			// working cell
			PdfPCell _PdfPCell;

			// define blank cell
			PdfPCell _BlankCell =  new PdfPCell(new Phrase("",font));
			_BlankCell.setColspan(2);
			_BlankCell.setFixedHeight(12f);
			_BlankCell.setBorder(0);

			// insert blank row
			_BlankCell.setColspan(1);
			_Table1.addCell(_BlankCell);  		  		

			// insert blank row
			_BlankCell.setColspan(1);
			_Table1.addCell(_BlankCell);

			// 
			float[] widths = {0.1f, 0.8f, 0.1f};
			PdfPTable _Table1_1 = new PdfPTable(widths);
			_Table1_1.getDefaultCell().setBorder(CELL_BORDER);    		

			_Table1_1.addCell(new Phrase("",font));
			_PdfPCell = new PdfPCell(new Phrase("",font));
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			_Table1_1.addCell(_PdfPCell);   			
			_PdfPCell = new PdfPCell(new Phrase("",font)); 
			_PdfPCell.setBorder(CELL_BORDER); 
			_PdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			_Table1_1.addCell(_PdfPCell);     		

			_PdfPCell = new PdfPCell(_Table1_1);
			_PdfPCell.setFixedHeight(20f);
			_PdfPCell.setBorder(CELL_BORDER);
			_Table1.addCell(_PdfPCell);   


		}
		catch (Exception e) {}

		return _Table1;
	}



}
