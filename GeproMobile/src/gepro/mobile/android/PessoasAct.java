package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.dto.Pessoa;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;

public class PessoasAct extends Activity implements Runnable {

	TextView txtCode = null, txtName = null, txtPhone = null, txtWeb = null,
			txtEmail = null, txtAct = null;

	Pessoa pessoa = null;

	ProgressDialog pg = null;

	private void Init() {
		txtCode = (TextView) findViewById(R.id.txt_customer_detail_code);
		txtName = (TextView) findViewById(R.id.txt_customer_detail_name);
		txtPhone = (TextView) findViewById(R.id.txt_customer_detail_phone);
		txtWeb = (TextView) findViewById(R.id.txt_customer_detail_web);
		txtEmail = (TextView) findViewById(R.id.txt_customer_detail_email);
		txtAct = (TextView) findViewById(R.id.txt_customer_detail_activity);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			pessoa = null;
			this.finish();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	

	private void BindData() {
		txtCode.setText(String.valueOf(pessoa.getID()));
		
		if(!Util.IsNullOrEmpty(pessoa.getNome()))
			txtName.setText(pessoa.getNome());
		
		if(!Util.IsNullOrEmpty(pessoa.getTelefone()))
			txtPhone.setText(pessoa.getTelefone());
		
		if(!Util.IsNullOrEmpty(pessoa.getWebSite()))
			txtWeb.setText(pessoa.getWebSite());
	
		if(!Util.IsNullOrEmpty(pessoa.getEmail()))
			txtEmail.setText(pessoa.getEmail());
		
		if(!Util.IsNullOrEmpty(pessoa.getRamoAtividade()))
			txtAct.setText(pessoa.getRamoAtividade());
	}

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
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_detail);

		Init();

		if (pessoa == null) {
			pg = ProgressDialog.show(this, null, "Aguarde...");
			new Thread(this, "GET_CUSTOMER").start();
		}
	}

	@Override
	public void run() {

		GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
				new HttpRequest());

		String customerid = getIntent().getExtras().getString(
				GeproKeys.SELECTED_CUSTOMER_ID);

		try {
			pessoa = (Pessoa) proxy.GetCustomer(Config.getCurrentSession(),
					customerid);

			handlerBinddata.sendEmptyMessage(0);

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			handlerBinddata.sendMessage(msg);

		}

	}

	Handler handlerBinddata = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (msg.what == 0)
				BindData();

			if (msg.what == -1) {
				Toast.makeText(PessoasAct.this,
						"Occorreu um erro durante a busca de Cliente",
						Toast.LENGTH_LONG).show();
			}

			if (pg != null)
				pg.hide();

		};
	};

}
