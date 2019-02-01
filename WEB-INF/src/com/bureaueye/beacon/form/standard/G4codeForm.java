package com.bureaueye.beacon.form.standard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bureaueye.beacon.form.BaseForm;
import com.bureaueye.beacon.util.Util;



public final class G4codeForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String _action;
	private String _readonly = "false";



	// primary key
	private java.lang.String G4codeId;
	
	// fields
	private java.lang.String g1codekey;
	private java.lang.String g2codekey;
	private java.lang.String g3codekey;
	private java.lang.String g4codekey;
	private java.lang.String ldesc;
	private java.lang.String sdesc;
	private java.lang.String locationcode;
	private java.lang.String groupg3codekey;

	
	
	
	public String getAction() {return _action;}
	public void setAction(String _action) {this._action = _action;}	

	public String getReadonly() {return _readonly;}
	public void setReadonly(String _readonly) {this._readonly = _readonly;}


	
	/**
     */
	public java.lang.String getG4codeId () {
		return G4codeId;
	}

	/**
	 */
	public void setG4codeId (java.lang.String G4codeId) {
		this.G4codeId = G4codeId;
	}




	/**
	 */
	public java.lang.String getG1codekey () {
		return g1codekey;
	}
	/**
	 */
	public void setG1codekey (java.lang.String g1codekey) {
		this.g1codekey = g1codekey;
	}


	/**
	 */
	public java.lang.String getG2codekey () {
		return g2codekey;
	}

	/**
	 */
	public void setG2codekey (java.lang.String g2codekey) {
		this.g2codekey = g2codekey;
	}

	
	/**
	 */
	public java.lang.String getG3codekey () {
		return g3codekey;
	}
	/**
	 */
	public void setG3codekey (java.lang.String g3codekey) {
		this.g3codekey = g3codekey;
	}
	
	/**
	 */
	public java.lang.String getG4codekey () {
		return g4codekey;
	}
	/**
	 */
	public void setG4codekey (java.lang.String g4codekey) {
		this.g4codekey = g4codekey;
	}
	
	
	
	/**
	 * Return the value associated with the column: ldesc
	 */
	public java.lang.String getLdesc () {
		return ldesc;
	}

	/**
	 * Set the value related to the column: ldesc
	 * @param ldesc the ldesc value
	 */
	public void setLdesc (java.lang.String ldesc) {
		this.ldesc = ldesc;
	}



	/**
	 * Return the value associated with the column: sdesc
	 */
	public java.lang.String getSdesc () {
		return sdesc;
	}

	/**
	 * Set the value related to the column: sdesc
	 * @param sdesc the sdesc value
	 */
	public void setSdesc (java.lang.String sdesc) {
		this.sdesc = sdesc;
	}



	/**
	 * Return the value associated with the column: locationcode
	 */
	public java.lang.String getLocationcode () {
		return locationcode;
	}

	/**
	 * Set the value related to the column: locationcode
	 * @param locationcode the locationcode value
	 */
	public void setLocationcode (java.lang.String locationcode) {
		this.locationcode = locationcode;
	}



	/**
	 */
	public java.lang.String getGroupg3codekey () {
		return groupg3codekey;
	}

	/**
	 */
	public void setGroupg3codekey (java.lang.String groupg3codekey) {
		this.groupg3codekey = groupg3codekey;
	}



	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}	


	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// do not vaildate if Delete Action
		if (_action == null || _action.equals("Delete"))
			return null;

		// Perform validator framework validations
		ActionErrors errors = new ActionErrors();

        if ((g1codekey == null) || (g1codekey.length() < 1))
            errors.add("g1codekey",
                    new ActionMessage("error.g1code.required"));  
        
        if ((g2codekey == null) || (g2codekey.length() < 1))
            errors.add("g2codekey",
                    new ActionMessage("error.g2code.required"));        	

        if ((g3codekey == null) || (g3codekey.length() < 1))
            errors.add("g3codekey",
                    new ActionMessage("error.g3code.required"));   
 
        if ((g4codekey == null) || (g4codekey.length() < 1))
            errors.add("g4codekey",
                    new ActionMessage("error.g4code.required"));   
        
        
		if (_action.equals("Create")) {
			if (Util.invalidChars(this.g4codekey)) errors.add("g4code", new ActionMessage("error.g4code.invalidchars"));
		}

		addErrorIfBlank(errors, "ldesc", ldesc, "error.desc.required");      
		addErrorIfBlank(errors, "sdesc", sdesc, "error.sdesc.required");    
		
      
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
