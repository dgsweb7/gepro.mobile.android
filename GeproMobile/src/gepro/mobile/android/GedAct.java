package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.GedExpandListAdapter;
import gepro.mobile.android.adapters.AndamentosExpandListAdapter;
import gepro.mobile.camera.CameraActivity;
import gepro.mobile.dto.Ged;
import gepro.mobile.dto.GedItem;
import gepro.mobile.dto.Andamento;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class GedAct extends Activity implements Runnable,
		DialogInterface.OnCancelListener {

	static final int MSG_INCGREMENT = 99;
	static final int MSG_SETMAX = 80;
	static final int MSG_HIDEDIALOG = -99;

	static final int MSG_CANCELDOWNLOAD = 70;

	List<Ged> _geds;
	static ExpandableListView lst = null;
	ProgressDialog pg = null;
	AlertDialog downloadDialog = null;
	GedItem selectedGedItem = null;
	ProgressDialog pgDownload = null;

	Thread downloadThread = null;
	boolean stopDownload = false;

	Handler DocumentoHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			try {

				if (pg != null)
					pg.hide();

				if (msg.what == GeproKeys.RETURN_CODE.OK) {
					GedExpandListAdapter caa;
					caa = new GedExpandListAdapter(GedAct.this, _geds);

					lst.setAdapter(caa);

				} else if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
					
					Toast.makeText(GedAct.this,
							"Ocorreu um erro durante a busca de Documentos",
							Toast.LENGTH_LONG).show();
					
				} else if (msg.what == GeproKeys.RETURN_CODE.NULL) {
					
					GedExpandListAdapter caa;
					caa = new GedExpandListAdapter(GedAct.this,  new ArrayList<Ged>() );

					lst.setAdapter(caa);
				}

			} catch (Exception ex) {
				Toast.makeText(GedAct.this, ex.getMessage(), Toast.LENGTH_LONG)
						.show();
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuItem menuItem = menu.add("GED - Anexar Doc.");
		menuItem.setIcon(R.drawable.documento);
		menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				String id = GedAct.this.getIntent().getExtras().getString(
						GeproKeys.PASTA_SELECIONADA);

				Intent itn = new Intent(GedAct.this, MobileGedAct.class);
				itn.putExtra(GeproKeys.PASTA_SELECIONADA, id);
				startActivityForResult(itn, 1);

				return true;
			}
		});

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == GeproKeys.RETURN_CODE.OK) {

			pg = ProgressDialog.show(this, null, "Aguarde...", true, false);
			new Thread(this, "GET_GEDS").start();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ged_expand_list);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		
		CreateDownloadDialog();

		lst = (ExpandableListView) findViewById(R.id.lst_ged_expandlist);
		lst.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				String state = Environment.getExternalStorageState();

				if (Environment.MEDIA_MOUNTED.equals(state)) {
					
					selectedGedItem = (GedItem) lst.getExpandableListAdapter()
							.getChild(groupPosition, childPosition);
					downloadDialog.show();

					return true;
				} else {
					Toast
							.makeText(
									GedAct.this,
									"Não foi encontrado nenhum dispositivo de armazenamento \"sdcard\"",
									Toast.LENGTH_LONG);
					return true;
				}

			}
		});

		if (_geds == null) {
			pg = ProgressDialog.show(this, null, "Aguarde...", true, false);
			new Thread(this, "GET_GEDS").start();
		}
	}

	private void CreateDownloadDialog() {

		AlertDialog.Builder builder = new Builder(this);
		builder
				.setMessage("Para vizualizar o documento é necessario efetuar o download. Deseja fazer o download do arquivo.");
		builder.setPositiveButton("Sim, Desejo.", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				pgDownload = ProgressDialog.show(GedAct.this, null,
						"Aguarde... Efetuando download do arquivo "
								+ selectedGedItem.getDescricao());
				pgDownload.setCancelable(true);
				pgDownload.setOnCancelListener(GedAct.this);

				downloadThread = new Thread(downloadRunnable, "dowloadfile");
				downloadThread.start();
			}
		});
		builder.setCancelable(false);
		builder.setNegativeButton("Não, Obrigado.", null);
		downloadDialog = builder.create();
	}

	Runnable downloadRunnable = new Runnable() {

		@Override
		public void run() {

			GeproMobileServiceProxy proxy;
			InputStream stream;

			try {

				proxy = new GeproMobileServiceProxy(new HttpRequest());
				stream = proxy.DownLoadGedItem(gepro.mobile.util.Config
						.getCurrentSession(), selectedGedItem.getID());

				Message msg = new Message();
				msg.arg1 = (int) selectedGedItem.getSize();
				msg.what = MSG_SETMAX;
				pgDownloadHandler.sendMessage(msg);

				File file = 
					new File(Config.getDownloadDir()+ "/" + selectedGedItem.getDescricao());
				if (!file.exists())
					file.createNewFile();

				OutputStream outStream = new FileOutputStream(file);

				byte[] buffer = new byte[1024];

				int read = 0;
				read = stream.read(buffer, 0, 1024);
				while (read != -1 && !stopDownload) {
					outStream.write(buffer, 0, read);
					Message msgCount = new Message();
					msgCount.arg1 = read;
					msgCount.what = MSG_INCGREMENT;
					pgDownloadHandler.sendMessage(msgCount);
					read = stream.read(buffer, 0, 1024);
				}

				outStream.flush();
				outStream.close();
				stream.close();

				if (stopDownload) {
					stopDownload = false;

					if (file.exists())
						file.delete();

					Message msgCancel = new Message();
					msgCancel.obj = "Download Cancelado";
					msgCancel.what = MSG_CANCELDOWNLOAD;
					pgDownloadHandler.sendMessage(msgCancel);

				} else {
					Message msgOK = new Message();
					msgOK.obj = file.getAbsolutePath();
					msgOK.what = GeproKeys.RETURN_CODE.OK;
					pgDownloadHandler.sendMessage(msgOK);
				}

			} catch (Exception e) {
				Message msgErro = new Message();
				msgErro = Tools.CreateMessageFomEx(e);
				msgErro.what = GeproKeys.RETURN_CODE.FAIL;
				pgDownloadHandler.sendMessage(msgErro);
			}
		}
	};

	Handler pgDownloadHandler = new Handler() {
		public void handleMessage(Message msg) {

			if (msg.what == MSG_SETMAX) {
				pgDownload.setMax(msg.arg1);
			}

			if (msg.what == MSG_HIDEDIALOG) {
				downloadDialog.hide();
			}

			if (msg.what == MSG_INCGREMENT) {
				pgDownload.incrementProgressBy(msg.arg1);
			}

			if (msg.what == MSG_CANCELDOWNLOAD) {
				pgDownload.dismiss();
				Toast.makeText(GedAct.this, msg.obj.toString(),
						Toast.LENGTH_LONG).show();

			}

			if (msg.what == GeproKeys.RETURN_CODE.OK) {
				pgDownload.hide();
				Toast.makeText(
						GedAct.this,
						"Download do arquivo concluído. [" + msg.obj.toString()
								+ "]", Toast.LENGTH_LONG).show();
			}

			if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
				if (pgDownload != null)
					pgDownload.dismiss();

				Toast.makeText(GedAct.this, Tools.GetMessageExeption(msg),
						Toast.LENGTH_LONG);
			}
		};
	};

	@Override
	public void run() {
		try {

			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());

			String pastaid = getIntent().getExtras().getString(
					GeproKeys.PASTA_SELECIONADA);

			Ged[] ged = proxy.GetGED(gepro.mobile.util.Config
					.getCurrentSession(), Integer.parseInt(pastaid));

			if (ged == null) {
				_geds = null;
				DocumentoHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.NULL);
			} else {
				DocumentoHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);
				_geds = Arrays.asList(ged);
			}

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			DocumentoHandler.sendMessage(msg);
		}
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		stopDownload = true;
	}
}
