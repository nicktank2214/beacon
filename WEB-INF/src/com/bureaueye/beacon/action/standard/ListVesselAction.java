package com.bureaueye.beacon.action.standard;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessages;

import com.bureaueye.beacon.action.ListAction;
import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.standard.bd.VesselBD;

public final class ListVesselAction extends ListAction {

	public void setTotalResults(ListForm listForm) throws ApplicationException {
		
		// Set the default ordering
		if (listForm.getOrderBy() == null) {
			listForm.setOrderBy("Vesselkey");
		}

		int total = 0;
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+" Total number of results = " + total);
	}

	public void setTotalResults(ListForm listForm, HttpSession session) throws ApplicationException {
	}
	
	public ActionMessages setLineItems(ListForm listForm)
			throws ApplicationException {
		
		VesselBD bd = new VesselBD(this.getSessionFactoryClusterMap());
		listForm.setLineItems(bd.findVesselsByVessel(listForm));
		bd=null;
		
		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+
				" ListForm Parameters: "+listForm.toString()
				);
		
		return null;
	}
}
