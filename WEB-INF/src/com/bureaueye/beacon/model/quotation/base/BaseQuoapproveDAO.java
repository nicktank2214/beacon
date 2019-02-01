package com.bureaueye.beacon.model.quotation.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.bureaueye.beacon.model.quotation.dao.iface.QuoapproveDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseQuoapproveDAO extends com.bureaueye.beacon.model.quotation.dao._RootDAO {

	public BaseQuoapproveDAO () {}
	
	public BaseQuoapproveDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_QUOAPPROVES_TOTAL_RESULTS = "findQuoapprovesTotalResults";
	public static final String QUERY_QUOAPPROVES_COUNT_BY_CREATEDATE_USERID = "quoapprovesCountByCreatedateUserid";


	public static QuoapproveDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static QuoapproveDAO getInstance () {
		if (null == instance) instance = new com.bureaueye.beacon.model.quotation.dao.QuoapproveDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.bureaueye.beacon.model.quotation.Quoapprove.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.bureaueye.beacon.model.quotation.Quoapprove
	 */
	public com.bureaueye.beacon.model.quotation.Quoapprove cast (Object object) {
		return (com.bureaueye.beacon.model.quotation.Quoapprove) object;
	}

	public com.bureaueye.beacon.model.quotation.Quoapprove get(java.lang.Integer key)
	{
		return (com.bureaueye.beacon.model.quotation.Quoapprove) get(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.quotation.Quoapprove get(java.lang.Integer key, Session s)
	{
		return (com.bureaueye.beacon.model.quotation.Quoapprove) get(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.quotation.Quoapprove load(java.lang.Integer key)
	{
		return (com.bureaueye.beacon.model.quotation.Quoapprove) load(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.quotation.Quoapprove load(java.lang.Integer key, Session s)
	{
		return (com.bureaueye.beacon.model.quotation.Quoapprove) load(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.quotation.Quoapprove loadInitialize(java.lang.Integer key, Session s) 
	{ 
		com.bureaueye.beacon.model.quotation.Quoapprove obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.quotation.Quoapprove> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.quotation.Quoapprove> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.bureaueye.beacon.model.quotation.Quoapprove> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param quoapprove a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove)
	{
		return (java.lang.Integer) super.save(quoapprove);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param quoapprove a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove, Session s)
	{
		return (java.lang.Integer) save((Object) quoapprove, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param quoapprove a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove)
	{
		saveOrUpdate((Object) quoapprove);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param quoapprove a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove, Session s)
	{
		saveOrUpdate((Object) quoapprove, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param quoapprove a transient instance containing updated state
	 */
	public void update(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove) 
	{
		update((Object) quoapprove);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param quoapprove a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove, Session s)
	{
		update((Object) quoapprove, s);
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
	 * @param quoapprove the instance to be removed
	 */
	public void delete(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove)
	{
		delete((Object) quoapprove);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param quoapprove the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.bureaueye.beacon.model.quotation.Quoapprove quoapprove, Session s)
	{
		delete((Object) quoapprove, s);
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
	public void refresh (com.bureaueye.beacon.model.quotation.Quoapprove quoapprove, Session s)
	{
		refresh((Object) quoapprove, s);
	}


}