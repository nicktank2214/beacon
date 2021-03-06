package com.bureaueye.beacon.action.salesinvoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.hibernate.Hibernate;

import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.bean.Constants;
import com.bureaueye.beacon.bean.LabelValue;
import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.form.ListForm;

import com.bureaueye.beacon.model.order.Orderfile;
import com.bureaueye.beacon.model.order.bd.OrderfileBD;
import com.bureaueye.beacon.model.salesinvoice.Sihdr;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.beacon.model.system.Systemcode;
import com.bureaueye.beacon.model.system.bd.SystemcodeBD;
import com.bureaueye.beacondms.model.dms.Xdocument;
import com.bureaueye.beacondms.model.dms.bd.XdocumentBD;



/**
 * 
 * Amendments
 * ----------
 * 
 * NT	2013-12-11		WSI-201311-0002		BEACON LAKER (WSI LINER) - Sales Invoice Template  
 * 
 */
public final class EmailSalesinvoiceAction extends BaseAction {


	SystemcodeBD systemcodebd;
	

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		// If the session has timed-out then return to the login page
		HttpSession session = request.getSession(false);
		ListForm saveForm = (ListForm) form;
		String action = saveForm.getAction();
		User user = (User) session.getAttribute(Constants.USER_KEY);
		ActionMessages messages = new ActionMessages();
		ActionMessages errors = new ActionMessages();
		ActionMessages appErrorMessages = new ActionMessages();
		ActionMessages appInformationMessages = new ActionMessages();


		// If the session has timed-out then take the user back to the login page
		if (action == null || session == null || user == null) {
			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Session has timed out");
			return mapping.findForward("welcome");
		}

		// If the cancel button is selected then go back to the list
		if (isCancelled(request)) {
			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Cancel transaction");
			return findSuccess(mapping);
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Processing " + action + " action");



		// Report any messages back to the original form
		if (!messages.isEmpty()) {
			this.saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		// Report any errors back to the original form
		if (!errors.isEmpty()) {
			this.saveErrors(request, messages);
			return (mapping.getInputForward());
		}	





		// Generate Business Delegate
		OrderfileBD orderfilebd = new OrderfileBD(this.getSessionFactoryClusterMap());
		XdocumentBD xdocumentbd = new XdocumentBD(this.getSessionFactoryClusterMap());
		systemcodebd= new SystemcodeBD(this.getSessionFactoryClusterMap());

		
		Systemcode systemcode = null; 
		String workDirectory = "";
		try {
			systemcode = systemcodebd.findSystemcodesByTypekeyCodekey(
					"BEACONDMS", 
					"WORKDIRECTORY"
			);
			workDirectory = systemcode.getDescr();
		} catch (ApplicationException e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" WORKDIRECTORY:  findSystemcodesByTypekeyCodekey: ApplicationException: "+e.getMessage());				
		} catch (Exception e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" WORKDIRECTORY:  findSystemcodesByTypekeyCodekey: Exception: "+e.getMessage());							
		}

