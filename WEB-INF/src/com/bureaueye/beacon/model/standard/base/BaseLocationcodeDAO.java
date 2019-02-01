package com.bureaueye.beacon.model.standard.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.bureaueye.beacon.model.standard.dao.iface.LocationcodeDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseLocationcodeDAO extends com.bureaueye.beacon.model.standard.dao._RootDAO {

	public BaseLocationcodeDAO () {}
	
	public BaseLocationcodeDAO (Session session) {
		super(session);
	}

	// query name references
	public static final String QUERY_FIND_LOCATIONCODES_BY_COUNTRYKEY = "findLocationcodesByCountrykey";


	public static LocationcodeDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static LocationcodeDAO getInstance () {
		if (null == instance) instance = new com.bureaueye.beacon.model.standard.dao.LocationcodeDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.bureaueye.beacon.model.standard.Locationcode.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.bureaueye.beacon.model.standard.Locationcode
	 */
	public com.bureaueye.beacon.model.standard.Locationcode cast (Object object) {
		return (com.bureaueye.beacon.model.standard.Locationcode) object;
	}

	public com.bureaueye.beacon.model.standard.Locationcode get(java.lang.String key)
	{
		return (com.bureaueye.beacon.model.standard.Locationcode) get(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.standard.Locationcode get(java.lang.String key, Session s)
	{
		return (com.bureaueye.beacon.model.standard.Locationcode) get(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.standard.Locationcode load(java.lang.String key)
	{
		return (com.bureaueye.beacon.model.standard.Locationcode) load(getReferenceClass(), key);
	}

	public com.bureaueye.beacon.model.standard.Locationcode load(java.lang.String key, Session s)
	{
		return (com.bureaueye.beacon.model.standard.Locationcode) load(getReferenceClass(), key, s);
	}

	public com.bureaueye.beacon.model.standard.Locationcode loadInitialize(java.lang.String key, Session s) 
	{ 
		com.bureaueye.beacon.model.standard.Locationcode obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Locationcode> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Locationcode> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.bureaueye.beacon.model.standard.Locationcode> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param locationcode a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.bureaueye.beacon.model.standard.Locationcode locationcode)
	{
		return (java.lang.String) super.save(locationcode);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param locationcode a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.bureaueye.beacon.model.standard.Locationcode locationcode, Session s)
	{
		return (java.lang.String) save((Object) locationcode, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param locationcode a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.standard.Locationcode locationcode)
	{
		saveOrUpdate((Object) locationcode);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param locationcode a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.bureaueye.beacon.model.standard.Locationcode locationcode, Session s)
	{
		saveOrUpdate((Object) locationcode, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param locationcode a transient instance containing updated state
	 */
	public void update(com.bureaueye.beacon.model.standard.Locationcode locationcode) 
	{
		update((Object) locationcode);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param locationcode a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.bureaueye.beacon.model.standard.Locationcode locationcode, Session s)
	{
		update((Object) locationcode, s);
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
	 * @param locationcode the instance to be removed
	 */
	public void delete(com.bureaueye.beacon.model.standard.Locationcode locationcode)
	{
		delete((Object) locationcode);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param locationcode the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.bureaueye.beacon.model.standard.Locationcode locationcode, Session s)
	{
		delete((Object) locationcode, s);
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
	public void refresh (com.bureaueye.beacon.model.standard.Locationcode locationcode, Session s)
	{
		refresh((Object) locationcode, s);
	}


}