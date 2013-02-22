package gepro.mobile.parse;

import org.json.JSONException;
import org.json.JSONObject;

import gepro.mobile.dto.Agenda;
import gepro.mobile.util.Util;

public class AgendaJSONParser {

	public static Agenda fromJson(JSONObject jobj) throws JSONException {

		Agenda agenda = new Agenda();
		
		if (jobj.has("ID") && jobj.get("ID") != JSONObject.NULL)
			agenda.setId(jobj.getInt("ID"));

		if (jobj.has("Titulo") && jobj.get("Titulo") != JSONObject.NULL)
			agenda.setTitulo(jobj.getString("Titulo"));

		if (jobj.has("Descricao") && jobj.get("Descricao") != JSONObject.NULL)
			agenda.setDescricao(jobj.getString("Descricao"));

		if (jobj.has("DataInicial") && jobj.get("DataInicial") != JSONObject.NULL)
			agenda.setDataInicial(jobj.getString("DataInicial"));

		if (jobj.has("HoraInicial") && jobj.get("HoraInicial") != JSONObject.NULL)
			agenda.setHoraInicial(jobj.getString("HoraInicial"));

		if (jobj.has("DataFinal") && jobj.get("DataFinal") != JSONObject.NULL)
			agenda.setDataFinal(jobj.getString("DataFinal"));

		if (jobj.has("HoraFinal") && jobj.get("HoraFinal") != JSONObject.NULL)
			agenda.setHoraFinal(jobj.getString("HoraFinal"));

		if (jobj.has("Local") && jobj.get("Local") != JSONObject.NULL)
			agenda.setLocal(jobj.getString("Local"));

		if (jobj.has("Observacao") && jobj.get("Observacao") != JSONObject.NULL)
			agenda.setObservacao(jobj.getString("Observacao"));

		if (jobj.has("Confidencial") && jobj.get("Confidencial") != JSONObject.NULL)
			agenda.setConfidencial(jobj.getBoolean("Confidencial"));

		if (jobj.has("Particular") && jobj.get("Particular") != JSONObject.NULL)
			agenda.setParticular(jobj.getBoolean("Particular"));

		if (jobj.has("HorarioOcupado") && jobj.get("HorarioOcupado") != JSONObject.NULL)
			agenda.setHorarioOcupado(jobj.getBoolean("HorarioOcupado"));

		if (jobj.has("Realizado") && jobj.get("Realizado") != JSONObject.NULL)
			agenda.setRealizado(jobj.getBoolean("Realizado"));
		
		return agenda;

	}

	public static Agenda fromJson(String jobj) throws JSONException {
		return fromJson(new JSONObject(jobj));
	}

	public static JSONObject toJson(Agenda obj) throws JSONException {
		JSONObject jobj = new JSONObject();

		if (obj.getId() != 0)
			jobj.put("ID", obj.getId());

		if (!Util.IsNullOrEmpty(obj.getTitulo()))
			jobj.put("Titulo", obj.getTitulo());

		if (!Util.IsNullOrEmpty(obj.getDescricao()))
			jobj.put("Descricao", obj.getDescricao());

		if (!Util.IsNullOrEmpty(obj.getDataFinal()))
			jobj.put("DataFinal", obj.getDataFinal());

		if (!Util.IsNullOrEmpty(obj.getDataInicial()))
			jobj.put("DataInicial", obj.getDataInicial());
		
		if (!Util.IsNullOrEmpty(obj.getHoraInicial()))
			jobj.put("HoraInicial", obj.getHoraInicial());
		
		if (!Util.IsNullOrEmpty(obj.getHoraFinal()))
			jobj.put("HoraFinal", obj.getHoraFinal());
		

		if (!Util.IsNullOrEmpty(obj.getLocal()))
			jobj.put("Local", obj.getLocal());

		if (!Util.IsNullOrEmpty(obj.getObservacao()))
			jobj.put("Observacao", obj.getObservacao());
		
		jobj.put("Tipo", obj.getTipo() );
		jobj.put("PessoaID", obj.getPessoaID() );
		jobj.put("PastaID", obj.getPastaID() );

		jobj.put("Confidencial", obj.isConfidencial());
		jobj.put("Particular", obj.isParticular());
		jobj.put("HorarioOcupado", obj.isHorario_ocupado());
		jobj.put("Realizado", obj.isRealizado());

		return jobj;
	}

}
