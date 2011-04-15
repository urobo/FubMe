/**
 * 
 */
package org.fubme.helper;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.postgresql.jdbc2.TimestampUtils;

/**
 * @author riccardo
 * 
 */
public abstract class Utils {

	private static TimestampUtils mTimeUtil = null;
	private static Calendar mCalendar = null;

	public static Date getDateFromTimestamp(Timestamp inTimestamp)
			throws SQLException {
		if (mTimeUtil == null)
			mTimeUtil = (TimestampUtils) new Object();
		if (mCalendar == null)
			mCalendar = Calendar.getInstance();
		return mTimeUtil.toDate(mCalendar, inTimestamp.toString());
	}
}
