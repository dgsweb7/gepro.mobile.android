package gepro.mobile.android;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TakePhotoAct extends Activity implements SurfaceHolder.Callback {

	ImageButton btnTake = null;
	boolean bIsPictureTaking = false;
	Camera cDevice;
	SurfaceView camera_surface;
	SurfaceHolder mHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}

		setContentView(R.layout.scan_preview_layout);
		btnTake = (ImageButton) findViewById(R.id.btn_take_photo);
		camera_surface = (SurfaceView) findViewById(R.id.camera_surface);

		mHolder = camera_surface.getHolder();
		mHolder = camera_surface.getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		btnTake.setBackgroundResource(drawable.ic_menu_camera);
		btnTake.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				focusAndTake();
			}
		});

	}

	private void focusAndTake() {
		if(cDevice != null )
			cDevice.autoFocus(new Focus());
	}

	private class Focus implements Camera.AutoFocusCallback {

		@Override
		public void onAutoFocus(boolean success, Camera camera) {

			takePicture();
		}

	}

	private void takePicture() {
		bIsPictureTaking = true;

		String fileName = null;
		OutputStream os = null;
	
		try {
			fileName = String.valueOf( new Date().getTime() );
			os = openFileOutput(fileName,MODE_PRIVATE );

		} catch (Exception ex) {
			Logger.getLogger("CameraActivity").log(Level.WARNING,
					"Failed to take Picture", ex);
			setResult(-1);
			finish();
			return;
		}

		ImageCaptureCallback iccb = new ImageCaptureCallback(os,fileName  );

		cDevice.takePicture(null, null, iccb);
		
		if(camera_surface != null)
			camera_surface.setVisibility(View.INVISIBLE);
	}

	private class ImageCaptureCallback implements PictureCallback {

		private OutputStream foStream;
		private final String sFileName;

		public ImageCaptureCallback(OutputStream filoutputStream,
				String fileName) {
			this.foStream = filoutputStream;
			this.sFileName = fileName;
		}

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			boolean exception = false;
			try {

				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
						data.length);
				Bitmap bmp = Bitmap.createScaledBitmap(bitmap, bitmap
						.getWidth() / 2, bitmap.getHeight() / 2, true);
				bmp.compress(Bitmap.CompressFormat.JPEG, 50, foStream);

				foStream.flush();
			} catch (IOException ex) {
				exception = true;
				android.util.Log.v(getClass().getSimpleName(),
						"Error while taking photo", ex);
				ex.printStackTrace();
			} finally {
				if (foStream != null) {
					try {
						foStream.close();
					} catch (Exception ex) {
						android.util.Log.v(getClass().getSimpleName(),
								"Error while taking photo", ex);
						ex.printStackTrace();
					}
				}
			}

			Bundle bundle = new Bundle();
			if (sFileName != null) {
				bundle.putString("URI", sFileName);
			}

			Intent intent = new Intent();
			intent.putExtras(bundle);
			setResult(exception ? -1 : 1, intent);
			finish();

			try {

				cDevice.stopPreview();
				cDevice.release();
				camera_surface = null;
				cDevice = null;
				mHolder = null;

				finalize();
				System.gc();
			} catch (Throwable e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		try {
			cDevice.release();
		} catch (Exception e) {
		}
	}

	@Override
	protected void onStop() {

		super.onStop();
		try {
			cDevice.release();
		} catch (Exception e) {
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		try {
			Camera.Parameters parameters = cDevice.getParameters();
			parameters.setPreviewSize(width, height);
			cDevice.setParameters(parameters);
			cDevice.startPreview();
		} catch (Exception e) {

		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		cDevice = Camera.open();
		Camera.Parameters parameters = cDevice.getParameters();
		parameters.setPictureFormat(PixelFormat.JPEG);
		
		cDevice.setParameters(parameters);

		
		
		try {
			cDevice.setPreviewDisplay(holder);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		try {
			if (cDevice != null) {
				cDevice.stopPreview();
				cDevice = null;
			}

			finalize();
			System.gc();
		} catch (Exception e) {
		} catch (Throwable e) {
		}
	}

}
