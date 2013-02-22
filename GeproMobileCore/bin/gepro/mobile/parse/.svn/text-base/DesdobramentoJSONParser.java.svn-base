package gepro.mobile.parse;

import gepro.mobile.dto.Desdobramento;
import gepro.mobile.dto.GedItem;

import org.json.JSONException;
import org.json.JSONObject;

public class DesdobramentoJSONParser {


	public static Desdobramento fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static Desdobramento fromJson(JSONObject jobj) throws JSONException {

		Desdobramento des = new Desdobramento();

		if (jobj.has("Descricao"))
			if (jobj.get("Descricao") != JSONObject.NULL)
				des.setDescricao(jobj.getString("Descricao"));

		if (jobj.has("ID"))
			if (jobj.get("ID") != JSONObject.NULL)
				des.setID(jobj.getInt("ID"));

		return des;
	}

	public static JSONObject toJson(Desdobramento obj) throws JSONException {
		return null;
	}
}
