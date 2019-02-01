package com.bureaueye.beacon.model.standard.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the addrtype table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="addrtype"
 */

public abstract class BaseAddrtype  implements Serializable {

	public static String REF = "Addrtype";
	public static String PROP_TYPEKEY = "Typekey";


	// constructors
	public BaseAddrtype () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAddrtype (java.lang.String typekey) {
		this.setTypekey(typekey);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String typekey;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="typekey"
     */
	public java.lang.String getTypekey () {
		return typekey;
	}

	/**
	 * Set the unique identifier of this class
	 * @param typekey the new ID
	 */
	public void setTypekey (java.lang.String typekey) {
		this.typekey = typekey;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.bureaueye.beacon.model.standard.Addrtype)) return false;
		else {
			com.bureaueye.beacon.model.standard.Addrtype addrtype = (com.bureaueye.beacon.model.standard.Addrtype) obj;
			if (null == this.getTypekey() || null == addrtype.getTypekey()) return false;
			else return (this.getTypekey().equals(addrtype.getTypekey()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getTypekey()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getTypekey().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}