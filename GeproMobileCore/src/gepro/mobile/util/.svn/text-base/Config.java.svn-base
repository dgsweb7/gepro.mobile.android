package gepro.mobile.util;

import gepro.mobile.dto.Session;

public class Config {
	private static Session currentSession;
	private static String CacheDir;
	private static String ThumbsDir;
	private static String DownloadDir;

	public static String getCacheDir() {
		return CacheDir;
	}

	public static void setCacheDir(String cacheDir) {
		CacheDir = cacheDir;
	}

	public static String getThumbsDir() {
		return ThumbsDir;
	}

	public static void setThumbsDir(String thumbsDir) {
		ThumbsDir = thumbsDir;
	}

	public static String getDownloadDir() {
		return DownloadDir;
	}

	public static void setDownloadDir(String downloadDir) {
		DownloadDir = downloadDir;
	}

	public static void setCurrentSession(Session currentSession,
			String ApplicationID) {
		if (currentSession != null) {
			currentSession.setApplicationID(ApplicationID);
		}

		Config.currentSession = currentSession;
	}

	public static Session getCurrentSession() {
		return currentSession;
	}

}
