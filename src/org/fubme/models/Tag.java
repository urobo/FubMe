/**
 * 
 */
package org.fubme.models;

/**
 * @author riccardo
 * 
 */
public class Tag {
	public static final String TAG_NAME = "tag_name";

	public String name;

	public Tag(String tag_name) {
		this.name = tag_name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
