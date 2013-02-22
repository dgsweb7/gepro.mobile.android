package gepro.mobile.parse;

import org.json.JSONException;
import org.json.JSONObject;

import gepro.mobile.dto.Login;
import gepro.mobile.util.Util;

public class LoginJSONParser {

	public static Login fromJson(JSONObject jobj) throws JSONException {

		Login login = new Login();

		if (jobj.has("User"))
			if (jobj.get("User") != JSONObject.NULL)
				login.setUser(jobj.getString("User"));

		if (jobj.has("Password"))
			if (jobj.get("Password") != JSONObject.NULL)
				login.setPassword(jobj.getString("Password"));

		if (jobj.has("ConsumerID"))
			if (jobj.get("ConsumerID") != JSONObject.NULL)
				login.setConsumerID(jobj.getString("ConsumerID"));

		if (jobj.has("CultureInfo"))
			if (jobj.get("CultureInfo") != JSONObject.NULL)
				login.setCultureInfo(jobj.getString("CultureInfo"));
		
		return login;
	}

	public static JSONObject toJson(Login obj) throws JSONException {

		JSONObject jobj = new JSONObject();

		if (!Util.IsNullOrEmpty(obj.getUser()))
			jobj.put("User", obj.getUser());

		if (!Util.IsNullOrEmpty(obj.getPassword()))
			jobj.put("Password", obj.getPassword());

		if (!Util.IsNullOrEmpty(obj.getPassword()))
			jobj.put("ConsumerID", obj.getConsumerID());

		if (!Util.IsNullOrEmpty(obj.getPassword()))
			jobj.put("CultureInfo", obj.getCultureInfo());

		return jobj;
	}

	public static Login fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

}
