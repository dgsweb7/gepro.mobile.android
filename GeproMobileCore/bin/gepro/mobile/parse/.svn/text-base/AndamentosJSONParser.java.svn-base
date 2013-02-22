package gepro.mobile.parse;

import org.json.JSONException;
import org.json.JSONObject;

import gepro.mobile.dto.Andamento;
import gepro.mobile.util.Util;

public class AndamentosJSONParser {

	public static Andamento fromJson(JSONObject jobj) throws JSONException {

		Andamento mov = new Andamento();

		if (jobj.has("ID"))
			if (jobj.get("ID") != JSONObject.NULL)
				mov.setId(jobj.getInt("ID"));

		if (jobj.has("Responsavel"))
			if (jobj.get("Responsavel") != JSONObject.NULL)
				mov.setResponsavel(jobj.getString("Responsavel"));

		if (jobj.has("Tipo"))
			if (jobj.get("Tipo") != JSONObject.NULL)
				mov.setTipo(TipoJSONParser.fromJson((JSONObject) jobj
						.get("Tipo")));

		if (jobj.has("Data"))
			if (jobj.get("Data") != JSONObject.NULL)
				mov.setData(jobj.getString("Data"));

		if (jobj.has("Descricao"))
			if (jobj.get("Descricao") != JSONObject.NULL)
				mov.setDescricao(jobj.getString("Descricao"));

		if (jobj.has("DesdobramentoID"))
			if (jobj.get("DesdobramentoID") != JSONObject.NULL)
				mov.setDesdobramentoID(jobj.getInt("DesdobramentoID"));

		return mov;

	}

	public static Andamento fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static JSONObject toJson(Andamento obj) throws JSONException {
		JSONObject jobj = new JSONObject();

		jobj.put("ID", obj.getId());
		jobj.put("Tipo", TipoJSONParser.toJson(obj.getTipo()));
		jobj.put("PastaID", obj.getPastaId());
		jobj.put("DesdobramentoID", obj.getDesdobramentoID());
		jobj.put("Data", obj.getData());
		jobj.put("Responsavel", obj.getResponsavel());
		jobj.put("Descricao", obj.getDescricao());

		return jobj;

	}

}
