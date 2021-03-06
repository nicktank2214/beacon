package com.bureaueye.beacondms.form.dms;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bureaueye.beacon.form.BaseForm;

public final class StoreForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _action;

	// primary key
	private java.lang.Integer xdocumentId;
	private java.lang.String dockey;
	

	// fields
	private java.lang.String docid;
	private java.lang.String filename;
	private java.lang.String contenttype;
	private java.sql.Blob filebin;
	private java.lang.String filesize;
	private org.apache.struts.upload.FormFile infile;
	private java.lang.StringBuffer outfile;
	private java.lang.String description;	
	private java.lang.String categorycode;	
	private java.lang.String subcategorycode;	
	private java.lang.String txt1;
	private java.lang.String txt2;
	private java.lang.String doctype;
	
	private java.lang.Integer  versionno;
	private java.util.Date versiondate;
	private java.lang.String versiontime;
	private java.lang.String versionuserid;
	private java.lang.String versiondescription;
	
	
	public String getAction() {
		return _action;
	}

	public void setAction(String _action) {
		this._action = _action;
	}

	
	
	public java.lang.Integer getXdocumentId () {
		return xdocumentId;
	}

	/**
	 * Set the unique identifier of this class
	 * @param xdocumentId the new ID
	 */
	public void setXdocumentId (java.lang.Integer xdocumentId) {
		this.xdocumentId = xdocumentId;
	}
	

	/**
	 * Return the value associated with the column: dockey
	 */
	public java.lang.String getDockey () {
		return dockey;
	}

	/**
	 * Set the value related to the column: dockey
	 * @param dockey the dockey value
	 */
	public void setDockey (java.lang.String dockey) {
		this.dockey = dockey;
	}
	
	
	public org.apache.struts.upload.FormFile getInfile () {
		return infile;
	}
	public void setInfile (org.apache.struts.upload.FormFile infile) {
		this.infile = infile;
	}
	
	public StringBuffer getOutfile () {
		return outfile;
	}
	public void setOutfile (StringBuffer outfile) {
		this.outfile = outfile;
	}
	
	/**
	 * Return the value associated with the column: filename
	 */
	public java.lang.String getFilename () {
		return filename;
	}

	/**
	 * Set the value related to the column: filename
	 * @param filename the filename value
	 */
	public void setFilename (java.lang.String filename) {
		this.filename = filename;
	}

	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}
	
	
	/**
	 * Return the value associated with the column: docid
	 */
	public java.lang.String getDocid () {
		return docid;
	}

	/**
	 * Set the value related to the column: docid
	 * @param docid the docid value
	 */
	public void setDocid (java.lang.String docid) {
		this.docid = docid;
	}
	
	
	/**
	 * Return the value associated with the column: filetype
	 */
	public java.lang.String getContenttype () {
		return contenttype;
	}

	/**
	 * Set the value related to the column: filetype
	 * @param filetype the filetype value
	 */
	public void setContenttype (java.lang.String contenttype) {
		this.contenttype = contenttype;
	}



	/**
	 * Return the value associated with the column: filebin
	 */
	public java.sql.Blob getFilebin () {
		return filebin;
	}

	/**
	 * Set the value related to the column: filebin
	 * @param filebin the filebin value
	 */
	public void setFilebin (java.sql.Blob filebin) {
		this.filebin = filebin;
	}



	/**
	 * Return the value associated with the column: filesize
	 */
	public java.lang.String getFilesize () {
		return filesize;
	}

	/**
	 * Set the value related to the column: filesize
	 * @param filesize the filesize value
	 */
	public void setFilesize (java.lang.String filesize) {
		this.filesize = filesize;
	}





	/**
	 * Return the value associated with the column: doctype
	 */
	public java.lang.String getDoctype () {
		return doctype;
	}

	/**
	 * Set the value related to the column: doctype
	 * @param doctype the doctype value
	 */
	public void setDoctype (java.lang.String doctype) {
		this.doctype = doctype;
	}


	/**
     */
	public java.lang.String getCategorycode () {
		return categorycode;
	}

	/**
	 */
	public void setCategorycode (java.lang.String categorycode) {
		this.categorycode = categorycode;
	}
	
	
	/**
	 * Return the value associated with the column: subcategorycode
	 */
	public java.lang.String getSubcategorycode () {
		return subcategorycode;
	}

	/**
	 * Set the value related to the column: subcategorycode
	 * @param subcategorycode the subcategorycode value
	 */
	public void setSubcategorycode (java.lang.String subcategorycode) {
		this.subcategorycode = subcategorycode;
	}
	
	
	/**
	 * Return the value associated with the column: txt1
	 */
	public java.lang.String getTxt1 () {
		return txt1;
	}

	/**
	 * Set the value related to the column: txt1
	 * @param txt1 the txt1 value
	 */
	public void setTxt1 (java.lang.String txt1) {
		this.txt1 = txt1;
	}



	/**
	 * Return the value associated with the column: txt2
	 */
	public java.lang.String getTxt2 () {
		return txt2;
	}

	/**
	 * Set the value related to the column: txt2
	 * @param txt2 the txt2 value
	 */
	public void setTxt2 (java.lang.String txt2) {
		this.txt2 = txt2;
	}
	

	
	
	
	/**
	 * Return the value associated with the column: versionno
	 */
	public java.lang.Integer getVersionno () {
		return versionno;
	}

	/**
	 * Set the value related to the column: versionno
	 * @param versionno the versionno value
	 */
	public void setVersionno (java.lang.Integer versionno) {
		this.versionno = versionno;
	}



	/**
	 * Return the value associated with the column: versiondate
	 */
	public java.util.Date getVersiondate () {
		return versiondate;
	}

	/**
	 * Set the value related to the column: versiondate
	 * @param versiondate the versiondate value
	 */
	public void setVersiondate (java.util.Date versiondate) {
		this.versiondate = versiondate;
	}



	/**
	 * Return the value associated with the column: versiontime
	 */
	public java.lang.String getVersiontime () {
		return versiontime;
	}

	/**
	 * Set the value related to the column: versiontime
	 * @param versiontime the versiontime value
	 */
	public void setVersiontime (java.lang.String versiontime) {
		this.versiontime = versiontime;
	}



	/**
	 * Return the value associated with the column: versionuserid
	 */
	public java.lang.String getVersionuserid () {
		return versionuserid;
	}

	/**
	 * Set the value related to the column: versionuserid
	 * @param versionuserid the versionuserid value
	 */
	public void setVersionuserid (java.lang.String versionuserid) {
		this.versionuserid = versionuserid;
	}



	/**
	 * Return the value associated with the column: versiondescription
	 */
	public java.lang.String getVersiondescription () {
		return versiondescription;
	}

	/**
	 * Set the value related to the column: versiondescription
	 * @param versiondescription the versiondescription value
	 */
	public void setVersiondescription (java.lang.String versiondescription) {
		this.versiondescription = versiondescription;
	}


	
	
	

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// do not vaildate if Delete Action
		if (_action == null || _action.equals("Delete")) return null;

		// Perform validator framework validations
		ActionErrors errors = new ActionErrors();

		addErrorIfBlank(errors, "docid", docid, "error.docid.required");

		addErrorIfBlank(errors, "description", description, "error.description.required");
		//addErrorIfBlank(errors, "categorycode", categorycode, "error.categorycode.required");
		addErrorIfBlank(errors, "doctype", doctype, "error.documenttype.required");
		
		if (_action.equals("Create")) {
			addErrorIfBlank(errors, "infile", infile.getFileName(), "error.document.required");
		}
		
		return errors;

	}

	private void addErrorIfBlank(ActionErrors errors, String fieldName,
			String fieldValue, String message) {
		if (fieldValue == null || fieldValue.trim().equals("")) {
			errors.add(fieldName, new ActionMessage(message));
		}
	}

	
	/**
	 * Returns an array of keys, representing values that should not be
	 * populated for the current form instance. Subclasses that override
	 * this method to provide additional keys to be skipped should be
	 * sure to call <code>super</code>
	 *
	 * @return an array of keys to be skipped
	 */
	protected ArrayList keysToSkip() {
		ArrayList keysToSkip = super.keysToSkip();		
		
		return keysToSkip;
	}
	
	
}
