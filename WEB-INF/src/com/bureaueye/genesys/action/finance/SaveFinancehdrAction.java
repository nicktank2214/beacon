package com.bureaueye.genesys.action.finance;



import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import com.bureaueye.beacon.action.BaseAction;
import com.bureaueye.beacon.bean.standard.Constants;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.genesys.form.finance.FinancehdrForm;
import com.bureaueye.genesys.model.finance.Financehdr;
import com.bureaueye.genesys.model.finance.bd.FinancehdrBD;



public final class SaveFinancehdrAction extends BaseAction {
	
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		// If the session has timed-out then return to the login page
		HttpSession session = request.getSession(false);
		FinancehdrForm saveForm = (FinancehdrForm) form;
		String action = saveForm.getAction();
		User user = (User) session.getAttribute(Constants.USER_KEY);
		ActionMessages messages = new ActionMessages();
		ActionMessages errors = new ActionMessages();
		
		
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
		
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Processing " + action+ " action");
		
		// Generate Business Delegate
		FinancehdrBD bd = new FinancehdrBD(this.getSessionFactoryClusterMap());
		
		// set primary key
		Integer pk = null;		
		try {
			pk = new Integer(saveForm.getId());			
		} catch (Exception e) {}
		// find data-access-object using primary key
		Financehdr dto = bd.read(pk);
		// not found create empty object
		if (dto == null) {
			dto = new Financehdr();
		}
		
		
		
		// error validation 
		
			
		
		// Report any messages back to the original form
		if (!messages.isEmpty()) {
			this.saveMessages(request, messages);
			return (mapping.getInputForward());
		}
		// Report any errors back to the original form
		if (!errors.isEmpty()) {
			this.saveErrors(request, errors);
			return (mapping.getInputForward());
		}				
		
		
		
		
		if (action.equals("Delete")) {		
			bd.delete(dto, user);		
		} else {
			
			
			// populate data-transfer-object from form
			// init FORM with DTO values
			try {
				BeanUtils.copyProperties(dto, saveForm);
			} catch (IllegalAccessException ite) {
			} catch (InvocationTargetException ite) {
			} catch (Exception e) {
			}	
			
			bd.createOrUpdate(dto, user);
			
			// Find the list form and reset the parameters as if it
			// was the first time in
			((ListForm) session.getAttribute("List" + mapping.getName())).setStartPage();
			
		}
		
		
		
		
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+"  Forwarding to success page");
		return findSuccess(mapping);
		
	}
	
	
	
}
