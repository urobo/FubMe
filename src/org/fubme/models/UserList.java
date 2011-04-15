/**
 * 
 */
package org.fubme.models;

import java.util.List;

/**
 * @author riccardo
 * 
 */
public class UserList {

	protected User owner;
	protected String id;
	protected List<User> userlisted;

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the userlisted
	 */
	public List<User> getUserlisted() {
		return userlisted;
	}

	/**
	 * @param userlisted
	 *            the userlisted to set
	 */
	public void setUserlisted(List<User> userlisted) {
		this.userlisted = userlisted;
	}

}
