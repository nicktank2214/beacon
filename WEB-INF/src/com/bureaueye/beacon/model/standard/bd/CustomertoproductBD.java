package com.bureaueye.beacon.model.standard.bd;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;

import com.bureaueye.beacon.exception.ApplicationException;
import com.bureaueye.beacon.model.bd.BaseBD;
import com.bureaueye.beacon.model.standard.Customertoproduct;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.beacon.model.standard.dao.CustomertoproductDAO;
import com.bureaueye.beacon.model.system.bd.SystemlogBD;

public final class CustomertoproductBD extends BaseBD {
	
	
	// constructors
	public CustomertoproductBD() {
	}
	public CustomertoproductBD(SessionFactory _factory) {
		super(_factory);		
	}
	public CustomertoproductBD(Map<String, SessionFactory> sfcm) {
		super(sfcm);		
	}	
	
	
    private static final String DEFAULT_CLASS_NAME = Customertoproduct.class.getName();
	
	
	//-----------------------------------------------
	// CRUD METHODS
	//-----------------------------------------------
	public Customertoproduct read(Object key)
	throws ApplicationException {
		
		if (key == null) return null;
		
		Customertoproduct dto = null;
		
		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_READ);			
		}
				
		try {
			
			dto = (Customertoproduct)session.get(Customertoproduct.class, (Integer)key);
			
		} catch (HibernateException e) {
			// Any other error should result in a SystemException
			throw new ApplicationException(ApplicationException.ROW_NOT_READ);
		}		
		
		// close Session 	
		try {
			session.close();
		}	
		catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_READ);
		}
		
		return dto;
		
	}
	
	
	public void createOrUpdate(Object dto, User user)
	throws ApplicationException {
		
		//		get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_CREATED_UPDATED);			
		}
				
		try {
			Customertoproduct _dto=(Customertoproduct)dto;
			
			Transaction tx = session.beginTransaction();
			
			if (read(_dto.getCustomertoproductId()) != null) {
				session.update(_dto);
				new SystemlogBD(this.getSessionFactoryClusterMap()).createSystemlog(_dto, user, "update");
			} else {
/*				Calendar c = Calendar.getInstance();
				 _dto.setCreatedate(c.getTime());
				 _dto.setCreatetime(com.bureaueye.beacon.util.Util.toTime(c));
				 _dto.setCreateuserid(user.getUserid());*/
				session.save(_dto);
			}
			
			tx.commit();			
		} catch (HibernateException e) {	
			throw new ApplicationException(
					ApplicationException.ROW_NOT_CREATED_UPDATED);
		}
		
		//		close Session 	
		try {
			session.close();
		}	
		catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_CREATED_UPDATED);
		}
		
	}
	
	
	public void delete(Object dto, User user) throws ApplicationException {
		
		//		get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_DELETED);			
		}
		
		try {
			Customertoproduct _dto=(Customertoproduct)dto;
			
			Transaction tx = session.beginTransaction();
			session.delete(_dto);
			tx.commit();		
			
			new SystemlogBD(this.getSessionFactoryClusterMap()).createSystemlog(_dto, user, "delete");
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_DELETED);
		} 
				
		//		close Session 	
		try {
			session.close();
		}	
		catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_DELETED);
		}
	}
	
	
	
	
	
	//-----------------------------------------------
	// FIND METHODS
	//-----------------------------------------------
	public List findCustomertoproductsByCustomer(
			String customeraddrkey, 
			int gotoPage, 
			int maxResults
			)
	throws ApplicationException {
		
		List queryResult = null;
		
		if (customeraddrkey == null || customeraddrkey.equals("")) {
			customeraddrkey = "%";
		}
		
		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);			
		}
		
		try {
			
			queryResult = session
			.createCriteria(Customertoproduct.class)
			.add(Expression.like("Customeraddrkey", customeraddrkey))
			.setFirstResult(gotoPage * maxResults)
			.setMaxResults(maxResults)
			.list();
			
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);
		} 
		
		// close Session 	
		try {
			session.close();
		}	
		catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);
		}
		
		return queryResult;
	}
	

	public int findCustomertoproductsTotalResults(
			String customeraddrkey
			) 
	throws ApplicationException {
		
		int queryTotal=0;
		
		if (customeraddrkey == null || customeraddrkey.equals("")) {
			customeraddrkey = "%";
		}
		
//		get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);			
		}
				
		try {
			
			Query q = session.getNamedQuery(CustomertoproductDAO.QUERY_FIND_CUSTOMERTOPRODUCT_TOTAL_RESULTS);
			q.setParameter("customeraddrkey", customeraddrkey);	
			queryTotal=((Long)q.uniqueResult()).intValue();
			
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);
		}
				
//		close Session 	
		try {
			session.close();
		}	
		catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);
		}
				
		return queryTotal;
		
	}
	

	
	
}
