package com.bureaueye.beacon.model.standard.bd;

import java.util.Calendar;
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
import com.bureaueye.beacon.model.standard.Taxcode;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.beacon.model.system.bd.SystemlogBD;



public final class TaxcodeBD extends BaseBD {
	
	
	// constructors
	public TaxcodeBD() {
	}
	public TaxcodeBD(SessionFactory _factory) {
		super(_factory);		
	}
	public TaxcodeBD(Map<String, SessionFactory> sfcm) {
		super(sfcm);		
	}	
	
	
    private static final String DEFAULT_CLASS_NAME = Taxcode.class.getName();
	
	
	//-----------------------------------------------
	// CRUD METHODS
	//-----------------------------------------------
	public Taxcode read(Object key)
	throws ApplicationException {
		
		if (key == null) return null;
		
		Taxcode dto = null;
		
		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_READ);			
		}
		
		
		try {
			
			dto = (Taxcode)session.get(Taxcode.class, (String)key);
			
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
			Taxcode _dto=(Taxcode)dto;
			
			Transaction tx = session.beginTransaction();
			
			if (read(_dto.getTaxcodekey()) != null) {
				session.update(_dto);
				new SystemlogBD(this.getSessionFactoryClusterMap()).createSystemlog(_dto, user, "update");
			} else {
				Calendar c = Calendar.getInstance();
				_dto.setCreatedate(c.getTime());
				_dto.setCreatetime(com.bureaueye.beacon.util.Util.toTime(c));
				_dto.setCreateuserid(user.getUserid());
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
			Taxcode _dto=(Taxcode)dto;
			
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
	public List findTaxcodes() throws ApplicationException {		
		
		List queryResult=null;
		
		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);			
		}
		
		try {
			
			Query q = session.createQuery("from Taxcode taxcode");			
			queryResult=q.list();
			
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
	
	
	public Taxcode findTaxcodeByTaxcodekey(String key) 
	throws ApplicationException {
		
		Taxcode queryResult = null;
		
		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);			
		}
		
		try {
			
			queryResult= (Taxcode)session
			.createCriteria(Taxcode.class)
			.add( Expression.eq("Taxcodekey", key) )
			.setMaxResults(1).uniqueResult();
			
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
	
	
	
	
}
