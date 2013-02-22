package gepro.mobile.parse;

import org.json.JSONException;
import org.json.JSONObject;

import gepro.mobile.dto.Tipo;

public class TipoJSONParser {

	public static Tipo fromJson(JSONObject jobj) throws JSONException {

		Tipo type = new Tipo();

		if (jobj.has("Descricao"))
			if (jobj.get("Descricao") != JSONObject.NULL)
				type.setDescricao(jobj.getString("Descricao"));

		if (jobj.has("ID"))
			if (jobj.get("ID") != JSONObject.NULL)
				type.setID(jobj.getString("ID"));

		return type;
	}

	public static Tipo fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static JSONObject toJson(Tipo obj) throws JSONException {

		JSONObject jobj = new JSONObject();
		jobj.put("ID", obj.getID()  );
		jobj.put("Descricao", obj.getDescricao() );
		
		return jobj;
	}

}
