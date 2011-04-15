/**
 * 
 */
package org.fubme.models;

import org.fubme.models.Interfaces.HighPrivilegedUser;

/**
 * @author riccardo
 * 
 */
public class Admin extends FubMeUser implements HighPrivilegedUser {
	public static final String[] ROLE = { "censor", "ban", "owner" };
	public static final int CENSOR = 0;
	public static final int BAN = 1;
	public static final int OWNER = 2;

	protected String rights;

	/**
	 * @param id
	 * @param pswd
	 * @param email
	 * @param rights
	 */
	public Admin(String id, String pswd, String email, int rights) {
		super(id, pswd, email);
		this.rights = ROLE[rights];
	}

	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}

	/**
	 * @param rights
	 *            the rights to set
	 */
	public void setRights(int rights) {
		this.rights = ROLE[rights];
	}

	@Override
	public void blocks(User user, String reason, int category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void censors(Post post, String reason, int category) {
		// TODO Auto-generated method stub

	}

}
