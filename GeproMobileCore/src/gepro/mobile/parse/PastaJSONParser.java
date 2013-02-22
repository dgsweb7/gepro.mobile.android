package gepro.mobile.parse;

import org.json.JSONException;
import org.json.JSONObject;


import gepro.mobile.dto.Pasta;

public class PastaJSONParser {

	public static Pasta fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}
	public static Pasta fromJson(JSONObject jobj) throws JSONException {

		Pasta pasta = new Pasta();

		if (jobj.get("PastaID") != JSONObject.NULL)
			pasta.setPastaId(jobj.getString("PastaID"));

		if (jobj.get("NProcesso") != JSONObject.NULL)
			pasta.setNProcesso(jobj.getString("NProcesso"));

		if (jobj.get("ParteSolicitante") != JSONObject.NULL)
			pasta.setParteSolicitante(jobj.getString("ParteSolicitante"));

		if (jobj.get("ParteContraria") != JSONObject.NULL)
			pasta.setParteContrario(jobj.getString("ParteContraria"));
		
		if (jobj.get("Area") != JSONObject.NULL)
			pasta.setArea(jobj.getString("Area"));
		
		if (jobj.get("DataDistribuicao") != JSONObject.NULL)
			pasta.setDataDistribuicao(jobj.getString("DataDistribuicao"));
		
		if (jobj.get("DescricaoObjeto") != JSONObject.NULL)
			pasta.setDescricaoObjeto(jobj.getString("DescricaoObjeto"));
		
		if (jobj.get("Empresa") != JSONObject.NULL)
			pasta.setEmpresa(jobj.getString("Empresa"));
		
		if (jobj.get("Foro") != JSONObject.NULL)
			pasta.setForo(jobj.getString("Foro"));
		
		if (jobj.get("TipoAcao") != JSONObject.NULL)
			pasta.setTipoAcao(jobj.getString("TipoAcao"));
		
		if (jobj.get("Titulo") != JSONObject.NULL)
			pasta.setTitulo(jobj.getString("Titulo"));
		
		if (jobj.get("Vara") != JSONObject.NULL)
			pasta.setVara(jobj.getString("Vara"));
		
		
		return pasta;
	}

	
	public  static JSONObject toJson(Pasta obj) throws JSONException {

		return null;
	}

}
