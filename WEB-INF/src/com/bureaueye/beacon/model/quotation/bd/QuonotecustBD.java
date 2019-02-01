package com.bureaueye.beacon.model.quotation.bd;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.bureaueye.beacon.exception.ApplicationException;

import com.bureaueye.beacon.model.bd.BaseBD;
import com.bureaueye.beacon.model.quotation.Quonotecust;
import com.bureaueye.beacon.model.quotation.dao.QuonotecustDAO;
import com.bureaueye.beacon.model.standard.User;
import com.bureaueye.beacon.model.system.bd.SystemlogBD;



/**
 * 
 * Amendments
 * ----------
 * 												
 * NT	2011-03-01		ITT-201103-0001		Quotation Customer Notes 
 *
 */
public final class QuonotecustBD extends BaseBD {


	// constructors
	public QuonotecustBD() {
	}
	public QuonotecustBD(SessionFactory _factory) {
		super(_factory);		
	}
	public QuonotecustBD(SessionFactory _factory1, SessionFactory _factory2) {
		super(_factory1, _factory2);		
	}	
	public QuonotecustBD(Map<String, SessionFactory> sfcm) {
		super(sfcm);		
	}


	private static final String DEFAULT_CLASS_NAME = Quonotecust.class.getName();	


	//-----------------------------------------------
	// CRUD METHODS
	//-----------------------------------------------
	public Quonotecust read(Object key)
	throws ApplicationException {

		if (key == null) return null;

		Quonotecust dto = null;

		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.ROW_NOT_READ);			
		}


		try {

			dto = (Quonotecust)session.get(Quonotecust.class, (Integer)key);

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
			Quonotecust _dto=(Quonotecust)dto;

			Transaction tx = session.beginTransaction();

			if (read(_dto.getQuonotecustId()) != null) {
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
			Quonotecust _dto=(Quonotecust)dto;

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
	public List findQuonotecustsById(
			String id, 
			int gotoPage,
			int maxResults, 
			String order,
			String order2							
	) throws ApplicationException {

		List queryResult = null;

		if (order == null || order.equals("")) {
			return null;
		}
		if (order2 == null) {
			order2 = "";
		}

		//		get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);			
		}

		try {

			if (order.equals("Createdate")) {
				queryResult = session.createCriteria(
						Quonotecust.class).add(
								Expression.eq("QuohdrId", new Integer(id)))
								.addOrder(Order.desc(order))
								.addOrder(Order.desc(order2))						
								.setFirstResult(gotoPage * maxResults)
								.setMaxResults(maxResults).list();
			} else {			
				if (order2.equals("")) {
					queryResult = session.createCriteria(
							Quonotecust.class).add(
									Expression.eq("QuohdrId", new Integer(id)))
									.addOrder(Order.asc(order))					
									.setFirstResult(gotoPage * maxResults)
									.setMaxResults(maxResults).list();
				} else {
					queryResult = session.createCriteria(
							Quonotecust.class).add(
									Expression.eq("QuohdrId", new Integer(id)))
									.addOrder(Order.asc(order))
									.addOrder(Order.desc(order2))					
									.setFirstResult(gotoPage * maxResults)
									.setMaxResults(maxResults).list();

				}
			}
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

		return queryResult;
	}	


	public int findQuonotecustsTotalResults(String id)
	throws ApplicationException {

		int queryTotal=0;

		// get Session 
		Session session = null;
		try {
			session = this.getSessionFactoryClusterByClass(DEFAULT_CLASS_NAME).openSession();
		} catch (HibernateException e) {
			throw new ApplicationException(ApplicationException.LIST_FAILED);			
		}

		try {

			Query q = session.getNamedQuery(QuonotecustDAO.QUERY_FIND_QUONOTECUSTS_TOTAL_RESULTS);
			q.setParameter("id", new Integer(id));			
			queryTotal=((Long)q.uniqueResult()).intValue();

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

		return queryTotal;
	}



}
