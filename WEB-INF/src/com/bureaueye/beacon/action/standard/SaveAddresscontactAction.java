package com.bureaueye.beacon.action.standard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.bean.Constants;
import com.bureaueye.beacon.form.ListForm;

import com.bureaueye.beacon.form.standard.AddresscontactForm;

import com.bureaueye.beacon.model.standard.Addresscontact;
import com.bureaueye.beacon.model.standard.AddresscontactPK;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.beacon.model.standard.bd.AddresscontactBD;

public final class SaveAddresscontactAction extends BaseAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// If the session has timed-out then return to the login page
		HttpSession session = request.getSession(false);
		AddresscontactForm addresscontactform = (AddresscontactForm) form;
		String action = addresscontactform.getAction();
		User user = (User) session.getAttribute(Constants.USER_KEY);

		// If the session has timed-out then take the user back to the login
		// page
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

		// Generate Business Delegate
		AddresscontactBD addresscontactbd = new AddresscontactBD(this.getSessionFactoryClusterMap());

		AddresscontactPK addresscontactpk = new AddresscontactPK(
				addresscontactform.getContactkey(), addresscontactform
						.getAddrkey());

		if (action.equals("Delete")) {
			addresscontactbd.delete(new Addresscontact(addresscontactpk), user);
		} else {

			Addresscontact addresscontact = addresscontactbd
					.read(addresscontactpk);

			if (addresscontact == null) {
				addresscontact = new Addresscontact(addresscontactpk);
			}

			BeanUtils.copyProperties(addresscontact, addresscontactform);

			addresscontactbd.createOrUpdate(addresscontact, user);

			// Find the addresscontact form and reset the parameters as if it
			// was the first time in
			((ListForm) session.getAttribute("List" + mapping.getName()))
					.setStartPage();
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to success page");
		return findSuccess(mapping);

	}

}
