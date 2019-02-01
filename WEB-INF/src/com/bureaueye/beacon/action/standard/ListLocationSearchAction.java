package com.bureaueye.beacon.action.standard;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessages;

import com.bureaueye.beacon.action.PopupListAction;
import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.standard.bd.LocationBD;

public final class ListLocationSearchAction extends PopupListAction {

	public void setTotalResults(ListForm listForm) throws ApplicationException {
		
		// Set the default ordering
		if (listForm.getOrderBy() == null) {
			listForm.setOrderBy("LocationName");
		}
		
	}

	
	public void setTotalResults(ListForm listForm, HttpSession session) throws ApplicationException {		
		
	}
	
	
	public ActionMessages setLineItems(ListForm listForm)
			throws ApplicationException {
		
		listForm.setLineItems(
				new LocationBD(this.getSessionFactoryClusterMap()).findLocationsForSearch( 
				listForm.getSearchString1(), 
				listForm.getSearchString2(),
				listForm.getSearchString3(), 
				listForm.getSearchString4(),
				listForm.getSearchString5(), 
				listForm.getSearchString6(),
				listForm.getSearchString7(), 
				listForm.getSearchString8(),
				listForm.getSearchString9(), 
				listForm.getSearchString10(),
				listForm.getSearchString11(), 
				listForm.getSearchString12(),
				listForm.getSearchString13(), 
				listForm.getSearchString14(),
				listForm.getG1codekey(),
				listForm.getG2codekey(),
				listForm.getG3codekey(),
				listForm.getG4codekey(),					
				listForm.getOrderBy(), 
				listForm.getHeaderInfo1()
				)
				);

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+
				" ListForm Parameters: "+listForm.toString()
				);

		return null;
	}
}