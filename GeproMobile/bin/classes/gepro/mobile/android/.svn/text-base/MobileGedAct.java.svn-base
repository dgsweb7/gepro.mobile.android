package gepro.mobile.android;

import gepro.mobile.camera.CameraActivity;
import gepro.mobile.util.GeproKeys;
import gepro.mobile.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewParent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MobileGedAct extends Activity {

	final int REQUEST_CODE_CAMERA = 1;
	final int REQUEST_CODE_FILEINFO = 2;

	final int ACTION_NEXT = 1;
	final int ACTION_PREV = 2;
	final int ACTION_FIRST = 3;
	final int ACTION_LAST = 4;

	ArrayList<String> _uriList = new ArrayList<String>();
	ImageButton btnAdd, btnRemove, btnNext, btnPrev, btnMakePdf;
	ImageView img_view;
	int currentpos;

	private void loadImages() {
		final Object data = getLastNonConfigurationInstance();

		if (data != null) {
			Bundle b = (Bundle) data;
			_uriList = b.getStringArrayList("LIST");
			ShowPreview(ACTION_FIRST);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadImages();

		setContentView(R.layout.viewer_layout);

		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
			ViewParent parent = titleView.getParent();
			if (parent != null && (parent instanceof View)) {
				View parentView = (View) parent;
				parentView.setBackgroundColor(Color.BLACK);
			}
		}

		img_view = (ImageView) findViewById(R.id.img_mobile_ged_preview);

		ShowPreview(ACTION_FIRST);

		btnAdd = (ImageButton) findViewById(R.id.btn_mobile_ged_add);
		btnAdd.setImageResource(R.drawable.ged_adicionar);
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String state = Environment.getExternalStorageState();

				if (Environment.MEDIA_MOUNTED.equals(state)) {
					Intent itn = new Intent(MobileGedAct.this,
							CameraActivity.class);
					MobileGedAct.this.startActivityForResult(itn,
							REQUEST_CODE_CAMERA);
				} else {
					Toast
							.makeText(
									MobileGedAct.this,
									"Não foi encontrado nenhum dispositivo de armazenamento \"sdcard\"",
									Toast.LENGTH_LONG);
				}
			}
		});

		btnRemove = (ImageButton) findViewById(R.id.btn_mobile_ged_remove);
		btnRemove.setImageResource(R.drawable.ged_deletar);
		btnRemove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RemoveItem();
			}
		});

		btnNext = (ImageButton) findViewById(R.id.btn_mobile_ged_nextImage);
		btnNext.setImageResource(R.drawable.ged_seta_dirteira);
		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ShowPreview(ACTION_NEXT);
			}
		});

		btnPrev = (ImageButton) findViewById(R.id.btn_mobile_ged_prevImage);
		btnPrev.setImageResource(R.drawable.ged_seta_esquerda);
		btnPrev.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ShowPreview(ACTION_PREV);
			}
		});

		btnMakePdf = (ImageButton) findViewById(R.id.btn_mobile_ged_make_pdf);
		btnMakePdf.setImageResource(R.drawable.ged_anexar);
		btnMakePdf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					Validate();

					String[] array = new String[_uriList.size()];
					_uriList.toArray(array);

					Intent itn = new Intent(MobileGedAct.this,
							FileInfoAct.class);
					itn.putExtra(GeproKeys.FILES_URI, array);

					if (MobileGedAct.this.getIntent().hasExtra(
							GeproKeys.PASTA_SELECIONADA)) {
						itn.putExtra(GeproKeys.PASTA_SELECIONADA,
								MobileGedAct.this.getIntent().getStringExtra(
										GeproKeys.PASTA_SELECIONADA));
					}

					MobileGedAct.this.startActivityForResult(itn,
							REQUEST_CODE_FILEINFO);

				} catch (Exception ex) {
					Toast t = Toast.makeText(MobileGedAct.this,
							ex.getMessage(), Toast.LENGTH_SHORT);
					t.show();
				}

			}
		});
	}

	private void Validate() throws Exception {
		if (_uriList.size() == 0)
			throw new Exception("Não há arquivos para gerar o pdf");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE_CAMERA
				&& resultCode == GeproKeys.RETURN_CODE.OK) {

			String filename = data.getExtras().getString("URI");
			_uriList.add(filename);
			ShowPreview(ACTION_LAST);
		}

		if (requestCode == REQUEST_CODE_FILEINFO
				&& resultCode == GeproKeys.RETURN_CODE.OK) {
			try {
				setResult(GeproKeys.RETURN_CODE.OK);
				MobileGedAct.this.finish();
			} catch (Exception e) {
				setResult(GeproKeys.RETURN_CODE.FAIL);
				MobileGedAct.this.finish();
			}

		}

	}

	private void RemoveItem() {
		try {

			File file = new File(_uriList.get(currentpos).toString());
			if (file.exists()) {
				file.delete();
				_uriList.remove(currentpos);
				ShowPreview(ACTION_PREV);
			}

		} catch (Exception e) {
		}

	}

	private void ShowPreview(int action) {

		switch (action) {
		case ACTION_PREV:
			currentpos--;
			currentpos = currentpos < 0 ? 0 : currentpos;
			break;

		case ACTION_NEXT:
			currentpos++;
			currentpos = currentpos > _uriList.size() - 1 ? _uriList.size() - 1
					: currentpos;
			break;

		case ACTION_FIRST:
			currentpos = 0;
			break;

		case ACTION_LAST:
			currentpos = _uriList.size() - 1;
		}

		try {
			if (_uriList != null || _uriList.size() > 0) {

				img_view.destroyDrawingCache();
				Uri uri = Uri.parse(_uriList.get(currentpos));
				img_view.setImageURI(uri);
			}
		} catch (Exception e) {
		}
	}
}
