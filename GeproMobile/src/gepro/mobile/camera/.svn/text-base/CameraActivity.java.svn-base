package gepro.mobile.camera;

import gepro.mobile.android.R;
import gepro.mobile.util.GeproKeys;

import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class CameraActivity extends Activity implements CameraCallback,  SensorEventListener  {
	private FrameLayout cameraholder = null;
	private CameraSurface camerasurface = null;	
	 private SensorManager mSensorManager;
     private Sensor mAccelerometer;

     private float positionOnTaken = 0f;
     private boolean takePicture = false;
     
     protected void onResume() {
         super.onResume();
         mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
     }

     protected void onPause() {
         super.onPause();
         mSensorManager.unregisterListener(this);
     }

     public void onAccuracyChanged(Sensor sensor, int accuracy) {
    	 
    	 
     }

     public void onSensorChanged(SensorEvent event) {
    	if(!takePicture)
    	 positionOnTaken =  event.values[2];
     }
     

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_act);

		cameraholder = (FrameLayout) findViewById(R.id.camera_preview);

		setupPictureMode();
		
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

		((ImageButton) findViewById(R.id.takepicture))
				.setOnClickListener(onButtonClick);
	}

	private void setupPictureMode() {
		camerasurface = new CameraSurface(this);

		cameraholder.addView(camerasurface, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

		camerasurface.setCallback(this);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}

	@Override
	public void onJpegPictureTaken(byte[] data, Camera camera) {
		takePicture = true; 
		boolean exception = false;

		String sFileName = String.format(gepro.mobile.util.Config.getCacheDir()
				+ "/%d.jpg", System.currentTimeMillis());

		try {

			FileOutputStream foStream = new FileOutputStream(sFileName);
			foStream.write(data);
			foStream.flush();
			foStream.close();

		} catch (Exception e) {
			exception = true;
			e.printStackTrace();
		}
		finally
		{
			if(camerasurface != null)
				camerasurface.Dispose();
		}

		
		if(positionOnTaken > 45)
		       Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
		else if( positionOnTaken < 45 )
	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
		
		Bundle bundle = new Bundle();
		bundle.putString("URI", sFileName);
		Intent intent = new Intent();
		intent.putExtras(bundle);
		setResult(exception ? GeproKeys.RETURN_CODE.FAIL :  GeproKeys.RETURN_CODE.OK , intent);
		finish();
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
	}

	@Override
	public void onRawPictureTaken(byte[] data, Camera camera) {
	}

	@Override
	public void onShutter() {
	}

	@Override
	public String onGetVideoFilename() {
		String filename = String.format("/sdcard/%d.3gp", System
				.currentTimeMillis());

		return filename;
	}

	private View.OnClickListener onButtonClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.takepicture:
				camerasurface.startTakePicture();
				break;

			}
		}
	};
}