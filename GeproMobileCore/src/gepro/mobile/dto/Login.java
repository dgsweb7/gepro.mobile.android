package gepro.mobile.dto;

public class Login {

	private String ConsumerID;
	private String User;
	private String Password;
	private String CultureInfo;

	public void setUser(String user) {
		User = user;
	}

	public String getUser() {
		return User;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPassword() {
		return Password;
	}

	public String getCultureInfo() {
		return CultureInfo;
	}

	public void setCultureInfo(String cultureInfo) {
		CultureInfo = cultureInfo;
	}

	public void setConsumerID(String consumerID) {
		ConsumerID = consumerID;
	}

	public String getConsumerID() {
		return ConsumerID;
	}

}
