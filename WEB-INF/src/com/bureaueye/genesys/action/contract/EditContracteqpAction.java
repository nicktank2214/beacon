package com.bureaueye.genesys.action.contract;

import java.lang.reflect.InvocationTargetException;

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
import com.bureaueye.genesys.form.contract.ContracteqpForm;
import com.bureaueye.genesys.model.contract.Contracteqp;
import com.bureaueye.genesys.model.contract.bd.ContracteqpBD;






public final class EditContracteqpAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ContracteqpForm editForm = (ContracteqpForm) form;

		// Extract attributes we will need
		String action = editForm.getAction();

		// If action isn't supplied then invalidate the session
		if (editForm.getAction() == null) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" Form not supplied for EditAction");
			request.getSession().removeAttribute(Constants.USER_KEY);
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Processing " + action	+ " action");

		Contracteqp dto = null; 
			
		if (!(action == null || action.equals("Create"))) {

			dto = new ContracteqpBD(this.getSessionFactoryClusterMap())
					.read(new Integer(editForm.getContracteqpId()));

			if (dto == null) {
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" No Record found for hdrId: "
						+ editForm.getContractId() + ", "
						+ "dtlid: " + editForm.getContracteqpId());
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.record.deleted", editForm.getContractId()
								+ " - " + editForm.getContracteqpId()));
				saveMessages(request, errors);				
				return findFailure(mapping);
			}

			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Populating Form from " + dto);

			try {
				// init FORM with DTO values
				BeanUtils.copyProperties(editForm, dto);
			} catch (IllegalAccessException ite) {			
			} catch (InvocationTargetException ite) {				
			} catch (Exception e) {
			}		
			
			
			if (action.equals("Copy")) {
				editForm.setCopyId(editForm.getContracteqpId());
				editForm.setContracteqpId("");				
				editForm.setEqpkeyp1("");
				editForm.setEqpkeyp2("");				
				editForm.setAction("Create");
				action="Create";
			}
		}


		// set defaults for Create process
		if (action.equals("Create")) {

		}				
		
		
		// Forward control to the edit page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page");

		return findSuccess(mapping);

	}

}
