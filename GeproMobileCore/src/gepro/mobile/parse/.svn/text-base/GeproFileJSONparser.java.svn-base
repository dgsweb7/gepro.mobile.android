package gepro.mobile.parse;

import gepro.mobile.dto.GeproFile;
import gepro.mobile.dto.Tipo;
import gepro.mobile.util.Base64Coder;
import gepro.mobile.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class GeproFileJSONparser {

	public static GeproFile fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static GeproFile fromJson(JSONObject jobj) throws JSONException {
		GeproFile file = new GeproFile();

		if (jobj.has("Nome"))
			if (jobj.get("Nome") != JSONObject.NULL)
				file.setNome(jobj.getString("Nome"));

		if (jobj.has("filebase64"))
			if (jobj.get("filebase64") != JSONObject.NULL) {
				String file64 = jobj.getString("filebase64");
				file.setFileContent(Base64Coder.decode(file64));

			}

		return file;

	}

	public static JSONObject toJson(GeproFile obj) throws JSONException {
		JSONObject jobj = new JSONObject();

		if (!Util.IsNullOrEmpty(obj.getNome()))
			jobj.put("Nome", obj.getNome());

		if (!Util.IsNullOrEmpty(obj.getDescricao()))
			jobj.put("Descricao", obj.getDescricao());

		if (!Util.IsNullOrEmpty(obj.getPalaavraChave()))
			jobj.put("PalavraChave", obj.getPalaavraChave());

		jobj.put("Tipo", obj.getTipo());

		if (!Util.IsNullOrEmpty(obj.getPastaID()))
			jobj.put("PastaID", obj.getPastaID());

		if (obj.getFileContent() != null)
			jobj.put("filebase64",
					new String(Base64Coder.encode(obj.getFileContent())));

		return jobj;
	}

}
