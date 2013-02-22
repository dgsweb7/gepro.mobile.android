package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.PessoasArrayAdapter;
import gepro.mobile.dto.Pessoa;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PessoasSearchAct extends Activity implements Runnable {

	private ProgressDialog pg = null;
	private List<Pessoa> _customers = null;
	private String queryString = null;
	static ListView lst = null;
	static int PerfilPessoa = 0;

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);

		final String queryAction = intent.getAction();

		if (Intent.ACTION_SEARCH.equals(queryAction)) {
			doSearch(intent);
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_list);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		
		lst = (ListView) findViewById(R.id.lst_customer);

		if (_customers == null)
			doSearch(this.getIntent());

		lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Pessoa cust = (Pessoa) lst.getItemAtPosition(arg2);

				Bundle b = new Bundle();
				
				b.putString(GeproKeys.SELECTED_CUSTOMER_ID, String.valueOf(cust
						.getID()));
				
				b.putString(GeproKeys.PERFIL_PESSOA_SELECIONADO_NOME, String.valueOf(cust
						.getNome() ));

				Intent intent = new Intent(PessoasSearchAct.this,
						PessoasTabAct.class);

				intent.putExtras(b);

				if (Intent.ACTION_PICK_ACTIVITY.equals(PessoasSearchAct.this
						.getIntent().getAction())) {
					setResult(RESULT_OK, intent);
					finish();

				} else {
					PessoasSearchAct.this.startActivity(intent);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuItem item = menu.add("Pesquisar Pessoas");
		item.setIcon(R.drawable.pessoa_pesquisar);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				onSearchRequested();
				return true;
			}
		});

		return true;
	}

	private void doSearch(Intent queryIntent) {

		queryString = queryIntent.getStringExtra(SearchManager.QUERY);
		PerfilPessoa = queryIntent.getIntExtra(
				GeproKeys.PERFIL_PESSOA_SELECIONADO, GeproKeys.Perfil.Empresa);

		pg = ProgressDialog.show(this, null, "Aguarde...", true, false);

		new Thread(this, "DO_SEARCH").start();
	}

	@Override
	public void run() {
		try {

			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());

			if (Util.IsNullOrEmpty(queryString))
				queryString = "_";

			Pessoa[] _cust = proxy.SearchCustomer(gepro.mobile.util.Config
					.getCurrentSession(), queryString, PerfilPessoa);

			if (_cust != null)
				_customers = Arrays.asList(_cust);
			else
				_customers = null;

			customerHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			customerHandler.sendMessage(msg);
		}

	}

	Handler customerHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
				Toast.makeText(PessoasSearchAct.this,
						Tools.GetMessageExeption(msg), Toast.LENGTH_SHORT)
						.show();
				return;
			}

			if (msg.what == GeproKeys.RETURN_CODE.OK) {

				if (_customers == null || _customers.size() == 0) {

					Toast.makeText(PessoasSearchAct.this,
							"Não há items para exibir", Toast.LENGTH_LONG)
							.show();

					lst.setAdapter(null);

				} else {

					PessoasArrayAdapter caa = new PessoasArrayAdapter(
							PessoasSearchAct.this, _customers);

					lst.setAdapter(caa);
				}

			}
		}
	};

}
