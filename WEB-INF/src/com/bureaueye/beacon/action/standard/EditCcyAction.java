package com.bureaueye.beacon.action.standard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.bean.Constants;
import com.bureaueye.beacon.form.standard.CcyForm;
import com.bureaueye.beacon.model.standard.Ccy;
import com.bureaueye.beacon.model.standard.bd.CcyBD;

public final class EditCcyAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CcyForm ccyForm = (CcyForm) form;

		String action = ccyForm.getAction();

		// If action isn't supplied then invalidate the session
		if (action == null) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" CcyForm not supplied for EditCcyAction");
			request.getSession().removeAttribute(Constants.USER_KEY);
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" EditCcyAction:  Processing " + action + " action");

		if (!(action == null || action.equals("Create"))) {

			Ccy ccy = new CcyBD(this.getSessionFactoryClusterMap()).read(ccyForm.getCcykey());

			if (ccy == null) {
				log
						.error("No Currency found for ccykey "
								+ ccyForm.getCcykey());
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.record.deleted", ccyForm.getCcykey()));
				saveMessages(request, errors);
				log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Can't copy: forwarding to: "
						+ mapping.findForward("failure"));
				return findFailure(mapping);
			}

			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Populating CcyForm from " + ccy);

			BeanUtils.copyProperties(ccyForm, ccy);

			if (action.equals("Copy")) {
				ccyForm.setCcykey("");
				ccyForm.setAction("Create");
			}
		}

		// Forward control to the edit page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page");

		return findSuccess(mapping);
	}
}
