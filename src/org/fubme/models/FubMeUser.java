/**
 * 
 */
package org.fubme.models;

/**
 * @author riccardo
 * 
 */
public abstract class FubMeUser {
	protected String id;
	protected String pswd;
	protected String email;

	/**
	 * @param id
	 * @param pswd
	 * @param email
	 */
	public FubMeUser(String id, String pswd, String email) {
		super();
		this.id = id;
		this.pswd = pswd;
		this.email = email;
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
	 * @return the pswd
	 */
	public String getPswd() {
		return pswd;
	}

	/**
	 * @param pswd
	 *            the pswd to set
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
