package gepro.mobile.parse;

import gepro.mobile.dto.Ged;
import gepro.mobile.dto.GedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GedJSONParser {

	public static Ged fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static Ged fromJson(JSONObject jobj) throws JSONException {

		Ged ged = new Ged();

		if (jobj.has("TipoDocumento"))
			if (jobj.get("TipoDocumento") != JSONObject.NULL)
				ged.setTipoDocumento(  jobj.getString("TipoDocumento") );

		if (jobj.has("Descricao"))
			if (jobj.get("Descricao") != JSONObject.NULL)
				ged.setDescricao(jobj.getString("Descricao"));

		if (jobj.has("ID"))
			if (jobj.get("ID") != JSONObject.NULL)
				ged.setID(jobj.getInt("ID"));

		if (jobj.has("Items"))
			if (jobj.get("Items") != JSONObject.NULL) {

				JSONArray array = jobj.getJSONArray("Items");
				GedItem[] geditem = new GedItem[array.length()];

				for (int i = 0; i < array.length(); i++) {
					geditem[i] = GedItemJSONParser.fromJson(array
							.getJSONObject(i));
				}

				ged.setGedIems(geditem);
			}

		return ged;
	}

	public static JSONObject toJson(Ged obj) throws JSONException {
		return null;
	}

}
