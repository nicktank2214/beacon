package com.bureaueye.beacon.form.standard;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bureaueye.beacon.form.BaseForm;

public final class VendortariffdtlForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vendtarfhdrId;

	private Integer vendtarfdtlId;

	private String action;

	private String fromvalue = "0";

	private String tovalue = "0";

	private String unitrate = "0.0";

	private String lumpsum = "0.0";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getFromvalue() {
		return fromvalue;
	}

	public void setFromvalue(String fromvalue) {
		this.fromvalue = fromvalue;
	}

	public String getLumpsum() {
		return lumpsum;
	}

	public void setLumpsum(String lumpsum) {
		this.lumpsum = lumpsum;
	}

	public String getTovalue() {
		return tovalue;
	}

	public void setTovalue(String tovalue) {
		this.tovalue = tovalue;
	}

	public String getUnitrate() {
		return unitrate;
	}

	public void setUnitrate(String unitrate) {
		this.unitrate = unitrate;
	}

	public Integer getVendtarfdtlId() {
		return vendtarfdtlId;
	}

	public void setVendtarfdtlId(Integer vendtarfdtlId) {
		this.vendtarfdtlId = vendtarfdtlId;
	}

	public String getVendtarfhdrId() {
		return vendtarfhdrId;
	}

	public void setVendtarfhdrId(String vendtarfhdrId) {
		this.vendtarfhdrId = vendtarfhdrId;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {

		// Do not vaildate if Delete Action
		if (action == null || action.equals("Delete")) {
			return null;
		}

		ActionErrors errors =
			super.validate(mapping, request);
			if (errors==null) errors = new ActionErrors();
//		// Perform validator framework validations
//		ActionErrors errors = new ActionErrors();

		try {
			int fromvalueInt = new Integer(fromvalue).intValue();
			int tovalueInt = new Integer(tovalue).intValue();
			if (tovalueInt < fromvalueInt) {
				errors.add("tovalue", new ActionMessage(
						"error.tovalue.afterfromvalue"));
			}
		} catch (java.lang.NumberFormatException nfe) {
			errors.add("tovalue", new ActionMessage(
					"error.tovalue.afterfromvalue"));
		}

//		addErrorIfBlank(errors, "fromvalue", fromvalue,
//				"error.fromvalue.required");
//		addErrorIfBlank(errors, "tovalue", tovalue, "error.tovalue.required");
//		addErrorIfBlank(errors, "unitrate", unitrate, "error.unitrate.required");
//		addErrorIfBlank(errors, "lumpsum", lumpsum, "error.lumpsum.required");

		return errors;

	}

/*	private void addErrorIfBlank(ActionErrors errors, String fieldName,
			String fieldValue, String message) {
		if (fieldValue == null || fieldValue.trim().equals("")) {
			errors.add(fieldName, new ActionMessage(message));
		}
	}*/

}
