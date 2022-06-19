package vn.edu.hcmuaf.fit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

	public static Date getNow() {
		return new Date(System.currentTimeMillis());
	}

	public static Date toDatetime(final String dateStr) {
		if (dateStr == null) return null;

		try {
			return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static Date toDate(final String dateStr) {
		if (dateStr == null) return null;

		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String toStringDatetime(final Date date) {
		if (date == null) return null;
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}


	public static String toStringDate(final Date date) {
		if (date == null) return null;
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
}
