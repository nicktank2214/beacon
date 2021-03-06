package com.bureaueye.beacon.action.standard;

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
import com.bureaueye.beacon.form.standard.MovementForm;
import com.bureaueye.beacon.model.standard.Movement;
import com.bureaueye.beacon.model.standard.bd.MovementBD;






public final class EditMovementAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MovementForm editForm = (MovementForm) form;

		// Extract attributes we will need
		String action = editForm.getAction();

		// If action isn't supplied then invalidate the session
		if (editForm.getAction() == null) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" Form not supplied for EditAction");
			request.getSession().removeAttribute(Constants.USER_KEY);
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Processing " + action	+ " action");

		if (!(action == null || action.equals("Create"))) {

			Movement dto = new MovementBD(this.getSessionFactoryClusterMap())
					.read(new Integer(editForm.getMovementId()));

			if (dto == null) {
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" No Record found for Main Id: "
						+ editForm.getUnitId() + ", "
						+ "Record Id: " + editForm.getMovementId());
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.record.deleted", editForm.getUnitId()
								+ " - " + editForm.getMovementId()));
				saveMessages(request, errors);
				return findFailure(mapping);
			}

			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Populating Form from " + dto);

			try {
				// init FORM with DTO values
				BeanUtils.copyProperties(editForm, dto);
			} catch (IllegalAccessException ite) {
			} catch (InvocationTargetException ite) {
			}  catch (Exception e) {
				}		
			
		}

		
		// set collections

		
		// set defaults for Create process
		if (action.equals("Create")) {
			// default user information
			editForm.setMovests(com.bureaueye.beacon.bean.Constants.AVAILABLE_MOVSTS);
		}
		
		// Forward control to the edit page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page");

		return findSuccess(mapping);

	}

}
