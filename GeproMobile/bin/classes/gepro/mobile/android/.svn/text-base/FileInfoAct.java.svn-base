package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.TipoArrayAdapter;
import gepro.mobile.dto.GeproFile;
import gepro.mobile.dto.Tipo;
import gepro.mobile.parse.GeproFileJSONparser;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.modularsistemas.modpdf.A4;
import com.modularsistemas.modpdf.Image;
import com.modularsistemas.modpdf.PDF;
import com.modularsistemas.modpdf.Page;

public class FileInfoAct extends Activity {

	EditText txtFileName = null, txtDescription = null, txtKeyWord = null;
	Spinner ddlDocType = null;
	Button btnSend = null, btnPasta;
	List<Tipo> _tipos = null;
	ProgressDialog pg = null;
	static final int CALL_PASTA = 5;
	String pastaID;

	Handler SendFileHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {

			try {
				if (pg != null)
					pg.hide();

				if (msg.what == GeproKeys.RETURN_CODE.OK) {
					setResult(GeproKeys.RETURN_CODE.OK);
					Toast.makeText(FileInfoAct.this,
							"Arquivo enviado com sucesso", Toast.LENGTH_SHORT)
							.show();
					FileInfoAct.this.finish();
				}

				if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
					Toast
							.makeText(
									FileInfoAct.this,
									"Ocorreu um erro durante o envio do Arquivo. tente novamente.",
									Toast.LENGTH_SHORT).show();
				}

			} catch (Exception ex) {
				Toast.makeText(FileInfoAct.this,
						"Ocorreu um erro durante o envio do Arquivo",
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	private void LoadTipoDocumento() {

		GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
				new HttpRequest());
		try {

			Tipo[] _t = proxy.GetTipos(gepro.mobile.util.Config
					.getCurrentSession(), GeproKeys.Tabela.Ged_TipoDocumento);

			if (_t != null) {
				_tipos = Arrays.asList(_t);
				TipoArrayAdapter tad = new TipoArrayAdapter(this, _tipos);
				ddlDocType.setAdapter(tad);
			}

		} catch (Exception e) {
			Toast.makeText(FileInfoAct.this,
					"Ocorreu um erro ao buscar tipos de Documento ",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.mobile_ged_dialog);
		
		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		

		try {
			Init();
		} catch (Exception e) {
			Toast.makeText(FileInfoAct.this,
					"Ocorreu um erro ao iniciar informaçoes de arquivo ",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void Init() throws Exception {

		txtFileName = (EditText) findViewById(R.id.txt_mobile_ged_filename);
		txtDescription = (EditText) findViewById(R.id.txt_mobile_ged_description);
		txtKeyWord = (EditText) findViewById(R.id.txt_mobile_ged_keywords);
		ddlDocType = (Spinner) findViewById(R.id.ddl_mobile_ged_doctype);
		btnSend = (Button) findViewById(R.id.btn_mobile_ged_enviar);
		btnPasta = (Button) findViewById(R.id.btn_mobile_ged_pasta);

		LoadTipoDocumento();
		btnSend.setOnClickListener(SendListener);

		pastaID = this.getIntent().getStringExtra(
				GeproKeys.PASTA_SELECIONADA);
		
		if(!Util.IsNullOrEmpty( pastaID))
			btnPasta.setText(pastaID);
		else
			btnPasta.setText("N/A");
		
		btnPasta.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent itn = new Intent(FileInfoAct.this, PastaTipoAct.class);
				itn.setAction(Intent.ACTION_PICK_ACTIVITY);
				FileInfoAct.this.startActivityForResult(itn, CALL_PASTA);

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == CALL_PASTA && resultCode == RESULT_OK) {
			pastaID = data.getStringExtra(GeproKeys.PASTA_SELECIONADA);
			String numero = data.getStringExtra(GeproKeys.PASTA_SELECIONADA_NUMERO);
			if (!Util.IsNullOrEmpty(pastaID)) {
				btnPasta.setText(pastaID  + " - " + numero);
			}
		}
	}

	Runnable CreateAndSendFile = new Runnable() {

		@Override
		public void run() {

			byte[] buffer;

			try {

				buffer = CreatePdf();

				SendFile(buffer);

				ClearTempFiles();

				SendFileHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

			} catch (Exception e) {

				Message msg = Tools.CreateMessageFomEx(e);
				msg.what = GeproKeys.RETURN_CODE.FAIL;
				SendFileHandler.sendMessage(msg);

			} finally {

				buffer = null;
				System.gc();
			}
		}
	};

	private void ClearTempFiles() {
		try {

			String[] _uriList = this.getIntent().getExtras().getStringArray(
					GeproKeys.FILES_URI);

			// Deleta os arquivos temporario gerado
			for (String uri : _uriList) {
				deleteFile(uri);
			}

		} catch (Exception e) {
		}
	}

	private byte[] CreatePdf() throws IOException, Exception {

		FileOutputStream pdfOutStream = null;
		FileInputStream pdfInStream = null;

		PDF pdf = null;
		byte[] buffer = null;

		String[] _uriList = null;
		String fileName =  Config.getCacheDir() + "/" + new Date().getTime() + ".pdf";

		try {

			_uriList = this.getIntent().getExtras().getStringArray(
					GeproKeys.FILES_URI);

			// Retorna todas as Images URIS e escre no arquivo
			pdfOutStream = new FileOutputStream(fileName);
			pdf = new PDF(pdfOutStream);
			for (String uri : _uriList) {

				FileInputStream fScanIn = new FileInputStream(uri);
				Page page = new Page(pdf, A4.PORTRAIT);
				Image img = new Image(pdf, fScanIn, 1);
				img.setPosition(0, 0);
				img.drawOn(page);
				fScanIn.close();
				fScanIn = null;
			}
			pdf.flush();
			pdfOutStream.flush();
			pdfOutStream.close();

			// Le o Arquivo Gerado e armazena em buffer
			pdfInStream = new FileInputStream(fileName);
			buffer = new byte[pdfInStream.available()];
			pdfInStream.read(buffer, 0, buffer.length);
			
			return buffer;

		} finally {

			// Fecha os Streans Pendentes
			if (pdfInStream != null)
				pdfInStream.close();

			if (pdfOutStream != null)
				pdfOutStream.close();

			// força o garbage collector
			pdf = null;
			pdfInStream = null;
			pdfOutStream = null;
			System.gc();
		}
	}

	public void SendFile(byte[] buffer) throws Exception {

		GeproFile geproFile = new GeproFile();

		Tipo tipo = (Tipo) ddlDocType.getSelectedItem();

		geproFile.setNome(String.format("%s_%s.pdf", txtFileName.getText()
				.toString(), new Date().getTime()));
		geproFile.setDescricao(txtDescription.getText().toString());
		geproFile.setPalaavraChave(txtKeyWord.getText().toString());
		geproFile.setTipo(Integer.parseInt(tipo.getID()));
		geproFile.setFileContent(buffer);

		geproFile.setPastaID(pastaID);

		GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
				new HttpRequest());

		proxy.UploadFile(gepro.mobile.util.Config.getCurrentSession(),
				geproFile);

	}

	OnClickListener SendListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			try {
				if (Util.IsNullOrEmpty(txtFileName.getText().toString()))
					throw new Exception("Nome do Arquivo Obrigatorio");

				if (Util.IsNullOrEmpty(txtDescription.getText().toString()))
					throw new Exception("Descricao do Arquivo Obrigatorio");
				
				if (Util.IsNullOrEmpty(pastaID))
					throw new Exception("Pasta de Destino é Obrigatória");

				pg = ProgressDialog.show(FileInfoAct.this, null,
						"Aguarde...Enviando Arquivo");

				new Thread(CreateAndSendFile, "SEND_FILE").start();

			}

			catch (Exception e) {
				Toast.makeText(FileInfoAct.this, e.getMessage(),
						Toast.LENGTH_LONG).show();
			}

		}
	};
}
