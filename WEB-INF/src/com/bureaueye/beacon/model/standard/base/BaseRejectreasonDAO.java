package com.bureaueye.beacon.model.standard.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.bureaueye.beacon.model.standard.dao.iface.RejectreasonDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseRejectreasonDAO extends com.bureaueye.beacon.model.standard.dao._RootDAO {

	public BaseRejectreasonDAO () {}
	
	public BaseRejectreasonDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_REJECTREASONS = "findRejectreasons";


	public static RejectreasonDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static RejectreasonDAO getInstance () {
		if (null == instance) instance = new com.bureaueye.beacon.model.standard.dao.RejectreasonDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.bureaueye.beacon.model.standard.Rejectreason.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.bureaueye.beacon.model.standard.Rejectreason
	 */
	public com.bureaueye.beacon.model.standard.Rejectreason cast (Object object) {
		return (com.bureaueye.beacon.model.standard.Rejectreason) object;
	}

	public com.bureaueye.beacon.model.standard.Rejectreason get(java.lang.String key)
	{
		return (com.bureaueye.beacon.model.standard.Rejectreason) get(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.standard.Rejectreason get(java.lang.String key, Session s)
	{
		return (com.bureaueye.beacon.model.standard.Rejectreason) get(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.standard.Rejectreason load(java.lang.String key)
	{
		return (com.bureaueye.beacon.model.standard.Rejectreason) load(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.standard.Rejectreason load(java.lang.String key, Session s)
	{
		return (com.bureaueye.beacon.model.standard.Rejectreason) load(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.standard.Rejectreason loadInitialize(java.lang.String key, Session s) 
	{ 
		com.bureaueye.beacon.model.standard.Rejectreason obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Rejectreason> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Rejectreason> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Rejectreason> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param rejectreason a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.bureaueye.beacon.model.standard.Rejectreason rejectreason)
	{
		return (java.lang.String) super.save(rejectreason);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param rejectreason a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.bureaueye.beacon.model.standard.Rejectreason rejectreason, Session s)
	{
		return (java.lang.String) save((Object) rejectreason, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param rejectreason a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.standard.Rejectreason rejectreason)
	{
		saveOrUpdate((Object) rejectreason);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param rejectreason a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.standard.Rejectreason rejectreason, Session s)
	{
		saveOrUpdate((Object) rejectreason, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param rejectreason a transient instance containing updated state
	 */
	public void update(com.bureaueye.beacon.model.standard.Rejectreason rejectreason) 
	{
		update((Object) rejectreason);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param rejectreason a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.bureaueye.beacon.model.standard.Rejectreason rejectreason, Session s)
	{
		update((Object) rejectreason, s);
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
	 * @param rejectreason the instance to be removed
	 */
	public void delete(com.bureaueye.beacon.model.standard.Rejectreason rejectreason)
	{
		delete((Object) rejectreason);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param rejectreason the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.bureaueye.beacon.model.standard.Rejectreason rejectreason, Session s)
	{
		delete((Object) rejectreason, s);
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
	public void refresh (com.bureaueye.beacon.model.standard.Rejectreason rejectreason, Session s)
	{
		refresh((Object) rejectreason, s);
	}


}