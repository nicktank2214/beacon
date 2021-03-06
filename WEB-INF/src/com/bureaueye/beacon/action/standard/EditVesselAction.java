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
import com.bureaueye.beacon.form.standard.VesselForm;
import com.bureaueye.beacon.model.standard.Vessel;
import com.bureaueye.beacon.model.standard.bd.VesselBD;

public final class EditVesselAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		VesselForm vesselForm = (VesselForm) form;

		String action = vesselForm.getAction();

		// If action isn't supplied then invalidate the session
		if (action == null) {
			log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" VesselForm not supplied for EditVesselAction");
			request.getSession().removeAttribute(Constants.USER_KEY);
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" EditVesselAction:  Processing " + action + " action");

		if (!(action == null || action.equals("Create"))) {

			Vessel vessel = new VesselBD(this.getSessionFactoryClusterMap()).read(vesselForm.getVesselkey());

			if (vessel == null) {
				log.error("["+this.getClass().getName()+"] "+new java.util.Date()+" No Vessel found for Vesselkey "
						+ vesselForm.getVesselkey());
				ActionMessages errors = new ActionMessages();
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"error.record.deleted", vesselForm.getVesselkey()));
				saveMessages(request, errors);
				log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Can't copy: forwarding to: "
						+ mapping.findForward("failure"));
				return findFailure(mapping);
			}

			log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Populating VesselForm from " + vessel);

			BeanUtils.copyProperties(vesselForm, vessel);

			if (action.equals("Copy")) {
				vesselForm.setVesselkey("");
				vesselForm.setAction("Create");
			}
		}

		// Forward control to the edit page
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to 'success' page");

		return findSuccess(mapping);
	}
}
