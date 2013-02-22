package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.DesdobramentoArrayAdapter;
import gepro.mobile.android.adapters.TipoArrayAdapter;
import gepro.mobile.dto.Andamento;
import gepro.mobile.dto.Desdobramento;
import gepro.mobile.dto.Tipo;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AndamentoManutAct extends Activity {

	EditText txtDescricao;
	Spinner ddlDesdobramento, ddlTipos;
	Button btnAdcionar, btnData;
	DatePicker dtpData;

	ProgressDialog pg = null;
	AlertDialog dialog = null;

	List<Desdobramento> _desdobramento = null;
	List<Tipo> _tipos = null;
	String _dataSelecionada = null;

	private static final int DATA_DIALOG = 1;
	private int dataYear, dataMonth, dataDay;

	Handler handlerData = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == GeproKeys.RETURN_CODE.OK) {
				btnData.setText(_dataSelecionada);
			}
		};
	};

	Handler handlerDesdobramento = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.FAIL) {

				Toast.makeText(AndamentoManutAct.this,
						Tools.GetMessageExeption(msg), Toast.LENGTH_LONG)
						.show();

			} else if (msg.what == GeproKeys.RETURN_CODE.NULL) {

				DesdobramentoArrayAdapter daa = new DesdobramentoArrayAdapter(
						AndamentoManutAct.this, Arrays
								.asList(new Desdobramento[0]));

				ddlDesdobramento.setAdapter(daa);

				TipoArrayAdapter taa = new TipoArrayAdapter(
						AndamentoManutAct.this, _tipos);

				ddlTipos.setAdapter(taa);

				Toast
						.makeText(
								AndamentoManutAct.this,
								"Não é possível cadastrar um andamento. pois não ha desdobramento associado",
								Toast.LENGTH_LONG).show();

			} else if (msg.what == GeproKeys.RETURN_CODE.OK) {

				DesdobramentoArrayAdapter daa = new DesdobramentoArrayAdapter(
						AndamentoManutAct.this, _desdobramento);

				ddlDesdobramento.setAdapter(daa);

				TipoArrayAdapter taa = new TipoArrayAdapter(
						AndamentoManutAct.this, _tipos);

				ddlTipos.setAdapter(taa);
			}
		};
	};

	Handler saveHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.OK) {

				setResult(GeproKeys.RETURN_CODE.OK);

				Toast.makeText(AndamentoManutAct.this,
						"Andamento inserido com sucesso.", Toast.LENGTH_LONG)
						.show();

				finish();
			}
		}
	};

	Runnable desdobramentoThread = new Runnable() {
		@Override
		public void run() {

			GeproMobileServiceProxy proxy = null;

			Desdobramento[] desd;

			Tipo[] tipos;

			try {

				proxy = new GeproMobileServiceProxy(new HttpRequest());

				String pastaid = getIntent().getExtras().getString(
						GeproKeys.PASTA_SELECIONADA);

				desd = proxy.GetDesdobramento(gepro.mobile.util.Config
						.getCurrentSession(), Integer.parseInt(pastaid));

				tipos = proxy.GetTipos(Config.getCurrentSession(),
						GeproKeys.Tabela.Contencioso_TipoAndamento);

				if (desd != null)
					_desdobramento = Arrays.asList(desd);

				if (tipos != null)
					_tipos = Arrays.asList(tipos);

				handlerDesdobramento.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

			} catch (Exception e) {
				Message msg = Tools.CreateMessageFomEx(e);
				msg.what = GeproKeys.RETURN_CODE.FAIL;
				handlerDesdobramento.sendMessage(msg);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		
		super.onCreate(savedInstanceState);

		Init();

		if (_desdobramento == null)
			LoadDesdobramento();
	}

	Runnable insertThread = new Runnable() {
		@Override
		public void run() {

			InsertAndamento();
		}
	};

	private void InsertAndamento() {
		GeproMobileServiceProxy proxy = null;

		try {

			proxy = new GeproMobileServiceProxy(new HttpRequest());

			String pastaid = getIntent().getExtras().getString(
					GeproKeys.PASTA_SELECIONADA);

			Andamento mov = new Andamento();

			Desdobramento desdo = (Desdobramento) ddlDesdobramento
					.getSelectedItem();

			Tipo tipo = (Tipo) ddlTipos.getSelectedItem();

			mov.setDescricao(txtDescricao.getText().toString());
			mov.setDesdobramentoID(desdo.getID());
			mov.setTipo(tipo);

			mov.setData(_dataSelecionada);
			mov.setPastaId(Integer.parseInt(pastaid));

			proxy.InsertAndamento(Config.getCurrentSession(), mov);

			saveHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			handlerDesdobramento.sendMessage(msg);
		}
	}

	private void LoadDesdobramento() {
		pg = ProgressDialog.show(this, null, "Aguarde...");
		Thread t = new Thread(desdobramentoThread, "GET_DESDOBRAMENTOS");
		t.start();
	}

	private void Init() {
		setContentView(R.layout.moviment_insert_layout);
		ddlDesdobramento = (Spinner) findViewById(R.id.ddl_moviment_insert_desdobramento);
		ddlTipos = (Spinner) findViewById(R.id.ddl_moviment_insert_tipo);
		txtDescricao = (EditText) findViewById(R.id.txt_moviments_insert_descricao);
		btnAdcionar = (Button) findViewById(R.id.btn_moviment_insert_salvar);
		btnAdcionar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pg = ProgressDialog.show(AndamentoManutAct.this, null,
						"Aguarde...");
				Thread t = new Thread(insertThread, "INSERT_ANDAMENTO");
				t.start();
			}
		});

		btnData = (Button) findViewById(R.id.btn_moviment_insert_data);
		btnData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DATA_DIALOG);
			}
		});

		Calendar c = Calendar.getInstance();

		dataYear = c.get(Calendar.YEAR);
		dataMonth = c.get(Calendar.MONTH);
		dataDay = c.get(Calendar.DAY_OF_MONTH);

		UpdateDataText();

	}

	private void UpdateDataText() {
		_dataSelecionada = new StringBuilder().append(
				dataDay < 10 ? "0" + dataDay : dataDay).append("-").append(
				dataMonth + 1 < 10 ? "0" + (dataMonth + 1) : dataMonth + 1)
				.append("-").append(dataYear).toString();

		btnData.setText(_dataSelecionada);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, dataListener, dataYear, dataMonth,
				dataDay);
	}

	OnDateSetListener dataListener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			dataYear = year;
			dataMonth = monthOfYear;
			dataDay = dayOfMonth;

			UpdateDataText();

		}
	};
}