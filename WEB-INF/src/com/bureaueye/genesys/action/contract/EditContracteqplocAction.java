package com.bureaueye.genesys.action.contract;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.bean.standard.Constants;
import com.bureaueye.genesys.form.contract.ContracteqplocForm;
import com.bureaueye.genesys.model.contract.Contracteqploc;
import com.bureaueye.genesys.model.contract.bd.ContracteqplocBD;



public final class EditContracteqplocAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ContracteqplocForm editForm = (ContracteqplocForm) form;

		// Extract attributes we will need
		String action = editForm.getAction();

		// If action isn't supplied then invalidate the session
		if (editForm.getAction() == null) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" Form not supplied for EditAction");
			request.getSession().removeAttribute(Constants.USER_KEY);
		}

		log
				.debug("EditAction:  Processing " + action
						+ " action");

		if (!(action == null || action.equals("Create"))) {

			Contracteqploc dto = new ContracteqplocBD(this.getSessionFactoryClusterMap()).read(new Integer(editForm.getContracteqplocId()));

			if (dto == null) {
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+
						" No record found for contracteqpId: "
						+ editForm.getContracteqpId() + ", "
						+ "contracteqplocId: " + editForm.getContracteqplocId());
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.record.deleted", editForm.getContracteqpId()
								+ " - " + editForm.getContracteqplocId()));
				saveMessages(request, errors);
				return findFailure(mapping);
			}

			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Populating Form from " + dto);

			// init FORM with DTO values
			BeanUtils.copyProperties(editForm, dto);

			// set header information

			
			if (action.equals("Copy")) {
				editForm.setCopyId(editForm.getContracteqplocId());				
				editForm.setContracteqplocId("");				
				//editForm.setLocationkey("");		do not blank on copy to make it easier to copy locations
				//editForm.setPudoflag("");				
				editForm.setAction("Create");
				action="Create";
			}
			
		}

		
		// set defaults for Create process
		if (action.equals("Create")) {
			// default information

		}
		
		
		// Forward control to the edit page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page");

		return findSuccess(mapping);

	}

}
