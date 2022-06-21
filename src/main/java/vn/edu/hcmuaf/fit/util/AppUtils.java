package vn.edu.hcmuaf.fit.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public final class AppUtils {

	public static String getCurrentUsername() {
		return null;
	}

	public static String getFullName(String lastName, String firstName) {
		return lastName + " " + firstName;
	}

	public static String getVerifyEmailContent(String verifyUrl) {
		try {
			Document doc = Jsoup.parse(AppUtils.class.getClassLoader().getResourceAsStream("verify-email.html"), "UTF-8", "");
			doc.select("a.es-button").attr("href", verifyUrl);
			return doc.html();
		} catch (IOException e) {
			return null;
		}
	}

	public static String getResetPasswordContent(String fullname, String password) {
		try {
			Document doc = Jsoup.parse(AppUtils.class.getClassLoader().getResourceAsStream("reset-password.html"), "UTF-8", "");
			doc.select("span#fullname").html(fullname);
			doc.select("span#password").html(password);
			return doc.html();
		} catch (IOException e) {
			return null;
		}
	}
}
