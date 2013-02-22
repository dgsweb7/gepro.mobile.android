package gepro.mobile.android;

import java.io.File;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.dto.Login;
import gepro.mobile.dto.Session;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginAct extends Activity implements Runnable {

	Button btnLogin, btnExperimentar;
	EditText txtLogin, txtSenha;
	ImageView img;
	ProgressDialog pg;
	Login login = null;
	String consumerid;

	private void getPrefs() {

		try {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getBaseContext());

			Resources res = getResources();

			consumerid = prefs.getString(res
					.getString(R.string.settings_consumer_id_key), null);
		} catch (Exception ex) {
			consumerid = null;
		}
	}

	private void onLoginOk() {
		Intent inte = new Intent(this, MenuAct.class);
		this.startActivityForResult(inte, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == -99) {
			this.finish();
		}
	}

	private void doLogin(int tipo) throws Exception {

		String slogin = "";
		String ssenha = "";

		if (tipo == 1) {
			getPrefs();

			slogin = txtLogin.getText().toString();
			ssenha = txtSenha.getText().toString();

			if (Util.IsNullOrEmpty(slogin) || Util.IsNullOrEmpty(ssenha))
				throw new Exception("Usuario senha Inválidos");

			if (consumerid == null) {
				Resources res = getResources();
				throw new Exception(res.getString(R.string.login_token_help));
			}
		} else if (tipo == 2) {
			ssenha = "123456";
			slogin = "orianbsilva";
			consumerid = "001ICQTRIAL";
		}

		login = new Login();
		login.setPassword(ssenha);
		login.setUser(slogin);
		login.setConsumerID(consumerid);
		login.setCultureInfo("pt-BR");

		pg = ProgressDialog.show(this, null, "Aguarde...", true, false);

		new Thread(this, "DO_LOGIN").start();

	}

	private void CleanFileCache() {

		try {
			for (String item : fileList()) {
				deleteFile(item);
			}

		} catch (Exception e) {
			Log.e("DEL", "ERRO AO DELETER CACHE", e);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		getPrefs();

		if (Config.getCurrentSession() != null)
			onLoginOk();
	}

	@Override
	protected void onResume() {

		super.onResume();

		getPrefs();

		try {

			if (Config.getCurrentSession() != null)
				onLoginOk();

		} catch (Exception e) {
			Toast t = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			t.show();
		} finally {
		}

	}

	private void Init() {

		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnExperimentar = (Button) findViewById(R.id.btnExperimentar);
		txtLogin = (EditText) findViewById(R.id.txtLogin);
		txtSenha = (EditText) findViewById(R.id.txtSenha);
		img = (ImageView) findViewById(R.id.imglog);

		img.setImageResource(R.drawable.gepromobile);

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBtnLoginClick(v);
			}
		});

		btnExperimentar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				try {

					doLogin(2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {

			String externalStorageName = Environment
					.getExternalStorageDirectory().getAbsolutePath();
			
			Config.setCacheDir(externalStorageName + "/gepro/cache");
			Config.setDownloadDir(externalStorageName + "/gepro/dowload");
			Config.setThumbsDir(externalStorageName + "/gepro/thumbs");
			
			File file = new File(Config.getCacheDir());
			file.mkdirs();
			File fileThumbs = new File(Config.getThumbsDir());
			fileThumbs.mkdirs();
			File fileDownload = new File(Config.getDownloadDir());
			fileDownload.mkdirs();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.login);

			View titleView = getWindow().findViewById(android.R.id.title);
			if (titleView != null) {
				ViewParent parent = titleView.getParent();
				if (parent != null && (parent instanceof View)) {
					View parentView = (View) parent;
					parentView.setBackgroundColor(Color.BLACK);
				}
			}

			CleanFileCache();

			Init();

			getPrefs();

		} catch (Exception e) {
			Toast t = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			t.show();
		} finally {
		}

	}

	public void onBtnLoginClick(View v) {

		try {
			doLogin(1);
		} catch (Exception e) {
			Toast t = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			t.show();
		} finally {
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		try {

			super.onCreateOptionsMenu(menu);

			MenuItem item = menu.add(R.string.settings);
			item.setIcon(R.drawable.config);
			item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

				@Override
				public boolean onMenuItemClick(MenuItem item) {
					Intent itn = new Intent(LoginAct.this, SettingsAct.class);
					LoginAct.this.startActivity(itn);
					return true;
				}
			});

			return true;

		} catch (Exception e) {
			Toast t = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			t.show();

			return false;

		} finally {

		}
	}

	@Override
	public void run() {
		try {

			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());
			Session session;

			session = proxy.Login(login);

			Config.setCurrentSession(session, "1");

			loginHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			loginHandler.sendMessage(msg);
		}
	}

	Handler loginHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
				Toast.makeText(LoginAct.this, Tools.GetMessageExeption(msg),
						Toast.LENGTH_SHORT).show();
				return;
			}

			if (msg.what == GeproKeys.RETURN_CODE.OK)
				onLoginOk();
		}
	};
}