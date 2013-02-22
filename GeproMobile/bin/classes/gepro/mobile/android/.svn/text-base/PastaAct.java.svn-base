package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.dto.Pasta;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class PastaAct extends Activity implements Runnable {

	static TextView txtArea = null, txtTitulo = null, txtEmpresa = null,
			txtParteInterassada = null, txtParteContraria = null,
			txtObjeto = null, txtTipoAcao = null, txtData = null,
			txtNprocesso = null, txtForo = null, txtVara = null,
			txtCodigo = null;

	static ProgressDialog pg = null;
	static Pasta pasta = null;

	Handler PastaHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.OK)
				BindData();

			if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
				Toast.makeText(PastaAct.this,
						"Occorreu um erro durante a busca da pasta",
						Toast.LENGTH_LONG).show();
			}

		};
	};

	private void Init() {

		txtCodigo = (TextView) findViewById(R.id.txt_pasta_detail_codigo);
		txtArea = (TextView) (findViewById(R.id.txt_pasta_detail_area));
		txtTitulo = (TextView) (findViewById(R.id.txt_pasta_detail_titulo));
		txtEmpresa = (TextView) (findViewById(R.id.txt_pasta_detail_empresa));
		txtParteInterassada = (TextView) (findViewById(R.id.txt_pasta_detail_parteinteressada));
		txtParteContraria = (TextView) (findViewById(R.id.txt_pasta_detail_partecontraria));
		txtObjeto = (TextView) (findViewById(R.id.txt_pasta_detail_objeto));
		txtTipoAcao = (TextView) (findViewById(R.id.txt_pasta_detail_tipoacao));
		txtData = (TextView) (findViewById(R.id.txt_pasta_detail_datadistri));
		txtNprocesso = (TextView) (findViewById(R.id.txt_pasta_detail_nprocesso));
		txtForo = (TextView) (findViewById(R.id.txt_pasta_detail_foro));
		txtVara = (TextView) (findViewById(R.id.txt_pasta_detail_vara));
	}

	private void BindData() {
		try {

			if (pasta == null)
				return;

			if (!Util.IsNullOrEmpty(pasta.getPastaId()))
				txtCodigo.setText(pasta.getPastaId());

			if (!Util.IsNullOrEmpty(pasta.getArea()))
				txtArea.setText(pasta.getArea());

			if (!Util.IsNullOrEmpty(pasta.getArea()))
				txtArea.setText(pasta.getArea());

			if (!Util.IsNullOrEmpty(pasta.getTitulo()))
				txtTitulo.setText(pasta.getTitulo());

			if (!Util.IsNullOrEmpty(pasta.getEmpresa()))
				txtEmpresa.setText(pasta.getEmpresa());

			if (!Util.IsNullOrEmpty(pasta.getParteSolicitante()))
				txtParteInterassada.setText(pasta.getParteSolicitante());

			if (!Util.IsNullOrEmpty(pasta.getParteContrario()))
				txtParteContraria.setText(pasta.getParteContrario());

			if (!Util.IsNullOrEmpty(pasta.getDescricaoObjeto()))
				txtObjeto.setText(pasta.getDescricaoObjeto());

			if (!Util.IsNullOrEmpty(pasta.getTipoAcao()))
				txtTipoAcao.setText(pasta.getTipoAcao());

			if (!Util.IsNullOrEmpty(pasta.getDataDistribuicao()))
				txtData.setText(pasta.getDataDistribuicao());

			if (!Util.IsNullOrEmpty(pasta.getNProcesso()))
				txtNprocesso.setText(pasta.getNProcesso());

			if (!Util.IsNullOrEmpty(pasta.getForo()))
				txtForo.setText(pasta.getForo());

			if (!Util.IsNullOrEmpty(pasta.getVara()))
				txtVara.setText(pasta.getVara());

		} catch (Exception e) {
			Toast t = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
			t.show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		try {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.pasta_detail);
			
			View titleView = getWindow().findViewById(android.R.id.title);
			if (titleView != null) {
			  ViewParent parent = titleView.getParent();
			  if (parent != null && (parent instanceof View)) {
			    View parentView = (View)parent;
			    parentView.setBackgroundColor(Color.BLACK);
			  }
			}
			
			Init();

			pg = ProgressDialog.show(this, null, "Aguarde...", true, false);
			new Thread(this, "GET_PASTA").start();

		} catch (Exception ex) {
			Toast t = Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT);
			t.show();
		}
	}

	@Override
	public void run() {

		GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
				new HttpRequest(6000));

		String pastaid = getIntent().getExtras().getString(
				GeproKeys.PASTA_SELECIONADA);

		try {
			pasta = proxy.GetPasta(Config.getCurrentSession(),
					Integer.parseInt(pastaid));

			PastaHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			PastaHandler.sendMessage(msg);
		}

	}

}
