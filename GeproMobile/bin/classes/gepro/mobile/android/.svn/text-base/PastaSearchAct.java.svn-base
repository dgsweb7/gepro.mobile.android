package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.PastaArrayAdapter;
import gepro.mobile.dto.Pasta;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;
import gepro.mobile.util.GeproKeys.RETURN_CODE;

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

public class PastaSearchAct extends Activity implements Runnable {

	List<Pasta> _pastas = null;
	ProgressDialog pg = null;
	String queryString;
	int PastaTipo;

	static ListView lst = null;

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

		setContentView(R.layout.pasta_list);
		
		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}

		lst = (ListView) findViewById(R.id.lst_pasta);
		lst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Pasta item = (Pasta) lst.getAdapter().getItem(arg2);

				if (Intent.ACTION_PICK_ACTIVITY.equals(PastaSearchAct.this
						.getIntent().getAction())) {
					
					Intent itn = new Intent();
					itn.putExtra(GeproKeys.PASTA_SELECIONADA , item.getPastaId());
					itn.putExtra(GeproKeys.PASTA_SELECIONADA_NUMERO , Util.IsNullOrEmpty( item.getNProcesso() )  ? item.getPastaId() : item.getNProcesso()  );
					setResult( RESULT_OK , itn);
					finish();

				} else {

					Intent itn = new Intent(PastaSearchAct.this,
							PastaTabAct.class);
					
					itn.putExtra(GeproKeys.PASTA_SELECIONADA, item.getPastaId());

					PastaSearchAct.this.startActivity(itn);
				}

			}
		});

		if (_pastas == null)
			doSearch(this.getIntent());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuItem menuItem = menu.add(R.string.dashboard_menu_pasta);
		menuItem.setIcon(R.drawable.pasta_pesquisar);
		menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				onSearchRequested();
				return true;
			}
		});

		return true;
	}

	private void doSearch(Intent itn) {

		queryString = itn.getStringExtra(SearchManager.QUERY);
		
		PastaTipo = itn.getIntExtra(
				GeproKeys.PASTA_TIPO_SELECIONADA, GeproKeys.Perfil.Empresa);
		
		if (Util.IsNullOrEmpty(queryString))
			queryString = "_";

		pg = ProgressDialog.show(this, null, "Aguarde...");
		new Thread(this, "DO_SEARCH").start();
	}

	@Override
	public void run() {

		try {

			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());

			Pasta[] p = proxy.SearchPasta(gepro.mobile.util.Config
					.getCurrentSession(), queryString, PastaTipo);

			if (p != null)
				_pastas = Arrays.asList(p);
			else
				_pastas = null;

			pastaHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

		} catch (Exception e) {
			Message message = Tools.CreateMessageFomEx(e);
			message.what = GeproKeys.RETURN_CODE.FAIL;
			pastaHandler.sendMessage(message);
		} finally {

		}
	}

	Handler pastaHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
				Toast.makeText(PastaSearchAct.this,
						Tools.GetMessageExeption(msg), Toast.LENGTH_SHORT)
						.show();
				return;
			}

			if (msg.what == GeproKeys.RETURN_CODE.OK) {

				if (_pastas == null || _pastas.size() == 0) {

					Toast.makeText(PastaSearchAct.this,
							"Não há items para exibir", Toast.LENGTH_LONG)
							.show();

					lst.setAdapter(null);

				} else {

					PastaArrayAdapter paa = new PastaArrayAdapter(
							PastaSearchAct.this, _pastas);
					lst.setAdapter(paa);
				}

			}
		}
	};
}
