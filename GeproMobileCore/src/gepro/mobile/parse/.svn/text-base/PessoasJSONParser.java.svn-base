package gepro.mobile.parse;

import gepro.mobile.dto.Pessoa;
import gepro.mobile.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class PessoasJSONParser {

	public static Pessoa fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static Pessoa fromJson(JSONObject jobj) throws JSONException {

		Pessoa cust = new Pessoa();
		if (jobj.has("ID"))
			if (jobj.get("ID") != JSONObject.NULL)
				cust.setID(jobj.getInt("ID"));

		if (jobj.has("Nome"))
			if (jobj.get("Nome") != JSONObject.NULL)
				cust.setNome(jobj.getString("Nome"));

		if (jobj.has("Telefone"))
			if (jobj.get("Telefone") != JSONObject.NULL)
				cust.setTelefone(jobj.getString("Telefone"));

		if (jobj.has("WebSite"))
			if (jobj.get("WebSite") != JSONObject.NULL)
				cust.setWebSite(jobj.getString("WebSite"));

		if (jobj.has("Email"))
			if (jobj.get("Email") != JSONObject.NULL)
				cust.setEmail(jobj.getString("Email"));

		if (jobj.has("RamoAtividade"))
			if (jobj.get("RamoAtividade") != JSONObject.NULL)
				cust.setRamoAtividade(jobj.getString("RamoAtividade"));

		return cust;
	}

	public static JSONObject toJson(Pessoa obj) throws JSONException {
		JSONObject jobj = new JSONObject();

		if (obj.getID() != 0)
			jobj.put("ID", obj.getID());

		if (!Util.IsNullOrEmpty(obj.getNome()))
			jobj.put("Name", obj.getNome());

		if (!Util.IsNullOrEmpty(obj.getTelefone()))
			jobj.put("Phone", obj.getTelefone());

		return jobj;

	}

}