		int maximumAttachments = 0;
		try {
			systemcode = systemcodebd.findSystemcodesByTypekeyCodekey(
					"BEACONDMS", 
					"MAXIMUM_ATTACHMENTS"
			);
			maximumAttachments = new Integer(systemcode.getDescr()).intValue();
		} catch (ApplicationException e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" MAXIMUM_ATTACHMENTS: findSystemcodesByTypekeyCodekey: ApplicationException: "+e.getMessage());				
		} catch (Exception e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" MAXIMUM_ATTACHMENTS: findSystemcodesByTypekeyCodekey: Exception: "+e.getMessage());							
		}
		
		
		List mailAttachmentsList = new LinkedList();

		int row=0;		
		try {


			//build attachments process
			try {

				
				// BUILD SALES INVOICE DOCUMENT ATTACHMENTS==============================>
				Iterator<Sihdr> it = saveForm.getLineItems().iterator();
				LOOP1:
					while (it.hasNext()) {
						Sihdr sihdrdao = (Sihdr) it.next();
						Integer id = sihdrdao.getSihdrId();
						
						//GENERATE DOCUMENT														
						//check document key already generated
						String key = id+"|SALES_INVOICE";	

						//find document if already generated
						Xdocument xdocumentdao = null;
						try {
							xdocumentdao = xdocumentbd.findXdocumentByDockey(key);
						} catch (Exception e) {								
						}
						//if not found generate new document
						if (xdocumentdao==null) xdocumentdao = new Xdocument();


						user.setUserid(user.getUserid());

						//store file information
						xdocumentdao.setSourcefilename("");
						xdocumentdao.setFilename("");
						xdocumentdao.setFilesize(0);
						xdocumentdao.setContenttype("");

						xdocumentdao.setDoctype("SALES_INVOICE");
						xdocumentdao.setCreateaction(Constants.GENERATE);

						//init id
						xdocumentdao.setDocid(id.toString());
						xdocumentdao.setDockey(key);

						xdocumentdao.setErrors("");
						xdocumentdao.setWarnings("");
						xdocumentdao.setCompanykey(user.getCompanykey());
						xdocumentdao.setDepartmentkey(user.getDepartmentkeylist());
						xdocumentdao.setActivitykey(user.getActvid());

						xdocumentdao.setTxt1("");
						xdocumentdao.setTxt2("");
						xdocumentdao.setCategorycode("");
						xdocumentdao.setSubcategorycode("");
						xdocumentdao.setGroupid("");
						xdocumentdao.setTransactionid("");
						xdocumentdao.setModuleid("");
						xdocumentdao.setDocument("");								
						xdocumentdao.setControlid("");	

						//store document
						xdocumentdao.setXmldocument("");	

						xdocumentbd.createOrUpdate(xdocumentdao, user);	

						//print Sales invoice===========================================>
						// build class and worker method
						Method printWorker = null;
						Class<?> printClass = null;
						try {
							printClass = Class.forName(user.getSalesinvoiceprintclass());
							Class[] argTypes = { 
									Integer.class,
									String.class, 
									com.bureaueye.beacon.model.standard.User.class,
									java.util.Map.class
							};    		

							String methodName = "executePrint";
							// initialise work method depending on process
							printWorker = printClass.getMethod(
									methodName,
									argTypes
							);	

							// initialise actual data to send
							Object[] theData = { 
									id, 
									"Print",
									user,
									this.getSessionFactoryClusterMap()
							};
							// invoke worker method
							printWorker.invoke(printClass.newInstance(), theData);
						} catch (Exception x) {	
							log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
									" Print Sales Invoice Exception: " + x.getMessage());
						}
						//print Sales invoice===========================================<


						//generate print file
						String printFilename = "";															
						printFilename = workDirectory+"\\SALES_INVOICE"+id+".pdf";								
						File printFile = new File(printFilename);							


						log.info("["+this.getClass().getName()+"] "+new Date()+
								" print file: "+printFilename);



						//store print file	
						try {

							FileInputStream fin = new FileInputStream(printFile);
							byte fileContent[] = new byte[(int)printFile.length()];
							fin.read(fileContent);
							fin.close();

							//upload file information
							xdocumentdao.setFilebin( Hibernate.createBlob (fileContent) );
							//store file information
							xdocumentdao.setFilename(printFile.getName());
							xdocumentdao.setFilesize((int)printFile.length());
							xdocumentdao.setContenttype("application/pdf");
							xdocumentdao.setLanguageid("en");
							xdocumentdao.setTestindicator("");

							//store desctiption and category
							xdocumentdao.setDescription("SALES INVOICE");
							xdocumentdao.setCategorycode("FINANCE");
							xdocumentdao.setSubcategorycode("SALES_INVOICE");

							//set receive details
							xdocumentdao.setReceivedate(xdocumentdao.getCreatedate());
							xdocumentdao.setReceivetime(xdocumentdao.getCreatetime());

							xdocumentdao.setXmlheader("");



							xdocumentbd.createOrUpdate(xdocumentdao, user);


						} catch(Exception ae){
							log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
									" Store: Exception: "+ae.getMessage()
							);
							throw new Exception();									
						}
						
						
						//attach document to email
						mailAttachmentsList.add(new LabelValue("SALES_INVOICE_"+sihdrdao.getInvoiceno()+".pdf",printFilename));
						

						row++;
					}//end for loop					
				// BUILD SALES INVOICE DOCUMENT ATTACHMENTS==============================<
				
				
				// BUILD STORED DOCUMENT ATTACHMENTS=====================================>
				int[] selectedObjects = saveForm.getSelectedObjects();
				// process selected
				if (selectedObjects != null) {
					LOOP2:
						for (int i = 0; i < selectedObjects.length; i++) {	

							//maximum number of attachments allowed
							if (row>maximumAttachments) {
								row=maximumAttachments;
								break LOOP2;
							}

							Integer id = new Integer(selectedObjects[i]);			
							// retrieve record
							Orderfile dao = orderfilebd.read(new Integer(id));

							//output attachment to work directory
							String outFilename = workDirectory+"\\"+dao.getFilename();
							//File outFile = new File(outFilename);
							FileOutputStream outStream = new FileOutputStream(outFilename);
							InputStream in = dao.getFilebin().getBinaryStream();
							byte[] buffer = new byte[32768];
							int n = 0;
							while ( ( n = in.read(buffer)) != -1) {
								outStream.write(buffer, 0, n);
							}
							in.close();
							outStream.flush();
							outStream.close();

							mailAttachmentsList.add(new LabelValue(dao.getFilename(),outFilename));

							row++;
						}//end for loop					

				}//check objects selected
				// BUILD STORED DOCUMENT ATTACHMENTS=====================================<
				






			} catch(Exception ae){
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
						" Build Attachments: Exception: "+ae.getMessage()
				);
				throw new Exception();									
			}	


			//mail process
			try {

				toMail(saveForm,mailAttachmentsList);

			} catch(Exception ae){
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
						" Email: Exception: "+ae.getMessage()
				);
				
				// Add error message 
				appErrorMessages.add(
						"messages.salesinvoicemail",
						new ActionMessage(
								"error.mail.notsent2",
								saveForm.getHeaderInfo3()
						)
				);
				saveAppErrorMessages(request, appErrorMessages);				
				throw new Exception();									
			}			



			if (row > 0) {
				// Add information message 
				appInformationMessages.add(
						"messages.trackmail",
						new ActionMessage(
								"information.trackmail.sent",
								new Integer(row).toString(),
								saveForm.getHeaderInfo3()
						)
				);
			}


		}
		catch (Exception e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" MAIN: Exception: "+e.getMessage());			
		}
		finally {
			// reset select objects
			//saveForm.reset();
			saveForm.setSelectedObjects(null);
		}


		//clear out work directory
		//loop attachments
		try {
			Iterator it2 = mailAttachmentsList.iterator();
			while (it2.hasNext()) {
				LabelValue labelValue = (LabelValue) it2.next();
				String mailattachmentValue=labelValue.getValue();
				File file = new File(mailattachmentValue);
				if (file.exists()) {
					file.delete();
				}
			}//end loop attachments
		} catch(Exception ae){
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" Clear Work Directory: Exception: "+ae.getMessage()
			);
			throw new Exception();									
		}



		// Report any errors back to the original form		
		if (!appErrorMessages.isEmpty()) {
			saveAppErrorMessages(request, appErrorMessages);
		} else {
			// Report any information back to the original form
			if (!appInformationMessages.isEmpty()) {
				saveAppInformationMessages(request, appInformationMessages);
			}					
		}



		//clear
		systemcodebd =null; 


		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to success page");
		return findSuccess(mapping);

	}




	public void toMail(
			ListForm form, List mailAttachmentsList
	) throws Exception
	{



		//system defaults
		String typekey="BEACONDMS";

		//init mail parameters
		String mailhost="";
		String mailto=form.getHeaderInfo3();
		String mailfrom=form.getHeaderInfo2();
		String mailcc=form.getHeaderInfo4();
		String mailbcc="";
		String mailsubject=form.getHeaderInfo1();
		String mailtext=form.getHeaderInfo6();

		Systemcode systemcodedao = null;

		//mail process----------------------------------------------->

		try {

			//init MAILHOST
			try {
				String codekey="MAILHOST";
				systemcodedao = systemcodebd.findSystemcodesByTypekeyCodekey(typekey,codekey);	
				mailhost=systemcodedao.getDescr();
			} catch (ApplicationException e) {
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
						"MAILHOST: findSystemcodesByTypekeyCodekey: ApplicationException: "+e.getMessage());				
			} catch (Exception e) {
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
						"MAILHOST: findSystemcodesByTypekeyCodekey: Exception: "+e.getMessage());							
			}


			log.info("["+this.getClass().getName()+"] "+new Date()+
					" mailhost["+mailhost+"]"+
					" mailto["+mailto+"]"+
					" mailfrom["+mailfrom+"]"+
					" mailcc["+mailcc+"]"+
					" mailbcc["+mailbcc+"]"+
					" mailsubject["+mailsubject+"]"+
					" mailtext["+mailtext+"]"							
			);




			//mail connection
			// set email properties
			Properties props = new Properties();
			props.put("mail.host", mailhost);			
			javax.mail.Session mailSession = javax.mail.Session.getInstance(props, null);
			javax.mail.Message message = new MimeMessage(mailSession);
			message.setSentDate(new Date());


			javax.mail.Address mailaddress = null;

			//mail from
			if (!mailfrom.equals("")) {
				try {
					mailaddress = new InternetAddress(mailfrom);
					message.setFrom(mailaddress);
				}	
				catch (AddressException ae) {
					log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
							" Email From: AddressException: "+ae.getMessage());	
					throw new Exception();	
				}
			}
			mailaddress = null;							

			//mail to
			if (!mailto.equals("")) {
				if ( mailto.indexOf(";") == 0 ) {
					try {
						mailaddress = new InternetAddress(mailto);
						message.addRecipient(Message.RecipientType.TO, mailaddress);
					}
					catch (AddressException ae) {
						log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
								" Email TO: AddressException: "+ae.getMessage());
						throw new Exception();
					}
				}
				else {
					StringTokenizer _StringTokenizer = new StringTokenizer(mailto, ";");
					while (_StringTokenizer.hasMoreTokens()) {
						try {	
							mailaddress = new InternetAddress(_StringTokenizer.nextToken());
							message.addRecipient(Message.RecipientType.TO, mailaddress);
						}
						catch (AddressException ae) {
							log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
									" Email TO: AddressException: "+ae.getMessage());
							throw new Exception();
						}						
					}
				}	
			}
			mailaddress = null;

			//mail cc
			if (!mailcc.equals("")) {
				if ( mailcc.indexOf(";") == 0 ) {
					try {
						mailaddress = new InternetAddress(mailcc);
						message.addRecipient(Message.RecipientType.CC, mailaddress);
					}
					catch (AddressException ae) {
						log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
								" Email CC: AddressException: "+ae.getMessage());
						throw new Exception();
					}
				}
				else {
					StringTokenizer _StringTokenizer = new StringTokenizer(mailcc, ";");
					while (_StringTokenizer.hasMoreTokens()) {
						try {	
							mailaddress = new InternetAddress(_StringTokenizer.nextToken());
							message.addRecipient(Message.RecipientType.CC, mailaddress);
						}
						catch (AddressException ae) {
							log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
									" Email CC: AddressException: "+ae.getMessage());
							throw new Exception();
						}						
					}
				}
			}
			mailaddress = null;

			//mail bcc
			if (!mailbcc.equals("")) {
				if ( mailbcc.indexOf(";") == 0 ) {
					try {
						mailaddress = new InternetAddress(mailbcc);
						message.addRecipient(Message.RecipientType.BCC, mailaddress);
					}
					catch (AddressException ae) {
						log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
								" Email BCC: AddressException: "+ae.getMessage());
						throw new Exception();
					}
				}
				else {
					StringTokenizer _StringTokenizer = new StringTokenizer(mailbcc, ";");
					while (_StringTokenizer.hasMoreTokens()) {
						try {	
							mailaddress = new InternetAddress(_StringTokenizer.nextToken());
							message.addRecipient(Message.RecipientType.BCC, mailaddress);
						}
						catch (AddressException ae) {
							log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
									" Email BCC: AddressException: "+ae.getMessage());	
							throw new Exception();
						}						
					}
				}	
			}
			mailaddress = null;


			message.setSubject(mailsubject);


			Multipart multipart = new MimeMultipart();
			// Part one is the text
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setText(mailtext);
			multipart.addBodyPart(bodyPart);


			//loop attachments
			Iterator it = mailAttachmentsList.iterator();
			while (it.hasNext()) {
				LabelValue labelValue = (LabelValue) it.next();
				String mailattachmentValue=labelValue.getValue();
				String mailattachmentLabel=labelValue.getLabel();				
				File file = new File(mailattachmentValue);
				if (file.exists()) {
					// Part two is the attachment
					bodyPart = new MimeBodyPart();
					DataSource sourceFile = new FileDataSource(mailattachmentValue);
					bodyPart.setDataHandler(new DataHandler(sourceFile));
					bodyPart.setFileName(mailattachmentLabel);
					multipart.addBodyPart(bodyPart);
				}
			}//end loop attachments

			message.setContent(multipart);

			//send mail	
			Transport.send(message);


		}
		catch (javax.mail.SendFailedException sfe) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" Mail Process: SendFailedException: "+sfe.getMessage());
			throw new Exception();	
		}	
		catch (javax.mail.MessagingException mex) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" Mail Process: MessagingException: "+mex.getMessage());
			throw new Exception();	
		}
		catch (Exception e) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
					" Mail Process: Exception: "+e.getMessage());	
			throw new Exception();	
		}		


		//mail process-----------------------------------------------<


	}  




}

