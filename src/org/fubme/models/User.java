/**
 * 
 */
package org.fubme.models;

import java.util.Date;
import java.util.List;

import org.fubme.models.Interfaces.LowPrivilegedUser;

/**
 * @author riccardo
 * 
 */
public class User extends FubMeUser implements LowPrivilegedUser {

	protected String bio;
	protected String firstname;
	protected String lastname;
	protected Date birthdate;
	protected String location;

	public User(String id, String pswd, String email) {
		super(id, pswd, email);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio
	 *            the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void likes(Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void comments(String body, Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public void follows(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void lists(User user, UserList id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shares(Post post, User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reports(User user, String reason, int category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reports(Post post, User user, String reason, int category) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tags(Post post, List<Tag> tags) {
		// TODO Auto-generated method stub

	}

}
