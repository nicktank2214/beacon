package com.bureaueye.beacon.action.standard;


import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessages;

import com.bureaueye.beacon.action.ListAction;
import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.standard.bd.UserBD;


public final class ListUserAction extends ListAction {
	
	public void setTotalResults(ListForm listForm) throws ApplicationException {
		
		// Set the default ordering
		if (listForm.getOrderBy() == null) {
			listForm.setOrderBy("Userid");
			listForm.setOrderByDesc("");
		}
		
		
		
		int total = 0;
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Total number of results = " + total);
		
	}
	
	public void setTotalResults(ListForm listForm, HttpSession session) throws ApplicationException {
	}
	
	public ActionMessages setLineItems(ListForm listForm)
	throws ApplicationException {
		
		UserBD userbd=new UserBD(this.getSessionFactoryClusterMap());
		listForm.setLineItems(userbd.findUsersBySearch(listForm));
						
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+
				" ListForm Parameters: "+listForm.toString()
		);
		
		userbd=null;
		
		return null;
	}
}
