package gepro.mobile.dto;


public class Session{


	private String User;
	private String UserID;
	private String ConsumerID;
	private String LastTimeLogin;
	private String Expire;
	private String ApplicationID;

	public String getUser() {
		return User;
	}

	public String getApplicationID() {
		return ApplicationID;
	}

	public void setApplicationID(String applicationID) {
		ApplicationID = applicationID;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getLastTimeLogin() {
		return LastTimeLogin;
	}

	public void setLastTimeLogin(String lastTimeLogin) {
		LastTimeLogin = lastTimeLogin;
	}

	public String getExpire() {
		return Expire;
	}

	public void setExpire(String expire) {
		Expire = expire;
	}

	public void setConsumerID(String consumerID) {
		ConsumerID = consumerID;
	}

	public String getConsumerID() {
		return ConsumerID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getUserID() {
		return UserID;
	}

	

	
}
