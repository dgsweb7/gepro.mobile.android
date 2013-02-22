package gepro.mobile.parse;

import gepro.mobile.dto.Ged;
import gepro.mobile.dto.GedItem;

import org.json.JSONException;
import org.json.JSONObject;

public class GedItemJSONParser {

	public static GedItem fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static GedItem fromJson(JSONObject jobj) throws JSONException {

		GedItem ged = new GedItem();

		if (jobj.has("Descricao"))
			if (jobj.get("Descricao") != JSONObject.NULL)
				ged.setDescricao(jobj.getString("Descricao"));

		if (jobj.has("ID"))
			if (jobj.get("ID") != JSONObject.NULL)
				ged.setID(jobj.getInt("ID"));
		
		if (jobj.has("Size"))
			if (jobj.get("Size") != JSONObject.NULL)
				ged.setSize(jobj.getLong("Size"));

		return ged;
	}

	public static JSONObject toJson(GedItem obj) throws JSONException {
		return null;
	}
}
