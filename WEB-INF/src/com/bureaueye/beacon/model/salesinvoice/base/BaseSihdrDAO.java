package com.bureaueye.beacon.model.salesinvoice.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.bureaueye.beacon.model.salesinvoice.dao.iface.SihdrDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseSihdrDAO extends com.bureaueye.beacon.model.salesinvoice.dao._RootDAO {

	public BaseSihdrDAO () {}
	
	public BaseSihdrDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_SIHDRS_TOTAL_RESULTS = "findSihdrsTotalResults";
	public static final String QUERY_FIND_SIHDRS_TOTAL_RESULTS_BY_INVOICENO = "findSihdrsTotalResultsByInvoiceno";
	public static final String QUERY_FIND_SIHDRS_TOTAL_RESULTS_BY_SIHDR_ID = "findSihdrsTotalResultsBySihdrId";
	public static final String QUERY_SIHDRS_COUNT_BY_PRINTEDFLAG_CREATEDATE_CREATEUSERID = "sihdrsCountByPrintedflagCreatedateCreateuserid";


	public static SihdrDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static SihdrDAO getInstance () {
		if (null == instance) instance = new com.bureaueye.beacon.model.salesinvoice.dao.SihdrDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.bureaueye.beacon.model.salesinvoice.Sihdr.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.bureaueye.beacon.model.salesinvoice.Sihdr
	 */
	public com.bureaueye.beacon.model.salesinvoice.Sihdr cast (Object object) {
		return (com.bureaueye.beacon.model.salesinvoice.Sihdr) object;
	}

	public com.bureaueye.beacon.model.salesinvoice.Sihdr get(java.lang.Integer key)
	{
		return (com.bureaueye.beacon.model.salesinvoice.Sihdr) get(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.salesinvoice.Sihdr get(java.lang.Integer key, Session s)
	{
		return (com.bureaueye.beacon.model.salesinvoice.Sihdr) get(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.salesinvoice.Sihdr load(java.lang.Integer key)
	{
		return (com.bureaueye.beacon.model.salesinvoice.Sihdr) load(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.salesinvoice.Sihdr load(java.lang.Integer key, Session s)
	{
		return (com.bureaueye.beacon.model.salesinvoice.Sihdr) load(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.salesinvoice.Sihdr loadInitialize(java.lang.Integer key, Session s) 
	{ 
		com.bureaueye.beacon.model.salesinvoice.Sihdr obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.salesinvoice.Sihdr> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.salesinvoice.Sihdr> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.bureaueye.beacon.model.salesinvoice.Sihdr> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param sihdr a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr)
	{
		return (java.lang.Integer) super.save(sihdr);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param sihdr a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr, Session s)
	{
		return (java.lang.Integer) save((Object) sihdr, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param sihdr a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr)
	{
		saveOrUpdate((Object) sihdr);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param sihdr a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr, Session s)
	{
		saveOrUpdate((Object) sihdr, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param sihdr a transient instance containing updated state
	 */
	public void update(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr) 
	{
		update((Object) sihdr);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param sihdr a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr, Session s)
	{
		update((Object) sihdr, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.Integer id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.Integer id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param sihdr the instance to be removed
	 */
	public void delete(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr)
	{
		delete((Object) sihdr);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param sihdr the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr, Session s)
	{
		delete((Object) sihdr, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.bureaueye.beacon.model.salesinvoice.Sihdr sihdr, Session s)
	{
		refresh((Object) sihdr, s);
	}


}