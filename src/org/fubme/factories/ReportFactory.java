/**
 * 
 */
package org.fubme.factories;

import org.fubme.models.Report;

/**
 * @author riccardo
 * 
 */
public final class ReportFactory {
	public static final Report getReport(int postId, String whistleblowerId,
			String wrongdoingId, String category, String reason) {
		Report report = new Report();
		report.setCategory(category);
		report.setPostId(postId);
		report.setReason(reason);
		report.setWhistleblowerId(whistleblowerId);
		report.setWrongdoingId(wrongdoingId);
		return report;
	}
}
