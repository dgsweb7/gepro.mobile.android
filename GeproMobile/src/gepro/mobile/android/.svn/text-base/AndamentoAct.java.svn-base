package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.DesdobramentoArrayAdapter;
import gepro.mobile.android.adapters.AndamentosExpandListAdapter;
import gepro.mobile.dto.Desdobramento;
import gepro.mobile.dto.Andamento;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.GeproKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AndamentoAct extends Activity implements Runnable {

	List<Andamento> _andamentos;
	List<Desdobramento> _desdobramentos;

	static ExpandableListView lst = null;
	static Spinner ddlDesdobramentos = null;
	static int DesdobramentoSelecionado = 0;

	ProgressDialog pg = null;

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {

		MenuItem item = menu.add("Adicionar Andamento");
		item.setIcon(R.drawable.andamento_adicionar);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent itn = new Intent(AndamentoAct.this,
						AndamentoManutAct.class);
				itn.putExtra(
						GeproKeys.PASTA_SELECIONADA,
						AndamentoAct.this.getIntent().getExtras()
								.getString(GeproKeys.PASTA_SELECIONADA));
				AndamentoAct.this.startActivityForResult(itn, 1);
				return true;
			}
		});

		return true;
	};

	Handler AndamentoHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			try {

				if (msg.what == GeproKeys.RETURN_CODE.OK) {
					AndamentosExpandListAdapter caa;
					caa = new AndamentosExpandListAdapter(AndamentoAct.this,
							_andamentos);

					lst.setAdapter(caa);
				}

				if (msg.what == GeproKeys.RETURN_CODE.NULL) {

					AndamentosExpandListAdapter caa;

					caa = new AndamentosExpandListAdapter(AndamentoAct.this,
							Arrays.asList(new Andamento[0]));

					lst.setAdapter(caa);

					Toast.makeText(
							AndamentoAct.this,
							"Não ha andamentos cadastrados para este desdobramento",
							Toast.LENGTH_LONG).show();
				}

				if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
					Toast.makeText(AndamentoAct.this,
							"Occorreu um erro durante a busca de Andamentos",
							Toast.LENGTH_LONG).show();
				}

				if (pg != null)
					pg.hide();

			} catch (Exception ex) {
				Toast.makeText(AndamentoAct.this, ex.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		}
	};

	Handler DesdobramentoHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			try {

				if (pg != null)
					pg.hide();

				if (msg.what == GeproKeys.RETURN_CODE.OK) {
					DesdobramentoArrayAdapter daa;

					daa = new DesdobramentoArrayAdapter(AndamentoAct.this,
							_desdobramentos);

					ddlDesdobramentos.setAdapter(daa);

				} else if (msg.what == GeproKeys.RETURN_CODE.NULL) {

					Toast.makeText(
							AndamentoAct.this,
							"Não ha desdobramento cadastrados para este processo",
							Toast.LENGTH_LONG).show();

				} else if (msg.what == GeproKeys.RETURN_CODE.FAIL) {

					Toast.makeText(
							AndamentoAct.this,
							"Occorreu um erro durante a busca de Desdobramentos ",
							Toast.LENGTH_LONG).show();
				}

			} catch (Exception e) {
				Toast.makeText(AndamentoAct.this,
						"Occorreu um erro durante a busca de Desdobramentos ",
						Toast.LENGTH_LONG).show();
			}
		}

	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 1 && resultCode == GeproKeys.RETURN_CODE.OK) {
			pg = ProgressDialog.show(this, null, "Aguarde...", true, false);
			new Thread(this, "GET_MOVIMENTS").start();
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.moviments_exlist);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		
		lst = (ExpandableListView) findViewById(R.id.lst_moviments);
		ddlDesdobramentos = (Spinner) findViewById(R.id.ddl_desdobramentos_moviment);

		if (_andamentos == null) {
			pg = ProgressDialog.show(this, null, "Aguarde...", true, false);
			new Thread(this, "GET_MOVIMENTS").start();
		}
	}

	@Override
	public void run() {
		LoadDesdobramentos();
		LoadAndamentos(0);

		ddlDesdobramentos
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						pg = ProgressDialog.show(AndamentoAct.this, null,
								"Aguarde...", true, false);

						Runnable runable = new Runnable() {
							@Override
							public void run() {
								Desdobramento desdo = (Desdobramento) ddlDesdobramentos
										.getSelectedItem();
								LoadAndamentos(desdo.getID());
							}
						};

						new Thread(runable).start();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}

	public void LoadDesdobramentos() {
		try {
			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());

			String pastaid = getIntent().getExtras().getString(
					GeproKeys.PASTA_SELECIONADA);

			Desdobramento[] desd;

			desd = proxy.GetDesdobramento(
					gepro.mobile.util.Config.getCurrentSession(),
					Integer.parseInt(pastaid));

			if (desd == null || desd.length == 0) {
				_desdobramentos = null;
				DesdobramentoHandler
						.sendEmptyMessage(GeproKeys.RETURN_CODE.NULL);
			} else {
				_desdobramentos = Arrays.asList(desd);
				DesdobramentoHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);
			}

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			DesdobramentoHandler.sendMessage(msg);
		}
	}

	public void LoadAndamentos(int desdobramentoid) {
		try {

			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());

			String pastaid = getIntent().getExtras().getString(
					GeproKeys.PASTA_SELECIONADA);

			Andamento[] mov = proxy.GetMoviments(
					gepro.mobile.util.Config.getCurrentSession(),
					Integer.parseInt(pastaid), desdobramentoid);

			if (mov == null) {
				_andamentos = null;
				AndamentoHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.NULL);
			} else {
				_andamentos = Arrays.asList(mov);
				AndamentoHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);
			}

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			AndamentoHandler.sendMessage(msg);
		}
	}
}
