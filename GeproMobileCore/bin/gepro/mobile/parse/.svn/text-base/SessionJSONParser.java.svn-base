package gepro.mobile.parse;

import org.json.JSONException;
import org.json.JSONObject;

import gepro.mobile.dto.Session;
import gepro.mobile.util.Util;

public class SessionJSONParser {


	public static Session fromJson(JSONObject jobj) throws JSONException {

		Session session = new Session();
		
		if (jobj.get("User") != JSONObject.NULL)
			session.setUser(jobj.getString("User"));

		if (jobj.get("UserID") != JSONObject.NULL)
			session.setUserID(jobj.getString("UserID"));
		
		if (jobj.get("ConsumerID") != JSONObject.NULL)
			session.setConsumerID(jobj.getString("ConsumerID"));

		if (jobj.get("Expire") != JSONObject.NULL)
			session.setExpire(jobj.getString("Expire"));

		if (jobj.get("LastTimeLogin") != JSONObject.NULL)
			session.setLastTimeLogin(jobj.getString("LastTimeLogin"));
		
		
		return session;
	}
	public static Session fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static JSONObject toJson(Session obj) throws JSONException {
		
		JSONObject jobj = new JSONObject();
		
		if (!Util.IsNullOrEmpty(obj.getUser()))
			jobj.put("User", obj.getUser());
		
		if (!Util.IsNullOrEmpty(obj.getUserID()))
			jobj.put("UserID", obj.getUserID());

		if (!Util.IsNullOrEmpty(obj.getConsumerID()))
			jobj.put("Token", obj.getConsumerID());

		if (!Util.IsNullOrEmpty(obj.getExpire()))
			jobj.put("Expire", obj.getExpire());

		if (!Util.IsNullOrEmpty(obj.getLastTimeLogin()))
			jobj.put("LastTimeLogin", obj.getLastTimeLogin());
		
		return jobj;
	}

}
