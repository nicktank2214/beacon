package com.bureaueye.genesys.action.contract;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMessages;

import com.bureaueye.beacon.action.ListAction;
import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.form.ListForm;
import com.bureaueye.beacon.model.system.SystemcodePK;
import com.bureaueye.beacon.model.system.bd.SystemcodeBD;
import com.bureaueye.genesys.model.contract.bd.ContractBD;



public final class ListContractAction extends ListAction {

	public void setTotalResults(ListForm listForm) throws ApplicationException {
	}

	public void setTotalResults(ListForm listForm, HttpSession session) throws ApplicationException {		
	
		// Set the default ordering
			if (listForm.getOrderBy() == null) {
				listForm.setOrderBy("Contractno");
			}


		// order by
		listForm.setOrderByDesc("");
		if (listForm.getOrderBy().equals("Createdate")) listForm.setOrderByDesc("desc");
		
		// set default field values
		if (listForm.getSearchString1() == null) listForm.setSearchString1("");
		if (listForm.getSearchString2() == null) listForm.setSearchString2("");	
		
		
		// lessee
		if (listForm.getSearchString3() == null || listForm.getSearchString3().equals("")) {
			listForm.setSearchString3("%");
		}
		
				
		Calendar c = Calendar.getInstance();
		int dateRangeDays = 7;
		try{
			dateRangeDays = new Integer(new SystemcodeBD(this.getSessionFactoryClusterMap()).read(new SystemcodePK("DATERANGEDAYS","LISTCONTRACT")).getDescr()).intValue();
		}catch(Exception e){}			
		//  date 'from'
		if (listForm.getSearchDate1() == null) {
			c.add(Calendar.DATE,-1*dateRangeDays);			
			// default 'from' date to today minus 7
			try {
				listForm.setSearchDate1(c.getTime());
			} catch (Exception e) {				
			}					
		}
		//  date 'to'		
		if (listForm.getSearchDate2() == null) {			
			// default 'to' date plus 7 
			c.add(Calendar.DATE,dateRangeDays);
			try {
				listForm.setSearchDate2(c.getTime());
			} catch (Exception e) {
			}								
		}

		int total = 0;
		boolean processedflag = false;
		if (!listForm.getSearchString1().equals("")) {
			total = new ContractBD(this.getSessionFactoryClusterMap()).findContractsTotalResultsByContractno(listForm);
			processedflag = true;
		}
		if (!processedflag) {
			total = new ContractBD(this.getSessionFactoryClusterMap()).findContractsTotalResults(listForm);
		}
		listForm.setTotalResults(total);

	
	}

	
	public ActionMessages setLineItems(ListForm listForm)
			throws ApplicationException {

	
		boolean processedflag = false;
		listForm.setLineItems(null);
		if (!listForm.getSearchString1().equals("")) {
			listForm.setLineItems(new ContractBD(this.getSessionFactoryClusterMap()).findContractsForSearchByContractno(listForm));
			processedflag = true;
		}
		if (!processedflag) {
			listForm.setLineItems(new ContractBD(this.getSessionFactoryClusterMap()).findContractsForSearch(listForm));
		}

		log.debug("["+this.getClass().getName()+"] "+new java.util.Date()+
				" ListForm Parameters: "+listForm.toString()
		);
		
		return null;
	}


}
