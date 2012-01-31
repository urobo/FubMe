/**
 * 
 */
package org.fubme.models;

import java.sql.Timestamp;

/**
 * @author riccardo
 * 
 */
public class Report {
	public static String[] CATEGORYTYPES = { "offensive", "spam", "racist" };
	public static int OFFENSIVE = 0;
	public static int SPAM = 1;
	public static int RACIST = 2;

	protected int id;
	protected Timestamp reportDate;
	protected int postId;
	protected String whistleblowerId;
	protected String wrongdoingId;
	protected String category;
	protected String reason;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the reportDate
	 */
	public Timestamp getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate
	 *            the reportDate to set
	 */
	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * @return the postId
	 */
	public int getPostId() {
		return postId;
	}

	/**
	 * @param postId
	 *            the postId to set
	 */
	public void setPostId(int postId) {
		this.postId = postId;
	}

	/**
	 * @return the whistleblowerId
	 */
	public String getWhistleblowerId() {
		return whistleblowerId;
	}

	/**
	 * @param whistleblowerId
	 *            the whistleblowerId to set
	 */
	public void setWhistleblowerId(String whistleblowerId) {
		this.whistleblowerId = whistleblowerId;
	}

	/**
	 * @return the wrongdoingId
	 */
	public String getWrongdoingId() {
		return wrongdoingId;
	}

	/**
	 * @param wrongdoingId
	 *            the wrongdoingId to set
	 */
	public void setWrongdoingId(String wrongdoingId) {
		this.wrongdoingId = wrongdoingId;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {

		this.category = category;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

}
