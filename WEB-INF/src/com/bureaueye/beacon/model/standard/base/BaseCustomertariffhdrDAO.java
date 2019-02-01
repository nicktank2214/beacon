package com.bureaueye.beacon.model.standard.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.bureaueye.beacon.model.standard.dao.iface.CustomertariffhdrDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseCustomertariffhdrDAO extends com.bureaueye.beacon.model.standard.dao._RootDAO {

	public BaseCustomertariffhdrDAO () {}
	
	public BaseCustomertariffhdrDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_CUSTOMERTARIFFHDRS_TOTAL_RESULTS = "findCustomertariffhdrsTotalResults";
	public static final String QUERY_FIND_CUSTOMERTARIFFHDRS_TOTAL_RESULTS_EXPIRED = "findCustomertariffhdrsTotalResultsExpired";
	public static final String QUERY_FIND_CUSTOMERTARIFFHDRS_TOTAL_RESULTS_ACTIVE = "findCustomertariffhdrsTotalResultsActive";
	public static final String QUERY_CUSTOMERTARIFF_SEARCH_TOTAL_RESULTS = "customertariffSearchTotalResults";


	public static CustomertariffhdrDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static CustomertariffhdrDAO getInstance () {
		if (null == instance) instance = new com.bureaueye.beacon.model.standard.dao.CustomertariffhdrDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.bureaueye.beacon.model.standard.Customertariffhdr.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.bureaueye.beacon.model.standard.Customertariffhdr
	 */
	public com.bureaueye.beacon.model.standard.Customertariffhdr cast (Object object) {
		return (com.bureaueye.beacon.model.standard.Customertariffhdr) object;
	}

	public com.bureaueye.beacon.model.standard.Customertariffhdr get(java.lang.String key)
	{
		return (com.bureaueye.beacon.model.standard.Customertariffhdr) get(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.standard.Customertariffhdr get(java.lang.String key, Session s)
	{
		return (com.bureaueye.beacon.model.standard.Customertariffhdr) get(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.standard.Customertariffhdr load(java.lang.String key)
	{
		return (com.bureaueye.beacon.model.standard.Customertariffhdr) load(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.standard.Customertariffhdr load(java.lang.String key, Session s)
	{
		return (com.bureaueye.beacon.model.standard.Customertariffhdr) load(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.standard.Customertariffhdr loadInitialize(java.lang.String key, Session s) 
	{ 
		com.bureaueye.beacon.model.standard.Customertariffhdr obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Customertariffhdr> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Customertariffhdr> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Customertariffhdr> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param customertariffhdr a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr)
	{
		return (java.lang.String) super.save(customertariffhdr);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param customertariffhdr a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr, Session s)
	{
		return (java.lang.String) save((Object) customertariffhdr, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param customertariffhdr a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr)
	{
		saveOrUpdate((Object) customertariffhdr);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param customertariffhdr a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr, Session s)
	{
		saveOrUpdate((Object) customertariffhdr, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param customertariffhdr a transient instance containing updated state
	 */
	public void update(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr) 
	{
		update((Object) customertariffhdr);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param customertariffhdr a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr, Session s)
	{
		update((Object) customertariffhdr, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
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
	public void delete(java.lang.String id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param customertariffhdr the instance to be removed
	 */
	public void delete(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr)
	{
		delete((Object) customertariffhdr);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param customertariffhdr the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr, Session s)
	{
		delete((Object) customertariffhdr, s);
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
	public void refresh (com.bureaueye.beacon.model.standard.Customertariffhdr customertariffhdr, Session s)
	{
		refresh((Object) customertariffhdr, s);
	}


}