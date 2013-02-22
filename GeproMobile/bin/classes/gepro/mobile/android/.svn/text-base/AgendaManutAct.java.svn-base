package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.TipoArrayAdapter;
import gepro.mobile.dto.Agenda;
import gepro.mobile.dto.Tipo;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.R.drawable;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AgendaManutAct extends Activity {

	ProgressDialog pg = null;

	private Spinner ddlTipoAgenda;
	private EditText txtAssunto, txtLocal, txtDescricao;
	private Button btnData, btnHoaraIni, btnHoraFim, btnPasta, btnPessoa;
	private ToggleButton tgConfidencial, tgOcupado, tgParticular, tgRealizado;

	private String pessoaID, pastaID;

	static final int DATA_DIALOG_ID = 0;
	static final int HORA_INI_DIALOG_ID = 1;
	static final int HORA_FIM_DIALOG_ID = 2;
	static final int AGUARDE_DIALOG = 3;

	static final int CALL_PESSOA = 4;
	static final int CALL_PASTA = 5;

	private String dataCompromisso, horaInicial, horaFinal;
	private int dataYear, dataMonth, dataDay;

	private int horaInicioHoras, horaInicioMinutos, horaFimHoras,
			horaFimMinutos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agenda_manut);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		
		Init();

		LoadTipoAgenda();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		super.onCreateDialog(id);

		switch (id) {
		case DATA_DIALOG_ID:
			return new DatePickerDialog(this, dataCompromissoListener,
					dataYear, dataMonth, dataDay);
		case HORA_INI_DIALOG_ID:
			return new TimePickerDialog(this, HoraInicioListener,
					horaInicioHoras, horaInicioMinutos, true);
		case HORA_FIM_DIALOG_ID:
			return new TimePickerDialog(this, HoraFimListener, horaFimHoras,
					horaFimMinutos, true);
		}

		return null;
	}

	private OnTimeSetListener HoraInicioListener = new OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horaInicioHoras = hourOfDay;
			horaInicioMinutos = minute;

			UpdateHoraIniText();
		}
	};

	private OnTimeSetListener HoraFimListener = new OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horaFimHoras = hourOfDay;
			horaFimMinutos = minute;

			UpdateHoraFimText();
		}
	};

	private OnDateSetListener dataCompromissoListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			dataYear = year;
			dataMonth = monthOfYear;
			dataDay = dayOfMonth;

			UpdateDataText();
		}
	};

	private void UpdateDataText() {
		dataCompromisso = new StringBuilder().append(
				dataDay < 10 ? "0" + dataDay : dataDay).append("-").append(
				dataMonth + 1 < 10 ? "0" + (dataMonth + 1) : dataMonth + 1)
				.append("-").append(dataYear).toString();

		btnData.setText(dataCompromisso);

	}

	private void UpdateHoraIniText() {
		horaInicial = new StringBuilder().append(
				horaInicioHoras < 10 ? "0" + horaInicioHoras : horaInicioHoras
						+ "").append(":").append(
				horaInicioMinutos < 10 ? "0" + horaInicioMinutos : ""
						+ horaInicioMinutos).toString();

		btnHoaraIni.setText(horaInicial);

	}

	private void UpdateHoraFimText() {
		horaFinal = new StringBuilder().append(
				horaFimHoras < 10 ? "0" + horaFimHoras : horaFimHoras + "")
				.append(":").append(
						horaFimMinutos < 10 ? "0" + horaFimMinutos : ""
								+ horaInicioMinutos).toString();

		btnHoraFim.setText(horaFinal);
	}

	private void Init() {

		Calendar c = Calendar.getInstance();
		dataDay = c.get(Calendar.DAY_OF_MONTH);
		dataMonth = c.get(Calendar.MONTH);
		dataYear = c.get(Calendar.YEAR);
		horaInicioHoras = c.get(Calendar.HOUR_OF_DAY);
		horaInicioMinutos = c.get(Calendar.MINUTE);

		horaFimHoras = horaInicioHoras + 1;
		horaFimMinutos = horaInicioMinutos;

		ddlTipoAgenda = (Spinner) findViewById(R.id.ddl_agenda_manut_tipo);
		btnData = (Button) findViewById(R.id.btn_agenda_manut_data_inicio);
		btnData.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATA_DIALOG_ID);
			}
		});

		btnHoaraIni = (Button) findViewById(R.id.btn_agenda_manut_hora_inicio);
		btnHoaraIni.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(HORA_INI_DIALOG_ID);
			}
		});

		btnHoraFim = (Button) findViewById(R.id.btn_agenda_manut_hora_fim);
		btnHoraFim.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(HORA_FIM_DIALOG_ID);
			}
		});

		btnPasta = (Button) findViewById(R.id.btn_agenda_manut_pasta);
		btnPasta.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent itn = new Intent(AgendaManutAct.this,
						PastaTipoAct.class);
				itn.setAction(Intent.ACTION_PICK_ACTIVITY);
				AgendaManutAct.this.startActivityForResult(itn, CALL_PASTA);

			}
		});

		btnPessoa = (Button) findViewById(R.id.btn_agenda_manut_pessoa);
		btnPessoa.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent itn = new Intent(AgendaManutAct.this,
						PessoasPerfilAct.class);
				itn.setAction(Intent.ACTION_PICK_ACTIVITY);
				AgendaManutAct.this.startActivityForResult(itn, CALL_PESSOA);

			}
		});

		txtAssunto = (EditText) findViewById(R.id.txt_agenda_manut_assunto);
		txtDescricao = (EditText) findViewById(R.id.txt_agenda_manut_descricao);
		txtLocal = (EditText) findViewById(R.id.txt_agenda_manut_local);

		tgConfidencial = (ToggleButton) findViewById(R.id.tg_agenda_manut_confidencial);
		tgOcupado = (ToggleButton) findViewById(R.id.tg_agenda_manut_ocupado);
		tgParticular = (ToggleButton) findViewById(R.id.tg_agenda_manut_particular);
		tgRealizado = (ToggleButton) findViewById(R.id.tg_agenda_manut_realizado);

		UpdateDataText();
		UpdateHoraIniText();
		UpdateHoraFimText();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == CALL_PASTA && resultCode == RESULT_OK) {
			pastaID = data.getStringExtra(GeproKeys.PASTA_SELECIONADA);
			String numero = data.getStringExtra(GeproKeys.PASTA_SELECIONADA_NUMERO);
			if (!Util.IsNullOrEmpty(pastaID)) {
				btnPasta.setText(pastaID  + " - " + numero);
			}
		} else if (requestCode == CALL_PESSOA && resultCode == RESULT_OK) {
			pessoaID = data.getStringExtra(GeproKeys.SELECTED_CUSTOMER_ID);
			String nome = data.getStringExtra(GeproKeys.PERFIL_PESSOA_SELECIONADO_NOME);
			if (!Util.IsNullOrEmpty(pessoaID)) {
				btnPessoa.setText(pessoaID + "-"  + nome);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuItem item = menu.add("Salvar");
		item.setIcon(R.drawable.salvar);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				pg = ProgressDialog.show(AgendaManutAct.this, null,
						"Aguarde...");
				Thread t = new Thread(InsertAgenda);
				t.start();
				return true;
			}
		});

		item = menu.add("Cancelar");
		item.setIcon(R.drawable.cancelar);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				finish();
				return true;
			}
		});

		return true;
	}

	Runnable InsertAgenda = new Runnable() {
		@Override
		public void run() {

			Message msgValidate = new Message();
			msgValidate.what = GeproKeys.RETURN_CODE.INVALID;

			if (Util.IsNullOrEmpty(txtAssunto.getText().toString())) {
				msgValidate.obj = "O campo Assunto é obrigatório.";
				AgendaInsertHandler.sendMessage(msgValidate);
				return;
			}

			if (Util.IsNullOrEmpty(dataCompromisso)) {
				msgValidate.obj = "A data do compromisso  é obrigatória.";
				AgendaInsertHandler.sendMessage(msgValidate);
				return;
			}

			if (horaFimHoras < horaInicioHoras
					|| (horaFimHoras >= horaInicioHoras && horaFimMinutos < horaInicioMinutos)) {
				msgValidate.obj = "A data final do compromisso não pode ser menor que a data inicial.";
				AgendaInsertHandler.sendMessage(msgValidate);
				return;
			}

			try {

				Tipo tipo = (Tipo) ddlTipoAgenda.getSelectedItem();
				Agenda agenda = new Agenda();
				agenda.setTitulo(txtAssunto.getText().toString());
				agenda.setDescricao(txtDescricao.getText().toString());
				agenda.setDataFinal(dataCompromisso);
				agenda.setDataInicial(dataCompromisso);
				agenda.setHoraInicial(horaInicial);
				agenda.setHoraFinal(horaFinal);
				agenda.setParticular(tgParticular.isChecked());
				agenda.setConfidencial(tgConfidencial.isChecked());
				agenda.setHorarioOcupado(tgOcupado.isChecked());
				agenda.setRealizado(tgOcupado.isChecked());
				agenda.setLocal(txtLocal.getText().toString());
				agenda.setTipo(tipo.getID());

				if (Util.IsNullOrEmpty(pessoaID))
					agenda.setPessoaID(pessoaID);

				if (Util.IsNullOrEmpty(pastaID))
					agenda.setPastaID(pastaID);

				GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
						new HttpRequest());

				proxy.InsertAgenda(
						gepro.mobile.util.Config.getCurrentSession(), agenda);

				AgendaInsertHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

			} catch (Exception e) {
				Message msg = Tools.CreateMessageFomEx(e);
				msg.what = GeproKeys.RETURN_CODE.FAIL;
				AgendaInsertHandler.sendMessage(msg);
			}
		}
	};

	private void LoadTipoAgenda() {

		List<Tipo> lstTipoAgenda = null;

		GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
				new HttpRequest());
		try {

			Tipo[] _t = proxy.GetTipos(gepro.mobile.util.Config
					.getCurrentSession(), GeproKeys.Tabela.Agenda_Tipo);

			if (_t != null) {
				lstTipoAgenda = Arrays.asList(_t);
				TipoArrayAdapter tad = new TipoArrayAdapter(this, lstTipoAgenda);
				ddlTipoAgenda.setAdapter(tad);
			}

		} catch (Exception e) {
			Toast.makeText(AgendaManutAct.this,
					"Occorreu um erro durante a busca de tipos de Agenda ",
					Toast.LENGTH_LONG).show();
		}
	}

	private Handler AgendaInsertHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {

			try {

				if (pg != null)
					pg.hide();

				if (msg.what == GeproKeys.RETURN_CODE.NULL) {

				} else if (msg.what == GeproKeys.RETURN_CODE.OK) {
					Toast.makeText(AgendaManutAct.this,
							"Agenda incluida com sucesso.", Toast.LENGTH_LONG)
							.show();
					setResult(RESULT_OK);
					finish();

				} else if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
					Toast.makeText(AgendaManutAct.this,
							"Occorreu um erro durante a inclusão da Agenda ",
							Toast.LENGTH_LONG).show();
				}
				else if (msg.what == GeproKeys.RETURN_CODE.INVALID) {
					Toast.makeText(AgendaManutAct.this, (String)msg.obj  ,
							Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(AgendaManutAct.this,
						"Occorreu um erro durante a inclusão da Agenda ",
						Toast.LENGTH_LONG).show();
			}
		};
	};

}
