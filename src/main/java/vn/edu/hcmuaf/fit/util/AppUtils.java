package vn.edu.hcmuaf.fit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class AppUtils {

	public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static Date getNow() {
		return new Date(System.currentTimeMillis());
	}

	public static String getCurrentUsername() {
		String currentUser = null;
		return currentUser;
	}
	
}
